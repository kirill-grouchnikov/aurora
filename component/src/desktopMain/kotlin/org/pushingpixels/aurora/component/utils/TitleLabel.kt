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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import org.pushingpixels.aurora.theming.utils.paintOutline

private object TitleLabelOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = 0.0f,
            sides = Sides(
                straightSides = Side.entries.toSet(),
                openSides = setOf(Side.Leading, Side.Trailing)
            ),
            insets = insets,
            outlineKind = outlineKind,
        )
    }
}

@Composable
@OptIn(AuroraInternalApi::class)
internal fun TitleLabel(
    modifier: Modifier,
    title: String,
    presentationModel: LabelPresentationModel
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = remember { ClassicButtonShaper() }
    val outlinePainter = AuroraSkin.painters.outlinePainter

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = this.size.width
            val height = this.size.height

            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = width,
                    bottom = height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                val neutralColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType)
                drawRect(
                    color = if (neutralColorTokens.isDark) {
                        neutralColorTokens.containerSurfaceLow
                    } else {
                        neutralColorTokens.containerSurfaceHigh
                    },
                    topLeft = Offset.Zero,
                    size = this.size,
                    style = Fill
                )

                val borderOutline = buttonShaper.getButtonOutline(
                    layoutDirection = layoutDirection,
                    width = width,
                    height = height,
                    insets = 0.5f,
                    sides = Sides(
                        straightSides = Side.entries.toSet(),
                        openSides = setOf(Side.Leading, Side.Trailing)
                    ),
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Outline,
                    density = this
                )

                val outlineBoundingRect = borderOutline.bounds
                if (outlineBoundingRect.isEmpty) {
                    return@withTransform
                }

                paintOutline(
                    drawScope = this,
                    componentState = ComponentState.Enabled,
                    outlinePainter = outlinePainter,
                    outlinePainterOverlay = null,
                    size = this.size,
                    alpha = 1.0f,
                    outlineSupplier = TitleLabelOutlineSuppler,
                    colorTokens = neutralColorTokens)
            }
        }
        // The title of the current command group
        LabelProjection(
            contentModel = LabelContentModel(text = title),
            presentationModel = presentationModel
        ).project()
    }
}
