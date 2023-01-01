/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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

interface RibbonBandResizeSequencingPolicy {
    /**
     * Resets this policy. Note that this method is for internal use only and
     * should not be called by the application code.
     */
    fun reset(ribbonTask: RibbonTask)

    /**
     * Returns the next ribbon band for collapse.
     *
     * @return The next ribbon band for collapse.
     */
    fun next(ribbonTask: RibbonTask): AbstractRibbonBand
}

object CoreRibbonResizeSequencingPolicies {
    /**
     * The round robin resize sequencing policy. Under this policy the ribbon
     * bands are being collapsed in a cyclic fashion, distributing the collapsed
     * pixels between the different bands.
     *
     * @author Kirill Grouchnikov
     */
    class RoundRobin : RibbonBandResizeSequencingPolicy {
        // The index of the next ribbon task for collapsing.
        private var nextIndex = 0

        override fun reset(ribbonTask: RibbonTask) {
            nextIndex = ribbonTask.bands.size - 1
        }

        override fun next(ribbonTask: RibbonTask): AbstractRibbonBand {
            val result: AbstractRibbonBand = ribbonTask.bands[nextIndex]
            nextIndex--
            if (nextIndex < 0) nextIndex = ribbonTask.bands.size - 1
            return result
        }
    }

    class CollapseFromLast: RibbonBandResizeSequencingPolicy {
        // The index of the next ribbon task for collapsing.
        private var nextIndex = 0

        override fun reset(ribbonTask: RibbonTask) {
            nextIndex = ribbonTask.bands.size - 1
        }

        override fun next(ribbonTask: RibbonTask): AbstractRibbonBand {
            val result: AbstractRibbonBand = ribbonTask.bands[nextIndex]

            // check whether the current resize policy on the returned ribbon
            // band is the last
            val resizePolicies: List<RibbonBandResizePolicy> = result.resizePolicies
//            if (result.getCurrentResizePolicy() === resizePolicies[resizePolicies.size - 1]) {
//                nextIndex--
//                if (nextIndex < 0) nextIndex = 0
//            }
            return result
        }
    }
}