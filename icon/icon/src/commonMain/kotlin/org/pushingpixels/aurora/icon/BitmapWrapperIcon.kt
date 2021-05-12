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

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BitmapWrapperIcon(private val bitmap: ImageBitmap, density: Float): AuroraIcon {
    var _width = (bitmap.width / density).dp
    var _height = (bitmap.height / density).dp

    override fun getWidth(): Dp {
        return _width
    }

    override fun getHeight(): Dp {
        return _height
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        _width = width
        _height = height
    }

    override fun paintIcon(drawScope: DrawScope) {
        with (drawScope) {
            val scaleX = bitmap.width.toFloat() / (_width.value * density)
            val scaleY = bitmap.height.toFloat() / (_height.value * density)
            scale(scaleX = scaleX, scaleY = scaleY, pivot = Offset.Zero) {
                drawImage(bitmap)
            }
        }
    }
}