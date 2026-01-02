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
package org.pushingpixels.aurora.theming.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.overlayWith
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.ContainerColorTokensAssociationKind
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.DecorationAreaType

internal fun getRolloverUnselectedTokens(baseTokens: ContainerColorTokens): ContainerColorTokens {
    // Mixing in 20% of surface bright on top of base
    return baseTokens.overlay(baseTokens.containerSurfaceBright, 0.2f)
}

internal fun getRolloverSelectedTokens(baseTokens: ContainerColorTokens): ContainerColorTokens {
    // Mixing in 30% of surface bright on top of base
    return baseTokens.overlay(baseTokens.containerSurfaceBright, 0.3f)
}

internal fun getPressedUnselectedTokens(baseTokens: ContainerColorTokens): ContainerColorTokens {
    // Mixing in 50% of surface dim on top
    return baseTokens.overlay(baseTokens.containerSurfaceDim, 0.5f)
}

internal fun getPressedSelectedTokens(baseTokens: ContainerColorTokens): ContainerColorTokens {
    // Mixing in 40% of surface dim on top
    return baseTokens.overlay(baseTokens.containerSurfaceDim, 0.4f)
}

private fun ContainerColorTokens.overlay(overlay: Color, overlayAmount: Float): ContainerColorTokens {
    val original = this
    val overlayWithAlpha: Color = overlay.withAlpha(overlayAmount)

    // Apply overlay on the container tokens
    val containerSurfaceLowest: Color = original.containerSurfaceLowest.overlayWith(overlayWithAlpha)
    val containerSurfaceLow: Color = original.containerSurfaceLow.overlayWith(overlayWithAlpha)
    val containerSurface: Color = original.containerSurface.overlayWith(overlayWithAlpha)
    val containerSurfaceHigh: Color = original.containerSurfaceHigh.overlayWith(overlayWithAlpha)
    val containerSurfaceHighest: Color = original.containerSurfaceHighest.overlayWith(overlayWithAlpha)
    val inverseContainerSurface: Color = original.inverseContainerSurface.overlayWith(overlayWithAlpha)
    val containerSurfaceDim: Color = original.containerSurfaceDim.overlayWith(overlayWithAlpha)
    val containerSurfaceBright: Color = original.containerSurfaceBright.overlayWith(overlayWithAlpha)

    // Leave on container and container outline tokens as they are
    val onContainer: Color = original.onContainer
    val onContainerVariant: Color = original.onContainerVariant
    val containerOutline: Color = original.containerOutline
    val containerOutlineVariant: Color = original.containerOutlineVariant

    val inverseOnContainer: Color = original.inverseOnContainer
    val inverseContainerOutline: Color = original.inverseContainerOutline
    val complementaryOnContainer: Color = original.complementaryOnContainer
    val complementaryContainerOutline: Color = original.complementaryContainerOutline
    val accentOnContainer: Color = original.accentOnContainer

    return object : ContainerColorTokens {
        override val isDark: Boolean
            get() = original.isDark

        override val containerSurfaceLowest: Color
            get() = containerSurfaceLowest

        override val containerSurfaceLow: Color
            get() = containerSurfaceLow

        override val containerSurface: Color
            get() = containerSurface

        override val containerSurfaceHigh: Color
            get() = containerSurfaceHigh

        override val containerSurfaceHighest: Color
            get() = containerSurfaceHighest

        override  val containerSurfaceDim: Color
            get() = containerSurfaceDim

        override val containerSurfaceBright: Color
            get() = containerSurfaceBright

        override val onContainer: Color
            get() = onContainer

        override val onContainerVariant: Color
            get() = onContainerVariant

        override val containerOutline: Color
            get() = containerOutline

        override val containerOutlineVariant: Color
            get() = containerOutlineVariant

        override val containerSurfaceDisabledAlpha: Float
            get() = original.containerSurfaceDisabledAlpha

        override val onContainerDisabledAlpha: Float
            get() = original.onContainerDisabledAlpha

        override val containerOutlineDisabledAlpha: Float
            get() = original.containerOutlineDisabledAlpha

        override val inverseContainerSurface: Color
            get() = inverseContainerSurface

        override val inverseOnContainer: Color
            get() = inverseOnContainer

        override val inverseContainerOutline: Color
            get() = inverseContainerOutline

        override val complementaryOnContainer: Color
            get() = complementaryOnContainer

        override val complementaryContainerOutline: Color
            get() = complementaryContainerOutline

        override val accentOnContainer: Color
            get() = accentOnContainer
    }
}

/**
 * Enumeration of available container types. Each entry corresponds to the matching container
 * color tokens passed to [org.pushingpixels.aurora.theming.ContainerColorTokensBundle].
 */
@AuroraInternalApi
enum class ContainerType {
    Neutral,
    Muted,
    Active
}

@AuroraInternalApi
fun getActiveContainerTokens(
    colors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
    componentState: ComponentState
): ContainerColorTokens {

    val tokensOverlay = tokensOverlayProvider?.getOverlay(colors, decorationAreaType)

    val result = if (tokensOverlay != null)
        tokensOverlay.getActiveContainerTokens(componentState)
    else
        colors.getActiveContainerTokens(decorationAreaType, componentState)
    return result
}

@AuroraInternalApi
fun getContainerTokens(
    colors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
    componentState: ComponentState,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    inactiveContainerType: ContainerType
): ContainerColorTokens {
    var componentState = componentState

    val tokensOverlay = tokensOverlayProvider?.getOverlay(colors, decorationAreaType)

    // special case - if the component is marked as flat and
    // it is in the default state, or it is a component
    // that is never painting its background - get the color tokens of the
    // parent
    val isNeverPainted = (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Never)
    val isFlat = (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat)
    if (isNeverPainted || (!componentState.isActive && isFlat)) {
        val result: ContainerColorTokens = if (tokensOverlay != null)
            tokensOverlay.neutralContainerTokens
        else
            colors.getNeutralContainerTokens(decorationAreaType)
        return result
    }

    if (componentState.isDisabled) {
        componentState = componentState.enabledMatch!!
    }
    val result: ContainerColorTokens
    if (componentState.isActive) {
        result = if (tokensOverlay != null)
            tokensOverlay.getActiveContainerTokens(componentState)
        else
            colors.getActiveContainerTokens(decorationAreaType, componentState)
    } else {
        if (inactiveContainerType == ContainerType.Muted) {
            result = if (tokensOverlay != null)
                tokensOverlay.mutedContainerTokens
            else
                colors.getMutedContainerTokens(decorationAreaType)
        } else {
            result = if (tokensOverlay != null)
                tokensOverlay.neutralContainerTokens
            else
                colors.getNeutralContainerTokens(decorationAreaType)
        }
    }

    return result
}

/**
 * Returns the color tokens that match the passed parameters.
 *
 * @param colors       Skin colors.
 * @param associationKind Association kind.
 * @param componentState  Component state.
 * @return Component color tokens.
 */
@AuroraInternalApi
fun getContainerTokens(
    colors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
    associationKind: ContainerColorTokensAssociationKind,
    componentState: ComponentState,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    inactiveContainerType: ContainerType
): ContainerColorTokens {
    return getContainerTokens(colors, tokensOverlayProvider, decorationAreaType, associationKind,
        componentState, backgroundAppearanceStrategy, inactiveContainerType, false)
}

/**
 * Returns the color tokens that match the passed parameters.
 *
 * @param colors       Skin colors.
 * @param associationKind Association kind.
 * @param componentState  Component state.
 * @return Component color tokens.
 */
@AuroraInternalApi
fun getContainerTokens(
    colors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    decorationAreaType: DecorationAreaType,
    associationKind: ContainerColorTokensAssociationKind,
    componentState: ComponentState,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    inactiveContainerType: ContainerType,
    skipFlatCheck: Boolean
): ContainerColorTokens {
    var componentState = componentState

    val tokensOverlay = tokensOverlayProvider?.getOverlay(colors, decorationAreaType)

    // special case - if the component is marked as flat, get the color tokens of the parent.
    // However, flat toolbars should be ignored, since they are
    // the "top" level decoration area.
    if (!skipFlatCheck && !componentState.isActive && (backgroundAppearanceStrategy == BackgroundAppearanceStrategy.Flat)) {
        val result: ContainerColorTokens = if (tokensOverlay != null)
            tokensOverlay.neutralContainerTokens
        else
            colors.getNeutralContainerTokens(decorationAreaType)
        return result
    }

    if (componentState.isDisabled) {
        componentState = componentState.enabledMatch!!
    }
    val result: ContainerColorTokens
    if (componentState.isActive) {
        result = if (tokensOverlay != null)
            tokensOverlay.getActiveContainerTokens(componentState)
        else
            colors.getActiveContainerTokens(decorationAreaType, associationKind, componentState)
    } else {
        if (inactiveContainerType == ContainerType.Neutral) {
            result = if (tokensOverlay != null)
                tokensOverlay.neutralContainerTokens
            else
                colors.getNeutralContainerTokens(decorationAreaType, associationKind)
        } else {
            result = if (tokensOverlay != null)
                tokensOverlay.mutedContainerTokens
            else
                colors.getMutedContainerTokens(decorationAreaType, associationKind)
        }
    }
    return result
}


