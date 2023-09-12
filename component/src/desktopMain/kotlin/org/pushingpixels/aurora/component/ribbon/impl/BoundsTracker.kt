/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.common.contains
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.BaseCommandButtonProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryProjection

@AuroraInternalApi
object BoundsTracker {
    private val bounds: MutableMap<Projection<ContentModel, PresentationModel>, AuroraRect> = hashMapOf()

    fun trackBounds(projection: Projection<ContentModel, PresentationModel>, rect: AuroraRect) {
        bounds[projection] = rect
//        if (projection is RibbonGalleryProjection) {
//            println("Gallery at $rect")
//        }
        //println("Added tracking, total ${bounds.size}")
    }

    fun untrackBounds(projection: Projection<ContentModel, PresentationModel>) {
        bounds.remove(projection)
        //println("Removed tracking, total ${bounds.size}")
    }

    internal fun getBounds(): MutableMap<Projection<ContentModel, PresentationModel>, AuroraRect> = bounds
}

@AuroraInternalApi
fun getGalleryProjectionUnder(x: Float, y: Float) : RibbonGalleryProjection? {
    for (tracked in BoundsTracker.getBounds().entries) {
        if (tracked.key is RibbonGalleryProjection) {
            if (tracked.value.contains(x, y)) {
                return tracked.key as RibbonGalleryProjection
            }
        }
    }
    // second pass - see if a command button projection was created from a gallery
    for (tracked in BoundsTracker.getBounds().entries) {
        if (tracked.key is BaseCommandButtonProjection<*, *, *>) {
            val commandButtonProjection = tracked.key as BaseCommandButtonProjection<*, *, *>
            if ((commandButtonProjection.contentModel.tag is RibbonGalleryProjection) &&
                    tracked.value.contains(x, y)) {
                return commandButtonProjection.contentModel.tag as RibbonGalleryProjection
            }
        }
    }
    return null
}

@AuroraInternalApi
fun getComponentProjectionUnder(x: Float, y: Float) : Projection<*, *>? {
    for (tracked in BoundsTracker.getBounds().entries) {
        if ((tracked.key !is RibbonGalleryProjection) && (tracked.key !is BaseCommandButtonProjection<*, *, *>)) {
            if (tracked.value.contains(x, y)) {
                return tracked.key
            }
        }
    }
    return null
}

@AuroraInternalApi
fun getCommandButtonProjectionUnder(x: Float, y: Float) : BaseCommandButtonProjection<*, *, *>? {
    for (tracked in BoundsTracker.getBounds().entries) {
        if (tracked.key is BaseCommandButtonProjection<*, *, *>) {
            if (tracked.value.contains(x, y)) {
                return tracked.key as BaseCommandButtonProjection<*, *, *>
            }
        }
    }
    return null
}

@AuroraInternalApi
@Composable
fun RibbonOverlay(modifier: Modifier, insets: Dp) {
    Canvas(modifier = modifier) {
        val width = this.size.width
        val height = this.size.height
        println("BOUNDS TRACKER SIZE ${this.size}")
        for (tracked in BoundsTracker.getBounds().entries) {
            val color = when (tracked.key) {
                is RibbonGalleryProjection -> Color.Blue
                else -> Color.Red
            }
            drawRect(
                color = color,
                topLeft = Offset(tracked.value.x - insets.toPx(), tracked.value.y - insets.toPx()),
                size = Size(tracked.value.width, tracked.value.height),
                style = Stroke(width=1.0f, cap= StrokeCap.Butt, join= StrokeJoin.Round)
            )
        }
    }
}
