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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.geometry.Size

internal data class AuroraOffset(var x: Float, var y: Float)
internal data class AuroraSize(var width: Int, var height: Int)
internal data class AuroraRect(var x: Float, var y: Float, var width: Float, var height: Float)

internal fun AuroraRect.contains(x: Float, y: Float): Boolean {
    return (x >= this.x) && (x < (this.x + this.width)) && (y >= this.y) &&
            (y < (this.y + this.height))
}

internal val AuroraSize.asSize: Size
    get() = Size(width.toFloat(), height.toFloat())

internal fun AuroraSize.asSize(extraWidth: Int, extraHeight: Int) =
    Size((width + extraWidth).toFloat(), (height + extraHeight).toFloat())
