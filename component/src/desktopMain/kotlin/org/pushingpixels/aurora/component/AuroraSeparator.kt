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
package org.pushingpixels.aurora.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.common.withAlpha

@Composable
fun AuroraVerticalSeparator(
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val separatorScheme = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        associationKind = ColorSchemeAssociationKind.SEPARATOR,
        componentState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    )

    Canvas(modifier.preferredWidth(2.0f.dp)) {
        val height = size.height

        if (height > 0.0f) {
            val primaryBrush = Brush.verticalGradient(
                0.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                2.0f / height to separatorScheme.separatorPrimaryColor,
                (height - 2.0f) / height to separatorScheme.separatorPrimaryColor,
                1.0f to separatorScheme.separatorPrimaryColor.withAlpha(0.0f),
                startY = 0.0f,
                endY = height,
                tileMode = TileMode.Repeated
            )
            val secondaryBrush = Brush.verticalGradient(
                0.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                2.0f / height to separatorScheme.separatorSecondaryColor,
                (height - 2.0f) / height to separatorScheme.separatorSecondaryColor,
                1.0f to separatorScheme.separatorSecondaryColor.withAlpha(0.0f),
                startY = 0.0f,
                endY = height,
                tileMode = TileMode.Repeated
            )

            drawLine(
                brush = primaryBrush,
                start = Offset(0.5f, 0.0f),
                end = Offset(0.5f, height),
                strokeWidth = 1.0f
            )
            drawLine(
                brush = secondaryBrush,
                start = Offset(1.5f, 0.0f),
                end = Offset(1.5f, height),
                strokeWidth = 1.0f
            )
        }
    }
}