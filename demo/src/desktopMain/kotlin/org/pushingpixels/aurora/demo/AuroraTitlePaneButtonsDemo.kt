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

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.SystemContainerType
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var windowTitlePaneConfiguration by remember {
        mutableStateOf(AuroraWindowTitlePaneConfigurations.AuroraPlain(
            titlePaneButtonsProvider = object : AuroraWindowTitlePaneConfigurations.DefaultTitlePaneButtonsProvider() {
                override val closeButtonProvider: AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider
                    get() = object : AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider {
                        override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                            with(drawScope) {
                                val start = iconSize.toPx() / 4.0f
                                val end = iconSize.toPx() * 0.75f
                                val color = colorTokens.onContainer

                                // Triangle
                                drawLine(
                                    color = color,
                                    start = Offset(start, start),
                                    end = Offset(end, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(end, start),
                                    end = Offset(end, end),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(end, end),
                                    end = Offset(start, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                            }
                        }

                        override fun getContainerColorTokensOverlayProvider(): ContainerColorTokensOverlay.Provider {
                            return ContainerColorTokensOverlay.defaultSystemOverlayProvider(SystemContainerType.Error)
                        }
                    }

                override val restoreButtonProvider: AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider
                    get() = object : AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider {
                        override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                            with(drawScope) {
                                val start = iconSize.toPx() / 4.0f
                                val end = iconSize.toPx() * 0.75f
                                val color = colorTokens.onContainer

                                // Arrow to bottom-left
                                drawLine(
                                    color = color,
                                    start = Offset(start, end),
                                    end = Offset(end, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(start, start),
                                    end = Offset(start, end),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(start, end),
                                    end = Offset(end, end),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                            }
                        }

                        override fun getContainerColorTokensOverlayProvider(): ContainerColorTokensOverlay.Provider {
                            return ContainerColorTokensOverlay.defaultSystemOverlayProvider(SystemContainerType.Success)
                        }
                    }

                override val iconifyButtonProvider: AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider
                    get() = object : AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider {
                        override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                            with(drawScope) {
                                val start = iconSize.toPx() / 4.0f
                                val end = iconSize.toPx() * 0.75f
                                val mid = (start + end) / 2.0f
                                val color = colorTokens.onContainer

                                drawLine(
                                    color = color,
                                    start = Offset(mid, start),
                                    end = Offset(mid, end),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(start, end),
                                    end = Offset(end, end),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                            }
                        }

                        override fun getContainerColorTokensOverlayProvider(): ContainerColorTokensOverlay.Provider {
                            return ContainerColorTokensOverlay.defaultSystemOverlayProvider(SystemContainerType.Warning)
                        }
                    }

                override val maximizeButtonProvider: AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider
                    get() = object : AuroraWindowTitlePaneConfigurations.TitlePaneButtonProvider {
                        override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                            with(drawScope) {
                                val start = iconSize.toPx() / 4.0f
                                val end = iconSize.toPx() * 0.75f
                                val color = colorTokens.onContainer

                                // Arrow to top-right
                                drawLine(
                                    color = color,
                                    start = Offset(start, end),
                                    end = Offset(end, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(start, start),
                                    end = Offset(end, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                                drawLine(
                                    color = color,
                                    start = Offset(end, end),
                                    end = Offset(end, start),
                                    strokeWidth = 1.5f * density,
                                    cap = StrokeCap.Round
                                )
                            }
                        }

                        override fun getContainerColorTokensOverlayProvider(): ContainerColorTokensOverlay.Provider {
                            return ContainerColorTokensOverlay.defaultSystemOverlayProvider(SystemContainerType.Success)
                        }
                    }
            },
            titlePaneTitleTextConfiguration = object: AuroraWindowTitlePaneConfigurations.TitlePaneTitleTextConfiguration {
                override fun getTitleTextStyle(colorTokens: ContainerColorTokens): TextStyle {
                    return TextStyle(
                        color = colorTokens.accentOnContainer,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(color = colorTokens.complementaryOnContainer, blurRadius = 5.0f)
                    )
                }
            }
        ))
    }

    println("Window title pane configuration $windowTitlePaneConfiguration")
    AuroraWindow(
        skin = skin,
        title = "Aurora skeleton",
        state = state,
        windowTitlePaneConfiguration = windowTitlePaneConfiguration,
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = resourceBundle.getString("Menu.file"),
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = resourceBundle.getString("Menu.file.new"),
                                    action = { println("New file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.open"),
                                    action = { println("Open file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.save"),
                                    action = { println("Save file!") })
                            )
                        )
                    )
                ),
                Command(
                    text = resourceBundle.getString("Menu.edit"),
                    action = { println("Edit activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.source"),
                    action = { println("Source activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.refactor"),
                    action = { println("Refactor activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.navigate"),
                    action = { println("Navigate activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.search"),
                    action = { println("Search activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.project"),
                    action = { println("Project activated!") })
            )
        )
    ) {
        DemoTitlePaneContent(resourceBundle,
            { skin = it },
            windowTitlePaneConfiguration, { windowTitlePaneConfiguration = it })
    }
}




