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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.delay
import org.pushingpixels.aurora.component.AuroraBreadcrumbBar
import org.pushingpixels.aurora.component.model.BreadcrumbBarContentProvider
import org.pushingpixels.aurora.component.model.BreadcrumbBarPresentationModel
import org.pushingpixels.aurora.component.model.BreadcrumbItem
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.io.File
import java.io.InputStream
import java.util.*
import javax.swing.filechooser.FileSystemView

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(400.dp, 200.dp)
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
fun AuroraApplicationScope.BreadcrumbContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    val fileSystemView = FileSystemView.getFileSystemView()
    val contentProvider: BreadcrumbBarContentProvider<File> =
        object : BreadcrumbBarContentProvider<File> {
            override suspend fun getPathChoices(path: List<BreadcrumbItem<File>>): List<BreadcrumbItem<File>> {
                // Sample delay to emulate slow loading of content
                delay(500)
                if (path.isEmpty()) {
                    // Get file system roots, filter out hidden ones, map the rest to
                    // what the content provider needs to return, and sort them by display name
                    return fileSystemView.roots.filterNot { fileSystemView.isHiddenFile(it) }
                        .map {
                            BreadcrumbItem(
                                displayName = fileSystemView.getSystemDisplayName(it)
                                    .let { rootName -> rootName.ifEmpty { it.absolutePath } },
                                icon = null,
                                data = it
                            )
                        }
                        .sortedBy { it.displayName.lowercase() }
                } else {
                    // Get all files under the last file in the path, filter out hidden ones and
                    // non-directory ones, map the rest to what the content provider needs to
                    // return, and sort them by display name
                    println(path.last().data.listFiles())
                    return path.last().data.listFiles()
                        .filterNot { !it.isDirectory || fileSystemView.isHiddenFile(it) }
                        .map {
                            BreadcrumbItem(
                                displayName = fileSystemView.getSystemDisplayName(it)
                                    .let { childName -> childName.ifEmpty { it.absolutePath } },
                                icon = null,
                                data = it
                            )
                        }
                        .sortedBy { it.displayName.lowercase() }
                }
            }

            override suspend fun getLeaves(path: List<BreadcrumbItem<File>>): List<BreadcrumbItem<File>> {
                // Sample delay to emulate slow loading of content
                delay(500)
                // Get all files under the last file in the path, filter out hidden ones and
                // directory ones, map the rest to what the content provider needs to
                // return, and sort them by display name
                return path.last().data.listFiles()
                    .filterNot { it.isDirectory || fileSystemView.isHiddenFile(it) }
                    .map {
                        BreadcrumbItem(
                            displayName = fileSystemView.getSystemDisplayName(it)
                                .let { childName -> childName.ifEmpty { it.absolutePath } },
                            icon = null,
                            data = it
                        )
                    }
                    .sortedBy { it.displayName.lowercase() }
            }

            override suspend fun getLeafContent(leaf: File): InputStream? {
                return null
            }
        }

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            AuroraBreadcrumbBar(
                contentProvider = contentProvider,
                presentationModel = BreadcrumbBarPresentationModel(
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                ),
                modifier = Modifier.fillMaxWidth().auroraBackground()
                    .padding(horizontal = 2.dp, vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1.0f, true))
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .auroraBackground()
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Spacer(modifier = Modifier.weight(1.0f, true))
                AuroraSkinSwitcher(
                    auroraSkinDefinition = auroraSkinDefinition,
                    popupPlacementStrategy = PopupPlacementStrategy.Upward
                )
            }
        }
    }
}





