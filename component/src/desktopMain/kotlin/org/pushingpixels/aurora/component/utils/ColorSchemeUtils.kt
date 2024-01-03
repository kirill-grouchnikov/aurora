/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.byAlpha
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.lighter
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import kotlin.math.max

interface ColorSchemeDelegate {
    fun getColorSchemeForCurrentState(state: ComponentState): AuroraColorScheme
    fun getColorSchemeForActiveState(state: ComponentState): AuroraColorScheme
}

@OptIn(AuroraInternalApi::class)
@Composable
fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colorSchemeDelegate: ColorSchemeDelegate
) {
    val currStateScheme = colorSchemeDelegate.getColorSchemeForCurrentState(currState)

    var ultraLight = currStateScheme.ultraLightColor
    var extraLight = currStateScheme.extraLightColor
    var light = currStateScheme.lightColor
    var mid = currStateScheme.midColor
    var dark = currStateScheme.darkColor
    var ultraDark = currStateScheme.ultraDarkColor
    var foreground = currStateScheme.foregroundColor
    var backgroundFill = currStateScheme.backgroundFillColor
    var accentedBackgroundFill = currStateScheme.accentedBackgroundFillColor
    var focusRing = currStateScheme.focusRingColor
    var line = currStateScheme.lineColor
    var selectionForeground = currStateScheme.selectionForegroundColor
    var selectionBackground = currStateScheme.selectionBackgroundColor
    var textBackgroundFill = currStateScheme.textBackgroundFillColor
    var separatorPrimary = currStateScheme.separatorPrimaryColor
    var separatorSecondary = currStateScheme.separatorSecondaryColor
    var mark = currStateScheme.markColor
    var echo = currStateScheme.echoColor

    //println("Starting with $currState at $backgroundStart")

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
        val contributionScheme = colorSchemeDelegate.getColorSchemeForActiveState(contribution.key)

        // And interpolate the colors
        ultraLight = ultraLight.interpolateTowards(contributionScheme.ultraLightColor, 1.0f - amount)
        extraLight = extraLight.interpolateTowards(contributionScheme.extraLightColor, 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor, 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor, 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor, 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor, 1.0f - amount)
        foreground = foreground.interpolateTowards(contributionScheme.foregroundColor, 1.0f - amount)
        backgroundFill = backgroundFill.interpolateTowards(contributionScheme.backgroundFillColor, 1.0f - amount)
        accentedBackgroundFill =
            accentedBackgroundFill.interpolateTowards(contributionScheme.accentedBackgroundFillColor, 1.0f - amount)
        focusRing = focusRing.interpolateTowards(contributionScheme.focusRingColor, 1.0f - amount)
        line = line.interpolateTowards(contributionScheme.lineColor, 1.0f - amount)
        selectionForeground =
            selectionForeground.interpolateTowards(contributionScheme.selectionForegroundColor, 1.0f - amount)
        selectionBackground =
            selectionBackground.interpolateTowards(contributionScheme.selectionBackgroundColor, 1.0f - amount)
        textBackgroundFill =
            textBackgroundFill.interpolateTowards(contributionScheme.textBackgroundFillColor, 1.0f - amount)
        separatorPrimary = separatorPrimary.interpolateTowards(contributionScheme.separatorPrimaryColor, 1.0f - amount)
        separatorSecondary =
            separatorSecondary.interpolateTowards(contributionScheme.separatorSecondaryColor, 1.0f - amount)
        mark = mark.interpolateTowards(contributionScheme.markColor, 1.0f - amount)
        echo = echo.interpolateTowards(contributionScheme.echoColor, 1.0f - amount)

        //println("\tcontribution of $amount from ${contribution.key} to $backgroundStart")
    }

    // Update the mutable color scheme with the interpolated colors
    colorScheme.ultraLight = ultraLight
    colorScheme.extraLight = extraLight
    colorScheme.light = light
    colorScheme.mid = mid
    colorScheme.dark = dark
    colorScheme.ultraDark = ultraDark
    colorScheme.foreground = foreground
    colorScheme.backgroundFill = backgroundFill
    colorScheme.accentedBackgroundFill = accentedBackgroundFill
    colorScheme.focusRing = focusRing
    colorScheme.line = line
    colorScheme.selectionForeground = selectionForeground
    colorScheme.selectionBackground = selectionBackground
    colorScheme.textBackgroundFill = textBackgroundFill
    colorScheme.separatorPrimary = separatorPrimary
    colorScheme.separatorSecondary = separatorSecondary
    colorScheme.mark = mark
    colorScheme.echo = echo
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind,
    treatEnabledAsActive: Boolean = false
) {
    val currStateScheme = if (treatEnabledAsActive && (currState == ComponentState.Enabled))
        colorSchemeBundle?.getActiveColorScheme()
            ?: AuroraSkin.colors.getActiveColorScheme(decorationAreaType = decorationAreaType) else
        colorSchemeBundle?.getColorScheme(
            associationKind = associationKind,
            componentState = currState,
            allowFallback = true
        ) ?: AuroraSkin.colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = currState
        )

    var ultraLight = currStateScheme.ultraLightColor
    var extraLight = currStateScheme.extraLightColor
    var light = currStateScheme.lightColor
    var mid = currStateScheme.midColor
    var dark = currStateScheme.darkColor
    var ultraDark = currStateScheme.ultraDarkColor
    var foreground = currStateScheme.foregroundColor
    var backgroundFill = currStateScheme.backgroundFillColor
    var accentedBackgroundFill = currStateScheme.accentedBackgroundFillColor
    var focusRing = currStateScheme.focusRingColor
    var line = currStateScheme.lineColor
    var selectionForeground = currStateScheme.selectionForegroundColor
    var selectionBackground = currStateScheme.selectionBackgroundColor
    var textBackgroundFill = currStateScheme.textBackgroundFillColor
    var separatorPrimary = currStateScheme.separatorPrimaryColor
    var separatorSecondary = currStateScheme.separatorSecondaryColor
    var mark = currStateScheme.markColor
    var echo = currStateScheme.echoColor

    //println("Starting with $currState at $backgroundStart")

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
        val contributionScheme =
            if (treatEnabledAsActive && (contribution.key == ComponentState.Enabled))
                colorSchemeBundle?.getActiveColorScheme()
                    ?: AuroraSkin.colors.getActiveColorScheme(decorationAreaType = decorationAreaType) else
                colorSchemeBundle?.getColorScheme(
                    associationKind = associationKind,
                    componentState = contribution.key,
                    allowFallback = true
                ) ?: AuroraSkin.colors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    associationKind = associationKind,
                    componentState = contribution.key
                )

        // And interpolate the colors
        ultraLight = ultraLight.interpolateTowards(contributionScheme.ultraLightColor, 1.0f - amount)
        extraLight = extraLight.interpolateTowards(contributionScheme.extraLightColor, 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor, 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor, 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor, 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor, 1.0f - amount)
        foreground = foreground.interpolateTowards(contributionScheme.foregroundColor, 1.0f - amount)
        backgroundFill = backgroundFill.interpolateTowards(contributionScheme.backgroundFillColor, 1.0f - amount)
        accentedBackgroundFill =
            accentedBackgroundFill.interpolateTowards(contributionScheme.accentedBackgroundFillColor, 1.0f - amount)
        focusRing = focusRing.interpolateTowards(contributionScheme.focusRingColor, 1.0f - amount)
        line = line.interpolateTowards(contributionScheme.lineColor, 1.0f - amount)
        selectionForeground =
            selectionForeground.interpolateTowards(contributionScheme.selectionForegroundColor, 1.0f - amount)
        selectionBackground =
            selectionBackground.interpolateTowards(contributionScheme.selectionBackgroundColor, 1.0f - amount)
        textBackgroundFill =
            textBackgroundFill.interpolateTowards(contributionScheme.textBackgroundFillColor, 1.0f - amount)
        separatorPrimary = separatorPrimary.interpolateTowards(contributionScheme.separatorPrimaryColor, 1.0f - amount)
        separatorSecondary =
            separatorSecondary.interpolateTowards(contributionScheme.separatorSecondaryColor, 1.0f - amount)
        mark = mark.interpolateTowards(contributionScheme.markColor, 1.0f - amount)
        echo = echo.interpolateTowards(contributionScheme.echoColor, 1.0f - amount)

        //println("\tcontribution of $amount from ${contribution.key} to $backgroundStart")
    }

    // Update the mutable color scheme with the interpolated colors
    colorScheme.ultraLight = ultraLight
    colorScheme.extraLight = extraLight
    colorScheme.light = light
    colorScheme.mid = mid
    colorScheme.dark = dark
    colorScheme.ultraDark = ultraDark
    colorScheme.foreground = foreground
    colorScheme.backgroundFill = backgroundFill
    colorScheme.accentedBackgroundFill = accentedBackgroundFill
    colorScheme.focusRing = focusRing
    colorScheme.line = line
    colorScheme.selectionForeground = selectionForeground
    colorScheme.selectionBackground = selectionBackground
    colorScheme.textBackgroundFill = textBackgroundFill
    colorScheme.separatorPrimary = separatorPrimary
    colorScheme.separatorSecondary = separatorSecondary
    colorScheme.mark = mark
    colorScheme.echo = echo
}

internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfoSnapshot,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind
) {
    val currStateScheme = colorSchemeBundle?.getColorScheme(
        associationKind = associationKind,
        componentState = modelStateInfo.currModelState,
        allowFallback = true
    ) ?: skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = modelStateInfo.currModelState
    )

    var ultraLight = currStateScheme.ultraLightColor
    var extraLight = currStateScheme.extraLightColor
    var light = currStateScheme.lightColor
    var mid = currStateScheme.midColor
    var dark = currStateScheme.darkColor
    var ultraDark = currStateScheme.ultraDarkColor
    var foreground = currStateScheme.foregroundColor
    var backgroundFill = currStateScheme.backgroundFillColor
    var accentedBackgroundFill = currStateScheme.accentedBackgroundFillColor
    var focusRing = currStateScheme.focusRingColor
    var line = currStateScheme.lineColor
    var selectionForeground = currStateScheme.selectionForegroundColor
    var selectionBackground = currStateScheme.selectionBackgroundColor
    var textBackgroundFill = currStateScheme.textBackgroundFillColor
    var separatorPrimary = currStateScheme.separatorPrimaryColor
    var separatorSecondary = currStateScheme.separatorSecondaryColor
    var mark = currStateScheme.markColor
    var echo = currStateScheme.echoColor

    //println("Starting with ${modelStateInfo.currModelState} at ${mark.hexadecimal}")

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == modelStateInfo.currModelState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionScheme = colorSchemeBundle?.getColorScheme(
            associationKind = associationKind,
            componentState = contribution.key,
            allowFallback = true
        ) ?: skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )

        // And interpolate the colors
        ultraLight = ultraLight.interpolateTowards(contributionScheme.ultraLightColor, 1.0f - amount)
        extraLight = extraLight.interpolateTowards(contributionScheme.extraLightColor, 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor, 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor, 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor, 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor, 1.0f - amount)
        foreground = foreground.interpolateTowards(contributionScheme.foregroundColor, 1.0f - amount)
        backgroundFill = backgroundFill.interpolateTowards(contributionScheme.backgroundFillColor, 1.0f - amount)
        accentedBackgroundFill =
            accentedBackgroundFill.interpolateTowards(contributionScheme.accentedBackgroundFillColor, 1.0f - amount)
        focusRing = focusRing.interpolateTowards(contributionScheme.focusRingColor, 1.0f - amount)
        line = line.interpolateTowards(contributionScheme.lineColor, 1.0f - amount)
        selectionForeground =
            selectionForeground.interpolateTowards(contributionScheme.selectionForegroundColor, 1.0f - amount)
        selectionBackground =
            selectionBackground.interpolateTowards(contributionScheme.selectionBackgroundColor, 1.0f - amount)
        textBackgroundFill =
            textBackgroundFill.interpolateTowards(contributionScheme.textBackgroundFillColor, 1.0f - amount)
        separatorPrimary = separatorPrimary.interpolateTowards(contributionScheme.separatorPrimaryColor, 1.0f - amount)
        separatorSecondary =
            separatorSecondary.interpolateTowards(contributionScheme.separatorSecondaryColor, 1.0f - amount)
        mark = mark.interpolateTowards(contributionScheme.markColor, 1.0f - amount)
        echo = echo.interpolateTowards(contributionScheme.echoColor, 1.0f - amount)

        //println("\tcontribution of $amount from ${contribution.key} to ${mark.hexadecimal}")
    }

    // Update the mutable color scheme with the interpolated colors
    colorScheme.ultraLight = ultraLight
    colorScheme.extraLight = extraLight
    colorScheme.light = light
    colorScheme.mid = mid
    colorScheme.dark = dark
    colorScheme.ultraDark = ultraDark
    colorScheme.foreground = foreground
    colorScheme.backgroundFill = backgroundFill
    colorScheme.accentedBackgroundFill = accentedBackgroundFill
    colorScheme.focusRing = focusRing
    colorScheme.line = line
    colorScheme.selectionForeground = selectionForeground
    colorScheme.selectionBackground = selectionBackground
    colorScheme.textBackgroundFill = textBackgroundFill
    colorScheme.separatorPrimary = separatorPrimary
    colorScheme.separatorSecondary = separatorSecondary
    colorScheme.mark = mark
    colorScheme.echo = echo
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun populateColorSchemeWithHighlightAlpha(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind
) {
    val skinColors = AuroraSkin.colors
    val currStateScheme = colorSchemeBundle?.getColorScheme(
        associationKind = associationKind,
        componentState = currState,
        allowFallback = true
    ) ?: skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = currState
    )
    val currHighlightAlpha = colorSchemeBundle?.getHighlightAlpha(currState) ?: skinColors.getHighlightAlpha(
        decorationAreaType = decorationAreaType,
        componentState = currState
    )
    val currHighlightAmount = currHighlightAlpha * modelStateInfo.stateContributionMap.entries
        .find { it.key == currState }!!.value.contribution

    var ultraLight = currStateScheme.ultraLightColor.byAlpha(currHighlightAmount)
    var extraLight = currStateScheme.extraLightColor.byAlpha(currHighlightAmount)
    var light = currStateScheme.lightColor.byAlpha(currHighlightAmount)
    var mid = currStateScheme.midColor.byAlpha(currHighlightAmount)
    var dark = currStateScheme.darkColor.byAlpha(currHighlightAmount)
    var ultraDark = currStateScheme.ultraDarkColor.byAlpha(currHighlightAmount)
    var foreground = currStateScheme.foregroundColor.byAlpha(currHighlightAmount)
    var backgroundFill = currStateScheme.backgroundFillColor.byAlpha(currHighlightAmount)
    var accentedBackgroundFill = currStateScheme.accentedBackgroundFillColor.byAlpha(currHighlightAmount)
    var focusRing = currStateScheme.focusRingColor.byAlpha(currHighlightAmount)
    var line = currStateScheme.lineColor.byAlpha(currHighlightAmount)
    var selectionForeground = currStateScheme.selectionForegroundColor.byAlpha(currHighlightAmount)
    var selectionBackground = currStateScheme.selectionBackgroundColor.byAlpha(currHighlightAmount)
    var textBackgroundFill = currStateScheme.textBackgroundFillColor.byAlpha(currHighlightAmount)
    var separatorPrimary = currStateScheme.separatorPrimaryColor.byAlpha(currHighlightAmount)
    var separatorSecondary = currStateScheme.separatorSecondaryColor.byAlpha(currHighlightAmount)
    var mark = currStateScheme.markColor.byAlpha(currHighlightAmount)
    var echo = currStateScheme.echoColor.byAlpha(currHighlightAmount)

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val alpha = skinColors.getHighlightAlpha(
            decorationAreaType = decorationAreaType,
            componentState = contribution.key
        )
        val amount = alpha * contribution.value.contribution
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionScheme = colorSchemeBundle?.getColorScheme(
            associationKind = associationKind,
            componentState = contribution.key,
            allowFallback = true
        ) ?: skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )
        // And interpolate the colors
        ultraLight = ultraLight.interpolateTowards(contributionScheme.ultraLightColor.byAlpha(amount), 1.0f - amount)
        extraLight = extraLight.interpolateTowards(contributionScheme.extraLightColor.byAlpha(amount), 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor.byAlpha(amount), 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor.byAlpha(amount), 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor.byAlpha(amount), 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor.byAlpha(amount), 1.0f - amount)
        foreground = foreground.interpolateTowards(contributionScheme.foregroundColor.byAlpha(amount), 1.0f - amount)
        backgroundFill =
            backgroundFill.interpolateTowards(contributionScheme.backgroundFillColor.byAlpha(amount), 1.0f - amount)
        accentedBackgroundFill = accentedBackgroundFill.interpolateTowards(
            contributionScheme.accentedBackgroundFillColor.byAlpha(amount),
            1.0f - amount
        )
        focusRing = focusRing.interpolateTowards(contributionScheme.focusRingColor.byAlpha(amount), 1.0f - amount)
        line = line.interpolateTowards(contributionScheme.lineColor.byAlpha(amount), 1.0f - amount)
        selectionForeground = selectionForeground.interpolateTowards(
            contributionScheme.selectionForegroundColor.byAlpha(amount),
            1.0f - amount
        )
        selectionBackground = selectionBackground.interpolateTowards(
            contributionScheme.selectionBackgroundColor.byAlpha(amount),
            1.0f - amount
        )
        textBackgroundFill = textBackgroundFill.interpolateTowards(
            contributionScheme.textBackgroundFillColor.byAlpha(amount),
            1.0f - amount
        )
        separatorPrimary =
            separatorPrimary.interpolateTowards(contributionScheme.separatorPrimaryColor.byAlpha(amount), 1.0f - amount)
        separatorSecondary = separatorSecondary.interpolateTowards(
            contributionScheme.separatorSecondaryColor.byAlpha(amount),
            1.0f - amount
        )
        mark = mark.interpolateTowards(contributionScheme.markColor.byAlpha(amount), 1.0f - amount)
        echo = echo.interpolateTowards(contributionScheme.echoColor.byAlpha(amount), 1.0f - amount)

        //println("\tcontribution of $amount from ${contribution.key} to $backgroundStart")
    }

    // Update the mutable color scheme with the interpolated colors
    colorScheme.ultraLight = ultraLight
    colorScheme.extraLight = extraLight
    colorScheme.light = light
    colorScheme.mid = mid
    colorScheme.dark = dark
    colorScheme.ultraDark = ultraDark
    colorScheme.foreground = foreground
    colorScheme.backgroundFill = backgroundFill
    colorScheme.accentedBackgroundFill = accentedBackgroundFill
    colorScheme.focusRing = focusRing
    colorScheme.line = line
    colorScheme.selectionForeground = selectionForeground
    colorScheme.selectionBackground = selectionBackground
    colorScheme.textBackgroundFill = textBackgroundFill
    colorScheme.separatorPrimary = separatorPrimary
    colorScheme.separatorSecondary = separatorSecondary
    colorScheme.mark = mark
    colorScheme.echo = echo
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun getStateAwareColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind,
    query: (AuroraColorScheme) -> Color,
): Color {
    val currStateScheme = colorSchemeBundle?.getColorScheme(
        associationKind = associationKind,
        componentState = currState,
        allowFallback = true
    ) ?: AuroraSkin.colors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = currState
    )

    var result = query.invoke(currStateScheme)

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
        val contributionScheme = colorSchemeBundle?.getColorScheme(
            associationKind = associationKind,
            componentState = contribution.key,
            allowFallback = true
        ) ?: AuroraSkin.colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )

        // Interpolate the color based on the scheme and contribution amount
        result = result.interpolateTowards(query.invoke(contributionScheme), 1.0f - amount)
    }

    return result
}

@OptIn(AuroraInternalApi::class)
internal fun getTextColor(
    modelStateInfo: ModelStateInfo?,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType,
    colorSchemeAssociationKind: ColorSchemeAssociationKind,
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

    val colorScheme = colorSchemeBundle?.getColorScheme(
        associationKind = colorSchemeAssociationKind,
        componentState = tweakedCurrState,
        allowFallback = true
    ) ?: skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = colorSchemeAssociationKind,
        componentState = tweakedCurrState
    )
    var foreground: Color
    if (tweakedCurrState.isDisabled || activeStates == null || activeStates.size == 1) {
        // Disabled state or only one active state being tracked
        foreground = colorScheme.foregroundColor
    } else {
        // Get the combined foreground color from all states
        var aggrRed = 0f
        var aggrGreen = 0f
        var aggrBlue = 0f
        for ((activeState, value) in activeStates) {
            val contribution = value.contribution
            val activeColorScheme = colorSchemeBundle?.getColorScheme(
                associationKind = colorSchemeAssociationKind,
                componentState = activeState,
                allowFallback = true
            ) ?: skinColors.getColorScheme(
                decorationAreaType = decorationAreaType,
                associationKind = colorSchemeAssociationKind,
                componentState = activeState
            )
            val activeForeground = activeColorScheme.foregroundColor
            aggrRed += contribution * activeForeground.red
            aggrGreen += contribution * activeForeground.green
            aggrBlue += contribution * activeForeground.blue
        }
        foreground = Color(red = aggrRed, blue = aggrBlue, green = aggrGreen, alpha = 1.0f)
    }

    val baseAlpha = colorSchemeBundle?.getAlpha(tweakedCurrState) ?: skinColors.getAlpha(
        decorationAreaType = decorationAreaType,
        componentState = tweakedCurrState
    )

    if (baseAlpha < 1.0f) {
        // Blend with the background fill
        val stateForQuery =
            if (tweakedCurrState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
        val backgroundColorScheme = colorSchemeBundle?.getColorScheme(stateForQuery) ?: skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            componentState = stateForQuery
        )
        val bgFillColor = backgroundColorScheme.backgroundFillColor
        foreground = foreground.interpolateTowards(bgFillColor, baseAlpha)
    }
    return foreground
}

@OptIn(AuroraInternalApi::class)
internal fun getTextSelectionBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType
): Color {
    val activeStates = modelStateInfo.stateContributionMap

    var tweakedCurrState = currState
    if (currState == ComponentState.Enabled) {
        // Treat ENABLED state as SELECTED (since we are talking about selections)
        tweakedCurrState = ComponentState.Selected
    }

    val currentScheme = colorSchemeBundle?.getColorScheme(tweakedCurrState) ?: skinColors.getColorScheme(
        decorationAreaType,
        tweakedCurrState
    )
    var result = currentScheme.textBackgroundFillColor
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
            val alpha: Float = skinColors.getAlpha(decorationAreaType, activeState)
            if (alpha == 0.0f) {
                continue
            }
            val activeScheme = colorSchemeBundle?.getColorScheme(activeState) ?: skinColors.getColorScheme(
                decorationAreaType,
                activeState
            )
            val active = activeScheme.textBackgroundFillColor
            result = result.interpolateTowards(active, 1.0f - contribution * alpha)
        }
    }
    return result
}

@OptIn(AuroraInternalApi::class)
internal fun getTextFillBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle?,
    decorationAreaType: DecorationAreaType
): Color {
    val stateForQuery =
        if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
    val fillColorScheme = colorSchemeBundle?.getColorScheme(
        associationKind = ColorSchemeAssociationKind.Fill,
        componentState = stateForQuery,
        allowFallback = true
    ) ?: skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = ColorSchemeAssociationKind.Fill,
        componentState = stateForQuery
    )
    var textBackgroundFillColor = fillColorScheme.textBackgroundFillColor

    val lightnessFactor = if (fillColorScheme.isDark) 0.1f else 0.4f
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

