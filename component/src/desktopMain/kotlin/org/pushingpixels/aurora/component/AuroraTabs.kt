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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.utils.getEndwardDoubleArrowIcon
import org.pushingpixels.aurora.component.utils.getStartwardDoubleArrowIcon
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerTokens
import kotlin.math.max

@OptIn(AuroraInternalApi::class)
@Composable
internal fun tabsIntrinsicSize(
    contentModel: TabsContentModel,
    presentationModel: TabsPresentationModel,
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }
    val buttonShaper = LocalButtonShaper.current

    // Presentation model for the content - copy fields from the tabs presentation model
    val contentPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.tabPresentationState,
        backgroundAppearanceStrategy = presentationModel.tabBackgroundAppearanceStrategy,
        iconActiveFilterStrategy = presentationModel.tabIconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.tabIconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.tabIconDisabledFilterStrategy,
        sides = Sides(openSides = hashSetOf(Side.Bottom), straightSides = hashSetOf(Side.Bottom)),
        contentPadding = presentationModel.tabContentPadding,
        minWidth = presentationModel.tabMinWidth
    )

    // For the intrinsic size, we do not account for the scroller buttons that are shown
    // when there is not enough horizontal space
    var width = 0.0f
    var height = 0.0f

    val layoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )
    for (tabModel in contentModel.tabs) {
        val currentTabCommand = Command(text = tabModel.text, icon = tabModel.icon)
        val preLayoutInfo = layoutManager.getPreLayoutInfo(currentTabCommand, contentPresentationModel)
        val currentTabIntrinsicSize = layoutManager.getPreferredSize(
            currentTabCommand, contentPresentationModel, preLayoutInfo, buttonShaper
        )
        width += currentTabIntrinsicSize.width
        height = max(height, currentTabIntrinsicSize.height)
    }

    // Account for leading and traling margins
    val leadingMarginPx = presentationModel.leadingMargin.value * density.density
    val trailingMarginPx = presentationModel.trailingMargin.value * density.density
    val interTabMarginPx = presentationModel.interTabMargin.value * density.density

    width += (leadingMarginPx + trailingMarginPx)
    if (contentModel.tabs.size > 1) {
        width += interTabMarginPx * (contentModel.tabs.size - 1)
    }

    return Size(width, height)
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraTabs(
    contentModel: TabsContentModel,
    presentationModel: TabsPresentationModel = TabsPresentationModel(),
    horizontalScrollState: ScrollState = rememberScrollState(0),
    modifier: Modifier
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = LocalButtonShaper.current

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    // Presentation model for the scroller buttons. Note usage of Original icon filter strategy
    // since we're using TransitionAwarePainterDelegate to draw the double arrows, and
    // OnRollover action fire trigger for interacting with the scroller buttons.
    val scrollerPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Small,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        iconActiveFilterStrategy = IconFilterStrategy.Original,
        iconEnabledFilterStrategy = IconFilterStrategy.Original,
        iconDisabledFilterStrategy = IconFilterStrategy.Original,
        contentPadding = PaddingValues(0.dp),
        actionFireTrigger = ActionFireTrigger.OnRollover,
        autoRepeatAction = true,
        autoRepeatInitialInterval = 250L,
        autoRepeatSubsequentInterval = 100L
    )
    val scrollerLayoutManager = scrollerPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    // Presentation model for the content - copy fields from the tabs presentation model
    val contentPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.tabPresentationState,
        backgroundAppearanceStrategy = presentationModel.tabBackgroundAppearanceStrategy,
        iconActiveFilterStrategy = presentationModel.tabIconActiveFilterStrategy,
        iconEnabledFilterStrategy = presentationModel.tabIconEnabledFilterStrategy,
        iconDisabledFilterStrategy = presentationModel.tabIconDisabledFilterStrategy,
        sides = Sides(openSides = hashSetOf(Side.Bottom), straightSides = hashSetOf(Side.Bottom)),
        contentPadding = presentationModel.tabContentPadding,
        minWidth = presentationModel.tabMinWidth
    )
    val contentLayoutManager = contentPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val underlineColorTokens = getContainerTokens(
        colors = AuroraSkin.colors,
        tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Tab,
        componentState = ComponentState.Selected,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
        inactiveContainerType = ContainerType.Neutral
    )
    val underlineColor = if (underlineColorTokens.isDark) {
        underlineColorTokens.complementaryContainerOutline
    } else {
        underlineColorTokens.containerOutline
    }

    val scope = rememberCoroutineScope()
    val scrollAmount = 12.dp.value * density.density

    val startwardScrollerCommand = Command(text = "",
        icon = getStartwardDoubleArrowIcon(
            decorationAreaType = decorationAreaType,
            skinColors = colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            inactiveContainerType = ContainerType.Muted,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            density = density
        ),
        isActionEnabled = (horizontalScrollState.value > 0),
        action = {
            scope.launch {
                horizontalScrollState.scrollTo(
                    (horizontalScrollState.value - scrollAmount.toInt()).coerceAtLeast(0)
                )
            }
        })
    val endwardScrollerCommand = Command(text = "",
        icon = getEndwardDoubleArrowIcon(
            decorationAreaType = decorationAreaType,
            skinColors = colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            inactiveContainerType = ContainerType.Muted,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            density = density
        ),
        isActionEnabled = (horizontalScrollState.value < horizontalScrollState.maxValue),
        action = {
            scope.launch {
                horizontalScrollState.scrollTo(
                    (horizontalScrollState.value + scrollAmount.toInt()).coerceAtMost(
                        horizontalScrollState.maxValue
                    )
                )
            }
        })

    Layout(modifier = modifier.fillMaxWidth().padding(top = presentationModel.topPadding),
        content = {
            // Startwards scroller
            CommandButtonProjection(
                contentModel = startwardScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = BaseCommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Trailing))
                    )
                )
            ).project()

            Box(modifier = Modifier.horizontalScroll(horizontalScrollState)) {
                Layout(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        for ((index, tabModel) in contentModel.tabs.withIndex()) {
                            AuroraTabButton(
                                modifier = Modifier,
                                command = Command(
                                    text = tabModel.text,
                                    icon = tabModel.icon,
                                    isActionEnabled = tabModel.isEnabled,
                                    isActionToggle = true,
                                    isActionToggleSelected = (index == contentModel.selectedTabIndex),
                                    onTriggerActionToggleSelectedChange = {
                                        if (it) {
                                            contentModel.onTriggerTabSelected.invoke(index)
                                        }
                                    }
                                ),
                                presentationModel = contentPresentationModel,
                            )
                        }

                        // Left underline
                        Canvas(modifier = Modifier) {
                            drawRect(color = underlineColor, topLeft = Offset.Zero, size = size)
                        }
                        // Right underline
                        Canvas(modifier = Modifier) {
                            drawRect(color = underlineColor, topLeft = Offset.Zero, size = size)
                        }
                    },
                    measurePolicy = { measurables, constraints ->
                        val ltr = (layoutDirection == LayoutDirection.Ltr)
                        val leadingMarginPx = presentationModel.leadingMargin.toPx()
                        val trailingMarginPx = presentationModel.trailingMargin.toPx()

                        // Process tab buttons
                        val tabButtonMeasurables = measurables.subList(0, measurables.size - 2)
                        val tabButtonPlaceables = tabButtonMeasurables.map { it.measure(Constraints()) }
                        val height = tabButtonPlaceables.maxOf { it.measuredHeight }
                        val contentWidth = tabButtonPlaceables.sumOf { it.measuredWidth } +
                                (tabButtonPlaceables.size - 1) * presentationModel.interTabMargin.toPx()

                        val fullWidth = max(constraints.minWidth, contentWidth.toInt()) +
                                (leadingMarginPx + trailingMarginPx).toInt()

                        // Process underlines
                        val leadingUnderlineMeasurable = measurables[measurables.size - 2]
                        val trailingUnderlineMeasurable = measurables[measurables.size - 1]
                        val leadingUnderlinePlaceable: Placeable
                        val trailingUnderlinePlaceable: Placeable

                        val leadingUnderlineStart = 0
                        var leadingUnderlineEnd = leadingMarginPx
                        if (contentModel.selectedTabIndex > 0) {
                            leadingUnderlineEnd += tabButtonPlaceables.subList(0, contentModel.selectedTabIndex)
                                .sumOf { it.measuredWidth }
                            leadingUnderlineEnd += (contentModel.selectedTabIndex * presentationModel.interTabMargin.toPx())
                        }
                        leadingUnderlinePlaceable = leadingUnderlineMeasurable.measure(
                            Constraints.fixed(width = (leadingUnderlineEnd - leadingUnderlineStart).toInt(), height = 1)
                        )
                        val trailingUnderlineStart =
                            leadingUnderlineEnd + tabButtonPlaceables[contentModel.selectedTabIndex].measuredWidth
                        val trailingUnderlineEnd = fullWidth
                        trailingUnderlinePlaceable = trailingUnderlineMeasurable.measure(
                            Constraints.fixed(
                                width = (trailingUnderlineEnd - trailingUnderlineStart).toInt(),
                                height = 1
                            )
                        )

                        layout(width = fullWidth, height = height) {
                            if (ltr) {
                                var x = leadingMarginPx
                                for (tabButtonPlaceable in tabButtonPlaceables) {
                                    tabButtonPlaceable.place(x.toInt(), 0)
                                    x += (tabButtonPlaceable.measuredWidth + presentationModel.interTabMargin.toPx())
                                }
                            } else {
                                var x = fullWidth - leadingMarginPx
                                for (tabButtonPlaceable in tabButtonPlaceables) {
                                    tabButtonPlaceable.place(x.toInt() - tabButtonPlaceable.measuredWidth, 0)
                                    x -= (tabButtonPlaceable.measuredWidth + presentationModel.interTabMargin.toPx())
                                }
                            }
                            leadingUnderlinePlaceable.placeRelative(0, height - 1)
                            trailingUnderlinePlaceable.placeRelative(
                                fullWidth - trailingUnderlinePlaceable.measuredWidth,
                                height - 1
                            )
                        }
                    }
                )
            }

            // Endwards scroller
            CommandButtonProjection(
                contentModel = endwardScrollerCommand,
                presentationModel = scrollerPresentationModel.overlayWith(
                    overlay = BaseCommandButtonPresentationModel.Overlay(
                        sides = Sides(straightSides = hashSetOf(Side.Leading))
                    )
                )
            ).project()

            // Leading underline to extend the whole width of the tabs and scroller buttons
            Canvas(modifier = Modifier) {
                drawRect(color = underlineColor, topLeft = Offset.Zero, size = size)
            }
            // Trailing underline to extend the whole width of the tabs and scroller buttons
            Canvas(modifier = Modifier) {
                drawRect(color = underlineColor, topLeft = Offset.Zero, size = size)
            }
            if (presentationModel.contentSeparatorKind == TabContentSeparatorKind.Double) {
                // Bottom part of the double content separator
                Canvas(modifier = Modifier) {
                    drawRect(color = underlineColor, topLeft = Offset.Zero, size = size)
                }
            }
        },
        measurePolicy = { measurables, constraints ->
            val leftScrollerMeasurable = measurables[0]
            val contentMeasurable = measurables[1]
            val rightScrollerMeasurable = measurables[2]
            val leftUnderlineMeasurable = measurables[3]
            val rightUnderlineMeasurable = measurables[4]
            val bottomContentSeparatorMeasurable =
                if (presentationModel.contentSeparatorKind == TabContentSeparatorKind.Double) measurables[5] else null

            // How big is the left scroller?
            val leftScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    startwardScrollerCommand,
                    scrollerPresentationModel
                )
            val leftScrollerSize = scrollerLayoutManager.getPreferredSize(
                startwardScrollerCommand, scrollerPresentationModel, leftScrollerPreLayoutInfo, buttonShaper
            )

            // How big is the right scroller?
            val rightScrollerPreLayoutInfo =
                scrollerLayoutManager.getPreLayoutInfo(
                    endwardScrollerCommand,
                    scrollerPresentationModel
                )
            val rightScrollerSize = scrollerLayoutManager.getPreferredSize(
                endwardScrollerCommand, scrollerPresentationModel, rightScrollerPreLayoutInfo, buttonShaper
            )

            // How much space does the scrollable content need?
            val sideMargins = presentationModel.leadingMargin.toPx() +
                    presentationModel.trailingMargin.toPx()

            var boxRequiredWidth = sideMargins
            var boxHeight = 0
            if (contentModel.tabs.isNotEmpty()) {
                for (tabModel in contentModel.tabs) {
                    val command = Command(
                        text = tabModel.text,
                        icon = tabModel.icon,
                        isActionEnabled = tabModel.isEnabled,
                        action = {}
                    )

                    val commandPreLayoutInfo =
                        contentLayoutManager.getPreLayoutInfo(
                            command,
                            contentPresentationModel
                        )
                    val commandSize = contentLayoutManager.getPreferredSize(
                        command, contentPresentationModel, commandPreLayoutInfo, buttonShaper
                    )
                    boxRequiredWidth += commandSize.width
                    boxHeight = commandSize.height.toInt()
                }
                // Account for inter-tab margins (N-1 margins for N tabs)
                boxRequiredWidth += (contentModel.tabs.size - 1) *
                        presentationModel.interTabMargin.toPx()
            } else {
                val forSizing = Command(text = "sample", action = {})
                val commandPreLayoutInfo =
                    contentLayoutManager.getPreLayoutInfo(
                        forSizing,
                        contentPresentationModel
                    )
                boxHeight = contentLayoutManager.getPreferredSize(
                    forSizing, contentPresentationModel, commandPreLayoutInfo, buttonShaper
                ).height.toInt()
            }

            // Do we need to show the scrollers? If available width from constraints is enough
            // to fully display all scrollable content, we don't need to show the scrollers.
            val needScrollers = (boxRequiredWidth > constraints.maxWidth)
            val contentWidth = if (needScrollers) constraints.maxWidth -
                    leftScrollerSize.width - rightScrollerSize.width
            else constraints.maxWidth

            val leftScrollerPlaceable = leftScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) leftScrollerSize.width.toInt() else 0,
                    height = boxHeight
                )
            )
            val rightScrollerPlaceable = rightScrollerMeasurable.measure(
                Constraints.fixed(
                    width = if (needScrollers) rightScrollerSize.width.toInt() else 0,
                    height = boxHeight
                )
            )
            val contentPlaceable = contentMeasurable.measure(
                Constraints.fixed(contentWidth.toInt(), boxHeight)
            )

            val leftUnderlineWidth = if (needScrollers) leftScrollerPlaceable.measuredWidth else 0
            val rightUnderlineWidth = if (needScrollers) {
                rightScrollerPlaceable.measuredWidth
            } else {
                contentWidth.toInt() - boxRequiredWidth.toInt()
            }

            val leftUnderlinePlaceable = leftUnderlineMeasurable.measure(
                Constraints.fixed(leftUnderlineWidth, 1)
            )
            val rightUnderlinePlaceable = rightUnderlineMeasurable.measure(
                Constraints.fixed(rightUnderlineWidth, 1)
            )

            val fullHeight = boxHeight +
                    if (bottomContentSeparatorMeasurable != null) TabConstants.DoubleSeparatorGap.toPx().toInt() else 0
            val bottomContentSeparatorPlaceable = bottomContentSeparatorMeasurable?.measure(
                Constraints.fixed(constraints.maxWidth, 1)
            )

            layout(width = constraints.maxWidth, height = fullHeight) {
                if (leftScrollerPlaceable.width > 0) {
                    leftScrollerPlaceable.placeRelative(0, 0)
                }
                if (rightScrollerPlaceable.width > 0) {
                    rightScrollerPlaceable.placeRelative(
                        constraints.maxWidth - rightScrollerPlaceable.width,
                        0
                    )
                }
                contentPlaceable.placeRelative(leftScrollerPlaceable.width, 0)
                leftUnderlinePlaceable.placeRelative(0, boxHeight - 1)
                rightUnderlinePlaceable.placeRelative(
                    constraints.maxWidth - rightUnderlinePlaceable.measuredWidth, boxHeight - 1
                )
                bottomContentSeparatorPlaceable?.placeRelative(0, fullHeight - 1)
            }
        })
}
