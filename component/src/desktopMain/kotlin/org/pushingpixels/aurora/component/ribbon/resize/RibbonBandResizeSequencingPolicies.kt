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
package org.pushingpixels.aurora.component.ribbon.resize

import org.pushingpixels.aurora.component.ribbon.AbstractRibbonBand
import org.pushingpixels.aurora.component.ribbon.RibbonTask

/**
 * Resize sequencing policy for [RibbonTask] content. This policy is responsible for determining
 * the order in which the task's bands get progressively less or more horizontal space as the ribbon
 * is resized.
 */
interface RibbonBandResizeSequencingPolicy {
    /**
     * The pair of ribbon band and ribbon band resize policy in this list is used to determine which
     * ribbon band should be getting less or more horizontal space as the ribbon is resized, and
     * what should this band's resize policy be at that moment.
     */
    fun getResizeSequence(ribbonTask: RibbonTask) : List<Pair<AbstractRibbonBand, RibbonBandResizePolicy>>
}

object CoreRibbonResizeSequencingPolicies {
    /**
     * The round-robin resize sequencing policy. Under this policy the ribbon
     * bands are being collapsed in a cyclic fashion starting from the last ribbon band.
     *
     * @author Kirill Grouchnikov
     */
    object RoundRobin : RibbonBandResizeSequencingPolicy {
        override fun getResizeSequence(ribbonTask: RibbonTask) : List<Pair<AbstractRibbonBand, RibbonBandResizePolicy>> {
            val result: MutableList<Pair<AbstractRibbonBand, RibbonBandResizePolicy>> = arrayListOf()
            val bands = ribbonTask.bands
            val resizePolicies: Map<AbstractRibbonBand, MutableList<RibbonBandResizePolicy>> =
                ribbonTask.bands.associateWith { ArrayList(it.resizePolicies) }
            val resizePolicyCount = ribbonTask.bands.sumOf { it.resizePolicies.size }

            var policiesLeft = resizePolicyCount
            var currentBandIndex = bands.size - 1
            while (policiesLeft > 0) {
                val currentBand: AbstractRibbonBand = bands[currentBandIndex]
                val policiesLeftForCurrentBand = resizePolicies[currentBand]!!
                if (policiesLeftForCurrentBand.isEmpty()) {
                    // Nothing left to take from this band, go to the next one in sequence
                    currentBandIndex--
                    if (currentBandIndex < 0) {
                        currentBandIndex = bands.size - 1
                    }
                    continue
                }
                val currentPolicy: RibbonBandResizePolicy = policiesLeftForCurrentBand.removeFirst()
                policiesLeft--
                result.add(Pair(currentBand, currentPolicy))
                // Go to the next band in sequence
                currentBandIndex--
                if (currentBandIndex < 0) {
                    currentBandIndex = bands.size - 1
                }
            }

            return result
        }
    }

    /**
     * The collapse from last resize sequencing policy. Under this policy the ribbon
     * bands are being collapsed starting from the last ribbon band. The current ribbon band
     * collapses its content until its own resize policies list is exhausted before going to the
     * next band.
     *
     * @author Kirill Grouchnikov
     */
    object CollapseFromLast: RibbonBandResizeSequencingPolicy {
        override fun getResizeSequence(ribbonTask: RibbonTask) : List<Pair<AbstractRibbonBand, RibbonBandResizePolicy>> {
            val result: MutableList<Pair<AbstractRibbonBand, RibbonBandResizePolicy>> = arrayListOf()

            val reversedBands: List<AbstractRibbonBand> = ribbonTask.bands.reversed()
            for (band in reversedBands) {
                result.addAll(band.resizePolicies.map { Pair(band, it) })
            }

            return result
        }
    }
}