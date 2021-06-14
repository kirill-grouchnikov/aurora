/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.*
import org.pushingpixels.aurora.common.byAlpha
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.lighter
import kotlin.math.max


internal data class MutableColorScheme(
    override val displayName: String,
    override var isDark: Boolean,
    var ultraLight: Color,
    var extraLight: Color,
    var light: Color,
    var mid: Color,
    var dark: Color,
    var ultraDark: Color,
    var foreground: Color
) : AuroraColorScheme {

    override val ultraLightColor: Color
        get() = ultraLight
    override val extraLightColor: Color
        get() = extraLight
    override val lightColor: Color
        get() = light
    override val midColor: Color
        get() = mid
    override val darkColor: Color
        get() = dark
    override val ultraDarkColor: Color
        get() = ultraDark
    override val foregroundColor: Color
        get() = foreground

    override val backgroundFillColor: Color
        get() = throw UnsupportedOperationException()
    override val accentedBackgroundFillColor: Color
        get() = throw UnsupportedOperationException()
    override val focusRingColor: Color
        get() = throw UnsupportedOperationException()
    override val lineColor: Color
        get() = throw UnsupportedOperationException()
    override val selectionForegroundColor: Color
        get() = throw UnsupportedOperationException()
    override val selectionBackgroundColor: Color
        get() = throw UnsupportedOperationException()
    override val textBackgroundFillColor: Color
        get() = throw UnsupportedOperationException()
    override val separatorPrimaryColor: Color
        get() = throw UnsupportedOperationException()
    override val separatorSecondaryColor: Color
        get() = throw UnsupportedOperationException()
    override val markColor: Color
        get() = throw UnsupportedOperationException()
    override val echoColor: Color
        get() = throw UnsupportedOperationException()

    override fun shift(
        backgroundShiftColor: Color,
        backgroundShiftFactor: Float,
        foregroundShiftColor: Color,
        foregroundShiftFactor: Float
    ): AuroraColorScheme {
        // TODO - what are the performance implications?
        return ShiftColorScheme(
            this, backgroundShiftColor,
            backgroundShiftFactor, foregroundShiftColor,
            foregroundShiftFactor, true
        )
    }

    override fun shade(shadeFactor: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return ShadeColorScheme(this, shadeFactor)
    }

    override fun tint(tintFactor: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return TintColorScheme(this, tintFactor)
    }

    override fun tone(toneFactor: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return ToneColorScheme(this, toneFactor)
    }

    override fun negate(): AuroraColorScheme {
        // TODO - what are the performance implications?
        return NegatedColorScheme(this)
    }

    override fun invert(): AuroraColorScheme {
        // TODO - what are the performance implications?
        return InvertedColorScheme(this)
    }

    override fun saturate(saturateFactor: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return SaturatedColorScheme(this, saturateFactor)
    }

    override fun hueShift(hueShiftFactor: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return HueShiftColorScheme(this, hueShiftFactor)
    }

    override fun blendWith(
        otherScheme: AuroraColorScheme,
        likenessToThisScheme: Float
    ): AuroraColorScheme {
        // TODO - what are the performance implications?
        return BlendBiColorScheme(this, otherScheme, likenessToThisScheme)
    }
}

@Composable
internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind
) {
    val currStateScheme = AuroraSkin.colors.getColorScheme(
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
        val contributionScheme = AuroraSkin.colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )
        // And interpolate the colors
        ultraLight =
            ultraLight.interpolateTowards(contributionScheme.ultraLightColor, 1.0f - amount)
        extraLight =
            extraLight.interpolateTowards(contributionScheme.extraLightColor, 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor, 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor, 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor, 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor, 1.0f - amount)
        foreground =
            foreground.interpolateTowards(contributionScheme.foregroundColor, 1.0f - amount)

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
}

@Composable
internal fun populateColorSchemeWithHighlightAlpha(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind
) {
    val skinColors = AuroraSkin.colors
    val currStateScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = associationKind,
        componentState = currState
    )
    val currHighlightAlpha = skinColors.getHighlightAlpha(
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
        val contributionScheme = skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )
        // And interpolate the colors
        ultraLight =
            ultraLight.interpolateTowards(
                contributionScheme.ultraLightColor.byAlpha(amount),
                1.0f - amount
            )
        extraLight =
            extraLight.interpolateTowards(
                contributionScheme.extraLightColor.byAlpha(amount),
                1.0f - amount
            )
        light =
            light.interpolateTowards(contributionScheme.lightColor.byAlpha(amount), 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor.byAlpha(amount), 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor.byAlpha(amount), 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(
            contributionScheme.ultraDarkColor.byAlpha(amount),
            1.0f - amount
        )
        foreground =
            foreground.interpolateTowards(
                contributionScheme.foregroundColor.byAlpha(amount),
                1.0f - amount
            )

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
}

@Composable
internal fun getStateAwareColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind,
    query: (AuroraColorScheme) -> Color,
): Color {
    val currStateScheme = AuroraSkin.colors.getColorScheme(
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
        val contributionScheme = AuroraSkin.colors.getColorScheme(
            decorationAreaType = decorationAreaType,
            associationKind = associationKind,
            componentState = contribution.key
        )

        // Interpolate the color based on the scheme and contribution amount
        result = result.interpolateTowards(query.invoke(contributionScheme), 1.0f - amount)
    }

    return result
}

internal fun getTextColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    colorSchemeAssociationKind: ColorSchemeAssociationKind,
    isTextInFilledArea: Boolean
): Color {
    var activeStates: Map<ComponentState, StateContributionInfo>? =
        modelStateInfo.stateContributionMap
    var tweakedCurrState = currState
    // Special case for when text is not drawn in the filled area
    if (!isTextInFilledArea) {
        tweakedCurrState =
            if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
        activeStates = null
    }

    val colorScheme = skinColors.getColorScheme(
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
            val activeColorScheme = skinColors.getColorScheme(
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

    val baseAlpha = skinColors.getAlpha(
        decorationAreaType = decorationAreaType,
        componentState = tweakedCurrState
    )

    if (baseAlpha < 1.0f) {
        // Blend with the background fill
        val backgroundColorScheme = skinColors.getColorScheme(
            decorationAreaType,
            if (tweakedCurrState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
        )
        val bgFillColor = backgroundColorScheme.backgroundFillColor
        foreground = foreground.interpolateTowards(bgFillColor, baseAlpha)
    }
    return foreground
}

internal fun getMenuTextColor(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType
): Color {
    val activeStates = modelStateInfo.stateContributionMap

    var currAssocKind = ColorSchemeAssociationKind.Fill
    // use HIGHLIGHT on active and non-rollover menu items
    if (!currState.isDisabled && (currState !== ComponentState.Enabled)
        && !currState.isFacetActive(ComponentStateFacet.Rollover)
    ) currAssocKind = ColorSchemeAssociationKind.Highlight

    val colorScheme = skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
        associationKind = currAssocKind,
        componentState = currState
    )

    var foreground: Color
    if (currState.isDisabled || (activeStates.size == 1)) {
        // Disabled state or only one active state being tracked
        foreground = colorScheme.foregroundColor
    } else {
        // Get the combined foreground color from all states
        var aggrRed = 0f
        var aggrGreen = 0f
        var aggrBlue = 0f
        for ((activeState, value) in activeStates) {
            val contribution = value.contribution
            var assocKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.Fill
            // use HIGHLIGHT on active and non-rollover menu items
            if (!activeState.isDisabled && (activeState !== ComponentState.Enabled)
                && !activeState.isFacetActive(ComponentStateFacet.Rollover)
            ) assocKind = ColorSchemeAssociationKind.Highlight
            val activeColorScheme = skinColors.getColorScheme(
                decorationAreaType = decorationAreaType,
                associationKind = assocKind,
                componentState = activeState
            )
            val activeForeground = activeColorScheme.foregroundColor
            aggrRed += contribution * activeForeground.red
            aggrGreen += contribution * activeForeground.green
            aggrBlue += contribution * activeForeground.blue
        }
        foreground = Color(red = aggrRed, blue = aggrBlue, green = aggrGreen, alpha = 1.0f)
    }

    val baseAlpha = skinColors.getAlpha(
        decorationAreaType = decorationAreaType,
        componentState = currState
    )

    if (baseAlpha < 1.0f) {
        // Blend with the background fill
        val backgroundColorScheme = skinColors.getColorScheme(
            decorationAreaType,
            if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
        )
        val bgFillColor = backgroundColorScheme.backgroundFillColor
        foreground = foreground.interpolateTowards(bgFillColor, baseAlpha)
    }
    return foreground
}

internal fun getTextSelectionBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType
): Color {
    val activeStates = modelStateInfo.stateContributionMap

    var tweakedCurrState = currState
    if (currState == ComponentState.Enabled) {
        // Treat ENABLED state as SELECTED (since we are talking about selections)
        tweakedCurrState = ComponentState.Selected
    }

    var result =
        skinColors.getColorScheme(decorationAreaType, tweakedCurrState).textBackgroundFillColor
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
            val active =
                skinColors.getColorScheme(decorationAreaType, activeState).textBackgroundFillColor
            result = result.interpolateTowards(active, 1.0f - contribution * alpha)
        }
    }
    return result
}

internal fun getTextFillBackground(
    modelStateInfo: ModelStateInfo,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType
): Color {
    val stateForQuery =
        if (currState.isDisabled) ComponentState.DisabledUnselected else ComponentState.Enabled
    val fillColorScheme = skinColors.getColorScheme(
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

