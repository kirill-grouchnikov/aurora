/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface AuroraIcon {
    /**
     * Draws the icon with the provided draw scope.
     */
    fun paintIcon(drawScope: DrawScope)

    /**
     * Returns the current width of this icon.
     */
    fun getWidth(): Int

    /**
     * Returns the current height of this icon.
     */
    fun getHeight(): Int

    /**
     * Changes the size of this icon.
     */
    @Composable
    fun setSize(width: Dp, height: Dp)

    /**
     * Interface for creating new icons of this type.
     *
     * @author Kirill Grouchnikov
     */
    interface Factory {
        /**
         * Returns a new instance of the icon managed by this factory.
         *
         * @return A new instance of the icon managed by this factory.
         */
        fun createNewIcon(): AuroraIcon
    }
}

fun Modifier.auroraIconPaint(icon: AuroraIcon) =
    this.then(AuroraIconModifier(icon = icon))

class AuroraIconModifier(val icon: AuroraIcon) : DrawModifier {
    override fun ContentDrawScope.draw() {
        icon.paintIcon(this)
    }
}

@Composable
fun AuroraIcon(
    icon: AuroraIcon,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.preferredSize(
            width = icon.getWidth().dp,
            height = icon.getHeight().dp
        ).auroraIconPaint(icon)
    )
}

