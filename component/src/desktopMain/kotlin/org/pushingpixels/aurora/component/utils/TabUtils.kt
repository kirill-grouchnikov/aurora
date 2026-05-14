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
package org.pushingpixels.aurora.component.utils

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
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.ContainerColorTokensAssociationKind
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerTokens

@OptIn(AuroraInternalApi::class)
object TabUtils {
    fun getTabOutlineColor(colorTokens: ContainerColorTokens): Color {
        return colorTokens.markerOnContainer
    }

    @Composable
    fun getTabContentColorTokens(presentationModel: CommandButtonPresentationModel,
        modelStateInfo: ModelStateInfo, currState: ComponentState) : Color {

        val skinColors = AuroraSkin.colors
        val decorationAreaType = AuroraSkin.decorationAreaType
        return getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currState,
            colors = skinColors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Tab,
            backgroundAppearanceStrategy = presentationModel.backgroundAppearanceStrategy,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted,
            isTextInFilledArea = true)
    }

    @Composable
    fun getTabOutlineColorTokens(tokensOverlayProvider: ContainerColorTokensOverlay.Provider?) : ContainerColorTokens {
        val tokensOverlay = tokensOverlayProvider?.getOverlay(AuroraSkin.colors, AuroraSkin.decorationAreaType)
        return tokensOverlay?.neutralContainerTokens
            ?: AuroraSkin.colors.getNeutralContainerTokens(AuroraSkin.decorationAreaType)
    }

    fun paintTabSurface(
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
        alpha: Float) {

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

    fun paintTabSurfaceHighlight(
        drawScope: DrawScope, outlineSupplier: OutlineSupplier,
        density: Density, size: Size,
        surfaceHighlightColorTokens: ContainerColorTokens, alpha: Float) {
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

    fun paintTabOutline(
        drawScope: DrawScope, outlineSupplier: OutlineSupplier,
        density: Density, size: Size,
        outlineColorTokens: ContainerColorTokens, alpha: Float) {

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
                    color = getTabOutlineColor(outlineColorTokens),
                    alpha = alpha
                )
            }

        }
    }
}
