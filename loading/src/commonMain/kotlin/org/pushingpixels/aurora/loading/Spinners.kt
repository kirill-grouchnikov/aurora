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
package org.pushingpixels.aurora.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// This is a modified version of the original source code by Jakub Krehel
// available under the MIT license. See the full license under resources/Loading.license.

// ─── Arc ───────────────────────────────────────────────────────────────────────

@Composable
public fun Arc(
    modifier: Modifier,
    color: Color,
    duration: Int = 800,
    easing: Easing = LinearEasing,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing)),
    )

    Canvas(modifier) {
        val radius = size.width / 2f
        val strokeWidth = 2.5.dp.toPx()
        val circumference = 2f * PI.toFloat() * radius
        val dash = 18.0f * circumference / (18.0f + 44.8f)
        withTransform({ rotate(angle) }) {
            drawArc(
                color = color, startAngle = 0f, sweepAngle = 360f, useCenter = false,
                style = Stroke(
                    strokeWidth,
                    cap = cap,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, circumference - dash))
                ),
            )
        }
    }
}

// ─── Atom ──────────────────────────────────────────────────────────────────────

@Composable
public fun Atom(
    modifier: Modifier,
    color: Color,
    duration: Int = 1000,
    easing: Easing = LinearEasing,
) {
    val t = rememberInfiniteTransition()

    val animationDuration = duration * 2 / 3

    // Three inner orbits at 120 degree intervals and time delays
    val orbits = List(3) { index ->
        t.animateFloat(
            initialValue = 1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle (animation + end delay)
                    durationMillis = duration

                    // At 0ms, value is 1.0f
                    0.0f at 0 using easing

                    // Reaches 0.0f halfway through the active animation time
                    1.0f at (animationDuration / 2) using easing

                    // Returns to 1.0f at the end of the active animation time
                    0.0f at animationDuration using easing

                    // Holds 1.0f from animationDuration until totalDuration (the delay)
                    0.0f at duration using easing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific orbits to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * animationDuration / 2,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    Canvas(modifier) {
        val strokeWidth = 1.5.dp.toPx()
        val innerStrokeWidth = 0.8.dp.toPx()
        drawCircle(color, radius = size.width / 2f, style = Stroke(strokeWidth))

        for ((index, orbit) in orbits.withIndex()) {
            val orbitValue = orbit.value
            // Special handling for zero-sized stroke that is treated by Skia as hairline.
            // Here, we skip the orbit that is in the fully collapsed state.
            if (orbitValue != 1.0f) {
                rotate(degrees = index * 120.0f) {
                    drawOval(
                        color = color,
                        topLeft = Offset(x = 0.5f * size.width * orbitValue, y = 0.0f),
                        size = Size(width = size.width * (1.0f - orbitValue), height = size.height),
                        style = Stroke(innerStrokeWidth * (1.0f - orbitValue * orbitValue))
                    )
                }
            }
        }
    }
}

// ─── Blocks ────────────────────────────────────────────────────────────────────

public enum class BlocksSweep { Columns, Diagonal, Rows }

@Composable
public fun Blocks(
    modifier: Modifier,
    color: Color,
    duration: Int = 1300,
    sweep: BlocksSweep = BlocksSweep.Diagonal,
) {
    val t = rememberInfiniteTransition()

    val gridSize = 3
    val maxStep = when (sweep) {
        BlocksSweep.Columns -> gridSize
        BlocksSweep.Diagonal -> 2 * gridSize - 1
        BlocksSweep.Rows -> gridSize
    }

    // Sweeps (rows, columns or diagonals, based on the incoming sweep enum)
    val sweeps = List(maxStep) { index ->
        t.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = duration, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of Delay to delay the specific sweep to start in a staggered fashion
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / maxStep,
                    offsetType = StartOffsetType.Delay
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val gap = dim * 0.1f
        val cellSize = (dim - gap * 2) / gridSize
        val br = dim * 0.0625f

        for (index in 0 until gridSize * gridSize) {
            val column = index % gridSize
            val row = index / gridSize
            val step = when (sweep) {
                BlocksSweep.Columns -> column
                BlocksSweep.Diagonal -> row + column
                BlocksSweep.Rows -> row
            }
            val sweepValue = sweeps[step].value
            // Scale: 0% -> 0.0, 33% -> 1.0, 66% -> 1.0, 100% -> 0.0
            // Scaling down for the first third, holding steady for the next third, scaling down for the last third
            val scale = if (sweepValue < 1.0f / 3.0f) {
                // Going from 0.0 to 1.0
                3.0f * sweepValue
            } else if (sweepValue < 2.0f / 3.0f) {
                // Steady at 1.0
                1.0f
            } else {
                // Going from 1.0 to 0.0
                3.0f * (1 - sweepValue)
            }
            val scaledSize = cellSize * scale
            val x = column * (cellSize + gap) + (cellSize - scaledSize) / 2f
            val y = row * (cellSize + gap) + (cellSize - scaledSize) / 2f
            drawRoundRect(
                color,
                Offset(x, y),
                Size(scaledSize, scaledSize),
                CornerRadius(br * scale.coerceAtLeast(0f), br * scale.coerceAtLeast(0f))
            )
        }
    }
}

// ─── BouncingDots ──────────────────────────────────────────────────────────────

@Composable
public fun BouncingDots(
    modifier: Modifier,
    color: Color,
    duration: Int = 500,
) {
    val t = rememberInfiniteTransition()

    // Dots
    val dots = List(3) { index ->
        t.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = duration, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of Delay to delay the specific sweep to start in a staggered fashion
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.Delay
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val dotSize = dim * 0.25f
        val gap = dim * 0.2f
        val totalWidth = 3 * dotSize + 2 * gap
        val startX = (dim - totalWidth) / 2f

        for (i in 0..2) {
            val dotValue = dots[i].value
            val yOff = (0.28f - dotValue) * dotSize
            drawCircle(
                color,
                radius = dotSize / 2f,
                center = Offset(startX + i * (dotSize + gap) + dotSize / 2f, dim / 2f + yOff)
            )
        }
    }
}

// ─── Cascade ───────────────────────────────────────────────────────────────────

@Composable
public fun Cascade(
    modifier: Modifier,
    color: Color,
    duration: Int = 1500,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()
    val cascades = List(3) {
        // The Y coordinate of the first control point is negative to start each cascade rotation in the
        // opposite direction. The amount of that opposite motion is larger for the outer and middle cascades
        // to create a staggered effect.
        index -> t.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = duration,
                    easing = CubicBezierEasing(0.68f, -0.75f * (1.0f + 0.35f * (2 - index)), 0.265f, 1.75f)
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val center = dim / 2.0f
        listOf(0.875f, 0.58f, 0.3f).forEachIndexed { index, cascadeScale ->
            val scaledRadius = 0.5f * dim * cascadeScale
            val circumference = 2.0f * PI.toFloat() * scaledRadius
            // Rotate the cascade arc based on the cascade tracker
            withTransform({
                rotate(cascades[index].value)
            }) {
                drawArc(
                    color = color,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset(x = center - scaledRadius, y = center - scaledRadius),
                    size = Size(width = 2.0f * scaledRadius, height = 2.0f * scaledRadius),
                    style = Stroke(
                        1.6.dp.toPx(),
                        cap = cap,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(0.25f * circumference, 0.75f * circumference))
                    )
                )
            }
        }
    }
}

// ─── CircularDots ──────────────────────────────────────────────────────────────

@Composable
public fun CircularDots(
    modifier: Modifier,
    color: Color,
    duration: Int = 800,
) {
    val t = rememberInfiniteTransition()
    val highlight by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing)))

    Canvas(modifier) {
        val dim = size.width
        val scale = dim / 16f
        val dotR = 1.5f * scale

        for (index in 0..7) {
            val angle = index * PI / 4
            val distanceFromHighlight = ((highlight + index / 8f) % 1f)
            val alpha = 1f - distanceFromHighlight * 0.8f
            drawCircle(
                color.copy(alpha = alpha.coerceIn(0f, 1f)),
                radius = dotR,
                center = Offset(
                    ((8f + 6.5f * sin(angle)) * scale).toFloat(),
                    ((8f + 6.5f * cos(angle)) * scale).toFloat()
                )
            )
        }
    }
}

// ─── Classic ───────────────────────────────────────────────────────────────────

@Composable
public fun Classic(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()
    val highlight by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing))
    )

    Canvas(modifier) {
        val dim = size.width
        val barLength = 0.22f * dim
        val barThickness = 0.1f * dim
        val offsetFromCenter = 0.28f * dim

        for (index in 0..11) {
            val distanceFromHighlight = ((highlight + index / 12f) % 1f)
            val alpha = 1f - distanceFromHighlight * 0.8f
            withTransform({
                translate(dim / 2f, dim / 2f)
            }) {
                val angle = index * PI.toFloat() / 6.0f

                val startPoint =
                    Offset(x = offsetFromCenter * sin(angle), y = offsetFromCenter * cos(angle))
                val endPoint = Offset(
                    x = (offsetFromCenter + barLength) * sin(angle),
                    y = (offsetFromCenter + barLength) * cos(angle)
                )

                drawLine(
                    color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                    start = startPoint,
                    end = endPoint,
                    strokeWidth = barThickness,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

// ─── ClassicV2 ─────────────────────────────────────────────────────────────────

@Composable
public fun ClassicV2(
    modifier: Modifier,
    color: Color,
    duration: Int = 800,
) {
    val t = rememberInfiniteTransition()
    val highlight by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing))
    )

    Canvas(modifier) {
        val dim = size.width
        val barLength = 0.22f * dim
        val barThickness = 0.1f * dim
        val offsetFromCenter = 0.28f * dim

        for (index in 0..7) {
            val distanceFromHighlight = ((highlight + index / 8f) % 1f)
            val alpha = 1f - distanceFromHighlight * 0.8f
            withTransform({
                translate(dim / 2f, dim / 2f)
            }) {
                val angle = index * PI.toFloat() / 4.0f

                val startPoint =
                    Offset(x = offsetFromCenter * sin(angle), y = offsetFromCenter * cos(angle))
                val endPoint = Offset(
                    x = (offsetFromCenter + barLength) * sin(angle),
                    y = (offsetFromCenter + barLength) * cos(angle)
                )

                drawLine(
                    color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                    start = startPoint,
                    end = endPoint,
                    strokeWidth = barThickness,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

// ─── Clock ─────────────────────────────────────────────────────────────────────

internal fun Float.toRadians() = this * PI.toFloat() / 180f

internal val PointZero = Offset(0f, 0f)
internal fun radialToCartesian(
    radius: Float,
    angleRadians: Float,
    center: Offset = PointZero
) = directionVectorPointF(angleRadians) * radius + center

internal fun directionVectorPointF(angleRadians: Float) =
    Offset(cos(angleRadians), sin(angleRadians))

@Composable
public fun Clock(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
    easing: Easing = LinearEasing,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    // Following the sample at https://developer.android.com/develop/ui/compose/graphics/draw/shapes#custom-polygons
    val vertices = remember {
        val radiusBig = 1.05f
        val radius = 0.75f
        val smallRadius = 0.0f
        floatArrayOf(
            radialToCartesian(radius, 330f.toRadians()).x,
            radialToCartesian(radius, 330f.toRadians()).y,
            radialToCartesian(smallRadius, 270f.toRadians()).x,
            radialToCartesian(smallRadius, 270f.toRadians()).y,
            radialToCartesian(radius, 210f.toRadians()).x,
            radialToCartesian(radius, 210f.toRadians()).y,
            radialToCartesian(radiusBig, 270f.toRadians()).x,
            radialToCartesian(radiusBig, 270f.toRadians()).y,
        )
    }

    val rounding = remember {
        val roundingSmall = 0.15f
        val roundingBig = 1.0f
        listOf(
            CornerRounding(roundingSmall),
            CornerRounding(roundingSmall),
            CornerRounding(roundingSmall),
            CornerRounding(roundingBig),
        )
    }

    val polygon = remember(vertices, rounding) {
        RoundedPolygon(
            vertices = vertices,
            perVertexRounding = rounding
        )
    }

    val path = polygon.toPath()
    Canvas(modifier) {
        val dim = size.width
        drawCircle(color.copy(alpha = 0.1f), radius = dim / 2f)
        withTransform({
            rotate(angle)
            scale(size.width * 0.5f, size.width * 0.5f)
            translate(size.width * 0.5f, size.height * 0.5f)
        }) {
            drawPath(path, color = color)
        }
    }
}

// ─── Comet ─────────────────────────────────────────────────────────────────────

@Composable
public fun Comet(
    modifier: Modifier,
    color: Color,
    duration: Int = 700,
    easing: Easing = LinearEasing,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val radius = size.width / 2
        val strokeWidth = 2.5.dp.toPx()

        // Account for the rounded stroke cap in start and sweep angles of the arc
        val circumference = 2.0f * PI.toFloat() * (radius - strokeWidth / 2)
        val strokeAngle = strokeWidth / circumference * 180.0f

        withTransform({
            rotate(angle)
        }) {
            val sweepGradientBrush = Brush.sweepGradient(
                0.0f to color.copy(alpha = 0.0f),
                0.5f to color.copy(alpha = 0.5f),
                1.0f to color
            )
            drawArc(
                brush = sweepGradientBrush,
                startAngle = 0.0f + strokeAngle,
                sweepAngle = 360.0f - strokeAngle * 2.0f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
        }
    }
}

// ─── Compass ───────────────────────────────────────────────────────────────────

@Composable
public fun Compass(
    modifier: Modifier,
    color: Color,
    duration: Int = 500,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        infiniteRepeatable(tween(durationMillis = duration, easing = EaseInOut), RepeatMode.Restart)
    )

    Canvas(modifier) {
        val dim = size.width
        val barLength = 0.22f * dim
        val barThickness = 0.1f * dim
        val offsetFromCenter = 0.28f * dim

        for (index in 0..3) {
            withTransform({
                translate(dim / 2f, dim / 2f)
            }) {
                val barAngle = index * PI.toFloat() / 2.0f - angle * PI.toFloat() / 180.0f

                val startPoint =
                    Offset(x = offsetFromCenter * sin(barAngle), y = offsetFromCenter * cos(barAngle))
                val endPoint = Offset(
                    x = (offsetFromCenter + barLength) * sin(barAngle),
                    y = (offsetFromCenter + barLength) * cos(barAngle)
                )

                drawLine(
                    color = color,
                    start = startPoint,
                    end = endPoint,
                    strokeWidth = barThickness,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

// ─── Dual ──────────────────────────────────────────────────────────────────────

@Composable
public fun Dual(
    modifier: Modifier,
    color: Color,
    duration: Int = 1000,
    easing: Easing = LinearEasing,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val dim = size.width
        val strokeWidth = 2.5f.dp.toPx()

        withTransform({
            rotate(angle)
        }) {
            // Outer arc
            val radiusOuter = 0.42f * dim
            val circumferenceOuter = 2f * PI.toFloat() * radiusOuter
            val dashOuter = 18.0f * circumferenceOuter / (18.0f + 44.8f)
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(x = dim / 2.0f - radiusOuter, y = dim / 2.0f - radiusOuter),
                size = Size(width = 2.0f * radiusOuter, height = 2.0f * radiusOuter),
                style = Stroke(
                    width = strokeWidth,
                    cap = cap,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashOuter, circumferenceOuter - dashOuter))
                )
            )
        }
        withTransform({
            rotate(-angle)
        }) {
            // Inner arc
            val radiusInner = 0.23f * dim
            val circumferenceInner = 2f * PI.toFloat() * radiusInner
            val dashInner = 18.0f * circumferenceInner / (18.0f + 44.8f)
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(x = dim / 2.0f - radiusInner, y = dim / 2.0f - radiusInner),
                size = Size(width = 2.0f * radiusInner, height = 2.0f * radiusInner),
                style = Stroke(
                    width = strokeWidth,
                    cap = cap,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashInner, circumferenceInner - dashInner))
                )
            )
        }
    }
}

// ─── Eclipse ───────────────────────────────────────────────────────────────────

@Composable
public fun Eclipse(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()

    val slide = List(2) { index ->
        t.animateFloat(
            initialValue = 0.5f,
            targetValue = 0.5f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.5f
                    0.5f at 0 using LinearEasing

                    // Halfway in reaches -0.5f
                    (-0.5f) at duration / 2 using LinearEasing

                    // Back to 0.5f at the end
                    0.5f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific dots to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 2,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    val alpha = List(2) { index ->
        t.animateFloat(
            initialValue = 1.0f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.75f
                    0.75f at 0 using LinearEasing

                    // At 25% in reaches 1.0f
                    1.0f at duration / 4 using LinearEasing

                    // At 50% back to 0.75f
                    0.75f at duration / 2 using LinearEasing

                    // At 75% in reaches 0.5f
                    0.5f at duration * 3 / 4 using LinearEasing

                    // Back to 0.75f at the end
                    0.75f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific dots to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 2,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    val scale = List(2) { index ->
        t.animateFloat(
            initialValue = 1.0f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 1.0f
                    1.0f at 0 using LinearEasing

                    // At 25% in reaches 1.3f
                    1.3f at duration / 4 using LinearEasing

                    // At 50% back to 1.0f
                    1.0f at duration / 2 using LinearEasing

                    // At 75% in reaches 0.7f
                    0.7f at duration * 3 / 4 using LinearEasing

                    // Back to 0.75f at the end
                    1.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific dots to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 2,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val dotSize = dim * 0.4f
        for (index in 0..1) {
            val offset = slide[index].value
            val alpha = alpha[index].value
            val scale = scale[index].value
            drawCircle(
                color.copy(alpha = alpha.coerceIn(0f, 1f)),
                radius = dotSize * scale / 2f,
                center = Offset(dim / 2.0f + offset * dim * 0.6f, dim / 2.0f)
            )
        }
    }
}

// ─── Flip ──────────────────────────────────────────────────────────────────────

@Composable
public fun Flip(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()
    val position by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing)))

    // First half is X flip
    val rotX = if (position <= 0.5f) -position * 360f else -180f
    // Second half is Y flip
    val rotY = if (position > 0.5f) -(position - 0.5f) * 360f else 0f

    Canvas(modifier.graphicsLayer {
        rotationX = rotX
        rotationY = rotY
    }) {
        val dim = size.width
        val sq = dim * 0.64f
        val br = dim * 0.1f
        withTransform({
            translate(dim / 2f, dim / 2f)
        }) {
            drawRoundRect(color, Offset(-sq / 2f, -sq / 2f), Size(sq, sq), CornerRadius(br, br))
        }
    }
}

// ─── Gather ────────────────────────────────────────────────────────────────────

@Composable
public fun Gather(
    modifier: Modifier,
    color: Color,
    duration: Int = 1600,
) {
    val t = rememberInfiniteTransition()

    val pull =
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = 0.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using LinearEasing

                    // At 30% in, value is 1.0f
                    1.0f at duration * 3 / 10 using LinearEasing

                    // Stays at 1.0f until 60% in
                    1.0f at duration * 6 / 10 using LinearEasing

                    // Back to 0.0f at the end
                    0.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            )
        )

    val turn =
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using LinearEasing

                    // Stays at 0.0f until 30% in
                    0.0f at duration * 3 / 10 using LinearEasing

                    // Goes to 90.0f at 60% in
                    90.0f at duration * 6 / 10 using LinearEasing

                    // Stays at 90.0f til the end
                    90.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            )
        )

    Canvas(modifier) {//.background(Color.Red)) {
        val dim = size.width
        val defaultBlockGap = dim * 0.24f
        val blockSize = (dim - defaultBlockGap) / 2f
        val blockCornerRadius = dim * 0.14f
        val pullA = pull.value * blockSize / 3.0f

        withTransform({
            rotate(turn.value)
            translate(dim / 2f, dim / 2f)
        }) {
            listOf(1f to 1f, -1f to 1f, 1f to -1f, -1f to -1f).forEach { (dx, dy) ->
                val x = dx * (blockSize / 2f + defaultBlockGap / 2f - pullA / 2f) - blockSize / 2f
                val y = dy * (blockSize / 2f + defaultBlockGap / 2f - pullA / 2f) - blockSize / 2f
                drawRoundRect(color, Offset(x, y), Size(blockSize, blockSize),
                    CornerRadius(blockCornerRadius, blockCornerRadius))
            }
        }
    }
}

// ─── Leap ──────────────────────────────────────────────────────────────────────

@Composable
public fun Leap(
    modifier: Modifier,
    color: Color,
    duration: Int = 1800,
) {
    val t = rememberInfiniteTransition()

    val translate = List(3) { index ->
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = -2.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using LinearEasing

                    // Stays at 0.0f until 33% in
                    0.0f at duration / 3 using LinearEasing

                    // Goes to 1.0f at 66% in
                    1.0f at duration * 2 / 3 using LinearEasing

                    // Goes to 2.0f at the end
                    2.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific dots to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    val rotate = List(3) { index ->
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = 2.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using LinearEasing

                    // Goes to 180.0f at 33% in
                    180.0f at duration / 3 using LinearEasing

                    // Stays at 180.0f until the end
                    180.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run). Note the
                // usage of FastForward to force the specific dots to start straight away
                // into their respective time offsets
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val dotDiameter = dim * 0.24f
        val gap = (dim - 3 * dotDiameter) / 2f

        for (index in 0..2) {
            withTransform({
                rotate(rotate[index].value)
            }) {
                val offsetX = translate[index].value
                drawCircle(
                    color = color,
                    radius = dotDiameter / 2f,
                    center = Offset(x = offsetX * (dotDiameter + gap) + dotDiameter / 2f, y = dim / 2f))
            }
        }
    }
}

// ─── LinearDots ────────────────────────────────────────────────────────────────

@Composable
public fun LinearDots(
    modifier: Modifier,
    color: Color,
    duration: Int = 900,
) {
    val t = rememberInfiniteTransition()

    val fade = List(3) { index ->
        t.animateFloat(
            initialValue = 1.0f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 1.0f
                    1.0f at 0 using LinearEasing

                    // Goes to 0.5f at 66% in
                    0.5f at duration * 2 / 3 using LinearEasing

                    // Goes back to 1.0f at the end
                    1.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run).
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.Delay
                )
            )
        )
    }

    Canvas(modifier) {
        val dim = size.width
        val dotDiameter = dim * 0.24f
        val gap = (dim - 3 * dotDiameter) / 2f

        for (index in 0..2) {
            val alpha = fade[index].value
            drawCircle(
                color.copy(alpha = alpha.coerceIn(0f, 1f)),
                radius = dotDiameter / 2f,
                center = Offset(index * (dotDiameter + gap), dim / 2f)
            )
        }
    }
}

// ─── Loading ───────────────────────────────────────────────────────────────────

@Composable
public fun Loading(
    modifier: Modifier,
    color: Color,
    duration: Int = 1000,
) {
    val t = rememberInfiniteTransition()
    val highlight by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing)))

    val segments = listOf(
        IntOffset(10, 2), IntOffset(6, 0), IntOffset(2, 2), IntOffset(0, 6),
        IntOffset(2, 10), IntOffset(6, 12), IntOffset(10, 10), IntOffset(12, 6))

    Canvas(modifier) {
        val dim = size.width
        val cellSize = dim / 15.0f
        segments.forEachIndexed { index, (x, y) ->
            val distanceFromHighlight = ((highlight + index / 8f) % 1f)
            val alpha = 1f - distanceFromHighlight * 0.8f
            drawRect(
                color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                topLeft = Offset(x * cellSize, y * cellSize),
                Size(cellSize, cellSize))
            drawRect(
                color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                topLeft = Offset((x + 2) * cellSize, y * cellSize),
                Size(cellSize, cellSize))
            drawRect(
                color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                topLeft = Offset(x * cellSize, (y + 2) * cellSize),
                Size(cellSize, cellSize))
            drawRect(
                color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                topLeft = Offset((x + 2) * cellSize, (y + 2) * cellSize),
                Size(cellSize, cellSize))
        }
    }
}

// ─── Morph ─────────────────────────────────────────────────────────────────────

@Composable
public fun Morph(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()

    val cornerRadiusFactor =
        t.animateFloat(
            initialValue = 0.12f,
            targetValue = 0.12f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.08f
                    0.08f at 0 using EaseIn

                    // Goes to 0.5f halfway in
                    0.5f at duration * 3 / 10 using EaseIn

                    // Goes back to 0.08f at the end
                    0.08f at duration using EaseOut
                },
                repeatMode = RepeatMode.Restart,
            )
        )

    val rotate =
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = 90.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using EaseIn

                    // Goes to 45.0f at 50%
                    45.0f at duration / 2 using EaseIn

                    // Goes to 90.0f at the end
                    90.0f at duration using EaseOut
                },
                repeatMode = RepeatMode.Restart,
            )
        )

    Canvas(modifier) {
        val dim = size.width
        val sq = dim * 0.64f
        val cornerRadius = cornerRadiusFactor.value * sq
        withTransform({
            rotate(rotate.value)
            translate(dim / 2f, dim / 2f)
        }) {
            drawRoundRect(
                color = color,
                topLeft = Offset(-sq / 2f, -sq / 2f),
                size = Size(sq, sq),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius))
        }
    }
}

// ─── Orbit ─────────────────────────────────────────────────────────────────────

@Composable
public fun Orbit(
    modifier: Modifier,
    color: Color,
    duration: Int = 750,
    easing: Easing = LinearEasing,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val dim = size.width
        val strokeWidth = 2.0.dp.toPx()
        val dotR = dim / 8.0f

        drawCircle(color, radius = dotR, center = Offset(dim / 2f, dim / 2f))
        withTransform({
            rotate(angle)
        }) {
            val sweepGradientBrush = Brush.sweepGradient(
                0.0f to color.copy(alpha = 0.0f),
                0.5f to color.copy(alpha = 0.0f),
                1.0f to color
            )
            drawArc(
                brush = sweepGradientBrush,
                startAngle = 0.0f,
                sweepAngle = 360.0f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
        }
    }
}

// ─── Pulse ─────────────────────────────────────────────────────────────────────

@Composable
public fun Pulse(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()
    val position by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = EaseOut)))

    val alpha = 0.4f * (1f - position)
    val sc = 0.25f + position * 0.75f

    Canvas(modifier) {
        val dim = size.width
        val dotR = dim / 8.0f
        drawCircle(
            color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
            radius = dim / 2f * sc,
            style = Fill)
        drawCircle(color, radius = dotR)
    }
}

// ─── Radar ─────────────────────────────────────────────────────────────────────

@Composable
public fun Radar(
    modifier: Modifier,
    color: Color,
    duration: Int = 1500,
    easing: Easing = LinearEasing,
) {
    val t = rememberInfiniteTransition()
    val beamAngle by t.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val dim = size.width
        val beamStart = dim / 5.0f

        drawCircle(color.copy(alpha = 0.2f), radius = dim / 2.0f)
        drawCircle(color.copy(alpha = 0.2f), radius = dim / 3.0f, style = Stroke(2.5.dp.toPx()))
        withTransform({
           rotate(beamAngle)
        }) {
            val path = Path().apply {
                moveTo(x = dim / 2.0f + beamStart, y = dim / 2.0f)
                lineTo(x = dim, y = dim / 2.0f)
                // outer arc
                arcTo(
                    rect = Rect(
                        offset = Offset.Zero,
                        size = Size(width = size.width, height = size.height)
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(x = dim / 2.0f, y = dim / 2.0f + beamStart)
                // inner arc
                arcTo(
                    rect = Rect(
                        offset = Offset(x = dim / 2.0f - beamStart, y = dim / 2.0f - beamStart),
                        size = Size(width = 2.0f * beamStart, height = 2.0f * beamStart)
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )
                close()
            }
            val beamBrush = Brush.radialGradient(
                colorStops = arrayOf(
                    0.0f to color,
                    0.4f to color,
                    1.0f to color.copy(alpha = 0.0f)
                ),
                center = center,
                radius = dim / 2.0f
            )
            drawPath(path, beamBrush)
        }
        drawCircle(color, radius = dim / 8.0f)
    }
}

// ─── Ring ──────────────────────────────────────────────────────────────────────

@Composable
public fun Ring(
    modifier: Modifier,
    color: Color,
    duration: Int = 800,
    easing: Easing = LinearEasing,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        0f,
        360f,
        infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val radius = size.width / 2f
        val strokeWidth = 2.5.dp.toPx()
        val circumference = 2f * PI.toFloat() * radius
        val dash = 18.0f * circumference / (18.0f + 44.8f)

        drawCircle(color.copy(alpha = 0.2f), radius = radius, style = Stroke(strokeWidth))
        withTransform({ rotate(angle) }) {
            drawArc(
                color = color, startAngle = 0f, sweepAngle = 360f, useCenter = false,
                style = Stroke(
                    strokeWidth,
                    cap = cap,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, circumference - dash))
                ),
            )
        }
    }
}

// ─── Ripple ────────────────────────────────────────────────────────────────────

public enum class RippleDirection { In, Out }

@Composable
public fun Ripple(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
    direction: RippleDirection = RippleDirection.Out,
) {
    val t = rememberInfiniteTransition()
    val position by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing)))

    Canvas(modifier) {
        val dim = size.width

        for (i in 0..2) {
            val sp = ((position + i.toFloat() / 3f) % 1f)
            val sc = if (direction == RippleDirection.Out) sp else 1f - sp
            val strokeWidth = (1.0.dp + sc.dp).toPx()
            val alpha = if (direction == RippleDirection.Out) 1f - sp else sp
            drawCircle(color.copy(alpha = alpha.coerceIn(0f, 1f)), radius = dim / 2f * sc, style = Stroke(strokeWidth))
        }
    }
}

// ─── Slide ─────────────────────────────────────────────────────────────────────

@Composable
public fun Slide(
    modifier: Modifier,
    color: Color,
    duration: Int = 2400,
) {
    val t = rememberInfiniteTransition()

    val slideX = List(3) { index ->
        t.animateFloat(
            initialValue = 1.0f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 1.0f
                    1.0f at 0 using LinearEasing

                    // Until 25% in, value stays at 1.0f
                    1.0f at duration / 4 using LinearEasing

                    // At 33% in, value goes to 0.0f
                    0.0f at duration / 3 using LinearEasing

                    // And stays at 0.0f until 75% in
                    0.0f at duration * 3 / 4 using LinearEasing

                    // At 83% in (5/6ths), value goes to 1.0f
                    1.0f at duration * 5 / 6 using LinearEasing

                    // And stays at 1.0f until the end
                    1.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run).
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }

    val slideY = List(3) { index ->
        t.animateFloat(
            initialValue = 0.0f,
            targetValue = 0.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.0f
                    0.0f at 0 using LinearEasing

                    // Goes to 1.0f at 8% (1/12ths)
                    1.0f at duration / 12 using LinearEasing

                    // And stays at 1.0f until 50% in
                    1.0f at duration / 2 using LinearEasing

                    // At 58% in (7/12ths), value goes to 0.0f
                    0.0f at duration * 7 / 12 using LinearEasing

                    // And stays at 0.0f until the end
                    0.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run).
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 3,
                    offsetType = StartOffsetType.FastForward
                )
            )
        )
    }


    Canvas(modifier) {
        val dim = size.width
        val dotSize = dim * 0.34f
        val gap = dim * 0.2f
        val padding = (dim - 2.0f * dotSize - gap) / 2.0f

        for (index in 0..2) {
            val offsetX = slideX[index].value
            val offsetY = slideY[index].value
            val x = padding + offsetX * (dotSize + gap) + dotSize / 2.0f
            val y = padding + offsetY * (dotSize + gap) + dotSize / 2.0f
            drawCircle(color, radius = dotSize / 2f, center = Offset(x, y))
        }
    }
}

// ─── Snake ─────────────────────────────────────────────────────────────────────

@Composable
public fun Snake(
    modifier: Modifier,
    color: Color,
    duration: Int = 1400,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()

    // Our snake does two full rotations in the allotted duration
    val lead by t.animateFloat(
        initialValue = 0f,
        targetValue = 720f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                // Total duration of one full cycle
                durationMillis = duration

                // At 0ms, value is 0.0f
                0.0f at 0 using LinearEasing

                // Goes to 540.0f at 50%
                540.0f at duration * 2 using LinearEasing

                // Goes to 720.0f at the end
                720.0f at duration using LinearEasing
            },
            repeatMode = RepeatMode.Restart,
        )
    )

    val span =
        t.animateFloat(
            initialValue = 1.0f,
            targetValue = 45.0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 1.0f
                    15.0f at 0 using LinearEasing

                    // Goes to 270.0f at 50%
                    270.0f at duration / 2 using LinearEasing

                    // Goes back to 1.0f to the end
                    15.0f at duration using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            )
        )

    Canvas(modifier) {
        val radius = size.width / 2f
        val strokeWidth = 2.5.dp.toPx()
        val circumference = 2f * PI.toFloat() * radius

        val inter = (span.value / 360.0f) * circumference

        withTransform({
            rotate(lead)
        }) {
            drawArc(
                color = color,
                startAngle = -span.value,
                sweepAngle = 360.0f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = cap,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(inter, circumference - inter),
                    )
                )
            )
        }
    }
}

// ─── Swirl ─────────────────────────────────────────────────────────────────────

@Composable
public fun Swirl(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
) {
    val t = rememberInfiniteTransition()
    val highlight by t.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = duration, easing = LinearEasing)))

    val ring = listOf(0, 1, 2, 7, null, 3, 6, 5, 4)
    val cellCount = 8

    Canvas(modifier) {
        val dim = size.width
        val gap = dim * 5.0f / 32.0f
        val padding = dim / 16.0f
        val cellSize = (dim - 2 * padding - 2 * gap) / 3f
        val cornerRadius = dim / 16.0f

        ring.forEachIndexed { index, gridPosition ->
            if (gridPosition == null) return@forEachIndexed

            val adjustedGridPosition = cellCount - gridPosition - 1
            val x = padding + (index % 3) * (cellSize + gap)
            val y = padding + (index / 3) * (cellSize + gap)

            val distanceFromHighlight = ((highlight + adjustedGridPosition.toFloat() / cellCount.toFloat()) % 1f)
            val alpha = 1f - distanceFromHighlight * 0.8f
            drawRoundRect(
                color = color.copy(alpha = alpha.coerceIn(0f, 1f)),
                topLeft = Offset(x, y),
                size = Size(cellSize, cellSize),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )
        }
    }
}

// ─── Trace ─────────────────────────────────────────────────────────────────────

@Composable
public fun Trace(
    modifier: Modifier,
    color: Color,
    duration: Int = 1200,
    easing: Easing = LinearEasing,
    cap: StrokeCap = StrokeCap.Round,
) {
    val t = rememberInfiniteTransition()
    val angle by t.animateFloat(
        0f,
        360f,
        infiniteRepeatable(tween(durationMillis = duration, easing = easing))
    )

    Canvas(modifier) {
        val dim = size.width

        val strokeWidth = 2.5.dp.toPx()
        val side = dim - strokeWidth
        val cornerRadius = 0.2f * dim

        val perimeter = 4f * (side - 2 * cornerRadius) + 2f * PI.toFloat() * cornerRadius
        val dash = 0.8f * dim

        drawRoundRect(
            color = color.copy(alpha = 0.2f),
            size = Size(side, side),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            style = Stroke(strokeWidth)
        )
        drawRoundRect(
            color = color,
            size = Size(side, side),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            style = Stroke(
                width = strokeWidth,
                cap = cap,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(dash, perimeter - dash),
                    phase = -(angle / 360.0f) * perimeter)
            )
        )
    }
}

// ─── Wave ──────────────────────────────────────────────────────────────────────

public enum class WaveOrigin { Bottom, Center }

@Composable
public fun Wave(
    modifier: Modifier,
    color: Color,
    duration: Int = 900,
    origin: WaveOrigin = WaveOrigin.Center,
) {
    val t = rememberInfiniteTransition()

    val rise = List(5) { index ->
        t.animateFloat(
            initialValue = 0.3f,
            targetValue = 0.3f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    // Total duration of one full cycle
                    durationMillis = duration

                    // At 0ms, value is 0.3f
                    0.3f at 0 using EaseIn

                    // Goes to 1.0f at 50% in
                    1.0f at duration / 2 using EaseIn

                    // Goes back to 0.3f at the end
                    0.3f at duration using EaseOut
                },
                repeatMode = RepeatMode.Restart,
                // Set the initial start delay (only applies to the very first run).
                initialStartOffset = StartOffset(
                    offsetMillis = index * duration / 5,
                    offsetType = StartOffsetType.Delay
                )
            )
        )
    }


    Canvas(modifier) {
        val dim = size.width
        val barWidth = dim * 0.12f
        val gap = (dim - 5 * barWidth) / 6f

        for (index in 0..4) {
            val barHeight = dim * rise[index].value
            val x = gap + index * (barWidth + gap)
            val y = if (origin == WaveOrigin.Bottom) dim - barHeight else (dim - barHeight) / 2f
            drawRoundRect(
                color = color,
                topLeft = Offset(x, y),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f))
        }
    }
}
