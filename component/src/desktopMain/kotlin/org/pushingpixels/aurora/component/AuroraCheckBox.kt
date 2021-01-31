/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.utils.getBaseOutline

@Immutable
private class CheckBoxDrawingCache(
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
    ),
    val markPath: Path = Path()
)

object CheckBoxConstants {
    val CheckboxSize = 14.dp
}

@Composable
fun AuroraCheckBox(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onTriggerSelectedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    AuroraCheckBox(
        modifier = modifier,
        selected = selected,
        onTriggerSelectedChange = onTriggerSelectedChange,
        enabled = enabled,
        interactionState = remember { InteractionState() },
        content = content
    )
}

@Composable
private fun AuroraCheckBox(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onTriggerSelectedChange: (Boolean) -> Unit,
    enabled: Boolean,
    interactionState: InteractionState,
    content: @Composable RowScope.() -> Unit
) {
    val drawingCache = remember { CheckBoxDrawingCache() }
    var rollover by remember { mutableStateOf(false) }
    // TODO - look at the correct pressed state once https://github.com/JetBrains/compose-jb/issues/295
    //  is fixed
    val isPressed = false//Interaction.Pressed in interactionState

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = enabled,
                isRollover = rollover,
                isSelected = selected,
                isPressed = isPressed
            )
        )
    }

    val markAlpha = remember { mutableStateOf(if (selected) 1.0f else 0.0f) }

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
    val enabledTransition = updateTransition(enabled)
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
        enabled = enabled,
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

    // The toggleable modifier is set on the checkbox mark, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    val decorationAreaType = AuroraSkin.decorationAreaType
    Row(
        modifier = modifier
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
                })
            .toggleable(
                value = selected,
                onValueChange = {
                    onTriggerSelectedChange.invoke(it)
                },
                enabled = enabled,
                role = Role.Checkbox,
                interactionState = interactionState,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Populate the cached color scheme for filling the mark box
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.MARK_BOX
        )

        // And retrieve the mark box colors
        val fillUltraLight = drawingCache.colorScheme.ultraLightColor
        val fillExtraLight = drawingCache.colorScheme.extraLightColor
        val fillLight = drawingCache.colorScheme.lightColor
        val fillMid = drawingCache.colorScheme.midColor
        val fillDark = drawingCache.colorScheme.darkColor
        val fillUltraDark = drawingCache.colorScheme.ultraDarkColor
        val fillIsDark = drawingCache.colorScheme.isDark

        // Populate the cached color scheme for drawing the mark box border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, currentState.value, decorationAreaType,
            ColorSchemeAssociationKind.BORDER
        )
        // And retrieve the mark box border colors
        val borderUltraLight = drawingCache.colorScheme.ultraLightColor
        val borderExtraLight = drawingCache.colorScheme.extraLightColor
        val borderLight = drawingCache.colorScheme.lightColor
        val borderMid = drawingCache.colorScheme.midColor
        val borderDark = drawingCache.colorScheme.darkColor
        val borderUltraDark = drawingCache.colorScheme.ultraDarkColor
        val borderIsDark = drawingCache.colorScheme.isDark

        // Mark color
        val markColor = getStateAwareColor(
            modelStateInfo, currentState.value,
            decorationAreaType, ColorSchemeAssociationKind.MARK
        ) { it.markColor }

        // Checkmark alpha is the combined strength of all the
        // states that have the selection bit turned on
        markAlpha.value = modelStateInfo.stateContributionMap
            .filter { it.key.isFacetActive(ComponentStateFacet.SELECTION) }
            .map { it.value }
            .sumByDouble { it.contribution.toDouble() }
            .toFloat()

        // Text color. Note that the text doesn't "participate" in state changes that
        // involve rollover, selection or pressed bits
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            skinColors = AuroraSkin.colors,
            decorationAreaType = decorationAreaType,
            isTextInFilledArea = false
        )
        val alpha = if (currentState.value.isDisabled)
            AuroraSkin.colors.getAlpha(decorationAreaType, currentState.value) else 1.0f

        val fillPainter = AuroraSkin.painters.fillPainter
        val borderPainter = AuroraSkin.painters.borderPainter

        Canvas(modifier.wrapContentSize(Alignment.Center).size(CheckBoxConstants.CheckboxSize)) {
            val width = this.size.width
            val height = this.size.height

            val outline = getBaseOutline(
                width = this.size.width,
                height = this.size.height,
                radius = 3.0f.dp.toPx(),
                straightSides = null,
                insets = 0.5f
            )

            // Populate the cached color scheme for filling the markbox
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

            // Populate the cached color scheme for drawing the markbox border
            drawingCache.colorScheme.ultraLight = borderUltraLight
            drawingCache.colorScheme.extraLight = borderExtraLight
            drawingCache.colorScheme.light = borderLight
            drawingCache.colorScheme.mid = borderMid
            drawingCache.colorScheme.dark = borderDark
            drawingCache.colorScheme.ultraDark = borderUltraDark
            drawingCache.colorScheme.isDark = borderIsDark
            drawingCache.colorScheme.foreground = textColor

            val outlineInner = if (borderPainter.isPaintingInnerOutline) getBaseOutline(
                width = this.size.width,
                height = this.size.height,
                radius = 3.0f.dp.toPx() - 1,
                straightSides = null,
                insets = 2.0f
            ) else null

            borderPainter.paintBorder(
                this, this.size, outline, outlineInner, drawingCache.colorScheme, alpha
            )

            // Draw the checkbox mark with the alpha that corresponds to the current
            // selection and potential transition
            val markStroke = 0.12f * width

            with(drawingCache) {
                markPath.reset()
                markPath.moveTo(0.25f * width, 0.48f * height)
                markPath.lineTo(0.48f * width, 0.73f * height)
                markPath.lineTo(0.76f * width, 0.28f * height)

                // Note that we apply alpha twice - once for the selected / checked
                // state or transition, and the second time based on the enabled state
                drawPath(
                    path = markPath,
                    color = markColor.copy(alpha = markAlpha.value),
                    style = Stroke(
                        width = markStroke,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    ),
                    alpha = alpha
                )
            }
        }
        // Pass our text color and model state snapshot to the children
        Providers(
            AmbientTextColor provides textColor,
            AmbientModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            Row(
                // TODO - extract paddings into a centralized location
                Modifier
                    .defaultMinSizeConstraints(minWidth = 0.dp, minHeight = CheckBoxConstants.CheckboxSize)
                    .padding(4.dp, 10.dp, 4.dp, 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}
