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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides
import org.pushingpixels.aurora.theming.utils.getBaseOutline

/**
 * Component shaper that returns rectangular buttons with slightly rounded corners.
 *
 * @author Kirill Grouchnikov
 */
open class ClassicComponentShaper : AuroraComponentShaper {
    override val displayName: String
        get() = "Classic"

    private fun getCornerRadius(density: Density): Float {
        return density.getClassicCornerRadius()
    }

    override fun getButtonExtraContentPadding(uiPreferredSize: Size, layoutDirection: LayoutDirection, density: Density): PaddingValues {
        return PaddingValues.Zero
    }

    override fun getBaselineOutlineSupplier(): OutlineSupplier {
        return DefaultOutlineSuppler
    }

    override fun getBaselineOutlineSupplier(sides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val radius = (getCornerRadius(density) - radiusAdjustment).coerceAtLeast(0.0f)

                return getBaseOutline(layoutDirection, size.width, size.height, radius, sides, insets, outlineKind)
            }
        }
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
                val radius = (getCornerRadius(density) - radiusAdjustment).coerceAtLeast(0.0f)

                return getBaseOutline(layoutDirection, size.width, size.height, radius, buttonSides, insets, outlineKind)
            }
        }
    }

    override fun getCheckBoxOutlineSupplier(): OutlineSupplier {
        return DefaultOutlineSuppler
    }

    override fun getComboBoxOutlineSupplier(): OutlineSupplier {
        return DefaultOutlineSuppler
    }

    override fun getLinearProgressBarProgressOutlineSupplier(sides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val radius = (getCornerRadius(density) / 2.0f - radiusAdjustment).coerceAtLeast(0.0f)

                return getBaseOutline(layoutDirection, size.width, size.height, radius, sides, insets, outlineKind)
            }
        }
    }

    override fun getLinearProgressBarTrackOutlineSupplier(): OutlineSupplier {
        return DefaultHalfOutlineSuppler
    }

    override fun getRadioButtonOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    override fun getScrollBarThumbOutlineSupplier(): OutlineSupplier {
        return ScrollBarThumbOutlineSuppler
    }

    override fun getSliderThumbOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    override fun getSliderTrackOutlineSupplier(): OutlineSupplier {
        return DefaultHalfOutlineSuppler
    }

    override fun getSwitchThumbOutlineSupplier(): OutlineSupplier {
        return RoundOutlineSuppler
    }

    override fun getSwitchTrackOutlineSupplier(): OutlineSupplier {
        return SwitchTrackOutlineSuppler
    }

    override fun getTabButtonOutlineSupplier(sides: Sides): OutlineSupplier {
        return object: OutlineSupplier {
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
                    sides = sides,
                    insets = insets,
                    outlineKind = outlineKind,
                )
            }
        }
    }

    override fun getTextFieldOutlineSupplier(): OutlineSupplier {
        return RectangleOutlineSuppler
    }

    companion object {
        /** Reusable instance of this shaper. */
        val Instance = ClassicComponentShaper()

        private fun Density.getClassicCornerRadius(): Float = 3.0.dp.toPx()

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

        private val DefaultHalfOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                val cornerRadius = density.getClassicCornerRadius() / 2.0f
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

        private val ScrollBarThumbOutlineSuppler = object: OutlineSupplier {
            override fun getOutline(
                layoutDirection: LayoutDirection,
                density: Density,
                size: Size,
                insets: Float,
                radiusAdjustment: Float,
                outlineKind: OutlineKind
            ): Outline {
                // Adaptive corner radius, either half the height (which will be width after rotation for vertical
                // scrollbars) for larger thumbs, or quarter the height for smaller thumbs
                val adjustedInsets = insets + 1.0f
                val radius: Float = if (size.width >= 1.5 * size.height)
                    (size.height - 2.0f * adjustedInsets) / 2.0f
                else
                    (size.height - 2.0f * adjustedInsets) / 4.0f

                return getBaseOutline(
                    layoutDirection = layoutDirection,
                    width = size.width,
                    height = size.height,
                    radius = radius - radiusAdjustment,
                    sides = Sides(),
                    insets = insets,
                    outlineKind = outlineKind,
                )
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
                return getBaseOutline(
                    layoutDirection = layoutDirection,
                    width = size.width,
                    height = size.height,
                    radius = size.height / 2.0f - radiusAdjustment,
                    sides = Sides(),
                    insets = insets,
                    outlineKind = outlineKind,
                )
            }
        }
    }
}