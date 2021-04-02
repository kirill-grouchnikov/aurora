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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.PopupPlacementStrategy

object CommandButtonSizingConstants {
    val WideButtonContentPadding = PaddingValues(start = 10.dp, top = 4.dp, end = 10.dp, bottom = 4.dp)
    val CompactButtonContentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
}

data class CommandButtonPresentationModel(
    val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.MEDIUM,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.CENTER,
    val iconDimension: Dp? = null,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    val textClick: TextClick = TextClick.ACTION,
    val contentPadding: PaddingValues = CommandButtonSizingConstants.CompactButtonContentPadding,
    val horizontalGapScaleFactor: Float = 1.0f,
    val verticalGapScaleFactor: Float = 1.0f,
    val isMenu: Boolean = false
) {
    data class Overlay(
        val presentationState: CommandButtonPresentationState? = null,
        val backgroundAppearanceStrategy: BackgroundAppearanceStrategy? = null,
        val horizontalAlignment: HorizontalAlignment? = null,
        val iconDimension: Dp? = null,
        val iconDisabledFilterStrategy: IconFilterStrategy? = null,
        val iconEnabledFilterStrategy: IconFilterStrategy? = null,
        val iconActiveFilterStrategy: IconFilterStrategy? = null,
        val popupPlacementStrategy: PopupPlacementStrategy? = null,
        val textClick: TextClick? = null,
        val contentPadding: PaddingValues? = null,
        val horizontalGapScaleFactor: Float? = null,
        val verticalGapScaleFactor: Float? = null,
        val isMenu: Boolean? = null,
    )

    fun overlayWith(overlay: Overlay): CommandButtonPresentationModel {
        return CommandButtonPresentationModel(
            presentationState = overlay.presentationState ?: this.presentationState,
            backgroundAppearanceStrategy = overlay.backgroundAppearanceStrategy
                ?: this.backgroundAppearanceStrategy,
            horizontalAlignment = overlay.horizontalAlignment ?: this.horizontalAlignment,
            iconDimension = overlay.iconDimension ?: this.iconDimension,
            iconDisabledFilterStrategy = overlay.iconDisabledFilterStrategy ?: this.iconDisabledFilterStrategy,
            iconEnabledFilterStrategy = overlay.iconEnabledFilterStrategy ?: this.iconEnabledFilterStrategy,
            iconActiveFilterStrategy = overlay.iconActiveFilterStrategy ?: this.iconActiveFilterStrategy,
            popupPlacementStrategy = overlay.popupPlacementStrategy ?: this.popupPlacementStrategy,
            textClick = overlay.textClick ?: this.textClick,
            contentPadding = overlay.contentPadding ?: this.contentPadding,
            horizontalGapScaleFactor = overlay.horizontalGapScaleFactor ?: this.horizontalGapScaleFactor,
            verticalGapScaleFactor = overlay.verticalGapScaleFactor ?: this.verticalGapScaleFactor,
            isMenu = overlay.isMenu ?: this.isMenu
        )
    }
}