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
package org.pushingpixels.aurora.component.utils.popup

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.BaseCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.auroraPopupMenuRowBackground
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.utils.TitleLabel
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

internal data class ColorSelectorPopupContentLayoutInfo(
    override val popupSize: Size,
    val menuButtonPresentationModel: CommandButtonPresentationModel,
    val showTrailingSeparator: BooleanArray,
    val gutterWidth: Float
) : BaseCommandMenuPopupLayoutInfo

internal object ColorSelectorCommandMenuPopupHandler : BaseCommandMenuHandler<
        ColorSelectorMenuContentModel, ColorSelectorCommandPopupMenuPresentationModel,
        ColorSelectorPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
    ): ColorSelectorPopupContentLayoutInfo {
        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val menuButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.menuPresentationState,
            iconActiveFilterStrategy = IconFilterStrategy.Original,
            iconEnabledFilterStrategy = IconFilterStrategy.Original,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = CommandButtonSizingConstants.CompactButtonContentPadding,
            isMenu = true,
            sides = Sides(straightSides = Side.values().toSet())
        )

        val layoutManager: CommandButtonLayoutManager =
            menuButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var gutterWidth = 0.0f
        var atLeastOneButtonHasIcon = false
        for (entry in menuContentModel.entries) {
            if (entry is ColorSelectorPopupMenuCommand) {
                if ((entry.command.icon != null) || (entry.command.isActionToggle)) {
                    atLeastOneButtonHasIcon = true
                    if (gutterWidth == 0.0f) {
                        gutterWidth =
                            (menuButtonPresentationModel.contentPadding.calculateStartPadding(layoutDirection) +
                                    layoutManager.getPreferredIconSize(
                                        command = entry.command,
                                        presentationModel = menuButtonPresentationModel
                                    ).width +
                                    layoutManager.getIconTextGap(menuButtonPresentationModel) / 2.0f).value * density.density
                    }
                }
            }
        }

        val menuButtonPresentationModelOverlay =
            CommandButtonPresentationModel.Overlay(
                forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
        val menuButtonPresentationModelWithOverlay =
            menuButtonPresentationModel.overlayWith(
                menuButtonPresentationModelOverlay
            )

        // First pass - go over all the entries to determine the width of the popup
        // as the max of preferred widths of commands and section selectors. Here we
        // also compute how much height the commands need.
        var maxWidth = 0.0f
        var combinedHeight = 0.0f
        for (entry in menuContentModel.entries) {
            when (entry) {
                // For the command section, their width is determined by the command itself
                // (icon, title, presentation model)
                is ColorSelectorPopupMenuCommand -> {
                    val preferredSize = layoutManager.getPreferredSize(
                        command = entry.command,
                        presentationModel = menuButtonPresentationModelWithOverlay,
                        preLayoutInfo = layoutManager.getPreLayoutInfo(
                            command = entry.command,
                            presentationModel = menuButtonPresentationModelWithOverlay
                        )
                    )
                    maxWidth = max(maxWidth, preferredSize.width)
                    combinedHeight += preferredSize.height
                }

                // For the rest of the sections, their width is determined by how many color
                // cells they display in a single row
                else -> {
                    val cellCount = menuPresentationModel.colorColumns
                    val sectionWidthDp =
                        menuPresentationModel.sectionContentPadding.calculateLeftPadding(layoutDirection) +
                                cellCount * menuPresentationModel.colorCellSize +
                                (cellCount - 1) * menuPresentationModel.colorCellGap +
                                menuPresentationModel.sectionContentPadding.calculateRightPadding(layoutDirection)
                    maxWidth = max(maxWidth, sectionWidthDp.value * density.density)
                }
            }
        }

        // Second pass - go over all section entries to determine how much height their
        // titles need under the computed popup width. This is where we complete the
        // computation of the overall popup height.
        val titleLabelPresentationModel = LabelPresentationModel(
            horizontalAlignment = HorizontalAlignment.Leading,
            textStyle = menuPresentationModel.sectionTitleTextStyle
        )
        val resolvedTitleTextStyle = resolveDefaults(
            textStyle.merge(menuPresentationModel.sectionTitleTextStyle),
            layoutDirection
        )

        for (entry in menuContentModel.entries) {
            when (entry) {
                is ColorSelectorPopupMenuCommand -> {
                    // We already processed preferred width and height in the first pass.
                    // Nothing more to do here.
                }

                is ColorSelectorPopupMenuSection -> {
                    combinedHeight += getLabelPreferredHeight(
                        contentModel = LabelContentModel(text = entry.title),
                        presentationModel = titleLabelPresentationModel,
                        resolvedTextStyle = resolvedTitleTextStyle,
                        layoutDirection = layoutDirection,
                        density = density,
                        fontFamilyResolver = fontFamilyResolver,
                        availableWidth = maxWidth
                    )
                    combinedHeight += (menuPresentationModel.sectionContentPadding.calculateTopPadding() +
                            menuPresentationModel.colorCellSize +
                            menuPresentationModel.sectionContentPadding.calculateBottomPadding()).value *
                            density.density
                }

                is ColorSelectorPopupMenuSectionWithDerived -> {
                    combinedHeight += getLabelPreferredHeight(
                        contentModel = LabelContentModel(text = entry.title),
                        presentationModel = titleLabelPresentationModel,
                        resolvedTextStyle = resolvedTitleTextStyle,
                        layoutDirection = layoutDirection,
                        density = density,
                        fontFamilyResolver = fontFamilyResolver,
                        availableWidth = maxWidth
                    )
                    // One row of seed colors, and multiple rows of derived colors. We also have content
                    // padding around the whole content, and a vertical gap between the seed colors and the
                    // block of derived colors rows (no gaps between inside that block)
                    combinedHeight += (menuPresentationModel.sectionContentPadding.calculateTopPadding() +
                            menuPresentationModel.colorCellSize +
                            menuPresentationModel.colorCellGap +
                            menuPresentationModel.colorCellSize * entry.derivedCount +
                            menuPresentationModel.sectionContentPadding.calculateBottomPadding()).value *
                            density.density

                }

                is ColorSelectorPopupMenuRecentsSection -> {
                    combinedHeight += getLabelPreferredHeight(
                        contentModel = LabelContentModel(text = entry.title),
                        presentationModel = titleLabelPresentationModel,
                        resolvedTextStyle = resolvedTitleTextStyle,
                        layoutDirection = layoutDirection,
                        density = density,
                        fontFamilyResolver = fontFamilyResolver,
                        availableWidth = maxWidth
                    )
                    // Account for one row of cells, even when there are no recent colors to show yet
                    combinedHeight += (menuPresentationModel.sectionContentPadding.calculateTopPadding() +
                            menuPresentationModel.colorCellSize +
                            menuPresentationModel.sectionContentPadding.calculateBottomPadding()).value *
                            density.density
                }
            }
        }

        // Third pass (technically can combine with the first, but splitting for clarity) -
        // which entries should show a trailing horizontal separator? We show a separator for
        // every entry that has title and is followed directly by a command entry
        val showTrailingSeparator = BooleanArray(menuContentModel.entries.size)
        for ((index, entry) in menuContentModel.entries.withIndex()) {
            showTrailingSeparator[index] = (index < (menuContentModel.entries.size - 1) &&
                    (entry !is ColorSelectorPopupMenuCommand) &&
                    (menuContentModel.entries[index + 1] is ColorSelectorPopupMenuCommand))
            if (showTrailingSeparator[index]) {
                // horizontal separator is 1dp tall
                combinedHeight += density.density
            }
        }

        return ColorSelectorPopupContentLayoutInfo(
            popupSize = Size(
                width = maxWidth,
                height = combinedHeight
            ),
            menuButtonPresentationModel = menuButtonPresentationModelWithOverlay,
            showTrailingSeparator = showTrailingSeparator,
            gutterWidth = gutterWidth
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: ColorSelectorPopupContentLayoutInfo
    ) {
        val menuButtonPresentationModel = popupContentLayoutInfo.menuButtonPresentationModel

        val sectionTitlePresentationModel = LabelPresentationModel(
            textStyle = menuPresentationModel.sectionTitleTextStyle,
            horizontalAlignment = HorizontalAlignment.Leading
        )

        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        Column(
            modifier = Modifier.fillMaxSize().background(color = backgroundColorScheme.backgroundFillColor)
                .padding(all = 1.0.dp)
        ) {
            for ((index, entry) in menuContentModel.entries.withIndex()) {
                when (entry) {
                    is ColorSelectorPopupMenuCommand -> {
                        // Check if we have a presentation overlay for this secondary command
                        val hasOverlay = overlays.containsKey(entry.command)
                        val currSecondaryPresentationModel = if (hasOverlay)
                            menuButtonPresentationModel.overlayWith(overlays[entry.command]!!)
                        else menuButtonPresentationModel
                        // Project a command button for each secondary command, passing the same
                        // overlays into it.
                        CommandButtonProjection(
                            contentModel = entry.command,
                            presentationModel = currSecondaryPresentationModel,
                            overlays = overlays
                        ).project(
                            modifier = Modifier.fillMaxWidth().auroraPopupMenuRowBackground(gutterWidth = popupContentLayoutInfo.gutterWidth),
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() }
                        )
                    }

                    is ColorSelectorPopupMenuSection -> {
                        ColorSelectorSection(
                            menuContentModel = menuContentModel,
                            menuPresentationModel = menuPresentationModel,
                            sectionTitle = entry.title,
                            sectionColors = entry.colors,
                            sectionTitlePresentationModel = sectionTitlePresentationModel,
                            showTrailingSeparator = popupContentLayoutInfo.showTrailingSeparator[index]
                        )
                    }

                    is ColorSelectorPopupMenuSectionWithDerived -> {
                        ColorSelectorSectionWithDerived(
                            menuContentModel = menuContentModel,
                            menuPresentationModel = menuPresentationModel,
                            sectionTitle = entry.title,
                            sectionColors = entry.colors,
                            sectionDerivedCount = entry.derivedCount,
                            sectionTitlePresentationModel = sectionTitlePresentationModel,
                            showTrailingSeparator = popupContentLayoutInfo.showTrailingSeparator[index]
                        )
                    }

                    is ColorSelectorPopupMenuRecentsSection -> {
                        val fullRecentColors = RecentlyUsed.getRecentlyUsedColors()
                        val recentColors = fullRecentColors.subList(
                            fromIndex = 0,
                            toIndex = min(menuPresentationModel.colorColumns, fullRecentColors.size)
                        )
                        ColorSelectorSection(
                            menuContentModel = menuContentModel,
                            menuPresentationModel = menuPresentationModel,
                            sectionTitle = entry.title,
                            sectionColors = recentColors,
                            sectionTitlePresentationModel = sectionTitlePresentationModel,
                            showTrailingSeparator = popupContentLayoutInfo.showTrailingSeparator[index]
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun ColorSelectorSectionWithDerived(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        sectionTitle: String,
        sectionColors: List<Color>,
        sectionDerivedCount: Int,
        sectionTitlePresentationModel: LabelPresentationModel,
        showTrailingSeparator: Boolean
    ) {
        val layoutDirection = LocalLayoutDirection.current

        Column(modifier = Modifier.fillMaxWidth()) {
            TitleLabel(
                modifier = Modifier.fillMaxWidth(),
                title = sectionTitle,
                presentationModel = sectionTitlePresentationModel
            )

            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(menuPresentationModel.sectionContentPadding.calculateTopPadding())
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    start = menuPresentationModel.sectionContentPadding.calculateStartPadding(layoutDirection),
                    end = menuPresentationModel.sectionContentPadding.calculateEndPadding(layoutDirection)
                ),
                horizontalArrangement = Arrangement.spacedBy(menuPresentationModel.colorCellGap)
            ) {
                for (color in sectionColors) {
                    ColorSelectorCell(
                        menuContentModel = menuContentModel,
                        menuPresentationModel = menuPresentationModel,
                        color = color,
                        sides = Sides(straightSides = Side.values().toSet())
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(menuPresentationModel.colorCellGap))

            for (derivedRow in 1..sectionDerivedCount) {
                val openSides = hashSetOf<Side>()
                if (derivedRow > 1) {
                    openSides.add(Side.Top)
                }
                if (derivedRow < sectionDerivedCount) {
                    openSides.add(Side.Bottom)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(
                        start = menuPresentationModel.sectionContentPadding.calculateStartPadding(layoutDirection),
                        end = menuPresentationModel.sectionContentPadding.calculateEndPadding(layoutDirection)
                    ),
                    horizontalArrangement = Arrangement.spacedBy(menuPresentationModel.colorCellGap)
                ) {
                    for (color in sectionColors) {
                        val primaryHsb = RGBtoHSB(from = color)

                        var bFactor: Float = (derivedRow - 1).toFloat() / sectionDerivedCount.toFloat()
                        bFactor = bFactor.toDouble().pow(1.5).toFloat()
                        var brightness = 1.0f - bFactor

                        if (primaryHsb[1] == 0.0f) {
                            // special handling for gray scale
                            val max: Float = 0.5f + 0.5f * primaryHsb[2]
                            brightness = max * (sectionDerivedCount - derivedRow + 1) / sectionDerivedCount
                        }

                        val secondary = HSBtoRGB(
                            floatArrayOf(
                                primaryHsb[0],
                                primaryHsb[1] * (derivedRow + 1) / (sectionDerivedCount + 1),
                                brightness
                            )
                        )

                        ColorSelectorCell(
                            menuContentModel = menuContentModel,
                            menuPresentationModel = menuPresentationModel,
                            color = secondary,
                            sides = Sides(straightSides = Side.values().toSet(), openSides = openSides)
                        )
                    }
                }
            }

            if (showTrailingSeparator) {
                BottomLine()
            }

            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(menuPresentationModel.sectionContentPadding.calculateBottomPadding())
            )

        }
    }

    @Composable
    private fun ColorSelectorSection(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        sectionTitle: String,
        sectionColors: List<Color>,
        sectionTitlePresentationModel: LabelPresentationModel,
        showTrailingSeparator: Boolean
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TitleLabel(
                modifier = Modifier.fillMaxWidth(),
                title = sectionTitle,
                presentationModel = sectionTitlePresentationModel
            )

            if (sectionColors.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(menuPresentationModel.sectionContentPadding),
                    horizontalArrangement = Arrangement.spacedBy(menuPresentationModel.colorCellGap)
                ) {
                    for (color in sectionColors) {
                        ColorSelectorCell(
                            menuContentModel = menuContentModel,
                            menuPresentationModel = menuPresentationModel,
                            color = color,
                            sides = Sides(straightSides = Side.values().toSet())
                        )
                    }
                }
            } else {
                // Reserve vertical space even if there are no recent colors to show
                Box(
                    modifier = Modifier.fillMaxWidth().padding(menuPresentationModel.sectionContentPadding)
                        .height(menuPresentationModel.colorCellSize)
                )
            }

            if (showTrailingSeparator) {
                BottomLine()
            }
        }
    }

    @Composable
    private fun BottomLine() {
        val skinColors = AuroraSkin.colors
        val decorationAreaType = AuroraSkin.decorationAreaType
        val borderColorScheme = skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = ColorSchemeAssociationKind.HighlightBorder,
            componentState = ComponentState.Enabled
        )
        val borderColor = borderColorScheme.lineColor

        Box(modifier = Modifier.fillMaxWidth().height(1.0.dp)) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val width = this.size.width

                drawLine(
                    color = borderColor,
                    start = Offset(0.5f, 0.5f),
                    end = Offset(width - 0.5f, 0.5f),
                    strokeWidth = 1.0f
                )
            }
        }
    }

    @Composable
    private fun ColorSelectorCell(
        menuContentModel: ColorSelectorMenuContentModel,
        menuPresentationModel: ColorSelectorCommandPopupMenuPresentationModel,
        color: Color,
        sides: Sides
    ) {
        val skinColors = AuroraSkin.colors
        val decorationAreaType = AuroraSkin.decorationAreaType

        var wasRollover by remember { mutableStateOf(false) }

        val interactionSource = remember { MutableInteractionSource() }
        val rollover by interactionSource.collectIsHoveredAsState()
        val rolloverTransition = updateTransition(rollover)
        val rolloverFraction by rolloverTransition.animateFloat(
            transitionSpec = {
                tween(durationMillis = AuroraSkin.animationConfig.regular)
            }
        ) {
            when (it) {
                false -> 0.0f
                true -> 1.0f
            }
        }

        if (!wasRollover && rollover) {
            SideEffect {
                menuContentModel.onColorPreviewActivated.onColorPreviewActivated(color)
            }
        }
        if (wasRollover && !rollover) {
            SideEffect {
                menuContentModel.onColorPreviewActivated.onColorPreviewCanceled(color)
            }
        }
        wasRollover = rollover

        Box(
            modifier = Modifier.size(size = menuPresentationModel.colorCellSize)
                .clickable(interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        menuContentModel.onColorActivated.invoke(color)
                        RecentlyUsed.addColorToRecentlyUsed(color)
                        AuroraPopupManager.hidePopups(null)
                    })
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = this.size.width
                val height = this.size.height

                drawRect(color = color)

                val hsb = RGBtoHSB(from = color)
                val brightness = hsb[2] * 0.7f
                val borderColor = Color(brightness, brightness, brightness)

                val borderOutline = getBaseOutline(width, height, 0.0f, sides, 0.0f, OutlineKind.Border)
                drawOutline(
                    outline = borderOutline,
                    color = borderColor,
                    style = Stroke(1.0f)
                )

                if (rolloverFraction > 0.0f) {
                    val highlightBorderScheme = skinColors.getColorScheme(
                        decorationAreaType = decorationAreaType,
                        associationKind = ColorSchemeAssociationKind.HighlightBorder,
                        componentState = ComponentState.RolloverUnselected
                    )

                    drawRect(
                        color = highlightBorderScheme.midColor,
                        style = Stroke(1.0f),
                        alpha = rolloverFraction
                    )

                    drawRect(
                        color = highlightBorderScheme.ultraDarkColor,
                        style = Stroke(1.0f),
                        topLeft = Offset(1.0f, 1.0f),
                        size = Size(width - 2.0f, height - 2.0f),
                        alpha = rolloverFraction
                    )
                }
            }
        }

        DisposableEffect(interactionSource) {
            onDispose {
                // This is to handle the case where the popup is canceled (with Escape key,
                // for example) while the mouse is hovering over this cell. We need to tell
                // the application code to cancel color preview.
                if (rollover) {
                    menuContentModel.onColorPreviewActivated.onColorPreviewCanceled(color)
                }
            }
        }
    }
}
