/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraBreadcrumbBar
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.auroraApplication
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    val skin = mutableStateOf(marinerSkin())

    AuroraWindow(
        skin = skin,
        title = "Breadcrumb Bar Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
    ) {
        BreadcrumbContent(skin)
    }
}

@Composable
fun AuroraWindowScope.BreadcrumbContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    val scope = rememberCoroutineScope()

    val fileSystemView = FileSystemView.getFileSystemView()
    val contentProvider: BreadcrumbBarContentProvider<File> =
        object : BreadcrumbBarContentProvider<File> {
            override fun getDisplayText(item: File?): String {
                if (item == null) {
                    return ""
                }
                return fileSystemView.getSystemDisplayName(item)
                    .let { name -> name.ifEmpty { item.absolutePath } }
            }

            override suspend fun getPathChoices(item: File?): List<File> {
                // If our item is null, get the file system roots. Otherwise, get all files under
                // this file item.
                val candidates =
                    (if (item == null) fileSystemView.roots else item.listFiles())
                        ?: return emptyList()

                // Now filter out hidden ones and non-directories, map the rest to
                // what the content provider needs to return, and sort them by display name
                return candidates.filterNot { !it.isDirectory || fileSystemView.isHiddenFile(it) }
                    .map { it }
                    .sortedBy { getDisplayText(it).lowercase() }
            }

            override suspend fun getLeaves(item: File): List<File> {
                // Get all files under the file item, filter out hidden ones and
                // directory ones, map the rest to what the content provider needs to
                // return, and sort them by display name
                val candidates = item.listFiles() ?: return emptyList()
                return candidates
                    .filterNot { it.isDirectory || fileSystemView.isHiddenFile(it) }
                    .map { it }
                    .sortedBy { getDisplayText(it).lowercase() }
            }
        }

    val contentModel = remember { mutableStateListOf<Command>() }

    suspend fun <T> BreadcrumbBarContentProvider<T>.getPathCommand(
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
                // This is called when the path item is clicked
                while (contentModel.size > level) {
                    contentModel.removeLast()
                }
                onItemSelected.invoke(item!!)
            },
            secondaryContentModel = if (pathChoices.isNotEmpty()) CommandMenuContentModel(
                group = CommandGroup(title = null,
                    commands = pathChoices.map { pathChoice ->
                        Command(text = this.getDisplayText(pathChoice),
                            icon = this.getIcon(pathChoice),
                            action = {
                                // This is called when a dropdown item is clicked
                                while (contentModel.size > level) {
                                    contentModel.removeLast()
                                }
                                scope.launch {
                                    contentModel.add(
                                        getPathCommand(
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

    suspend fun getCommandPanelContent(selected: File): CommandPanelContentModel {
        val leaves = contentProvider.getLeaves(selected)
        return CommandPanelContentModel(
            commandGroups = listOf(
                CommandGroup(
                    title = null,
                    leaves.map { leaf ->
                        val extension = leaf.extension.lowercase()

                        val className =
                            "org.pushingpixels.aurora.demo.svg.filetypes.ext_${extension}"
                        var icon: Painter? = null
                        try {
                            val transcodedClass = Class.forName(className)
                            val ctr = transcodedClass.getConstructor()
                            icon = ctr.newInstance() as Painter
                        } catch (_: Throwable) {
                        }

                        Command(
                            text = contentProvider.getDisplayText(leaf),
                            icon = icon,
                            action = {})
                    }
                )
            )
        )
    }

    val commandPanelContentModel = remember { mutableStateOf<CommandPanelContentModel?>(null) }
    LaunchedEffect(null) {
        coroutineScope {
            // Root content for the breadcrumb bar
            contentModel.add(
                contentProvider.getPathCommand(
                    item = null,
                    onItemSelected = { selected: File ->
                        scope.launch {
                            commandPanelContentModel.value = getCommandPanelContent(selected)
                        }
                    },
                    level = 1
                )
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            AuroraBreadcrumbBar(
                contentModel = contentModel,
                presentationModel = BreadcrumbBarPresentationModel(
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                ),
                modifier = Modifier.fillMaxWidth().auroraBackground()
                    .padding(horizontal = 2.dp, vertical = 4.dp)
            )
        }

        if (commandPanelContentModel.value == null) {
            Spacer(modifier = Modifier.weight(1.0f, true))
        } else {
            CommandButtonPanelProjection(
                contentModel = commandPanelContentModel.value!!,
                presentationModel = CommandPanelPresentationModel(
                    layoutFillMode = PanelLayoutFillMode.RowFill,
                    maxColumns = 5,
                    showGroupLabels = false,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    commandPresentationState = CommandButtonPresentationState.Medium,
                    commandHorizontalAlignment = HorizontalAlignment.Leading,
                    iconActiveFilterStrategy = IconFilterStrategy.Original,
                    iconEnabledFilterStrategy = IconFilterStrategy.Original
                )
            ).project(modifier = Modifier.fillMaxWidth().weight(1.0f, true))
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .auroraBackground()
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Spacer(modifier = Modifier.weight(1.0f, true))

                CommandButtonProjection(
                    contentModel = Command(
                        text = "Choose",
                        action = {
                            scope.launch {
                                val chooser = JFileChooser()
                                chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                                val returnCode = chooser.showOpenDialog(window)
                                if (returnCode == JFileChooser.APPROVE_OPTION) {
                                    val selected = chooser.selectedFile
                                    // Build the full path using our FileSystemView to stay
                                    // consistent with the navigation tree
                                    val filePath = arrayListOf(selected)
                                    var currentFile = fileSystemView.getParentDirectory(selected)
                                    while (currentFile != null) {
                                        filePath.add(0, currentFile)
                                        currentFile = fileSystemView.getParentDirectory(currentFile)
                                    }
                                    // Convert to list of commands
                                    contentModel.clear()
                                    for ((index, file) in filePath.withIndex()) {
                                        contentModel.add(
                                            contentProvider.getPathCommand(
                                                item = file,
                                                onItemSelected = { selected: File ->
                                                    scope.launch {
                                                        commandPanelContentModel.value =
                                                            getCommandPanelContent(selected)
                                                    }
                                                },
                                                level = index + 1
                                            )
                                        )
                                    }
                                    commandPanelContentModel.value =
                                        getCommandPanelContent(selected)
                                }
                            }
                        }),
                    presentationModel =
                    CommandButtonPresentationModel(presentationState = CommandButtonPresentationState.Medium)
                ).project()

                Spacer(modifier = Modifier.width(12.dp))

                AuroraSkinSwitcher(
                    auroraSkinDefinition = auroraSkinDefinition,
                    popupPlacementStrategy = PopupPlacementStrategy.Upward
                )
            }
        }
    }
}
