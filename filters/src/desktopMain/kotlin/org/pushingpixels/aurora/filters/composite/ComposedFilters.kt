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
package org.pushingpixels.aurora.filters.composite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import org.jetbrains.skia.ImageFilter

@JvmInline
value class FloatPair(val packed: Long) {
    constructor(first: Float, second: Float) : this(
        (first.toBits().toLong() shl 32) or (second.toBits().toLong() and 0xFFFFFFFFL)
    )

    val first: Float
        get() = Float.fromBits((packed ushr 32).toInt())

    val second: Float
        get() = Float.fromBits((packed and 0xFFFFFFFFL).toInt())
}

@Composable
fun Modifier.effectChainModifier(
    outer: (FloatPair) -> ImageFilter,
    inner: (FloatPair) -> ImageFilter
): Modifier {
    val contentSize = remember { mutableStateOf<FloatPair?>(null) }

    val sizeChangedModifier = Modifier.onSizeChanged { newSize ->
        contentSize.value = FloatPair(newSize.width.toFloat(), newSize.height.toFloat())
    }

    return sizeChangedModifier.then(this).graphicsLayer {
        val size = contentSize.value ?: return@graphicsLayer

        val innerFilter: ImageFilter = inner.invoke(size)
        val outerFilter: ImageFilter = outer.invoke(size)

        // Compose: outer(inner(source))
        val composedFilter = ImageFilter.makeCompose(outerFilter, innerFilter)
        renderEffect = composedFilter.asComposeRenderEffect()
        clip = true
    }
}
