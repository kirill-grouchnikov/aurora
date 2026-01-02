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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCascadingCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.popup.CascadingCommandMenuHandler
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import kotlin.math.max

data class CustomComplexCommand(
    override val icon: Painter,
    override val secondaryContentModel: CustomComplexMenuContentModel,
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

sealed interface CustomComplexPopupMenuEntry

data class CustomComplexPopupMenuCommand(val command: Command) : CustomComplexPopupMenuEntry
data class CustomComplexPopupMenuZoom(
    val title: String,
    val zoom: Int,
    val commandZoomOut: Command,
    val commandZoomIn: Command,
    val commandFullScreen: Command
) : CustomComplexPopupMenuEntry

data class CustomComplexPopupMenuEdit(
    val title: String,
    val commandCut: Command,
    val commandCopy: Command,
    val commandPaste: Command
) : CustomComplexPopupMenuEntry

data class CustomComplexPopupMenuHeader(
    val title: String,
    val commandSignIn: Command,
    val colors: List<Color>
) : CustomComplexPopupMenuEntry

data class CustomComplexPopupMenuFooter(
    val commandFooter: Command,
) : CustomComplexPopupMenuEntry

data class CustomComplexMenuContentSection(
    val entries: List<CustomComplexPopupMenuEntry>
)

data class CustomComplexMenuContentModel(
    override val onActivatePopup: (() -> Unit)? = null,
    override val onDeactivatePopup: (() -> Unit)? = null,
    val sections: List<CustomComplexMenuContentSection>
) : BaseCommandMenuContentModel

data class CustomComplexCommandPopupMenuPresentationModel(
    val itemPresentationState: CommandButtonPresentationState = DefaultCommandPopupMenuPresentationState,
    val itemPopupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnRollover,
    val itemSelectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.IconOnly,
    val itemIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val itemIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    val itemContentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Fill,
    val zoomPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            toDismissPopupsOnActivation = false,
            sides = Sides.ClosedRectangle,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
        ),
    val zoomLabelPresentationModel: LabelPresentationModel = LabelPresentationModel(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
        textMaxLines = 1,
        singleLineDisplayPrototype = "999%",
        horizontalAlignment = HorizontalAlignment.Center
    ),
    val fullScreenPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Small,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
            sides = Sides.ClosedRectangle,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ),
    val editPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            sides = Sides.ClosedRectangle,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ),
    val headerTitlePresentationModel: LabelPresentationModel =
        LabelPresentationModel(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            textMaxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Bold)
        ),
    val headerSignInPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            colorTokensOverlayProvider = ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                systemContainerType = SystemContainerType.Info),
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding
        ),
    val headerSeparatorHeight: Dp = 2.0.dp,
    val footerPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            sides = Sides.ClosedRectangle,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            horizontalAlignment = HorizontalAlignment.Leading
        )
) : BaseCommandPopupMenuPresentationModel

data class CustomComplexCommandButtonPresentationModel(
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    override val colorTokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null,
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    override val iconDimension: DpSize? = null,
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    override val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    override val textStyle: TextStyle? = null,
    override val textOverflow: TextOverflow = TextOverflow.Clip,
    override val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    override val toDismissPopupsOnActivation: Boolean = true,
    override val popupKeyTip: String? = null,
    override val popupMenuPresentationModel: CustomComplexCommandPopupMenuPresentationModel = CustomComplexCommandPopupMenuPresentationModel(),
    override val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    override val minWidth: Dp = 0.dp,
    override val sides: Sides = Sides()
) : BaseCommandButtonPresentationModel {
    override val presentationState = CommandButtonPresentationState.Small
    override val forceAllocateSpaceForIcon = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val popupFireTrigger: PopupFireTrigger = PopupFireTrigger.OnPressed
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupAnchorBoundsProvider: (() -> Rect)? = null
    override val horizontalGapScaleFactor = 1.0f
    override val verticalGapScaleFactor = 1.0f
    override val showPopupIcon: Boolean = false
    override val selectedStateHighlight: SelectedStateHighlight = SelectedStateHighlight.FullSize

    override fun overlayWith(overlay: BaseCommandButtonPresentationModel.Overlay): CustomComplexCommandButtonPresentationModel {
        return CustomComplexCommandButtonPresentationModel(
            colorTokensOverlayProvider = overlay.colorTokensOverlayProvider ?: this.colorTokensOverlayProvider,
            backgroundAppearanceStrategy = overlay.backgroundAppearanceStrategy
                ?: this.backgroundAppearanceStrategy,
            horizontalAlignment = overlay.horizontalAlignment ?: this.horizontalAlignment,
            iconDimension = overlay.iconDimension ?: this.iconDimension,
            iconDisabledFilterStrategy = overlay.iconDisabledFilterStrategy ?: this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = overlay.iconEnabledFilterStrategy ?: this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = overlay.iconActiveFilterStrategy ?: this.iconActiveFilterStrategy,
            textStyle = overlay.textStyle ?: this.textStyle,
            textOverflow = overlay.textOverflow ?: this.textOverflow,
            popupPlacementStrategy = overlay.popupPlacementStrategy ?: this.popupPlacementStrategy,
            toDismissPopupsOnActivation = overlay.toDismissPopupsOnActivation ?: this.toDismissPopupsOnActivation,
            popupKeyTip = overlay.popupKeyTip ?: this.popupKeyTip,
            popupMenuPresentationModel = (overlay.popupMenuPresentationModel as? CustomComplexCommandPopupMenuPresentationModel)
                ?: this.popupMenuPresentationModel,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            minWidth = overlay.minWidth ?: this.minWidth,
            sides = overlay.sides ?: this.sides
        )
    }
}

data class CustomComplexPopupContentLayoutInfo(
    override val popupSize: Size,
    val itemButtonPresentationModel: CommandButtonPresentationModel,
    val itemHeights: FloatArray
) : BaseCascadingCommandMenuPopupLayoutInfo

object CustomComplexCommandMenuPopupHandler : CascadingCommandMenuHandler<
        CustomComplexMenuContentModel, CustomComplexCommandPopupMenuPresentationModel,
        CustomComplexPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: CustomComplexMenuContentModel,
        menuPresentationModel: CustomComplexCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): CustomComplexPopupContentLayoutInfo {
        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val itemButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.itemPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.itemIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.itemIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.itemIconDisabledFilterStrategy,
            forceAllocateSpaceForIcon = false,
            popupPlacementStrategy = menuPresentationModel.popupPlacementStrategy,
            popupFireTrigger = menuPresentationModel.itemPopupFireTrigger,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = menuPresentationModel.horizontalAlignment,
            contentPadding = menuPresentationModel.itemContentPadding,
            selectedStateHighlight = menuPresentationModel.itemSelectedStateHighlight,
            sides = Sides.ClosedRectangle
        )

        val layoutManager: CommandButtonLayoutManager =
            itemButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        val itemHeights = FloatArray(menuContentModel.sections.sumOf { it.entries.size })

        // Rows such as zoom, edit and header have two areas: title and actions.
        // Treat the title area separately to be aligned with the command-based
        // rows. That is, do not allow the action area of any such row to vertically
        // overlap with the text of any command-based row.
        var maxPrimaryWidth = 0.0f
        var maxActionWidth = 0.0f
        var combinedHeight = 0.0f

        var entryIndex = 0

        for (section in menuContentModel.sections) {
            for (entry in section.entries) {
                when (entry) {
                    is CustomComplexPopupMenuCommand -> {
                        val preferredSize = layoutManager.getPreferredSize(
                            command = entry.command,
                            presentationModel = itemButtonPresentationModel,
                            preLayoutInfo = layoutManager.getPreLayoutInfo(
                                command = entry.command,
                                presentationModel = itemButtonPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )
                        maxPrimaryWidth = max(maxPrimaryWidth, preferredSize.width)
                        combinedHeight += preferredSize.height
                        itemHeights[entryIndex] = preferredSize.height
                    }

                    is CustomComplexPopupMenuZoom -> {
                        val titleWidth = getLabelPreferredSingleLineWidth(
                            contentModel = LabelContentModel(text = entry.title),
                            presentationModel = LabelPresentationModel(
                                contentPadding = menuPresentationModel.itemContentPadding,
                                textMaxLines = 1
                            ),
                            resolvedTextStyle = textStyle,
                            layoutDirection = layoutDirection,
                            density = density,
                            fontFamilyResolver = fontFamilyResolver
                        )

                        maxPrimaryWidth = max(maxPrimaryWidth, titleWidth)

                        val zoomLayoutManager =
                            menuPresentationModel.zoomPresentationModel.presentationState.createLayoutManager(
                                layoutDirection, density, textStyle, fontFamilyResolver
                            )
                        val zoomOutPreferredSize = zoomLayoutManager.getPreferredSize(
                            command = entry.commandZoomOut,
                            presentationModel = menuPresentationModel.zoomPresentationModel,
                            preLayoutInfo = zoomLayoutManager.getPreLayoutInfo(
                                command = entry.commandZoomOut,
                                presentationModel = menuPresentationModel.zoomPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )
                        val zoomLabelWidth = getLabelPreferredSingleLineWidth(
                            contentModel = LabelContentModel(
                                text = menuPresentationModel.zoomLabelPresentationModel.singleLineDisplayPrototype!!),
                            presentationModel = menuPresentationModel.zoomLabelPresentationModel,
                            resolvedTextStyle = textStyle,
                            layoutDirection = layoutDirection,
                            density = density,
                            fontFamilyResolver = fontFamilyResolver
                        )
                        val zoomInPreferredSize = zoomLayoutManager.getPreferredSize(
                            command = entry.commandZoomIn,
                            presentationModel = menuPresentationModel.zoomPresentationModel,
                            preLayoutInfo = zoomLayoutManager.getPreLayoutInfo(
                                command = entry.commandZoomIn,
                                presentationModel = menuPresentationModel.zoomPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )

                        val fullScreenLayoutManager =
                            menuPresentationModel.fullScreenPresentationModel.presentationState.createLayoutManager(
                                layoutDirection, density, textStyle, fontFamilyResolver
                            )
                        val fullScreenPreferredSize = fullScreenLayoutManager.getPreferredSize(
                            command = entry.commandFullScreen,
                            presentationModel = menuPresentationModel.fullScreenPresentationModel,
                            preLayoutInfo = fullScreenLayoutManager.getPreLayoutInfo(
                                command = entry.commandFullScreen,
                                presentationModel = menuPresentationModel.fullScreenPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )

                        maxActionWidth = max(
                            maxActionWidth,
                            zoomOutPreferredSize.width + zoomLabelWidth + zoomInPreferredSize.width
                                    + fullScreenPreferredSize.width
                                    + 2.0f * SeparatorSizingConstants.Thickness.value * density.density
                        )
                        combinedHeight += fullScreenPreferredSize.height
                        itemHeights[entryIndex] = fullScreenPreferredSize.height
                    }

                    is CustomComplexPopupMenuEdit -> {
                        val titleWidth = getLabelPreferredSingleLineWidth(
                            contentModel = LabelContentModel(text = entry.title),
                            presentationModel = LabelPresentationModel(
                                contentPadding = menuPresentationModel.itemContentPadding,
                                textMaxLines = 1
                            ),
                            resolvedTextStyle = textStyle,
                            layoutDirection = layoutDirection,
                            density = density,
                            fontFamilyResolver = fontFamilyResolver
                        )

                        maxPrimaryWidth = max(maxPrimaryWidth, titleWidth)

                        val editLayoutManager =
                            menuPresentationModel.editPresentationModel.presentationState.createLayoutManager(
                                layoutDirection, density, textStyle, fontFamilyResolver
                            )
                        val cutPreferredSize = editLayoutManager.getPreferredSize(
                            command = entry.commandCut,
                            presentationModel = menuPresentationModel.editPresentationModel,
                            preLayoutInfo = editLayoutManager.getPreLayoutInfo(
                                command = entry.commandCut,
                                presentationModel = menuPresentationModel.editPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )
                        val copyPreferredSize = editLayoutManager.getPreferredSize(
                            command = entry.commandCopy,
                            presentationModel = menuPresentationModel.editPresentationModel,
                            preLayoutInfo = editLayoutManager.getPreLayoutInfo(
                                command = entry.commandCopy,
                                presentationModel = menuPresentationModel.editPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )
                        val pastePreferredSize = editLayoutManager.getPreferredSize(
                            command = entry.commandPaste,
                            presentationModel = menuPresentationModel.editPresentationModel,
                            preLayoutInfo = editLayoutManager.getPreLayoutInfo(
                                command = entry.commandPaste,
                                presentationModel = menuPresentationModel.editPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )

                        maxActionWidth = max(
                            maxActionWidth,
                            cutPreferredSize.width + copyPreferredSize.width + pastePreferredSize.width
                                    + 3.0f * SeparatorSizingConstants.Thickness.value * density.density
                        )

                        combinedHeight += cutPreferredSize.height
                        itemHeights[entryIndex] = cutPreferredSize.height
                    }

                    is CustomComplexPopupMenuHeader -> {
                        val titleWidth = getLabelPreferredSingleLineWidth(
                            contentModel = LabelContentModel(text = entry.title),
                            presentationModel = menuPresentationModel.headerTitlePresentationModel,
                            resolvedTextStyle = textStyle,
                            layoutDirection = layoutDirection,
                            density = density,
                            fontFamilyResolver = fontFamilyResolver
                        )
                        val titleHeight = getLabelPreferredHeight(
                            contentModel = LabelContentModel(text = entry.title),
                            presentationModel = menuPresentationModel.headerTitlePresentationModel,
                            resolvedTextStyle = textStyle,
                            layoutDirection = layoutDirection,
                            density = density,
                            fontFamilyResolver = fontFamilyResolver,
                            availableWidth = Float.MAX_VALUE,
                        )

                        maxPrimaryWidth = max(maxPrimaryWidth, titleWidth)

                        val signInLayoutManager =
                            menuPresentationModel.editPresentationModel.presentationState.createLayoutManager(
                                layoutDirection, density, textStyle, fontFamilyResolver
                            )
                        val signInPreferredSize = signInLayoutManager.getPreferredSize(
                            command = entry.commandSignIn,
                            presentationModel = menuPresentationModel.headerSignInPresentationModel,
                            preLayoutInfo = signInLayoutManager.getPreLayoutInfo(
                                command = entry.commandSignIn,
                                presentationModel = menuPresentationModel.editPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )

                        maxActionWidth = max(maxActionWidth, signInPreferredSize.width)

                        val itemHeight =
                            titleHeight + menuPresentationModel.headerSeparatorHeight.value * density.density

                        combinedHeight += itemHeight
                        itemHeights[entryIndex] = itemHeight
                    }

                    is CustomComplexPopupMenuFooter -> {
                        val footerLayoutManager =
                            menuPresentationModel.footerPresentationModel.presentationState.createLayoutManager(
                                layoutDirection, density, textStyle, fontFamilyResolver
                            )
                        val preferredSize = footerLayoutManager.getPreferredSize(
                            command = entry.commandFooter,
                            presentationModel = menuPresentationModel.footerPresentationModel,
                            preLayoutInfo = footerLayoutManager.getPreLayoutInfo(
                                command = entry.commandFooter,
                                presentationModel = menuPresentationModel.footerPresentationModel
                            ),
                            buttonShaper = buttonShaper
                        )
                        maxPrimaryWidth = max(maxPrimaryWidth, preferredSize.width)
                        combinedHeight += preferredSize.height
                        itemHeights[entryIndex] = preferredSize.height
                    }
                }
                entryIndex++
            }
        }

        // account for horizontal separators
        combinedHeight += (menuContentModel.sections.size - 1) *
                (SeparatorSizingConstants.Thickness + 1.0.dp).value * density.density

        return CustomComplexPopupContentLayoutInfo(
            popupSize = Size(
                width = maxPrimaryWidth + 32 * density.density + maxActionWidth,
                height = combinedHeight
            ),
            itemButtonPresentationModel = itemButtonPresentationModel,
            itemHeights = itemHeights
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: CustomComplexMenuContentModel,
        menuPresentationModel: CustomComplexCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: CustomComplexPopupContentLayoutInfo
    ) {
        val density = LocalDensity.current
        val itemButtonPresentationModel = popupContentLayoutInfo.itemButtonPresentationModel

        val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Column(
            modifier = Modifier.fillMaxSize().background(color = neutralColorTokens.containerSurface)
                .padding(all = 1.0.dp)
        ) {
            var entryIndex = 0
            for ((sectionIndex, section) in menuContentModel.sections.withIndex()) {
                for (entry in section.entries) {
                    when (entry) {
                        is CustomComplexPopupMenuCommand -> {
                            // Check if we have a presentation overlay for this secondary command
                            val hasOverlay = overlays.containsKey(entry.command)
                            val currSecondaryPresentationModel = if (hasOverlay)
                                itemButtonPresentationModel.overlayWith(overlays[entry.command]!!)
                            else itemButtonPresentationModel
                            // Project a command button for each secondary command, passing the same
                            // overlays into it.
                            CommandButtonProjection(
                                contentModel = entry.command,
                                presentationModel = currSecondaryPresentationModel,
                                secondaryOverlays = overlays
                            ).project(
                                modifier = Modifier.fillMaxWidth(),
                                actionInteractionSource = remember { MutableInteractionSource() },
                                popupInteractionSource = remember { MutableInteractionSource() }
                            )
                        }

                        is CustomComplexPopupMenuZoom -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().height(
                                    (popupContentLayoutInfo.itemHeights[entryIndex] / density.density).dp
                                )
                            ) {
                                LabelProjection(
                                    contentModel = LabelContentModel(text = entry.title),
                                    presentationModel = LabelPresentationModel(
                                        textMaxLines = 1,
                                        contentPadding = menuPresentationModel.itemContentPadding
                                    )
                                ).project()

                                Spacer(modifier = Modifier.weight(1.0f))

                                VerticalSeparatorProjection(
                                    contentModel = SeparatorContentModel(),
                                    presentationModel = SeparatorPresentationModel(
                                        startGradientAmount = 0.dp,
                                        endGradientAmount = 0.dp
                                    )
                                ).project(modifier = Modifier.fillMaxHeight())
                                CommandButtonProjection(
                                    contentModel = entry.commandZoomOut,
                                    presentationModel = menuPresentationModel.zoomPresentationModel
                                ).project()
                                LabelProjection(
                                    contentModel = LabelContentModel(text = "${entry.zoom}%"),
                                    presentationModel = menuPresentationModel.zoomLabelPresentationModel
                                ).project()
                                CommandButtonProjection(
                                    contentModel = entry.commandZoomIn,
                                    presentationModel = menuPresentationModel.zoomPresentationModel
                                ).project()

                                VerticalSeparatorProjection(
                                    contentModel = SeparatorContentModel(),
                                    presentationModel = SeparatorPresentationModel(
                                        startGradientAmount = 0.dp,
                                        endGradientAmount = 0.dp
                                    )
                                ).project(modifier = Modifier.fillMaxHeight())
                                CommandButtonProjection(
                                    contentModel = entry.commandFullScreen,
                                    presentationModel = menuPresentationModel.fullScreenPresentationModel
                                ).project()
                            }
                        }

                        is CustomComplexPopupMenuEdit -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().height(
                                    (popupContentLayoutInfo.itemHeights[entryIndex] / density.density).dp
                                )
                            ) {
                                LabelProjection(
                                    contentModel = LabelContentModel(text = entry.title),
                                    presentationModel = LabelPresentationModel(
                                        textMaxLines = 1,
                                        contentPadding = menuPresentationModel.itemContentPadding
                                    )
                                ).project()

                                Spacer(modifier = Modifier.weight(1.0f))

                                VerticalSeparatorProjection(
                                    contentModel = SeparatorContentModel(),
                                    presentationModel = SeparatorPresentationModel(
                                        startGradientAmount = 0.dp,
                                        endGradientAmount = 0.dp
                                    )
                                ).project(modifier = Modifier.fillMaxHeight())

                                CommandButtonProjection(
                                    contentModel = entry.commandCut,
                                    presentationModel = menuPresentationModel.editPresentationModel
                                ).project()

                                VerticalSeparatorProjection(
                                    contentModel = SeparatorContentModel(),
                                    presentationModel = SeparatorPresentationModel(
                                        startGradientAmount = 0.dp,
                                        endGradientAmount = 0.dp
                                    )
                                ).project(modifier = Modifier.fillMaxHeight())

                                CommandButtonProjection(
                                    contentModel = entry.commandCopy,
                                    presentationModel = menuPresentationModel.editPresentationModel
                                ).project()

                                VerticalSeparatorProjection(
                                    contentModel = SeparatorContentModel(),
                                    presentationModel = SeparatorPresentationModel(
                                        startGradientAmount = 0.dp,
                                        endGradientAmount = 0.dp
                                    )
                                ).project(modifier = Modifier.fillMaxHeight())

                                CommandButtonProjection(
                                    contentModel = entry.commandPaste,
                                    presentationModel = menuPresentationModel.editPresentationModel
                                ).project()
                            }
                        }

                        is CustomComplexPopupMenuHeader -> {
                            Column(
                                modifier = Modifier.fillMaxWidth().height(
                                    (popupContentLayoutInfo.itemHeights[entryIndex] / density.density).dp
                                )
                            ) {
                                Row(modifier = Modifier.fillMaxWidth().weight(1.0f),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(text = entry.title),
                                        presentationModel = menuPresentationModel.headerTitlePresentationModel
                                    ).project(modifier = Modifier.fillMaxHeight())

                                    Spacer(modifier = Modifier.weight(1.0f))

                                    CommandButtonProjection(
                                        contentModel = entry.commandSignIn,
                                        presentationModel = menuPresentationModel.headerSignInPresentationModel
                                    ).project(modifier = Modifier.padding(end = 8.dp))
                                }
                                Canvas(
                                    modifier = Modifier.fillMaxWidth()
                                        .height(menuPresentationModel.headerSeparatorHeight)
                                ) {
                                    drawRect(
                                        brush = Brush.horizontalGradient(
                                            colors = entry.colors
                                        )
                                    )
                                }
                            }
                        }

                        is CustomComplexPopupMenuFooter -> {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .height((popupContentLayoutInfo.itemHeights[entryIndex] / density.density).dp)
                                    .background(
                                        if (neutralColorTokens.isDark) {
                                            neutralColorTokens.containerSurfaceHigh
                                        } else {
                                            neutralColorTokens.containerSurfaceLow
                                        }
                                    )
                            ) {
                                CommandButtonProjection(
                                    contentModel = entry.commandFooter,
                                    presentationModel = menuPresentationModel.footerPresentationModel,
                                    secondaryOverlays = overlays
                                ).project(
                                    modifier = Modifier.fillMaxWidth(),
                                    actionInteractionSource = remember { MutableInteractionSource() },
                                    popupInteractionSource = remember { MutableInteractionSource() }
                                )
                            }
                        }
                    }
                    entryIndex++
                }

                if (sectionIndex < (menuContentModel.sections.size - 1)) {
                    Spacer(modifier = Modifier.fillMaxWidth().height(1.0.dp))
                    HorizontalSeparatorProjection(
                        contentModel = SeparatorContentModel(),
                        presentationModel = SeparatorPresentationModel(
                            startGradientAmount = 0.dp,
                            endGradientAmount = 0.dp
                        )
                    ).project(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

class CustomComplexCommandButtonProjection(
    contentModel: CustomComplexCommand,
    presentationModel: CustomComplexCommandButtonPresentationModel = CustomComplexCommandButtonPresentationModel(),
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<CustomComplexCommand, CustomComplexCommandButtonPresentationModel, CustomComplexCommandButtonProjection>(
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
            popupHandler = CustomComplexCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): CustomComplexCommandButtonProjection {
        return CustomComplexCommandButtonProjection(
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
            popupHandler = CustomComplexCommandMenuPopupHandler,
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
            popupHandler = CustomComplexCommandMenuPopupHandler,
        )
    }
}
