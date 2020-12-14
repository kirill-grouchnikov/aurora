/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.components.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.ComponentState
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.colorscheme.*
import org.pushingpixels.aurora.common.interpolateTowards

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

    override fun blendWith(otherScheme: AuroraColorScheme, likenessToThisScheme: Float): AuroraColorScheme {
        // TODO - what are the performance implications?
        return BlendBiColorScheme(this, otherScheme, likenessToThisScheme)
    }
}

@Composable
internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind
) {
    val currState = modelStateInfo.currModelState
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
        ultraLight = ultraLight.interpolateTowards(contributionScheme.ultraLightColor, 1.0f - amount)
        extraLight = extraLight.interpolateTowards(contributionScheme.extraLightColor, 1.0f - amount)
        light = light.interpolateTowards(contributionScheme.lightColor, 1.0f - amount)
        mid = mid.interpolateTowards(contributionScheme.midColor, 1.0f - amount)
        dark = dark.interpolateTowards(contributionScheme.darkColor, 1.0f - amount)
        ultraDark = ultraDark.interpolateTowards(contributionScheme.ultraDarkColor, 1.0f - amount)
        foreground = foreground.interpolateTowards(contributionScheme.foregroundColor, 1.0f - amount)

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
    decorationAreaType: DecorationAreaType,
    associationKind: ColorSchemeAssociationKind,
    query: (AuroraColorScheme) -> Color,
): Color {
    val currState = modelStateInfo.currModelState
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
    skinColors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
    isTextInFilledArea: Boolean
): Color {
    var currState = modelStateInfo.currModelState
    var activeStates: Map<ComponentState, StateContributionInfo>? = modelStateInfo.stateContributionMap

    // Special case for when text is not drawn in the filled area
    if (!isTextInFilledArea) {
        currState = if (currState.isDisabled) ComponentState.DISABLED_UNSELECTED else ComponentState.ENABLED
        activeStates = null
    }

    val colorScheme = skinColors.getColorScheme(decorationAreaType, currState)
    var foreground: Color
    if (currState.isDisabled || activeStates == null || activeStates.size == 1) {
        // Disabled state or only one active state being tracked
        foreground = colorScheme.foregroundColor
    } else {
        // Get the combined foreground color from all states
        var aggrRed = 0f
        var aggrGreen = 0f
        var aggrBlue = 0f
        for ((activeState, value) in activeStates) {
            val contribution = value.contribution
            val activeColorScheme = skinColors.getColorScheme(decorationAreaType, activeState)
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
            if (currState.isDisabled) ComponentState.DISABLED_UNSELECTED else ComponentState.ENABLED
        )
        val bgFillColor = backgroundColorScheme.backgroundFillColor
        foreground = foreground.interpolateTowards(bgFillColor, baseAlpha)
    }
    return foreground
}

