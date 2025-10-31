/*
 * Copyright 2020-2025 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.colorscheme

import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.DecorationAreaType

/**
 * Color scheme bundle. Defines the visual appearance of a single decoration area of a skin.
 *
 * @author Kirill Grouchnikov
 * @see DecorationAreaType
 * @see ColorSchemeAssociationKind
 * @see AuroraSkinColors
 */
class AuroraColorSchemeBundle(
    private val activeColorScheme: AuroraColorScheme,
    private val enabledColorScheme: AuroraColorScheme,
    private val disabledColorScheme: AuroraColorScheme
) {
    /**
     * Maps from component state to the alpha channel applied on color scheme.
     * This map doesn't have to contain entries for all [ComponentState]
     * instances.
     */
    private val stateAlphaMap: MutableMap<ComponentState, Float> = hashMapOf()

    /**
     * Maps from component state to the alpha channel applied on highlight color
     * scheme. This map doesn't have to contain entries for all
     * [ComponentState] instances.
     */
    private val stateHighlightAlphaMap: MutableMap<ComponentState, Float> = hashMapOf()

    /**
     * If there is no explicitly registered color scheme for pressed component
     * state, this field will contain a synthesized color scheme for the pressed
     * state.
     *
     * @see ComponentState.PressedSelected
     * @see ComponentState.PressedUnselected
     */
    private var pressedScheme: AuroraColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the disabled
     * selected component state, this field will contain a synthesized color
     * scheme for the disabled selected state.
     *
     * @see ComponentState.DisabledSelected
     */
    private var disabledSelectedScheme: AuroraColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the selected
     * component state, this field will contain a synthesized color scheme for
     * the selected state.
     *
     * @see ComponentState.Selected
     */
    private var selectedScheme: AuroraColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the rollover
     * selected component state, this field will contain a synthesized color
     * scheme for the rollover selected state.
     *
     * @see ComponentState.RolloverSelected
     */
    private var rolloverSelectedScheme: AuroraColorScheme? = null

    /**
     * Maps from color scheme association kinds to the map of color schemes.
     * Different visual parts of controls in the specific decoration are can be
     * painted with different color schemes. For example, a rollover button can
     * use a light orange scheme for the gradient fill and a dark gray scheme
     * for the border. In this case, this map will have:
     *
     *  * An entry with key [ColorSchemeAssociationKind.Fill]. This entry
     * has a map entry with key [ComponentState.Selected] and value that
     * points to the light orange scheme.
     *  * An entry with key [ColorSchemeAssociationKind.Border]. This
     * entry has a map entry with key [ComponentState.Selected] and value
     * that points to the dark gray scheme.
     */
    private val colorSchemeMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, AuroraColorScheme>> =
        hashMapOf()
    private val bestFillMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, ComponentState?>> =
        hashMapOf()

    /**
     * Returns the color scheme of the specified component in the specified
     * component state.
     *
     * @param componentState Component state.
     * @return The color scheme of the component in the specified component
     * state.
     */
    fun getColorScheme(componentState: ComponentState): AuroraColorScheme {
        var registered: AuroraColorScheme? = colorSchemeMap[ColorSchemeAssociationKind.Fill]!![componentState]
        if (registered != null) {
            return registered
        }

        // for now look for the best fit only on active states
        val bestFitForFill: MutableMap<ComponentState, ComponentState?> = bestFillMap[ColorSchemeAssociationKind.Fill]!!
        if (!bestFitForFill.containsKey(componentState)) {
            val registeredStates: Collection<ComponentState> = colorSchemeMap[ColorSchemeAssociationKind.Fill]!!.keys
            bestFitForFill[componentState] = componentState.bestFit(registeredStates)
        }
        val bestFit: ComponentState? = bestFitForFill[componentState]
        if (bestFit != null) {
            registered = colorSchemeMap[ColorSchemeAssociationKind.Fill]!![bestFit]
            if (registered != null) {
                return registered
            }
        }
        if (componentState.isFacetActive(ComponentStateFacet.Press)) {
            if (pressedScheme == null) {
                pressedScheme = activeColorScheme.shade(0.2f).saturate(0.1f)
            }
            return pressedScheme!!
        }
        if (componentState === ComponentState.DisabledSelected) {
            if (disabledSelectedScheme == null) {
                disabledSelectedScheme = activeColorScheme.blendWith(disabledColorScheme, 0.25f)
            }
            return disabledSelectedScheme!!
        }
        if (componentState === ComponentState.Selected) {
            if (selectedScheme == null) {
                selectedScheme = activeColorScheme.saturate(0.2f)
            }
            return selectedScheme!!
        }
        if (componentState === ComponentState.RolloverSelected) {
            if (rolloverSelectedScheme == null) {
                rolloverSelectedScheme = activeColorScheme.tint(0.1f).saturate(0.1f)
            }
            return rolloverSelectedScheme!!
        }
        val hardFallback: ComponentState? = componentState.hardFallback
        if (hardFallback != null) {
            return this.getColorScheme(hardFallback)
        }
        if (componentState === ComponentState.Enabled) {
            return enabledColorScheme
        }
        return if (componentState.isDisabled) {
            disabledColorScheme
        } else activeColorScheme
    }

    /**
     * Returns the active color scheme of this bundle.
     *
     * @return The active color scheme of this bundle.
     */
    fun getActiveColorScheme(): AuroraColorScheme {
        return activeColorScheme
    }

    /**
     * Returns the enabled color scheme of this bundle.
     *
     * @return The enabled color scheme of this bundle.
     */
    fun getEnabledColorScheme(): AuroraColorScheme {
        return enabledColorScheme
    }

    /**
     * Returns the disabled color scheme of this bundle.
     *
     * @return The disabled color scheme of this bundle.
     */
    fun getDisabledColorScheme(): AuroraColorScheme {
        return disabledColorScheme
    }

    /**
     * Registers the color scheme to be used for the specified visual area of
     * controls under the specified states. For example, if the light orange
     * scheme has to be used for gradient fill for rollover selected and rollover
     * controls, the parameters would be:
     *
     * `scheme`=light orange scheme
     * `associationKind`=[ColorSchemeAssociationKind.Fill]
     * `states`=[ComponentState.RolloverSelected], [ComponentState.RolloverUnselected]
     *
     * @param scheme          Color scheme.
     * @param associationKind Color scheme association kind that specifies the visual areas
     * of controls to be painted with this color scheme.
     * @param states          Component states that further restrict the usage of the
     * specified color scheme.
     */
    fun registerColorScheme(
        scheme: AuroraColorScheme,
        associationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
        vararg states: ComponentState
    ) {
        if (states.isEmpty()) {
            for (state in ComponentState.getAllStates()) {
                if (colorSchemeMap[associationKind]!!.containsKey(state)) {
                    continue
                }
                if (state.hardFallback != null) {
                    // Skip states with hard fallback - that link will be traversed in
                    // getColorScheme() logic
                    continue
                }
                colorSchemeMap[associationKind]!![state] = scheme
            }
        } else {
            for (state in states) {
                colorSchemeMap[associationKind]!![state] = scheme
            }
        }
    }

    /**
     * Returns the color scheme to be used for painting the specified visual
     * area of the component under the specified component state.
     *
     * @param associationKind Color scheme association kind.
     * @param componentState  Component state.
     * @param allowFallback   If true, this method will return a color scheme for the fallback
     * association kind.
     * @return Color scheme to be used for painting the specified visual area of
     * the component under the specified component state.
     * @see .registerColorScheme
     */
    fun getColorScheme(
        associationKind: ColorSchemeAssociationKind,
        componentState: ComponentState, allowFallback: Boolean
    ): AuroraColorScheme? {
        if (associationKind === ColorSchemeAssociationKind.Fill) {
            return this.getColorScheme(componentState)
        }
        var registered: AuroraColorScheme? = colorSchemeMap[associationKind]!![componentState]
        if (registered != null) {
            return registered
        }

        // if (componentState.isActive()) {
        // for now look for the best fit only on active states
        val bestFitForState: MutableMap<ComponentState, ComponentState?> = bestFillMap[associationKind]!!
        if (!bestFitForState.containsKey(componentState)) {
            val registeredStates: Collection<ComponentState> = colorSchemeMap[associationKind]!!.keys
            bestFitForState[componentState] = componentState.bestFit(registeredStates)
        }
        val bestFit: ComponentState? = bestFitForState[componentState]
        if (bestFit != null) {
            registered = colorSchemeMap[associationKind]!![bestFit]
            if (registered != null) return registered
        }
        if (!allowFallback) {
            return null
        }
        val fallback: ColorSchemeAssociationKind = associationKind.fallback ?: return null
        return getColorScheme(fallback, componentState, allowFallback)
    }

    /**
     * Registers an alpha channel value for the specific component states.
     *
     * @param alpha  Alpha channel value.
     * @param states Component states.
     */
    fun registerAlpha(alpha: Float, vararg states: ComponentState) {
        if (states.isEmpty()) {
            for (state in ComponentState.getAllStates()) {
                stateAlphaMap[state] = alpha
            }
        } else {
            for (state in states) {
                stateAlphaMap[state] = alpha
            }
        }
    }

    /**
     * Registers a highlight color scheme for the specific component state if
     * the component state is not `null`, or a global highlight color
     * scheme otherwise.
     *
     * @param stateHighlightScheme Highlight color scheme for the specified component state.
     * @param states               Component states. If `null`, the specified color
     * scheme will be applied for all states left unspecified.
     */
    fun registerHighlightColorScheme(stateHighlightScheme: AuroraColorScheme, vararg states: ComponentState) {
        if (states.isEmpty()) {
            for (state in ComponentState.getAllStates()) {
                if (colorSchemeMap[ColorSchemeAssociationKind.Highlight]!!.containsKey(state)) {
                    continue
                }
                if (state.isDisabled) {
                    continue
                }
                if (state === ComponentState.Enabled) {
                    continue
                }
                colorSchemeMap[ColorSchemeAssociationKind.Highlight]!![state] = stateHighlightScheme
            }
        } else {
            for (state in states) {
                colorSchemeMap[ColorSchemeAssociationKind.Highlight]!![state] = stateHighlightScheme
            }
        }
    }

    /**
     * Registers a highlight alpha channel value for the specific component states.
     *
     * @param alpha  Highlight alpha channel value.
     * @param states Component states.
     */
    fun registerHighlightAlpha(alpha: Float, vararg states: ComponentState) {
        if (states.isEmpty()) {
            for (state in ComponentState.getAllStates()) {
                stateHighlightAlphaMap[state] = alpha
            }
        } else {
            for (state in states) {
                stateHighlightAlphaMap[state] = alpha
            }
        }
    }

    /**
     * Returns the set of all component states that have non-trivial alpha
     * associated with them. Non-trivial alpha is a value that is strictly less
     * than 1.0.
     *
     * @return All component states that have associated non-trivial alpha values.
     */
    fun getStatesWithAlpha(): Set<ComponentState> {
        val result: MutableSet<ComponentState> = HashSet()
        for ((key, value) in stateAlphaMap) {
            if (value < 1.0f) {
                result.add(key)
            }
        }
        return result
    }

    /**
     * Creates a new color scheme bundle that has the same settings as this
     * color scheme bundle with the addition of applying the specified color
     * scheme transformation on all the relevant color schemes
     *
     * @param transform Color scheme transformation.
     * @return The new color scheme bundle.
     */
    fun transform(transform: (AuroraColorScheme) -> AuroraColorScheme): AuroraColorSchemeBundle {
        // transform the basic schemes
        val result = AuroraColorSchemeBundle(
            transform.invoke(activeColorScheme),
            transform.invoke(enabledColorScheme),
            transform.invoke(disabledColorScheme)
        )
        for ((key, value) in colorSchemeMap) {
            for ((subKey, subValue) in value) {
                result.colorSchemeMap[key]!![subKey] = transform.invoke(subValue)
            }
        }

        // alphas are the same
        result.stateAlphaMap.putAll(this.stateAlphaMap)

        // highlight alphas are the same
        result.stateHighlightAlphaMap.putAll(this.stateHighlightAlphaMap)
        return result
    }

    /**
     * Creates a new color scheme bundle.
     */
    init {
        for (associationKind in ColorSchemeAssociationKind.values()) {
            colorSchemeMap[associationKind] = HashMap()
        }
        for (associationKind in ColorSchemeAssociationKind.values()) {
            bestFillMap[associationKind] = HashMap()
        }
    }
}

