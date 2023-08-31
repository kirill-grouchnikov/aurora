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
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.utils.AuroraRect

internal object BoundsTracker {
    private val bounds: MutableMap<Projection<ContentModel, PresentationModel>, AuroraRect> = hashMapOf()

    fun trackBounds(projection: Projection<ContentModel, PresentationModel>, rect: AuroraRect) {
        bounds[projection] = rect
        println("Added tracking, total ${bounds.size}")
    }

    fun untrackBounds(projection: Projection<ContentModel, PresentationModel>) {
        bounds.remove(projection)
        println("Removed tracking, total ${bounds.size}")
    }

    internal fun getBounds(): MutableMap<Projection<ContentModel, PresentationModel>, AuroraRect> = bounds
}

@AuroraInternalApi
@Composable
fun RibbonOverlay(modifier: Modifier, insets: Dp) {
    Canvas(modifier = modifier) {
        for (tracked in BoundsTracker.getBounds().values) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(tracked.x - insets.toPx(), tracked.y - insets.toPx()),
                size = Size(tracked.width, tracked.height),
                style = Stroke(width=1.0f, cap= StrokeCap.Butt, join= StrokeJoin.Round)
            )
        }
    }
}
