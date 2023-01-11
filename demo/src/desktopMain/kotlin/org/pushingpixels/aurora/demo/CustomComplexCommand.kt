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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.BaseCommandMenuPopupLayoutInfo
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.*
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
)

data class CustomComplexMenuContentModel(
    val entries: List<CustomComplexPopupMenuEntry>
) : BaseCommandMenuContentModel

class CommandButtonLayoutManagerCustomComplex(
    override val layoutDirection: LayoutDirection,
    _density: Density
) : CommandButtonLayoutManager {
    override val density = _density.density
    override val fontScale = _density.fontScale

    override fun getPreferredIconSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): DpSize {
        return DpSize(16.dp, 16.dp)
    }

    override fun getPreferredSize(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): Size {
        val prefIconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val prefIconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        val paddingValues = presentationModel.contentPadding
        val by = presentationModel.verticalGapScaleFactor * paddingValues.verticalPaddings.toPx()
        val bx = presentationModel.horizontalGapScaleFactor * paddingValues.horizontalPaddings.toPx()

        return Size(bx + prefIconWidth, by + prefIconHeight)
    }

    override fun getPreLayoutInfo(
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel
    ): CommandButtonLayoutManager.CommandButtonPreLayoutInfo {
        // Popup only button with no popup (arrow) icon
        return CommandButtonLayoutManager.CommandButtonPreLayoutInfo(
            commandButtonKind = CommandButtonKind.PopupOnly,
            showIcon = true,
            texts = emptyList(),
            extraTexts = emptyList(),
            isTextInActionArea = false,
            separatorOrientation = null,
            showPopupIcon = false
        )
    }

    override fun getLayoutInfo(
        constraints: Constraints,
        command: BaseCommand,
        presentationModel: BaseCommandButtonPresentationModel,
        preLayoutInfo: CommandButtonLayoutManager.CommandButtonPreLayoutInfo
    ): CommandButtonLayoutManager.CommandButtonLayoutInfo {
        val preferredSize = getPreferredSize(command, presentationModel, preLayoutInfo)

        val paddingValues = presentationModel.contentPadding
        val paddingTop = presentationModel.verticalGapScaleFactor * paddingValues.topPadding.toPx()
        val paddingBottom = presentationModel.verticalGapScaleFactor * paddingValues.bottomPadding.toPx()

        val iconWidth = getPreferredIconSize(command, presentationModel).width.toPx()
        val iconHeight = getPreferredIconSize(command, presentationModel).height.toPx()

        val ltr = (layoutDirection == LayoutDirection.Ltr)

        var shiftX = 0.0f
        var finalWidth = preferredSize.width
        var finalHeight = preferredSize.height
        if (constraints.hasFixedWidth && (constraints.maxWidth > 0)) {
            finalWidth = constraints.maxWidth.toFloat()
            if (finalWidth > preferredSize.width) {
                // We have more horizontal space than needed to display the content.
                // Consult the horizontal alignment attribute of the command button to see
                // how we should shift the content horizontally.
                when (presentationModel.horizontalAlignment) {
                    HorizontalAlignment.Leading ->
                        shiftX = 0.0f

                    HorizontalAlignment.Center,
                    HorizontalAlignment.Fill ->
                        // shift everything to be centered horizontally
                        shiftX = (finalWidth - preferredSize.width) / 2

                    HorizontalAlignment.Trailing -> if (ltr) {
                        // shift everything to the right
                        shiftX = finalWidth - preferredSize.width
                    }
                }
            }
        }
        if (finalWidth < presentationModel.minWidth.toPx()) {
            shiftX += (presentationModel.minWidth.toPx() - finalWidth) / 2.0f
            finalWidth = presentationModel.minWidth.toPx()
        }
        if (constraints.hasFixedHeight && (constraints.maxHeight > 0)) {
            finalHeight = constraints.maxHeight.toFloat()
        }

        val iconTop = paddingTop + (finalHeight - iconHeight - paddingTop - paddingBottom) / 2
        val iconRect = if (ltr) {
            val x = paddingValues.startPadding.toPx() + shiftX

            Rect(
                left = x,
                right = x + iconWidth,
                top = iconTop,
                bottom = iconTop + iconHeight
            )
        } else {
            val x = finalWidth - paddingValues.startPadding.toPx() - shiftX

            Rect(
                left = x - iconWidth,
                right = x,
                top = iconTop,
                bottom = iconTop + iconHeight
            )
        }

        val popupClickArea = Rect(
            left = 0.0f,
            right = finalWidth,
            top = 0.0f,
            bottom = finalHeight
        )

        return CommandButtonLayoutManager.CommandButtonLayoutInfo(
            fullSize = Size(finalWidth, finalHeight),
            actionClickArea = Rect.Zero,
            popupClickArea = popupClickArea,
            separatorArea = Rect.Zero,
            iconRect = iconRect,
            textLayoutInfoList = emptyList(),
            extraTextLayoutInfoList = emptyList(),
            popupActionRect = Rect.Zero
        )
    }
}

val CustomComplexPresentationState: CommandButtonPresentationState =
    object : CommandButtonPresentationState("CustomComplex") {
        override fun createLayoutManager(
            layoutDirection: LayoutDirection,
            density: Density,
            textStyle: TextStyle,
            fontFamilyResolver: FontFamily.Resolver
        ): CommandButtonLayoutManager {
            return CommandButtonLayoutManagerCustomComplex(layoutDirection, density)
        }
    }

data class CustomComplexCommandPopupMenuPresentationModel(
    override val menuPresentationState: CommandButtonPresentationState =
        DefaultCommandPopupMenuPresentationState,
    val menuIconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val menuIconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val menuIconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val menuContentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
    val zoomPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            toDismissPopupsOnActivation = false,
            sides = Sides(straightSides = Side.values().toSet()),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
        ),
    val zoomValueContentPadding: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
    val fullScreenPresentationModel: CommandButtonPresentationModel =
        CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Small,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
            sides = Sides(straightSides = Side.values().toSet()),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        )
) : BaseCommandPopupMenuPresentationModel

data class CustomComplexCommandButtonPresentationModel(
    override val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    override val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Center,
    override val iconDimension: DpSize? = null,
    override val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
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
    override val presentationState = CustomComplexPresentationState
    override val forceAllocateSpaceForIcon = false
    override val actionKeyTip = null
    override val autoRepeatAction = false
    override val autoRepeatInitialInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatInitialIntervalMillis
    override val autoRepeatSubsequentInterval =
        CommandButtonInteractionConstants.DefaultAutoRepeatSubsequentIntervalMillis
    override val actionFireTrigger = ActionFireTrigger.OnPressReleased
    override val textClick = TextClick.Action
    override val actionRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val popupRichTooltipPresentationModel = RichTooltipPresentationModel()
    override val horizontalGapScaleFactor = 1.0f
    override val verticalGapScaleFactor = 1.0f
    override val isMenu = false
}

data class CustomComplexPopupContentLayoutInfo(
    override val popupSize: Size,
    val menuButtonPresentationModel: CommandButtonPresentationModel
) : BaseCommandMenuPopupLayoutInfo

object CustomComplexCommandMenuPopupHandler : BaseCommandMenuHandler<
        CustomComplexMenuContentModel, CustomComplexCommandPopupMenuPresentationModel,
        CustomComplexPopupContentLayoutInfo> {
    override fun getPopupContentLayoutInfo(
        menuContentModel: CustomComplexMenuContentModel,
        menuPresentationModel: CustomComplexCommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver
    ): CustomComplexPopupContentLayoutInfo {
        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val menuButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.menuPresentationState,
            iconActiveFilterStrategy = menuPresentationModel.menuIconActiveFilterStrategy,
            iconEnabledFilterStrategy = menuPresentationModel.menuIconEnabledFilterStrategy,
            iconDisabledFilterStrategy = menuPresentationModel.menuIconDisabledFilterStrategy,
            forceAllocateSpaceForIcon = false,
            popupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Leading,
            contentPadding = menuPresentationModel.menuContentPadding,
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

        // Rows such as zoom, edit and header have two areas: title and actions.
        // Treat the title area separately to be aligned with the command-based
        // rows. That is, do not allow the action area of any such row to vertically
        // overlap with the text of any command-based row.
        var maxPrimaryWidth = 0.0f
        var maxActionWidth = 0.0f
        var combinedHeight = 0.0f
        for (entry in menuContentModel.entries) {
            when (entry) {
                is CustomComplexPopupMenuCommand -> {
                    val preferredSize = layoutManager.getPreferredSize(
                        command = entry.command,
                        presentationModel = menuButtonPresentationModel,
                        preLayoutInfo = layoutManager.getPreLayoutInfo(
                            command = entry.command,
                            presentationModel = menuButtonPresentationModel
                        )
                    )
                    maxPrimaryWidth = max(maxPrimaryWidth, preferredSize.width)
                    combinedHeight += preferredSize.height
                }

                is CustomComplexPopupMenuZoom -> {
                    val titleWidth = getLabelPreferredSingleLineWidth(
                        contentModel = LabelContentModel(text = entry.title),
                        presentationModel = LabelPresentationModel(
                            contentPadding = menuPresentationModel.menuContentPadding,
                            textMaxLines = 1
                        ),
                        resolvedTextStyle = textStyle,
                        layoutDirection = layoutDirection,
                        density = density,
                        fontFamilyResolver = fontFamilyResolver
                    )

                    maxPrimaryWidth = max(maxPrimaryWidth, titleWidth)

                    val zoomLayoutManager = menuPresentationModel.zoomPresentationModel.presentationState.createLayoutManager(
                        layoutDirection, density, textStyle, fontFamilyResolver
                    )
                    val zoomOutPreferredSize = zoomLayoutManager.getPreferredSize(
                        command = entry.commandZoomOut,
                        presentationModel = menuPresentationModel.zoomPresentationModel,
                        preLayoutInfo = zoomLayoutManager.getPreLayoutInfo(
                            command = entry.commandZoomOut,
                            presentationModel = menuPresentationModel.zoomPresentationModel
                        )
                    )
                    val zoomLabelWidth = getLabelPreferredSingleLineWidth(
                        contentModel = LabelContentModel(text = "${entry.zoom}%"),
                        presentationModel = LabelPresentationModel(
                            contentPadding = menuPresentationModel.zoomValueContentPadding,
                            textMaxLines = 1
                        ),
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
                        )
                    )

                    val fullScreenLayoutManager = menuPresentationModel.fullScreenPresentationModel.presentationState.createLayoutManager(
                        layoutDirection, density, textStyle, fontFamilyResolver
                    )
                    val fullScreenPreferredSize = fullScreenLayoutManager.getPreferredSize(
                        command = entry.commandFullScreen,
                        presentationModel = menuPresentationModel.fullScreenPresentationModel,
                        preLayoutInfo = fullScreenLayoutManager.getPreLayoutInfo(
                            command = entry.commandFullScreen,
                            presentationModel = menuPresentationModel.fullScreenPresentationModel
                        )
                    )

                    maxActionWidth = max(
                        maxActionWidth,
                        zoomOutPreferredSize.width + zoomLabelWidth + zoomInPreferredSize.width + fullScreenPreferredSize.width
                    )

                    combinedHeight += fullScreenPreferredSize.height
                }

                else -> {}
            }

        }

        return CustomComplexPopupContentLayoutInfo(
            popupSize = Size(
                width = maxPrimaryWidth + 32 * density.density + maxActionWidth,
                height = combinedHeight
            ),
            menuButtonPresentationModel = menuButtonPresentationModel
        )
    }

    @Composable
    override fun generatePopupContent(
        menuContentModel: CustomComplexMenuContentModel,
        menuPresentationModel: CustomComplexCommandPopupMenuPresentationModel,
        toUseBackgroundStriping: Boolean,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>,
        popupContentLayoutInfo: CustomComplexPopupContentLayoutInfo
    ) {
        val menuButtonPresentationModel = popupContentLayoutInfo.menuButtonPresentationModel

        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        Column(
            modifier = Modifier.fillMaxSize().background(color = backgroundColorScheme.backgroundFillColor)
                .padding(all = 1.0.dp)
        ) {
            for (entry in menuContentModel.entries) {
                when (entry) {
                    is CustomComplexPopupMenuCommand -> {
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
                            modifier = Modifier.fillMaxWidth(),
                            actionInteractionSource = remember { MutableInteractionSource() },
                            popupInteractionSource = remember { MutableInteractionSource() }
                        )
                    }

                    is CustomComplexPopupMenuZoom -> {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            LabelProjection(
                                contentModel = LabelContentModel(text = entry.title),
                                presentationModel = LabelPresentationModel(
                                    textMaxLines = 1,
                                    contentPadding = menuPresentationModel.menuContentPadding
                                )
                            ).project()

                            Spacer(modifier = Modifier.weight(1.0f))

                            CommandButtonProjection(
                                contentModel = entry.commandZoomOut,
                                presentationModel = menuPresentationModel.zoomPresentationModel
                            ).project()
                            LabelProjection(
                                contentModel = LabelContentModel(text = "${entry.zoom}%"),
                                presentationModel = LabelPresentationModel(
                                    textMaxLines = 1,
                                    contentPadding = menuPresentationModel.zoomValueContentPadding
                                )
                            ).project()
                            CommandButtonProjection(
                                contentModel = entry.commandZoomIn,
                                presentationModel = menuPresentationModel.zoomPresentationModel
                            ).project()

                            CommandButtonProjection(
                                contentModel = entry.commandFullScreen,
                                presentationModel = menuPresentationModel.fullScreenPresentationModel
                            ).project()
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

class CustomComplexCommandButtonProjection(
    contentModel: CustomComplexCommand,
    presentationModel: CustomComplexCommandButtonPresentationModel = CustomComplexCommandButtonPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<CustomComplexCommand, CustomComplexCommandButtonPresentationModel>(
    contentModel, presentationModel, overlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = CustomComplexCommandMenuPopupHandler,
        )
    }
}
