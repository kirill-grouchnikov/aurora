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
package org.pushingpixels.aurora.theming

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * Defines a single facet of core and custom [ComponentState]s. See Javadocs of the
 * [ComponentState] class for more information on state facets.
 *
 * @param name  Facet name.
 * @param value Facet value. This is used in the matching algorithm described in the
 * javadocs of [ComponentState]. The larger the value, the more importance is
 * given to the specific facet.
*/
class ComponentStateFacet(var name: String, value: Int) {
    var value: Int
    override fun toString(): String {
        return "$name:$value"
    }

    companion object {
        /**
         * Facet that describes the enabled bit.
         */
        val Enable = ComponentStateFacet("enable", 0)

        /**
         * Facet that describes the rollover bit.
         */
        val Rollover = ComponentStateFacet("rollover", 10)

        /**
         * Facet that describes the selection bit.
         */
        val Selection = ComponentStateFacet("selection", 10)

        /**
         * Facet that describes the press bit.
         */
        val Press = ComponentStateFacet("press", 50)

        /**
         * Facet that describes the determinate bit.
         */
        val Determinate = ComponentStateFacet("determinate", 10)

        /**
         * Facet that describes the indeterminate bit.
         */
        val Indeterminate = ComponentStateFacet("indeterminate", 10)

        /**
         * Facet that describes the mix bit.
         */
        val Mix = ComponentStateFacet("mixed", 10)
    }

    init {
        require(value >= 0) { "Facet value must be non-negative" }
        this.value = value
    }
}

/**
 * Creates a new component state.
 *
 * @param name
 * Component state name. Does not have to be unique. The name is
 * only used in the [.toString].
 * @param hardFallback
 * The fallback state that will be used in color tokens querying
 * in case [.bestFit] returns `null`
 * @param facetsOn
 * Indicates that are turned on for this state. For example, [.RolloverSelected] should pass both
 * [ComponentStateFacet.Rollover] and [ComponentStateFacet.Selection].
 * @param facetsOff
 * Indicates that are turned on for this state. For example, [.DisabledUnselected] should pass both
 * [ComponentStateFacet.Enable] and [ComponentStateFacet.Selection].
 */
class ComponentState(
    private val name: String, val hardFallback: ComponentState?,
    val enabledMatch: ComponentState? = null,
    facetsOn: Array<ComponentStateFacet>?, facetsOff: Array<ComponentStateFacet>?
) {
    /**
     * Facets that are turned on for this state. For example,
     * [.RolloverSelected] contains [ComponentStateFacet.Rollover]
     * and [ComponentStateFacet.Selection].
     */
    private val facetsTurnedOn: MutableSet<ComponentStateFacet>?

    /**
     * Facets that are turned on for this state. For example,
     * [.DisabledUnselected] contains [ComponentStateFacet.Enable]
     * and [ComponentStateFacet.Selection].
     */
    private val facetsTurnedOff: MutableSet<ComponentStateFacet>?

    private val mappingOn: MutableMap<ComponentStateFacet, Boolean>

    /**
     * Creates a new component state.
     *
     * @param name
     * Component state name. Does not have to be unique. The name is
     * only used in the [.toString].
     * @param facetsOn
     * Indicates that are turned on for this state. For example, [.RolloverSelected] should pass both
     * [ComponentStateFacet.Rollover] and [ComponentStateFacet.Selection].
     * @param facetsOff
     * Indicates that are turned on for this state. For example, [.DisabledUnselected] should pass both
     * [ComponentStateFacet.Enable] and [ComponentStateFacet.Selection].
     */
    constructor(
        name: String, facetsOn: Array<ComponentStateFacet>?,
        facetsOff: Array<ComponentStateFacet>?
    ) : this(name, null, null, facetsOn, facetsOff)

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(name)
        sb.append(" : on {")
        if (facetsTurnedOn != null) {
            var sep = ""
            for (on in facetsTurnedOn) {
                sb.append(sep)
                sep = ", "
                sb.append(on.toString())
            }
        }
        sb.append("} : off {")
        if (facetsTurnedOff != null) {
            var sep = ""
            for (off in facetsTurnedOff) {
                sb.append(sep)
                sep = ", "
                sb.append(off.toString())
            }
        }
        sb.append("}")
        return sb.toString()
    }

    /**
     * Returns indication whether `this` component state is "active"
     * under the specified facet. For example, [.RolloverSelected] will
     * return `true` for both [ComponentStateFacet.Rollover]
     * and [ComponentStateFacet.Selection].
     *
     * @param stateFacet State facet.
     * @return `true` if `this` component state is
     * "active" under the specified facet (for example,
     * [.RolloverSelected] will return `true` for both
     * [ComponentStateFacet.Rollover] and
     * [ComponentStateFacet.Selection]), `false`
     * otherwise.
     */
    fun isFacetActive(stateFacet: ComponentStateFacet): Boolean {
        val result = mappingOn[stateFacet]
        if (result != null) {
            return result
        }
        if (facetsTurnedOn != null && facetsTurnedOn.contains(stateFacet)) {
            mappingOn[stateFacet] = true
            return true
        }
        mappingOn[stateFacet] = false
        return false
    }

    /**
     * Checks whether this state is disabled. A disabled state has
     * [ComponentStateFacet.Enable] facet in its `off` set.
     *
     * @return `true` if this state is disabled, `false`
     * otherwise.
     */
    val isDisabled: Boolean
        get() = !isFacetActive(ComponentStateFacet.Enable)
    val isActive: Boolean
        get() {
            if (this === Enabled) {
                return false
            }
            return isFacetActive(ComponentStateFacet.Enable)
        }

    private fun fitValue(state: ComponentState): Int {
        var value = 0
        if (facetsTurnedOn != null) {
            for (on in facetsTurnedOn) {
                if (state.facetsTurnedOn == null) {
                    value -= on.value / 2
                } else {
                    if (state.facetsTurnedOn.contains(on)) {
                        value += on.value
                    } else {
                        value -= on.value / 2
                    }
                    if (state.facetsTurnedOff!!.contains(on)) {
                        value -= on.value
                    }
                }
            }
        }
        if (facetsTurnedOff != null) {
            for (off in facetsTurnedOff) {
                if (state.facetsTurnedOff == null) {
                    value -= off.value / 2
                } else {
                    if (state.facetsTurnedOff.contains(off)) {
                        value += off.value
                    } else {
                        value -= off.value / 2
                    }
                    if (state.facetsTurnedOn!!.contains(off)) {
                        value -= off.value
                    }
                }
            }
        }
        return value
    }

    fun bestFit(states: Collection<ComponentState>): ComponentState? {
        var bestFit: ComponentState? = null
        var bestFitValue = 0
        for (state in states) {
            if (isActive != state.isActive) {
                continue
            }
            val currFitValue = state.fitValue(this) + fitValue(state)
            if (bestFit == null) {
                bestFit = state
                bestFitValue = currFitValue
            } else {
                if (currFitValue > bestFitValue) {
                    bestFit = state
                    bestFitValue = currFitValue
                }
            }
        }
        // fit value must be positive
        return if (bestFitValue > 0) {
            bestFit
        } else null
    }

    override fun hashCode(): Int {
        if (facetsTurnedOn!!.isEmpty() && facetsTurnedOff!!.isEmpty()) {
            return 0
        }
        return if (facetsTurnedOn.isEmpty()) {
            var isFirst = true
            var result = 0
            for (off in facetsTurnedOff!!) {
                if (isFirst) {
                    result = off.hashCode().inv()
                    isFirst = false
                } else {
                    result = result and off.hashCode().inv()
                }
            }
            result
        } else {
            var isFirst = true
            var result = 0
            for (on in facetsTurnedOn) {
                if (isFirst) {
                    result = on.hashCode()
                    isFirst = false
                } else {
                    result = result and on.hashCode()
                }
            }
            for (off in facetsTurnedOff!!) {
                result = result and off.hashCode().inv()
            }
            result
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ComponentState) {
            return false
        }
        if (facetsTurnedOn!!.size != other.facetsTurnedOn!!.size) {
            return false
        }
        if (facetsTurnedOff!!.size != other.facetsTurnedOff!!.size) {
            return false
        }
        if (!facetsTurnedOn.containsAll(other.facetsTurnedOn)) {
            return false
        }
        return facetsTurnedOff.containsAll(other.facetsTurnedOff)
    }

    companion object {
        private val allStates: MutableSet<ComponentState> = HashSet()

        /**
         * Pressed selected.
         */
        val PressedSelected = ComponentState(
            name = "pressed selected",
            facetsOn = arrayOf(
                ComponentStateFacet.Selection, ComponentStateFacet.Press,
                ComponentStateFacet.Enable
            ),
            facetsOff = null
        )

        /**
         * Pressed and not selected.
         */
        val PressedUnselected = ComponentState(
            name = "pressed unselected",
            facetsOn = arrayOf(
                ComponentStateFacet.Press, ComponentStateFacet.Enable
            ),
            facetsOff = arrayOf(ComponentStateFacet.Selection)
        )

        /**
         * Pressed and indeterminate.
         */
        val PressedMixed = ComponentState(
            name = "pressed mixed",
            hardFallback = PressedSelected,
            facetsOn = arrayOf(
                ComponentStateFacet.Press, ComponentStateFacet.Enable, ComponentStateFacet.Mix
            ),
            facetsOff = null
        )

        /**
         * Selected.
         */
        val Selected = ComponentState(
            name = "selected",
            facetsOn = arrayOf(
                ComponentStateFacet.Selection,
                ComponentStateFacet.Enable
            ),
            facetsOff = null
        )

        /**
         * Selected and rolled over.
         */
        val RolloverSelected = ComponentState(
            name = "rollover selected",
            facetsOn = arrayOf(
                ComponentStateFacet.Selection,
                ComponentStateFacet.Rollover, ComponentStateFacet.Enable
            ),
            facetsOff = null
        )

        /**
         * Not selected and rolled over.
         */
        val RolloverUnselected = ComponentState(
            name = "rollover unselected",
            facetsOn = arrayOf(
                ComponentStateFacet.Rollover, ComponentStateFacet.Enable
            ),
            facetsOff = arrayOf(ComponentStateFacet.Selection)
        )

        /**
         * Not determinate and rolled over.
         */
        val RolloverMixed = ComponentState(
            name = "rollover mixed",
            hardFallback = RolloverSelected,
            facetsOn = arrayOf(
                ComponentStateFacet.Rollover, ComponentStateFacet.Enable, ComponentStateFacet.Mix
            ),
            facetsOff = null
        )

        /**
         * Determinate
         */
        val Determinate = ComponentState(
            name = "determinate",
            hardFallback = Selected,
            facetsOn = arrayOf(
                ComponentStateFacet.Enable,
                ComponentStateFacet.Determinate
            ),
            facetsOff = null
        )

        /**
         * Indeterminate
         */
        val Indeterminate = ComponentState(
            name = "indeterminate",
            hardFallback = Selected,
            facetsOn = arrayOf(ComponentStateFacet.Enable, ComponentStateFacet.Indeterminate),
            facetsOff = null
        )

        /**
         * Mixed
         */
        val Mixed = ComponentState(
            name = "mixed",
            hardFallback = Selected,
            facetsOn = arrayOf(ComponentStateFacet.Enable, ComponentStateFacet.Mix),
            facetsOff = null
        )

        /**
         * Enabled state.
         */
        val Enabled = ComponentState(
            name = "enabled",
            facetsOn = arrayOf(ComponentStateFacet.Enable),
            facetsOff = null)

        /**
         * Disabled selected.
         */
        val DisabledSelected = ComponentState(
            name = "disabled selected",
            hardFallback = null,
            enabledMatch = Selected,
            facetsOn = arrayOf(ComponentStateFacet.Selection),
            facetsOff = arrayOf(ComponentStateFacet.Enable)
        )

        /**
         * Disabled and not selected.
         */
        val DisabledUnselected = ComponentState(
            name = "disabled unselected",
            hardFallback = null,
            enabledMatch = Enabled,
            facetsOn = null,
            facetsOff = arrayOf(
                ComponentStateFacet.Enable, ComponentStateFacet.Selection
            )
        )

        /**
         * Disabled and indeterminate.
         */
        val DisabledIndeterminate = ComponentState(
            name = "indeterminate disabled",
            hardFallback = DisabledSelected,
            enabledMatch = Indeterminate,
            facetsOn = arrayOf(ComponentStateFacet.Indeterminate),
            facetsOff = arrayOf(ComponentStateFacet.Enable)
        )

        /**
         * Disabled and determinate.
         */
        val DisabledDeterminate = ComponentState(
            name = "determinate disabled",
            hardFallback = DisabledSelected,
            enabledMatch = Determinate,
            facetsOn = arrayOf(ComponentStateFacet.Determinate),
            facetsOff = arrayOf(ComponentStateFacet.Enable)
        )

        /**
         * Disabled and mixed.
         */
        val DisabledMixed = ComponentState(
            name = "mixed disabled",
            hardFallback = DisabledSelected,
            enabledMatch = Mixed,
            facetsOn = arrayOf(ComponentStateFacet.Mix),
            facetsOff = arrayOf(ComponentStateFacet.Enable)
        )

        /**
         * Returns all active component states. Note that the result will **not** contain
         * [ComponentState.Enabled].
         *
         * @return All active component states.
         */
        val activeStates: Array<ComponentState>
            get() {
                val states: MutableList<ComponentState> = mutableListOf()
                for (state in allStates) {
                    if (state.isActive) {
                        states.add(state)
                    }
                }
                return states.toTypedArray()
            }

        /**
         * Returns all component states.
         *
         * @return All component states
         */
        fun getAllStates(): Array<ComponentState> {
            return allStates.toTypedArray()
        }

        /**
         * Returns the component state that matches the specified parameters.
         *
         * @param isEnabled Enabled flag.
         * @param isRollover Rollover flag.
         * @param isSelected Selected flag.
         * @param isPressed Pressed flag.
         * @return The component state that matches the specified parameters.
         */
        fun getState(
            isEnabled: Boolean,
            isRollover: Boolean,
            isSelected: Boolean,
            isPressed: Boolean
        ): ComponentState {
            if (!isEnabled) {
                return if (isSelected) {
                    DisabledSelected
                } else DisabledUnselected
            }
            if (isPressed) {
                return if (isSelected) {
                    PressedSelected
                } else PressedUnselected
            }
            if (isSelected) {
                return if (isRollover) {
                    RolloverSelected
                } else Selected
            }
            return if (isRollover) {
                RolloverUnselected
            } else Enabled
        }

        /**
         * Returns the component state that matches the specified parameters.
         *
         * @param isEnabled Enabled flag.
         * @param isRollover Rollover flag.
         * @param isSelected Selected flag.
         * @param isPressed Pressed flag.
         * @return The component state that matches the specified parameters.
         */
        fun getState(
            isEnabled: Boolean,
            isRollover: Boolean,
            isSelected: Boolean,
            isMixed: Boolean,
            isPressed: Boolean
        ): ComponentState {
            if (!isEnabled) {
                return when {
                    isSelected -> DisabledSelected
                    isMixed -> DisabledMixed
                    else -> DisabledUnselected
                }
            }

            if (isPressed) {
                return when {
                    isSelected -> PressedSelected
                    isMixed -> PressedMixed
                    else -> PressedUnselected
                }
            }

            return when {
                isSelected -> if (isRollover) RolloverSelected else Selected
                isMixed -> if (isRollover) RolloverMixed else Mixed
                else -> if (isRollover) RolloverUnselected else Enabled
            }
        }
    }

    init {
        facetsTurnedOn = mutableSetOf()
        if (facetsOn != null) {
            facetsTurnedOn.addAll(facetsOn)
        }
        facetsTurnedOff = mutableSetOf()
        if (facetsOff != null) {
            facetsTurnedOff.addAll(facetsOff)
        }
        mappingOn = HashMap()
        allStates.add(this)
    }
}

/**
 * Allows associating different color tokens to different visual parts of UI components. For
 * example, the checkbox's checkmark can be configured with [.Mark].
 *
 * Applications can create custom instances of this class to further refine the control over the
 * painting. In this case, the custom UI delegates must be created to use these new association
 * kinds.
 *
 * @author Kirill Grouchnikov
 */
class ContainerColorTokensAssociationKind(
    /**
     * Name for this association kind.
     */
    private val name: String,
    /**
     * Fallback for this association kind. This is used when no color tokens are associated with
     * this kind. For example, [.HighlightText] specifies that its fallback is [.Highlight].
     * When the highlighted text is painted, it will try to use the color tokens associated with
     * [.HighlightText]. If none was registered, it will fall back to use the color tokens associated with
     * [.Highlight], and if that is not registered as well, will use the color tokens associated with [.Default].
     */
    val fallback: ContainerColorTokensAssociationKind?
) {
    override fun toString(): String {
        return name
    }

    companion object {
        /**
         * All known association kind values.
         */
        private val values: MutableSet<ContainerColorTokensAssociationKind> = HashSet()

        /**
         * The default visual area that is used for the inner part of most controls.
         */
        val Default = ContainerColorTokensAssociationKind("default", null)

        /**
         * Visual area of separators.
         */
        val Separator = ContainerColorTokensAssociationKind("separator", Default)

        /**
         * Fill visual area of the tabs.
         */
        val Tab = ContainerColorTokensAssociationKind("tab", Default)

        /**
         * Visual area of marks. Used for painting check marks of checkboxes and radio buttons, as
         * well as arrow icons of combo boxes, spinners and more.
         */
        val Mark = ContainerColorTokensAssociationKind("mark", Default)

        /**
         * Highlight visual areas for lists, tables, trees and menus.
         */
        val Highlight = ContainerColorTokensAssociationKind("highlight", Default)

        /**
         * Highlight visual areas for text components.
         */
        val HighlightText = ContainerColorTokensAssociationKind("highlightText", Highlight)

        /**
         * Returns all available association kinds.
         *
         * @return All available association kinds.
         */
        fun values(): Set<ContainerColorTokensAssociationKind> {
            return values.toCollection(LinkedHashSet(values.size))
        }
    }

    init {
        values.add(this)
    }
}

/**
 * Enumeration of available decoration area types.
 *
 * @author Kirill Grouchnikov
 */
class DecorationAreaType(val displayName: String) {
    override fun toString(): String {
        return displayName
    }

    companion object {
        /**
         * Title pane of top-level windows (frames, dialogs).
         */
        val TitlePane = DecorationAreaType("Title pane")

        /**
         * Toolbar.
         */
        val Toolbar = DecorationAreaType("Toolbar")

        /**
         * Any area that can be placed in the top portion of its window. Menu bar is an example of a
         * core Aurora component.
         */
        val Header = DecorationAreaType("Header")

        /**
         * Any area that can be placed in the bottom portion of its window.
         */
        val Footer = DecorationAreaType("Footer")

        /**
         * Control pane area, such as sidebars / task panes or ribbon bands.
         */
        val ControlPane = DecorationAreaType("Control pane")

        /**
         * The default decoration area type. Components placed in areas with this type do not get
         * any special background decoration painting.
         */
        val None = DecorationAreaType("None")
    }
}

/**
 * Enumerates available sides.
 *
 * @author Kirill Grouchnikov
 */
enum class Side {
    /** Leading horizontal side */
    Leading,

    /** Trailing horizontal side */
    Trailing,

    /** Top side */
    Top,

    /** Bottom side */
    Bottom
}

@Immutable
data class Sides(
    val openSides: Set<Side> = emptySet(),
    val straightSides: Set<Side> = emptySet()
) {
    companion object {
        @Stable
        val ClosedRectangle: Sides = Sides(straightSides = Side.entries.toSet())
    }
}

enum class OutlineKind {
    Surface,
    Outline
}

enum class InsetKind {
    Surface,
    Content
}

/**
 * Enumerates available background appearance strategies.
 *
 * @author Kirill Grouchnikov
 */
enum class BackgroundAppearanceStrategy {
    /** The component never paints its background */
    Never,

    /** The component only paints its background in active (rollover, selected, pressed) states. */
    Flat,

    /** The component always paints its background */
    Always
}

/**
 * Enumerates available icon filter strategies.
 *
 * @author Kirill Grouchnikov
 */
enum class IconFilterStrategy {
    /** The icon is always painted in its original appearance. */
    Original,

    /** The icon is themed based on the current text color. */
    ThemedFollowText,

    /** The icon is themed based on the color tokens that match the current component state. */
    ThemedFollowColorTokens
}

sealed class PopupPlacementStrategy(val isHorizontal: Boolean) {
    object Upward {
        data object HAlignStart : PopupPlacementStrategy(false)
        data object HAlignEnd : PopupPlacementStrategy(false)
    }

    object Downward {
        data object HAlignStart : PopupPlacementStrategy(false)
        data object HAlignEnd : PopupPlacementStrategy(false)
    }

    object CenteredVertically {
        data object HAlignStart : PopupPlacementStrategy(false)
        data object HAlignEnd : PopupPlacementStrategy(false)
    }

    object Startward {
        data object VAlignTop : PopupPlacementStrategy(true)
        data object VAlignBottom : PopupPlacementStrategy(true)
    }

    object Endward {
        data object VAlignTop : PopupPlacementStrategy(true)
        data object VAlignBottom : PopupPlacementStrategy(true)
    }
}

enum class TabContentSeparatorKind {
    /** A single line under the tabs, with a break under the currently selected tab. */
    Single,

    /** A double line under the tabs, with a break in the top line under the currently selected tab. */
    Double
}

enum class HorizontalGravity {
    /**
     * Platform-specific content gravity.
     */
    Platform,

    /**
     * Align content to the leading edge of the parent container. Leading edge is left under LTR
     * component orientation and right under RTL component orientation.
     */
    Leading,

    /**
     * Center content horizontally in the parent container.
     */
    Centered,

    /**
     * Align content to the trailing edge of the parent container. Trailing edge is right under
     * LTR component orientation and left under RTL component orientation.
     */
    Trailing
}

enum class VerticalGravity {
    /**
     * Align content to the top edge of the parent container.
     */
    Top,

    /**
     * Center content horizontally in the parent container.
     */
    Centered,

    /**
     * Align content to the bottom edge of the parent container.
     */
    Bottom
}

enum class TitleIconHorizontalGravity {
    /**
     * Platform-specific icon gravity.
     */
    Platform,

    /**
     * Do not show icon.
     */
    None,

    /**
     * Align icon next to the title text (on the left under LTR component orientation and on the
     * right under RTL component orientation).
     */
    NextToTitle,

    /**
     * Align icon on the side of the title pane opposite to that of the control buttons.
     */
    OppositeControlButtons
}

/**
 * Enumeration of available system container types.
 */
enum class SystemContainerType {
    Info,
    Warning,
    Error,
    Success
}
