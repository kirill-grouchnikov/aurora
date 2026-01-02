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
package org.pushingpixels.aurora.window.ribbon

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.AuroraHorizontallyScrollableBox
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicies
import org.pushingpixels.aurora.component.ribbon.resize.CoreRibbonResizePolicy
import org.pushingpixels.aurora.component.ribbon.resize.FlowRibbonBandResizePolicy
import org.pushingpixels.aurora.component.ribbon.resize.RibbonBandResizePolicy
import org.pushingpixels.aurora.component.utils.getEndwardDoubleArrowIcon
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.utils.ContainerType
import kotlin.math.ceil
import kotlin.math.max

@Composable
private fun getBandsIntrinsicWidth(
    bands: List<AbstractRibbonBand>,
    bandCurrentResizePolicies: Map<AbstractRibbonBand, RibbonBandResizePolicy>,
    bandFullHeight: Int,
    bandContentHeight: Int,
    gap: Int,
    separatorWidth: Int
): Int {
    var result = 0
    for (band in bands) {
        val bandCurrentResizePolicy = bandCurrentResizePolicies[band]!!
        val heightForWidth = if (bandCurrentResizePolicy is CoreRibbonResizePolicies.Icon)
            bandFullHeight else bandContentHeight
        result += bandCurrentResizePolicy.getPreferredWidth(
            ribbonBand = band,
            availableHeight = heightForWidth,
            gap = gap
        )
    }
    result += bands.size * separatorWidth

    return result
}

private fun validateResizePolicies(
    bands: List<AbstractRibbonBand>,
    intrinsicWidthList: List<Pair<Map<AbstractRibbonBand, RibbonBandResizePolicy>, Int>>
) {
    for (index in 0 until intrinsicWidthList.size - 1) {
        val currentIntrinsicWidth = intrinsicWidthList[index].second
        val nextIntrinsicWidth = intrinsicWidthList[index + 1].second
        if (nextIntrinsicWidth > currentIntrinsicWidth) {
            val currentMapping = intrinsicWidthList[index].first
            val nextMapping = intrinsicWidthList[index + 1].first
            val currentLegend =
                bands.joinToString { "${it.title}:${currentMapping[it]?.javaClass?.simpleName}" }
            val nextLegend =
                bands.joinToString { "${it.title}:${nextMapping[it]?.javaClass?.simpleName}" }
            error(
                """Inconsistent intrinsic widths
                resizing sequence $currentLegend with intrinsic width $currentIntrinsicWidth
                is followed by
                resizing sequence $nextLegend with intrinsic width $nextIntrinsicWidth
            """.trimMargin()
            )
        }
    }
}

@Composable
internal fun getBandContentHeight(
    layoutDirection: LayoutDirection,
    density: Density,
    resolvedTextStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver,
    buttonShaper: AuroraButtonShaper
): Int {
    val gap = (RibbonBandContentGap.value * density.density).toInt()

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
    return (sizingLayoutManager.getPreferredSize(
        commandForSizing, presentationForSizing, sizingPreLayoutInfo, buttonShaper
    ).height + 2 * gap).toInt()
}

@Composable
internal fun getBandTitleHeight(
    layoutDirection: LayoutDirection,
    density: Density,
    resolvedTextStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
): Int {
    return getLabelPreferredHeight(
        contentModel = LabelContentModel(text = "Title"),
        presentationModel = LabelPresentationModel(contentPadding = RibbonBandTitleLabelPadding, textMaxLines = 1),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver,
        availableWidth = Float.MAX_VALUE
    ).toInt()
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonBands(ribbonTask: RibbonTask) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }
    val gap = (RibbonBandContentGap.value * density.density).toInt()
    val buttonShaper = AuroraSkin.buttonShaper

    val bands = ribbonTask.bands

    val bandContentHeight = getBandContentHeight(layoutDirection, density, resolvedTextStyle, fontFamilyResolver, buttonShaper)
    val bandTitleHeight = getBandTitleHeight(layoutDirection, density, resolvedTextStyle, fontFamilyResolver)
    val bandFullHeight = (bandContentHeight + bandTitleHeight)
    val bandFullHeightDp = (bandFullHeight / density.density).dp

    val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()
    val separatorWidth: Int = VerticalSeparatorProjection().intrinsicWidth(bandFullHeight)

    val resizePolicySequence = ribbonTask.resizeSequencingPolicy.getResizeSequence(ribbonTask)
    val intrinsicWidthList: MutableList<Pair<Map<AbstractRibbonBand, RibbonBandResizePolicy>, Int>> =
        arrayListOf()

    // Start with the most permissive resize policy for every band
    val bandCurrentResizePolicies: MutableMap<AbstractRibbonBand, RibbonBandResizePolicy> =
        bands.associateWith { it.resizePolicies[0] }.toMutableMap()
    for (resizePolicy in resizePolicySequence) {
        // Start replacing the resize policies for the ribbon bands based on the resize
        // sequence associated with this task
        bandCurrentResizePolicies[resizePolicy.first] = resizePolicy.second

        var neededWidth = getBandsIntrinsicWidth(
            bands, bandCurrentResizePolicies,
            bandFullHeight, bandContentHeight, gap, separatorWidth
        )
        neededWidth += bands.size * separatorWidth

        intrinsicWidthList.add(Pair(bandCurrentResizePolicies.toMap(), neededWidth))
    }

    // Make sure that resize policies configured on this task create a non-increasing sequence
    // of intrinsic widths
    validateResizePolicies(bands, intrinsicWidthList)

    val bandsRowScrollState: ScrollState = rememberScrollState(0)

    CompositionLocalProvider(
        LocalRibbonBandRowHeight provides rowHeight,
        LocalRibbonBandRow provides RibbonBandRow.None,
        LocalRibbonKeyTipChainRoot provides ribbonTask,
        LocalRibbonKeyTipChainRootKeyTip provides ribbonTask.keyTip,
    ) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
            SubcomposeLayout(
                modifier = Modifier.fillMaxWidth().height(bandFullHeightDp)
            ) { constraints ->
                val widthAvailable = constraints.maxWidth
                val heightAvailable = constraints.maxHeight

                val requiredWidthForBands: Int
                val bandResizePolicies: Map<AbstractRibbonBand, RibbonBandResizePolicy>
                val bestFittingMatch = intrinsicWidthList.find { it.second <= widthAvailable }
                if (bestFittingMatch != null) {
                    bandResizePolicies = bestFittingMatch.first
                    requiredWidthForBands = bestFittingMatch.second
                } else {
                    // Kick in horizontal scrolling if we don't have enough horizontal
                    // space to display the most restrictive configuration
                    bandResizePolicies = intrinsicWidthList.last().first
                    requiredWidthForBands = intrinsicWidthList.last().second
                }

                val bandsPlaceable =
                    subcompose(1) {
                        Box(modifier = Modifier.fillMaxSize().auroraBackground()) {
                            AuroraHorizontallyScrollableBox(
                                modifier = Modifier.width(width = (widthAvailable / density.density).dp),
                                height = (heightAvailable / density.density).dp,
                                contentWidth = { requiredWidthForBands },
                                horizontalScrollState = bandsRowScrollState,
                                scrollAmount = 12.dp,
                                content = {
                                    for (band in bands) {
                                        RibbonBand(
                                            band = band,
                                            bandResizePolicy = bandResizePolicies[band]!!,
                                            bandContentHeight = bandContentHeight,
                                            bandFullHeight = bandFullHeight
                                        )
                                        VerticalSeparatorProjection().project(modifier = Modifier.fillMaxHeight())
                                    }
                                }
                            )
                        }
                    }.first().measure(
                        Constraints.fixed(
                            width = widthAvailable,
                            height = heightAvailable
                        )
                    )
                layout(widthAvailable, heightAvailable) {
                    bandsPlaceable.placeRelative(x = 0, y = 0)
                }
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonBand(
    band: AbstractRibbonBand,
    bandResizePolicy: RibbonBandResizePolicy,
    bandContentHeight: Int,
    bandFullHeight: Int
) {
    val density = LocalDensity.current
    val gap = (RibbonBandContentGap.value * density.density).toInt()

    if (bandResizePolicy is CoreRibbonResizePolicies.Icon) {
        val width = bandResizePolicy.getPreferredWidth(
            ribbonBand = band,
            availableHeight = bandFullHeight,
            gap = 0
        )
        Box(modifier = Modifier.width((width / density.density).dp).fillMaxHeight()) {
            val iconCommand = RibbonBandCollapsedCommand(
                text = band.title,
                icon = band.icon,
                secondaryContentModel = RibbonBandCollapsedMenuContentModel(ribbonBand = band)
            )
            val iconPresentationModel =
                RibbonBandCollapsedCommandButtonPresentationModel(
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                    popupKeyTip = band.collapsedStateKeyTip,
                    popupMenuPresentationModel = RibbonBandCollapsedCommandPopupMenuPresentationModel(
                        bandWidth = band.resizePolicies[0].getPreferredWidth(
                            ribbonBand = band,
                            availableHeight = bandContentHeight,
                            gap = gap
                        ),
                        bandContentHeight = bandContentHeight,
                        bandFullHeight = bandFullHeight,
                        gap = gap
                    )
                )

            CompositionLocalProvider(
                LocalRibbonTrackBounds provides false,
                LocalRibbonTrackKeyTips provides true
            ) {
                RibbonBandCollapsedCommandButtonProjection(
                    contentModel = iconCommand,
                    presentationModel = iconPresentationModel
                ).project(modifier = Modifier.fillMaxSize())
            }
        }
    } else {
        val bandTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
        val bandSize = remember { mutableStateOf(IntSize(0, 0)) }
        when (band) {
            is RibbonBand -> {
                CompositionLocalProvider(
                    LocalRibbonBandRectOnScreen provides Rect(
                        offset = bandTopLeftOffset.asOffset(density),
                        size = bandSize.value.asSize(density)),
                ) {
                    Column(
                        modifier = Modifier.width(IntrinsicSize.Min).fillMaxHeight().bandLocator(
                            band = band,
                            topLeftOffset = bandTopLeftOffset,
                            size = bandSize,
                            trackBounds = true
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().weight(1.0f)) {
                            RibbonBandContent(band, bandResizePolicy as CoreRibbonResizePolicy, bandContentHeight)
                        }

                        RibbonBandTitle(band)
                    }
                }
                DisposableEffect(band) {
                    onDispose {
                        BandBoundsTracker.untrackBounds(band)
                    }
                }
            }

            is FlowRibbonBand -> {
                CompositionLocalProvider(
                    LocalRibbonBandRectOnScreen provides Rect(
                        offset = bandTopLeftOffset.asOffset(density),
                        size = bandSize.value.asSize(density)),
                ) {

                    Column(
                        modifier = Modifier.width(IntrinsicSize.Min).fillMaxHeight().bandLocator(
                            band = band,
                            topLeftOffset = bandTopLeftOffset,
                            size = bandSize,
                            trackBounds = true
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().weight(1.0f)) {
                            FlowRibbonBandContent(
                                band,
                                bandResizePolicy as FlowRibbonBandResizePolicy,
                                bandContentHeight
                            )
                        }

                        RibbonBandTitle(band)
                    }
                }
                DisposableEffect(band) {
                    onDispose {
                        BandBoundsTracker.untrackBounds(band)
                    }
                }
            }
        }
    }
}

@Composable
private fun RibbonBandContent(
    band: RibbonBand,
    bandResizePolicy: CoreRibbonResizePolicy,
    bandContentHeight: Int
) {
    Row(modifier = Modifier.fillMaxSize().padding(0.dp)) {
        for ((index, bandGroup) in band.groups.withIndex()) {
            when (bandGroup) {
                is RibbonBandCommandGroup -> {
                    RibbonBandCommandGroupContent(bandGroup, bandResizePolicy)
                }

                is RibbonBandComponentGroup -> {
                    RibbonBandComponentGroupContent(bandGroup, bandContentHeight)
                }
            }
            if (index < (band.groups.size - 1)) {
                VerticalSeparatorProjection().project(
                    modifier = Modifier.fillMaxHeight().padding(vertical = RibbonBandContentGap)
                )
            }
        }
    }
}

@Composable
private fun RibbonBandCommandGroupContent(
    group: RibbonBandCommandGroup,
    bandResizePolicy: CoreRibbonResizePolicy
) {
    Row(modifier = Modifier.fillMaxHeight().padding(all = RibbonBandContentGap)) {
        // Display galleries first
        for (galleryProjection in group.galleries) {
            val mappedPriority = bandResizePolicy.mapping.invoke(galleryProjection.second)
            val visibleCount = when (mappedPriority) {
                PresentationPriority.Low -> galleryProjection.first.presentationModel.collapsedVisibleCountLow
                PresentationPriority.Medium -> galleryProjection.first.presentationModel.collapsedVisibleCountMedium
                PresentationPriority.Top -> galleryProjection.first.presentationModel.collapsedVisibleCountTop
            }
            galleryProjection.first.inlineState.presentationPriority = mappedPriority
            galleryProjection.first.inlineState.visibleCount = visibleCount
            galleryProjection.first.project()
        }

        // And command buttons second
        val buttonsBig: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        val buttonsMedium: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        val buttonsSmall: MutableList<BaseCommandButtonProjection<*, *, *>> = arrayListOf()
        for (commandProjection in group.commandProjections) {
            when (bandResizePolicy.mapping.invoke(commandProjection.second)) {
                PresentationPriority.Top -> buttonsBig.add(commandProjection.first)
                PresentationPriority.Medium -> buttonsMedium.add(commandProjection.first)
                PresentationPriority.Low -> buttonsSmall.add(commandProjection.first)
            }
        }

        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
        ) {
            if (buttonsBig.isNotEmpty()) {
                BigButtons(buttonsBig)
            }
            if (buttonsMedium.isNotEmpty()) {
                MediumButtons(buttonsMedium)
            }
            if (buttonsSmall.isNotEmpty()) {
                SmallButtons(buttonsSmall)
            }
        }
    }
}

@Composable
private fun BigButtons(buttons: List<BaseCommandButtonProjection<*, *, *>>) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
    ) {
        for (button in buttons) {
            button.reproject(modifier = Modifier,
                primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                    presentationState = CommandButtonPresentationState.Big,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                ),
                actionInteractionSource = remember { MutableInteractionSource() },
                popupInteractionSource = remember { MutableInteractionSource() })
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun MediumButtons(buttons: List<BaseCommandButtonProjection<*, *, *>>) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
    ) {
        val columnCount = ceil(buttons.size / 3.0f).toInt()
        var buttonIndex = 0
        for (column in 1..columnCount) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
            ) {
                for (row in 1..3) {
                    if (buttonIndex < buttons.size) {
                        val rowEnum = when (row) {
                            1 -> RibbonBandRow.Top
                            2 -> RibbonBandRow.Middle
                            else -> RibbonBandRow.Bottom
                        }
                        CompositionLocalProvider(LocalRibbonBandRow provides rowEnum) {
                            buttons[buttonIndex].reproject(modifier = Modifier,
                                primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                                    presentationState = CommandButtonPresentationState.Medium,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                                ),
                                actionInteractionSource = remember { MutableInteractionSource() },
                                popupInteractionSource = remember { MutableInteractionSource() })
                            buttonIndex++
                        }
                    }
                }
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun SmallButtons(buttons: List<BaseCommandButtonProjection<*, *, *>>) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
    ) {
        val columnCount = ceil(buttons.size / 3.0f).toInt()
        var buttonIndex = 0
        for (column in 1..columnCount) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(RibbonBandContentGap)
            ) {
                for (row in 1..3) {
                    if (buttonIndex < buttons.size) {
                        val rowEnum = when (row) {
                            1 -> RibbonBandRow.Top
                            2 -> RibbonBandRow.Middle
                            else -> RibbonBandRow.Bottom
                        }
                        CompositionLocalProvider(LocalRibbonBandRow provides rowEnum) {
                            buttons[buttonIndex].reproject(modifier = Modifier,
                                primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                                    presentationState = CommandButtonPresentationState.Small,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                                ),
                                actionInteractionSource = remember { MutableInteractionSource() },
                                popupInteractionSource = remember { MutableInteractionSource() })
                            buttonIndex++
                        }
                    }
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
    bandContentHeight: Int,
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
            presentationModel = LabelPresentationModel(
                contentPadding = PaddingValues(0.dp),
                textMaxLines = 1
            ),
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
        val widthNeeded = projection.intrinsicWidth(height = rowHeight)
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
        // Incomplete last column
        contentWidth += currentColumnWidth
        columnWidths.add(currentColumnWidth)
    }

    // Account for gaps between columns
    val contentColumnCount = ceil(group.componentProjections.size.toFloat() / contentRows.toFloat()).toInt()
    contentWidth += (contentColumnCount - 1) * gap

    val fullWidth = max(titleLabelWidth, contentWidth) + 2 * gap

    return RibbonBandComponentGroupLayoutInfo(
        fullWidth = fullWidth,
        titleLabelWidth = titleLabelWidth,
        columnWidths = columnWidths
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun RibbonBandComponentGroupContent(
    group: RibbonBandComponentGroup,
    bandContentHeight: Int
) {
    val density = LocalDensity.current
    val gap = (RibbonBandContentGap.value * density.density).toInt()
    val layoutInfo = getComponentGroupContentLayoutInfo(group, bandContentHeight, gap)

    val hasTitle = (group.title != null)
    val contentRows = if (hasTitle) 2 else 3

    Layout(modifier = Modifier.fillMaxHeight()
        .width((layoutInfo.fullWidth / density.density).dp)
        .padding(horizontal = RibbonBandContentGap),
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

                val rowEnum = when (currentContentRow) {
                    0 -> RibbonBandRow.Top
                    1 -> RibbonBandRow.Middle
                    else -> RibbonBandRow.Bottom
                }
                CompositionLocalProvider(LocalRibbonBandRow provides rowEnum) {
                    projection.project(Modifier.width((currentColumnWidth / density.density).dp))
                }

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

@OptIn(AuroraInternalApi::class)
@Composable
private fun FlowRibbonBandContent(
    band: FlowRibbonBand,
    bandResizePolicy: FlowRibbonBandResizePolicy,
    bandContentHeight: Int
) {
    val density = LocalDensity.current
    val gap = (RibbonBandContentGap.value * density.density).toInt()
    val rowHeight = ((bandContentHeight - 4 * gap) / 3.0f).toInt()

    val optimalWidth = bandResizePolicy.getPreferredWidth(band, bandContentHeight, gap)
    val rowCount = bandResizePolicy.rowCount
    Layout(modifier = Modifier.fillMaxHeight().width((optimalWidth / density.density).dp)
        .padding(horizontal = RibbonBandContentGap),
        content = {
            // Project all the content
            var currRow = 1
            var currX = 0
            val widthForContent = optimalWidth - 2 * gap
            for (projection in band.flowComponentProjections) {
                val projectionWidth = projection.intrinsicWidth(rowHeight)
                if ((currX + projectionWidth) > widthForContent) {
                    currX = 0
                    currRow++
                }
                currX += (projectionWidth + gap)

                val rowEnum = when (rowCount) {
                    1 -> RibbonBandRow.Middle
                    2 -> if (currRow == 1) RibbonBandRow.Top else RibbonBandRow.Bottom
                    else -> when (currRow) {
                        1 -> RibbonBandRow.Top
                        2 -> RibbonBandRow.Middle
                        else -> RibbonBandRow.Bottom
                    }
                }
                CompositionLocalProvider(LocalRibbonBandRow provides rowEnum) {
                    projection.project(Modifier)
                }
            }
        },
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth

            val placeables = measurables.map { it.measure(Constraints()) }

            // Compute vertical gap for balanced, vertically centered layout of the rows
            val verticalGap = (constraints.maxHeight - rowCount * rowHeight) / (rowCount + 1)

            layout(width = width, height = constraints.maxHeight) {
                var x = 0
                var y = verticalGap

                for (placeable in placeables) {
                    if (x + placeable.measuredWidth > width) {
                        x = 0
                        y += (rowHeight + verticalGap)
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
private fun RibbonBandTitle(band: AbstractRibbonBand) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current

    CompositionLocalProvider(
        LocalRibbonTrackBounds provides false,
        LocalRibbonTrackKeyTips provides true
    ) {
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
                            tokensOverlayProvider = null,
                            inactiveContainerType = ContainerType.Muted,
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
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
}

@OptIn(AuroraInternalApi::class)
private class BandLocator(
    val band: AbstractRibbonBand,
    val topLeftOffset: AuroraOffset,
    val size: MutableState<IntSize>,
    val trackBounds: Boolean
) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)
        topLeftOffset.x = converted.x
        topLeftOffset.y = converted.y

        // And store the component size
        size.value = coordinates.size

        if (trackBounds) {
            BandBoundsTracker.trackBounds(
                band,
                AuroraRect(
                    x = converted.x,
                    y = converted.y,
                    width = coordinates.size.width.toFloat(),
                    height = coordinates.size.height.toFloat()
                )
            )
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
private fun Modifier.bandLocator(
    band: AbstractRibbonBand,
    topLeftOffset: AuroraOffset,
    size: MutableState<IntSize>,
    trackBounds: Boolean
) = this.then(BandLocator(band, topLeftOffset, size, trackBounds))

private val RibbonBandTitleAreaPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
private val RibbonBandTitleLabelPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
private val RibbonBandExpandButtonContentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp)
private val RibbonBandContentGap: Dp = 4.dp
