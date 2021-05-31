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
package org.pushingpixels.aurora.component.renderer

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.utils.*

@Immutable
private class RendererDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false,
        ultraLight = Color.White,
        extraLight = Color.White,
        light = Color.White,
        mid = Color.White,
        dark = Color.White,
        ultraDark = Color.White,
        foreground = Color.Black
    )
)

@Composable
fun AuroraRenderer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    fillAssociationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.FILL,
    borderAssociationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.BORDER,
    sides: Sides = Sides(),
    onSelect: () -> Unit,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val drawingCache = remember { RendererDrawingCache() }
    var rollover by remember { mutableStateOf(false) }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = true,
                isRollover = rollover,
                isSelected = selected,
                isPressed = isPressed
            )
        )
    }

    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val buttonShaper = AuroraSkin.buttonShaper

    // Transition for the selection state
    val selectionTransition = updateTransition(selected)
    val selectedFraction by selectionTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the rollover state
    val rolloverTransition = updateTransition(rollover)
    val rolloverFraction by rolloverTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the pressed state
    val pressedTransition = updateTransition(isPressed)
    val pressedFraction by pressedTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the enabled state
    val enabledTransition = updateTransition(true)
    val enabledFraction by enabledTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = true,
        selected = selected,
        rollover = rollover,
        pressed = isPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (transitionInfo.value != null) {
        //val tweakedDuration = AuroraSkin.animationConfig.regular
        LaunchedEffect(currentState.value) {
            //println("In launch effect!")
            val transitionFloat = Animatable(transitionInfo.value!!.from)
//            stateTransitionFloat.value = Animatable(transitionInfo.from)
//            println("******** Animating from ${transitionInfo.value!!.from} to 1.0f over ${transitionInfo.value!!.duration} ********")
//            println("******** Is running ${transitionFloat.isRunning} ********")
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
//                println("During animation $value towards $targetValue")
                modelStateInfo.updateActiveStates(value)
            }

//            println("&&&&&&& Ended with reason ${result.endReason} at ${transitionFloat.value}")
            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
                //println("******** After clear (target reached) ********")
                //modelStateInfo.dumpState(stateTransitionFloat.value)
            }
        }
    }

    Box(
        modifier = modifier
            .clickable(
                onClick = onSelect,
                interactionSource = interactionSource,
                indication = null
            )
            .pointerMoveFilter(
                onEnter = {
                    rollover = true
                    false
                },
                onExit = {
                    rollover = false
                    false
                },
                onMove = {
                    false
                }),
        contentAlignment = Alignment.CenterStart
    ) {
        // Compute the text color
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            colorSchemeAssociationKind = fillAssociationKind,
            isTextInFilledArea = true
        )

        // Populate the cached color scheme for filling the combobox
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            fillAssociationKind
        )
        // And retrieve the container fill colors
        val fillUltraLight = drawingCache.colorScheme.ultraLightColor
        val fillExtraLight = drawingCache.colorScheme.extraLightColor
        val fillLight = drawingCache.colorScheme.lightColor
        val fillMid = drawingCache.colorScheme.midColor
        val fillDark = drawingCache.colorScheme.darkColor
        val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val fillIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            borderAssociationKind
        )
        // And retrieve the border colors
        val borderUltraLight = drawingCache.colorScheme.ultraLightColor
        val borderExtraLight = drawingCache.colorScheme.extraLightColor
        val borderLight = drawingCache.colorScheme.lightColor
        val borderMid = drawingCache.colorScheme.midColor
        val borderDark = drawingCache.colorScheme.darkColor
        val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
        val borderIsDark = drawingCache.colorScheme.isDark

        val fillPainter = AuroraSkin.painters.fillPainter
        val borderPainter = AuroraSkin.painters.borderPainter

        // Compute the combined contribution of all non-disabled states -
        // ignoring ComponentState.ENABLED
        val alpha = modelStateInfo.stateContributionMap
            .filter { !it.key.isDisabled && (it.key != ComponentState.ENABLED) }
            .values.sumOf { it.contribution.toDouble() }.toFloat()

        Canvas(Modifier.matchParentSize()) {
            val width = this.size.width
            val height = this.size.height

            withTransform({
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = width,
                    bottom = height,
                    clipOp = ClipOp.Intersect
                )
            }) {
                val outline = buttonShaper.getButtonOutline(
                    width = width,
                    height = height,
                    extraInsets = 0.5f,
                    isInner = false,
                    sides = sides,
                    drawScope = this
                )

                val outlineBoundingRect = outline.bounds
                if (outlineBoundingRect.isEmpty) {
                    return@withTransform
                }

                // Populate the cached color scheme for filling the combobox
                drawingCache.colorScheme.ultraLight = fillUltraLight
                drawingCache.colorScheme.extraLight = fillExtraLight
                drawingCache.colorScheme.light = fillLight
                drawingCache.colorScheme.mid = fillMid
                drawingCache.colorScheme.dark = fillDark
                drawingCache.colorScheme.ultraDark = fillUltraDark
                drawingCache.colorScheme.isDark = fillIsDark
                drawingCache.colorScheme.foreground = textColor
                fillPainter.paintContourBackground(
                    this, this.size, outline, drawingCache.colorScheme, alpha
                )

                // Populate the cached color scheme for drawing the border
                drawingCache.colorScheme.ultraLight = borderUltraLight
                drawingCache.colorScheme.extraLight = borderExtraLight
                drawingCache.colorScheme.light = borderLight
                drawingCache.colorScheme.mid = borderMid
                drawingCache.colorScheme.dark = borderDark
                drawingCache.colorScheme.ultraDark = borderUltraDark
                drawingCache.colorScheme.isDark = borderIsDark
                drawingCache.colorScheme.foreground = textColor

                val innerOutline = if (borderPainter.isPaintingInnerOutline)
                    buttonShaper.getButtonOutline(
                        width = width,
                        height = height,
                        extraInsets = 1.0f,
                        isInner = true,
                        sides = sides,
                        drawScope = this
                    ) else null

                borderPainter.paintBorder(
                    this, this.size, outline, innerOutline, drawingCache.colorScheme, alpha
                )
            }
        }

        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            content()
        }
    }
}