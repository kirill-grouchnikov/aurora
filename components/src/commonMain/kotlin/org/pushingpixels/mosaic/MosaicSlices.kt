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
package org.pushingpixels.mosaic

/**
 * Defines a single facet of core and custom [ComponentState]s. See Javadocs of the
 * [ComponentState] class for more information on state facets.
 */
class ComponentStateFacet(var name: String, value: Int) {
    var value: Int
    override fun toString(): String {
        return name + ":" + value
    }

    companion object {
        /**
         * Facet that describes the enabled bit.
         */
        val ENABLE = ComponentStateFacet("enable", 0)

        /**
         * Facet that describes the rollover bit.
         */
        val ROLLOVER = ComponentStateFacet("rollover", 10)

        /**
         * Facet that describes the selection bit.
         */
        val SELECTION = ComponentStateFacet("selection", 10)

        /**
         * Facet that describes the press bit.
         */
        val PRESS = ComponentStateFacet("press", 50)
    }

    /**
     * Creates a new facet.
     *
     * @param name  Facet name.
     * @param value Facet value. This is used in the matching algorithm described in the
     * javadocs of [ComponentState]. The larger the value, the more importance is
     * given to the specific facet.
     */
    init {
        require(value >= 0) { "Facet value must be non-negative" }
        this.value = value
    }
}

class ComponentState(
    name: String, hardFallback: ComponentState?,
    facetsOn: Array<ComponentStateFacet>?, facetsOff: Array<ComponentStateFacet>?
) {
    /**
     * Facets that are turned on for this state. For example,
     * [.ROLLOVER_SELECTED] contains [ComponentStateFacet.ROLLOVER]
     * and [ComponentStateFacet.SELECTION].
     */
    private val facetsTurnedOn: MutableSet<ComponentStateFacet>?

    /**
     * Facets that are turned on for this state. For example,
     * [.DISABLED_UNSELECTED] contains [ComponentStateFacet.ENABLE]
     * and [ComponentStateFacet.SELECTION].
     */
    private val facetsTurnedOff: MutableSet<ComponentStateFacet>?

    private val mapping: MutableMap<ComponentStateFacet, Boolean>
    private val name: String
    val hardFallback: ComponentState?

    /**
     * Creates a new component state.
     *
     * @param name
     * Component state name. Does not have to be unique. The name is
     * only used in the [.toString].
     * @param facetsOn
     * Indicates that are turned on for this state. For example, [.ROLLOVER_SELECTED] should pass both
     * [ComponentStateFacet.ROLLOVER] and [ComponentStateFacet.SELECTION].
     * @param facetsOff
     * Indicates that are turned on for this state. For example, [.DISABLED_UNSELECTED] should pass both
     * [ComponentStateFacet.ENABLE] and [ComponentStateFacet.SELECTION].
     */
    constructor(
        name: String, facetsOn: Array<ComponentStateFacet>?,
        facetsOff: Array<ComponentStateFacet>?
    ) : this(name, null, facetsOn, facetsOff) {
    }

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
     * under the specified facet. For example, [.ROLLOVER_SELECTED] will
     * return `true` for both [ComponentStateFacet.ROLLOVER]
     * and [ComponentStateFacet.SELECTION].
     *
     * @param stateFacet
     * State facet.
     * @return `true` if `this` component state is
     * "active" under the specified facet (for example,
     * [.ROLLOVER_SELECTED] will return `true` for both
     * [ComponentStateFacet.ROLLOVER] and
     * [ComponentStateFacet.SELECTION]), `false`
     * otherwise.
     */
    fun isFacetActive(stateFacet: ComponentStateFacet): Boolean {
        val result = mapping[stateFacet]
        if (result != null) {
            return result
        }
        if (facetsTurnedOn != null && facetsTurnedOn.contains(stateFacet)) {
            mapping[stateFacet] = true
            return true
        }
        mapping[stateFacet] = false
        return false
    }

    /**
     * Checks whether this state is disabled. A disabled state has
     * [ComponentStateFacet.ENABLE] facet in its `off` set.
     *
     * @return `true` if this state is disabled, `false`
     * otherwise.
     */
    val isDisabled: Boolean
        get() = !isFacetActive(ComponentStateFacet.ENABLE)
    val isActive: Boolean
        get() {
            if (this === ENABLED) {
                return false
            }
            return if (!isFacetActive(ComponentStateFacet.ENABLE)) {
                false
            } else true
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
        // / System.out.println("Fit value is " + value + " for ");
        // / System.out.println("\t" + this);
        // System.out.println("\t" + state);
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

    override fun equals(obj: Any?): Boolean {
        if (obj !is ComponentState) {
            return false
        }
        val second = obj
        if (facetsTurnedOn!!.size != second.facetsTurnedOn!!.size) {
            return false
        }
        if (facetsTurnedOff!!.size != second.facetsTurnedOff!!.size) {
            return false
        }
        if (!facetsTurnedOn.containsAll(second.facetsTurnedOn)) {
            return false
        }
        return if (!facetsTurnedOff.containsAll(second.facetsTurnedOff)) {
            false
        } else true
    }

    companion object {
        private val allStates: MutableSet<ComponentState> = HashSet()

        /**
         * Disabled selected.
         */
        val DISABLED_SELECTED = ComponentState(
            "disabled selected",
            arrayOf(ComponentStateFacet.SELECTION),
            arrayOf(ComponentStateFacet.ENABLE)
        )

        /**
         * Disabled and not selected.
         */
        val DISABLED_UNSELECTED = ComponentState(
            "disabled unselected", null, arrayOf(
                ComponentStateFacet.ENABLE, ComponentStateFacet.SELECTION
            )
        )

        /**
         * Pressed selected.
         */
        val PRESSED_SELECTED = ComponentState(
            "pressed selected", arrayOf(
                ComponentStateFacet.SELECTION, ComponentStateFacet.PRESS,
                ComponentStateFacet.ENABLE
            ), null
        )

        /**
         * Pressed and not selected.
         */
        val PRESSED_UNSELECTED = ComponentState(
            "pressed unselected", arrayOf(
                ComponentStateFacet.PRESS, ComponentStateFacet.ENABLE
            ), arrayOf(ComponentStateFacet.SELECTION)
        )

        /**
         * Selected.
         */
        val SELECTED = ComponentState(
            "selected", arrayOf(
                ComponentStateFacet.SELECTION,
                ComponentStateFacet.ENABLE
            ), null
        )

        /**
         * Selected and rolled over.
         */
        val ROLLOVER_SELECTED = ComponentState(
            "rollover selected", arrayOf(
                ComponentStateFacet.SELECTION,
                ComponentStateFacet.ROLLOVER, ComponentStateFacet.ENABLE
            ),
            null
        )

        /**
         * Not selected and rolled over.
         */
        val ROLLOVER_UNSELECTED = ComponentState(
            "rollover unselected", arrayOf(
                ComponentStateFacet.ROLLOVER, ComponentStateFacet.ENABLE
            ), arrayOf(ComponentStateFacet.SELECTION)
        )

        /**
         * Enabled state.
         */
        val ENABLED = ComponentState("enabled", arrayOf(ComponentStateFacet.ENABLE), null)

        /**
         * Returns all active component states. Note that the result will **not** contain
         * [ComponentState.ENABLED].
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
         * @param isEnabled
         * Enabled flag.
         * @param isRollover
         * Rollover flag.
         * @param isSelected
         * Selected flag.
         * @return The component state that matches the specified parameters.
         */
        fun getState(
            isEnabled: Boolean,
            isRollover: Boolean, isSelected: Boolean
        ): ComponentState {
            if (!isEnabled) {
                return if (isSelected) {
                    DISABLED_SELECTED
                } else DISABLED_UNSELECTED
            }
            if (isSelected) {
                return if (isRollover) {
                    ROLLOVER_SELECTED
                } else SELECTED
            }
            return if (isRollover) {
                ROLLOVER_UNSELECTED
            } else ENABLED
        }
    }

    /**
     * Creates a new component state.
     *
     * @param name
     * Component state name. Does not have to be unique. The name is
     * only used in the [.toString].
     * @param hardFallback
     * The fallback state that will be used in [MosaicColorSchemeBundle.getColorScheme]
     * in case [.bestFit] returns `null`
     * @param facetsOn
     * Indicates that are turned on for this state. For example, [.ROLLOVER_SELECTED] should pass both
     * [ComponentStateFacet.ROLLOVER] and [ComponentStateFacet.SELECTION].
     * @param facetsOff
     * Indicates that are turned on for this state. For example, [.DISABLED_UNSELECTED] should pass both
     * [ComponentStateFacet.ENABLE] and [ComponentStateFacet.SELECTION].
     */
    init {
        requireNotNull(name) { "Component state name must be non-null" }
        this.name = name
        this.hardFallback = hardFallback
        facetsTurnedOn = mutableSetOf()
        if (facetsOn != null) {
            facetsTurnedOn.addAll(facetsOn)
        }
        facetsTurnedOff = mutableSetOf()
        if (facetsOff != null) {
            facetsTurnedOff.addAll(facetsOff)
        }
        mapping = HashMap()
        allStates.add(this)
    }
}

/**
 * Allows associating different color schemes to different visual parts of UI components. For
 * example, the checkbox has three different visual areas:
 *
 *  * Border - assciated with [.BORDER]
 *  * Fill - associated with [.MARK_BOX]
 *  * Check mark - associated with [.MARK]
 *
 * Applications can create custom instances of this class to further refine the control over the
 * painting. In this case, the custom UI delegates must be created to use these new association
 * kinds.
 *
 * @author Kirill Grouchnikov
 */
class ColorSchemeAssociationKind(
    /**
     * Name for this association kind.
     */
    private val name: String,
    /**
     * Fallback for this association kind. This is used when no color scheme is associated with
     * this kind. For example, [.TAB_BORDER] specifies that its fallback is
     * [.BORDER]. When the tabs are painted, it will
     * try to use the color scheme associated with [.TAB_BORDER]. If none was registered,
     * it will fall back to use the color scheme associated with [.BORDER], and if that is
     * not registered as well, will use the color scheme associated with [.FILL].
     */
    val fallback: ColorSchemeAssociationKind?
) {
    override fun toString(): String {
        return name
    }

    companion object {
        /**
         * All known association kind values.
         */
        private val values: MutableSet<ColorSchemeAssociationKind> = HashSet()

        /**
         * The default visual area that is used for the inner part of most controls.
         */
        val FILL = ColorSchemeAssociationKind("fill", null)

        /**
         * Visual area of separators.
         */
        val SEPARATOR = ColorSchemeAssociationKind("separator", FILL)

        /**
         * Fill visual area of the tabs.
         */
        val TAB = ColorSchemeAssociationKind("tab", FILL)

        /**
         * Border visual area of non-tab controls.
         */
        val BORDER = ColorSchemeAssociationKind("border", FILL)

        /**
         * Visual area of marks. Used for painting check marks of checkboxes and radio buttons, as
         * well as arrow icons of combo boxes, spinners and more.
         */
        val MARK = ColorSchemeAssociationKind("mark", BORDER)

        /**
         * Visual area of mark boxes. Used for painting the box of checkboxes and radio buttons.
         */
        val MARK_BOX = ColorSchemeAssociationKind("markBox", FILL)

        /**
         * Visual area of focus indication.
         */
        val FOCUS = ColorSchemeAssociationKind("focus", MARK)

        /**
         * Border visual area of the tabs.
         */
        val TAB_BORDER = ColorSchemeAssociationKind("tabBorder", BORDER)

        /**
         * Highlight visual areas for lists, tables, trees and menus.
         */
        val HIGHLIGHT = ColorSchemeAssociationKind("highlight", FILL)

        /**
         * Highlight visual areas for text components.
         */
        val HIGHLIGHT_TEXT = ColorSchemeAssociationKind("highlightText", HIGHLIGHT)

        /**
         * Border visual areas for highlighted regions of lists, tables, trees and menus.
         */
        val HIGHLIGHT_BORDER = ColorSchemeAssociationKind("highlightBorder", BORDER)

        /**
         * Visual area of marks in highlighted regions of lists, tables, trees and menus.
         */
        val HIGHLIGHT_MARK = ColorSchemeAssociationKind("highlightMark", MARK)

        /**
         * Returns all available association kinds.
         *
         * @return All available association kinds.
         */
        fun values(): Set<ColorSchemeAssociationKind> {
            return values.toCollection(LinkedHashSet(values.size))
        }
    }

    /**
     * Creates a new association kind.
     *
     * @param name     Association kind name.
     * @param fallback Fallback association kind. This is used when no color scheme is
     * associated with this kind. For example, [.TAB_BORDER] specifies that its
     * fallback is [.BORDER]. When the tabbed pane is
     * painting the tabs, it will try to use the color scheme associated with
     * [.TAB_BORDER].
     * If none was registered, it will fall back to use the color scheme associated
     * with [.BORDER], and if that is not registered as well, will use the
     * color scheme associated with [.FILL].
     */
    init {
        values.add(this)
    }
}

