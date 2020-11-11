package org.pushingpixels.mosaic.colorscheme

import org.pushingpixels.mosaic.ColorSchemeAssociationKind
import org.pushingpixels.mosaic.ComponentState
import org.pushingpixels.mosaic.ComponentStateFacet

/**
 * Color scheme bundle. Defines the visual appearance of a single decoration area of a skin.
 *
 * @author Kirill Grouchnikov
 * @see DecorationAreaType
 * @see ColorSchemeAssociationKind
 * @see [MosaicSkin]
 */
class MosaicColorSchemeBundle(
    activeColorScheme: MosaicColorScheme?,
    enabledColorScheme: MosaicColorScheme?,
    disabledColorScheme: MosaicColorScheme?
) {
    /**
     * The active color scheme of this bundle.
     */
    private val activeColorScheme: MosaicColorScheme

    /**
     * The enabled color scheme of this bundle.
     */
    private val enabledColorScheme: MosaicColorScheme?

    /**
     * The disabled color scheme of this bundle.
     */
    private val disabledColorScheme: MosaicColorScheme?

    /**
     * If there is no explicitly registered color scheme for pressed component
     * state, this field will contain a synthesized color scheme for the pressed
     * state.
     *
     * @see ComponentState.PRESSED_SELECTED
     *
     * @see ComponentState.PRESSED_UNSELECTED
     */
    private var pressedScheme: MosaicColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the disabled
     * selected component state, this field will contain a synthesized color
     * scheme for the disabled selected state.
     *
     * @see ComponentState.DISABLED_SELECTED
     */
    private var disabledSelectedScheme: MosaicColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the selected
     * component state, this field will contain a synthesized color scheme for
     * the selected state.
     *
     * @see ComponentState.SELECTED
     */
    private var selectedScheme: MosaicColorScheme? = null

    /**
     * If there is no explicitly registered color scheme for the rollover
     * selected component state, this field will contain a synthesized color
     * scheme for the rollover selected state.
     *
     * @see ComponentState.ROLLOVER_SELECTED
     */
    private var rolloverSelectedScheme: MosaicColorScheme? = null

    /**
     * Maps from color scheme association kinds to the map of color schemes.
     * Different visual parts of controls in the specific decoration are can be
     * painted with different color schemes. For example, a rollover button can
     * use a light orange scheme for the gradient fill and a dark gray scheme
     * for the border. In this case, this map will have:
     *
     *
     *  * An entry with key [ColorSchemeAssociationKind.FILL]. This entry
     * has a map entry with key [ComponentState.SELECTED] and value that
     * points to the light orange scheme.
     *  * An entry with key [ColorSchemeAssociationKind.BORDER]. This
     * entry has a map entry with key [ComponentState.SELECTED] and value
     * that points to the dark gray scheme.
     *
     */
    private val colorSchemeMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, MosaicColorScheme>>
    private val bestFillMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, ComponentState?>>

    /**
     * Returns the color scheme of the specified component in the specified
     * component state.
     *
     * @param componentState Component state.
     * @return The color scheme of the component in the specified component
     * state.
     */
    fun getColorScheme(componentState: ComponentState): MosaicColorScheme {
        var registered: MosaicColorScheme? = colorSchemeMap[ColorSchemeAssociationKind.FILL]!![componentState]
        if (registered != null) {
            return registered
        }

        // for now look for the best fit only on active states
        val bestFitForFill: MutableMap<ComponentState, ComponentState?> = bestFillMap[ColorSchemeAssociationKind.FILL]!!
        if (!bestFitForFill.containsKey(componentState)) {
            val registeredStates: Collection<ComponentState> = colorSchemeMap[ColorSchemeAssociationKind.FILL]!!.keys
            bestFitForFill[componentState] = componentState.bestFit(registeredStates)
        }
        val bestFit: ComponentState? = bestFitForFill[componentState]
        if (bestFit != null) {
            registered = colorSchemeMap[ColorSchemeAssociationKind.FILL]!![bestFit]
            if (registered != null) {
                return registered
            }
        }
        if (componentState.isFacetActive(ComponentStateFacet.PRESS)) {
            if (pressedScheme == null) {
                pressedScheme = activeColorScheme.shade(0.2f).saturate(0.1f)
            }
            return pressedScheme!!
        }
        if (componentState === ComponentState.DISABLED_SELECTED) {
            if (disabledSelectedScheme == null) {
                disabledSelectedScheme = activeColorScheme.blendWith(disabledColorScheme!!, 0.25f)
            }
            return disabledSelectedScheme!!
        }
        if (componentState === ComponentState.SELECTED) {
            if (selectedScheme == null) {
                selectedScheme = activeColorScheme.saturate(0.2f)
            }
            return selectedScheme!!
        }
        if (componentState === ComponentState.ROLLOVER_SELECTED) {
            if (rolloverSelectedScheme == null) {
                rolloverSelectedScheme = activeColorScheme.tint(0.1f).saturate(0.1f)
            }
            return rolloverSelectedScheme!!
        }
        val hardFallback: ComponentState? = componentState.hardFallback
        if (hardFallback != null) {
            return this.getColorScheme(hardFallback)
        }
        if (componentState === ComponentState.ENABLED) {
            return enabledColorScheme!!
        }
        return if (componentState.isDisabled) {
            disabledColorScheme!!
        } else activeColorScheme
    }

    /**
     * Returns the active color scheme of this bundle.
     *
     * @return The active color scheme of this bundle.
     */
    fun getActiveColorScheme(): MosaicColorScheme {
        return activeColorScheme
    }

    /**
     * Returns the enabled color scheme of this bundle.
     *
     * @return The enabled color scheme of this bundle.
     */
    fun getEnabledColorScheme(): MosaicColorScheme? {
        return enabledColorScheme
    }

    /**
     * Returns the disabled color scheme of this bundle.
     *
     * @return The disabled color scheme of this bundle.
     */
    fun getDisabledColorScheme(): MosaicColorScheme? {
        return disabledColorScheme
    }

    /**
     * Registers the color scheme to be used for the specified visual area of
     * controls under the specified states. For example, if the light orange
     * scheme has to be used for gradient fill for rollover selected and rollover
     * controls, the parameters would be:
     *
     *
     *  * `scheme`=light orange scheme
     *  *
     * `associationKind`=[ColorSchemeAssociationKind.FILL]
     *  *
     * `states`=[ComponentState.ROLLOVER_SELECTED],
     * [ComponentState.ROLLOVER_UNSELECTED]
     *
     *
     * @param scheme          Color scheme.
     * @param associationKind Color scheme association kind that specifies the visual areas
     * of controls to be painted with this color scheme.
     * @param states          Component states that further restrict the usage of the
     * specified color scheme.
     */
    fun registerColorScheme(
        scheme: MosaicColorScheme,
        associationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.FILL,
        vararg states: ComponentState
    ) {
        requireNotNull(scheme) { "Cannot pass null color scheme" }
        if (states == null || states.size == 0) {
            for (state in ComponentState.getAllStates()) {
                if (colorSchemeMap[associationKind]!!.containsKey(state)) {
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
    ): MosaicColorScheme? {
        if (associationKind === ColorSchemeAssociationKind.FILL) {
            return this.getColorScheme(componentState)
        }
        var registered: MosaicColorScheme? = colorSchemeMap[associationKind]!![componentState]
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
     * Creates a new color scheme bundle.
     *
     * @param activeColorScheme   The active color scheme of this bundle.
     * @param enabledColorScheme  The enabled color scheme of this bundle.
     * @param disabledColorScheme The disabled color scheme of this bundle.
     */
    init {
        require(
            !(activeColorScheme == null || enabledColorScheme == null
                    || disabledColorScheme == null)
        ) { "Cannot pass null schemes" }
        this.activeColorScheme = activeColorScheme
        this.enabledColorScheme = enabledColorScheme
        this.disabledColorScheme = disabledColorScheme
        colorSchemeMap = HashMap()
        for (associationKind in ColorSchemeAssociationKind.values()) {
            colorSchemeMap[associationKind] = HashMap()
        }
        bestFillMap = HashMap()
        for (associationKind in ColorSchemeAssociationKind.values()) {
            bestFillMap[associationKind] = HashMap()
        }
    }
}