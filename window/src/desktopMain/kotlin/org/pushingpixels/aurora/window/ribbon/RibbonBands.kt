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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.utils.getEndwardDoubleArrowIcon
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import kotlin.math.ceil
import kotlin.math.max

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonBands(ribbonTask: RibbonTask) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val bands = ribbonTask.bands

    // The height of ribbon band control panel is computed based on the preferred height of a command
    // button in BIG state.
    val commandForSizing = Command(text = "Text", action = {})
    val presentationForSizing = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Big,
        forceAllocateSpaceForIcon = true
    )
    val sizingLayoutManager = presentationForSizing.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )
    val sizingPreLayoutInfo =
        sizingLayoutManager.getPreLayoutInfo(
            commandForSizing,
            presentationForSizing
        )
    val bandContentHeight = sizingLayoutManager.getPreferredSize(
        commandForSizing, presentationForSizing, sizingPreLayoutInfo
    ).height
    val bandTitleHeight = getLabelPreferredHeight(
        contentModel = LabelContentModel(text = "Title"),
        presentationModel = LabelPresentationModel(contentPadding = RibbonBandTitleLabelPadding, textMaxLines = 1),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver,
        availableWidth = Float.MAX_VALUE
    )
    val fullHeightDp = ((bandContentHeight + bandTitleHeight) / density.density).dp

    AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
        Row(modifier = Modifier.fillMaxWidth().height(fullHeightDp).auroraBackground()) {
            for (band in bands) {
                RibbonBand(band = band, bandContentHeight = bandContentHeight)
            }
        }
    }
}

@Composable
private fun RibbonBand(band: AbstractRibbonBand, bandContentHeight: Float) {
    when (band) {
        is RibbonBand -> {
            Column(modifier = Modifier.width(200.dp).fillMaxHeight()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1.0f)) {
                    RibbonBandContent(band, bandContentHeight)
                }

                RibbonBandTitle(band)
            }
        }

        is FlowRibbonBand -> {
            Column(modifier = Modifier.width(IntrinsicSize.Min).fillMaxHeight()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1.0f)) {
                    FlowRibbonBandContent(band, bandContentHeight)
                }

                RibbonBandTitle(band)
            }
        }
    }
}

@Composable
private fun RibbonBandContent(band: RibbonBand, bandContentHeight: Float) {
    Row(modifier = Modifier.fillMaxSize().padding(0.dp)) {
        for (bandGroup in band.groups) {
            when (bandGroup) {
                is RibbonBandCommandGroup ->
                    Box(modifier = Modifier.fillMaxSize().padding(all = RibbonBandContentGap)) {
                        for (gallery in bandGroup.galleries) {
                            RibbonGalleryProjection(
                                contentModel = gallery.contentModel,
                                presentationModel = InRibbonGalleryPresentationModel(
                                    collapsedVisibleCount = when (gallery.presentationPriority) {
                                        PresentationPriority.Low -> gallery.collapsedVisibleCountLow
                                        PresentationPriority.Medium -> gallery.collapsedVisibleCountMedium
                                        PresentationPriority.Top -> gallery.collapsedVisibleCountTop
                                    },
                                    commandButtonPresentationState = gallery.presentationModel.commandButtonPresentationState,
                                    commandButtonTextOverflow = gallery.presentationModel.commandButtonTextOverflow,
                                    commandPopupFireTrigger = gallery.presentationModel.commandPopupFireTrigger,
                                    commandSelectedStateHighlight = gallery.presentationModel.commandSelectedStateHighlight,
                                    contentPadding = gallery.presentationModel.contentPadding,
                                    layoutGap = gallery.presentationModel.layoutGap,
                                    expandKeyTip = gallery.presentationModel.expandKeyTip,
                                    popupLayoutSpec = gallery.presentationModel.popupLayoutSpec
                                ),
                                secondaryOverlays = gallery.secondaryOverlays
                            ).project(inlineState = gallery.inlineState)
                        }
                    }

                is RibbonBandComponentGroup -> {
                    RibbonBandComponentGroupContent(group = bandGroup, bandContentHeight = bandContentHeight)
                }
            }
        }
    }
}

private data class RibbonBandComponentGroupLayoutInfo(
    val fullWidth: Int,
    val titleLabelWidth: Int,
    val columnWidths: List<Int>
)

@OptIn(AuroraInternalApi::class)
@Composable
private fun getComponentGroupContentLayoutInfo(
    group: RibbonBandComponentGroup,
    bandContentHeight: Float,
    gap: Int
): RibbonBandComponentGroupLayoutInfo {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()

    val hasTitle = (group.title != null)
    val contentRows = if (hasTitle) 2 else 3

    val titleLabelWidth = if (hasTitle)
        getLabelPreferredSingleLineWidth(
            contentModel = LabelContentModel(text = group.title!!),
            presentationModel = LabelPresentationModel(),
            resolvedTextStyle = resolvedTextStyle,
            layoutDirection = layoutDirection,
            density = density,
            fontFamilyResolver = LocalFontFamilyResolver.current
        ).toInt() else 0

    val columnWidths: MutableList<Int> = arrayListOf()

    var contentWidth = 0
    var currentColumnWidth = 0
    var currentIndexInColumn = 0
    for (projection in group.componentProjections) {
        val widthNeeded = projection.first.intrinsicWidth(height = rowHeight)
        currentColumnWidth = max(currentColumnWidth, widthNeeded)

        currentIndexInColumn++
        if (currentIndexInColumn == contentRows) {
            columnWidths.add(currentColumnWidth)

            // Start a new column
            contentWidth += currentColumnWidth
            currentIndexInColumn = 0
            currentColumnWidth = 0
        }
    }
    if (currentIndexInColumn < contentRows) {
        columnWidths.add(currentColumnWidth)
    }

    // Account for gaps between columns
    val contentColumnCount = ceil(group.componentProjections.size.toFloat() / contentRows.toFloat()).toInt()
    contentWidth += (contentColumnCount - 1) * gap

    val fullWidth = max(titleLabelWidth, contentWidth)

    return RibbonBandComponentGroupLayoutInfo(
        fullWidth = fullWidth,
        titleLabelWidth = titleLabelWidth,
        columnWidths = columnWidths
    )
}

@Composable
private fun RibbonBandComponentGroupContent(group: RibbonBandComponentGroup, bandContentHeight: Float) {
    val density = LocalDensity.current
    val gap = (RibbonBandContentGap.value * density.density).toInt()
    val layoutInfo = getComponentGroupContentLayoutInfo(group, bandContentHeight, gap)

    val hasTitle = (group.title != null)
    val contentRows = if (hasTitle) 2 else 3

    Layout(modifier = Modifier.fillMaxHeight()
        .width((layoutInfo.fullWidth / density.density).dp),
        content = {
            // Title label if exists
            if (group.title != null) {
                LabelProjection(contentModel = LabelContentModel(text = group.title!!)).project(
                    Modifier.width((layoutInfo.titleLabelWidth / density.density).dp)
                )
            }

            // The rest of the content
            var currentColumnIndex = 0
            var currentContentRow = 0
            for (projection in group.componentProjections) {
                // All components in the same column have the same (max) width
                val currentColumnWidth = layoutInfo.columnWidths[currentColumnIndex]
                projection.first.reproject(Modifier.width((currentColumnWidth / density.density).dp))

                currentContentRow++
                if (currentContentRow == contentRows) {
                    currentColumnIndex++
                    currentContentRow = 0
                }
            }
        },
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth
            val placeables = measurables.map { it.measure(Constraints()) }

            layout(width = width, height = constraints.maxHeight) {
                val rowHeight = constraints.maxHeight / 3
                var x = 0
                var y = gap

                if (hasTitle) {
                    placeables[0].placeRelative(x, y)
                    y += rowHeight
                }

                val topContentY = y
                var currentContentRow = 0
                var currentColumnIndex = 0
                for (placeable in placeables.subList(
                    fromIndex = if (hasTitle) 1 else 0,
                    toIndex = placeables.size
                )) {
                    placeable.placeRelative(x, y)
                    currentContentRow++
                    y += rowHeight

                    if (currentContentRow == contentRows) {
                        // Start a new column
                        y = topContentY
                        x += (layoutInfo.columnWidths[currentColumnIndex] + gap)
                        currentContentRow = 0
                        currentColumnIndex++
                    }
                }
            }
        })
}

@Composable
private fun getOptimalFlowRibbonBandWidth(band: FlowRibbonBand, bandContentHeight: Float, gap: Int): Int {
    val compCount = band.flowComponentProjections.size
    val widths = IntArray(compCount)
    var currBestResult = 0
    val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()
    for ((index, flowCompProjection) in band.flowComponentProjections.withIndex()) {
        widths[index] = flowCompProjection.first.intrinsicWidth(height = rowHeight)
        currBestResult += (widths[index] + gap)
    }

    // need to find the inflection points that result in
    // the lowest value for max length of three sub-sequences
    for (inflectionIndex1 in 0 until (compCount - 2)) {
        for (inflectionIndex2 in inflectionIndex1 + 1 until compCount - 1) {
            var w1 = 0
            for (index1 in 0..inflectionIndex1) {
                w1 += widths[index1] + gap
            }
            var w2 = 0
            for (index2 in inflectionIndex1 + 1..inflectionIndex2) {
                w2 += widths[index2] + gap
            }
            var w3 = 0
            for (index3 in inflectionIndex2 + 1 until compCount) {
                w3 += widths[index3] + gap
            }
            val width = max(max(w1, w2), w3)
            if (width < currBestResult) {
                currBestResult = width
            }
        }
    }

    return currBestResult
}

@Composable
private fun FlowRibbonBandContent(band: FlowRibbonBand, bandContentHeight: Float) {
    val density = LocalDensity.current
    val gap = (RibbonBandContentGap.value * density.density).toInt()
    val optimalWidth = getOptimalFlowRibbonBandWidth(band, bandContentHeight, gap)
    //println("Optimal width: $optimalWidth")
    Layout(modifier = Modifier.fillMaxHeight()
        .width((optimalWidth / density.density).dp),
        content = {
            // Project all the content
            //println("Intrinsic widths:")
            for (projection in band.flowComponentProjections) {
                //println("\t${projection.first.intrinsicWidth(0)}")
                projection.first.reproject(Modifier)
            }
        },
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth

            val placeables = measurables.map { it.measure(Constraints()) }

//            println("Measured widths:")
//            placeables.forEach {
//                println("\t${it.measuredWidth}")
//            }

            layout(width = width, height = constraints.maxHeight) {
                val rowHeight = constraints.maxHeight / 3
                var x = 0
                var y = gap

                for (placeable in placeables) {
                    if (x + placeable.measuredWidth > width) {
                        x = 0
                        y += rowHeight
                    }
                    placeable.placeRelative(x, y)
                    x += placeable.measuredWidth
                    x += gap
                }
            }
        })
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun RibbonBandTitle(
    band: AbstractRibbonBand,
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current

    Row(
        modifier = Modifier.fillMaxWidth().padding(RibbonBandTitleAreaPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabelProjection(
            contentModel = LabelContentModel(text = band.title),
            presentationModel = LabelPresentationModel(
                contentPadding = RibbonBandTitleLabelPadding,
                horizontalAlignment = HorizontalAlignment.Center,
                textMaxLines = 1,
            )
        ).project(modifier = Modifier.weight(1.0f))

        if (band.expandCommand != null) {
            CommandButtonProjection(
                contentModel = Command(
                    text = "",
                    icon = getEndwardDoubleArrowIcon(
                        decorationAreaType = decorationAreaType,
                        skinColors = colors,
                        colorSchemeBundle = null,
                        density = density
                    ),
                    action = { band.expandCommand?.action?.invoke() },
                    actionRichTooltip = band.expandCommand?.actionRichTooltip
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.SmallFitToIcon,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    contentPadding = RibbonBandExpandButtonContentPadding,
                    sides = Sides.ClosedRectangle,
                    actionKeyTip = band.expandCommandKeyTip
                )
            ).project()
        }
    }
}

private val RibbonBandTitleAreaPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
private val RibbonBandTitleLabelPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
private val RibbonBandExpandButtonContentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp)
private val RibbonBandContentGap: Dp = 4.dp
