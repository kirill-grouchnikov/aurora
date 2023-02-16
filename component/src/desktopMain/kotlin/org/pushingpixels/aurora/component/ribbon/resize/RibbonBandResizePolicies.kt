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

import org.pushingpixels.aurora.component.ribbon.FlowRibbonBand
import org.pushingpixels.aurora.component.ribbon.RibbonBand
import org.pushingpixels.aurora.component.ribbon.RibbonTask

/**
 * Defines the resize policies for the [RibbonBand]s and [FlowRibbonBand]s.
 *
 * The resize policy defines a single visual state of the given ribbon band. For
 * every control in the specific ribbon band (command button, gallery etc), the
 * resize policy defines what is its presentation state.
 *
 * The resize policies are passed to the ribbon band constructor. The order
 * of the resize policies in this list is important. The first entry in the list
 * must be the most permissive policy that returns the largest value from its
 * [.getPreferredWidth]. Each successive entry in the list must
 * return the value smaller than its predecessors.
 *
 * As the ribbon horizontal size is changed (by the user resizing the
 * application window), the ribbon task resize sequencing policy set on
 * [RibbonTask]
 * determines the order of ribbon bands to shrink / expand. See more details in
 * the documentation of the [RibbonBandResizeSequencingPolicy].
 *
 * The [CoreRibbonResizePolicies] provides a number of built in resize
 * policies that respect the application element priorities passed to ribbon commands
 * and galleries on [RibbonBand]s. There are three types of built in resize policies:
 *
 * 1. Resize policies for the [FlowRibbonBand]s. The [CoreRibbonResizePolicies.FlowTwoRows]
 * and [CoreRibbonResizePolicies.FlowThreeRows] allow placing the flow ribbon band content in two
 * and three rows respectively.
 * 2. Resize policies for the [RibbonBand]s. The
 * [CoreRibbonResizePolicies.BaseCoreRibbonBandResizePolicy] is the base class for these policies.
 * These policies respect the presentation priority associated on
 * command buttons and ribbon galleries in [.getPreferredWidth]
 * and [.install]. While [.install] call on a
 * [FlowRibbonBand] only changes the bounds of the flow components, this
 * call on a [RibbonBand] can also change the presentation state of the
 * command buttons and the number of visible buttons in the ribbon galleries.
 * 3. The collapsed policy that replaces the entire content of the ribbon band
 * with a single popup button. This is done when there is not enough horizontal
 * space to show the content of the ribbon band under the most restrictive
 * resize policy. Activating the popup button will show the original content
 * under the most permissive resize policy in a popup. This policy is
 * implemented in the [CoreRibbonResizePolicies.IconRibbonBandResizePolicy].
 *
 * In addition to the specific resize policies, the
 * [CoreRibbonResizePolicies] provides three core resize policies lists
 * for [RibbonBand]s:
 *
 * 1. [CoreRibbonResizePolicies.getCorePoliciesPermissive]
 * returns a list that starts with a resize policy that shows all command
 * buttons in the [CommandButtonPresentationState.Big] and ribbon galleries
 * with the largest number of visible buttons, fully utilizing the available
 * screen space.
 * 2. [CoreRibbonResizePolicies.getCorePoliciesRestrictive]
 * returns a list that starts with a resize policy that respects the associated
 * ribbon element priority set on the specific components.
 * 3.  [CoreRibbonResizePolicies.getCorePoliciesNone] returns
 * a list that only has a `mirror` resize policy that respects the
 * associated ribbon element priority set on the specific components.
 *
 * Note that as mentioned above, all the three lists above have the
 * `collapsed` policy as their last element.
 *
 * In addition, the
 * [CoreRibbonResizePolicies.getCoreFlowPoliciesRestrictive]
 * returns a restrictive resize policy for [FlowRibbonBand]s. The list
 * starts with the two-row policy, goes to the three-row policy and then finally
 * to the collapsed policy.
 *
 * @author Kirill Grouchnikov
 */
interface RibbonBandResizePolicy {
    /**
     * Returns the preferred width of the associated ribbon band under the
     * specified dimensions.
     *
     * @param availableHeight The height available for the associated ribbon band.
     * @param gap The inter-component gap.
     * @return The preferred width of the associated ribbon band under the
     * specified dimensions.
     */
    fun getPreferredWidth(availableHeight: Int, gap: Int): Int

    /**
     * Installs this resize policy on the associated ribbon band. For
     * [FlowRibbonBand]s only changes the bounds of the flow components.
     * For [RibbonBand]s can also change the presentation state of the command
     * buttons and the number of visible buttons in the ribbon galleries. Note that
     * this method is for internal use only and should not be called by the
     * application code.
     *
     * @param availableHeight The height available for the associated ribbon band.
     * @param gap The inter-component gap.
     */
    fun install(availableHeight: Int, gap: Int)
}

object CoreRibbonResizePolicies {
    public fun getCorePoliciesPermissive(): List<RibbonBandResizePolicy> {
        return emptyList()
    }

    public fun getCorePoliciesRestrictive(): List<RibbonBandResizePolicy> {
        return emptyList()
    }

    public fun getCoreFlowPoliciesRestrictive(stepsToRepeat: Int): List<RibbonBandResizePolicy> {
        return emptyList()
    }
}