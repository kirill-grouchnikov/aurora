/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.component.model

import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.PopupPlacementStrategy

enum class StripOrientation {
    /**
     * Horizontal strip orientation.
     */
    HORIZONTAL,

    /**
     * Vertical strip orientation.
     */
    VERTICAL
}

data class CommandStripPresentationModel(
    val orientation: StripOrientation = StripOrientation.HORIZONTAL,
    val commandPresentationState: CommandButtonPresentationState = CommandButtonPresentationState.MEDIUM,
    val horizontalGapScaleFactor: Float =
        if (orientation == StripOrientation.HORIZONTAL) DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
        else DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS,
    val verticalGapScaleFactor: Float =
        if (orientation == StripOrientation.VERTICAL) DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS
        else DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.CENTER,
    val iconDimension: Int? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    val textClick: TextClick = TextClick.ACTION,
    val isMenu: Boolean = false
) {
    companion object {
        const val DEFAULT_GAP_SCALE_FACTOR_PRIMARY_AXIS = 0.75f
        const val DEFAULT_GAP_SCALE_FACTOR_SECONDARY_AXIS = 1.0f
    }
}