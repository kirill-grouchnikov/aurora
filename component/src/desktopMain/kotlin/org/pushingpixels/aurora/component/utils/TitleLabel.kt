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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.auroraFlatBackground
import org.pushingpixels.aurora.theming.utils.paintOutline

@Composable
@OptIn(AuroraInternalApi::class)
internal fun TitleLabel(
    modifier: Modifier,
    title: String,
    presentationModel: LabelPresentationModel
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val outlinePainter = AuroraSkin.painters.outlinePainter
    val componentShaper = AuroraSkin.componentShaper
    val sides = Sides(straightSides = Side.entries.toSet(), openSides = setOf(Side.Leading, Side.Trailing))

    val neutralColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType)
    val fillColor = if (neutralColorTokens.isDark) {
        neutralColorTokens.containerSurfaceLow
    } else {
        neutralColorTokens.containerSurfaceHigh
    }
    Box(modifier = modifier.auroraFlatBackground(fillColor)) {
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
                paintOutline(
                    drawScope = this,
                    componentState = ComponentState.Enabled,
                    outlinePainter = outlinePainter,
                    outlinePainterOverlay = null,
                    size = this.size,
                    alpha = 1.0f,
                    outlineSupplier = componentShaper.getBaselineOutlineSupplier(sides),
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
