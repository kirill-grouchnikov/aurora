package org.pushingpixels.mosaic.colorscheme

import org.pushingpixels.mosaic.ColorSchemeAssociationKind
import org.pushingpixels.mosaic.ComponentState
import org.pushingpixels.mosaic.ComponentStateFacet
import org.pushingpixels.mosaic.DecorationAreaType


/**
 * Color scheme bundle. Defines the visual appearance of a single decoration area of a skin.
 *
 * @author Kirill Grouchnikov
 * @see DecorationAreaType
 * @see ColorSchemeAssociationKind
 * @see [MosaicSkin]
 */
class MosaicColorSchemeBundle(
    activeColorScheme: MosaicColorScheme,
    enabledColorScheme: MosaicColorScheme,
    disabledColorScheme: MosaicColorScheme
) {
    /**
     * The active color scheme of this bundle.
     */
    private val activeColorScheme: MosaicColorScheme

    /**
     * The enabled color scheme of this bundle.
     */
    private val enabledColorScheme: MosaicColorScheme

    /**
     * The disabled color scheme of this bundle.
     */
    private val disabledColorScheme: MosaicColorScheme

    /**
     * Maps from component state to the alpha channel applied on color scheme.
     * This map doesn't have to contain entries for all [ComponentState]
     * instances.
     */
    private val stateAlphaMap: MutableMap<ComponentState, Float>

    /**
     * Maps from component state to the alpha channel applied on highlight color
     * scheme. This map doesn't have to contain entries for all
     * [ComponentState] instances.
     */
    private val stateHighlightSchemeAlphaMap: MutableMap<ComponentState, Float>

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
                disabledSelectedScheme = activeColorScheme.blendWith(disabledColorScheme, 0.25f)
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
    fun getActiveColorScheme(): MosaicColorScheme {
        return activeColorScheme
    }

    /**
     * Returns the enabled color scheme of this bundle.
     *
     * @return The enabled color scheme of this bundle.
     */
    fun getEnabledColorScheme(): MosaicColorScheme {
        return enabledColorScheme
    }

    /**
     * Returns the disabled color scheme of this bundle.
     *
     * @return The disabled color scheme of this bundle.
     */
    fun getDisabledColorScheme(): MosaicColorScheme {
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
        if (states.isEmpty()) {
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
    fun registerHighlightColorScheme(stateHighlightScheme: MosaicColorScheme, vararg states: ComponentState) {
        if (states.isEmpty()) {
            for (state in ComponentState.getAllStates()) {
                if (colorSchemeMap[ColorSchemeAssociationKind.HIGHLIGHT]!!.containsKey(state)) {
                    continue
                }
                if (state.isDisabled) {
                    continue
                }
                if (state === ComponentState.ENABLED) {
                    continue
                }
                colorSchemeMap[ColorSchemeAssociationKind.HIGHLIGHT]!![state] = stateHighlightScheme
            }
        } else {
            for (state in states) {
                colorSchemeMap[ColorSchemeAssociationKind.HIGHLIGHT]!![state] = stateHighlightScheme
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
                stateHighlightSchemeAlphaMap[state] = alpha
            }
        } else {
            for (state in states) {
                stateHighlightSchemeAlphaMap[state] = alpha
            }
        }
    }

    /**
     * Returns the alpha channel of the highlight color schemes for the specified component state.
     *
     * @param componentState Component state.
     * @return Highlight color scheme alpha channel.
     */
    fun getHighlightAlpha(componentState: ComponentState): Float {
        val registered = stateHighlightSchemeAlphaMap[componentState]
        return registered ?: -1.0f
    }

    /**
     * Returns the alpha channel of color schemes for the specified component state.
     *
     * @param componentState Component state.
     * @return Color scheme alpha channel.
     */
    fun getAlpha(componentState: ComponentState): Float {
        val registered = stateAlphaMap[componentState]
        return registered ?: -1.0f
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
     * Creates a new color scheme bundle.
     *
     * @param activeColorScheme   The active color scheme of this bundle.
     * @param enabledColorScheme  The enabled color scheme of this bundle.
     * @param disabledColorScheme The disabled color scheme of this bundle.
     */
    init {
        this.activeColorScheme = activeColorScheme
        this.enabledColorScheme = enabledColorScheme
        this.disabledColorScheme = disabledColorScheme
        stateAlphaMap = HashMap()
        stateHighlightSchemeAlphaMap = HashMap()

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

class MosaicSkinColors {
    /**
     * Maps decoration area type to the color scheme bundles. Must contain an
     * entry for [DecorationAreaType.NONE].
     */
    private val colorSchemeBundleMap: MutableMap<DecorationAreaType, MosaicColorSchemeBundle>

    /**
     * Maps decoration area type to the background color schemes.
     */
    private val backgroundColorSchemeMap: MutableMap<DecorationAreaType, MosaicColorScheme>

    /**
     * Set of all decoration area types that are not explicitly registered in
     * [.colorSchemeBundleMap] but still are considered as decoration
     * areas in this skin. Controls in such areas will have their background painted by
     *
     *
     * [SubstanceDecorationPainter.paintDecorationArea]
     * instead of a simple background fill.
     */
    private val decoratedAreaSet: MutableSet<DecorationAreaType>

    /**
     * All component states that have associated non-trivial alpha values.
     */
    private val statesWithAlpha: MutableSet<ComponentState>

    init {
        colorSchemeBundleMap = HashMap()
        backgroundColorSchemeMap = HashMap()

        decoratedAreaSet = HashSet()
        decoratedAreaSet.add(DecorationAreaType.PRIMARY_TITLE_PANE)
        decoratedAreaSet.add(DecorationAreaType.SECONDARY_TITLE_PANE)

        statesWithAlpha = HashSet()
    }

    /**
     * Returns the color scheme that matches the decoration area type and
     * component state.
     *
     * @param decorationAreaType Decoration area type.
     * @param componentState Component state.
     * @return The color scheme that matches the decoration area type and
     * component state.
     */
    fun getColorScheme(
        decorationAreaType: DecorationAreaType,
        componentState: ComponentState
    ): MosaicColorScheme {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]!!.getColorScheme(componentState)
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.NONE]!!.getColorScheme(componentState)
    }

    /**
     * Returns the alpha channel of the highlight color scheme.
     *
     * @param decorationAreaType Decoration area type.
     * @param componentState Component state.
     * @return Highlight color scheme alpha channel.
     */
    fun getHighlightAlpha(
        decorationAreaType: DecorationAreaType,
        componentState: ComponentState
    ): Float {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                val registered = colorSchemeBundleMap[decorationAreaType]!!
                    .getHighlightAlpha(componentState)
                if (registered >= 0.0) {
                    return registered
                }
            }
        }
        val registered = colorSchemeBundleMap[DecorationAreaType.NONE]!!
            .getHighlightAlpha(componentState)
        if (registered >= 0.0) {
            return registered
        }
        val isRollover = componentState.isFacetActive(ComponentStateFacet.ROLLOVER)
        val isSelected = componentState.isFacetActive(ComponentStateFacet.SELECTION)
        if (isRollover && isSelected) {
            return 0.9f
        }
        if (isSelected) {
            return 0.7f
        }
        return if (isRollover) {
            0.4f
        } else 0.0f
    }

    /**
     * Returns the alpha channel of the color scheme.
     *
     * @param decorationAreaType Decoration area type.
     * @param componentState Component state.
     * @return Color scheme alpha channel.
     */
    fun getAlpha(decorationAreaType: DecorationAreaType, componentState: ComponentState): Float {
        // optimization - if the state does not have hard fallback, and it is not registered in any
        // scheme bundle with custom alpha, return 1.0
        val fallback = componentState.hardFallback
        if (fallback == null && !statesWithAlpha.contains(componentState)) {
            return 1.0f
        }

        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                val registered = colorSchemeBundleMap[decorationAreaType]!!.getAlpha(componentState)
                if (registered >= 0.0) {
                    return registered
                }
            }
        }
        val registered = colorSchemeBundleMap[DecorationAreaType.NONE]!!.getAlpha(componentState)
        return if (registered >= 0.0) {
            registered
        } else fallback?.let { getAlpha(decorationAreaType, it) } ?: 1.0f
    }

    /**
     * Registers the specified color scheme bundle and background color scheme
     * to be used on controls in decoration areas.
     *
     * @param bundle                The color scheme bundle to use on controls in decoration
     * areas.
     * @param backgroundColorScheme The color scheme to use for background of controls in
     * decoration areas.
     * @param areaTypes             Enumerates the area types that are affected by the parameters.
     */
    fun registerDecorationAreaSchemeBundle(
        bundle: MosaicColorSchemeBundle,
        backgroundColorScheme: MosaicColorScheme,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            decoratedAreaSet.add(areaType)
            colorSchemeBundleMap[areaType] = bundle
            backgroundColorSchemeMap[areaType] = backgroundColorScheme
        }
        statesWithAlpha.addAll(bundle.getStatesWithAlpha())
    }

    /**
     * Registers the specified color scheme bundle to be used on controls in
     * decoration areas.
     *
     * @param bundle    The color scheme bundle to use on controls in decoration
     * areas.
     * @param areaTypes Enumerates the area types that are affected by the parameters.
     */
    fun registerDecorationAreaSchemeBundle(
        bundle: MosaicColorSchemeBundle, vararg areaTypes: DecorationAreaType
    ) {
        this.registerDecorationAreaSchemeBundle(
            bundle = bundle, backgroundColorScheme = bundle.getEnabledColorScheme(),
            areaTypes = areaTypes
        )
    }

    /**
     * Registers the specified background color scheme to be used on controls in
     * decoration areas.
     *
     * @param backgroundColorScheme The color scheme to use for background of controls in
     * decoration areas.
     * @param areaTypes             Enumerates the area types that are affected by the parameters.
     * Each decoration area type will be painted by
     * [SubstanceDecorationPainter.paintDecorationArea]
     */
    fun registerAsDecorationArea(
        backgroundColorScheme: MosaicColorScheme,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            decoratedAreaSet.add(areaType)
            backgroundColorSchemeMap[areaType] = backgroundColorScheme
        }
    }

    /**
     * Returns indication whether the specified decoration area type should have
     * their background painted by
     * [SubstanceDecorationPainter.paintDecorationArea]
     * instead of a simple background fill.
     *
     * @param decorationType Decoration area type.
     * @return `true` if specified decoration area type should have
     * their background painted by
     * [SubstanceDecorationPainter.paintDecorationArea]
     * , `false` otherwise.
     */
    fun isRegisteredAsDecorationArea(decorationType: DecorationAreaType?): Boolean {
        return decoratedAreaSet.contains(decorationType)
    }

    /**
     * Returns the main active color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use
     * [.getColorScheme] method and various
     * [SubstanceColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main active color scheme for this skin.
     * @see .getColorScheme
     */
    fun getActiveColorScheme(
        decorationAreaType: DecorationAreaType
    ): MosaicColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getActiveColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.NONE]!!.getActiveColorScheme()
    }

    /**
     * Returns the main enabled color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use
     * [.getColorScheme] method and various
     * [SubstanceColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main enabled color scheme for this skin.
     * @see .getColorScheme
     */
    fun getEnabledColorScheme(
        decorationAreaType: DecorationAreaType
    ): MosaicColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getEnabledColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.NONE]!!.getEnabledColorScheme()
    }

    /**
     * Returns the main disabled color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use
     * [.getColorScheme] method and various
     * [SubstanceColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main disabled color scheme for this skin.
     * @see .getColorScheme
     */
    fun getDisabledColorScheme(
        decorationAreaType: DecorationAreaType
    ): MosaicColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getDisabledColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.NONE]!!.getDisabledColorScheme()
    }

    /**
     * Returns the color scheme to be used for painting the specified visual
     * area of components in the specified decoration area.
     *
     * @param decorationAreaType Decoration area type.
     * @param associationKind    Color scheme association kind.
     * @param componentState     Component state.
     * @return Color scheme to be used for painting the specified visual area of
     * components in the specified decoration area.
     */
    fun getColorScheme(
        decorationAreaType: DecorationAreaType,
        associationKind: ColorSchemeAssociationKind,
        componentState: ComponentState
    ): MosaicColorScheme {
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]!!
                    .getColorScheme(associationKind, componentState, true)!!
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.NONE]!!
            .getColorScheme(associationKind, componentState, true)!!
    }

    /**
     * Returns the color scheme to be used for painting the specified visual
     * area of components in the specified decoration area.
     *
     * @param decorationAreaType Decoration area type.
     * @param associationKind Color scheme association kind.
     * @param componentState  Component state.
     * @return Color scheme to be used for painting the specified visual area of
     * the component under the specified component state.
     */
    fun getDirectColorScheme(
        decorationAreaType: DecorationAreaType,
        associationKind: ColorSchemeAssociationKind,
        componentState: ComponentState
    ): MosaicColorScheme? {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]?.
                getColorScheme(associationKind, componentState, false)
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.NONE]?.
            getColorScheme(associationKind, componentState, false)
    }

    /**
     * Returns the background color scheme for the specified decoration area
     * type. This method is mainly for the internal use of
     * [SubstanceDecorationPainter.paintDecorationArea]
     * but can be used in applications that wish to provide custom overlay
     * background painting.
     *
     * @param decorationAreaType Decoration area type.
     * @return The background color scheme for the specified decoration area type.
     */
    fun getBackgroundColorScheme(decorationAreaType: DecorationAreaType?): MosaicColorScheme {
        // 1 - check the registered background scheme for this specific area type.
        if (backgroundColorSchemeMap.containsKey(decorationAreaType)) {
            return backgroundColorSchemeMap[decorationAreaType]!!
        }
        // 2 - check the registered scheme bundle for this specific area type.
        if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            val registered: MosaicColorScheme? = colorSchemeBundleMap[decorationAreaType]?.getEnabledColorScheme()
            if (registered != null) {
                return registered
            }
        }
        // 3 - return the background scheme for the default area type
        return backgroundColorSchemeMap[DecorationAreaType.NONE]!!
    }
}

interface ColorSchemes {
    /**
     * Returns all the color schemes handled by this object.
     *
     * @return All the color schemes handled by this object.
     */
    val all: Collection<MosaicColorScheme>

    /**
     * Returns the color scheme based on its display name.
     *
     * @param displayName Display name of a color scheme.
     * @return The color scheme with the matching display name.
     */
    operator fun get(displayName: String): MosaicColorScheme
}
