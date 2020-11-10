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

import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import org.pushingpixels.mosaic.AmbientTextColor
import org.pushingpixels.mosaic.ComponentState
import org.pushingpixels.mosaic.MosaicSkin
import org.pushingpixels.mosaic.colorscheme.BaseColorScheme
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme
import java.util.*


enum class ButtonState {
    IDLE, SELECTED
}

private val SelectionTransitionFraction = FloatPropKey()

interface ButtonColors {
    @Composable
    fun fillColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun borderColorScheme(selected: Boolean): MosaicColorScheme

    @Composable
    fun textColorScheme(selected: Boolean): MosaicColorScheme
}

@Composable
fun defaultButtonColors(
    fillColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedFillColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    borderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedBorderColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
    textColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.enabled,
    selectedTextColorScheme: MosaicColorScheme = MosaicSkin.colorSchemes.selected,
): ButtonColors {
    return DefaultButtonColors(
        fillColorScheme = fillColorScheme,
        selectedFillColorScheme = selectedFillColorScheme,
        borderColorScheme = borderColorScheme,
        selectedBorderColorScheme = selectedBorderColorScheme,
        textColorScheme = textColorScheme,
        selectedTextColorScheme = selectedTextColorScheme
    )
}

private class DefaultButtonColors(
    private val fillColorScheme: MosaicColorScheme,
    private val selectedFillColorScheme: MosaicColorScheme,
    private val borderColorScheme: MosaicColorScheme,
    private val selectedBorderColorScheme: MosaicColorScheme,
    private val textColorScheme: MosaicColorScheme,
    private val selectedTextColorScheme: MosaicColorScheme
) : ButtonColors {

    @Composable
    override fun fillColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedFillColorScheme else fillColorScheme
    }

    @Composable
    override fun borderColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedBorderColorScheme else borderColorScheme
    }

    @Composable
    override fun textColorScheme(selected: Boolean): MosaicColorScheme {
        return if (selected) selectedTextColorScheme else textColorScheme
    }
}

@Composable
private fun getSelectedTransitionDefinition(duration: Int): TransitionDefinition<ButtonState> {
    return transitionDefinition {
        state(ButtonState.IDLE) {
            this[SelectionTransitionFraction] = 0.0f
        }

        state(ButtonState.SELECTED) {
            this[SelectionTransitionFraction] = 1.0f
        }

        transition(ButtonState.IDLE to ButtonState.SELECTED) {
            SelectionTransitionFraction using tween(durationMillis = duration)
        }

        transition(ButtonState.SELECTED to ButtonState.IDLE) {
            SelectionTransitionFraction using tween(durationMillis = duration)
        }
    }
}

// This will be initialized on first usage using the getSelectedTransitionDefinition
// with duration animation coming from [AmbientAnimationConfig]
private lateinit var SelectedTransitionDefinition: TransitionDefinition<ButtonState>

internal class StateContributionInfo(var start: Float, var end: Float) {
    var contribution: Float

    fun updateContribution(timelinePosition: Float) {
        contribution = start + timelinePosition * (end - start)
    }

    init {
        contribution = start
    }
}

internal class ModelStateInfo(var currModelState: ComponentState) {
    var stateContributionMap: MutableMap<ComponentState, StateContributionInfo>
    var activeStrength: Float

    init {
        activeStrength = 0.0f
        stateContributionMap = HashMap()
        stateContributionMap[currModelState] = StateContributionInfo(1.0f, 1.0f)
        sync()
    }

    fun sync() {
        activeStrength = 0.0f
        for ((activeState, value) in stateContributionMap) {
            if (activeState.isActive) {
                activeStrength += value.contribution
            }
        }
    }

    fun clear() {
        stateContributionMap.clear()
        stateContributionMap[currModelState] = StateContributionInfo(1.0f, 1.0f)
        sync()
    }

    fun updateActiveStates(position: Float) {
        for (pair in stateContributionMap.values) {
            pair.updateContribution(position)
        }
        sync()
    }

    fun dumpState() {
        println("######")
        println("Curr state ${currModelState}, position ${stateTransitionFloat.value}")
        for ((state, currRange) in stateContributionMap) {
            println("\t $state at ${currRange.contribution} [${currRange.start}-${currRange.end}]")
        }
        println("\tActive strength $activeStrength")
        println("######")
    }
}

lateinit var stateTransitionFloat: AnimatedFloat

@Composable
fun MosaicToggleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    shape: Shape = MosaicSkin.shapes.regular,
    colorsSchemes: ButtonColors = defaultButtonColors(),
    content: @Composable RowScope.() -> Unit
) {
    val state = remember { InteractionState() }

    val buttonSelectedState = remember { mutableStateOf(ButtonState.IDLE) }
    val rolloverState = remember { mutableStateOf(false) }

    val modelStateInfo = remember { ModelStateInfo(
        ComponentState.getState(
            isEnabled = true, isRollover = false,
            isSelected = false
        )
    ) }

    // Transition for the selection state
    if (!::SelectedTransitionDefinition.isInitialized) {
        SelectedTransitionDefinition =
            getSelectedTransitionDefinition(MosaicSkin.animationConfig.regular)
    }
    val selectionTransitionState = transition(
        definition = SelectedTransitionDefinition,
        initState = buttonSelectedState.value,
        toState = buttonSelectedState.value
    )

    val newState = ComponentState.getState(
        isEnabled = true,
        isRollover = rolloverState.value,
        isSelected = (buttonSelectedState.value == ButtonState.SELECTED)
    )

    Box(
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
            .clickable(onClick = {
                buttonSelectedState.value = if (buttonSelectedState.value == ButtonState.IDLE) {
                    ButtonState.SELECTED
                } else {
                    ButtonState.IDLE
                }
                onClick.invoke()
            }, interactionState = state, indication = null),
        alignment = Alignment.TopStart
    ) {
        val selectionTransitionPosition = selectionTransitionState[SelectionTransitionFraction]

        var duration = 500
        if (newState != modelStateInfo.currModelState) {
            if (::stateTransitionFloat.isInitialized) {
                stateTransitionFloat.stop()
            } else {
                stateTransitionFloat = AnimatedFloat(0.0f, AnimationClockAmbient.current)
            }
            println("******** Have new state to move to $newState ********")
            modelStateInfo.dumpState()
            // Need to transition to the new state
            if (modelStateInfo.stateContributionMap.containsKey(newState)) {
                println("Already has new state")
                // Going to a state that is already partially active
                val transitionPosition = modelStateInfo.stateContributionMap[newState]!!.contribution
                duration = (duration * (1.0f - transitionPosition)).toInt()
                stateTransitionFloat.setBounds(transitionPosition, 1.0f)
            } else {
                println("Does not have new state (curr state ${modelStateInfo.currModelState}")
                stateTransitionFloat.snapTo(0.0f)
                stateTransitionFloat.setBounds(0.0f, 1.0f)
            }
            println("Animating over $duration")
            stateTransitionFloat.animateTo(
                targetValue = 1.0f,
                anim = FloatTweenSpec(duration = duration),
                onEnd =  { _, _ ->
                    modelStateInfo.updateActiveStates(1.0f)
                    modelStateInfo.clear()
                    println("******** After clear ********")
                    modelStateInfo.dumpState()
                }
            )

            // Create a new contribution map
            val newContributionMap: MutableMap<ComponentState, StateContributionInfo> = HashMap()
            if (modelStateInfo.stateContributionMap.containsKey(newState)) {
                // 1. the new state goes from current value to 1.0
                // 2. the rest go from current value to 0.0
                for ((state, currRange) in modelStateInfo.stateContributionMap.entries) {
                    val newEnd = if (state === newState) 1.0f else 0.0f
                    newContributionMap[state] = StateContributionInfo(
                        currRange.contribution, newEnd
                    )
                }
            } else {
                // 1. all existing states go from current value to 0.0
                // 2. the new state goes from 0.0 to 1.0
                for ((state, currRange) in modelStateInfo.stateContributionMap.entries) {
                    newContributionMap[state] = StateContributionInfo(
                        currRange.contribution, 0.0f
                    )
                }
                newContributionMap[newState] = StateContributionInfo(0.0f, 1.0f)
            }
            modelStateInfo.stateContributionMap = newContributionMap
            modelStateInfo.sync()

            modelStateInfo.currModelState = newState
            println("******** After moving to new state *****")
            modelStateInfo.dumpState()

            println()
        }

        if (::stateTransitionFloat.isInitialized && stateTransitionFloat.isRunning) {
            modelStateInfo.updateActiveStates(stateTransitionFloat.value)
            println("********* During animation *******")
            modelStateInfo.dumpState()
        }

        // TODO - replace with optical-based interpolation
        val fillColorStart = lerp(
            colorsSchemes.fillColorScheme(false).backgroundColorStart,
            colorsSchemes.fillColorScheme(true).backgroundColorStart,
            selectionTransitionPosition
        )
        val fillColorEnd = lerp(
            colorsSchemes.fillColorScheme(false).backgroundColorEnd,
            colorsSchemes.fillColorScheme(true).backgroundColorEnd,
            selectionTransitionPosition
        )
        val borderColorStart = lerp(
            colorsSchemes.borderColorScheme(false).foregroundColor,
            colorsSchemes.borderColorScheme(true).foregroundColor,
            selectionTransitionPosition
        )
        val borderColorEnd = lerp(
            colorsSchemes.borderColorScheme(false).foregroundColor,
            colorsSchemes.borderColorScheme(true).foregroundColor,
            selectionTransitionPosition
        )
        val textColor = lerp(
            colorsSchemes.textColorScheme(false).foregroundColor,
            colorsSchemes.textColorScheme(true).foregroundColor,
            selectionTransitionPosition
        )

        // TODO: figure out how to not create a new color scheme object on every
        // redraw
        val currBackgroundColorScheme = BaseColorScheme(
            displayName = "dummy",
            backgroundStart = fillColorStart,
            backgroundEnd = fillColorEnd,
            foreground = textColor
        )
        val currBorderColorScheme = BaseColorScheme(
            displayName = "dummy",
            backgroundStart = borderColorStart,
            backgroundEnd = borderColorEnd,
            foreground = textColor
        )
        val fillPainter = MosaicSkin.painters.fillPainter
        val borderPainter = MosaicSkin.painters.borderPainter

        Canvas(modifier.matchParentSize().padding(2.dp)) {
            val width = this.size.width
            val height = this.size.height

            val outline = shape.createOutline(Size(width, height), this)

            fillPainter.paintContourBackground(
                this, this.size, outline, currBackgroundColorScheme
            )

            borderPainter.paintBorder(
                this, this.size, outline, null, currBorderColorScheme
            )
        }
        Providers(AmbientTextColor provides textColor) {
            Row(
                Modifier
                    .defaultMinSizeConstraints(
                        minWidth = 64.dp,
                        minHeight = 36.dp
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                children = content
            )
        }
    }
}
