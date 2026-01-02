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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BreadcrumbBarProjection
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.material.folder_open_black_24dp
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
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
    var skin by remember { mutableStateOf(businessSkin()) }

    AuroraWindow(
        skin = skin,
        title = "Breadcrumb Bar Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        BreadcrumbContent(onSkinChange = { skin = it })
    }
}

private suspend fun getCommandPanelContent(
    contentProvider: BreadcrumbBarContentProvider<File>,
    selected: File
): CommandPanelContentModel {
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

@Composable
fun AuroraWindowScope.BreadcrumbContent(onSkinChange: (AuroraSkinDefinition) -> Unit) {
    val scope = rememberCoroutineScope()

    val fileSystemView = FileSystemView.getFileSystemView()
    val breadcrumbBarContentProvider =
        object : BreadcrumbBarContentProvider<File>() {
            override fun getDisplayText(item: File?): String {
                if (item == null) {
                    return ""
                }
                return fileSystemView.getSystemDisplayName(item)
                    .let { name -> name.ifEmpty { item.absolutePath } }
            }

            override fun getIcon(item: File?): Painter? {
                return if (item?.isDirectory == true) folder_open_black_24dp() else null
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

    val commandPanelContentModel = remember { mutableStateOf<CommandPanelContentModel?>(null) }
    val breadcrumbBarHorizontalScrollState = rememberScrollState(0)
    val onBreadcrumbItemSelected: (File) -> Unit = {
        scope.launch(Dispatchers.Default) {
            commandPanelContentModel.value =
                getCommandPanelContent(breadcrumbBarContentProvider, it)
            delay(150)
            breadcrumbBarHorizontalScrollState.animateScrollTo(
                breadcrumbBarHorizontalScrollState.maxValue
            )
        }
    }

    val breadcrumbBarCommands = BreadcrumbBarContentModel(
        contentProvider = breadcrumbBarContentProvider,
        onItemSelected = onBreadcrumbItemSelected
    )
    val breadcrumbBarContentModel = BreadcrumbBarContentModel(breadcrumbBarCommands)

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            BreadcrumbBarProjection(
                contentModel = breadcrumbBarContentModel,
                presentationModel = BreadcrumbBarPresentationModel(
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                )
            ).project(
                modifier = Modifier.fillMaxWidth().auroraBackground()
                    .padding(horizontal = 2.dp, vertical = 4.dp),
                horizontalScrollState = breadcrumbBarHorizontalScrollState
            )
        }

        if (commandPanelContentModel.value == null) {
            Spacer(modifier = Modifier.weight(1.0f, true))
        } else {
            CommandButtonPanelProjection(
                contentModel = commandPanelContentModel.value!!,
                presentationModel = CommandPanelPresentationModel(
                    layoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Adaptive(140.dp)),
                    showGroupLabels = false,
                    useStickyGroupLabels = false,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    commandPresentationState = CommandButtonPresentationState.Medium,
                    commandHorizontalAlignment = HorizontalAlignment.Leading,
                    commandTextOverflow = TextOverflow.Ellipsis,
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
                                    // Convert to list of commands and set as content model
                                    breadcrumbBarContentModel.commands.clear()
                                    for ((index, file) in filePath.withIndex()) {
                                        breadcrumbBarContentModel.commands.add(
                                            breadcrumbBarContentProvider.getPathCommand(
                                                scope = scope,
                                                commands = breadcrumbBarContentModel.commands,
                                                item = file,
                                                onItemSelected = onBreadcrumbItemSelected,
                                                level = index + 1
                                            )
                                        )
                                    }
                                    // And trigger the item selected callback to populate
                                    // our command panel
                                    onBreadcrumbItemSelected.invoke(selected)
                                }
                            }
                        }),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Medium
                    )
                ).project()

                Spacer(modifier = Modifier.width(12.dp))

                AuroraSkinSwitcher(
                    onSkinChange = onSkinChange,
                    popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart
                )
            }
        }
    }
}
