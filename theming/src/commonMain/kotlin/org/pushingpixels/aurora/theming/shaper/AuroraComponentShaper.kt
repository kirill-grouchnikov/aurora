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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.theming.AuroraTrait
import org.pushingpixels.aurora.theming.OutlineKind
import org.pushingpixels.aurora.theming.Sides

interface OutlineSupplier {
    fun getOutline(
        layoutDirection: LayoutDirection, density: Density,
        size: Size, insets: Float, radiusAdjustment: Float, outlineKind: OutlineKind
    ): Outline
}

/**
 * Component shaper interface.
 *
 * @author Kirill Grouchnikov
 */
interface AuroraComponentShaper : AuroraTrait {
    fun getButtonOutlineSupplier(buttonSides: Sides): OutlineSupplier

    /**
     * Returns the additional content padding for the specified button dimensions.
     */
    fun getButtonExtraContentPadding(
        uiPreferredSize: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): PaddingValues

    fun getCheckBoxOutlineSupplier(): OutlineSupplier

    fun getComboBoxOutlineSupplier(): OutlineSupplier

    fun getLinearProgressBarProgressOutlineSupplier(sides: Sides): OutlineSupplier

    fun getLinearProgressBarTrackOutlineSupplier(): OutlineSupplier

    fun getRadioButtonOutlineSupplier(): OutlineSupplier
}