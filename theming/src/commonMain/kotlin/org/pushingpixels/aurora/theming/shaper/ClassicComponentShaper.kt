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
package org.pushingpixels.aurora.theming.shaper

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import org.pushingpixels.aurora.theming.utils.getClassicCornerRadius

/**
 * Component shaper that returns rectangular buttons with slightly rounded corners.
 *
 * @author Kirill Grouchnikov
 */
open class ClassicComponentShaper : AuroraComponentShaper {
    override val displayName: String
        get() = "Classic"

    private fun getCornerRadius(width: Float, height: Float, insets: Float, density: Density): Float {
        return density.getClassicCornerRadius()
    }

    override fun getButtonExtraContentPadding(uiPreferredSize: Size, layoutDirection: LayoutDirection, density: Density): PaddingValues {
        return PaddingValues.Zero
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
                val radius = (getCornerRadius(size.width, size.height, insets, density) - radiusAdjustment).coerceAtLeast(0.0f)

                return getBaseOutline(layoutDirection, size.width, size.height, radius, buttonSides, insets, outlineKind)
            }
        }
    }

    override fun getCheckBoxOutlineSupplier(): OutlineSupplier {
        return DefaultOutlineSuppler
    }

    override fun getRadioButtonOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    companion object {
        /** Reusable instance of this shaper. */
        val Instance = ClassicComponentShaper()

        private val DefaultOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val cornerRadius = density.getClassicCornerRadius()
                return getBaseOutline(
                    layoutDirection = layoutDirection,
                    width = size.width,
                    height = size.height,
                    radius = cornerRadius - radiusAdjustment,
                    sides = Sides(),
                    insets = insets,
                    outlineKind = outlineKind,
                )
            }
        }

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
    }
}