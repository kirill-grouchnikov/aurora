/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

interface AuroraIcon {
    /**
     * Draws the icon with the provided draw scope.
     */
    fun paintIcon(drawScope: DrawScope)

    /**
     * Returns the current width of this icon.
     */
    fun getWidth(): Dp

    /**
     * Returns the current height of this icon.
     */
    fun getHeight(): Dp

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

private class AuroraIconModifier(val icon: AuroraIcon) : DrawModifier {
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
        modifier.size(
            width = icon.getWidth(),
            height = icon.getHeight()
        ).auroraIconPaint(icon)
    )
}

