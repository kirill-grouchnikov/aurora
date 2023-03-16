package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.hexadecimal
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.PresentationPriority
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryContentModel
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryPresentationModel
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.component.utils.ArrowSizingConstants
import org.pushingpixels.aurora.component.utils.drawArrow
import org.pushingpixels.aurora.component.utils.drawDoubleArrow
import org.pushingpixels.aurora.theming.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonGallery(
    modifier: Modifier,
    presentationPriority: PresentationPriority,
    contentModel: RibbonGalleryContentModel,
    presentationModel: RibbonGalleryPresentationModel
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val baseTextStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val resolvedTextStyle = remember { resolveDefaults(baseTextStyle, layoutDirection) }
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType

    val flatCommandList = contentModel.commandGroups.map { it.commands }.flatten()

    val visibleCount = presentationModel.preferredVisibleCommandCounts[presentationPriority]!!
    val fullCount = flatCommandList.size
    val firstVisibleIndex = remember { mutableStateOf(0) }
    val lastVisibleIndex = derivedStateOf { min(firstVisibleIndex.value + visibleCount - 1, fullCount - 1) }
    println("Showing [${firstVisibleIndex.value} - ${lastVisibleIndex.value}] out of $fullCount")

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
                    colorSchemeBundle = null,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowWidth = ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val arrowHeight = ArrowSizingConstants.DefaultDoubleArrowHeight.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (firstVisibleIndex.value > 0),
        action = {
            firstVisibleIndex.value = firstVisibleIndex.value - visibleCount
        })
    val bottomScrollerCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    colorSchemeBundle = null,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowWidth = ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val arrowHeight = ArrowSizingConstants.DefaultDoubleArrowHeight.toPx()
                            val dx = (iconSize.toPx() - arrowWidth) / 2
                            val dy = (iconSize.toPx() - arrowHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawArrow(
                                    drawScope = this,
                                    width = arrowWidth,
                                    height = arrowHeight,
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = (lastVisibleIndex.value != (fullCount - 1)),
        action = {
            firstVisibleIndex.value = firstVisibleIndex.value + visibleCount
        })
    val showFullGalleryInPopupCommand = Command(text = "",
        icon = object : TransitionAwarePainterDelegate() {
            override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                return TransitionAwarePainter(
                    iconSize = ArrowSizingConstants.DefaultDoubleArrowWidth,
                    decorationAreaType = decorationAreaType,
                    skinColors = colors,
                    colorSchemeBundle = null,
                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                    paintDelegate = { drawScope, iconSize, colorScheme ->
                        with(drawScope) {
                            val arrowDoubleWidth =
                                ArrowSizingConstants.DefaultDoubleArrowHeight.toPx() +
                                        ArrowSizingConstants.DefaultDoubleArrowGap.toPx()
                            val arrowDoubleHeight =
                                ArrowSizingConstants.DefaultDoubleArrowWidth.toPx()
                            val dx = (iconSize.toPx() - arrowDoubleWidth) / 2
                            val dy = (iconSize.toPx() - arrowDoubleHeight) / 2
                            val alpha = if (modelStateInfoSnapshot.currModelState.isDisabled)
                                colors.getAlpha(
                                    decorationAreaType,
                                    modelStateInfoSnapshot.currModelState
                                ) else 1.0f
                            translate(left = dx, top = dy) {
                                drawDoubleArrow(
                                    drawScope = this,
                                    width = arrowDoubleWidth,
                                    height = arrowDoubleHeight,
                                    gap = ArrowSizingConstants.DefaultDoubleArrowGap.toPx(),
                                    strokeWidth = ArrowSizingConstants.DefaultDoubleArrowStroke.toPx(),
                                    popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
                                    layoutDirection = layoutDirection,
                                    color = colorScheme.markColor.withAlpha(alpha)
                                )
                            }
                        }
                    },
                    density = density
                )
            }
        },
        isActionEnabled = true,
        action = {
            println("Show popup gallery!")
        })


    Layout(content = {
        Row(
            modifier = modifier.auroraBorder(
                sides = Sides(
                    openSides = setOf(Side.Trailing),
                    straightSides = setOf(Side.Trailing)
                )
            ).padding(presentationModel.contentPadding),
            horizontalArrangement = Arrangement.spacedBy(presentationModel.layoutGap)
        ) {
            for (index in firstVisibleIndex.value .. lastVisibleIndex.value) {
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
                    CommandButtonPresentationModel.Overlay(
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
                    CommandButtonPresentationModel.Overlay(
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
                    CommandButtonPresentationModel.Overlay(
                        sides = Sides(
                            straightSides = setOf(
                                Side.Top,
                                Side.Leading
                            ), openSides = setOf(Side.Top)
                        )
                    )
                )
            ).project(modifier = Modifier.weight(1.0f / 3.0f))
        }
    },
        measurePolicy = { measurables, constraints ->
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
                    )
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
                    )
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
}
