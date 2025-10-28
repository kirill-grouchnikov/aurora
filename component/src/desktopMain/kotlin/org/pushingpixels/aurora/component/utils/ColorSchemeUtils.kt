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

import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.utils.MutableColorScheme

interface ColorSchemeDelegate {
    fun getColorSchemeForCurrentState(state: ComponentState): AuroraColorScheme
    fun getColorSchemeForActiveState(state: ComponentState): AuroraColorScheme
}

@OptIn(AuroraInternalApi::class)
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

