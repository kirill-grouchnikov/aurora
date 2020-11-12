/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.components

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.*
import org.pushingpixels.mosaic.utils.*
import java.util.*

private val CheckboxSize = 14.dp

// This will be initialized on first usage using the getSelectedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var SelectedTransitionDefinition: TransitionDefinition<ButtonState>

// This will be initialized on first usage using the getRolloverTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var RolloverTransitionDefinition: TransitionDefinition<Boolean>

@Immutable
private class CheckBoxDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        background = Color.White, foreground = Color.Black
    ),
    val markPath: Path = Path()
)

@Composable
fun MosaicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = MosaicSkin.shapes.small,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    MosaicCheckBox(
        modifier = modifier,
        shape = shape,
        checked = checked,
        onCheckedChange = onCheckedChange,
        stateTransitionFloat = AnimatedFloat(0.0f, AnimationClockAmbient.current.asDisposableClock()),
        content = content
    )
}

@Composable
fun MosaicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = MosaicSkin.shapes.small,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    stateTransitionFloat: AnimatedFloat,
    content: @Composable RowScope.() -> Unit
) {
    val drawingCache = remember { CheckBoxDrawingCache() }

    val selectedState = remember { mutableStateOf(if (checked) ButtonState.SELECTED else ButtonState.IDLE) }
    val rolloverState = remember { mutableStateOf(false) }

    val modelStateInfo = remember {
        ModelStateInfo(
            ComponentState.getState(
                isEnabled = true, isRollover = false,
                isSelected = checked
            )
        )
    }
    val markAlpha = remember { mutableStateOf(if (checked) 1.0f else 0.0f) }

    val decorationAreaType = MosaicSkin.decorationArea.type

    // Transition for the selection state
    if (!::SelectedTransitionDefinition.isInitialized) {
        SelectedTransitionDefinition =
            getSelectedTransitionDefinition(MosaicSkin.animationConfig.short)
    }
    val selectionTransitionState = transition(
        definition = SelectedTransitionDefinition,
        initState = selectedState.value,
        toState = selectedState.value
    )
    // Transition for the rollover state
    if (!::RolloverTransitionDefinition.isInitialized) {
        RolloverTransitionDefinition =
            getRolloverTransitionDefinition(MosaicSkin.animationConfig.regular)
    }
    val rolloverTransitionState = transition(
        definition = RolloverTransitionDefinition,
        initState = rolloverState.value,
        toState = rolloverState.value
    )

    // TODO - how to trigger the state transition animation without these two transitions
    // that track the changes in selected and rollover states?
    selectionTransitionState[SelectionTransitionFraction]
    rolloverTransitionState[RolloverTransitionFraction]

    val currentState = ComponentState.getState(
        isEnabled = true,
        isRollover = rolloverState.value,
        isSelected = (selectedState.value == ButtonState.SELECTED)
    )

    var duration = MosaicSkin.animationConfig.regular
    if (currentState != modelStateInfo.currModelState) {
        stateTransitionFloat.stop()
        println("******** Have new state to move to $currentState ********")
        modelStateInfo.dumpState(stateTransitionFloat.value)
        // Need to transition to the new state
        if (modelStateInfo.stateContributionMap.containsKey(currentState)) {
            println("Already has new state")
            // Going to a state that is already partially active
            val transitionPosition = modelStateInfo.stateContributionMap[currentState]!!.contribution
            duration = (duration * (1.0f - transitionPosition)).toInt()
            stateTransitionFloat.setBounds(transitionPosition, 1.0f)
            stateTransitionFloat.snapTo(transitionPosition)
        } else {
            println("Does not have new state (curr state ${modelStateInfo.currModelState}) at ${stateTransitionFloat.value}")
            stateTransitionFloat.setBounds(0.0f, 1.0f)
            stateTransitionFloat.snapTo(0.0f)
            println("\tat ${stateTransitionFloat.value}")
        }

        // Create a new contribution map
        val newContributionMap: MutableMap<ComponentState, StateContributionInfo> = HashMap()
        if (modelStateInfo.stateContributionMap.containsKey(currentState)) {
            // 1. the new state goes from current value to 1.0
            // 2. the rest go from current value to 0.0
            for ((contribState, currRange) in modelStateInfo.stateContributionMap.entries) {
                val newEnd = if (contribState == currentState) 1.0f else 0.0f
                newContributionMap[contribState] = StateContributionInfo(
                    currRange.contribution, newEnd
                )
            }
        } else {
            // 1. all existing states go from current value to 0.0
            // 2. the new state goes from 0.0 to 1.0
            for ((contribState, currRange) in modelStateInfo.stateContributionMap.entries) {
                newContributionMap[contribState] = StateContributionInfo(
                    currRange.contribution, 0.0f
                )
            }
            newContributionMap[currentState] = StateContributionInfo(0.0f, 1.0f)
        }
        modelStateInfo.stateContributionMap = newContributionMap
        modelStateInfo.sync()

        modelStateInfo.currModelState = currentState
        println("******** After moving to new state *****")
        modelStateInfo.dumpState(stateTransitionFloat.value)

        println("Animating over $duration from ${stateTransitionFloat.value} to 1.0f")
        stateTransitionFloat.animateTo(
            targetValue = 1.0f,
            anim = FloatTweenSpec(duration = duration),
            onEnd = { endReason, endValue ->
                println("Ended with reason $endReason at $endValue / ${stateTransitionFloat.value}")
                if (endReason == AnimationEndReason.TargetReached) {
                    modelStateInfo.updateActiveStates(1.0f)
                    modelStateInfo.clear()
                    println("******** After clear (target reached) ********")
                    modelStateInfo.dumpState(stateTransitionFloat.value)
                    markAlpha.value = if (modelStateInfo.currModelState.isFacetActive(ComponentStateFacet.SELECTION)) 1.0f else 0.0f
                }
            }
        )

        println()
    }

    if (stateTransitionFloat.isRunning) {
        modelStateInfo.updateActiveStates(stateTransitionFloat.value)
        println("********* During animation ${stateTransitionFloat.value} to ${stateTransitionFloat.targetValue} *******")
        modelStateInfo.dumpState(stateTransitionFloat.value)
    }

    // The toggleable modifier is set on the checkbox mark, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    Row(
        modifier = modifier
            .pointerMoveFilter(
                onEnter = {
                    rolloverState.value = true
                    false
                },
                onExit = {
                    rolloverState.value = false
                    false
                },
                onMove = {
                    false
                })
            .toggleable(
                value = (selectedState.value == ButtonState.SELECTED),
                onValueChange = {
                    selectedState.value = if (it) ButtonState.SELECTED else ButtonState.IDLE
                    onCheckedChange.invoke(selectedState.value == ButtonState.SELECTED)
                },
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Populate the cached color scheme for filling the mark box
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.MARK_BOX
        )
        // And retrieve the mark box colors
        val fillColorStart = drawingCache.colorScheme.backgroundColorStart
        val fillColorEnd = drawingCache.colorScheme.backgroundColorEnd

        // Populate the cached color scheme for drawing the mark box border
        // based on the current model state info
        populateColorScheme(
            drawingCache.colorScheme, modelStateInfo, decorationAreaType,
            ColorSchemeAssociationKind.BORDER
        )
        // And retrieve the mark box border colors
        val borderColorStart = drawingCache.colorScheme.foregroundColor
        val borderColorEnd = drawingCache.colorScheme.foregroundColor

        // Mark color
        // TODO - fix the state and use the right color
        val markColor = MosaicSkin.colors.getColorScheme(decorationAreaType,
            ColorSchemeAssociationKind.MARK, ComponentState.SELECTED).foregroundColor

        // Checkmark alpha is the combined strength of all the
        // states that have the selection bit turned on
        markAlpha.value = modelStateInfo.stateContributionMap.filter { it.key.isFacetActive(ComponentStateFacet.SELECTION) }
            .map { it.value }
            .sumByDouble { it.contribution.toDouble() }
            .toFloat()

        println("Mark alpha $markAlpha")

        // Text color
        // TODO - fix the state
        val textColor = MosaicSkin.colors.getColorScheme(decorationAreaType,
            ColorSchemeAssociationKind.FILL, ComponentState.ENABLED).foregroundColor

        val fillPainter = MosaicSkin.painters.fillPainter
        val borderPainter = MosaicSkin.painters.borderPainter

        Canvas(modifier.wrapContentSize(Alignment.Center).size(CheckboxSize)) {
            val width = this.size.width
            val height = this.size.height

            val outline = shape.createOutline(Size(width, height), this)

            // Populate the cached color scheme for filling the button container
            drawingCache.colorScheme.backgroundStart = fillColorStart
            drawingCache.colorScheme.backgroundEnd = fillColorEnd
            drawingCache.colorScheme.foreground = textColor
            fillPainter.paintContourBackground(
                this, this.size, outline, drawingCache.colorScheme
            )

            // Populate the cached color scheme for drawing the button border
            drawingCache.colorScheme.backgroundStart = borderColorStart
            drawingCache.colorScheme.backgroundEnd = borderColorEnd
            drawingCache.colorScheme.foreground = textColor
            borderPainter.paintBorder(
                this, this.size, outline, null, drawingCache.colorScheme
            )

            // Draw the checkbox mark with the alpha that corresponds to the current
            // selection and potential transition
            val markStroke = 0.12f * width

            with(drawingCache) {
                markPath.reset()
                markPath.moveTo(0.25f * width, 0.48f * height)
                markPath.lineTo(0.48f * width, 0.73f * height)
                markPath.lineTo(0.76f * width, 0.28f * height)

                drawPath(
                    path = markPath,
                    color = markColor.copy(alpha = markAlpha.value),
                    style = Stroke(
                        width = markStroke,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
        // Unlike buttons, the rest of the content should ignore (at least for now)
        // the selected state of the checkbox for drawing the text
        Providers(AmbientTextColor provides textColor) {
            Row(
                Modifier
                    .defaultMinSizeConstraints(
                        minWidth = 0.dp,
                        minHeight = CheckboxSize
                    )
                    .padding(4.dp, 10.dp, 4.dp, 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                children = content
            )
        }
    }
}
