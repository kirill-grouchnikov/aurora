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
package org.pushingpixels.aurora.theming

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.Data
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.decorator.window.DefaultWindowDecorator
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.painter.outline.FlatOutlinePainter
import org.pushingpixels.aurora.theming.painter.surface.ShaderWrapperSurfacePainter
import org.pushingpixels.aurora.theming.palette.DefaultPaletteColorResolver
import org.pushingpixels.aurora.theming.palette.TokenPaletteColorResolverOverlay
import org.pushingpixels.aurora.theming.palette.getContainerTokens
import org.pushingpixels.aurora.theming.palette.overlayWith
import org.pushingpixels.aurora.theming.shaper.AuroraComponentShaper
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier
import org.pushingpixels.ephemeral.chroma.blend.Blend
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min

private val GridSize = 8.dp
private const val GridAlpha = 0.2f

private fun blueprintSkinColors(): AuroraSkinColors {
    val result = AuroraSkinColors()

    val primaryBlue = Hct.fromInt(0xFF174792u.toInt())
    val darkPrimaryBlue = Hct.fromInt(0xFF103266u.toInt())

    val activeResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.complementaryContainerOutline },
            containerOutlineVariant = { Blend.harmonize(
                it.complementaryContainerOutline, it.containerSurface, 0.2
            ) },
            complementaryContainerOutline = { it.containerOutline },
            containerSurfaceEnabledAlpha = { 0.6f },
            containerSurfaceDisabledAlpha = { 0.35f },
            containerOutlineDisabledAlpha = { 0.35f },
            onContainerDisabledAlpha = { 0.85f },
            onContainer = { primaryBlue.toInt() },
            onContainerVariant = { primaryBlue.toInt() and 0xC0FFFFFFu.toInt() },
            inverseContainerSurface = { primaryBlue.toInt() },
            inverseContainerOutline = { darkPrimaryBlue.toInt() },
        )
    )
    val mutedResolver = DefaultPaletteColorResolver.overlayWith(
        TokenPaletteColorResolverOverlay(
            containerOutline = { it.complementaryContainerOutline },
            containerOutlineVariant = { Blend.harmonize(
                it.complementaryContainerOutline, it.containerSurface, 0.2
            ) },
            complementaryContainerOutline = { it.containerOutline },
            containerSurfaceEnabledAlpha = { 0.0f },
            containerSurfaceDisabledAlpha = { 0.0f },
            containerOutlineDisabledAlpha = { 0.55f },
            onContainerDisabledAlpha = { 0.65f },
        )
    )

    val blueprintDefaultBundle = ContainerColorTokensBundle(
        activeContainerTokens = getContainerTokens(
            seed = Hct.fromInt(0xFFFFFFFFu.toInt()),
            containerConfiguration = ContainerConfiguration.defaultLight(),
            colorResolver = activeResolver
        ),
        mutedContainerTokens = getContainerTokens(
            seed = primaryBlue,
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = mutedResolver
        ),
        neutralContainerTokens = getContainerTokens(
            seed = primaryBlue,
            containerConfiguration = ContainerConfiguration.defaultDark(),
            colorResolver = mutedResolver
        ),
        isSystemDark = true
    )

    val blueprintHighlightContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFFFFFFu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight(),
        colorResolver = activeResolver
    )

    blueprintDefaultBundle.registerActiveContainerTokens(
        colorTokens = blueprintHighlightContainerTokens,
        associationKind = ContainerColorTokensAssociationKind.Highlight,
        activeStates = ComponentState.activeStates
    )

    result.registerDecorationAreaTokensBundle(blueprintDefaultBundle,
        DecorationAreaType.None, DecorationAreaType.ControlPane,
        DecorationAreaType.Footer, DecorationAreaType.Toolbar,
        DecorationAreaType.Header)

    return result
}

private class BlueprintDecorationPainter: AuroraDecorationPainter() {
    override val displayName: String
        get() = "Blueprint"

    override fun paintDecorationArea(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        componentSize: Size,
        outline: Outline,
        rootSize: Size,
        offsetFromRoot: Offset,
        colorTokens: ContainerColorTokens
    ) {
        with(drawScope) {
            drawOutline(
                outline = outline,
                style = Fill,
                color = colorTokens.containerSurface
            )
        }
    }
}

private class BlueprintDecorationInlayPainter: AuroraDecorationPainter.InlayPainter {
    override val displayName: String
        get() = "Blueprint"

    override fun paintInlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        rootSize: Size,
        offsetFromRoot: Offset,
        width: Float,
        height: Float,
        colorTokens: ContainerColorTokens
    ) {
        with (drawScope) {
            val gridSize = GridSize.toPx()

            val offsetX = -offsetFromRoot.x
            val offsetY = -offsetFromRoot.y

            val startGridX = offsetX % gridSize
            val endGridX = startGridX + width + gridSize
            val startGridY = offsetY % gridSize
            val endGridY = startGridY + height + gridSize

            var gridX = startGridX
            while (gridX <= endGridX) {
                drawLine(
                    color = colorTokens.containerOutlineVariant,
                    start = Offset(gridX, 0.0f),
                    end = Offset(gridX, height),
                    strokeWidth = Stroke.HairlineWidth,
                    alpha = GridAlpha
                )

                gridX += gridSize
            }

            var gridY = startGridY
            while (gridY <= endGridY) {
                drawLine(
                    color = colorTokens.containerOutlineVariant,
                    start = Offset(0.0f, gridY),
                    end = Offset(width, gridY),
                    strokeWidth = Stroke.HairlineWidth,
                    alpha = GridAlpha
                )

                gridY += gridSize
            }
        }
    }
}

private class BlueprintDecorationOverlayPainter(
    private val colorTokensQuery: (ContainerColorTokens) -> Color,
    private val strokeWidth: Float,
    private val alignment: Alignment
): AuroraDecorationPainter.OverlayPainter {
    enum class Alignment {
        Top, Bottom
    }

    override val displayName: String
        get() = "Blueprint"

    override fun paintOverlay(
        drawScope: DrawScope,
        decorationAreaType: DecorationAreaType,
        width: Float,
        height: Float,
        colorTokens: ContainerColorTokens
    ) {
        with(drawScope) {
            val pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(5.dp.toPx(), 4.dp.toPx()),
                phase = 0f
            )
            when (alignment) {
                Alignment.Top ->
                    drawLine(
                        color = colorTokensQuery.invoke(colorTokens),
                        start = Offset(0.0f, strokeWidth),
                        end = Offset(width, strokeWidth),
                        strokeWidth = strokeWidth,
                        pathEffect = pathEffect,
                    )

                Alignment.Bottom ->
                    drawLine(
                        color = colorTokensQuery.invoke(colorTokens),
                        start = Offset(0.0f, height - strokeWidth),
                        end = Offset(width, height - strokeWidth),
                        strokeWidth = strokeWidth,
                        pathEffect = pathEffect,
                    )
            }
        }
    }
}

private class RectangularComponentShaper: AuroraComponentShaper {
    override val displayName: String
        get() = "Rectangular"

    private fun getRectangularOutline(
        layoutDirection: LayoutDirection,
        width: Float, height: Float,
        sides: Sides? = null,
        insets: Float = 0.0f, outlineKind: OutlineKind = OutlineKind.Outline
    ): Outline {
        val leftSide = if (layoutDirection == LayoutDirection.Ltr) Side.Leading else Side.Trailing
        val rightSide = if (layoutDirection == LayoutDirection.Ltr) Side.Trailing else Side.Leading

        val openSides = sides?.openSides
        val hasOpenSides = !openSides.isNullOrEmpty()

        if (!hasOpenSides) {
            // No open sides? A simple rectangle
            return Outline.Rectangle(
                rect = Rect(
                    left = insets, top = insets,
                    right = width - insets, bottom = height - insets
                )
            )
        }
        // Open sides are handled differently for fill and border outline kinds. For fill we need
        // to return a fully continuous and closed path so that it can be filled. For border, we
        // respect the open sides, using moveTo instead of lineTo for the relevant side(s).
        val isTopOpen = openSides.contains(Side.Top) && (outlineKind == OutlineKind.Outline)
        val isBottomOpen = openSides.contains(Side.Bottom) && (outlineKind == OutlineKind.Outline)
        val isLeftOpen = openSides.contains(leftSide) && (outlineKind == OutlineKind.Outline)
        val isRightOpen = openSides.contains(rightSide) && (outlineKind == OutlineKind.Outline)

        val path = Path()
        // Start in top left
        path.moveTo(x = insets, y = insets)
        // To top right
        if (isTopOpen) {
            path.moveTo(x = width - insets, y = insets)
        } else {
            path.lineTo(x = width - insets, y = insets)
        }
        // To bottom right
        if (isRightOpen) {
            path.moveTo(x = width - insets, y = height - insets)
        } else {
            path.lineTo(x = width - insets, y = height - insets)
        }

        // To bottom left
        if (isBottomOpen) {
            path.moveTo(x = insets, y = height - insets)
        } else {
            path.lineTo(x = insets, y = height - insets)
        }
        // To top left
        if (isLeftOpen) {
            path.moveTo(x = insets, y = insets)
        } else {
            path.lineTo(x = insets, y = insets)
        }

        return Outline.Generic(path = path)
    }

    override fun getButtonOutlineSupplier(buttonSides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                return getRectangularOutline(layoutDirection, size.width, size.height, buttonSides, insets, outlineKind)
            }
        }
    }

    override fun getButtonExtraContentPadding(
        uiPreferredSize: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): PaddingValues {
        return PaddingValues.Zero
    }

    override fun getBaselineOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getBaselineOutlineSupplier(sides: Sides): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getCheckBoxOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getComboBoxOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getLinearProgressBarProgressOutlineSupplier(sides: Sides): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getLinearProgressBarTrackOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getRadioButtonOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    override fun getScrollBarThumbOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getSliderThumbOutlineSupplier(): OutlineSupplier {
        return DiamondOutlineSuppler
    }

    override fun getSliderTrackOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    override fun getSwitchThumbOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    override fun getSwitchTrackOutlineSupplier(): OutlineSupplier {
        return SwitchTrackOutlineSuppler
    }

    override fun getTabOutlineSupplier(sides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                return getRectangularOutline(layoutDirection, size.width, size.height, sides, insets, outlineKind)
            }
        }
    }

    override fun getTextFieldOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    companion object {
        private val RoundOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                return Outline.Rounded(
                    roundRect = RoundRect(
                        left = insets,
                        top = insets,
                        right = size.width - insets,
                        bottom = size.height - insets,
                        radiusX = size.width / 2.0f - insets,
                        radiusY = size.height / 2.0f - insets
                    )
                )
            }
        }

        private val RectangleOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                return Outline.Rectangle(
                    Rect(
                        left = insets, top = insets,
                        right = size.width - insets, bottom = size.height - insets
                    )
                )
            }
        }

        private val DiamondOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val dimension = min(size.width, size.height)
                val midX = size.width / 2.0f
                val midY = size.height / 2.0f
                val halfSize = dimension / 2.0f - insets

                val path = Path()
                // Starting from top, clockwise
                path.moveTo(midX, midY - halfSize)
                path.lineTo(midX + halfSize, midY)
                path.lineTo(midX, midY + halfSize)
                path.lineTo(midX - halfSize, midY)
                path.close()

                return Outline.Generic(path)
            }
        }

        private val SwitchTrackOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val radius = size.height / 2.0f - radiusAdjustment
                return Outline.Rounded(
                    roundRect = RoundRect(
                        left = insets, top = insets,
                        right = size.width - insets, bottom = size.height - insets,
                        cornerRadius = CornerRadius(radius, radius)
                    )
                )
            }
        }
    }
}

private fun getStripeEffect(): RuntimeEffect {
    @Language("GLSL")
    val stripeDesc = """
            uniform vec4 baseColor;
            uniform vec4 stripeColor;
            uniform float alpha;
            uniform float stripeWidth;

            half4 main(vec2 fragcoord) {
                float combined = floor(fragcoord.x) + floor(fragcoord.y);
                if (mod(combined, 3.0 * stripeWidth) >= 2.0 * stripeWidth) {
                    float falpha = alpha * stripeColor.a;
                    return half4(stripeColor.r * falpha, stripeColor.g * falpha, stripeColor.b * falpha, falpha);
                }
                float falpha = alpha * baseColor.a;
                return half4(baseColor.r * falpha, baseColor.g * falpha, baseColor.b * falpha, falpha);
            }
        """

    return RuntimeEffect.makeForShader(stripeDesc)
}

private class BlueprintSurfacePainter: ShaderWrapperSurfacePainter(runtimeEffect = getStripeEffect()) {
    override val displayName: String
        get() = "Blueprint"

    override fun getShaderData(
        density: Density,
        outline: Outline,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ): Data {
        val stripeWidth = with(density) { 1.0.dp.toPx() }

        val dataBuffer = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN)
        // RGBA for the base color
        val baseColor = colorTokens.containerSurfaceDim.withAlpha(0.9f)
        dataBuffer.putFloat(0, baseColor.red)
        dataBuffer.putFloat(4, baseColor.green)
        dataBuffer.putFloat(8, baseColor.blue)
        dataBuffer.putFloat(12, baseColor.alpha)
        // RGBA for the stripe color
        val stripeColor = colorTokens.containerSurfaceLow
        dataBuffer.putFloat(16, stripeColor.red)
        dataBuffer.putFloat(20, stripeColor.green)
        dataBuffer.putFloat(24, stripeColor.blue)
        dataBuffer.putFloat(28, stripeColor.alpha)
        // Alpha
        dataBuffer.putFloat(32, alpha)
        // Stripe width
        dataBuffer.putFloat(36, stripeWidth)

        return Data.makeFromBytes(dataBuffer.array())
    }
}

private class BlueprintWindowDecorator: DefaultWindowDecorator() {
    override fun paintWindowBorder(drawScope: DrawScope, size: Size, colorTokens: ContainerColorTokens) {
        with (drawScope) {
            val width: Float = size.width
            val height: Float = size.height
            val thickness = getWindowBorderInsets().toPx()

            if ((width > thickness) && (height > thickness)) {
                // Inner part, as surface
                drawRect(
                    color = colorTokens.containerSurface,
                    topLeft = Offset(thickness / 2.0f, thickness / 2.0f),
                    size = Size(width - thickness, height - thickness),
                    style = Stroke(width = thickness)
                )

                // Grid dashes
                val gridSize = GridSize.toPx()
                val fullGridDashLength = thickness
                val partialGridDashLength = thickness * 0.7f

                // Top and bottom dashes
                var dashIndex = 1
                var dashX = thickness
                while (dashX < (width - 1)) {
                    val isFullDash = (dashIndex % 4 == 0)

                    if (!isFullDash) {
                        drawLine(
                            color = colorTokens.containerOutlineVariant,
                            start = Offset(dashX, 0.0f),
                            end = Offset(dashX, thickness),
                            strokeWidth = Stroke.HairlineWidth,
                            alpha = GridAlpha
                        )
                        drawLine(
                            color = colorTokens.containerOutlineVariant,
                            start = Offset(dashX, height - thickness - 1.0f),
                            end = Offset(dashX, height - 1.0f),
                            strokeWidth = Stroke.HairlineWidth,
                            alpha = GridAlpha
                        )
                    }

                    val gridDashLength = if (isFullDash) fullGridDashLength else partialGridDashLength
                    drawLine(
                        color = colorTokens.containerOutline,
                        start = Offset(dashX, 0.0f),
                        end = Offset(dashX, gridDashLength),
                        strokeWidth = 2.0f,
                    )
                    drawLine(
                        color = colorTokens.containerOutline,
                        start = Offset(dashX, height - gridDashLength - 1.0f),
                        end = Offset(dashX, height - 1.0f),
                        strokeWidth = 2.0f,
                    )

                    dashIndex++
                    dashX += gridSize
                }

                // Left and right dashes
                dashIndex = 1
                var dashY = thickness
                while (dashY < (height - 1)) {
                    val isFullDash = (dashIndex % 4 == 0)

                    if (!isFullDash) {
                        drawLine(
                            color = colorTokens.containerOutlineVariant,
                            start = Offset(0.0f, dashY),
                            end = Offset(thickness, dashY),
                            strokeWidth = Stroke.HairlineWidth,
                            alpha = GridAlpha
                        )
                        drawLine(
                            color = colorTokens.containerOutlineVariant,
                            start = Offset(width - thickness - 1, dashY),
                            end = Offset(width - 1, dashY),
                            strokeWidth = Stroke.HairlineWidth,
                            alpha = GridAlpha
                        )
                    }

                    val gridDashLength = if (isFullDash) fullGridDashLength else partialGridDashLength
                    drawLine(
                        color = colorTokens.containerOutline,
                        start = Offset(0.0f, dashY),
                        end = Offset(gridDashLength, dashY),
                        strokeWidth = 2.0f,
                    )
                    drawLine(
                        color = colorTokens.containerOutline,
                        start = Offset(width - gridDashLength - 1, dashY),
                        end = Offset(width - 1, dashY),
                        strokeWidth = 2.0f,
                    )

                    dashIndex++
                    dashY += gridSize
                }

                val quarterThickness = thickness / 4.0f
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = 0f, y = quarterThickness / 2.0f),
                    end = Offset(x = width - quarterThickness, y = quarterThickness / 2.0f),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = quarterThickness / 2.0f, y = 0f),
                    end = Offset(x = quarterThickness / 2.0f, y = height - quarterThickness),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = 0f, y = height - quarterThickness / 2.0f),
                    end = Offset(x = width, y = height - quarterThickness / 2.0f),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = colorTokens.containerOutline,
                    start = Offset(x = width - quarterThickness / 2.0f, y = 0f),
                    end = Offset(x = width - quarterThickness / 2.0f, y = height),
                    strokeWidth = quarterThickness,
                    cap = StrokeCap.Butt
                )
            }
        }
    }
}

fun blueprintSkin(): AuroraSkinDefinition {
    val decorationPainter = BlueprintDecorationPainter()
    decorationPainter.inlayPainter = BlueprintDecorationInlayPainter()

    decorationPainter.addOverlayPainter(
        BlueprintDecorationOverlayPainter({ it.containerOutlineVariant }, 1.5f,
            BlueprintDecorationOverlayPainter.Alignment.Bottom),
        DecorationAreaType.Toolbar
    )
    decorationPainter.addOverlayPainter(
        BlueprintDecorationOverlayPainter({ it.containerOutlineVariant }, 1.5f,
            BlueprintDecorationOverlayPainter.Alignment.Top),
        DecorationAreaType.Toolbar, DecorationAreaType.Footer
    )

    val painters = AuroraPainters(
        decorationPainter = decorationPainter,
        surfacePainter = BlueprintSurfacePainter(),
        highlightSurfacePainter = BlueprintSurfacePainter(),
        outlinePainter = FlatOutlinePainter(),
        highlightOutlinePainter = FlatOutlinePainter(),
    )

    return AuroraSkinDefinition(
        displayName = "Blueprint",
        colors = blueprintSkinColors(),
        painters = painters,
        componentShapers = AuroraComponentShapers.withNoDefaults(RectangularComponentShaper()),
        decorators = AuroraDecorators(windowDecorator = BlueprintWindowDecorator()),
    )
}
