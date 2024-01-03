/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper

@Composable
internal fun TitleLabel(
    modifier: Modifier,
    title: String,
    presentationModel: LabelPresentationModel
) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = remember { ClassicButtonShaper() }
    val borderPainter = AuroraSkin.painters.borderPainter

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
                // Fill the background with accented fill color
                drawRect(
                    color = skinColors.getBackgroundColorScheme(decorationAreaType)
                        .accentedBackgroundFillColor,
                    topLeft = Offset.Zero,
                    size = this.size,
                    style = Fill
                )

                val borderOutline = buttonShaper.getButtonOutline(
                    layoutDirection = layoutDirection,
                    width = width,
                    height = height,
                    extraInsets = 0.5f,
                    isInner = false,
                    sides = Sides(
                        straightSides = Side.entries.toSet(),
                        openSides = setOf(Side.Leading, Side.Trailing)
                    ),
                    outlineKind = OutlineKind.Border,
                    density = this
                )

                val outlineBoundingRect = borderOutline.bounds
                if (outlineBoundingRect.isEmpty) {
                    return@withTransform
                }

                val borderColorScheme = skinColors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = ColorSchemeAssociationKind.Border,
                    componentState = ComponentState.Enabled
                )

                val innerBorderOutline = if (borderPainter.isPaintingInnerOutline)
                    buttonShaper.getButtonOutline(
                        layoutDirection = layoutDirection,
                        width = width,
                        height = height,
                        extraInsets = 1.0f,
                        isInner = true,
                        sides = Sides(
                            straightSides = Side.entries.toSet(),
                            openSides = setOf(Side.Leading, Side.Trailing)
                        ),
                        outlineKind = OutlineKind.Border,
                        density = this
                    ) else null

                borderPainter.paintBorder(
                    this,
                    this.size,
                    borderOutline,
                    innerBorderOutline,
                    borderColorScheme,
                    1.0f
                )
            }
        }
        // The title of the current command group
        LabelProjection(
            contentModel = LabelContentModel(text = title),
            presentationModel = presentationModel
        ).project()
    }
}
