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
package org.pushingpixels.aurora.component.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.lighter
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.colortokens.ContainerColorTokens
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.MutableContainerColorTokens
import org.pushingpixels.aurora.theming.utils.getContainerTokens
import kotlin.math.max

interface ColorTokensDelegate {
    fun getContainerTokensForCurrentState(state: ComponentState): ContainerColorTokens
    fun getContainerTokensForActiveState(state: ComponentState): ContainerColorTokens
}

@OptIn(AuroraInternalApi::class)
fun populateColorTokens(
    colorTokens: MutableContainerColorTokens,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colorTokensDelegate: ColorTokensDelegate
) {
    val currColorTokens = colorTokensDelegate.getContainerTokensForCurrentState(currState)

    var containerSurfaceLowest = currColorTokens.containerSurfaceLowest
    var containerSurfaceLow = currColorTokens.containerSurfaceLow
    var containerSurface = currColorTokens.containerSurface
    var containerSurfaceHigh = currColorTokens.containerSurfaceHigh
    var containerSurfaceHighest = currColorTokens.containerSurfaceHighest
    var containerSurfaceDim = currColorTokens.containerSurfaceDim
    var containerSurfaceBright = currColorTokens.containerSurfaceBright
    var onContainer = currColorTokens.onContainer
    var onContainerVariant = currColorTokens.onContainerVariant
    var containerOutline = currColorTokens.containerOutline
    var containerOutlineVariant = currColorTokens.containerOutlineVariant
    var containerSurfaceDisabledAlpha = currColorTokens.containerSurfaceDisabledAlpha
    var onContainerDisabledAlpha = currColorTokens.onContainerDisabledAlpha
    var containerOutlineDisabledAlpha = currColorTokens.containerOutlineDisabledAlpha
    var inverseContainerSurface = currColorTokens.inverseContainerSurface
    var inverseOnContainer = currColorTokens.inverseOnContainer
    var inverseContainerOutline = currColorTokens.inverseContainerOutline
    var complementaryOnContainer = currColorTokens.complementaryOnContainer
    var complementaryContainerOutline = currColorTokens.complementaryContainerOutline
    var accentOnContainer = currColorTokens.accentOnContainer

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value.contribution
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionTokens = colorTokensDelegate.getContainerTokensForActiveState(contribution.key)

        // And interpolate the colors and alphas
        containerSurfaceLowest = containerSurfaceLowest.interpolateTowards(
            contributionTokens.containerSurfaceLowest, 1.0f - amount)
        containerSurfaceLow = containerSurfaceLow.interpolateTowards(
            contributionTokens.containerSurfaceLow, 1.0f - amount)
        containerSurface = containerSurface.interpolateTowards(
            contributionTokens.containerSurface, 1.0f - amount)
        containerSurfaceHigh = containerSurfaceHigh.interpolateTowards(
            contributionTokens.containerSurfaceHigh, 1.0f - amount)
        containerSurfaceHighest = containerSurfaceHighest.interpolateTowards(
            contributionTokens.containerSurfaceHighest, 1.0f - amount)
        containerSurfaceDim = containerSurfaceDim.interpolateTowards(
            contributionTokens.containerSurfaceDim, 1.0f - amount)
        containerSurfaceBright = containerSurfaceBright.interpolateTowards(
            contributionTokens.containerSurfaceBright, 1.0f - amount)
        onContainer = onContainer.interpolateTowards(
            contributionTokens.onContainer, 1.0f - amount)
        onContainerVariant = onContainerVariant.interpolateTowards(
            contributionTokens.onContainerVariant, 1.0f - amount)
        containerOutline = containerOutline.interpolateTowards(
            contributionTokens.containerOutline, 1.0f - amount)
        containerOutlineVariant = containerOutlineVariant.interpolateTowards(
            contributionTokens.containerOutlineVariant, 1.0f - amount)
        containerSurfaceDisabledAlpha = ((1.0f - amount) * containerSurfaceDisabledAlpha +
                amount * contributionTokens.containerSurfaceDisabledAlpha).coerceIn(0.0f, 1.0f)
        onContainerDisabledAlpha = ((1.0f - amount) * onContainerDisabledAlpha +
                amount * contributionTokens.onContainerDisabledAlpha).coerceIn(0.0f, 1.0f)
        containerOutlineDisabledAlpha = ((1.0f - amount) * containerOutlineDisabledAlpha +
                amount * contributionTokens.containerOutlineDisabledAlpha).coerceIn(0.0f, 1.0f)
        inverseContainerSurface = inverseContainerSurface.interpolateTowards(
            contributionTokens.inverseContainerSurface, 1.0f - amount)
        inverseOnContainer = inverseOnContainer.interpolateTowards(
            contributionTokens.inverseOnContainer, 1.0f - amount)
        inverseContainerOutline = inverseContainerOutline.interpolateTowards(
            contributionTokens.inverseContainerOutline, 1.0f - amount)
        complementaryOnContainer = complementaryOnContainer.interpolateTowards(
            contributionTokens.complementaryOnContainer, 1.0f - amount)
        complementaryContainerOutline = complementaryContainerOutline.interpolateTowards(
            contributionTokens.complementaryContainerOutline, 1.0f - amount)
        accentOnContainer = accentOnContainer.interpolateTowards(
            contributionTokens.accentOnContainer, 1.0f - amount)
    }

    // Update the mutable color tokens with the interpolated colors and alphas
    colorTokens.isDarkAttr = currColorTokens.isDark
    colorTokens.containerSurfaceLowestAttr = containerSurfaceLowest
    colorTokens.containerSurfaceLowAttr = containerSurfaceLow
    colorTokens.containerSurfaceAttr = containerSurfaceLow
    colorTokens.containerSurfaceHighAttr = containerSurfaceHigh
    colorTokens.containerSurfaceHighestAttr = containerSurfaceHighest
    colorTokens.containerSurfaceDimAttr = containerSurfaceDim
    colorTokens.containerSurfaceBrightAttr = containerSurfaceBright
    colorTokens.onContainerAttr = onContainer
    colorTokens.onContainerVariantAttr = onContainerVariant
    colorTokens.containerOutlineAttr = containerOutline
    colorTokens.containerOutlineVariantAttr = containerOutlineVariant
    colorTokens.containerSurfaceDisabledAlphaAttr = containerSurfaceDisabledAlpha
    colorTokens.onContainerDisabledAlphaAttr = onContainerDisabledAlpha
    colorTokens.containerOutlineDisabledAlphaAttr = containerOutlineDisabledAlpha
    colorTokens.inverseContainerSurfaceAttr = inverseContainerSurface
    colorTokens.inverseOnContainerAttr = inverseOnContainer
    colorTokens.inverseContainerOutlineAttr = inverseContainerOutline
    colorTokens.complementaryOnContainerAttr = complementaryOnContainer
    colorTokens.complementaryContainerOutlineAttr = complementaryContainerOutline
    colorTokens.accentOnContainerAttr = accentOnContainer
}

@OptIn(AuroraInternalApi::class)
internal fun populateColorTokens(
    colorTokens: MutableContainerColorTokens,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    associationKind: ContainerColorTokensAssociationKind,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    treatEnabledAsActive: Boolean,
    skipFlatCheck: Boolean,
    inactiveContainerType: ContainerType) {

    val currColorTokens = if (treatEnabledAsActive && (currState == ComponentState.Enabled)) {
        colors.getActiveContainerTokens(decorationAreaType = decorationAreaType)
    } else {
        getContainerTokens(
            colors = colors,
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = currState,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            inactiveContainerType = inactiveContainerType,
            skipFlatCheck = skipFlatCheck
        )
    }

    var containerSurfaceLowest = currColorTokens.containerSurfaceLowest
    var containerSurfaceLow = currColorTokens.containerSurfaceLow
    var containerSurface = currColorTokens.containerSurface
    var containerSurfaceHigh = currColorTokens.containerSurfaceHigh
    var containerSurfaceHighest = currColorTokens.containerSurfaceHighest
    var containerSurfaceDim = currColorTokens.containerSurfaceDim
    var containerSurfaceBright = currColorTokens.containerSurfaceBright
    var onContainer = currColorTokens.onContainer
    var onContainerVariant = currColorTokens.onContainerVariant
    var containerOutline = currColorTokens.containerOutline
    var containerOutlineVariant = currColorTokens.containerOutlineVariant
    var containerSurfaceDisabledAlpha = currColorTokens.containerSurfaceDisabledAlpha
    var onContainerDisabledAlpha = currColorTokens.onContainerDisabledAlpha
    var containerOutlineDisabledAlpha = currColorTokens.containerOutlineDisabledAlpha
    var inverseContainerSurface = currColorTokens.inverseContainerSurface
    var inverseOnContainer = currColorTokens.inverseOnContainer
    var inverseContainerOutline = currColorTokens.inverseContainerOutline
    var complementaryOnContainer = currColorTokens.complementaryOnContainer
    var complementaryContainerOutline = currColorTokens.complementaryContainerOutline
    var accentOnContainer = currColorTokens.accentOnContainer

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value.contribution
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionTokens =
            if (treatEnabledAsActive && (contribution.key == ComponentState.Enabled)) {
                colors.getActiveContainerTokens(decorationAreaType = decorationAreaType)
            } else {
                getContainerTokens(
                    colors = colors,
                    decorationAreaType = decorationAreaType,
                    associationKind = associationKind,
                    componentState = contribution.key,
                    backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                    inactiveContainerType = inactiveContainerType,
                    skipFlatCheck = skipFlatCheck
                )
            }

        // And interpolate the colors and alphas
        containerSurfaceLowest = containerSurfaceLowest.interpolateTowards(
            contributionTokens.containerSurfaceLowest, 1.0f - amount)
        containerSurfaceLow = containerSurfaceLow.interpolateTowards(
            contributionTokens.containerSurfaceLow, 1.0f - amount)
        containerSurface = containerSurface.interpolateTowards(
            contributionTokens.containerSurface, 1.0f - amount)
        containerSurfaceHigh = containerSurfaceHigh.interpolateTowards(
            contributionTokens.containerSurfaceHigh, 1.0f - amount)
        containerSurfaceHighest = containerSurfaceHighest.interpolateTowards(
            contributionTokens.containerSurfaceHighest, 1.0f - amount)
        containerSurfaceDim = containerSurfaceDim.interpolateTowards(
            contributionTokens.containerSurfaceDim, 1.0f - amount)
        containerSurfaceBright = containerSurfaceBright.interpolateTowards(
            contributionTokens.containerSurfaceBright, 1.0f - amount)
        onContainer = onContainer.interpolateTowards(
            contributionTokens.onContainer, 1.0f - amount)
        onContainerVariant = onContainerVariant.interpolateTowards(
            contributionTokens.onContainerVariant, 1.0f - amount)
        containerOutline = containerOutline.interpolateTowards(
            contributionTokens.containerOutline, 1.0f - amount)
        containerOutlineVariant = containerOutlineVariant.interpolateTowards(
            contributionTokens.containerOutlineVariant, 1.0f - amount)
        containerSurfaceDisabledAlpha = ((1.0f - amount) * containerSurfaceDisabledAlpha +
                amount * contributionTokens.containerSurfaceDisabledAlpha).coerceIn(0.0f, 1.0f)
        onContainerDisabledAlpha = ((1.0f - amount) * onContainerDisabledAlpha +
                amount * contributionTokens.onContainerDisabledAlpha).coerceIn(0.0f, 1.0f)
        containerOutlineDisabledAlpha = ((1.0f - amount) * containerOutlineDisabledAlpha +
                amount * contributionTokens.containerOutlineDisabledAlpha).coerceIn(0.0f, 1.0f)
        inverseContainerSurface = inverseContainerSurface.interpolateTowards(
            contributionTokens.inverseContainerSurface, 1.0f - amount)
        inverseOnContainer = inverseOnContainer.interpolateTowards(
            contributionTokens.inverseOnContainer, 1.0f - amount)
        inverseContainerOutline = inverseContainerOutline.interpolateTowards(
            contributionTokens.inverseContainerOutline, 1.0f - amount)
        complementaryOnContainer = complementaryOnContainer.interpolateTowards(
            contributionTokens.complementaryOnContainer, 1.0f - amount)
        complementaryContainerOutline = complementaryContainerOutline.interpolateTowards(
            contributionTokens.complementaryContainerOutline, 1.0f - amount)
        accentOnContainer = accentOnContainer.interpolateTowards(
            contributionTokens.accentOnContainer, 1.0f - amount)

        //println("\tcontribution of $amount from ${contribution.key} to $backgroundStart")
    }

    // Update the mutable color tokens with the interpolated colors and alphas
    colorTokens.isDarkAttr = currColorTokens.isDark
    colorTokens.containerSurfaceLowestAttr = containerSurfaceLowest
    colorTokens.containerSurfaceLowAttr = containerSurfaceLow
    colorTokens.containerSurfaceAttr = containerSurfaceLow
    colorTokens.containerSurfaceHighAttr = containerSurfaceHigh
    colorTokens.containerSurfaceHighestAttr = containerSurfaceHighest
    colorTokens.containerSurfaceDimAttr = containerSurfaceDim
    colorTokens.containerSurfaceBrightAttr = containerSurfaceBright
    colorTokens.onContainerAttr = onContainer
    colorTokens.onContainerVariantAttr = onContainerVariant
    colorTokens.containerOutlineAttr = containerOutline
    colorTokens.containerOutlineVariantAttr = containerOutlineVariant
    colorTokens.containerSurfaceDisabledAlphaAttr = containerSurfaceDisabledAlpha
    colorTokens.onContainerDisabledAlphaAttr = onContainerDisabledAlpha
    colorTokens.containerOutlineDisabledAlphaAttr = containerOutlineDisabledAlpha
    colorTokens.inverseContainerSurfaceAttr = inverseContainerSurface
    colorTokens.inverseOnContainerAttr = inverseOnContainer
    colorTokens.inverseContainerOutlineAttr = inverseContainerOutline
    colorTokens.complementaryOnContainerAttr = complementaryOnContainer
    colorTokens.complementaryContainerOutlineAttr = complementaryContainerOutline
    colorTokens.accentOnContainerAttr = accentOnContainer
}

@OptIn(AuroraInternalApi::class)
internal fun getStateAwareColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    associationKind: ContainerColorTokensAssociationKind,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    skipFlatCheck: Boolean,
    inactiveContainerType: ContainerType,
    query: (ContainerColorTokens) -> Color,
): Color {
    val currStateTokens = getContainerTokens(
        colors = colors,
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = currState,
        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
        inactiveContainerType = inactiveContainerType,
        skipFlatCheck = skipFlatCheck
    )

    var result = query.invoke(currStateTokens)

    if (currState.isDisabled || modelStateInfo.stateContributionMap.size == 1) {
        // Disabled state or only one active state being tracked
        return result
    }

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value.contribution
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionColorTokens = getContainerTokens(
            colors = colors,
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key,
            backgroundAppearanceStrategy = backgroundAppearanceStrategy,
            inactiveContainerType = inactiveContainerType,
            skipFlatCheck = skipFlatCheck
        )

        // Interpolate the color based on the color tokens and contribution amount
        result = result.interpolateTowards(query.invoke(contributionColorTokens), 1.0f - amount)
    }

    return result
}

@OptIn(AuroraInternalApi::class)
internal fun getTextColor(
    modelStateInfo: ModelStateInfo?,
    currState: ComponentState,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    associationKind: ContainerColorTokensAssociationKind,
    backgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    skipFlatCheck: Boolean,
    inactiveContainerType: ContainerType,
    isTextInFilledArea: Boolean
): Color {
    var activeStates: Map<ComponentState, StateContributionInfo>? =
        modelStateInfo?.stateContributionMap
    var tweakedCurrState = currState
    // Special case for when text is not drawn in the filled area
    if (!isTextInFilledArea) {
        tweakedCurrState =
            if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
        activeStates = null
    }

    val colorTokens = getContainerTokens(
        colors = colors,
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = tweakedCurrState,
        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
        inactiveContainerType = inactiveContainerType,
        skipFlatCheck = skipFlatCheck
    )

    var foreground: Color
    if (tweakedCurrState.isDisabled || activeStates == null || activeStates.size == 1) {
        // Disabled state or only one active state being tracked
        foreground = colorTokens.onContainer
    } else {
        // Get the combined foreground color from all states
        var aggrRed = 0f
        var aggrGreen = 0f
        var aggrBlue = 0f
        for ((activeState, value) in activeStates) {
            val contribution = value.contribution
            val activeColorScheme = getContainerTokens(
                colors = colors,
                decorationAreaType = decorationAreaType,
                associationKind = associationKind,
                componentState = activeState,
                backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                inactiveContainerType = inactiveContainerType,
                skipFlatCheck = skipFlatCheck
            )
            val activeForeground = activeColorScheme.onContainer
            aggrRed += contribution * activeForeground.red
            aggrGreen += contribution * activeForeground.green
            aggrBlue += contribution * activeForeground.blue
        }
        foreground = Color(red = aggrRed, blue = aggrBlue, green = aggrGreen, alpha = 1.0f)
    }

    if (tweakedCurrState.isDisabled) {
        foreground = foreground.withAlpha(colorTokens.onContainerDisabledAlpha)
    }

    return foreground
}

@OptIn(AuroraInternalApi::class)
internal fun getTextSelectionBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType
): Color {
    val activeStates = modelStateInfo.stateContributionMap

    var tweakedCurrState = currState
    if (currState == ComponentState.Enabled) {
        // Treat ENABLED state as SELECTED (since we are talking about selections)
        tweakedCurrState = ComponentState.Selected
    }

    val currStateTokens = getContainerTokens(
        colors = colors,
        decorationAreaType = decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Default,
        componentState = currState,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted,
        skipFlatCheck = false
    )
    // TODO: align with Radiance for the surface fill, including the animations
    var result = currStateTokens.containerSurface
    if (!tweakedCurrState.isDisabled && (activeStates.size > 1)) {
        // If we have more than one active state, compute the composite color from all
        // the contributions
        for (activeEntry in activeStates.entries) {
            var activeState = activeEntry.key
            if (activeState === tweakedCurrState) {
                continue
            }
            if (activeState === ComponentState.Enabled) {
                // Treat ENABLED state as SELECTED (since we are talking about selections)
                activeState = ComponentState.Selected
            }
            val contribution: Float = activeEntry.value.contribution
            if (contribution == 0.0f) {
                continue
            }

            val activeStateTokens = getContainerTokens(
                colors = colors,
                decorationAreaType = decorationAreaType,
                associationKind = ContainerColorTokensAssociationKind.Default,
                componentState = activeState,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                inactiveContainerType = ContainerType.Muted,
                skipFlatCheck = false
            )
            // TODO: align with Radiance for the surface fill, including the animations
            var active = activeStateTokens.containerSurface
            result = result.interpolateTowards(active, 1.0f - contribution)
        }
    }
    return result
}

@OptIn(AuroraInternalApi::class)
internal fun getTextFillBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType
): Color {
    val stateForQuery =
        if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
    val tokens = getContainerTokens(
        colors = colors,
        decorationAreaType = decorationAreaType,
        associationKind = ContainerColorTokensAssociationKind.Default,
        componentState = stateForQuery,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted,
        skipFlatCheck = false
    )

    // TODO: align with Radiance for the surface fill, including the animations
    var textBackgroundFillColor = tokens.containerSurface

    val lightnessFactor = if (tokens.isDark) 0.1f else 0.4f
    var lighterFill = textBackgroundFillColor.lighter(lightnessFactor)
    lighterFill = lighterFill.interpolateTowards(textBackgroundFillColor, 0.6f)
    val selectionStrength = modelStateInfo.strength(ComponentStateFacet.Selection)
    val rolloverStrength = modelStateInfo.strength(ComponentStateFacet.Rollover)
    val activeStrength = max(selectionStrength, rolloverStrength) / 4.0f
    textBackgroundFillColor = lighterFill.interpolateTowards(
        textBackgroundFillColor, activeStrength
    )
    return textBackgroundFillColor
}

