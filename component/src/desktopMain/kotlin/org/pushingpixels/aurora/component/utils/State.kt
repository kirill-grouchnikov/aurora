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
package org.pushingpixels.aurora.component.utils

import androidx.compose.runtime.MutableState
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ComponentStateFacet
import org.pushingpixels.aurora.theming.ModelStateInfoSnapshot

@AuroraInternalApi
class StateContributionInfo(var start: Float, var end: Float) {
    var contribution: Float

    fun updateContribution(timelinePosition: Float) {
        require((timelinePosition >= 0.0f) && (timelinePosition <= 1.0f)) {
            "Timeline position should be in 0.0-1.0 range [is $timelinePosition]"
        }
        contribution = start + timelinePosition * (end - start)
    }

    init {
        contribution = start
    }
}

@AuroraInternalApi
class ModelStateInfo(startModelState: ComponentState) {
    var stateContributionMap: MutableMap<ComponentState, StateContributionInfo>
    var activeStrength: Float

    init {
        activeStrength = 0.0f
        stateContributionMap = HashMap()
        stateContributionMap[startModelState] = StateContributionInfo(1.0f, 1.0f)
        sync()
    }

    fun sync() {
        activeStrength = 0.0f

//        activeStrength = stateContributionMap
//            .filter { (activeState, _) -> activeState.isActive }
//            .map { (_, value) -> value.contribution }
//            .fold(initial = 0.0f) { result, contribution -> result + contribution }

        for ((activeState, value) in stateContributionMap) {
            if (activeState.isActive) {
                activeStrength += value.contribution
            }
        }
        if (activeStrength > 1.0f) {
            // Can happen due to floating point rounding errors
            activeStrength = 1.0f
        }
    }

    fun strength(facet: ComponentStateFacet): Float {
        var result = 0.0f
        for ((activeState, value) in stateContributionMap) {
            if (activeState.isFacetActive(facet)) {
                result += value.contribution
            }
        }
        return result
    }

    fun clear(currModelState: ComponentState) {
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

    fun dumpState(currModelState: ComponentState, stateTransitionPosition: Float) {
        println("######")
        println("Curr state ${currModelState}, position $stateTransitionPosition")
        for ((state, currRange) in stateContributionMap) {
            println("\t $state at ${currRange.contribution} [${currRange.start}-${currRange.end}]")
        }
        println("\tActive strength $activeStrength")
        println("######")
    }

    fun dumpState() {
        println("######")
        for ((state, currRange) in stateContributionMap) {
            println("\t $state at ${currRange.contribution} [${currRange.start}-${currRange.end}]")
        }
        println("\tActive strength $activeStrength")
        println("######")
    }

    fun getSnapshot(currModelState: ComponentState): ModelStateInfoSnapshot {
        return ModelStateInfoSnapshot(
            currModelState = currModelState,
            stateContributionMap = stateContributionMap.map { (key, value) -> key to value.contribution }.toMap(),
            activeStrength = activeStrength
        )
    }
}

@AuroraInternalApi
fun StateTransitionTracker(
    modelStateInfo: ModelStateInfo,
    currentState: MutableState<ComponentState>,
    transitionInfo: MutableState<TransitionInfo?>,
    enabled: Boolean,
    selected: Boolean,
    rollover: Boolean,
    pressed: Boolean,
    duration: Int
) {
    val oldState = currentState.value
    currentState.value = ComponentState.getState(
        isEnabled = enabled,
        isRollover = rollover,
        isSelected = selected,
        isPressed = pressed
    )

    if (currentState.value != oldState) {
////        if (dump) {
//            println("******** Have new state to move to ${currentState.value} ********")
//            modelStateInfo.dumpState(stateTransitionFloat.value.value)
////        }
        // Need to transition to the new state
//        if (stateTransitionFloat.value.isRunning) {
//            println("Stopping a running animation!")
//            stateTransitionFloat.value.stop()
//        }
        val tweakedDuration: Int
        val tweakedStart: Float

        if (modelStateInfo.stateContributionMap.containsKey(currentState.value)) {
            //println("Already has new state")
            // Going to a state that is already partially active
            val transitionPosition = modelStateInfo.stateContributionMap[currentState.value]!!.contribution
            tweakedDuration = (duration * (1.0f - transitionPosition)).toInt()
            tweakedStart = transitionPosition
            //stateTransitionFloat.updateBounds(transitionPosition, 1.0f)
            //stateTransitionFloat.snapTo(transitionPosition)
//            stateTransitionFloat.value = Animatable(transitionPosition)
        } else {
            //println("Does not have new state (curr state ${currentState.value}) at ${stateTransitionFloat.value}")
            tweakedStart = 0.0f
            tweakedDuration = duration
//            stateTransitionFloat.updateBounds(0.0f, 1.0f)
//            stateTransitionFloat.snapTo(0.0f)
            //          stateTransitionFloat.value = Animatable(0.0f)
            //println("\tat ${stateTransitionFloat.value}")
        }

        // Create a new contribution map
        val newContributionMap: MutableMap<ComponentState, StateContributionInfo> = HashMap()
        if (modelStateInfo.stateContributionMap.containsKey(currentState.value)) {
            // 1. the new state goes from current value to 1.0
            // 2. the rest go from current value to 0.0
            for ((contribState, currRange) in modelStateInfo.stateContributionMap.entries) {
                val newEnd = if (contribState == currentState.value) 1.0f else 0.0f
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
            newContributionMap[currentState.value] = StateContributionInfo(0.0f, 1.0f)
        }
        modelStateInfo.stateContributionMap = newContributionMap
        modelStateInfo.sync()
////
//        //modelStateInfo.currModelState = currentState.value
//
////        if (dump) {
////            println("******** After moving to new state *****")
//            modelStateInfo.dumpState(currentState.value, stateTransitionFloat.value.value)
////        }

//        return TransitionInfo(from = stateTransitionFloat.value.value,
//        to = 1.0f,
//        duration = tweakedDuration)
//        println("Animating over $tweakedDuration from ${stateTransitionFloat.value.value} to 1.0f [${stateTransitionFloat.value.isRunning}]")
//        stateTransitionFloat.value.animateTo(
//            targetValue = 1.0f,
//            anim = FloatTweenSpec(duration = tweakedDuration),
//            onEnd = { endReason, _ ->
//                //println("Ended with reason $endReason at ${stateTransitionFloat.value}")
//                if (endReason == AnimationEndReason.TargetReached) {
//                    modelStateInfo.updateActiveStates(1.0f)
//                    modelStateInfo.clear()
//                    //println("******** After clear (target reached) ********")
//                    //modelStateInfo.dumpState(stateTransitionFloat.value)
//                }
//            }
//        )
//        LaunchedEffect(stateTransitionFloat.value) {
//            println("In launch effect!")
//            stateTransitionFloat.value = Animatable(tweakedStart)
//            println("******** Animating from ${stateTransitionFloat.value.value} to 1.0f over $tweakedDuration ********")
//            println("******** Is running ${stateTransitionFloat.value.isRunning} ********")
//            val result = stateTransitionFloat.value.animateTo(
//                targetValue = 1.0f,
//                animationSpec = tween(durationMillis = tweakedDuration)
//            ) {
//                println("During animation $value")
//                modelStateInfo.updateActiveStates(value)
//            }
//
//            println("&&&&&&& Ended with reason ${result.endReason} at ${stateTransitionFloat.value.value}")
//            if (result.endReason == AnimationEndReason.Finished) {
//                modelStateInfo.updateActiveStates(1.0f)
//                modelStateInfo.clear(currentState.value)
//                //println("******** After clear (target reached) ********")
//                //modelStateInfo.dumpState(stateTransitionFloat.value)
//            }
//        }
        transitionInfo.value = TransitionInfo(tweakedStart, 1.0f, tweakedDuration)

        //println()
    }

    //return null
//    if (stateTransitionFloat.value.isRunning) {
//        modelStateInfo.updateActiveStates(stateTransitionFloat.value.value)
////        if (dump) {
//        //println("********* [${System.currentTimeMillis()}] During animation ${stateTransitionFloat.value.value} to ${stateTransitionFloat.value.targetValue} *******")
//        //modelStateInfo.dumpState(stateTransitionFloat.value.value)
////        }
//    }
}


@AuroraInternalApi
fun StateTransitionTracker(
    modelStateInfo: ModelStateInfo,
    currentState: MutableState<ComponentState>,
    transitionInfo: MutableState<TransitionInfo?>,
    enabled: Boolean,
    selected: Boolean,
    mixed: Boolean,
    rollover: Boolean,
    pressed: Boolean,
    duration: Int
) {
    val oldState = currentState.value
    currentState.value = ComponentState.getState(
        isEnabled = enabled,
        isRollover = rollover,
        isSelected = selected,
        isMixed = mixed,
        isPressed = pressed
    )

    if (currentState.value != oldState) {
////        if (dump) {
//            println("******** Have new state to move to ${currentState.value} ********")
//            modelStateInfo.dumpState(stateTransitionFloat.value.value)
////        }
        // Need to transition to the new state
//        if (stateTransitionFloat.value.isRunning) {
//            println("Stopping a running animation!")
//            stateTransitionFloat.value.stop()
//        }
        val tweakedDuration: Int
        val tweakedStart: Float

        if (modelStateInfo.stateContributionMap.containsKey(currentState.value)) {
            //println("Already has new state")
            // Going to a state that is already partially active
            val transitionPosition = modelStateInfo.stateContributionMap[currentState.value]!!.contribution
            tweakedDuration = (duration * (1.0f - transitionPosition)).toInt()
            tweakedStart = transitionPosition
            //stateTransitionFloat.updateBounds(transitionPosition, 1.0f)
            //stateTransitionFloat.snapTo(transitionPosition)
//            stateTransitionFloat.value = Animatable(transitionPosition)
        } else {
            //println("Does not have new state (curr state ${currentState.value}) at ${stateTransitionFloat.value}")
            tweakedStart = 0.0f
            tweakedDuration = duration
//            stateTransitionFloat.updateBounds(0.0f, 1.0f)
//            stateTransitionFloat.snapTo(0.0f)
            //          stateTransitionFloat.value = Animatable(0.0f)
            //println("\tat ${stateTransitionFloat.value}")
        }

        // Create a new contribution map
        val newContributionMap: MutableMap<ComponentState, StateContributionInfo> = HashMap()
        if (modelStateInfo.stateContributionMap.containsKey(currentState.value)) {
            // 1. the new state goes from current value to 1.0
            // 2. the rest go from current value to 0.0
            for ((contribState, currRange) in modelStateInfo.stateContributionMap.entries) {
                val newEnd = if (contribState == currentState.value) 1.0f else 0.0f
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
            newContributionMap[currentState.value] = StateContributionInfo(0.0f, 1.0f)
        }
        modelStateInfo.stateContributionMap = newContributionMap
        modelStateInfo.sync()
////
//        //modelStateInfo.currModelState = currentState.value
//
////        if (dump) {
////            println("******** After moving to new state *****")
//            modelStateInfo.dumpState(currentState.value, stateTransitionFloat.value.value)
////        }

//        return TransitionInfo(from = stateTransitionFloat.value.value,
//        to = 1.0f,
//        duration = tweakedDuration)
//        println("Animating over $tweakedDuration from ${stateTransitionFloat.value.value} to 1.0f [${stateTransitionFloat.value.isRunning}]")
//        stateTransitionFloat.value.animateTo(
//            targetValue = 1.0f,
//            anim = FloatTweenSpec(duration = tweakedDuration),
//            onEnd = { endReason, _ ->
//                //println("Ended with reason $endReason at ${stateTransitionFloat.value}")
//                if (endReason == AnimationEndReason.TargetReached) {
//                    modelStateInfo.updateActiveStates(1.0f)
//                    modelStateInfo.clear()
//                    //println("******** After clear (target reached) ********")
//                    //modelStateInfo.dumpState(stateTransitionFloat.value)
//                }
//            }
//        )
//        LaunchedEffect(stateTransitionFloat.value) {
//            println("In launch effect!")
//            stateTransitionFloat.value = Animatable(tweakedStart)
//            println("******** Animating from ${stateTransitionFloat.value.value} to 1.0f over $tweakedDuration ********")
//            println("******** Is running ${stateTransitionFloat.value.isRunning} ********")
//            val result = stateTransitionFloat.value.animateTo(
//                targetValue = 1.0f,
//                animationSpec = tween(durationMillis = tweakedDuration)
//            ) {
//                println("During animation $value")
//                modelStateInfo.updateActiveStates(value)
//            }
//
//            println("&&&&&&& Ended with reason ${result.endReason} at ${stateTransitionFloat.value.value}")
//            if (result.endReason == AnimationEndReason.Finished) {
//                modelStateInfo.updateActiveStates(1.0f)
//                modelStateInfo.clear(currentState.value)
//                //println("******** After clear (target reached) ********")
//                //modelStateInfo.dumpState(stateTransitionFloat.value)
//            }
//        }
        transitionInfo.value = TransitionInfo(tweakedStart, 1.0f, tweakedDuration)

        //println()
    }

    //return null
//    if (stateTransitionFloat.value.isRunning) {
//        modelStateInfo.updateActiveStates(stateTransitionFloat.value.value)
////        if (dump) {
//        //println("********* [${System.currentTimeMillis()}] During animation ${stateTransitionFloat.value.value} to ${stateTransitionFloat.value.targetValue} *******")
//        //modelStateInfo.dumpState(stateTransitionFloat.value.value)
////        }
//    }
}

data class TransitionInfo(val from: Float, val to: Float, val duration: Int)


