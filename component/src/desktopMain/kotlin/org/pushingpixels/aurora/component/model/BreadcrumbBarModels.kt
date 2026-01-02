/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.component.model

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.io.InputStream

/**
 * Content provider for a breadcrumb bar.
 */
abstract class BreadcrumbBarContentProvider<T> {
    internal var currentJob: Job? = null

    /** Returns the display text for the item, or for the root is `null` is passed. */
    abstract fun getDisplayText(item: T?): String

    /** Returns the icon for the item, or for the root is `null` is passed. */
    open fun getIcon(item: T?): Painter? = null

    /**
     * Returns the choice elements that correspond to the specified path. If the
     * item is `null`, the list of root elements should be returned.
     *
     * @param item Breadcrumb bar item.
     * @return The choice elements that correspond to the specified item.
     */
    abstract suspend fun getPathChoices(item: T?): List<T>

    /**
     * Returns the leaf elements that correspond to the specified item. If the specified
     * item has no leaf elements, empty list should be returned.
     *
     * @param item Breadcrumb bar item.
     * @return The leaf elements that correspond to the specified item.
     */
    abstract suspend fun getLeaves(item: T): List<T>

    /**
     * Returns the input stream with the leaf content. Some implementations may
     * return `null` if this is not applicable.
     *
     * @param leaf Leaf.
     * @return Input stream with the leaf content. May be `null` if
     * this is not applicable.
     */
    open suspend fun getLeafContent(leaf: T): InputStream? = null
}

suspend fun <T> BreadcrumbBarContentProvider<T>.getPathCommand(
    scope: CoroutineScope,
    commands: SnapshotStateList<Command>,
    item: T?,
    onItemSelected: (T) -> Unit,
    level: Int
): Command {
    // These will be displayed in the dropdown
    val pathChoices = this.getPathChoices(item)

    return Command(
        text = this.getDisplayText(item),
        icon = this.getIcon(item),
        action = {
            currentJob?.cancel()
            // This is called when the path item is clicked
            while (commands.size > level) {
                commands.removeLast()
            }
            scope.launch(Dispatchers.Default) {
                onItemSelected.invoke(item!!)
            }
        },
        secondaryContentModel = if (pathChoices.isNotEmpty()) CommandMenuContentModel(
            group = CommandGroup(title = null,
                commands = pathChoices.map { pathChoice ->
                    Command(text = this.getDisplayText(pathChoice),
                        icon = this.getIcon(pathChoice),
                        action = {
                            // This is called when a dropdown item is clicked
                            while (commands.size > level) {
                                commands.removeLast()
                            }
                            currentJob?.cancel()
                            currentJob = scope.launch(Dispatchers.Default) {
                                commands.add(
                                    getPathCommand(
                                        scope = scope,
                                        commands = commands,
                                        item = pathChoice,
                                        onItemSelected = onItemSelected,
                                        level = level + 1
                                    )
                                )
                                onItemSelected.invoke(pathChoice)
                            }
                        })
                }
            )
        ) else null
    )
}

@Composable
fun <T> BreadcrumbBarContentModel(
    contentProvider: BreadcrumbBarContentProvider<T>,
    onItemSelected: (T) -> Unit
): SnapshotStateList<Command> {
    val commands = remember { mutableStateListOf<Command>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(null) {
        scope.launch(Dispatchers.Default) {
            // Root content for the breadcrumb bar
            commands.add(
                contentProvider.getPathCommand(
                    scope = scope,
                    commands = commands,
                    item = null,
                    onItemSelected = onItemSelected,
                    level = 1
                )
            )
        }
    }

    return commands
}

data class BreadcrumbBarContentModel(val commands: SnapshotStateList<Command>): ContentModel

data class BreadcrumbBarPresentationModel(
    val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val maxVisibleChoiceCommands: Int = 10,
) : PresentationModel
