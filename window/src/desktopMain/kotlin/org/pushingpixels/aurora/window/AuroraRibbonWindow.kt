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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
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
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.HorizontalAlignment
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.ribbon.impl.RibbonTaskbar
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getColorSchemeFilter
import org.pushingpixels.aurora.window.ribbon.RibbonBands
import org.pushingpixels.aurora.window.ribbon.RibbonContextualTaskGroupLayoutInfo
import org.pushingpixels.aurora.window.ribbon.RibbonPrimaryBar
import java.awt.*
import java.awt.event.AWTEventListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.max
import kotlin.math.roundToInt

private fun spanInfoMatches(
    ribbon: Ribbon,
    contextualTaskGroupSpans: List<RibbonContextualTaskGroupLayoutInfo>,
): Boolean {
    if (ribbon.contextualTaskGroups.size != contextualTaskGroupSpans.size) {
        return false
    }
    for (contextualTaskGroup in ribbon.contextualTaskGroups) {
        if (contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup } == null) {
            return false
        }
    }
    return true
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraWindowScope.RibbonWindowTitlePaneTextAndIcon(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    ribbon: Ribbon,
    contextualTaskGroupSpans: SnapshotStateList<RibbonContextualTaskGroupLayoutInfo>,
    contextualTaskGroupOffsetX: Int
) {
    val layoutDirection = LocalLayoutDirection.current

    val skinColors = AuroraSkin.colors
    val showsIcon = (icon != null)

    val contextualTaskGroupSeparatorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Separator,
        componentState = ComponentState.Enabled
    )

    // Layout info for the contextual task groups is one frame behind, so we need to test
    // for matching span info
    val spanInfoMatches = spanInfoMatches(ribbon, contextualTaskGroupSpans)

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
                modifier = Modifier.padding(TaskbarContentPadding),
                elements = ribbon.taskbarElements
            )

            AuroraWindowTitlePaneTitleText(title = title)

            if (spanInfoMatches) {
                for (contextualTaskGroupSpan in contextualTaskGroupSpans) {
                    Box {
                        LabelProjection(
                            contentModel = LabelContentModel(text = contextualTaskGroupSpan.ribbonContextualTaskGroup.title),
                            presentationModel = LabelPresentationModel(
                                horizontalAlignment = HorizontalAlignment.Leading,
                                contentPadding = TaskbarContextualTaskGroupTitlePadding
                            )
                        ).project(modifier = Modifier.fillMaxSize())

                        Canvas(modifier = Modifier.fillMaxSize()) {
                            withTransform({
                                clipRect(
                                    left = 0.0f,
                                    top = 0.0f,
                                    right = size.width,
                                    bottom = size.height + 1,
                                    clipOp = ClipOp.Intersect
                                )
                            }) {
                                val hueColor = contextualTaskGroupSpan.ribbonContextualTaskGroup.hueColor

                                // Translucent vertical gradient fill
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        0.0f to hueColor.withAlpha(0.0f),
                                        1.0f to hueColor.withAlpha(0.25f),
                                        startY = 0.0f,
                                        endY = size.height,
                                        tileMode = TileMode.Repeated
                                    )
                                )

                                // Full opacity horizontal line along the bottom edge
                                drawLine(
                                    color = hueColor,
                                    start = Offset(0.0f, size.height - 0.5f),
                                    end = Offset(size.width, size.height - 0.5f),
                                    strokeWidth = 1.5f * density
                                )

                                // Vertical separators along the left and right edges
                                val separatorBrush = Brush.verticalGradient(
                                    0.0f to contextualTaskGroupSeparatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                                    size.height / 3.0f to contextualTaskGroupSeparatorScheme.separatorPrimaryColor,
                                    1.0f to contextualTaskGroupSeparatorScheme.separatorPrimaryColor,
                                    startY = 0.0f,
                                    endY = size.height + 1,
                                    tileMode = TileMode.Repeated
                                )
                                drawLine(
                                    brush = separatorBrush,
                                    start = Offset(0.5f, 0.0f),
                                    end = Offset(0.5f, size.height + 1),
                                    strokeWidth = 1.0f
                                )
                                drawLine(
                                    brush = separatorBrush,
                                    start = Offset(size.width - 0.5f, 0.0f),
                                    end = Offset(size.width - 0.5f, size.height + 1),
                                    strokeWidth = 1.0f
                                )
                            }
                        }
                    }
                }
            }
        }) { measurables, constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()
        val iconSizePx = WindowTitlePaneSizingConstants.TitlePaneAppIconSize.toPx().roundToInt()
        val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
        val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()
        val controlButtonsWidth = 3 * buttonSizePx + regularGapPx + largeGapPx

        var placeableIndex = 0
        val iconMeasurable = if (showsIcon) measurables[placeableIndex++] else null
        val taskbarMeasurable = measurables[placeableIndex++]
        val titleMeasurable = measurables[placeableIndex++]
        val contextualTaskGroupIndicatorMeasurables: List<Measurable> =
            if (spanInfoMatches) contextualTaskGroupSpans.map { measurables[placeableIndex++] } else emptyList()

        val iconPlaceable = iconMeasurable?.measure(Constraints.fixed(width = iconSizePx, height = iconSizePx))
        val maxTaskbarWidth = (width * TaskbarWidthMaxRatio).toInt()
        val taskbarPlaceable = taskbarMeasurable.measure(
            Constraints(
                minWidth = 0,
                maxWidth = maxTaskbarWidth,
                minHeight = height,
                maxHeight = height
            )
        )
        val displayedIconWidth = if (iconMeasurable != null) iconSizePx else 0
        val maxTitleWidth = if (contextualTaskGroupSpans.isEmpty()) {
            max(0, width - controlButtonsWidth - displayedIconWidth - taskbarPlaceable.measuredWidth)
        } else {
            // If we have visible contextual task groups, limit the title to be positioned
            // between the taskbar and the contextual task group indicators
            val contextualTaskGroupsStartX = contextualTaskGroupSpans.minOf { it.startX }
            max(
                0,
                contextualTaskGroupsStartX - contextualTaskGroupOffsetX - displayedIconWidth - taskbarPlaceable.measuredWidth
            )
        }

        val titlePlaceable = titleMeasurable.measure(
            Constraints(
                minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0, maxHeight = height
            )
        )

        val contextualTaskGroupIndicatorPlaceables =
            contextualTaskGroupIndicatorMeasurables.mapIndexed { index, taskGroupMeasurable ->
                val contextualTaskGroup = ribbon.contextualTaskGroups[index]
                val span = contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup }!!
                taskGroupMeasurable.measure(Constraints.fixed(width = span.endX - span.startX, height = height))
            }

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

            for ((index, contextualTaskGroupIndicatorPlaceable) in contextualTaskGroupIndicatorPlaceables.withIndex()) {
                val contextualTaskGroup = ribbon.contextualTaskGroups[index]
                val span = contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup }!!
                contextualTaskGroupIndicatorPlaceable.placeRelative(span.startX - contextualTaskGroupOffsetX, 0)
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
    contextualTaskGroupSpans: SnapshotStateList<RibbonContextualTaskGroupLayoutInfo>,
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
                // Account for the horizontal padding around the title pane content
                val contextualTaskGroupOffsetX =
                    (WindowTitlePaneSizingConstants.TitlePaneContentPadding.calculateStartPadding(layoutDirection).value * density.density).toInt()
                WindowDraggableArea(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {
                    RibbonWindowTitlePaneTextAndIcon(
                        title = title,
                        icon = icon,
                        iconFilterStrategy = iconFilterStrategy,
                        ribbon = ribbon,
                        contextualTaskGroupSpans = contextualTaskGroupSpans,
                        contextualTaskGroupOffsetX = contextualTaskGroupOffsetX
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

private fun areSpansSame(
    list1: List<RibbonContextualTaskGroupLayoutInfo>,
    list2: List<RibbonContextualTaskGroupLayoutInfo>
): Boolean {
    if (list1.size != list2.size) {
        return false
    }

    for (entry in list1) {
        val taskGroup = entry.ribbonContextualTaskGroup

        val matchingEntry = list2.find { it.ribbonContextualTaskGroup == taskGroup }
        if (matchingEntry == null) {
            return false
        }
        if ((entry.startX != matchingEntry.startX) || (entry.endX != matchingEntry.endX)) {
            return false
        }
    }

    return true
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
    val contextualTaskGroupSpans = remember {
        mutableStateListOf<RibbonContextualTaskGroupLayoutInfo>()
    }

    Column(Modifier.fillMaxSize().auroraBackground()) {
        RibbonWindowTitlePane(
            title, icon, iconFilterStrategy, ribbon, contextualTaskGroupSpans,
            windowTitlePaneConfiguration
        )

        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
            Column(Modifier.fillMaxWidth().auroraBackground()) {
                RibbonPrimaryBar(ribbon = ribbon,
                    onContextualTaskGroupSpansUpdated = {
                        if (!areSpansSame(contextualTaskGroupSpans, it)) {
                            contextualTaskGroupSpans.clear()
                            contextualTaskGroupSpans.addAll(it)
                        }
                    })

                RibbonBands(ribbon = ribbon)

                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
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

private val TaskbarWidthMaxRatio = 0.25f
private val TaskbarContentPadding = PaddingValues(horizontal = 6.dp)
private val TaskbarContextualTaskGroupTitlePadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)

