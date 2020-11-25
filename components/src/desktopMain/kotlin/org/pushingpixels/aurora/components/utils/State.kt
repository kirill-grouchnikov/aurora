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
package org.pushingpixels.aurora.components.utils

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.pushingpixels.aurora.ComponentState

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

    fun dumpState(stateTransitionPosition: Float) {
        println("######")
        println("Curr state ${currModelState}, position $stateTransitionPosition")
        for ((state, currRange) in stateContributionMap) {
            println("\t $state at ${currRange.contribution} [${currRange.start}-${currRange.end}]")
        }
        println("\tActive strength $activeStrength")
        println("######")
    }
}

internal class StateTransitionTracker(
    enabled: Boolean,
    selected: Boolean,
    private val stateTransitionFloat: AnimatedFloat
) {
    var selectedState: MutableState<Boolean> = mutableStateOf(selected)
    var rolloverState: MutableState<Boolean> = mutableStateOf(false)
    var modelStateInfo: ModelStateInfo =
        ModelStateInfo(
            ComponentState.getState(
                isEnabled = enabled, isRollover = false,
                isSelected = selected, isPressed = false
            )
        )

    var currentState = ComponentState.getState(
        isEnabled = enabled,
        isRollover = false,
        isSelected = selected,
        isPressed = false
    )

    fun update(isEnabled: Boolean, isPressed: Boolean, duration: Int, dump: Boolean = false) {
        var duration = duration
        currentState = ComponentState.getState(
            isEnabled = isEnabled,
            isRollover = rolloverState.value,
            isSelected = selectedState.value,
            isPressed = isPressed
        )

        if (currentState != modelStateInfo.currModelState) {
            stateTransitionFloat.stop()
            if (dump) {
                println("******** Have new state to move to $currentState ********")
                modelStateInfo.dumpState(stateTransitionFloat.value)
            }
            // Need to transition to the new state
            if (modelStateInfo.stateContributionMap.containsKey(currentState)) {
                //println("Already has new state")
                // Going to a state that is already partially active
                val transitionPosition = modelStateInfo.stateContributionMap[currentState]!!.contribution
                duration = (duration * (1.0f - transitionPosition)).toInt()
                stateTransitionFloat.setBounds(transitionPosition, 1.0f)
                stateTransitionFloat.snapTo(transitionPosition)
            } else {
                //println("Does not have new state (curr state ${modelStateInfo.currModelState}) at ${stateTransitionFloat.value}")
                stateTransitionFloat.setBounds(0.0f, 1.0f)
                stateTransitionFloat.snapTo(0.0f)
                //println("\tat ${stateTransitionFloat.value}")
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
            if (dump) {
                println("******** After moving to new state *****")
                modelStateInfo.dumpState(stateTransitionFloat.value)
            }

            //println("Animating over $duration from ${stateTransitionFloat.value} to 1.0f")
            stateTransitionFloat.animateTo(
                targetValue = 1.0f,
                anim = FloatTweenSpec(duration = duration),
                onEnd = { endReason, endValue ->
                    //println("Ended with reason $endReason at $endValue / ${stateTransitionFloat.value}")
                    if (endReason == AnimationEndReason.TargetReached) {
                        modelStateInfo.updateActiveStates(1.0f)
                        modelStateInfo.clear()
                        //println("******** After clear (target reached) ********")
                        //modelStateInfo.dumpState(stateTransitionFloat.value)
                    }
                }
            )

            //println()
        }

        if (stateTransitionFloat.isRunning) {
            modelStateInfo.updateActiveStates(stateTransitionFloat.value)
            if (dump) {
                println("********* During animation ${stateTransitionFloat.value} to ${stateTransitionFloat.targetValue} *******")
                modelStateInfo.dumpState(stateTransitionFloat.value)
            }
        }

    }
}

internal val SelectionTransitionFraction = FloatPropKey()
internal val RolloverTransitionFraction = FloatPropKey()
internal val PressedTransitionFraction = FloatPropKey()
internal val EnabledTransitionFraction = FloatPropKey()

@Composable
internal fun getSelectedTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[SelectionTransitionFraction] = 0.0f
        }

        state(true) {
            this[SelectionTransitionFraction] = 1.0f
        }

        transition(false to true) {
            SelectionTransitionFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            SelectionTransitionFraction using tween(durationMillis = duration)
        }
    }
}

@Composable
internal fun getRolloverTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[RolloverTransitionFraction] = 0.0f
        }

        state(true) {
            this[RolloverTransitionFraction] = 1.0f
        }

        transition(false to true) {
            RolloverTransitionFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            RolloverTransitionFraction using tween(durationMillis = duration)
        }
    }
}

@Composable
internal fun getPressedTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[PressedTransitionFraction] = 0.0f
        }

        state(true) {
            this[PressedTransitionFraction] = 1.0f
        }

        transition(false to true) {
            PressedTransitionFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            PressedTransitionFraction using tween(durationMillis = duration)
        }
    }
}

@Composable
internal fun getEnabledTransitionDefinition(duration: Int): TransitionDefinition<Boolean> {
    return transitionDefinition {
        state(false) {
            this[EnabledTransitionFraction] = 0.0f
        }

        state(true) {
            this[EnabledTransitionFraction] = 1.0f
        }

        transition(false to true) {
            EnabledTransitionFraction using tween(durationMillis = duration)
        }

        transition(true to false) {
            EnabledTransitionFraction using tween(durationMillis = duration)
        }
    }
}

