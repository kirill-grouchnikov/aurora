package org.pushingpixels.aurora.theming.decorator.tab

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Density
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.byAlpha
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerTokens
import kotlin.math.min

open class DefaultTabDecorator: AuroraTabDecorator {
    override fun getTabExtraPadding(): PaddingValues {
        return PaddingValues.Zero
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun getTabContentColor(currState: ComponentState,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
        backgroundAppearanceStrategy: BackgroundAppearanceStrategy): Color {

        val skinColors = AuroraSkin.colors
        val decorationAreaType = AuroraSkin.decorationAreaType

        val colorTokens = getContainerTokens(
            colors = skinColors,
            tokensOverlayProvider = tokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Tab,
            componentState = if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            inactiveContainerType = ContainerType.Muted,
            skipFlatCheck = false
        )
        val alpha = if (currState.isDisabled) colorTokens.onContainerDisabledAlpha
            else colorTokens.onContainerEnabledAlpha
        return colorTokens.onContainer.byAlpha(alpha)
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun getDecoratedTabContentColor(
        currState: ComponentState,
        activeStates: Map<ComponentState, Float>,
        parentDecorationAreaType: DecorationAreaType,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
        backgroundAppearanceStrategy: BackgroundAppearanceStrategy
    ): Color {

        val skinColors = AuroraSkin.colors
        val decorationAreaType = AuroraSkin.decorationAreaType

        val parentSurfaceTokens = getContainerTokens(
            colors = skinColors,
            tokensOverlayProvider = tokensOverlayProvider,
            decorationAreaType = parentDecorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Tab,
            componentState = ComponentState.Enabled,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            inactiveContainerType = ContainerType.Neutral,
            skipFlatCheck = false
        )

        var activeStateTotalContribution = if (currState.isActive) 1.0f else 0.0f
        if (activeStates.size > 1) {
            for ((activeState, value) in activeStates) {
                if (activeState != currState) {
                    if (activeState != ComponentState.Enabled) {
                        activeStateTotalContribution += value
                    }
                }
            }
        }
        activeStateTotalContribution = min(1.0f, activeStateTotalContribution)

        val surfaceTokens = getContainerTokens(
            colors = skinColors,
            tokensOverlayProvider = tokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Tab,
            componentState = ComponentState.Enabled,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            inactiveContainerType = ContainerType.Muted,
            skipFlatCheck = false
        )

        val contentColor = parentSurfaceTokens.onContainer.interpolateTowards(
            surfaceTokens.onContainer, 1.0f - activeStateTotalContribution)

        val alpha = if (currState.isDisabled) {
            (1.0f - activeStateTotalContribution) * parentSurfaceTokens.onContainerDisabledAlpha +
                activeStateTotalContribution * surfaceTokens.onContainerDisabledAlpha
        } else {
            (1.0f - activeStateTotalContribution) * parentSurfaceTokens.onContainerEnabledAlpha +
                activeStateTotalContribution * surfaceTokens.onContainerEnabledAlpha
        }

        return contentColor.byAlpha(alpha)
    }

    @Composable
    override fun getTabOutlineColor(currState: ComponentState,
        tokensOverlayProvider: ContainerColorTokensOverlay.Provider?): Color {

        val tokensOverlay = tokensOverlayProvider?.getOverlay(AuroraSkin.colors, AuroraSkin.decorationAreaType)
        val tokens =  tokensOverlay?.neutralContainerTokens
            ?: AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)

        val alpha = if (currState.isDisabled) tokens.containerOutlineDisabledAlpha
            else tokens.containerOutlineEnabledAlpha
        return tokens.markerOnContainer.byAlpha(alpha)
    }

    override fun shouldDrawUnbrokenContentEdge(): Boolean {
        return false
    }

    override fun paintTabSurface(
        drawScope: DrawScope,
        skinColors: AuroraSkinColors,
        decorationAreaType: DecorationAreaType,
        decorationPainter: AuroraDecorationPainter,
        outlineFill: Outline,
        density: Density,
        rootSize: Size,
        offsetFromRoot: Offset,
        size: Size,
        surfaceColorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with (drawScope) {
            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = size.width,
                    bottom = size.height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                if (alpha > 0.0f) {
                    if (skinColors.isRegisteredAsDecorationArea(decorationAreaType)) {
                        // If the current skin has a decoration painter that provides custom visuals
                        // for this decoration area, use it
                        decorationPainter.paintDecorationArea(
                            drawScope = this,
                            decorationAreaType = decorationAreaType,
                            componentSize = size,
                            outline = outlineFill,
                            rootSize = rootSize,
                            offsetFromRoot = offsetFromRoot,
                            colorTokens = surfaceColorTokens
                        )
                    } else {
                        // Otherwise use flat color fill
                        drawOutline(
                            color = surfaceColorTokens.containerSurface,
                            outline = outlineFill
                        )
                    }
                    // Ask the inlay painter to paint its visuals
                    decorationPainter.inlayPainter?.paintInlay(
                        drawScope = this,
                        decorationAreaType = decorationAreaType,
                        rootSize = rootSize,
                        offsetFromRoot = offsetFromRoot,
                        width = size.width,
                        height = size.height,
                        colorTokens = surfaceColorTokens
                    )
                }
            }
        }
    }

    override fun paintTabSurfaceHighlight(
        drawScope: DrawScope,
        outlineSupplier: OutlineSupplier,
        density: Density,
        size: Size,
        surfaceHighlightColorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = size.width,
                    bottom = size.height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                val outlineFill = outlineSupplier.getOutline(
                    layoutDirection = layoutDirection,
                    density = density,
                    size = this.size,
                    insets = 0.0f,
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Surface
                )

                withTransform({
                    clipRect(
                        left = 0.0f,
                        top = 0.0f,
                        right = size.width,
                        bottom = size.height * 0.18f,
                        clipOp = ClipOp.Intersect
                    )
                }) {
                    val color = if (surfaceHighlightColorTokens.isDark)
                        surfaceHighlightColorTokens.containerSurfaceHigh else
                        surfaceHighlightColorTokens.containerSurfaceLow
                    drawOutline(
                        outline = outlineFill,
                        style = Fill,
                        color = color,
                        alpha = alpha
                    )
                }
            }
        }
    }

    override fun paintTabOutline(
        drawScope: DrawScope,
        outlineSupplier: OutlineSupplier,
        density: Density,
        size: Size,
        outlineColor: Color,
        alpha: Float
    ) {
        with (drawScope) {
            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = size.width,
                    bottom = size.height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                drawOutline(
                    outline = outlineSupplier.getOutline(
                        layoutDirection = this.layoutDirection,
                        density = this,
                        size = size,
                        insets = 0.5f,
                        radiusAdjustment = 0.0f,
                        outlineKind = OutlineKind.Outline
                    ),
                    style = Stroke(width = 1.0f),
                    color = outlineColor,
                    alpha = alpha
                )
            }
        }
    }
}