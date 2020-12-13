/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.window

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.WindowDraggableArea
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.components.AmbientStateTransitionTracker
import org.pushingpixels.aurora.components.AuroraButton
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.shaper.AuroraButtonShaper
import java.awt.Frame
import java.awt.image.BufferedImage
import javax.swing.JFrame

@Composable
private fun AuroraWindowContent(
    title: String,
    icon: BufferedImage?,
    undecorated: Boolean,
    content: @Composable () -> Unit
) {
    val density = AmbientDensity.current.density
    val iconSize = (18 * density).toInt()

    val extendedState = AppManager.focusedWindow?.window?.extendedState
    val isMaximized = remember { mutableStateOf(((extendedState != null) && ((extendedState and Frame.MAXIMIZED_BOTH) !== 0))) }

    Column(Modifier.fillMaxSize().auroraBackground()) {
        if (undecorated) {
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.TITLE_PANE) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .auroraBackground()
                        .padding(start = 24.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WindowDraggableArea(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = title, color = Color.White)
                    }
                    AuroraButton(
                        modifier = Modifier.width(20.dp).height(20.dp),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                        sizingStrategy = ButtonSizingStrategy.COMPACT,
                        contentPadding = PaddingValues(start = 1.dp, top = 1.dp, end = 2.dp, bottom = 2.dp),
                        onClick = {
                            AppManager.focusedWindow?.window?.extendedState = JFrame.ICONIFIED
                        }
                    ) {
                        AuroraIcon(
                            icon = TransitionAwareIcon(
                                decorationAreaType = DecorationAreaType.TITLE_PANE,
                                skinColors = AmbientSkinColors.current,
                                buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                                stateTransitionTracker = AmbientStateTransitionTracker.current,
                                delegate = { scheme ->
                                    getMinimizeIcon(
                                        iconSize = iconSize,
                                        scheme = scheme,
                                        density = density
                                    )
                                },
                                colorSchemeAssociationKindDelegate = null,
                                uniqueIconTypeId = "aurora.titlePane.minimizeIcon"
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    AuroraButton(
                        modifier = Modifier.width(20.dp).height(20.dp),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                        sizingStrategy = ButtonSizingStrategy.COMPACT,
                        contentPadding = PaddingValues(start = 1.dp, top = 1.dp, end = 2.dp, bottom = 2.dp),
                        onClick = {
                            val current = AppManager.focusedWindow
                            if (current != null) {
                                if (current.window.extendedState == JFrame.MAXIMIZED_BOTH) {
                                    current.window.extendedState = JFrame.NORMAL
                                } else {
                                    current.window.extendedState = JFrame.MAXIMIZED_BOTH
                                }
                                isMaximized.value = !isMaximized.value
                            }
                        }
                    ) {
                        if (isMaximized.value)
                            AuroraIcon(
                                icon = TransitionAwareIcon(
                                    decorationAreaType = DecorationAreaType.TITLE_PANE,
                                    skinColors = AmbientSkinColors.current,
                                    buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                                    stateTransitionTracker = AmbientStateTransitionTracker.current,
                                    delegate = { scheme ->
                                        getRestoreIcon(
                                            iconSize = iconSize,
                                            scheme = scheme,
                                            density = density
                                        )
                                    },
                                    colorSchemeAssociationKindDelegate = null,
                                    uniqueIconTypeId = "aurora.titlePane.restoreIcon"
                                )
                            ) else AuroraIcon(
                            icon = TransitionAwareIcon(
                                decorationAreaType = DecorationAreaType.TITLE_PANE,
                                skinColors = AmbientSkinColors.current,
                                buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                                stateTransitionTracker = AmbientStateTransitionTracker.current,
                                delegate = { scheme ->
                                    getMaximizeIcon(
                                        iconSize = iconSize,
                                        scheme = scheme,
                                        density = density
                                    )
                                },
                                colorSchemeAssociationKindDelegate = null,
                                uniqueIconTypeId = "aurora.titlePane.maximizeIcon"
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    AuroraButton(
                        modifier = Modifier.width(20.dp).height(20.dp),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                        sizingStrategy = ButtonSizingStrategy.COMPACT,
                        contentPadding = PaddingValues(start = 1.dp, top = 1.dp, end = 2.dp, bottom = 2.dp),
                        onClick = {
                            AppManager.focusedWindow?.close()
                        }
                    ) {
                        AuroraIcon(
                            icon = TransitionAwareIcon(
                                decorationAreaType = DecorationAreaType.TITLE_PANE,
                                skinColors = AmbientSkinColors.current,
                                buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT,
                                stateTransitionTracker = AmbientStateTransitionTracker.current,
                                delegate = { scheme ->
                                    getCloseIcon(
                                        iconSize = iconSize,
                                        scheme = scheme,
                                        density = density
                                    )
                                },
                                colorSchemeAssociationKindDelegate = null,
                                uniqueIconTypeId = "aurora.titlePane.closeIcon"
                            )
                        )
                    }
                }
            }
        }
        content()
    }
}

fun AuroraWindow(
    skin: AuroraSkinDefinition,
    title: String,
    size: IntSize,
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuBar: MenuBar? = null,
    undecorated: Boolean = false,
    events: WindowEvents = WindowEvents(),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit = emptyContent()
) = Window(
    title = title,
    size = size,
    location = location,
    centered = centered,
    icon = icon,
    menuBar = menuBar,
    undecorated = undecorated,
    events = events,
    onDismissRequest = onDismissRequest
) {
    AuroraSkin(
        decorationArea = DecorationArea(DecorationAreaType.NONE),
        colors = skin.colors,
        buttonShaper = skin.buttonShaper,
        painters = skin.painters,
        animationConfig = AuroraSkin.animationConfig
    ) {
        AuroraWindowContent(title = title, icon = icon, undecorated = undecorated, content = content)
    }
}

@Composable
private fun AuroraSkin(
    decorationArea: DecorationArea = AuroraSkin.decorationArea,
    colors: AuroraSkinColors = AuroraSkin.colors,
    buttonShaper: AuroraButtonShaper = AuroraSkin.buttonShaper,
    painters: Painters = AuroraSkin.painters,
    animationConfig: AnimationConfig = AuroraSkin.animationConfig,
    content: @Composable () -> Unit
) {
    Providers(
        AmbientDecorationArea provides decorationArea,
        AmbientSkinColors provides colors,
        AmbientButtonShaper provides buttonShaper,
        AmbientPainters provides painters,
        AmbientAnimationConfig provides animationConfig
    ) {
        content()
    }
}

@Composable
fun AuroraDecorationArea(
    decorationAreaType: DecorationAreaType,
    content: @Composable () -> Unit = emptyContent()
) {
    AuroraSkin(decorationArea = DecorationArea(decorationAreaType)) {
        content()
    }
}
