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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.utils.getPressedSelectedTokens
import org.pushingpixels.aurora.theming.utils.getPressedUnselectedTokens
import org.pushingpixels.aurora.theming.utils.getRolloverSelectedTokens
import org.pushingpixels.aurora.theming.utils.getRolloverUnselectedTokens
import org.pushingpixels.ephemeral.chroma.dynamiccolor.ContainerConfiguration
import org.pushingpixels.ephemeral.chroma.hct.Hct
import org.pushingpixels.ephemeral.chroma.palettes.TonalPalette
import org.pushingpixels.aurora.theming.palette.getContainerTokens as getPaletteContainerTokens

class ContainerColorTokensOverlay(
    val activeContainerTokens: ContainerColorTokens,
    val mutedContainerTokens: ContainerColorTokens,
    val neutralContainerTokens: ContainerColorTokens
) {
    private val activeTokenOverrides: MutableMap<ComponentState, ContainerColorTokens> = hashMapOf()

    fun interface Provider {
        fun getOverlay(skinColors: AuroraSkinColors, decorationAreaType: DecorationAreaType): ContainerColorTokensOverlay
    }

    abstract class DefaultOverlayProvider : Provider {
        private val overlays: MutableMap<Pair<ContainerColorTokens, ContainerColorTokens>, ContainerColorTokensOverlay> =
            hashMapOf()

        protected abstract fun getContainerTokens(
            skinColors: AuroraSkinColors,
            decorationAreaType: DecorationAreaType
        ): ContainerColorTokens

        protected abstract fun getInverseContainerTokens(
            skin: AuroraSkinColors,
            decorationAreaType: DecorationAreaType
        ): ContainerColorTokens

        override fun getOverlay(
            skinColors: AuroraSkinColors,
            decorationAreaType: DecorationAreaType
        ): ContainerColorTokensOverlay {
            val systemContainerTokens: ContainerColorTokens = this.getContainerTokens(
                skinColors, decorationAreaType
            )
            val inverseSystemContainerTokens =
                this.getInverseContainerTokens(skinColors, decorationAreaType)
            val key = Pair(systemContainerTokens, inverseSystemContainerTokens)

            var result: ContainerColorTokensOverlay? = this.overlays[key]
            if (result == null) {
                val neutralContainerTokens = object : ContainerColorTokens {
                    override val isDark: Boolean
                        get() = systemContainerTokens.isDark

                    override val containerSurfaceLowest: Color
                        get() = systemContainerTokens.containerSurfaceLowest

                    override val containerSurfaceLow: Color
                        get() = systemContainerTokens.containerSurfaceLow

                    override val containerSurface: Color
                        get() = systemContainerTokens.containerSurface

                    override val containerSurfaceHigh: Color
                        get() = systemContainerTokens.containerSurfaceHigh

                    override val containerSurfaceHighest: Color
                        get() = systemContainerTokens.containerSurfaceHighest

                    override val containerSurfaceDim: Color
                        get() = systemContainerTokens.containerSurfaceDim

                    override val containerSurfaceBright: Color
                        get() = systemContainerTokens.containerSurfaceBright

                    override val onContainer: Color
                        get() = systemContainerTokens.accentOnContainer

                    override val onContainerVariant: Color
                        get() = systemContainerTokens.accentOnContainer.withAlpha(0.95f)

                    override val containerOutline: Color
                        get() = systemContainerTokens.containerOutline

                    override val containerOutlineVariant: Color
                        get() = systemContainerTokens.containerOutlineVariant

                    override val containerSurfaceDisabledAlpha: Float
                        get() = systemContainerTokens.containerSurfaceDisabledAlpha

                    override val onContainerDisabledAlpha: Float
                        get() = systemContainerTokens.onContainerDisabledAlpha

                    override val containerOutlineDisabledAlpha: Float
                        get() = systemContainerTokens.containerOutlineDisabledAlpha

                    override val inverseContainerSurface: Color
                        get() = systemContainerTokens.inverseContainerSurface

                    override val inverseOnContainer: Color
                        get() = systemContainerTokens.inverseOnContainer

                    override val inverseContainerOutline: Color
                        get() = systemContainerTokens.inverseContainerOutline

                    override val complementaryOnContainer: Color
                        get() = systemContainerTokens.complementaryOnContainer

                    override val complementaryContainerOutline: Color
                        get() = systemContainerTokens.complementaryContainerOutline

                    override val accentOnContainer: Color
                        get() = systemContainerTokens.accentOnContainer
                }

                result = ContainerColorTokensOverlay(
                    inverseSystemContainerTokens,
                    systemContainerTokens,
                    neutralContainerTokens
                )
                overlays[key] = result
            }

            return result
        }
    }

    protected abstract class DefaultMenuOverlayProvider : Provider {
        private val overlays: MutableMap<ContainerColorTokens, ContainerColorTokensOverlay> = hashMapOf()

        protected abstract fun getContainerTokens(
            skinColors: AuroraSkinColors,
            decorationAreaType: DecorationAreaType
        ): ContainerColorTokens

        override fun getOverlay(
            skinColors: AuroraSkinColors,
            decorationAreaType: DecorationAreaType
        ): ContainerColorTokensOverlay {
            val systemContainerTokens = this.getContainerTokens(skinColors, decorationAreaType)
            val key = systemContainerTokens

            var result = this.overlays.get(key)
            if (result == null) {
                val neutralContainerTokens = object : ContainerColorTokens {
                    override val isDark: Boolean
                        get() = systemContainerTokens.isDark

                    override val containerSurfaceLowest: Color
                        get() = systemContainerTokens.containerSurfaceLowest

                    override val containerSurfaceLow: Color
                        get() = systemContainerTokens.containerSurfaceLow

                    override val containerSurface: Color
                        get() = systemContainerTokens.containerSurface

                    override val containerSurfaceHigh: Color
                        get() = systemContainerTokens.containerSurfaceHigh

                    override val containerSurfaceHighest: Color
                        get() = systemContainerTokens.containerSurfaceHighest

                    override val containerSurfaceDim: Color
                        get() = systemContainerTokens.containerSurfaceDim

                    override val containerSurfaceBright: Color
                        get() = systemContainerTokens.containerSurfaceBright

                    override val onContainer: Color
                        get() = systemContainerTokens.accentOnContainer

                    override val onContainerVariant: Color
                        get() = systemContainerTokens.accentOnContainer.withAlpha(0.95f)

                    override val containerOutline: Color
                        get() = systemContainerTokens.containerOutline

                    override val containerOutlineVariant: Color
                        get() = systemContainerTokens.containerOutlineVariant

                    override val containerSurfaceDisabledAlpha: Float
                        get() = systemContainerTokens.containerSurfaceDisabledAlpha

                    override val onContainerDisabledAlpha: Float
                        get() = systemContainerTokens.onContainerDisabledAlpha

                    override val containerOutlineDisabledAlpha: Float
                        get() = systemContainerTokens.containerOutlineDisabledAlpha

                    override val inverseContainerSurface: Color
                        get() = systemContainerTokens.inverseContainerSurface

                    override val inverseOnContainer: Color
                        get() = systemContainerTokens.inverseOnContainer

                    override val inverseContainerOutline: Color
                        get() = systemContainerTokens.inverseContainerOutline

                    override val complementaryOnContainer: Color
                        get() = systemContainerTokens.complementaryOnContainer

                    override val complementaryContainerOutline: Color
                        get() = systemContainerTokens.complementaryContainerOutline

                    override val accentOnContainer: Color
                        get() = systemContainerTokens.accentOnContainer
                }

                result = ContainerColorTokensOverlay(
                    systemContainerTokens,
                    systemContainerTokens,
                    neutralContainerTokens
                )
                this.overlays[key] = result
            }

            return result
        }
    }

    fun getActiveContainerTokens(componentState: ComponentState): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokens(componentState.enabledMatch!!)
        }

        require(componentState.isActive) { "Only active states supported" }

        val registered: ContainerColorTokens? = this.activeTokenOverrides[componentState]
        if (registered != null) {
            return registered
        } else {
            return this.getActiveContainerTokensForState(componentState)
        }
    }

    private fun getActiveContainerTokensForState(componentState: ComponentState): ContainerColorTokens {
        if (componentState.isDisabled) {
            return getActiveContainerTokensForState(componentState.enabledMatch!!)
        }

        val activeTokens = this.activeContainerTokens

        if (componentState === ComponentState.PressedUnselected) {
            if (!this.activeTokenOverrides.containsKey(componentState)) {
                this.activeTokenOverrides[componentState] = getPressedUnselectedTokens(activeTokens)
            }
            return this.activeTokenOverrides[componentState]!!
        }
        if (componentState === ComponentState.PressedSelected) {
            if (!this.activeTokenOverrides.containsKey(componentState)) {
                this.activeTokenOverrides[componentState] = getPressedSelectedTokens(activeTokens)
            }
            return this.activeTokenOverrides[componentState]!!
        }
        if (componentState === ComponentState.Selected) {
            if (!this.activeTokenOverrides.containsKey(componentState)) {
                this.activeTokenOverrides[componentState] = activeTokens
            }
            return this.activeTokenOverrides[componentState]!!
        }
        if (componentState === ComponentState.RolloverUnselected) {
            if (!this.activeTokenOverrides.containsKey(componentState)) {
                this.activeTokenOverrides[componentState] = getRolloverUnselectedTokens(activeTokens)
            }
            return this.activeTokenOverrides[componentState]!!
        }
        if (componentState === ComponentState.RolloverSelected) {
            if (!this.activeTokenOverrides.containsKey(componentState)) {
                this.activeTokenOverrides[componentState] = getRolloverSelectedTokens(activeTokens)
            }
            return this.activeTokenOverrides[componentState]!!
        }

        val hardFallback: ComponentState? = componentState.hardFallback
        if (hardFallback != null) {
            return this.getActiveContainerTokensForState(hardFallback)
        }

        if (componentState === ComponentState.Enabled) {
            return this.mutedContainerTokens
        }
        return activeTokens
    }

    companion object {
        fun defaultSystemOverlayProvider(
            systemContainerType: SystemContainerType
        ): Provider {
            return object : DefaultOverlayProvider() {
                override fun getContainerTokens(
                    skinColors: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    return skinColors.getSystemContainerTokens(decorationAreaType, systemContainerType)
                }

                override fun getInverseContainerTokens(
                    skin: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    return skin.getInverseSystemContainerTokens(decorationAreaType, systemContainerType)
                }
            }
        }

        fun defaultOverlayProvider(seed: Color): Provider {
            return object : DefaultOverlayProvider() {
                private val lightTokens = getPaletteContainerTokens(
                    seed = TonalPalette.fromHct(Hct.fromInt(seed.toArgb())).getHct(85.0),
                    containerConfiguration = ContainerConfiguration.defaultLight()
                )
                private val darkTokens = getPaletteContainerTokens(
                    seed = TonalPalette.fromHct(Hct.fromInt(seed.toArgb())).getHct(40.0),
                    containerConfiguration = ContainerConfiguration.defaultDark()
                )

                override fun getContainerTokens(
                    skinColors: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    val neutrals: ContainerColorTokens = skinColors.getNeutralContainerTokens(decorationAreaType)
                    return if (neutrals.isDark) darkTokens else lightTokens
                }

                override fun getInverseContainerTokens(
                    skin: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    val neutrals: ContainerColorTokens = skin.getNeutralContainerTokens(decorationAreaType)
                    return if (neutrals.isDark) lightTokens else darkTokens
                }
            }
        }

        fun defaultMenuSystemOverlayProvider(systemContainerType: SystemContainerType): Provider {
            return object : DefaultMenuOverlayProvider() {
                override fun getContainerTokens(
                    skinColors: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    return skinColors.getSystemContainerTokens(decorationAreaType, systemContainerType)
                }
            }
        }

        fun defaultMenuOverlayProvider(seed: Color): Provider {
            return object : DefaultMenuOverlayProvider() {
                private val lightTokens = getPaletteContainerTokens(
                    seed = TonalPalette.fromHct(Hct.fromInt(seed.toArgb())).getHct(85.0),
                    containerConfiguration = ContainerConfiguration.defaultLight()
                )
                private val darkTokens = getPaletteContainerTokens(
                    seed = TonalPalette.fromHct(Hct.fromInt(seed.toArgb())).getHct(40.0),
                    containerConfiguration = ContainerConfiguration.defaultDark()
                )

                override fun getContainerTokens(
                    skinColors: AuroraSkinColors,
                    decorationAreaType: DecorationAreaType
                ): ContainerColorTokens {
                    val neutrals = skinColors.getNeutralContainerTokens(decorationAreaType)
                    return if (neutrals.isDark) darkTokens else lightTokens
                }
            }
        }
    }
}

