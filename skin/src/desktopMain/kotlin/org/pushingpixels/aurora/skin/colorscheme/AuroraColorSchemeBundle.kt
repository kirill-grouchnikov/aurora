package org.pushingpixels.aurora.skin.colorscheme

import org.pushingpixels.aurora.skin.ColorSchemeAssociationKind
import org.pushingpixels.aurora.skin.ComponentState
import org.pushingpixels.aurora.skin.ComponentStateFacet
import org.pushingpixels.aurora.skin.DecorationAreaType
import org.pushingpixels.aurora.skin.painter.decoration.AuroraDecorationPainter
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set


/**
 * Color scheme bundle. Defines the visual appearance of a single decoration area of a skin.
 *
 * @author Kirill Grouchnikov
 * @see DecorationAreaType
 * @see ColorSchemeAssociationKind
 * @see AuroraSkin
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
    private var stateAlphaMap: MutableMap<ComponentState, Float>

    /**
     * Maps from component state to the alpha channel applied on highlight color
     * scheme. This map doesn't have to contain entries for all
     * [ComponentState] instances.
     */
    private var stateHighlightSchemeAlphaMap: MutableMap<ComponentState, Float>

    /**
     * If there is no explicitly registered color scheme for pressed component
     * state, this field will contain a synthesized color scheme for the pressed
     * state.
     *
     * @see ComponentState.PressedSelected
     *
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
     *
     *  * An entry with key [ColorSchemeAssociationKind.Fill]. This entry
     * has a map entry with key [ComponentState.Selected] and value that
     * points to the light orange scheme.
     *  * An entry with key [ColorSchemeAssociationKind.Border]. This
     * entry has a map entry with key [ComponentState.Selected] and value
     * that points to the dark gray scheme.
     *
     */
    private val colorSchemeMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, AuroraColorScheme>>
    private val bestFillMap: MutableMap<ColorSchemeAssociationKind, MutableMap<ComponentState, ComponentState?>>

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
     *
     *  * `scheme`=light orange scheme
     *  *
     * `associationKind`=[ColorSchemeAssociationKind.Fill]
     *  *
     * `states`=[ComponentState.RolloverSelected],
     * [ComponentState.RolloverUnselected]
     *
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
        result.stateAlphaMap = HashMap(stateAlphaMap)

        // highlight alphas are the same
        result.stateHighlightSchemeAlphaMap = HashMap(stateHighlightSchemeAlphaMap)
        return result
    }

    /**
     * Creates a new color scheme bundle.
     */
    init {
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

class AuroraSkinColors {
    /**
     * Maps decoration area type to the color scheme bundles. Must contain an
     * entry for [DecorationAreaType.None].
     */
    private val colorSchemeBundleMap: MutableMap<DecorationAreaType, AuroraColorSchemeBundle>

    /**
     * Maps decoration area type to the background color schemes.
     */
    private val backgroundColorSchemeMap: MutableMap<DecorationAreaType, AuroraColorScheme>

    /**
     * Set of all decoration area types that are not explicitly registered in
     * [.colorSchemeBundleMap] but still are considered as decoration
     * areas in this skin. Controls in such areas will have their background painted by
     * [AuroraDecorationPainter.paintDecorationArea] instead of a simple background fill.
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
        decoratedAreaSet.add(DecorationAreaType.TitlePane)

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
    ): AuroraColorScheme {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]!!.getColorScheme(componentState)
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.None]!!.getColorScheme(componentState)
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
        val registered = colorSchemeBundleMap[DecorationAreaType.None]!!
            .getHighlightAlpha(componentState)
        if (registered >= 0.0) {
            return registered
        }
        val isRollover = componentState.isFacetActive(ComponentStateFacet.Rollover)
        val isSelected = componentState.isFacetActive(ComponentStateFacet.Selection)
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
        val registered = colorSchemeBundleMap[DecorationAreaType.None]!!.getAlpha(componentState)
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
        bundle: AuroraColorSchemeBundle,
        backgroundColorScheme: AuroraColorScheme,
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
        bundle: AuroraColorSchemeBundle, vararg areaTypes: DecorationAreaType
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
     * Each decoration area type will be painted by [AuroraDecorationPainter.paintDecorationArea]
     */
    fun registerAsDecorationArea(
        backgroundColorScheme: AuroraColorScheme,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            decoratedAreaSet.add(areaType)
            backgroundColorSchemeMap[areaType] = backgroundColorScheme
        }
    }

    /**
     * Registers the specified background color scheme and a color scheme bundle overlay to be used
     * on controls in decoration areas.
     *
     * @param backgroundColorScheme     The color scheme to use for background of controls in
     * decoration areas.
     * @param noneTransformationOverlay Overlay to be applied to the [AuroraColorSchemeBundle]
     * registered on the [DecorationAreaType.None], with the
     * resulting color scheme bundle to be used on #areaTypes.
     * @param areaTypes                 Enumerates the area types that are affected by the
     * parameters.
     */
    fun registerAsDecorationArea(
        backgroundColorScheme: AuroraColorScheme,
        noneTransformationOverlay: (AuroraColorSchemeBundle) -> Unit,
        vararg areaTypes: DecorationAreaType
    ) {
        val defaultBundle = colorSchemeBundleMap[DecorationAreaType.None]
            ?: throw IllegalStateException("Cannot apply overlay without a registered NONE bundle")

        // Apply a dummy "transformation" - effectively makes a deep copy of the default bundle
        val noneCopy = defaultBundle.transform { scheme -> scheme }
        // Apply the overlay
        noneTransformationOverlay.invoke(noneCopy)
        // And register the overlay transform on the requested decoration areas
        this.registerDecorationAreaSchemeBundle(
            bundle = noneCopy,
            backgroundColorScheme = backgroundColorScheme,
            areaTypes = areaTypes
        )
    }


    /**
     * Returns indication whether the specified decoration area type should have
     * their background painted by [AuroraDecorationPainter.paintDecorationArea]
     * instead of a simple background fill.
     *
     * @param decorationType Decoration area type.
     * @return `true` if specified decoration area type should have
     * their background painted by [AuroraDecorationPainter.paintDecorationArea],
     * `false` otherwise.
     */
    fun isRegisteredAsDecorationArea(decorationType: DecorationAreaType?): Boolean {
        return decoratedAreaSet.contains(decorationType)
    }

    /**
     * Returns the main active color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use [.getColorScheme] method and various
     * [AuroraColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main active color scheme for this skin.
     * @see .getColorScheme
     */
    fun getActiveColorScheme(
        decorationAreaType: DecorationAreaType
    ): AuroraColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getActiveColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.None]!!.getActiveColorScheme()
    }

    /**
     * Returns the main enabled color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use [.getColorScheme] method and various
     * [AuroraColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main enabled color scheme for this skin.
     * @see .getColorScheme
     */
    fun getEnabledColorScheme(
        decorationAreaType: DecorationAreaType
    ): AuroraColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getEnabledColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.None]!!.getEnabledColorScheme()
    }

    /**
     * Returns the main disabled color scheme for the specific decoration area
     * type. Custom painting code that needs to consult the colors of the
     * specific component should use [.getColorScheme] method and various
     * [AuroraColorScheme] methods.
     *
     * @param decorationAreaType Decoration area type.
     * @return The main disabled color scheme for this skin.
     * @see .getColorScheme
     */
    fun getDisabledColorScheme(
        decorationAreaType: DecorationAreaType
    ): AuroraColorScheme {
        return if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            colorSchemeBundleMap[decorationAreaType]!!.getDisabledColorScheme()
        } else colorSchemeBundleMap[DecorationAreaType.None]!!.getDisabledColorScheme()
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
    ): AuroraColorScheme {
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]!!
                    .getColorScheme(associationKind, componentState, true)!!
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.None]!!
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
    ): AuroraColorScheme? {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific scheme bundles.
        if (colorSchemeBundleMap.size > 1) {
            if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
                return colorSchemeBundleMap[decorationAreaType]?.getColorScheme(associationKind, componentState, false)
            }
        }
        return colorSchemeBundleMap[DecorationAreaType.None]?.getColorScheme(associationKind, componentState, false)
    }

    /**
     * Returns the background color scheme for the specified decoration area
     * type. This method is mainly for the internal use of
     * [AuroraDecorationPainter.paintDecorationArea]
     * but can be used in applications that wish to provide custom overlay
     * background painting.
     *
     * @param decorationAreaType Decoration area type.
     * @return The background color scheme for the specified decoration area type.
     */
    fun getBackgroundColorScheme(decorationAreaType: DecorationAreaType): AuroraColorScheme {
        // 1 - check the registered background scheme for this specific area type.
        if (backgroundColorSchemeMap.containsKey(decorationAreaType)) {
            return backgroundColorSchemeMap[decorationAreaType]!!
        }
        // 2 - check the registered scheme bundle for this specific area type.
        if (colorSchemeBundleMap.containsKey(decorationAreaType)) {
            val registered: AuroraColorScheme? = colorSchemeBundleMap[decorationAreaType]?.getEnabledColorScheme()
            if (registered != null) {
                return registered
            }
        }
        // 3 - return the background scheme for the default area type
        return backgroundColorSchemeMap[DecorationAreaType.None]!!
    }
}

interface ColorSchemes {
    /**
     * Returns all the color schemes handled by this object.
     *
     * @return All the color schemes handled by this object.
     */
    val all: Collection<AuroraColorScheme>

    /**
     * Returns the color scheme based on its display name.
     *
     * @param displayName Display name of a color scheme.
     * @return The color scheme with the matching display name.
     */
    operator fun get(displayName: String): AuroraColorScheme
}
