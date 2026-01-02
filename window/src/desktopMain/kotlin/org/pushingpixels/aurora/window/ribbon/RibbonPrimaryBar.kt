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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.AuroraHorizontallyScrollableBox
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManagerMedium
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.ribbon.RibbonContextualTaskGroup
import org.pushingpixels.aurora.component.ribbon.impl.LocalRibbonTrackBounds
import org.pushingpixels.aurora.component.ribbon.impl.LocalRibbonTrackKeyTips
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerTokens
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette
import java.lang.Integer.min
import kotlin.math.max

internal data class RibbonContextualTaskGroupLayoutInfo(
    val ribbonContextualTaskGroup: RibbonContextualTaskGroup,
    val startX: Int,
    val endX: Int
)

private data class RibbonTaskInfo(
    val taskCommand: Command,
    val taskKeyTip: String?,
    val contextualTaskGroup: RibbonContextualTaskGroup?
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun RibbonPrimaryBar(
    modifier: Modifier,
    ribbon: Ribbon,
    onContextualTaskGroupSpansUpdated: (List<RibbonContextualTaskGroupLayoutInfo>) -> Unit,
    selectedTaskButtonModifier: Modifier,
    showSelectedTaskInPopup: Boolean,
    onUpdateShowSelectedTaskInPopup: (Boolean) -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val buttonShaper = AuroraSkin.buttonShaper

    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    val layoutGap = (TaskbarPrimaryBarGap.value * density.density).toInt()
    var everythingFits by remember { mutableStateOf(false) }

    // Primary bar content is:
    //
    // AppMenuButton | Scrollable row of task toggle buttons | Anchored commands
    //
    // If the entire content fits withing the available horizontal space, anchored commands are projected
    // with their original presentation states. Otherwise, all anchored commands are projected with the
    // small presentation state, and horizontal space left after app menu button and anchored commands
    // is given to the task toggle buttons.
    //
    // We use SubcomposeLayout to implement the presentation overlay logic of anchored commands during
    // composition / projection.

    // Pre-compute preferred sizes of all elements

    // Start with the application menu button (optional)
    var applicationMenuButtonPreferredSize: Size? = null
    if (ribbon.applicationMenuCommandButtonProjection != null) {
        val applicationMenuCommandButtonLayoutManager =
            ribbon.applicationMenuCommandButtonProjection!!.presentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = resolvedTextStyle,
                fontFamilyResolver = fontFamilyResolver
            )
        applicationMenuButtonPreferredSize = applicationMenuCommandButtonLayoutManager.getPreferredSize(
            command = ribbon.applicationMenuCommandButtonProjection!!.contentModel,
            presentationModel = ribbon.applicationMenuCommandButtonProjection!!.presentationModel,
            preLayoutInfo = applicationMenuCommandButtonLayoutManager.getPreLayoutInfo(
                ribbon.applicationMenuCommandButtonProjection!!.contentModel,
                ribbon.applicationMenuCommandButtonProjection!!.presentationModel
            ),
            buttonShaper = buttonShaper
        )
    }

    // Next is toggle buttons for all the ribbon tasks
    val ribbonTaskInfoList: List<RibbonTaskInfo> = ribbon.tasks.map { task ->
        RibbonTaskInfo(
            Command(
                text = task.title,
                icon = null,
                action = {
                    if (!task.isActive) {
                        task.onClick.invoke()
                    }
                },
                isActionToggle = true,
                isActionToggleSelected = task.isActive,
                tag = task
            ),
            task.keyTip,
            null
        )
    } + ribbon.contextualTaskGroups.flatMap { contextualTaskGroup ->
        contextualTaskGroup.tasks.map { contextualTask ->
            RibbonTaskInfo(
                Command(
                    text = contextualTask.title,
                    icon = null,
                    action = {
                        if (!contextualTask.isActive) {
                            contextualTask.onClick.invoke()
                        }
                    },
                    isActionToggle = true,
                    isActionToggleSelected = contextualTask.isActive,
                    tag = contextualTask
                ),
                contextualTask.keyTip,
                contextualTaskGroup
            )
        }
    }

    var combinedTaskButtonsWidth = 0
    var maxTaskButtonHeight = 0

    val taskButtonPresentationModel = CommandButtonPresentationModel(
        presentationState = RibbonTaskToggle,
        contentPadding = TaskbarPrimaryBarTaskButtonContentPadding,
        minWidth = TaskbarPrimaryBarTaskButtonMinWidth,
        horizontalAlignment = HorizontalAlignment.Center,
        sides = Sides(openSides = hashSetOf(Side.Bottom), straightSides = hashSetOf(Side.Bottom)),
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
    )
    val taskButtonLayoutManager = taskButtonPresentationModel.presentationState.createLayoutManager(
        layoutDirection = layoutDirection,
        density = density,
        textStyle = resolvedTextStyle,
        fontFamilyResolver = fontFamilyResolver
    )

    val combinedContextualGroupSpanMap: MutableMap<RibbonContextualTaskGroup, Pair<Int, Int>> = hashMapOf()
    for (contextualTaskGroup in ribbon.contextualTaskGroups) {
        combinedContextualGroupSpanMap[contextualTaskGroup] = Pair(Int.MAX_VALUE, Int.MIN_VALUE)
    }

    val taskButtonLayoutGap = (TaskbarPrimaryBarTaskButtonsGap.value * density.density).toInt()
    val startContentPadding =
        (TaskbarPrimaryBarContentPadding.calculateStartPadding(layoutDirection).value * density.density).toInt()
    var currTaskButtonX = startContentPadding

    if (applicationMenuButtonPreferredSize != null) {
        currTaskButtonX += (applicationMenuButtonPreferredSize.width.toInt()) + layoutGap
    }
    for (ribbonTaskInfo in ribbonTaskInfoList) {
        val currTaskButtonPreferredSize = taskButtonLayoutManager.getPreferredSize(
            command = ribbonTaskInfo.taskCommand,
            presentationModel = taskButtonPresentationModel,
            preLayoutInfo = taskButtonLayoutManager.getPreLayoutInfo(
                ribbonTaskInfo.taskCommand,
                taskButtonPresentationModel
            ),
            buttonShaper = buttonShaper
        )
        combinedTaskButtonsWidth += currTaskButtonPreferredSize.width.toInt()
        maxTaskButtonHeight = max(maxTaskButtonHeight, currTaskButtonPreferredSize.height.toInt())

        if (ribbonTaskInfo.contextualTaskGroup != null) {
            val currentCombinedSpan = combinedContextualGroupSpanMap[ribbonTaskInfo.contextualTaskGroup]!!
            val updatedMin = min(currentCombinedSpan.first, currTaskButtonX)
            val updatedMax =
                max(currentCombinedSpan.second, currTaskButtonX + currTaskButtonPreferredSize.width.toInt())
            combinedContextualGroupSpanMap[ribbonTaskInfo.contextualTaskGroup] = Pair(updatedMin, updatedMax)
        }

        currTaskButtonX += (currTaskButtonPreferredSize.width.toInt() + taskButtonLayoutGap)
    }
    if (ribbonTaskInfoList.isNotEmpty()) {
        combinedTaskButtonsWidth += taskButtonLayoutGap * (ribbonTaskInfoList.size - 1)
    }

    // And finally the anchored commands. We pre-compute their combined size twice, once at their original
    // presentation state, and once at the small state
    var combinedAnchoredCommandsOriginalWidth = 0
    var combinedAnchoredCommandsSmallWidth = 0
    var maxAnchoredCommandHeight = 0

    val anchoredCommandsOverlayOriginal = BaseCommandButtonPresentationModel.Overlay(
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd
    )
    val anchoredCommandsOverlaySmall = BaseCommandButtonPresentationModel.Overlay(
        presentationState = CommandButtonPresentationState.Small,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
        popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignEnd
    )
    for (anchoredCommandProjection in ribbon.anchoredCommands) {
        val presentationModelWithOriginal =
            anchoredCommandProjection.presentationModel.overlayWith(anchoredCommandsOverlayOriginal)
        val presentationModelWithSmall =
            anchoredCommandProjection.presentationModel.overlayWith(anchoredCommandsOverlaySmall)
        val layoutManagerWithOriginal = presentationModelWithOriginal.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )
        val layoutManagerWithSmall = presentationModelWithSmall.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )
        val currAnchoredCommandOriginalPreferredSize = layoutManagerWithOriginal.getPreferredSize(
            command = anchoredCommandProjection.contentModel,
            presentationModel = presentationModelWithOriginal,
            preLayoutInfo = layoutManagerWithOriginal.getPreLayoutInfo(
                anchoredCommandProjection.contentModel,
                presentationModelWithOriginal
            ),
            buttonShaper = buttonShaper
        )
        val currAnchoredCommandSmallPreferredSize = layoutManagerWithSmall.getPreferredSize(
            command = anchoredCommandProjection.contentModel,
            presentationModel = presentationModelWithSmall,
            preLayoutInfo = layoutManagerWithSmall.getPreLayoutInfo(
                anchoredCommandProjection.contentModel,
                presentationModelWithSmall
            ),
            buttonShaper = buttonShaper
        )

        combinedAnchoredCommandsOriginalWidth += currAnchoredCommandOriginalPreferredSize.width.toInt()
        maxAnchoredCommandHeight =
            max(maxAnchoredCommandHeight, currAnchoredCommandOriginalPreferredSize.height.toInt())
        combinedAnchoredCommandsSmallWidth += currAnchoredCommandSmallPreferredSize.width.toInt()
        maxAnchoredCommandHeight = max(maxAnchoredCommandHeight, currAnchoredCommandSmallPreferredSize.height.toInt())
    }
    if (ribbon.anchoredCommands.isNotEmpty()) {
        val anchoredLayoutGap = (TaskbarPrimaryBarAnchoredCommandsGap.value * density.density).toInt()
        combinedAnchoredCommandsOriginalWidth += anchoredLayoutGap * (ribbon.anchoredCommands.size - 1)
        combinedAnchoredCommandsSmallWidth += anchoredLayoutGap * (ribbon.anchoredCommands.size - 1)
    }

    // Our height is the max of all three part heights
    var finalHeight = max(maxTaskButtonHeight, maxAnchoredCommandHeight)
    if (applicationMenuButtonPreferredSize != null) {
        finalHeight = max(finalHeight, applicationMenuButtonPreferredSize.height.toInt())
    }
    finalHeight += (TaskbarPrimaryBarContentPadding.calculateTopPadding().value * density.density).toInt()

    var fullWidthNeeded = 0
    if (applicationMenuButtonPreferredSize != null) {
        fullWidthNeeded = applicationMenuButtonPreferredSize.width.toInt()
    }
    if ((fullWidthNeeded > 0) && (combinedTaskButtonsWidth > 0)) {
        fullWidthNeeded += layoutGap
    }
    fullWidthNeeded += combinedTaskButtonsWidth
    if ((fullWidthNeeded > 0) && (combinedAnchoredCommandsOriginalWidth > 0)) {
        fullWidthNeeded += layoutGap
    }
    fullWidthNeeded += combinedAnchoredCommandsOriginalWidth

    if (!everythingFits || ribbon.contextualTaskGroups.isEmpty()) {
        // Note special case where we kick in scrolling for the task toggle buttons.
        // In that case it would be too distracting to display the contextual task group highlights
        // in the title pane of the window, so we treat this case as no-highlights
        onContextualTaskGroupSpansUpdated(emptyList())
    } else {
        // Need to convert the map of combined spans to a flat list, ordered by the order of
        // contextual task groups in the ribbon
        val layoutInfoList: MutableList<RibbonContextualTaskGroupLayoutInfo> = arrayListOf()
        for (contextualTaskGroup in ribbon.contextualTaskGroups) {
            val combinedSpan = combinedContextualGroupSpanMap[contextualTaskGroup]!!
            layoutInfoList.add(
                RibbonContextualTaskGroupLayoutInfo(
                    contextualTaskGroup,
                    combinedSpan.first, combinedSpan.second
                )
            )
        }
        onContextualTaskGroupSpansUpdated(layoutInfoList)
    }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors

    val taskButtonRowScrollState: ScrollState = rememberScrollState(0)
    Box(
        modifier.auroraBackground()
            .fillMaxWidth()
            .height((finalHeight / density.density).dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val separatorTokens = getContainerTokens(
                colors = skinColors,
                tokensOverlayProvider = null,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Separator,
                componentState = ComponentState.Enabled,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                inactiveContainerType = ContainerType.Neutral
            )
            val separatorPrimaryColor = if (separatorTokens.isDark) {
                separatorTokens.complementaryContainerOutline.withAlpha(0.28125f)
            } else {
                separatorTokens.containerOutline.withAlpha(0.375f)
            }

            // Horizontal separator line across the entire bottom edge
            drawLine(
                color = separatorPrimaryColor,
                start = Offset(0.0f, size.height - 0.5f),
                end = Offset(size.width, size.height - 0.5f)
            )

            // Vertical separators along the left and right edges of each contextual task group
            val separatorBrush = Brush.verticalGradient(
                0.0f to separatorPrimaryColor,
                0.4f to separatorPrimaryColor,
                0.75f to separatorPrimaryColor.withAlpha(0.0f),
                1.0f to separatorPrimaryColor.withAlpha(0.0f),
                startY = 0.0f,
                endY = size.height,
                tileMode = TileMode.Repeated
            )

            for (contextualTaskGroup in ribbon.contextualTaskGroups) {
                val combinedSpan = combinedContextualGroupSpanMap[contextualTaskGroup]!!
                val startX = combinedSpan.first.toFloat() + 0.5f
                val endX = combinedSpan.second.toFloat() - 0.5f
                drawLine(
                    brush = separatorBrush,
                    start = Offset(startX, 0.0f),
                    end = Offset(startX, size.height),
                    strokeWidth = 1.0f
                )
                drawLine(
                    brush = separatorBrush,
                    start = Offset(endX, 0.0f),
                    end = Offset(endX, size.height),
                    strokeWidth = 1.0f
                )
            }
        }

        SubcomposeLayout(
            modifier = Modifier.fillMaxSize().padding(TaskbarPrimaryBarContentPadding)
        ) { constraints ->
            val widthAvailable = constraints.maxWidth
            val heightAvailable = constraints.maxHeight

            everythingFits = (fullWidthNeeded <= widthAvailable)

            // Application menu command button (optional)
            val applicationMenuCommandButtonPlaceable =
                if (applicationMenuButtonPreferredSize != null) {
                    subcompose(1) {
                        CompositionLocalProvider(
                            LocalRibbonTrackBounds provides false,
                            LocalRibbonTrackKeyTips provides true
                        ) {
                            ribbon.applicationMenuCommandButtonProjection?.project()
                        }
                    }.first().measure(
                        Constraints.fixed(
                            applicationMenuButtonPreferredSize.width.toInt(),
                            applicationMenuButtonPreferredSize.height.toInt()
                        )
                    )
                } else {
                    null
                }

            // Anchored commands
            val anchoredCommandsPlaceable = subcompose(2) {
                Row(horizontalArrangement = Arrangement.spacedBy(TaskbarPrimaryBarAnchoredCommandsGap)) {
                    for (anchored in ribbon.anchoredCommands) {
                        anchored.reproject(modifier = Modifier,
                            primaryOverlay = if (everythingFits) anchoredCommandsOverlayOriginal else anchoredCommandsOverlaySmall,
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() })
                    }
                }
            }.first().measure(
                Constraints.fixed(
                    if (everythingFits) combinedAnchoredCommandsOriginalWidth else combinedAnchoredCommandsSmallWidth,
                    maxAnchoredCommandHeight
                )
            )

            // Task toggle buttons
            val widthAvailableForTaskButtons = if (everythingFits) {
                combinedTaskButtonsWidth
            } else {
                var widthLeft = widthAvailable
                if (applicationMenuButtonPreferredSize != null) {
                    widthLeft -= (applicationMenuButtonPreferredSize.width.toInt() + layoutGap)
                }
                if (combinedAnchoredCommandsSmallWidth > 0) {
                    widthLeft -= (combinedAnchoredCommandsSmallWidth + layoutGap)
                }
                widthLeft
            }

            val taskButtonsPlaceable = subcompose(3) {
                AuroraHorizontallyScrollableBox(
                    modifier = Modifier.width(width = (widthAvailableForTaskButtons / density.density).dp),
                    height = (maxTaskButtonHeight / density.density).dp,
                    contentWidth = { combinedTaskButtonsWidth },
                    horizontalScrollState = taskButtonRowScrollState,
                    scrollAmount = 12.dp,
                    content = {
                        for ((index, ribbonTaskInfo) in ribbonTaskInfoList.withIndex()) {
                            if (index > 0) {
                                Spacer(modifier = Modifier.width(TaskbarPrimaryBarTaskButtonsGap))
                            }
                            CompositionLocalProvider(
                                LocalRibbonTrackBounds provides false,
                                LocalRibbonTrackKeyTips provides true
                            ) {
                                AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
                                    var presentationForCurrent = taskButtonPresentationModel.overlayWith(
                                        BaseCommandButtonPresentationModel.Overlay(
                                            actionKeyTip = ribbonTaskInfo.taskKeyTip
                                        )
                                    )
                                    if (ribbonTaskInfo.contextualTaskGroup != null) {
                                        val isDark = skinColors.getNeutralContainerTokens(DecorationAreaType.ControlPane).isDark
                                        val palette = TonalPalette.fromHct(Hct.fromInt(
                                            ribbonTaskInfo.contextualTaskGroup.hueColor.toArgb()))
                                        val surface = if (isDark) palette.getHct(80.0) else palette.getHct(45.0)
                                        val containerColorTokens = org.pushingpixels.aurora.theming.palette.getContainerTokens(
                                            seed = surface,
                                            containerConfiguration = if (isDark) ContainerConfiguration.defaultDark()
                                                else ContainerConfiguration.defaultLight()
                                        )
                                        presentationForCurrent = presentationForCurrent.overlayWith(
                                            BaseCommandButtonPresentationModel.Overlay(
                                                colorTokensOverlayProvider = ContainerColorTokensOverlay.Provider { _, _ ->
                                                    ContainerColorTokensOverlay(
                                                        activeContainerTokens = containerColorTokens,
                                                        mutedContainerTokens = containerColorTokens,
                                                        neutralContainerTokens = containerColorTokens
                                                    )
                                                },
                                            )
                                        )
                                    }

                                    RibbonTaskToggleButton(
                                        modifier = if (ribbonTaskInfo.taskCommand.isActionToggleSelected) {
                                            selectedTaskButtonModifier
                                        } else {
                                            Modifier
                                        },
                                        originalProjection = CommandButtonProjection(
                                            ribbonTaskInfo.taskCommand,
                                            presentationForCurrent
                                        ),
                                        command = ribbonTaskInfo.taskCommand,
                                        presentationModel = presentationForCurrent,
                                        showSelectedTaskInPopup = showSelectedTaskInPopup,
                                        onUpdateShowSelectedTaskInPopup = onUpdateShowSelectedTaskInPopup
                                    )
                                }
                            }
                        }
                    }
                )
            }.first().measure(
                Constraints.fixed(
                    widthAvailableForTaskButtons,
                    maxTaskButtonHeight
                )
            )

            layout(widthAvailable, heightAvailable) {
                applicationMenuCommandButtonPlaceable?.placeRelative(
                    x = startContentPadding,
                    y = heightAvailable - applicationMenuCommandButtonPlaceable.measuredHeight
                )
                val xForTaskButtons = if (applicationMenuCommandButtonPlaceable != null) {
                    applicationMenuCommandButtonPlaceable.measuredWidth + layoutGap
                } else {
                    startContentPadding
                }
                taskButtonsPlaceable.placeRelative(
                    x = xForTaskButtons,
                    y = heightAvailable - taskButtonsPlaceable.measuredHeight
                )

                val xForAnchoredCommands = widthAvailable - anchoredCommandsPlaceable.measuredWidth
                anchoredCommandsPlaceable.placeRelative(
                    x = xForAnchoredCommands,
                    y = heightAvailable - anchoredCommandsPlaceable.measuredHeight
                )
            }
        }
    }
}

private val RibbonTaskToggle: CommandButtonPresentationState =
    object : CommandButtonPresentationState("RibbonTaskToggle") {
        override fun createLayoutManager(
            layoutDirection: LayoutDirection,
            density: Density,
            textStyle: TextStyle,
            fontFamilyResolver: FontFamily.Resolver
        ): CommandButtonLayoutManager {
            return CommandButtonLayoutManagerRibbonTaskToggle(
                layoutDirection,
                density,
                textStyle,
                fontFamilyResolver
            )
        }
    }

@OptIn(AuroraInternalApi::class)
private class CommandButtonLayoutManagerRibbonTaskToggle(
    layoutDirection: LayoutDirection,
    _density: Density,
    textStyle: TextStyle,
    fontFamilyResolver: FontFamily.Resolver
) : CommandButtonLayoutManagerMedium(layoutDirection, _density, textStyle, fontFamilyResolver) {
    override fun getActionKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        // horizontally centered at the bottom edge of the action click area
        return Offset(
            x = layoutInfo.actionClickArea.left + layoutInfo.actionClickArea.width / 2,
            y = layoutInfo.actionClickArea.top + layoutInfo.actionClickArea.height
        )
    }

    override fun getPopupKeyTipAnchorCenterPoint(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        layoutInfo: CommandButtonLayoutManager.CommandButtonLayoutInfo
    ): Offset {
        // horizontally centered at the bottom edge of the popup click area
        return Offset(
            x = layoutInfo.popupClickArea.left + layoutInfo.popupClickArea.width / 2,
            y = layoutInfo.popupClickArea.top + layoutInfo.popupClickArea.height
        )
    }
}

private val TaskbarPrimaryBarGap = 8.dp
private val TaskbarPrimaryBarTaskButtonMinWidth = 120.dp
private val TaskbarPrimaryBarTaskButtonContentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp)
private val TaskbarPrimaryBarTaskButtonsGap = 6.dp
private val TaskbarPrimaryBarAnchoredCommandsGap = 4.dp
private val TaskbarPrimaryBarContentPadding = PaddingValues(start = 4.dp, end = 4.dp, top = 2.dp)

