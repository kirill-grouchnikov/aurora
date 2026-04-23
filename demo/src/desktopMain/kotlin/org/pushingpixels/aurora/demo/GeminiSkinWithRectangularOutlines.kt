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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraComponentShaper
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier
import kotlin.math.min

fun geminiSkinWithRectangularOutlines(): AuroraSkinDefinition {
    return geminiSkin().copy(componentShapers = AuroraComponentShapers.withNoDefaults(RectangularComponentShaper()))
}

private class RectangularComponentShaper: AuroraComponentShaper {
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

    override val displayName: String
        get() = "Rectangular"

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
