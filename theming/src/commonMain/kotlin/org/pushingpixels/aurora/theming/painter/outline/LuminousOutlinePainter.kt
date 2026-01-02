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
package org.pushingpixels.aurora.theming.painter.outline

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.InsetKind
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.painter.ColorStop
import kotlin.math.max
import kotlin.math.min

/**
 * Outline painter that draws visuals with flat appearance using the container outline token.
 *
 * @author Kirill Grouchnikov
 */
class LuminousOutlinePainter : AuroraOutlinePainter {
    override val displayName = "Luminous"

    override fun paintOutline(
        drawScope: DrawScope,
        size: Size,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        alpha: Float
    ) {
        with(drawScope) {
            translate(left = OuterStrokeWidth, top = OuterStrokeWidth) {
                // In theory, the radius adjustment for the inner outline should be the full stroke
                // width - for perfect concentric rounded corners. In practice, for smaller corners,
                // reducing the inner outline radius by the full stroke width results in inner outline
                // corners that are too tight. This might be revisited in the future.
                val innerOutlineRadiusAdjustment = OuterStrokeWidth / 2.0f

                // Smaller components get simpler outline visuals
                val innerQueries =
                    if ((size.width / density <= 16) || (size.height / density <= 16))
                        InnerHorizontalSimplifiedColorQueries
                    else
                        InnerHorizontalColorQueries

                this.paintHorizontal(
                    size = Size(
                        width = size.width - 2.0f * OuterStrokeWidth,
                        height = size.height - 2.0f * OuterStrokeWidth
                    ),
                    radiusAdjustment = innerOutlineRadiusAdjustment,
                    outlineSupplier = outlineSupplier,
                    colorTokens = colorTokens,
                    strokeWidth = InnerStrokeWidth,
                    colorQueries = innerQueries,
                    alpha = alpha,
                )
            }

            this.paintVertical(
                size = size,
                outlineSupplier = outlineSupplier,
                colorTokens = colorTokens,
                strokeWidth = OuterStrokeWidth,
                colorStops = OuterVerticalColorStops,
                alpha = alpha,
            )
        }
    }

    private fun DrawScope.paintVertical(
        size: Size,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        strokeWidth: Float,
        colorStops: Array<ColorStop>,
        alpha: Float
    ) {
        val colors = colorStops.map { it.colorQuery.invoke(colorTokens) }
        val fractions = colorStops.map { it.fraction }

        val outline = outlineSupplier.getOutline(
            layoutDirection = this.layoutDirection,
            density = this,
            size = size,
            insets = strokeWidth - 0.5f,
            radiusAdjustment = 0.0f,
            outlineKind = OutlineKind.Outline
        )

        drawOutline(
            outline = outline,
            style = Stroke(width = strokeWidth),
            brush = ShaderBrush(
                LinearGradientShader(
                    from = Offset.Zero,
                    to = Offset(0.0f, size.height),
                    colors = colors,
                    colorStops = fractions,
                    tileMode = TileMode.Repeated
                )
            ),
            alpha = alpha
        )
    }

    private fun DrawScope.paintHorizontal(
        size: Size,
        radiusAdjustment: Float,
        outlineSupplier: OutlineSupplier,
        colorTokens: ContainerColorTokens,
        strokeWidth: Float,
        colorQueries: Array<(ContainerColorTokens) -> Color>,
        alpha: Float
    ) {
        val colors = colorQueries.map { it.invoke(colorTokens) }

        val outline = outlineSupplier.getOutline(
            layoutDirection = this.layoutDirection,
            density = this,
            size = size,
            insets = strokeWidth - 0.5f,
            radiusAdjustment = radiusAdjustment,
            outlineKind = OutlineKind.Outline
        )

        var topLeftRadius: Float
        var topRightRadius: Float
        when (outline) {
            is Outline.Rounded -> {
                topLeftRadius = outline.roundRect.topLeftCornerRadius.x
                topRightRadius = outline.roundRect.topRightCornerRadius.x
            }
            else -> {
                topLeftRadius = 0.0f
                topRightRadius = 0.0f
            }
        }
        // Handle completely square corners, and clamp them to not be more than 10% of the overall width
        topLeftRadius = topLeftRadius.coerceIn(1.0f, 0.1f * size.width)
        topRightRadius = topRightRadius.coerceIn(1.0f, 0.1f * size.width)


        // Dynamically compute the gradient fractions to follow the corner radius on left
        // and right sides
        val fractions = listOf(
            0.0f,
            min(0.499f, 0.5f * topLeftRadius / (size.width - 1.0f)),
            max(0.501f, 1.0f - 0.5f * topRightRadius / (size.width - 1.0f)),
            1.0f
        )

        drawOutline(
            outline = outline,
            style = Stroke(width = strokeWidth),
            brush = ShaderBrush(
                LinearGradientShader(
                    from = Offset.Zero,
                    to = Offset(size.width, 0.0f),
                    colors = colors,
                    colorStops = fractions,
                    tileMode = TileMode.Repeated
                )
            ),
            alpha = alpha
        )
    }

    override fun getOutlineInset(insetKind: InsetKind): Float {
        return when (insetKind) {
            InsetKind.Surface -> {
                // Ignore the inner outline, and treat surface to extend to outer outline
                OuterStrokeWidth
            }
            InsetKind.Content -> {
                // For content, both outlines should be considered. However, to preserve the layout
                // alignment between single outlines (from [FractionBasedOutlinePainter]) and more
                // complex outlines from this painter, make a special case where only the outer outline
                // is considered for the content insets.
                return OuterStrokeWidth
            }
        }
    }

    companion object {
        private const val OuterStrokeWidth = 1.0f
        private val OuterVerticalColorStops = arrayOf(
            ColorStop(fraction = 0.0f, colorQuery = ContainerColorTokens::containerOutline),
            ColorStop(fraction = 0.5f, colorQuery = ContainerColorTokens::containerOutline),
            ColorStop(fraction = 1.0f, colorQuery = ContainerColorTokens::containerOutlineVariant),
        )

        private const val InnerStrokeWidth = 2.0f
        private val InnerHorizontalLightQuery: (ContainerColorTokens) -> Color =
            { it.containerSurface.interpolateTowards(it.containerOutlineVariant, 0.4f)}
        private val InnerHorizontalDarkQuery: (ContainerColorTokens) -> Color =
            { it.containerSurface.interpolateTowards(it.complementaryContainerOutline, 0.85f)}
        private val InnerHorizontalColorQueries = arrayOf<(ContainerColorTokens) -> Color>(
            { tokens ->
                if (tokens.isDark) {
                    tokens.containerOutlineVariant
                } else {
                    InnerHorizontalLightQuery.invoke(tokens)
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    tokens.containerOutlineVariant
                } else {
                    InnerHorizontalLightQuery.invoke(tokens)
                }
            }
        )
        private val InnerHorizontalSimplifiedColorQueries = arrayOf<(ContainerColorTokens) -> Color>(
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            },
            { tokens ->
                if (tokens.isDark) {
                    InnerHorizontalDarkQuery.invoke(tokens)
                } else {
                    tokens.complementaryContainerOutline
                }
            }
        )
    }
}