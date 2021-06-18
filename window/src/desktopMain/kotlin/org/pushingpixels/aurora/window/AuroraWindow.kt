/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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

package org.pushingpixels.aurora.window

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.ComposeWindow
import androidx.compose.desktop.WindowEvents
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowDraggableArea
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.component.utils.TransitionAwareIcon
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.shaper.ClassicButtonShaper
import java.awt.*
import java.awt.event.*
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.SwingUtilities

object WindowSizingConstants {
    val DecoratedBorderThickness = 4.dp

    // The amount of space that the cursor is changed on.
    val CornerDragWidth = 16.dp

    // Region from edges that dragging is active from.
    val BorderDragThickness = 5.dp
}

@Composable
private fun WindowTitlePane(
    title: String,
    icon: BufferedImage?,
    titlePaneBounds: MutableState<Rect>
) {
    val density = LocalDensity.current
    val iconSize = (18 * density.density).toInt()

    val extendedState = AppManager.focusedWindow?.window?.extendedState
    val isMaximized =
        remember { mutableStateOf(((extendedState != null) && ((extendedState and Frame.MAXIMIZED_BOTH) != 0))) }
    val skinColors = AuroraSkin.colors

    CompositionLocalProvider(
        LocalButtonShaper provides ClassicButtonShaper(),
    ) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TitlePane) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .onGloballyPositioned {
                        titlePaneBounds.value = Rect(
                            offset = it.positionInWindow(),
                            size = Size(it.size.width.toFloat(), it.size.height.toFloat())
                        )
                    }
                    .auroraBackground()
                    .padding(start = 24.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WindowDraggableArea(
                    modifier = Modifier.weight(1f)
                ) {
                    val colorScheme =
                        skinColors.getEnabledColorScheme(DecorationAreaType.TitlePane)
                    val titleTextStyle = TextStyle(
                        color = colorScheme.foregroundColor,
                        shadow = Shadow(
                            color = colorScheme.echoColor,
                            blurRadius = density.density
                        )
                    )
                    LabelProjection(
                        contentModel = LabelContentModel(text = title),
                        presentationModel = LabelPresentationModel(textStyle = titleTextStyle)
                    ).project()
                }

                val colors = AuroraSkin.colors

                val titlePaneButtonPresentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Small,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    contentPadding = PaddingValues(
                        start = 1.dp,
                        end = 2.dp,
                        top = 1.dp,
                        bottom = 2.dp
                    ),
                    horizontalGapScaleFactor = 1.0f,
                    verticalGapScaleFactor = 1.0f
                )

                // Minimize button
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        action = {
                            AppManager.focusedWindow?.window?.extendedState =
                                JFrame.ICONIFIED
                        },
                        iconFactory = object :
                            TransitionAwareIcon.TransitionAwareIconFactory() {
                            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon {
                                return TransitionAwareIcon(
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = colors,
                                    buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    delegate = { scheme ->
                                        getMinimizeIcon(
                                            iconSize = iconSize,
                                            scheme = scheme,
                                            density = density.density
                                        )
                                    },
                                    density = density,
                                    colorSchemeAssociationKindDelegate = null,
                                    uniqueIconTypeId = "aurora.titlePane.minimizeIcon"
                                )
                            }
                        }
                    ),
                    presentationModel = titlePaneButtonPresentationModel
                ).project()

                Spacer(modifier = Modifier.width(4.dp))

                // Maximize / Unmaximize button
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        action = {
                            val current = AppManager.focusedWindow
                            if (current != null) {
                                if (current.window.extendedState == JFrame.MAXIMIZED_BOTH) {
                                    current.window.extendedState = JFrame.NORMAL
                                } else {
                                    current.window.extendedState = JFrame.MAXIMIZED_BOTH
                                }
                                isMaximized.value = !isMaximized.value
                            }
                        },
                        iconFactory = object :
                            TransitionAwareIcon.TransitionAwareIconFactory() {
                            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon {
                                return if (isMaximized.value) {
                                    TransitionAwareIcon(
                                        decorationAreaType = DecorationAreaType.TitlePane,
                                        skinColors = colors,
                                        buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                        modelStateInfoSnapshot = modelStateInfoSnapshot,
                                        delegate = { scheme ->
                                            getRestoreIcon(
                                                iconSize = iconSize,
                                                scheme = scheme,
                                                density = density.density
                                            )
                                        },
                                        density = density,
                                        colorSchemeAssociationKindDelegate = null,
                                        uniqueIconTypeId = "aurora.titlePane.restoreIcon"
                                    )
                                } else {
                                    TransitionAwareIcon(
                                        decorationAreaType = DecorationAreaType.TitlePane,
                                        skinColors = colors,
                                        buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                        modelStateInfoSnapshot = modelStateInfoSnapshot,
                                        delegate = { scheme ->
                                            getMaximizeIcon(
                                                iconSize = iconSize,
                                                scheme = scheme,
                                                density = density.density
                                            )
                                        },
                                        density = density,
                                        colorSchemeAssociationKindDelegate = null,
                                        uniqueIconTypeId = "aurora.titlePane.maximizeIcon"
                                    )
                                }
                            }
                        }
                    ),
                    presentationModel = titlePaneButtonPresentationModel
                ).project()

                Spacer(modifier = Modifier.width(8.dp))

                // Close button
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        action = {
                            AppManager.focusedWindow?.close()
                        },
                        iconFactory = object :
                            TransitionAwareIcon.TransitionAwareIconFactory() {
                            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon {
                                return TransitionAwareIcon(
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = colors,
                                    buttonBackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    delegate = { scheme ->
                                        getCloseIcon(
                                            iconSize = iconSize,
                                            scheme = scheme,
                                            density = density.density
                                        )
                                    },
                                    density = density,
                                    colorSchemeAssociationKindDelegate = null,
                                    uniqueIconTypeId = "aurora.titlePane.closeIcon"
                                )
                            }
                        }
                    ),
                    presentationModel = titlePaneButtonPresentationModel
                ).project()
            }
        }
    }
}

@Composable
private fun WindowInnerContent(
    title: String,
    icon: BufferedImage?,
    undecorated: Boolean,
    titlePaneBounds: MutableState<Rect>,
    menuCommands: CommandGroup? = null,
    content: @Composable () -> Unit
) {
    Column(Modifier.fillMaxSize().auroraBackground()) {
        if (undecorated) {
            WindowTitlePane(title, icon, titlePaneBounds)
        }
        if (menuCommands != null) {
            AuroraWindowMenuBar(menuCommands)
        }
        // Wrap the entire content in NONE decoration area. App code can set its
        // own decoration area types on specific parts.
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
            content()
        }
    }
}

private fun Modifier.drawUndecoratedWindowBorder(
    backgroundColorScheme: AuroraColorScheme,
    borderColorScheme: AuroraColorScheme
): Modifier = drawBehind {
    val width: Float = size.width
    val height: Float = size.height
    val thickness = WindowSizingConstants.DecoratedBorderThickness.toPx()
    drawRect(
        color = backgroundColorScheme.lightColor,
        topLeft = Offset(thickness / 2.0f, thickness / 2.0f),
        size = Size(width - thickness, height - thickness),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = thickness)
    )

    val quarterThickness = thickness / 4.0f
    // bottom and right in border ultra dark
    drawLine(
        color = borderColorScheme.ultraDarkColor,
        start = Offset(x = 0f, y = height - quarterThickness / 2.0f),
        end = Offset(x = width, y = height - quarterThickness / 2.0f),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    drawLine(
        color = borderColorScheme.ultraDarkColor,
        start = Offset(x = width - quarterThickness / 2.0f, y = 0f),
        end = Offset(x = width - quarterThickness / 2.0f, y = height),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    // top and left in border dark
    drawLine(
        color = borderColorScheme.darkColor,
        start = Offset(x = 0f, y = quarterThickness / 2.0f),
        end = Offset(x = width, y = quarterThickness / 2.0f),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    drawLine(
        color = borderColorScheme.darkColor,
        start = Offset(x = quarterThickness / 2.0f, y = 0f),
        end = Offset(x = quarterThickness / 2.0f, y = height),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    // inner bottom and right in background mid
    drawLine(
        color = borderColorScheme.midColor,
        start = Offset(
            x = quarterThickness,
            y = height - 1.5f * quarterThickness
        ),
        end = Offset(
            x = width - quarterThickness,
            y = height - 1.5f * quarterThickness
        ),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    drawLine(
        color = borderColorScheme.midColor,
        start = Offset(
            x = width - 1.5f * quarterThickness,
            y = quarterThickness
        ),
        end = Offset(
            x = width - 1.5f * quarterThickness,
            y = height - quarterThickness
        ),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    // inner top and left in background mid
    drawLine(
        color = borderColorScheme.midColor,
        start = Offset(x = quarterThickness, y = 1.5f * quarterThickness),
        end = Offset(x = width - quarterThickness, y = 1.5f * quarterThickness),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
    drawLine(
        color = borderColorScheme.midColor,
        start = Offset(x = 1.5f * quarterThickness, y = quarterThickness),
        end = Offset(x = 1.5f * quarterThickness, y = height - quarterThickness),
        strokeWidth = quarterThickness,
        cap = StrokeCap.Butt
    )
}

@Composable
private fun WindowContent(
    title: String,
    icon: BufferedImage?,
    titlePaneBounds: MutableState<Rect>,
    undecorated: Boolean,
    menuCommands: CommandGroup? = null,
    content: @Composable () -> Unit
) {

    val skinColors = AuroraSkin.colors
    val backgroundColorScheme = skinColors.getBackgroundColorScheme(DecorationAreaType.TitlePane)
    val borderColorScheme = skinColors.getColorScheme(
        DecorationAreaType.TitlePane, ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    if (undecorated) {
        Box(
            Modifier
                .fillMaxSize()
                .drawUndecoratedWindowBorder(
                    backgroundColorScheme = backgroundColorScheme,
                    borderColorScheme = borderColorScheme
                )
                .padding(WindowSizingConstants.DecoratedBorderThickness)
        ) {
            WindowInnerContent(title, icon, undecorated, titlePaneBounds, menuCommands, content)
        }
    } else {
        WindowInnerContent(title, icon, undecorated, titlePaneBounds, menuCommands, content)
    }

    val awtEventListener = remember {
        AWTEventListener { event ->
            val src = event.source
            if (src !is Component) {
                return@AWTEventListener
            }
            if ((event is KeyEvent) && (event.id == KeyEvent.KEY_RELEASED) && (event.keyCode == KeyEvent.VK_ESCAPE)) {
                AuroraPopupManager.hideLastPopup()
            }
            if ((event is MouseEvent) && (event.id == MouseEvent.MOUSE_PRESSED)) {
                val windowAncestor = SwingUtilities.getWindowAncestor(src)
                AuroraPopupManager.hidePopups(windowAncestor as? ComposeWindow)
            }
        }
    }

    DisposableEffect(awtEventListener) {
        Toolkit.getDefaultToolkit().addAWTEventListener(
            awtEventListener,
            AWTEvent.KEY_EVENT_MASK or AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_WHEEL_EVENT_MASK
        )

        onDispose {
            Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener)
        }
    }
}

fun AuroraWindow(
    skin: AuroraSkinDefinition,
    title: String,
    size: IntSize,
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuCommands: CommandGroup? = null,
    undecorated: Boolean = false,
    resizable: Boolean = true,
    events: WindowEvents = WindowEvents(),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit
) =
    SwingUtilities.invokeLater {
        val appWindow = AppWindow(
            title = title,
            size = size,
            location = location,
            centered = centered,
            icon = icon,
            menuBar = null,
            undecorated = undecorated,
            resizable = resizable,
            events = events,
            onDismissRequest = onDismissRequest
        )

        val titlePaneBounds = mutableStateOf(Rect.Zero)

        // Initialize with defaults
        val density = mutableStateOf(Density(1.0f, 1.0f))
        appWindow.show {
            AuroraSkin(
                displayName = skin.displayName,
                decorationAreaType = DecorationAreaType.None,
                colors = skin.colors,
                buttonShaper = skin.buttonShaper,
                painters = skin.painters,
                animationConfig = AuroraSkin.animationConfig
            ) {
                density.value = LocalDensity.current
                WindowContent(
                    title = title,
                    icon = icon,
                    titlePaneBounds = titlePaneBounds,
                    undecorated = undecorated,
                    menuCommands = menuCommands,
                    content = content
                )
            }
        }

        if (undecorated) {
            // This is the underlying Swing JFrame
            val composeWindow = appWindow.window

            val lastCursor = mutableStateOf(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            val awtInputHandler = AWTInputHandler(
                density = density.value,
                window = composeWindow,
                rootPane = composeWindow.rootPane,
                lastCursor = lastCursor,
                titlePaneBounds = titlePaneBounds
            )

            Toolkit.getDefaultToolkit().addAWTEventListener(
                awtInputHandler,
                AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK
            )
        }

        appWindow.window.addWindowFocusListener(object : WindowFocusListener {
            override fun windowGainedFocus(e: WindowEvent) {
            }

            override fun windowLostFocus(e: WindowEvent) {
                for (window in Window.getWindows()) {
                    // Hide all Aurora popup windows when our app window loses focus
                    AuroraPopupManager.hidePopups(null)
                }
            }
        })
    }


fun AuroraWindow(
    skin: MutableState<AuroraSkinDefinition>,
    title: String,
    size: IntSize,
    location: IntOffset = IntOffset.Zero,
    centered: Boolean = true,
    icon: BufferedImage? = null,
    menuCommands: CommandGroup? = null,
    undecorated: Boolean = false,
    resizable: Boolean = true,
    events: WindowEvents = WindowEvents(),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit
) =
    SwingUtilities.invokeLater {
        val appWindow = AppWindow(
            title = title,
            size = size,
            location = location,
            centered = centered,
            icon = icon,
            menuBar = null,
            undecorated = undecorated,
            resizable = resizable,
            events = events,
            onDismissRequest = onDismissRequest
        )

        val titlePaneBounds = mutableStateOf(Rect.Zero)

        // Initialize with defaults
        val density = mutableStateOf(Density(1.0f, 1.0f))
        appWindow.show {
            AuroraSkin(
                displayName = skin.value.displayName,
                decorationAreaType = DecorationAreaType.None,
                colors = skin.value.colors,
                buttonShaper = skin.value.buttonShaper,
                painters = skin.value.painters,
                animationConfig = AuroraSkin.animationConfig
            ) {
                density.value = LocalDensity.current
                WindowContent(
                    title = title,
                    icon = icon,
                    titlePaneBounds = titlePaneBounds,
                    undecorated = undecorated,
                    menuCommands = menuCommands,
                    content = content
                )
            }
        }

        if (undecorated) {
            // This is the underlying Swing JFrame
            val composeWindow = appWindow.window

            val lastCursor = mutableStateOf(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            val awtInputHandler = AWTInputHandler(
                density = density.value,
                window = composeWindow,
                rootPane = composeWindow.rootPane,
                lastCursor = lastCursor,
                titlePaneBounds = titlePaneBounds
            )

            Toolkit.getDefaultToolkit().addAWTEventListener(
                awtInputHandler,
                AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK
            )
        }

        appWindow.window.addWindowFocusListener(object : WindowFocusListener {
            override fun windowGainedFocus(e: WindowEvent) {
            }

            override fun windowLostFocus(e: WindowEvent) {
                for (window in Window.getWindows()) {
                    // Hide all Aurora popup windows when our app window loses focus
                    AuroraPopupManager.hidePopups(null)
                }
            }
        })
    }

@Composable
fun AuroraDecorationArea(
    decorationAreaType: DecorationAreaType,
    content: @Composable () -> Unit
) {
    AuroraSkin(decorationAreaType = decorationAreaType) {
        content()
    }
}

@Composable
private fun AuroraSkin(
    displayName: String = AuroraSkin.displayName,
    decorationAreaType: DecorationAreaType,
    colors: AuroraSkinColors = AuroraSkin.colors,
    buttonShaper: AuroraButtonShaper = AuroraSkin.buttonShaper,
    painters: AuroraPainters = AuroraSkin.painters,
    animationConfig: AnimationConfig = AuroraSkin.animationConfig,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDisplayName provides displayName,
        LocalDecorationAreaType provides decorationAreaType,
        LocalSkinColors provides colors,
        LocalButtonShaper provides buttonShaper,
        LocalPainters provides painters,
        LocalAnimationConfig provides animationConfig
    ) {
        content()
    }
}
