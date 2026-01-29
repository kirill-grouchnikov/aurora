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
package org.pushingpixels.aurora.window

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.ribbon.RibbonTask
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.component.utils.popup.GeneralCommandMenuPopupHandler
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerColorTokensFilter
import org.pushingpixels.aurora.window.ribbon.*
import java.awt.*
import java.awt.event.*
import javax.swing.JFrame
import javax.swing.JRootPane
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
internal fun AuroraWindowScope.RibbonWindowTitlePaneMainContent(
    titleTextConfiguration: AuroraWindowTitlePaneConfigurations.TitlePaneTitleTextConfiguration,
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    ribbon: Ribbon,
    contextualTaskGroupSpans: SnapshotStateList<RibbonContextualTaskGroupLayoutInfo>,
    contextualTaskGroupOffsetX: Int
) {
    val layoutDirection = LocalLayoutDirection.current
    val ltr = (layoutDirection == LayoutDirection.Ltr)

    val skinColors = AuroraSkin.colors
    val showsIcon = (icon != null)

    val contextualTaskGroupSeparatorTokens = AuroraSkin.colors.getNeutralContainerTokens(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Separator,
    )

    // Layout info for the contextual task groups is one frame behind, so we need to test
    // for matching span info
    val spanInfoMatches = spanInfoMatches(ribbon, contextualTaskGroupSpans)

    Layout(modifier = Modifier.fillMaxWidth(),
        content = {
            if (showsIcon) {
                val colorTokens = skinColors.getNeutralContainerTokens(DecorationAreaType.TitlePane)
                val colorFilter: ColorFilter? = when (iconFilterStrategy) {
                    IconFilterStrategy.ThemedFollowText ->
                        ColorFilter.tint(color = colorTokens.onContainer)

                    IconFilterStrategy.ThemedFollowColorTokens ->
                        getContainerColorTokensFilter(colorTokens)

                    IconFilterStrategy.Original -> null
                }
                Box(
                    modifier = Modifier.size(WindowTitlePaneSizingConstants.TitlePaneAppIconSize)
                        .paint(painter = icon, colorFilter = colorFilter)
                )
            }

            CompositionLocalProvider(
                LocalRibbonTrackBounds provides true,
                LocalRibbonTrackKeyTips provides true
            ) {
                RibbonTaskbar(
                    modifier = Modifier.padding(TaskbarContentPadding),
                    elements = ribbon.taskbarElements,
                    taskbarKeyTipPolicy = ribbon.taskbarKeyTipPolicy
                )
            }

            AuroraWindowTitlePaneTitleText(titleTextConfiguration = titleTextConfiguration, title = title)

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

                                val separatorPrimaryColor = if (contextualTaskGroupSeparatorTokens.isDark) {
                                    contextualTaskGroupSeparatorTokens.complementaryContainerOutline.withAlpha(0.28125f)
                                } else {
                                    contextualTaskGroupSeparatorTokens.containerOutline.withAlpha(0.375f)
                                }

                                // Vertical separators along the left and right edges
                                val separatorBrush = Brush.verticalGradient(
                                    0.0f to separatorPrimaryColor.withAlpha(0.0f),
                                    size.height / 3.0f to separatorPrimaryColor,
                                    1.0f to separatorPrimaryColor,
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

        val contextualTaskGroupIndicatorPlaceables =
            contextualTaskGroupIndicatorMeasurables.mapIndexed { index, taskGroupMeasurable ->
                val contextualTaskGroup = ribbon.contextualTaskGroups[index]
                val span = contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup }!!
                taskGroupMeasurable.measure(Constraints.fixed(width = span.endX - span.startX, height = height))
            }
        val contextualTaskGroupMinX = if (contextualTaskGroupSpans.isEmpty()) {
            0
        } else {
            contextualTaskGroupSpans.minOf { it.startX }
        }
        val contextualTaskGroupMaxX = if (contextualTaskGroupSpans.isEmpty()) {
            0
        } else {
            var localMax = 0
            for ((index, contextualTaskGroupIndicatorPlaceable) in contextualTaskGroupIndicatorPlaceables.withIndex()) {
                val contextualTaskGroup = ribbon.contextualTaskGroups[index]
                val span = contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup }!!
                localMax = max(localMax, span.startX + contextualTaskGroupIndicatorPlaceable.measuredWidth)
            }
            localMax
        }

        val isTitleAfterTaskBar: Boolean
        val maxTitleWidth: Int
        if (contextualTaskGroupSpans.isEmpty()) {
            isTitleAfterTaskBar = true
            maxTitleWidth = max(0, width - controlButtonsWidth - displayedIconWidth - taskbarPlaceable.measuredWidth)
        } else {
            // We have visible contextual task groups. There are two places the title can go:
            //  1. Between the taskbar and the contextual task group indicators
            //  2. Between the contextual task group indicators and the window control buttons
            // Choose the larger span
            val spaceBeforeContextualTaskGroups = if (ltr) {
                val endOfTaskbar = displayedIconWidth + taskbarPlaceable.measuredWidth
                contextualTaskGroupMinX - contextualTaskGroupOffsetX - endOfTaskbar
            } else {
                val startOfTaskbar = width - displayedIconWidth - taskbarPlaceable.measuredWidth
                startOfTaskbar - (width - contextualTaskGroupMinX) - contextualTaskGroupOffsetX
            }
            val spaceAfterContextualTaskGroups = if (ltr) {
                width - controlButtonsWidth - contextualTaskGroupMaxX
            } else {
                (width - contextualTaskGroupMaxX) - controlButtonsWidth
            }

            isTitleAfterTaskBar = (spaceBeforeContextualTaskGroups >= spaceAfterContextualTaskGroups)
            maxTitleWidth = max(spaceBeforeContextualTaskGroups, spaceAfterContextualTaskGroups)
        }

        val titlePlaceable = titleMeasurable.measure(
            Constraints(
                minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0, maxHeight = height
            )
        )

        layout(width = width, height = height) {

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
                if (isTitleAfterTaskBar) {
                    titlePlaceable.place(titleX, (height - titlePlaceable.measuredHeight) / 2)
                }
            }

            for ((index, contextualTaskGroupIndicatorPlaceable) in contextualTaskGroupIndicatorPlaceables.withIndex()) {
                val contextualTaskGroup = ribbon.contextualTaskGroups[index]
                val span = contextualTaskGroupSpans.find { it.ribbonContextualTaskGroup == contextualTaskGroup }!!
                contextualTaskGroupIndicatorPlaceable.placeRelative(span.startX - contextualTaskGroupOffsetX, 0)
            }
            if (!isTitleAfterTaskBar) {
                if (ltr) {
                    titlePlaceable.place(contextualTaskGroupMaxX,
                        (height - titlePlaceable.measuredHeight) / 2)
                } else {
                    titlePlaceable.place((width - contextualTaskGroupMaxX) - titlePlaceable.measuredWidth,
                        (height - titlePlaceable.measuredHeight) / 2)
                }
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
                    RibbonWindowTitlePaneMainContent(
                        titleTextConfiguration = windowConfiguration.titlePaneTitleTextConfiguration,
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
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .iconifyButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density
                            )
                        }
                    }),
                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider
                        .getContainerColorTokensOverlayProvider())

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
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return if (isMaximized.value) {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .restoreButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            } else {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .maximizeButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            }
                        }
                    }),
                    tokensOverlayProvider = if (isMaximized.value) {
                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider
                    } else {
                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider
                    }.getContainerColorTokensOverlayProvider())

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
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .closeButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.closeButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density,
                            )
                        }
                    }),
                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.closeButtonProvider
                        .getContainerColorTokensOverlayProvider())
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

private fun Ribbon.getSelectedTask(): RibbonTask {
    var selectedTask: RibbonTask? = null
    for (task in this.tasks) {
        if (task.isActive) {
            selectedTask = task
        }
    }
    for (contextualTaskGroup in this.contextualTaskGroups) {
        for (contextualTask in contextualTaskGroup.tasks) {
            if (contextualTask.isActive) {
                selectedTask = contextualTask
            }
        }
    }

    require(selectedTask != null) {
        "Ribbon needs one task to be marked as active"
    }

    return selectedTask
}

@OptIn(AuroraInternalApi::class)
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
    var showSelectedTaskInPopup by remember { mutableStateOf(false) }

    val selectedTask = ribbon.getSelectedTask()
    val contentModelState = rememberUpdatedState(RibbonTaskCollapsedMenuContentModel(
        ribbonTask = selectedTask,
        onDeactivatePopup = {
            showSelectedTaskInPopup = false
        }
    ))

    val ribbonPrimaryBarTopLeftOffset = remember { RibbonOffset(0.0f, 0.0f) }
    val ribbonPrimaryBarSize = remember { mutableStateOf(IntSize(0, 0)) }
    val ribbonSelectedButtonTopLeftOffset = remember { RibbonOffset(0.0f, 0.0f) }
    val ribbonSelectedButtonSize = remember { mutableStateOf(IntSize(0, 0)) }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            RibbonBox(Modifier.fillMaxWidth()) {
                CompositionLocalProvider(
                    LocalRibbonKeyTipChainRoot provides ribbon,
                    LocalRibbonKeyTipChainRootKeyTip provides null,
                ) {
                    Column(Modifier.fillMaxWidth().ribbonContextMenu(ribbon)) {
                        RibbonWindowTitlePane(
                            title, icon, iconFilterStrategy, ribbon, contextualTaskGroupSpans,
                            windowTitlePaneConfiguration
                        )
                        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
                            CompositionLocalProvider(
                                LocalRibbonTrackBounds provides true,
                                LocalRibbonTrackKeyTips provides true,
                            ) {
                                Column(Modifier.fillMaxWidth().auroraBackground()) {
                                    RibbonPrimaryBar(
                                        modifier = Modifier.ribbonElementLocator(
                                            ribbonPrimaryBarTopLeftOffset,
                                            ribbonPrimaryBarSize
                                        ),
                                        ribbon = ribbon,
                                        onContextualTaskGroupSpansUpdated = {
                                            if (!areSpansSame(contextualTaskGroupSpans, it)) {
                                                contextualTaskGroupSpans.clear()
                                                contextualTaskGroupSpans.addAll(it)
                                            }
                                        },
                                        selectedTaskButtonModifier = Modifier.ribbonElementLocator(
                                            ribbonSelectedButtonTopLeftOffset,
                                            ribbonSelectedButtonSize
                                        ),
                                        showSelectedTaskInPopup = showSelectedTaskInPopup,
                                        onUpdateShowSelectedTaskInPopup = {
                                            if (ribbon.isMinimized) {
                                                showSelectedTaskInPopup = it
                                            }
                                        }
                                    )

                                    if (!ribbon.isMinimized) {
                                        RibbonBands(ribbonTask = selectedTask)
                                    }

                                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                                }
                            }
                        }
                    }
                }
                //RibbonOverlay(Modifier, WindowSizingConstants.DecoratedBorderThickness)
            }
            Box(modifier = Modifier.fillMaxWidth().weight(1.0f)) {
                // Wrap the entire content in NONE decoration area. App code can set its
                // own decoration area types on specific parts.
                AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
                    content()
                }
            }
        }
        RibbonKeyTipOverlay(Modifier.fillMaxSize(), WindowSizingConstants.DecoratedBorderThickness)
    }

    val density = LocalDensity.current

    // This needs to use rememberUpdatedState. Otherwise switching locale to RTL will
    // not properly propagate in here.
    val layoutDirection by rememberUpdatedState(LocalLayoutDirection.current)
    val mergedTextStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper
    val decorationAreaType = AuroraSkin.decorationAreaType
    val popupOriginator = LocalPopupMenu.current ?: LocalWindow.current.rootPane
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)
    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }
    val coroutineScope = rememberCoroutineScope()

    val bandContentHeight = getBandContentHeight(layoutDirection, density, resolvedTextStyle, fontFamilyResolver, buttonShaper)
    val bandTitleHeight = getBandTitleHeight(layoutDirection, density, resolvedTextStyle, fontFamilyResolver)
    val bandFullHeight = (bandContentHeight + bandTitleHeight)

    LaunchedEffect(showSelectedTaskInPopup) {
        if (showSelectedTaskInPopup) {
            // TODO - need command overlays?
            val popupWindow = RibbonTaskCollapsedCommandMenuPopupHandler.showPopupContent(
                popupOriginator = popupOriginator,
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver,
                skinColors = skinColors,
                skinPainters = painters,
                buttonShaper = buttonShaper,
                decorationAreaType = decorationAreaType,
                compositionLocalContext = compositionLocalContext,
                anchorBoundsInWindow = Rect(
                    offset = ribbonPrimaryBarTopLeftOffset.asOffset(density),
                    size = ribbonPrimaryBarSize.value.asSize(density)
                ),
                popupTriggerAreaInWindow = Rect(
                    offset = RibbonOffset(
                        x = ribbonSelectedButtonTopLeftOffset.x,
                        y = ribbonSelectedButtonTopLeftOffset.y
                    ).asOffset(density),
                    size = ribbonSelectedButtonSize.value.asSize(density)
                ),
                contentModel = contentModelState,
                presentationModel = RibbonTaskCollapsedCommandPopupMenuPresentationModel(
                    taskWidth = ribbonPrimaryBarSize.value.width,
                    taskHeight = bandFullHeight
                ),
                displayPrototypeCommand = null,
                toDismissPopupsOnActivation = true,
                popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                popupAnchorBoundsProvider = null,
                popupOriginatorKeyTip = contentModelState.value.ribbonTask.keyTip,
                overlays = mapOf(),
                popupKind = AuroraPopupManager.PopupKind.Popup
            )
            coroutineScope.launch {
                popupWindow?.opacity = 1.0f
            }
        } else {
            AuroraPopupManager.hidePopups(
                originator = popupOriginator,
                popupKind = AuroraPopupManager.PopupKind.Popup
            )
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
    val neutralColorTokens = skinColors.getNeutralContainerTokens(DecorationAreaType.TitlePane)
    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .auroraWindowBorder(neutralColorTokens)
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

    val prevAltModif = remember(this, window) { mutableStateOf(false) }
    val awtEventListener = remember(this, window) {
        AWTEventListener { event ->
            val src = event.source
            if ((event is KeyEvent) && (event.id == KeyEvent.KEY_RELEASED)) {
                val wasAltModif: Boolean = prevAltModif.value
                prevAltModif.value = (event.modifiersEx == InputEvent.ALT_DOWN_MASK)
                if (wasAltModif && event.keyCode == KeyEvent.VK_ALT) {
                    return@AWTEventListener
                }

                val keyChar: Char = event.keyChar
                if (Character.isLetter(keyChar) || Character.isDigit(keyChar)) {
                    println("Pressed $keyChar")
                    KeyTipTracker.handleKeyPress(coroutineScope, keyChar)
                }

                if ((event.keyCode == KeyEvent.VK_ALT) || (event.keyCode == KeyEvent.VK_F10)) {
                    if (event.modifiersEx != 0) {
                        return@AWTEventListener
                    }
                    val hadPopups: Boolean = AuroraPopupManager.isShowingPopups()
                    AuroraPopupManager.hidePopups(null)
                    println("Pressed ALT / F10")
                    if (hadPopups || KeyTipTracker.isShowingKeyTips()) {
                        KeyTipTracker.hideAllKeyTips()
                    } else {
                        KeyTipTracker.showRootKeyTipChain(ribbon)
                    }
                }
                if (event.keyCode == KeyEvent.VK_ESCAPE) {
                    println("Pressed ESCAPE")
                    // Hide last shown popup
                    AuroraPopupManager.hideLastPopup()
                    // Dismiss currently shown key tip chain
                    if (KeyTipTracker.isShowingKeyTips()) {
                        KeyTipTracker.showPreviousChain()
                    }
                }
                if (KeyTipTracker.isShowingKeyTips()) {
                    // Traversal of ribbon tasks while keytips are showing
                    when (event.keyCode) {
                        KeyEvent.VK_LEFT -> {
                            println("Pressed LEFT")
                            val selectedIndex = ribbon.tasks.indexOfFirst { it == ribbon.getSelectedTask() }
                            if (selectedIndex > 0) {
                                ribbon.tasks[selectedIndex - 1].onClick.invoke()
                            }
                        }

                        KeyEvent.VK_RIGHT -> {
                            println("Pressed RIGHT")
                            val selectedIndex = ribbon.tasks.indexOfFirst { it == ribbon.getSelectedTask() }
                            if ((selectedIndex >= 0) && (selectedIndex < (ribbon.tasks.size - 1))) {
                                ribbon.tasks[selectedIndex + 1].onClick.invoke()
                            }
                        }
                    }
                }
            }
            if ((event is MouseEvent) && (event.id == MouseEvent.MOUSE_PRESSED) && (src is Component)) {
                // This can be in our custom popup menu or in the top-level window
                var originator = SwingUtilities.getAncestorOfClass(AuroraSwingPopupMenu::class.java, src)
                    ?: SwingUtilities.getWindowAncestor(src)
                if (originator is JFrame) {
                    originator = originator.rootPane
                }
                if (originator != null) {
                    val eventLocation = event.locationOnScreen
                    SwingUtilities.convertPointFromScreen(eventLocation, originator)

                    val showingFromHere = AuroraPopupManager.isShowingPopupFrom(
                        originator = originator,
                        pointInOriginator = Offset(eventLocation.x.toFloat(), eventLocation.y.toFloat())
                    )
                    if (!showingFromHere) {
                        // Mouse press on an area that doesn't have any popups originating in it.
                        // Hide popups.
                        AuroraPopupManager.hidePopups(originator)
                    }
                }
            }
            if (event.javaClass.simpleName == "UngrabEvent") {
                // Not the cleanest, but works for now
                AuroraPopupManager.hidePopups(null)
            }
        }
    }

    DisposableEffect(this, window) {
        // 0x80000000 is the mask for the internal sun.awt.SunToolkit.GRAB_EVENT_MASK which we need
        // to detect when our window is "ungrabbed". When that happens, we should hide all popups
        // shown from our window.
        Toolkit.getDefaultToolkit().addAWTEventListener(
            awtEventListener,
            AWTEvent.KEY_EVENT_MASK or AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_WHEEL_EVENT_MASK or 0x80000000
        )

        onDispose {
            Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener)
        }
    }
}

private class AWTRibbonInputHandler(
    density: Density,
    window: Window,
    rootPane: JRootPane,
    lastCursor: MutableState<Cursor?>
) : AWTInputHandler(density, window, rootPane, lastCursor) {

    @OptIn(AuroraInternalApi::class)
    override fun mouseReleased(pointInWindow: Point, w: Window) {
        KeyTipTracker.hideAllKeyTips()
        super.mouseReleased(pointInWindow, w)
    }

    @OptIn(AuroraInternalApi::class)
    override fun mousePressed(pointInWindow: Point, w: Window) {
        KeyTipTracker.hideAllKeyTips()
        super.mousePressed(pointInWindow, w)
    }

    @OptIn(AuroraInternalApi::class)
    override fun mouseDragged(pointInWindow: Point, w: Window, ev: MouseEvent) {
        KeyTipTracker.hideAllKeyTips()
        super.mouseDragged(pointInWindow, w, ev)
    }

    @OptIn(AuroraInternalApi::class)
    override fun mouseClicked(pointInWindow: Point, w: Window) {
        KeyTipTracker.hideAllKeyTips()
        super.mouseClicked(pointInWindow, w)
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
            val awtInputHandler = AWTRibbonInputHandler(
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

private data class RibbonOffset(var x: Float, var y: Float)

private fun RibbonOffset.asOffset(density: Density): Offset {
    return Offset(x / density.density, y / density.density)
}

private fun IntSize.asSize(density: Density): Size {
    return Size(width / density.density, height / density.density)
}

private class RibbonTaskLocator(val topLeftOffset: RibbonOffset, val size: MutableState<IntSize>) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size
    }
}

@Composable
private fun Modifier.ribbonElementLocator(topLeftOffset: RibbonOffset, size: MutableState<IntSize>) =
    this.then(
        RibbonTaskLocator(topLeftOffset, size)
    )

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.ribbonContextMenu(ribbon: Ribbon): Modifier {
    if (ribbon.onShowContextualMenuListener == null) {
        return this
    }

    val density = LocalDensity.current
    // This needs to use rememberUpdatedState. Otherwise switching locale to RTL will
    // not properly propagate in here.
    val layoutDirection by rememberUpdatedState(LocalLayoutDirection.current)
    val mergedTextStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters
    val buttonShaper = AuroraSkin.buttonShaper
    val decorationAreaType = AuroraSkin.decorationAreaType
    val popupOriginator = LocalPopupMenu.current ?: LocalWindow.current.rootPane
    val compositionLocalContext by rememberUpdatedState(currentCompositionLocalContext)

    val resolvedTextStyle = remember { resolveDefaults(mergedTextStyle, layoutDirection) }

    val coroutineScope = rememberCoroutineScope()

    val contentModel = remember {
        mutableStateOf(
            CommandMenuContentModel(
                groups = listOf(
                    CommandGroup(
                        commands = listOf(
                            Command(text = "",
                                action = {})
                        )
                    ),
                )
            )
        )
    }

    return this.then(Modifier.pointerInput(Unit) {
        while (true) {
            val lastMouseEvent = awaitPointerEventScope { awaitPointerEvent() }.awtEventOrNull

            if (lastMouseEvent?.isPopupTrigger == true) {
                // AWT event coordinates are in scaled pixels (equivalent to Dp in Compose).
                // Convert them to the underlying pixels using the density factor.
                val eventX = lastMouseEvent.x * density.density
                val eventY = lastMouseEvent.y * density.density

                val ribbonGallery = getGalleryProjectionUnder(eventX, eventY)
                if (ribbonGallery != null) {
                    contentModel.apply {
                        value = ribbon.onShowContextualMenuListener!!.getContextualMenuContentModel(
                            ribbon = ribbon,
                            galleryProjection = ribbonGallery
                        )
                    }
                } else {
                    val ribbonComponent = getComponentProjectionUnder(eventX, eventY)
                    if (ribbonComponent != null) {
                        contentModel.apply {
                            value = ribbon.onShowContextualMenuListener!!.getContextualMenuContentModel(
                                ribbon = ribbon,
                                componentProjection = ribbonComponent
                            )
                        }
                    } else {
                        val ribbonCommandButton = getCommandButtonProjectionUnder(eventX, eventY)
                        if (ribbonCommandButton != null) {
                            contentModel.apply {
                                value = ribbon.onShowContextualMenuListener!!.getContextualMenuContentModel(
                                    ribbon = ribbon,
                                    commandProjection = ribbonCommandButton
                                )
                            }
                        } else {
                            contentModel.apply {
                                value = ribbon.onShowContextualMenuListener!!.getContextualMenuContentModel(
                                    ribbon = ribbon,
                                )
                            }
                        }
                    }
                }
                val popupWindow = GeneralCommandMenuPopupHandler.showPopupContent(
                    popupOriginator = popupOriginator,
                    layoutDirection = layoutDirection,
                    density = density,
                    textStyle = resolvedTextStyle,
                    fontFamilyResolver = fontFamilyResolver,
                    skinColors = skinColors,
                    skinPainters = painters,
                    buttonShaper = buttonShaper,
                    decorationAreaType = decorationAreaType,
                    compositionLocalContext = compositionLocalContext,
                    anchorBoundsInWindow = Rect(
                        offset = Offset(
                            x = lastMouseEvent.x.toFloat(),
                            y = lastMouseEvent.y.toFloat()
                        ),
                        size = Size.Zero
                    ),
                    popupTriggerAreaInWindow = Rect.Zero,
                    contentModel = contentModel,
                    presentationModel = CommandPopupMenuPresentationModel(
                        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                        toDismissOnCommandActivation = true
                    ),
                    displayPrototypeCommand = null,
                    toDismissPopupsOnActivation = true,
                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                    popupAnchorBoundsProvider = null,
                    popupOriginatorKeyTip = null,
                    overlays = emptyMap(),
                    popupKind = AuroraPopupManager.PopupKind.Popup
                )
                coroutineScope.launch {
                    popupWindow?.opacity = 1.0f
                }
            }
        }
    })
}

@Composable
private fun RibbonBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val mainPlaceable = measurables[0].measure(Constraints.fixedWidth(width = width))
        val height = mainPlaceable.measuredHeight

        val otherPlaceables = measurables.subList(1, measurables.size).map {
            it.measure(Constraints.fixed(width = width, height = height))
        }

        layout(width = width, height = height) {
            mainPlaceable.placeRelative(0, 0)
            otherPlaceables.forEach {
                it.placeRelative(0, 0)
            }
        }
    }
}

private const val TaskbarWidthMaxRatio = 0.25f
private val TaskbarContentPadding = PaddingValues(horizontal = 6.dp)
private val TaskbarContextualTaskGroupTitlePadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
