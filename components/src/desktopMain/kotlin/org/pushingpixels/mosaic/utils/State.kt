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
package org.pushingpixels.mosaic.utils

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import org.pushingpixels.mosaic.ComponentState
import java.util.HashMap

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

enum class ButtonState {
    IDLE, SELECTED
}

internal val SelectionTransitionFraction = FloatPropKey()
internal val RolloverTransitionFraction = FloatPropKey()

@Composable
internal fun getSelectedTransitionDefinition(duration: Int): TransitionDefinition<ButtonState> {
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

