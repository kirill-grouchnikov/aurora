/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.ribbon.impl.RibbonTaskbar
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemeFilter
import java.awt.*
import java.awt.event.AWTEventListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun AuroraWindowScope.RibbonWindowTitlePaneTextAndIcon(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    ribbon: Ribbon
) {
    val layoutDirection = LocalLayoutDirection.current

    val skinColors = AuroraSkin.colors
    val showsIcon = (icon != null)

    Layout(modifier = Modifier.fillMaxWidth(),
        content = {
            if (showsIcon) {
                val scheme = skinColors.getEnabledColorScheme(DecorationAreaType.TitlePane)
                val colorFilter: ColorFilter? = when (iconFilterStrategy) {
                    IconFilterStrategy.ThemedFollowText ->
                        ColorFilter.tint(color = scheme.foregroundColor)

                    IconFilterStrategy.ThemedFollowColorScheme ->
                        getColorSchemeFilter(scheme)

                    IconFilterStrategy.Original -> null
                }
                Box(
                    modifier = Modifier.size(WindowTitlePaneSizingConstants.TitlePaneAppIconSize)
                        .paint(painter = icon!!, colorFilter = colorFilter)
                )
            }

            RibbonTaskbar(
                modifier = Modifier.height(32.dp).padding(horizontal = 6.dp),
                maxWidth = 150.dp,
                elements = ribbon.taskbarElements
            )

            AuroraWindowTitlePaneTitleText(title = title)
        }) { measurables, constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()
        val iconSizePx = WindowTitlePaneSizingConstants.TitlePaneAppIconSize.toPx().roundToInt()
        val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
        val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()
        val controlButtonsWidth = 3 * buttonSizePx + regularGapPx + largeGapPx

        val iconMeasurable = if (showsIcon) measurables[0] else null
        val taskbarMeasurable = if (showsIcon) measurables[1] else measurables[0]
        val titleMeasurable = if (showsIcon) measurables[2] else measurables[1]

        val iconPlaceable = iconMeasurable?.measure(Constraints.fixed(width = iconSizePx, height = iconSizePx))
        val taskbarPlaceable = taskbarMeasurable.measure(Constraints.fixedHeight(height = iconSizePx))
        val maxTitleWidth = max(0, width - 2 * controlButtonsWidth - taskbarPlaceable.measuredWidth)
        val titlePlaceable = titleMeasurable.measure(
            Constraints(
                minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0, maxHeight = height
            )
        )

        layout(width = width, height = height) {
            val ltr = (layoutDirection == LayoutDirection.Ltr)

            if (iconPlaceable == null) {
                val taskbarX = if (ltr) 0 else width - taskbarPlaceable.measuredWidth
                taskbarPlaceable.place(taskbarX, (height - taskbarPlaceable.measuredHeight) / 2)

                val titleX = if (ltr) taskbarPlaceable.measuredWidth
                else (width - taskbarPlaceable.measuredWidth - titlePlaceable.measuredWidth)
                titlePlaceable.place(titleX, (height - titlePlaceable.measuredHeight) / 2)
            } else {
                val iconX: Int
                val taskbarX: Int
                val titleX: Int
                if (ltr) {
                    iconX = 0
                    taskbarX = iconPlaceable.measuredWidth
                    titleX = taskbarX + taskbarPlaceable.measuredWidth
                } else {
                    iconX = width - iconPlaceable.measuredWidth
                    taskbarX = iconX - taskbarPlaceable.measuredWidth
                    titleX = taskbarX - titlePlaceable.measuredWidth
                }
                iconPlaceable.place(iconX, (height - iconPlaceable.measuredHeight) / 2)
                taskbarPlaceable.place(taskbarX, (height - taskbarPlaceable.measuredHeight) / 2)
                titlePlaceable.place(titleX, (height - titlePlaceable.measuredHeight) / 2)
            }
        }
    }
}

@Composable
private fun AuroraWindowScope.RibbonWindowTitlePane(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    ribbon: Ribbon,
    windowConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val extendedState = (window as? Frame)?.extendedState
    val isMaximized =
        remember { mutableStateOf(((extendedState != null) && ((extendedState and Frame.MAXIMIZED_BOTH) != 0))) }
    val skinColors = AuroraSkin.colors

    AuroraDecorationArea(
        decorationAreaType = DecorationAreaType.TitlePane,
        buttonShaper = ClassicButtonShaper.Instance
    ) {
        Layout(
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowTitlePaneSizingConstants.MinimumTitlePaneHeight)
                .auroraBackground()
                .padding(WindowTitlePaneSizingConstants.TitlePaneContentPadding),
            content = {
                WindowDraggableArea(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {
                    RibbonWindowTitlePaneTextAndIcon(
                        title = title,
                        icon = icon,
                        iconFilterStrategy = iconFilterStrategy,
                        ribbon = ribbon
                    )
                }

                // Minimize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.extendedState = JFrame.ICONIFIED
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                colorSchemeBundle = null,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorScheme ->
                                    windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider.drawIcon(
                                        drawScope, iconSize, colorScheme
                                    )
                                },
                                density = density
                            )
                        }
                    }
                ))

                // Maximize / Unmaximize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        val current = (window as? Frame)
                        if (current != null) {
                            if (current.extendedState == JFrame.MAXIMIZED_BOTH) {
                                current.extendedState = JFrame.NORMAL
                            } else {
                                // Note that on some older releases of Java, maximizing a custom-decorated frame
                                // results in that frame overlapping the system taskbar. This behavior has been
                                // fixed in Java 15 with https://bugs.openjdk.java.net/browse/JDK-8176359,
                                // https://bugs.openjdk.java.net/browse/JDK-8231564 and
                                // https://bugs.openjdk.java.net/browse/JDK-8243925.
                                // In addition, https://bugs.openjdk.org/browse/JDK-8231564 backported it to
                                // earlier Java versions.
                                // Since there is no reliable way to detect whether the current runtime has a fix
                                // for this issue, do not try to work around it. If your application is running
                                // into this issue, you will need to use a version of Java that has the fix for it.
                                current.extendedState = JFrame.MAXIMIZED_BOTH
                            }
                            isMaximized.value = !isMaximized.value
                        }
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return if (isMaximized.value) {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    colorSchemeBundle = null,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorScheme ->
                                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider.drawIcon(
                                            drawScope, iconSize, colorScheme
                                        )
                                    },
                                    density = density,
                                )
                            } else {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    colorSchemeBundle = null,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorScheme ->
                                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider.drawIcon(
                                            drawScope, iconSize, colorScheme
                                        )
                                    },
                                    density = density,
                                )
                            }
                        }
                    }
                ))

                // Close button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.dispatchEvent(
                            WindowEvent(
                                window,
                                WindowEvent.WINDOW_CLOSING
                            )
                        )
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                colorSchemeBundle = null,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorScheme ->
                                    windowConfiguration.titlePaneButtonsProvider.closeButtonProvider.drawIcon(
                                        drawScope, iconSize, colorScheme
                                    )
                                },
                                density = density,
                            )
                        }
                    }
                ))
            }) { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()

            val buttonMeasureSpec = Constraints.fixed(width = buttonSizePx, height = buttonSizePx)

            var childIndex = 0

            val titleBoxMeasurable = measurables[childIndex++]
            val minimizeButtonMeasurable = measurables[childIndex++]
            val maximizeButtonMeasurable = measurables[childIndex++]
            val closeButtonMeasurable = measurables[childIndex]

            val minimizeButtonPlaceable = minimizeButtonMeasurable.measure(buttonMeasureSpec)
            val maximizeButtonPlaceable = maximizeButtonMeasurable.measure(buttonMeasureSpec)
            val closeButtonPlaceable = closeButtonMeasurable.measure(buttonMeasureSpec)

            val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
            val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()

            val titleWidth = max(
                0, width -
                        (minimizeButtonPlaceable.width + regularGapPx +
                                maximizeButtonPlaceable.width + largeGapPx +
                                closeButtonPlaceable.width)
            )

            val titleBoxPlaceable = titleBoxMeasurable.measure(
                Constraints.fixed(width = titleWidth, height = height)
            )

            layout(width = width, height = height) {
                val controlButtonsOnRight = windowConfiguration.areTitlePaneControlButtonsOnRight(layoutDirection)

                val buttonY = (height - buttonSizePx) / 2

                var x = if (controlButtonsOnRight) width else 0
                if (controlButtonsOnRight) {
                    x -= buttonSizePx
                }
                closeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-largeGapPx - buttonSizePx) else largeGapPx
                maximizeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-regularGapPx - buttonSizePx) else regularGapPx
                minimizeButtonPlaceable.place(x = x, y = buttonY)
                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                if (controlButtonsOnRight) {
                    titleBoxPlaceable.place(0, 0)
                } else {
                    titleBoxPlaceable.place(x, 0)
                }
            }
        }
    }
}

@Composable
private fun AuroraWindowScope.RibbonWindowInnerContent(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain,
    ribbon: Ribbon,
    content: @Composable AuroraWindowScope.() -> Unit
) {
    Column(Modifier.fillMaxSize().auroraBackground()) {
        RibbonWindowTitlePane(title, icon, iconFilterStrategy, ribbon, windowTitlePaneConfiguration)
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            Box(modifier = Modifier.auroraBackground().fillMaxWidth().padding(all = 4.dp)) {
                ribbon.applicationMenuCommandButtonProjection?.project()
            }
        }
        // Wrap the entire content in NONE decoration area. App code can set its
        // own decoration area types on specific parts.
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
            content()
        }
    }
}

@AuroraInternalApi
@Composable
fun AuroraWindowScope.AuroraRibbonWindowContent(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain,
    ribbon: Ribbon,
    content: @Composable AuroraWindowScope.() -> Unit
) {

    val skinColors = AuroraSkin.colors
    val backgroundColorScheme = skinColors.getBackgroundColorScheme(DecorationAreaType.TitlePane)
    val borderColorScheme = skinColors.getColorScheme(
        DecorationAreaType.TitlePane, ColorSchemeAssociationKind.Border, ComponentState.Enabled
    )

    Box(
        Modifier
            .fillMaxSize()
            .drawAuroraWindowBorder(
                backgroundColorScheme = backgroundColorScheme,
                borderColorScheme = borderColorScheme
            )
            .padding(WindowSizingConstants.DecoratedBorderThickness)
    ) {
        RibbonWindowInnerContent(
            title,
            icon,
            iconFilterStrategy,
            windowTitlePaneConfiguration,
            ribbon,
            content
        )
    }

    val awtEventListener = remember(this, window) {
        AWTEventListener { event ->
            val src = event.source
            if ((event is KeyEvent) && (event.id == KeyEvent.KEY_RELEASED)
                && (event.keyCode == KeyEvent.VK_ESCAPE)
            ) {
                AuroraPopupManager.hideLastPopup()
            }
            if ((event is MouseEvent) && (event.id == MouseEvent.MOUSE_PRESSED) && (src is Component)) {
                // This can be in our custom popup menu or in the top-level window
                val originator = SwingUtilities.getAncestorOfClass(AuroraSwingPopupMenu::class.java, src)
                    ?: SwingUtilities.getWindowAncestor(src)
                if (originator != null) {
                    val eventLocation = event.locationOnScreen
                    SwingUtilities.convertPointFromScreen(eventLocation, originator)

                    if (!AuroraPopupManager.isShowingPopupFrom(
                            originator = originator,
                            pointInOriginator = Offset(eventLocation.x.toFloat(), eventLocation.y.toFloat())
                        )
                    ) {
                        AuroraPopupManager.hidePopups(originator)
                    }
                }
            }
        }
    }

    DisposableEffect(this, window) {
        Toolkit.getDefaultToolkit().addAWTEventListener(
            awtEventListener,
            AWTEvent.KEY_EVENT_MASK or AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_WHEEL_EVENT_MASK
        )

        onDispose {
            Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener)
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraApplicationScope.AuroraRibbonWindow(
    skin: AuroraSkinDefinition,
    onCloseRequest: () -> Unit,
    state: WindowState = rememberWindowState(),
    visible: Boolean = true,
    title: String = "Untitled",
    icon: Painter? = null,
    iconFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    resizable: Boolean = true,
    enabled: Boolean = true,
    focusable: Boolean = true,
    alwaysOnTop: Boolean = false,
    onPreviewKeyEvent: (androidx.compose.ui.input.key.KeyEvent) -> Boolean = { false },
    onKeyEvent: (androidx.compose.ui.input.key.KeyEvent) -> Boolean = { false },
    ribbon: Ribbon,
    content: @Composable AuroraWindowScope.() -> Unit
) {
    val density = mutableStateOf(Density(1.0f, 1.0f))

    Window(
        onCloseRequest = onCloseRequest,
        state = state,
        visible = visible,
        title = title,
        icon = icon,
        undecorated = true,
        resizable = resizable,
        enabled = enabled,
        focusable = focusable,
        alwaysOnTop = alwaysOnTop,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent
    ) {
        val swingComponentOrientation = ComponentOrientation.getOrientation(applicationLocale)
        val composeLayoutDirection = if (swingComponentOrientation.isLeftToRight)
            LayoutDirection.Ltr else LayoutDirection.Rtl
        val titlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain()
        // Get the current composition context
        CompositionLocalProvider(
            LocalWindow provides window,
            LocalWindowSize provides state.size,
            LocalTopWindowSize provides state.size,
            LocalLayoutDirection provides composeLayoutDirection
        ) {
            val auroraWindowScope = AuroraWindowScopeImpl(this@AuroraRibbonWindow, this, titlePaneConfiguration)
            AuroraSkin(
                displayName = skin.displayName,
                decorationAreaType = DecorationAreaType.None,
                colors = skin.colors,
                buttonShaper = skin.buttonShaper,
                painters = skin.painters,
                animationConfig = AuroraSkin.animationConfig
            ) {
                density.value = LocalDensity.current
                auroraWindowScope.AuroraRibbonWindowContent(
                    title = title,
                    icon = icon,
                    iconFilterStrategy = iconFilterStrategy,
                    windowTitlePaneConfiguration = titlePaneConfiguration,
                    ribbon = ribbon,
                    content = content
                )
            }
        }

        LaunchedEffect(Unit) {
            val lastCursor = mutableStateOf(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
            val awtInputHandler = AWTInputHandler(
                density = density.value,
                window = window,
                rootPane = window.rootPane,
                lastCursor = lastCursor
            )

            Toolkit.getDefaultToolkit().addAWTEventListener(
                awtInputHandler,
                AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK
            )
        }
    }
}
