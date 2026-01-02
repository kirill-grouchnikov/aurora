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
package org.pushingpixels.aurora.component.utils.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.component.AuroraVerticallyScrollableBox
import org.pushingpixels.aurora.component.layout.CommandButtonLayoutManager
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.popup.awtColor
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.component.ribbon.RibbonApplicationMenuCommandPopupMenuPresentationModel
import org.pushingpixels.aurora.component.ribbon.RibbonApplicationMenuContentModel
import org.pushingpixels.aurora.component.ribbon.impl.LocalRibbonKeyTipChainRoot
import org.pushingpixels.aurora.component.ribbon.impl.LocalRibbonKeyTipChainRootKeyTip
import org.pushingpixels.aurora.component.utils.TitleLabel
import org.pushingpixels.aurora.component.utils.appmenu.CommandButtonLayoutManagerAppMenuLevel2
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import java.awt.*
import java.awt.geom.Rectangle2D
import javax.swing.JPopupMenu
import javax.swing.UIManager
import javax.swing.border.Border
import kotlin.math.ceil
import kotlin.math.max

internal data class RibbonApplicationMenuLevel1ContentLayoutInfo(
    val fullSize: Size,
    val itemButtonPresentationModel: CommandButtonPresentationModel,
)

internal data class RibbonApplicationMenuFooterContentLayoutInfo(
    val fullHeight: Float,
    val footerButtonPresentationModel: CommandButtonPresentationModel,
)

private class RibbonApplicationMenuLevel1ButtonProjection(
    contentModel: Command,
    presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null,
    val popupHandler: BaseCommandMenuHandler<CommandMenuContentModel, CommandPopupMenuPresentationModel>
) : BaseCommandButtonProjection<Command, CommandButtonPresentationModel, RibbonApplicationMenuLevel1ButtonProjection>(
    contentModel, presentationModel, secondaryOverlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            popupHandler = popupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): RibbonApplicationMenuLevel1ButtonProjection {
        return RibbonApplicationMenuLevel1ButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = this.secondaryOverlays,
            popupHandler = this.popupHandler
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
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            popupHandler = popupHandler,
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        error("Reprojecting a ribbon application menu button is not supported")
    }
}

internal class RibbonApplicationMenuPopupHandler(
    val secondaryStates: Map<Command, CommandButtonPresentationState>?
) : BaseCommandMenuHandler<
        RibbonApplicationMenuContentModel, RibbonApplicationMenuCommandPopupMenuPresentationModel> {
    private fun getLevel1ContentLayoutInfo(
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): RibbonApplicationMenuLevel1ContentLayoutInfo {

        // If at least one secondary command in this popup menu has icon factory
        // we force all command buttons to allocate space for the icon (for overall
        // alignment of content across the entire popup menu)
        var atLeastOneButtonHasIcon = false
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                if (secondaryCommand.icon != null) {
                    atLeastOneButtonHasIcon = true
                }
                if (secondaryCommand.isActionToggle) {
                    atLeastOneButtonHasIcon = true
                }
            }
        }

        // Command presentation for menu content, taking some values from
        // the popup menu presentation model configured on the top-level presentation model
        val itemButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = menuPresentationModel.itemPresentationState,
            iconActiveFilterStrategy = IconFilterStrategy.Original,
            iconEnabledFilterStrategy = IconFilterStrategy.Original,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
            forceAllocateSpaceForIcon = atLeastOneButtonHasIcon,
            popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
            popupFireTrigger = menuPresentationModel.itemPopupFireTrigger,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            horizontalAlignment = HorizontalAlignment.Fill,
            contentPadding = menuPresentationModel.level1ItemContentPadding,
            selectedStateHighlight = menuPresentationModel.itemSelectedStateHighlight,
            sides = menuPresentationModel.itemSides
        )

        val layoutManager: CommandButtonLayoutManager =
            itemButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var maxWidth = 0.0f
        var combinedHeight = 0.0f
        for (commandGroup in menuContentModel.groups) {
            for (secondaryCommand in commandGroup.commands) {
                val preferredSize = layoutManager.getPreferredSize(
                    command = secondaryCommand,
                    presentationModel = itemButtonPresentationModel,
                    preLayoutInfo = layoutManager.getPreLayoutInfo(
                        command = secondaryCommand,
                        presentationModel = itemButtonPresentationModel
                    ),
                    buttonShaper = buttonShaper
                )
                maxWidth = max(maxWidth, preferredSize.width)
                combinedHeight += preferredSize.height
            }
        }

        return RibbonApplicationMenuLevel1ContentLayoutInfo(
            fullSize = Size(
                width = maxWidth,
                height = combinedHeight
            ),
            itemButtonPresentationModel = itemButtonPresentationModel
        )
    }

    private fun getFooterContentLayoutInfo(
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper
    ): RibbonApplicationMenuFooterContentLayoutInfo {
        // Footer command presentation for menu content
        val footerButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium,
            iconActiveFilterStrategy = IconFilterStrategy.Original,
            iconEnabledFilterStrategy = IconFilterStrategy.Original,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            popupFireTrigger = PopupFireTrigger.OnRollover,
            selectedStateHighlight = SelectedStateHighlight.FullSize
        )

        if (menuContentModel.footerCommands.commands.isEmpty()) {
            return RibbonApplicationMenuFooterContentLayoutInfo(
                fullHeight = 0.0f,
                footerButtonPresentationModel = footerButtonPresentationModel
            )
        }

        val layoutManager: CommandButtonLayoutManager =
            footerButtonPresentationModel.presentationState.createLayoutManager(
                layoutDirection = layoutDirection,
                density = density,
                textStyle = textStyle,
                fontFamilyResolver = fontFamilyResolver
            )

        var maxHeight = 0.0f
        for (footerCommand in menuContentModel.footerCommands.commands) {
            val preferredSize = layoutManager.getPreferredSize(
                command = footerCommand,
                presentationModel = footerButtonPresentationModel,
                preLayoutInfo = layoutManager.getPreLayoutInfo(
                    command = footerCommand,
                    presentationModel = footerButtonPresentationModel
                ),
                buttonShaper = buttonShaper
            )
            maxHeight = max(maxHeight, preferredSize.height)
        }

        val footerTopPadding = menuPresentationModel.footerContentPadding.calculateTopPadding()
        val footerBottomPadding = menuPresentationModel.footerContentPadding.calculateBottomPadding()
        return RibbonApplicationMenuFooterContentLayoutInfo(
            fullHeight = maxHeight + (footerTopPadding + footerBottomPadding).value * density.density,
            footerButtonPresentationModel = footerButtonPresentationModel
        )
    }

    @Composable
    private fun generateLevel1Content(
        modifier: Modifier,
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        onLevel1ActionRollover: (Command) -> Unit,
        level1ContentLayoutInfo: RibbonApplicationMenuLevel1ContentLayoutInfo
    ) {
        val itemButtonPresentationModel = level1ContentLayoutInfo.itemButtonPresentationModel

        Column(modifier = modifier.padding(all = 1.0.dp)) {
            for (commandGroup in menuContentModel.groups) {
                for (secondaryCommand in commandGroup.commands) {
                    // Check if we have a presentation overlay for this level 1 command
                    val hasOverlay = overlays.containsKey(secondaryCommand)
                    val currSecondaryPresentationModel = if (hasOverlay)
                        itemButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                    else itemButtonPresentationModel

                    val level2PopupHandler =
                        object : BaseCommandMenuHandler<CommandMenuContentModel, CommandPopupMenuPresentationModel> {
                            override fun showPopupContent(
                                popupOriginator: Component,
                                layoutDirection: LayoutDirection,
                                density: Density,
                                textStyle: TextStyle,
                                fontFamilyResolver: FontFamily.Resolver,
                                skinColors: AuroraSkinColors,
                                skinPainters: AuroraPainters,
                                buttonShaper: AuroraButtonShaper,
                                decorationAreaType: DecorationAreaType,
                                compositionLocalContext: CompositionLocalContext,
                                anchorBoundsInWindow: Rect,
                                popupTriggerAreaInWindow: Rect,
                                contentModel: State<CommandMenuContentModel?>,
                                presentationModel: CommandPopupMenuPresentationModel,
                                displayPrototypeCommand: BaseCommand?,
                                toDismissPopupsOnActivation: Boolean,
                                popupPlacementStrategy: PopupPlacementStrategy,
                                popupAnchorBoundsProvider: (() -> Rect)?,
                                popupOriginatorKeyTip: String?,
                                overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
                                popupKind: AuroraPopupManager.PopupKind
                            ): Window? {
                                onLevel1ActionRollover.invoke(secondaryCommand)
                                return null
                            }
                        }

                    // Project a command button for each level 1 command, passing the same
                    // overlays into it.
                    val actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
                    RibbonApplicationMenuLevel1ButtonProjection(
                        contentModel = secondaryCommand,
                        presentationModel = currSecondaryPresentationModel,
                        secondaryOverlays = overlays,
                        popupHandler = level2PopupHandler
                    ).project(
                        modifier = Modifier.fillMaxWidth(),
                        actionInteractionSource = actionInteractionSource,
                        popupInteractionSource = remember { MutableInteractionSource() }
                    )
                    LaunchedEffect(actionInteractionSource) {
                        actionInteractionSource.interactions.collect { interaction ->
                            when (interaction) {
                                is HoverInteraction.Enter -> onLevel1ActionRollover.invoke(secondaryCommand)
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    private fun generateLevel2Content(
        modifier: Modifier,
        dpSize: DpSize,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        buttonShaper: AuroraButtonShaper,
        level1Command: Command?,
        itemPresentationState: CommandButtonPresentationState,
        menuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>
    ) {
        val itemButtonPresentationModel = CommandButtonPresentationModel(
            presentationState = itemPresentationState,
            popupPlacementStrategy = PopupPlacementStrategy.Endward.VAlignTop,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
            sides = menuPresentationModel.itemSides,
            horizontalAlignment = HorizontalAlignment.Fill,
            contentPadding = menuPresentationModel.level2ItemContentPadding,
            popupFireTrigger = PopupFireTrigger.OnRollover,
            selectedStateHighlight = SelectedStateHighlight.FullSize
        )
        CompositionLocalProvider(
            LocalRibbonKeyTipChainRoot provides level1Command?.secondaryContentModel,
            LocalRibbonKeyTipChainRootKeyTip provides overlays[level1Command]?.popupKeyTip,
        ) {
            Column(modifier = modifier.padding(all = 1.0.dp)) {
                if (level1Command?.secondaryContentModel != null) {
                    // Determine the height of the tallest button
                    var maxButtonHeight = 0.0f
                    val regularButtonLayoutManager = itemPresentationState.createLayoutManager(
                        layoutDirection = layoutDirection,
                        density = density,
                        textStyle = textStyle,
                        fontFamilyResolver = fontFamilyResolver
                    )
                    for (commandGroup in level1Command.secondaryContentModel.groups) {
                        for (secondaryCommand in commandGroup.commands) {
                            val preferredSize = regularButtonLayoutManager.getPreferredSize(
                                command = secondaryCommand,
                                presentationModel = itemButtonPresentationModel,
                                preLayoutInfo = regularButtonLayoutManager.getPreLayoutInfo(
                                    command = secondaryCommand,
                                    presentationModel = itemButtonPresentationModel
                                ),
                                buttonShaper = buttonShaper
                            )
                            maxButtonHeight = max(maxButtonHeight, preferredSize.height)
                            if (regularButtonLayoutManager is CommandButtonLayoutManagerAppMenuLevel2) {
                                maxButtonHeight = max(
                                    maxButtonHeight,
                                    regularButtonLayoutManager.getPreferredHeight(
                                        fixedWidth = dpSize.width,
                                        command = secondaryCommand,
                                        presentationModel = itemButtonPresentationModel
                                    )
                                )
                            }
                        }
                    }

                    AuroraVerticallyScrollableBox(
                        modifier = Modifier.fillMaxWidth(),
                        width = dpSize.width,
                        contentHeight = {
                            var combinedHeight = 0.0f
                            for (commandGroup in level1Command.secondaryContentModel.groups) {
                                if (commandGroup.title != null) {
                                    combinedHeight += getLabelPreferredHeight(
                                        contentModel = LabelContentModel(text = commandGroup.title),
                                        presentationModel = LabelPresentationModel(
                                            horizontalAlignment = HorizontalAlignment.Leading
                                        ),
                                        resolvedTextStyle = textStyle,
                                        layoutDirection = layoutDirection,
                                        density = density,
                                        fontFamilyResolver = fontFamilyResolver,
                                        availableWidth = dpSize.width.value * density.density
                                    )
                                }

                                combinedHeight += (commandGroup.commands.size * maxButtonHeight)
                            }
                            combinedHeight.toInt()
                        },
                        verticalScrollState = rememberScrollState(0),
                        scrollAmount = 12.dp,
                        content = {
                            for (commandGroup in level1Command.secondaryContentModel.groups) {
                                if (commandGroup.title != null) {
                                    TitleLabel(
                                        modifier = Modifier.fillMaxWidth(),
                                        title = commandGroup.title,
                                        presentationModel = LabelPresentationModel(
                                            horizontalAlignment = HorizontalAlignment.Leading
                                        )
                                    )
                                }

                                for (secondaryCommand in commandGroup.commands) {
                                    // Check if we have a presentation overlay for this level 2 command
                                    val hasOverlay = overlays.containsKey(secondaryCommand)
                                    val currSecondaryPresentationModel = if (hasOverlay)
                                        itemButtonPresentationModel.overlayWith(overlays[secondaryCommand]!!)
                                    else itemButtonPresentationModel
                                    // Project a command button for each level 2 command, passing the same
                                    // overlays into it.
                                    CommandButtonProjection(
                                        contentModel = secondaryCommand,
                                        presentationModel = currSecondaryPresentationModel,
                                        secondaryOverlays = overlays
                                    ).project(
                                        modifier = Modifier.fillMaxWidth()
                                            .height(height = (maxButtonHeight / density.density).dp),
                                        actionInteractionSource = remember { MutableInteractionSource() },
                                        popupInteractionSource = remember { MutableInteractionSource() }
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun generateFooterContent(
        menuContentModel: RibbonApplicationMenuContentModel,
        menuPresentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        footerContentLayoutInfo: RibbonApplicationMenuFooterContentLayoutInfo
    ) {
        val footerButtonPresentationModel = footerContentLayoutInfo.footerButtonPresentationModel
        val neutralColorTokens = AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
        Row(
            modifier = Modifier.fillMaxSize()
                .background(
                    color = if (neutralColorTokens.isDark) {
                        neutralColorTokens.containerSurfaceHighest
                    } else {
                        neutralColorTokens.containerSurfaceLowest
                    }
                )
                .padding(menuPresentationModel.footerContentPadding),
            horizontalArrangement = Arrangement.End
        ) {
            for (footerCommand in menuContentModel.footerCommands.commands) {
                // Check if we have a presentation overlay for this footer command
                val hasOverlay = overlays.containsKey(footerCommand)
                val currFooterPresentationModel = if (hasOverlay)
                    footerButtonPresentationModel.overlayWith(overlays[footerCommand]!!)
                else footerButtonPresentationModel
                // Project a command button for each footer command, passing the same
                // overlays into it.
                CommandButtonProjection(
                    contentModel = footerCommand,
                    presentationModel = currFooterPresentationModel,
                    secondaryOverlays = overlays
                ).project(
                    actionInteractionSource = remember { MutableInteractionSource() },
                    popupInteractionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }

    @OptIn(AuroraInternalApi::class)
    override fun showPopupContent(
        popupOriginator: Component,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        skinColors: AuroraSkinColors,
        skinPainters: AuroraPainters,
        buttonShaper: AuroraButtonShaper,
        decorationAreaType: DecorationAreaType,
        compositionLocalContext: CompositionLocalContext,
        anchorBoundsInWindow: Rect,
        popupTriggerAreaInWindow: Rect,
        contentModel: State<RibbonApplicationMenuContentModel?>,
        presentationModel: RibbonApplicationMenuCommandPopupMenuPresentationModel,
        displayPrototypeCommand: BaseCommand?,
        toDismissPopupsOnActivation: Boolean,
        popupPlacementStrategy: PopupPlacementStrategy,
        popupAnchorBoundsProvider: (() -> Rect)?,
        popupOriginatorKeyTip: String?,
        overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>,
        popupKind: AuroraPopupManager.PopupKind
    ): Window? {
        val level1ContentLayoutInfo = getLevel1ContentLayoutInfo(
            menuContentModel = contentModel.value!!,
            menuPresentationModel = presentationModel,
            displayPrototypeCommand = displayPrototypeCommand,
            layoutDirection = layoutDirection,
            density = density,
            textStyle = textStyle,
            fontFamilyResolver = fontFamilyResolver,
            buttonShaper = buttonShaper
        )
        val footerContentLayoutInfo = getFooterContentLayoutInfo(
            menuContentModel = contentModel.value!!,
            menuPresentationModel = presentationModel,
            layoutDirection = layoutDirection,
            density = density,
            textStyle = textStyle,
            fontFamilyResolver = fontFamilyResolver,
            buttonShaper = buttonShaper
        )

        // From this point, all coordinates are in Swing display units - which are density independent.
        // The popup width and height is converted from pixels into dp (density-independent units),
        // and then passed those as is (the numeric value) to Swing / AWT

        // Full size of the popup accounts for extra pixel on each side for the popup border,
        // as well as the extra paddings for the main panel border and the vertical separator between
        // level 1 and level 2 panels
        val fullPopupWidth = ceil(level1ContentLayoutInfo.fullSize.width / density.density).toInt() +
                presentationModel.level2PanelWidth.value.toInt() + 4
        val fullPopupHeight = ceil(
            (level1ContentLayoutInfo.fullSize.height + footerContentLayoutInfo.fullHeight)
                    / density.density
        ).toInt() + 3

        val fullPopupSize = IntSize(width = fullPopupWidth, height = fullPopupHeight)

        val popupRect = BaseCommandMenuHandler.getPopupRectangleOnScreen(
            popupOriginator = popupOriginator,
            layoutDirection = layoutDirection,
            anchorBoundsInWindow = anchorBoundsInWindow,
            popupPlacementStrategy = popupPlacementStrategy,
            fullPopupSize = fullPopupSize
        )

        val neutralColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType)
        val popupContent = ComposePanel()
        val fillColor = neutralColorTokens.containerSurface
        val awtFillColor = fillColor.awtColor
        popupContent.background = awtFillColor

        val popupBorderColor = neutralColorTokens.containerOutline
        val awtBorderColor = popupBorderColor.awtColor
        val borderThickness = 1.0f / density.density

        popupContent.border = object : Border {
            override fun paintBorder(
                c: Component,
                g: Graphics,
                x: Int,
                y: Int,
                width: Int,
                height: Int
            ) {
                val g2d = g.create() as Graphics2D
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                g2d.setRenderingHint(
                    RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_PURE
                )

                g2d.color = awtFillColor
                g2d.fill(Rectangle(0, 0, width, height))

                val thickness = 0.5f
                g2d.stroke = BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND)
                g2d.color = awtBorderColor
                g2d.draw(
                    Rectangle2D.Float(
                        borderThickness / 2.0f, borderThickness / 2.0f,
                        width - borderThickness, height - borderThickness
                    )
                )
                g2d.dispose()
            }

            override fun getBorderInsets(c: Component?): Insets {
                return Insets(1, 1, 1, 1)
            }

            override fun isBorderOpaque(): Boolean {
                return false
            }
        }
        popupContent.preferredSize = Dimension(popupRect.width, popupRect.height)

        val popupDpSize = DpSize(
            width = (popupRect.width / density.density).dp,
            height = (popupRect.height / density.density).dp
        )

        // This line is needed to ensure that each popup is displayed in its own heavyweight window
        JPopupMenu.setDefaultLightWeightPopupEnabled(false)

        // ComposePanel has a private-access ComposeLayer (which is internal). That one has a SkiaLayer
        // which extends JPanel. Since there is no direct access to that panel, configure its background
        // color indirectly through setting this entry in the Swing's UIManager table.
        UIManager.put("Panel.background", awtFillColor)

        val popupMenu = AuroraSwingPopupMenu(toDismissPopupsOnActivation)
        popupMenu.background = awtFillColor
        popupContent.setContent {
            var activeLevel1Command by remember { mutableStateOf<Command?>(null) }

            // Get the current composition context
            CompositionLocalProvider(compositionLocalContext) {
                // And add the composition locals for the new popup
                CompositionLocalProvider(
                    LocalPopupMenu provides popupMenu,
                    LocalWindowSize provides popupDpSize,
                    LocalTopWindowSize provides LocalTopWindowSize.current,
                    LocalSkinColors provides LocalSkinColors.current,
                    LocalRibbonKeyTipChainRoot provides contentModel.value,
                    LocalRibbonKeyTipChainRootKeyTip provides popupOriginatorKeyTip,
                ) {
                    val level1PanelWidthDp = (level1ContentLayoutInfo.fullSize.width / density.density).dp
                    val level2PanelWidthDp = presentationModel.level2PanelWidth
                    val panelHeightDp = (level1ContentLayoutInfo.fullSize.height / density.density).dp
                    Column(modifier = Modifier.auroraBackground()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(1.0f)
                                .height(height = panelHeightDp + 2.dp)
                                .auroraBorder()
                                .padding(all = 1.dp)
                        ) {
                            generateLevel1Content(
                                modifier = Modifier.width(width = level1PanelWidthDp),
                                menuContentModel = contentModel.value!!,
                                menuPresentationModel = presentationModel,
                                overlays = overlays,
                                onLevel1ActionRollover = {
                                    activeLevel1Command = it
                                },
                                level1ContentLayoutInfo = level1ContentLayoutInfo
                            )
                            val level2PresentationState =
                                if ((secondaryStates != null) && (activeLevel1Command != null))
                                    secondaryStates.getOrDefault(
                                        activeLevel1Command!!,
                                        CommandButtonPresentationState.Medium
                                    )
                                else CommandButtonPresentationState.Medium
                            VerticalSeparatorProjection(
                                presentationModel = SeparatorPresentationModel(
                                    startGradientAmount = 0.dp,
                                    endGradientAmount = 0.dp
                                )
                            ).project(modifier = Modifier.size(width = 1.dp, height = panelHeightDp))
                            generateLevel2Content(
                                modifier = Modifier.size(width = level2PanelWidthDp, height = panelHeightDp),
                                dpSize = DpSize(width = level2PanelWidthDp, height = panelHeightDp),
                                layoutDirection = layoutDirection,
                                density = density,
                                textStyle = textStyle,
                                fontFamilyResolver = fontFamilyResolver,
                                buttonShaper = buttonShaper,
                                level1Command = activeLevel1Command,
                                itemPresentationState = level2PresentationState,
                                menuPresentationModel = presentationModel,
                                overlays = overlays
                            )
                        }
                        generateFooterContent(
                            menuContentModel = contentModel.value!!,
                            menuPresentationModel = presentationModel,
                            overlays = overlays,
                            footerContentLayoutInfo = footerContentLayoutInfo
                        )
                    }
                }
            }
        }
        popupMenu.add(popupContent)

        // Hide the popups that "start" from our popup originator
        AuroraPopupManager.hidePopups(originator = popupOriginator)
        // And display our new popup content
        return AuroraPopupManager.showPopup(
            originator = popupOriginator,
            popupTriggerAreaInOriginatorWindow = popupTriggerAreaInWindow,
            popup = popupMenu,
            popupContent = popupContent,
            popupRectOnScreen = popupRect,
            popupKind = popupKind,
            onActivatePopup = contentModel.value?.onActivatePopup,
            onDeactivatePopup = contentModel.value?.onDeactivatePopup
        )
    }
}
