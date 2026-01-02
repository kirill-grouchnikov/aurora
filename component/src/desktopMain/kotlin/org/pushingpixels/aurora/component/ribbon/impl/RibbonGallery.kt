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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.RibbonBandCommandButtonPresentationStates.BigFixed
import org.pushingpixels.aurora.component.ribbon.RibbonBandCommandButtonPresentationStates.BigFixedLandscape
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryContentModel
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryInlineState
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun ribbonGalleryIntrinsicWidth(
    contentModel: RibbonGalleryContentModel,
    presentationModel: RibbonGalleryPresentationModel,
    visibleCount: Int,
    height: Int
): Int {

    var resultPx: Float
    with (LocalDensity.current) {
        val layoutDirection = LocalLayoutDirection.current

        val heightForButtons = height - ((presentationModel.contentPadding.calculateTopPadding() +
                presentationModel.contentPadding.calculateBottomPadding()).toPx())
        // Leading margin
        resultPx = presentationModel.contentPadding.calculateStartPadding(layoutDirection).toPx()
        // Visible gallery buttons
        resultPx += when (presentationModel.commandButtonPresentationState) {
            CommandButtonPresentationState.Small -> visibleCount * heightForButtons / 3
            BigFixed -> visibleCount * heightForButtons
            BigFixedLandscape -> visibleCount * heightForButtons * 5 / 4
            else ->
                error("Presentation state ${presentationModel.commandButtonPresentationState.displayName} not supported")
        }
        // Gaps between and around the gallery buttons
        resultPx += (visibleCount + 1) * presentationModel.layoutGap.toPx()
        // Control button strip width
        resultPx += ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
        // Trailing margin
        resultPx += presentationModel.contentPadding.calculateEndPadding(layoutDirection).toPx()
    }

    return resultPx.toInt()
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonGallery(
    modifier: Modifier,
    originalProjection: Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>,
    contentModel: RibbonGalleryContentModel,
    presentationModel: RibbonGalleryPresentationModel,
    inlineState: RibbonGalleryInlineState
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val baseTextStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = LocalButtonShaper.current

    val resolvedTextStyle = remember { resolveDefaults(baseTextStyle, layoutDirection) }
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType

    val flatCommandList = contentModel.commandGroups.map { it.commands }.flatten()

    val visibleCount = inlineState.visibleCount
    val fullCount = flatCommandList.size
    if (inlineState.getAndClearRevealSelected()) {
        // The inline state has been marked to have the latest selected command button to be revealed
        for ((index, command) in flatCommandList.withIndex()) {
            if (command.isActionToggleSelected) {
                inlineState.revealAt(index)
                break
            }
        }
    }
    val buttonPresentationModel = CommandButtonPresentationModel(
        presentationState = presentationModel.commandButtonPresentationState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        horizontalAlignment = HorizontalAlignment.Center,
        textOverflow = presentationModel.commandButtonTextOverflow
    )

    // Note usage of Original icon filter strategy since we're using TransitionAwarePainterDelegate
    // to draw the arrows
    val scrollerButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = CommandButtonPresentationState.Small,
        iconActiveFilterStrategy = IconFilterStrategy.Original,
        iconEnabledFilterStrategy = IconFilterStrategy.Original,
        iconDisabledFilterStrategy = IconFilterStrategy.Original,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        contentPadding = PaddingValues(all = 0.dp)
    )

    val topScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    tokensOverlayProvider = null,
                    inactiveContainerType = ContainerType.Neutral,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorTokens ->
                        with(drawScope) {
                            val arrowWidth = ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val arrowHeight = ArrowSizingConstants.DefaultDoubleArrowHeight.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colorTokens.onContainerDisabledAlpha else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorTokens.onContainer.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (inlineState.firstVisibleIndex > 0),
        action = {
            inlineState.firstVisibleIndex -= visibleCount
        })
    val bottomScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    tokensOverlayProvider = null,
                    inactiveContainerType = ContainerType.Neutral,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorTokens ->
                        with(drawScope) {
                            val arrowWidth = ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val arrowHeight = ArrowSizingConstants.DefaultDoubleArrowHeight.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colorTokens.onContainerDisabledAlpha else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorTokens.onContainer.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (inlineState.lastVisibleIndex != (fullCount - 1)),
        action = {
            inlineState.firstVisibleIndex += visibleCount
        })
    val showFullGalleryInPopupCommand = Command(
        text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    tokensOverlayProvider = null,
                    inactiveContainerType = ContainerType.Neutral,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorTokens ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colorTokens.onContainerDisabledAlpha else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorTokens.onContainer.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        secondaryContentModel = CommandMenuContentModel(
            onDeactivatePopup = {
                // Mark the inline state to have the latest selected command button to be revealed
                inlineState.revealSelected()
            },
            panelContentModel = CommandPanelContentModel(commandGroups = contentModel.commandGroups),
            groups = contentModel.extraPopupGroups
        ),
        isSecondaryEnabled = true,
    )

    val galleryTopLeftOffset = remember { AuroraOffset(0.0f, 0.0f) }
    val gallerySize = remember { mutableStateOf(IntSize(0, 0)) }

    val trackBounds = LocalRibbonTrackBounds.current

    Layout(modifier = Modifier.galleryLocator(
        projection = originalProjection,
        topLeftOffset = galleryTopLeftOffset,
        size = gallerySize,
        trackBounds = trackBounds
    ),
        content = {
            Row(
                modifier = modifier.auroraBorder(
                    sides = Sides(
                        openSides = setOf(Side.Trailing),
                        straightSides = setOf(Side.Trailing)
                    )
                ).padding(presentationModel.contentPadding),
                horizontalArrangement = Arrangement.spacedBy(presentationModel.layoutGap)
            ) {
                for (index in inlineState.firstVisibleIndex..inlineState.lastVisibleIndex) {
                    CommandButtonProjection(
                        contentModel = flatCommandList[index],
                        presentationModel = buttonPresentationModel
                    ).project()
                }
            }

            Column {
                CommandButtonProjection(
                    contentModel = topScrollerCommand,
                    presentationModel = scrollerButtonPresentationModel.overlayWith(
                        BaseCommandButtonPresentationModel.Overlay(
                            sides = Sides(
                                straightSides = setOf(
                                    Side.Bottom,
                                    Side.Leading
                                )
                            )
                        )
                    )
                ).project(modifier = Modifier.weight(1.0f / 3.0f))
                CommandButtonProjection(
                    contentModel = bottomScrollerCommand,
                    presentationModel = scrollerButtonPresentationModel.overlayWith(
                        BaseCommandButtonPresentationModel.Overlay(
                            sides = Sides(
                                straightSides = setOf(
                                    Side.Bottom,
                                    Side.Top,
                                    Side.Leading
                                ), openSides = setOf(Side.Top)
                            )
                        )
                    )
                ).project(modifier = Modifier.weight(1.0f / 3.0f))
                CommandButtonProjection(
                    contentModel = showFullGalleryInPopupCommand,
                    presentationModel = scrollerButtonPresentationModel.overlayWith(
                        BaseCommandButtonPresentationModel.Overlay(
                            sides = Sides(
                                straightSides = setOf(
                                    Side.Top,
                                    Side.Leading
                                ), openSides = setOf(Side.Top)
                            ),
                            showPopupIcon = false,
                            popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                                panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                                    layoutSpec = presentationModel.popupLayoutSpec,
                                    contentPadding = PaddingValues(0.dp),
                                    showGroupLabels = contentModel.commandGroups.all { !it.title.isNullOrEmpty() },
                                    commandPresentationState = presentationModel.commandButtonPresentationState,
                                    commandPopupFireTrigger = presentationModel.commandPopupFireTrigger,
                                    commandSelectedStateHighlight = presentationModel.commandSelectedStateHighlight
                                )
                            ),
                            popupAnchorBoundsProvider = {
                                // Passing zero height as the anchor bounds is needed to vertically align
                                // the top edge of the popup with the top edge of our gallery
                                Rect(
                                    offset = galleryTopLeftOffset.asOffset(density),
                                    size = Size(width = gallerySize.value.width / density.density, height = 0.0f)
                                )
                            },
                            popupKeyTip = presentationModel.expandKeyTip
                        )
                    )
                ).project(modifier = Modifier.weight(1.0f / 3.0f))
            }
        },
        measurePolicy = { measurables, _ ->
            val buttonRow = measurables[0]
            val scrollerColumn = measurables[1]

            val buttonLayoutManager = presentationModel.commandButtonPresentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver
            )

            var buttonRowWidth = presentationModel.contentPadding.calculateStartPadding(layoutDirection).toPx()
            var buttonRowHeight = 0.0f
            for (index in 0 until visibleCount) {
                val buttonPreferredSize = buttonLayoutManager.getPreferredSize(
                    command = contentModel.commandGroups[0].commands[index],
                    presentationModel = buttonPresentationModel,
                    preLayoutInfo = buttonLayoutManager.getPreLayoutInfo(
                        command = contentModel.commandGroups[0].commands[index],
                        presentationModel = buttonPresentationModel
                    ),
                    buttonShaper = buttonShaper
                )
                buttonRowWidth += buttonPreferredSize.width
                buttonRowHeight = max(buttonRowHeight, buttonPreferredSize.height)
            }

            buttonRowWidth += ((visibleCount - 1) * presentationModel.layoutGap.toPx())
            buttonRowWidth += presentationModel.contentPadding.calculateEndPadding(layoutDirection).toPx()

            buttonRowHeight += (presentationModel.contentPadding.calculateTopPadding() + presentationModel.contentPadding.calculateBottomPadding()).toPx()

            val buttonRowPlaceable = buttonRow.measure(
                Constraints.fixed(
                    width = buttonRowWidth.roundToInt(),
                    height = buttonRowHeight.roundToInt()
                )
            )

            val scrollerLayoutManager = scrollerButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver
            )
            var scrollerColumnWidth = 0.0f
            for (scrollerCommand in listOf(topScrollerCommand, bottomScrollerCommand, showFullGalleryInPopupCommand)) {
                val scrollerButtonPreferredSize = scrollerLayoutManager.getPreferredSize(
                    command = scrollerCommand,
                    presentationModel = scrollerButtonPresentationModel,
                    preLayoutInfo = scrollerLayoutManager.getPreLayoutInfo(
                        command = scrollerCommand,
                        presentationModel = scrollerButtonPresentationModel
                    ),
                    buttonShaper = buttonShaper
                )
                scrollerColumnWidth = max(scrollerColumnWidth, scrollerButtonPreferredSize.width)
            }
            val scrollerButtonColumnPlaceable = scrollerColumn.measure(
                Constraints.fixed(
                    width = scrollerColumnWidth.roundToInt(),
                    height = buttonRowHeight.roundToInt()
                )
            )

            val totalWidth = buttonRowWidth + scrollerColumnWidth
            layout(width = totalWidth.roundToInt(), height = buttonRowHeight.roundToInt()) {
                buttonRowPlaceable.placeRelative(0, 0)
                scrollerButtonColumnPlaceable.placeRelative(buttonRowWidth.roundToInt(), 0)
            }
        })

    DisposableEffect(originalProjection) {
        onDispose {
            BoundsTracker.untrackBounds(originalProjection)
        }
    }
}

@OptIn(AuroraInternalApi::class)
private class GalleryLocator(
    val projection: Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>,
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
            BoundsTracker.trackBounds(
                projection,
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
private fun Modifier.galleryLocator(
    projection: Projection<RibbonGalleryContentModel, RibbonGalleryPresentationModel>,
    topLeftOffset: AuroraOffset,
    size: MutableState<IntSize>,
    trackBounds: Boolean
) = this.then(GalleryLocator(projection, topLeftOffset, size, trackBounds))
