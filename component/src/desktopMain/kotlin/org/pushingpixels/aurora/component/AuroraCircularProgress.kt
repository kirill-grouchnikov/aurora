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
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ComponentState
import kotlin.math.min


private val ArcSpanProp = FloatPropKey()

private val CircularProgressTransition = transitionDefinition<Int> {
    state(0) {
        this[ArcSpanProp] = 30f
    }

    state(1) {
        this[ArcSpanProp] = 300f
    }

    transition(fromState = 0, toState = 1) {
        ArcSpanProp using infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    }
}

@Composable
fun AuroraCircularProgress(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val state = transition(
        definition = CircularProgressTransition,
        initState = 0,
        toState = 1
    )

    val arcStart = remember { mutableStateOf(0.0f) }
    val arcEnd = remember { mutableStateOf(0.0f) }
    // TODO - not ideal, but will do for now
    val prevArcSpan = remember { mutableStateOf(state[ArcSpanProp]) }

    val color = AuroraSkin.colors.getColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    ).foregroundColor
    val alpha = AuroraSkin.colors.getAlpha(
        decorationAreaType = AuroraSkin.decorationAreaType,
        componentState = if (enabled) ComponentState.ENABLED else ComponentState.DISABLED_UNSELECTED
    )

    Canvas(
        modifier
            .progressSemantics()
            .preferredSize(10.dp)
    ) {
        val arcSpan = state[ArcSpanProp]
        val isArcGrowing = (arcSpan > prevArcSpan.value)
        if (isArcGrowing) {
            arcStart.value = arcStart.value - 8.0f
            arcEnd.value = arcStart.value - arcSpan
        } else {
            arcEnd.value = arcEnd.value - 8.0f
            arcStart.value = arcEnd.value + arcSpan
        }

        arcStart.value = arcStart.value % 360.0f
        arcEnd.value = arcEnd.value % 360.0f

        prevArcSpan.value = arcSpan

        val diameter = min(size.width, size.height) - 2.0f
        drawArc(
            color = color,
            startAngle = arcStart.value,
            sweepAngle = arcSpan,
            useCenter = false,
            topLeft = Offset(0.0f, 0.0f),
            size = Size(2.0f * diameter, 2.0f * diameter),
            style = Stroke(width = 1.2f.dp.toPx(), cap = StrokeCap.Butt, join = StrokeJoin.Round),
            alpha = alpha
        )
    }
}
