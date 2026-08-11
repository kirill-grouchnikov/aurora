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
package org.pushingpixels.aurora.common

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize

@AuroraInternalApi
data class AuroraOffset(var x: Float, var y: Float)
@AuroraInternalApi
data class AuroraRect(var x: Float, var y: Float, var width: Float, var height: Float)

@AuroraInternalApi
fun AuroraRect.contains(x: Float, y: Float): Boolean {
    return (x >= this.x) && (x < (this.x + this.width)) && (y >= this.y) &&
            (y < (this.y + this.height))
}

@AuroraInternalApi
val AuroraRect.isEmpty: Boolean
    get() = (this.width == 0.0f) && (this.height == 0.0f)

@AuroraInternalApi
fun AuroraOffset.asOffset(density: Density): Offset {
    return Offset(x / density.density, y / density.density)
}

@AuroraInternalApi
fun IntSize.asSize(density: Density): Size {
    return Size(width / density.density, height / density.density)
}

@AuroraInternalApi
fun IntSize.asSize(extraWidth: Int = 0, extraHeight: Int = 0) =
    Size((width + extraWidth).toFloat(), (height + extraHeight).toFloat())
