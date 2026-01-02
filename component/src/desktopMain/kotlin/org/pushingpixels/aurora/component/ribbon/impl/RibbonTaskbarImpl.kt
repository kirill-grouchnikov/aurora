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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.utils.getEndwardDoubleArrowIcon
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.utils.ContainerType

private data class TaskbarExpandCommand(
    override val icon: Painter,
    override val secondaryContentModel: TaskbarExpandMenuContentModel,
) : BaseCommand {
    override val text = ""
    override val extraText = null
    override val action = null
    override val actionPreview = null
    override val isSecondaryEnabled = true
    override val secondaryRichTooltip = null
    override val isActionEnabled = false
    override val isActionToggle = false
    override val isActionToggleSelected = false
    override val actionRichTooltip = null
    override val onTriggerActionToggleSelectedChange = null
    override val tag = null
}

private data class TaskbarExpandMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val elements: List<RibbonTaskbarElement>,
    val taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy,
    val taskbarKeyTipPolicyStartElement: () -> Int
) : BaseCommandMenuContentModel

private data class TaskbarExpandCommandPopupMenuPresentationModel(
    val combinedWidths: Int
) : BaseCommandPopupMenuPresentationModel

private class TaskbarExpandCommandButtonPresentationModel(
    val combinedWidths: Int,
    override val popupKeyTip: String
) :
    BaseCommandButtonPresentationModel {
    override val presentationState = CommandButtonPresentationState.Small
    override val backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    override val contentPadding: PaddingValues =
        PaddingValues(start = 2.dp, end = 2.dp,
            top = CommandButtonSizingConstants.CompactButtonContentPadding.calculateTopPadding(),
            bottom = CommandButtonSizingConstants.CompactButtonContentPadding.calculateBottomPadding())
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center
    override val iconDimension: DpSize? = null
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original
    override val forceAllocateSpaceForIcon = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val textStyle: TextStyle? = null
    override val textOverflow: TextOverflow = TextOverflow.Clip
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
    override val toDismissPopupsOnActivation: Boolean = true
    override val popupMenuPresentationModel: TaskbarExpandCommandPopupMenuPresentationModel =
        TaskbarExpandCommandPopupMenuPresentationModel(combinedWidths = combinedWidths)
    override val minWidth: Dp = 0.dp
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupAnchorBoundsProvider: (() -> Rect)? = null
    override val horizontalGapScaleFactor = 1.0f
    override val verticalGapScaleFactor = 1.0f
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize
    override val showPopupIcon: Boolean = false
    override val sides: Sides = Sides.ClosedRectangle

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): TaskbarExpandCommandButtonPresentationModel {
        return TaskbarExpandCommandButtonPresentationModel(this.combinedWidths, this.popupKeyTip)
    }
}

private data class TaskbarExpandPopupContentLayoutInfo(
    override val popupSize: Size,
) : BaseCascadingCommandMenuPopupLayoutInfo

private object TaskbarExpandCommandMenuPopupHandler : CascadingCommandMenuHandler<
        TaskbarExpandMenuContentModel, TaskbarExpandCommandPopupMenuPresentationModel,
        TaskbarExpandPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: TaskbarExpandMenuContentModel,
        menuPresentationModel: TaskbarExpandCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): TaskbarExpandPopupContentLayoutInfo {

        val startPadding = TaskbarExpandPopupContentPadding.calculateStartPadding(layoutDirection)
        val endPadding = TaskbarExpandPopupContentPadding.calculateEndPadding(layoutDirection)
        val topPadding = TaskbarExpandPopupContentPadding.calculateTopPadding()
        val bottomPadding = TaskbarExpandPopupContentPadding.calculateBottomPadding()

        val heightDp = TaskbarExpandPopupHeight + topPadding + bottomPadding

        return TaskbarExpandPopupContentLayoutInfo(
            popupSize = Size(
                width = menuPresentationModel.combinedWidths + (startPadding + endPadding).value * density.density,
                height = heightDp.value * density.density
            )
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: TaskbarExpandMenuContentModel,
        menuPresentationModel: TaskbarExpandCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: TaskbarExpandPopupContentLayoutInfo
    ) {
        AuroraDecorationArea(
            decorationAreaType = DecorationAreaType.Header,
            buttonShaper = AuroraSkin.buttonShaper
        ) {
            Layout(modifier = Modifier.auroraBackgroundNoOverlays()
                .padding(TaskbarExpandPopupContentPadding),
                content = {
                    TaskbarContent(
                        menuContentModel.elements,
                        menuContentModel.taskbarKeyTipPolicy,
                        menuContentModel.taskbarKeyTipPolicyStartElement.invoke()
                    )
                },
                measurePolicy = { measurables, _ ->
                    val height = TaskbarExpandPopupHeight.toPx().toInt()
                    val gap = TaskbarLayoutGap.toPx().toInt()

                    val placeables = mutableListOf<Placeable>()
                    var fullWidth = 0
                    for ((index, measurable) in measurables.withIndex()) {
                        val neededWidth = measurable.maxIntrinsicWidth(height)
                        val needsGapAfter = (index < measurables.size - 1)
                        placeables.add(
                            measurable.measure(
                                Constraints.fixed(
                                    width = measurable.maxIntrinsicWidth(height),
                                    height = measurable.maxIntrinsicHeight(neededWidth)
                                )
                            )
                        )
                        fullWidth += (neededWidth + (if (needsGapAfter) gap else 0))
                    }

                    layout(width = fullWidth, height = height) {
                        var x = 0
                        for (placeable in placeables) {
                            val currWidth = placeable.measuredWidth
                            val currHeight = placeable.measuredHeight
                            placeable.placeRelative(x, (height - currHeight) / 2)
                            x += (currWidth + gap)
                        }
                    }
                })
        }
    }
}

private class TaskbarExpandCommandButtonProjection(
    contentModel: TaskbarExpandCommand,
    presentationModel: TaskbarExpandCommandButtonPresentationModel,
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<TaskbarExpandCommand, TaskbarExpandCommandButtonPresentationModel, TaskbarExpandCommandButtonProjection>(
    contentModel, presentationModel, secondaryOverlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = TaskbarExpandCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): TaskbarExpandCommandButtonProjection {
        return TaskbarExpandCommandButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = this.secondaryOverlays
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() },
            popupHandler = TaskbarExpandCommandMenuPopupHandler,
        )
    }

    @Composable
    override fun reproject(
        modifier: Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay,
        actionInteractionSource: MutableInteractionSource,
        popupInteractionSource: MutableInteractionSource
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = primaryOverlay,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = TaskbarExpandCommandMenuPopupHandler,
        )
    }
}

@AuroraInternalApi
@Composable
fun RibbonTaskbar(
    modifier: Modifier,
    elements: List<RibbonTaskbarElement>,
    taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy
) {
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType
    val density = LocalDensity.current

    var overflowCombinedWidth by remember { mutableStateOf(0) }
    val overflowElements = remember { mutableStateListOf<RibbonTaskbarElement>() }

    Layout(modifier = modifier,
        content = {
            TaskbarContent(elements, taskbarKeyTipPolicy, 1)

            TaskbarExpandCommandButtonProjection(
                contentModel = TaskbarExpandCommand(
                    icon = getEndwardDoubleArrowIcon(
                        decorationAreaType = decorationAreaType,
                        skinColors = colors,
                        tokensOverlayProvider = null,
                        inactiveContainerType = ContainerType.Muted,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                        density = density
                    ),
                    secondaryContentModel = TaskbarExpandMenuContentModel(
                        elements = overflowElements,
                        taskbarKeyTipPolicy = taskbarKeyTipPolicy,
                        taskbarKeyTipPolicyStartElement = {
                            // Go over elements displayed in the taskbar (not overflow)
                            var taken = 0
                            for (index in 0 until (elements.count() - overflowElements.count())) {
                                when (val element = elements[index]) {
                                    is RibbonTaskbarCommand -> {
                                        val needsActionTip = (element.commandProjection.contentModel.action != null) &&
                                                element.commandProjection.contentModel.isActionEnabled
                                        val needsPopupTip = (element.commandProjection.contentModel.secondaryContentModel != null) &&
                                                element.commandProjection.contentModel.isSecondaryEnabled
                                        if (needsActionTip) {
                                            taken++
                                        }
                                        if (needsPopupTip) {
                                            taken++
                                        }
                                    }

                                    is RibbonTaskbarGallery -> {
                                        taken++
                                    }

                                    else -> {}
                                }
                            }
                            taken + 1
                        }
                    )
                ),
                presentationModel = TaskbarExpandCommandButtonPresentationModel(
                    combinedWidths = overflowCombinedWidth,
                    popupKeyTip = taskbarKeyTipPolicy.overflowButtonKeyTip
                )
            ).project()
        },
        measurePolicy = { measurables, constraints ->
            val maxWidthPx = constraints.maxWidth
            val height = constraints.maxHeight
            val gap = TaskbarLayoutGap.toPx().toInt()

            val contentMeasurables = measurables.subList(0, elements.size)
            val expandButtonMeasurable = measurables.last()

            // Measure the max intrinsic width of every single child (including the expand button)
            val measuredWidths = measurables.map { it.maxIntrinsicWidth(height) }
            // How much width is needed to show all the content children with gaps between them,
            // but excluding the expand button?
            val fullWidthNeeded = measuredWidths.subList(0, measuredWidths.size - 1).sum() +
                    gap * (elements.size - 1)
            // Can we show all the content?
            val canFitAllContent = (fullWidthNeeded <= maxWidthPx)
            // What is the width available to display content? If all content does not fit, we account
            // for the expand button and the gap before it
            val maxAvailableWidthPx = if (canFitAllContent) maxWidthPx
            else maxWidthPx - measuredWidths.last() - gap

            val visibleContentPlaceables = mutableListOf<Placeable>()
            var currentlyTakenWidth = 0
            var isOverflowing = false
            // Measure all content children that fit
            for ((index, measurable) in contentMeasurables.withIndex()) {
                val neededWidth = measuredWidths[index]
                val needsGapAfter = (index < contentMeasurables.size - 1)
                val takenWidthWithThisChild = currentlyTakenWidth + neededWidth +
                        if (needsGapAfter) gap else 0
                if (!isOverflowing && (takenWidthWithThisChild <= maxAvailableWidthPx)) {
                    visibleContentPlaceables.add(
                        measurable.measure(
                            Constraints.fixed(
                                width = neededWidth,
                                height = measurable.maxIntrinsicHeight(neededWidth)
                            )
                        )
                    )
                    currentlyTakenWidth += (neededWidth + gap)
                } else {
                    if (!isOverflowing) {
                        // Starts overflowing from this element
                        overflowElements.clear()
                        overflowElements.addAll(elements.subList(index, elements.size))

                        overflowCombinedWidth = measuredWidths.subList(index, measuredWidths.size - 1).sum() +
                                gap * (elements.size - 1 - index)
                    }
                    isOverflowing = true
                }
            }

            if (!isOverflowing && (measurables.size > 1)) {
                // Remove the "trailing" gap
                currentlyTakenWidth -= gap
            }

            // And finally measure the expand button if needed
            var expandButtonPlaceable: Placeable? = null
            if (!canFitAllContent) {
                val expandButtonWidth = measuredWidths.last()
                expandButtonPlaceable = expandButtonMeasurable.measure(
                    Constraints.fixed(
                        width = expandButtonWidth,
                        height = expandButtonMeasurable.maxIntrinsicHeight(expandButtonWidth)
                    )
                )
                currentlyTakenWidth += expandButtonWidth
            }

            layout(width = currentlyTakenWidth, height = height) {
                var x = 0
                for (placeable in visibleContentPlaceables) {
                    val currWidth = placeable.measuredWidth
                    val currHeight = placeable.measuredHeight
                    placeable.placeRelative(x, (height - currHeight) / 2)
                    x += (currWidth + gap)
                }

                expandButtonPlaceable?.placeRelative(x, (height - expandButtonPlaceable.height) / 2)
            }
        })
}

@Composable
private fun TaskbarContent(
    elements: List<RibbonTaskbarElement>,
    taskbarKeyTipPolicy: RibbonTaskbarKeyTipPolicy,
    startContentIndex: Int
) {
    var contentIndex = startContentIndex
    for (element in elements) {
        when (element) {
            is RibbonTaskbarCommand -> {
                val needsActionTip = (element.commandProjection.contentModel.action != null) &&
                        element.commandProjection.contentModel.isActionEnabled
                val needsPopupTip = (element.commandProjection.contentModel.secondaryContentModel != null) &&
                        element.commandProjection.contentModel.isSecondaryEnabled
                element.commandProjection.reproject(
                    modifier = Modifier,
                    primaryOverlay = BaseCommandButtonPresentationModel.Overlay(
                        presentationState = CommandButtonPresentationState.Small,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        actionKeyTip = if (needsActionTip) taskbarKeyTipPolicy.getContentKeyTip(contentIndex++) else null,
                        popupKeyTip = if (needsPopupTip) taskbarKeyTipPolicy.getContentKeyTip(contentIndex++) else null
                    ),
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )
            }

            is RibbonTaskbarGallery -> {
                val galleryContentModel = element.galleryProjection.contentModel
                val galleryPresentationModel = element.galleryProjection.presentationModel

                val galleryCommand = Command(
                    text = "",
                    icon = galleryContentModel.icon,
                    secondaryContentModel = CommandMenuContentModel(
                        onDeactivatePopup = {
                            // Mark the inline state to have the latest selected command button to be revealed
                            element.galleryProjection.inlineState.revealSelected()
                        },
                        panelContentModel = CommandPanelContentModel(
                            commandGroups = galleryContentModel.commandGroups
                        ),
                        groups = galleryContentModel.extraPopupGroups
                    ),
                    isSecondaryEnabled = true,
                    tag = element.galleryProjection
                )
                CommandButtonProjection(
                    contentModel = galleryCommand,
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Small,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                            panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                                layoutSpec = galleryPresentationModel.popupLayoutSpec,
                                contentPadding = PaddingValues(0.dp),
                                showGroupLabels = galleryContentModel.commandGroups.all { !it.title.isNullOrEmpty() },
                                commandPresentationState = galleryPresentationModel.commandButtonPresentationState,
                                commandPopupFireTrigger = galleryPresentationModel.commandPopupFireTrigger,
                                commandSelectedStateHighlight = galleryPresentationModel.commandSelectedStateHighlight
                            )
                        ),
                        popupKeyTip = taskbarKeyTipPolicy.getContentKeyTip(contentIndex++)
                    ),
                    secondaryOverlays = element.galleryProjection.secondaryOverlays
                ).project()
            }

            is RibbonTaskbarComponent -> {
                RibbonMetaComponentProjection(
                    projection = element.componentProjection,
                    enabled = { true },
                    ribbonComponentPresentationModel = RibbonComponentPresentationModel(
                        keyTip = taskbarKeyTipPolicy.getContentKeyTip(contentIndex++))
                ).project(modifier = Modifier)
            }
        }
    }
}

private val TaskbarLayoutGap = 4.dp
private val TaskbarExpandPopupHeight = 32.dp
private val TaskbarExpandPopupContentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp)
