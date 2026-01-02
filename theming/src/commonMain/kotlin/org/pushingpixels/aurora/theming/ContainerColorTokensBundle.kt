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

import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.utils.*
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct

/**
 * Color tokens bundle. Defines the visual appearance of a single decoration area of a skin.
 *
 * @author Kirill Grouchnikov
 * @see org.pushingpixels.aurora.theming.DecorationAreaType
 * @see ContainerColorTokensAssociationKind
 * @see org.pushingpixels.aurora.theming.AuroraSkinColors
 */
class ContainerColorTokensBundle {
    private val activeContainerTokens: ContainerColorTokens
    private val mutedContainerTokens: ContainerColorTokens
    private val neutralContainerTokens: ContainerColorTokens

    private val systemInfoContainerTokens: ContainerColorTokens
    private val inverseSystemInfoContainerTokens: ContainerColorTokens
    private val systemWarningContainerTokens: ContainerColorTokens
    private val inverseSystemWarningContainerTokens: ContainerColorTokens
    private val systemErrorContainerTokens: ContainerColorTokens
    private val inverseSystemErrorContainerTokens: ContainerColorTokens
    private val systemSuccessContainerTokens: ContainerColorTokens
    private val inverseSystemSuccessContainerTokens: ContainerColorTokens

    /**
     * Maps from color tokens association kinds to the map of color tokens. Controls in the specific
     * decoration area can use different colors for different active states, for example yellow
     * for rollover and deep orange for pressed. In this case, this map will have an entry with
     * [ContainerColorTokensAssociationKind.Default] key and a value
     * map with two entries:
     *
     *  * A map entry with key [ComponentState.RolloverUnselected] and value that
     * points to the yellow color tokens.
     *  * A map entry with key [ComponentState.PressedUnselected] and value that
     * points to the deep orange color tokens.
     *
     */
    private val activeTokenOverrides: MutableMap<ContainerColorTokensAssociationKind, 
            MutableMap<ComponentState, ContainerColorTokens?>> = hashMapOf()

    private val mutedTokenOverrides: MutableMap<ContainerColorTokensAssociationKind, ContainerColorTokens> = hashMapOf()

    private val neutralTokenOverrides: MutableMap<ContainerColorTokensAssociationKind, ContainerColorTokens> = hashMapOf()

    /**
     * Creates a new color tokens bundle.
     *
     * @param activeContainerTokens Active color tokens of this bundle.
     * @param mutedContainerTokens Muted color tokens of this bundle.
     * @param neutralContainerTokens Neutral color tokens of this bundle.
     * @param isSystemDark `true` if the system tokens should be created in dark mode.
     */
    constructor(
        activeContainerTokens: ContainerColorTokens,
        mutedContainerTokens: ContainerColorTokens, 
        neutralContainerTokens: ContainerColorTokens,
        isSystemDark: Boolean
    ) {
        val systemInfoSeed: Hct = Hct.fromInt(0xFF1060D0u.toInt())
        val systemWarningSeed: Hct = Hct.fromInt(0xFFFCC000u.toInt())
        val systemErrorSeed: Hct = Hct.fromInt(0xFFC01707u.toInt())
        val systemSuccessSeed: Hct = Hct.fromInt(0xFF007710u.toInt())

        val systemInfoLightTokens: ContainerColorTokens =
            getSystemTokens(
                seed = systemInfoSeed,
                containerConfiguration = ContainerConfiguration.defaultLight()
            )
        val systemWarningLightTokens: ContainerColorTokens =
            getSystemTokens(
                seed = systemWarningSeed,
                containerConfiguration = ContainerConfiguration.defaultLight()
            )
        val systemErrorLightTokens: ContainerColorTokens =
            getSystemTokens(
                seed = systemErrorSeed,
                containerConfiguration = ContainerConfiguration.defaultLight()
            )
        val systemSuccessLightTokens: ContainerColorTokens = getSystemTokens(
            seed = systemSuccessSeed,
            containerConfiguration = ContainerConfiguration.defaultLight()
        )

        val systemInfoDarkTokens: ContainerColorTokens = getSystemTokens(
            seed = systemInfoSeed,
            containerConfiguration = ContainerConfiguration.defaultDark()
        )
        val systemWarningDarkTokens: ContainerColorTokens = getSystemTokens(
            seed = systemWarningSeed,
            containerConfiguration = ContainerConfiguration.defaultDark()
        )
        val systemErrorDarkTokens: ContainerColorTokens = getSystemTokens(
            seed = systemErrorSeed,
            containerConfiguration = ContainerConfiguration.defaultDark()
        )
        val systemSuccessDarkTokens: ContainerColorTokens = getSystemTokens(
            seed = systemSuccessSeed,
            containerConfiguration = ContainerConfiguration.defaultDark()
        )

        this.activeContainerTokens = activeContainerTokens
        this.mutedContainerTokens = mutedContainerTokens
        this.neutralContainerTokens = neutralContainerTokens

        this.systemInfoContainerTokens = if (isSystemDark) systemInfoDarkTokens else systemInfoLightTokens
        this.systemWarningContainerTokens = if (isSystemDark) systemWarningDarkTokens else systemWarningLightTokens
        this.systemErrorContainerTokens = if (isSystemDark) systemErrorDarkTokens else systemErrorLightTokens
        this.systemSuccessContainerTokens = if (isSystemDark) systemSuccessDarkTokens else systemSuccessLightTokens

        this.inverseSystemInfoContainerTokens = if (isSystemDark) systemInfoLightTokens else systemInfoDarkTokens
        this.inverseSystemWarningContainerTokens =
            if (isSystemDark) systemWarningLightTokens else systemWarningDarkTokens
        this.inverseSystemErrorContainerTokens = if (isSystemDark) systemErrorLightTokens else systemErrorDarkTokens
        this.inverseSystemSuccessContainerTokens =
            if (isSystemDark) systemSuccessLightTokens else systemSuccessDarkTokens

        for (associationKind in ContainerColorTokensAssociationKind.values()) {
            this.activeTokenOverrides[associationKind] = hashMapOf()
        }
    }

    /**
     * Creates a new color tokens bundle.
     *
     * @param activeContainerTokens Active color tokens of this bundle.
     * @param mutedContainerTokens Muted color tokens of this bundle.
     * @param neutralContainerTokens Neutral color tokens of this bundle.
     * @param systemInfoContainerTokens System info color tokens of this bundle.
     * @param inverseSystemInfoContainerTokens Inverse system info color tokens of this bundle.
     * @param systemWarningContainerTokens System warning color tokens of this bundle.
     * @param inverseSystemWarningContainerTokens Inverse system warning color tokens of this bundle.
     * @param systemErrorContainerTokens System error color tokens of this bundle.
     * @param inverseSystemErrorContainerTokens Inverse system error color tokens of this bundle.
     * @param systemSuccessContainerTokens System success color tokens of this bundle.
     * @param inverseSystemSuccessContainerTokens Inverse system success color tokens of this bundle.
     */
    constructor(
        activeContainerTokens: ContainerColorTokens,
        mutedContainerTokens: ContainerColorTokens, neutralContainerTokens: ContainerColorTokens,
        systemInfoContainerTokens: ContainerColorTokens, inverseSystemInfoContainerTokens: ContainerColorTokens,
        systemWarningContainerTokens: ContainerColorTokens, inverseSystemWarningContainerTokens: ContainerColorTokens,
        systemErrorContainerTokens: ContainerColorTokens, inverseSystemErrorContainerTokens: ContainerColorTokens,
        systemSuccessContainerTokens: ContainerColorTokens, inverseSystemSuccessContainerTokens: ContainerColorTokens
    ) {
        this.activeContainerTokens = activeContainerTokens
        this.mutedContainerTokens = mutedContainerTokens
        this.neutralContainerTokens = neutralContainerTokens

        this.systemInfoContainerTokens = systemInfoContainerTokens
        this.systemWarningContainerTokens = systemWarningContainerTokens
        this.systemErrorContainerTokens = systemErrorContainerTokens
        this.systemSuccessContainerTokens = systemSuccessContainerTokens

        this.inverseSystemInfoContainerTokens = inverseSystemInfoContainerTokens
        this.inverseSystemWarningContainerTokens = inverseSystemWarningContainerTokens
        this.inverseSystemErrorContainerTokens = inverseSystemErrorContainerTokens
        this.inverseSystemSuccessContainerTokens = inverseSystemSuccessContainerTokens

        for (associationKind in ContainerColorTokensAssociationKind.values()) {
            this.activeTokenOverrides[associationKind] = hashMapOf()
        }
    }

    /**
     * Registers the container color tokens to be used for controls in specified active states.
     * For example, if light orange color tokens are to be used for rollover selected and rollover
     * controls in highlights, the parameters would be:
     *
     *  * `stateContainerTokens`=light orange color tokens
     *  * `associationKind`=[ContainerColorTokensAssociationKind.Highlight]
     *  * `states`=[ComponentState.RolloverSelected], [ComponentState.RolloverUnselected]
     *
     * @param colorTokens  Container color tokens for the specified active component states.
     * @param associationKind Color tokens association kind that specifies the visual areas
     *      of controls to be painted with this color tokens.
     * @param activeStates Component states that further restrict the usage of the
     *      specified color tokens.
     */
    fun registerActiveContainerTokens(
        colorTokens: ContainerColorTokens,
        associationKind: ContainerColorTokensAssociationKind = ContainerColorTokensAssociationKind.Default,
        vararg activeStates: ComponentState
    ) {
        require(activeStates.isNotEmpty()) { "Must pass at least one state" }

        for (state in activeStates) {
            require(!state.isDisabled && state.isActive) { "Only active states can have custom color tokens" }
            this.activeTokenOverrides[associationKind]!![state] = colorTokens
        }
    }

    /**
     * Registers muted container color tokens for the specified visual area of a component.
     *
     * @param colorTokens Muted container color tokens for the specified visual area.
     * @param associationKind Color tokens association kind.
     */
    fun registerMutedContainerTokens(
        colorTokens: ContainerColorTokens,
        associationKind: ContainerColorTokensAssociationKind
    ) {
        this.mutedTokenOverrides[associationKind] = colorTokens
    }

    /**
     * Registers neutral container color tokens for the specified visual area of a component.
     *
     * @param colorTokens Neutral container color tokens for the specified visual area.
     * @param associationKind Color tokens association kind.
     */
    fun registerNeutralContainerTokens(
        colorTokens: ContainerColorTokens,
        associationKind: ContainerColorTokensAssociationKind
    ) {
        this.neutralTokenOverrides[associationKind] = colorTokens
    }

    /**
     * Returns active container tokens for the specified component state.
     *
     * @param componentState Component state.
     * @return Active container tokens for the component state.
     */
    fun getActiveContainerTokens(componentState: ComponentState): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokens(componentState.enabledMatch!!)
        }

        require(componentState.isActive) { "Only active states supported" }

        val registered: ContainerColorTokens? =
            this.activeTokenOverrides[ContainerColorTokensAssociationKind.Default]!![componentState]
        if (registered != null) {
            // If we're here, the component state is guaranteed to be active due to restrictions
            // in registerActiveContainerTokens
            return registered
        } else {
            return this.getActiveContainerTokensForState(componentState)
        }
    }

    /**
     * Returns system container tokens.
     *
     * @param systemContainerType System container type.
     * @return System container tokens.
     */
    fun getSystemContainerTokens(systemContainerType: SystemContainerType): ContainerColorTokens {
        return when (systemContainerType) {
            SystemContainerType.Info -> this.systemInfoContainerTokens
            SystemContainerType.Warning -> this.systemWarningContainerTokens
            SystemContainerType.Error -> this.systemErrorContainerTokens
            SystemContainerType.Success -> this.systemSuccessContainerTokens
        }
    }

    /**
     * Returns inverse system container tokens.
     *
     * @param systemContainerType System container type.
     * @return Inverse system container tokens.
     */
    fun getInverseSystemContainerTokens(systemContainerType: SystemContainerType): ContainerColorTokens {
        return when (systemContainerType) {
            SystemContainerType.Info -> this.inverseSystemInfoContainerTokens
            SystemContainerType.Warning -> this.inverseSystemWarningContainerTokens
            SystemContainerType.Error -> this.inverseSystemErrorContainerTokens
            SystemContainerType.Success -> this.inverseSystemSuccessContainerTokens
        }
    }

    /**
     * Returns active container tokens.
     *
     * @return Active container tokens.
     */
    fun getActiveContainerTokens(): ContainerColorTokens {
        return this.activeContainerTokens
    }

    /**
     * Returns neutral container tokens.
     *
     * @return Neutral container tokens.
     */
    fun getNeutralContainerTokens(): ContainerColorTokens {
        return this.neutralContainerTokens
    }

    /**
     * Returns muted container tokens.
     *
     * @return Muted container tokens.
     */
    fun getMutedContainerTokens(): ContainerColorTokens {
        return this.mutedContainerTokens
    }

    private fun getActiveContainerTokensForState(componentState: ComponentState): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokensForState(componentState.enabledMatch!!)
        }

        val activeTokens = getActiveContainerTokens()
        val activeTokenStateOverrides =
            this.activeTokenOverrides[ContainerColorTokensAssociationKind.Default]!!

        if (componentState === ComponentState.PressedUnselected) {
            if (!activeTokenStateOverrides.containsKey(componentState)) {
                activeTokenStateOverrides[componentState] = getPressedUnselectedTokens(activeTokens)
            }
            return activeTokenStateOverrides[componentState]!!
        }
        if (componentState === ComponentState.PressedSelected) {
            if (!activeTokenStateOverrides.containsKey(componentState)) {
                activeTokenStateOverrides[componentState] = getPressedSelectedTokens(activeTokens)
            }
            return activeTokenStateOverrides[componentState]!!
        }
        if (componentState === ComponentState.Selected) {
            if (!activeTokenStateOverrides.containsKey(componentState)) {
                activeTokenStateOverrides[componentState] = activeTokens
            }
            return activeTokenStateOverrides[componentState]!!
        }
        if (componentState === ComponentState.RolloverUnselected) {
            if (!activeTokenStateOverrides.containsKey(componentState)) {
                activeTokenStateOverrides[componentState] = getRolloverUnselectedTokens(activeTokens)
            }
            return activeTokenStateOverrides[componentState]!!
        }
        if (componentState === ComponentState.RolloverSelected) {
            if (!activeTokenStateOverrides.containsKey(componentState)) {
                activeTokenStateOverrides[componentState] = getRolloverSelectedTokens(activeTokens)
            }
            return activeTokenStateOverrides[componentState]!!
        }

        val hardFallback: ComponentState? = componentState.hardFallback
        if (hardFallback != null) {
            return this.getActiveContainerTokensForState(hardFallback)
        }

        if (componentState === ComponentState.Enabled) {
            return getMutedContainerTokens()
        }
        return activeTokens
    }

    /**
     * Returns active container tokens for the specified visual area of a component in a specified
     * component state.
     *
     * @param associationKind Color tokens association kind.
     * @param componentState Component state.
     * @return Active container tokens.
     */
    fun getActiveContainerTokens(
        associationKind: ContainerColorTokensAssociationKind,
        componentState: ComponentState
    ): ContainerColorTokens {
        if (associationKind === ContainerColorTokensAssociationKind.Default) {
            return this.getActiveContainerTokens(componentState)
        }

        if (componentState.isDisabled) {
            // Use the enabled match, and alpha will be applied during rendering
            return getActiveContainerTokens(associationKind, componentState.enabledMatch!!)
        }

        val registered: ContainerColorTokens? =
            this.activeTokenOverrides[associationKind]!![componentState]
        if (registered != null) {
            return registered
        }

        val fallback: ContainerColorTokensAssociationKind? = associationKind.fallback
        if (fallback != null) {
            return getActiveContainerTokens(fallback, componentState)
        }

        return this.getActiveContainerTokens(componentState)
    }

    /**
     * Returns muted container tokens for the specified visual area of a component.
     *
     * @param associationKind Color tokens association kind.
     * @return Muted container tokens.
     */
    fun getMutedContainerTokens(associationKind: ContainerColorTokensAssociationKind): ContainerColorTokens {
        if (associationKind === ContainerColorTokensAssociationKind.Default) {
            return this.getMutedContainerTokens()
        }

        val registered: ContainerColorTokens? = this.mutedTokenOverrides.get(associationKind)
        if (registered != null) {
            return registered
        }

        val fallback: ContainerColorTokensAssociationKind? = associationKind.fallback
        if (fallback != null) {
            return getMutedContainerTokens(fallback)
        }

        return this.getMutedContainerTokens()
    }

    /**
     * Returns neutral container tokens for the specified visual area of a component.
     *
     * @param associationKind Color tokens association kind.
     * @return Neutral container tokens.
     */
    fun getNeutralContainerTokens(associationKind: ContainerColorTokensAssociationKind): ContainerColorTokens {
        if (associationKind === ContainerColorTokensAssociationKind.Default) {
            return this.getNeutralContainerTokens()
        }

        val registered: ContainerColorTokens? = this.neutralTokenOverrides.get(associationKind)
        if (registered != null) {
            return registered
        }

        val fallback: ContainerColorTokensAssociationKind? = associationKind.fallback
        if (fallback != null) {
            return getNeutralContainerTokens(fallback)
        }

        return this.getNeutralContainerTokens()
    }
}

class AuroraSkinColors {
    /**
     * Maps decoration area type to the color token bundles. Must contain an
     * entry for [DecorationAreaType.None].
     */
    private val colorTokensBundleMap: MutableMap<DecorationAreaType, ContainerColorTokensBundle> = hashMapOf()

    /**
     * Maps decoration area type to the neutral color tokens overrides.
     */
    private val neutralColorTokensOverrideMap: MutableMap<DecorationAreaType, ContainerColorTokens> = hashMapOf()

    /**
     * Set of all decoration area types that are not explicitly registered in
     * [.colorTokensBundleMap] but still are considered as decoration
     * areas in this skin. Controls in such areas will have their background painted by
     * [AuroraDecorationPainter.paintDecorationArea] instead of a simple background fill.
     */
    private val decoratedAreaSet: MutableSet<DecorationAreaType> = hashSetOf(DecorationAreaType.TitlePane)

    /**
     * Registers the specified color tokens bundle to be used on controls in
     * decoration areas.
     *
     * @param bundle    The color tokens bundle to use on controls in decoration
     * areas.
     * @param areaTypes Enumerates the area types that are affected by the parameters.
     */
    fun registerDecorationAreaTokensBundle(
        bundle: ContainerColorTokensBundle, vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            require(!this.neutralColorTokensOverrideMap.containsKey(areaType)) {
                "Decorated area type $areaType already configured"
            }

            this.decoratedAreaSet.add(areaType)
            this.colorTokensBundleMap[areaType] = bundle
        }
    }

    /**
     * Registers the specified neutral color tokens to be used on controls in
     * decoration areas.
     *
     * @param neutralContainerTokens The neutral tokens to use in specified decoration areas.
     * @param areaTypes             Enumerates the area types that are affected by the parameters.
     * Each decoration area type will be painted by
     * [AuroraDecorationPainter.paintDecorationArea]
     */
    fun registerAsDecorationArea(
        neutralContainerTokens: ContainerColorTokens,
        vararg areaTypes: DecorationAreaType
    ) {
        for (areaType in areaTypes) {
            require(areaType !== DecorationAreaType.None) { "Decoration area type NONE not supported by this API" }
            require(!this.colorTokensBundleMap.containsKey(areaType)) {
                "Decoration area type $areaType already configured"
            }
            this.decoratedAreaSet.add(areaType)
            this.neutralColorTokensOverrideMap[areaType] = neutralContainerTokens
        }
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

    fun getSystemContainerTokens(
        decorationAreaType: DecorationAreaType,
        systemContainerType: SystemContainerType
    ): ContainerColorTokens {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.
        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                return this.colorTokensBundleMap.get(decorationAreaType)!!
                    .getSystemContainerTokens(systemContainerType)
            }
        }

        return this.colorTokensBundleMap.get(DecorationAreaType.None)!!
            .getSystemContainerTokens(systemContainerType)
    }

    fun getInverseSystemContainerTokens(
        decorationAreaType: DecorationAreaType,
        systemContainerType: SystemContainerType
    ): ContainerColorTokens {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.
        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                return this.colorTokensBundleMap.get(decorationAreaType)!!
                    .getInverseSystemContainerTokens(systemContainerType)
            }
        }

        return this.colorTokensBundleMap.get(DecorationAreaType.None)!!
            .getInverseSystemContainerTokens(systemContainerType)
    }

    /**
     * Returns neutral container tokens for the specified decoration area type.
     *
     * @param decorationAreaType Decoration area type.
     * @return Neutral container tokens for the decoration area type.
     *
     * @see .getNeutralContainerTokens
     * @see .getNeutralContainerTokens
     * @see .getMutedContainerTokens
     * @see .getActiveContainerTokens
     */
    fun getNeutralContainerTokens(decorationAreaType: DecorationAreaType): ContainerColorTokens {
        // 1 - If it's the default area type, take its neutral container tokens
        if (decorationAreaType === DecorationAreaType.None) {
            return this.colorTokensBundleMap.get(DecorationAreaType.None)!!
                .getNeutralContainerTokens()
        }
        // 2 - check the registered neutral tokens override for this specific area type.
        if (this.neutralColorTokensOverrideMap.containsKey(decorationAreaType)) {
            return this.neutralColorTokensOverrideMap.get(decorationAreaType)!!
        }
        // 3 - check the registered tokens bundle for this specific area type.
        if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
            return this.colorTokensBundleMap[decorationAreaType]!!.getNeutralContainerTokens()
        }
        // 4 - return the neutral tokens for the default area type
        return this.colorTokensBundleMap[DecorationAreaType.None]!!.getNeutralContainerTokens()
    }

    fun getNeutralContainerTokens(
        decorationAreaType: DecorationAreaType,
        associationKind: ContainerColorTokensAssociationKind
    ): ContainerColorTokens {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.
        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                return this.colorTokensBundleMap[decorationAreaType]!!.getNeutralContainerTokens(
                    associationKind
                )
            }
        }
        return this.colorTokensBundleMap.get(DecorationAreaType.None)!!
            .getNeutralContainerTokens(associationKind)
    }


    /**
     * Returns active container tokens for the specified decoration area type.
     *
     * @param decorationAreaType Decoration area type.
     * @return Active container tokens for the decoration area type.
     */
    fun getActiveContainerTokens(decorationAreaType: DecorationAreaType): ContainerColorTokens {
        if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
            return this.colorTokensBundleMap[decorationAreaType]!!.getActiveContainerTokens()
        }
        return this.colorTokensBundleMap[DecorationAreaType.None]!!.getActiveContainerTokens()
    }

    fun getActiveContainerTokens(
        decorationAreaType: DecorationAreaType,
        componentState: ComponentState
    ): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokens(decorationAreaType, componentState.enabledMatch!!)
        }

        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.
        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                val registered = this.colorTokensBundleMap[decorationAreaType]!!.getActiveContainerTokens(componentState)
                return registered
            }
        }

        val registered = this.colorTokensBundleMap[DecorationAreaType.None]!!
            .getActiveContainerTokens(componentState)

        return registered
    }

    fun getActiveContainerTokens(
        decorationAreaType: DecorationAreaType,
        associationKind: ContainerColorTokensAssociationKind,
        componentState: ComponentState
    ): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokens(decorationAreaType, associationKind, componentState.enabledMatch!!)
        }

        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.
        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                return this.colorTokensBundleMap[decorationAreaType]!!.getActiveContainerTokens(
                    associationKind, componentState
                )
            }
        }
        return this.colorTokensBundleMap[DecorationAreaType.None]!!
            .getActiveContainerTokens(associationKind, componentState)
    }

    fun getMutedContainerTokens(decorationAreaType: DecorationAreaType): ContainerColorTokens {
        if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
            return this.colorTokensBundleMap[decorationAreaType]!!.getMutedContainerTokens()
        }
        return this.colorTokensBundleMap[DecorationAreaType.None]!!.getMutedContainerTokens()
    }

    fun getMutedContainerTokens(
        decorationAreaType: DecorationAreaType,
        associationKind: ContainerColorTokensAssociationKind
    ): ContainerColorTokens {
        // small optimization - lookup the decoration area only if there
        // are decoration-specific tokens bundles.

        if (this.colorTokensBundleMap.size > 1) {
            if (this.colorTokensBundleMap.containsKey(decorationAreaType)) {
                return this.colorTokensBundleMap[decorationAreaType]!!.getMutedContainerTokens(
                    associationKind
                )
            }
        }
        return this.colorTokensBundleMap[DecorationAreaType.None]!!
            .getMutedContainerTokens(associationKind)
    }

}
