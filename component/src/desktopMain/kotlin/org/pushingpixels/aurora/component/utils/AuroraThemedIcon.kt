/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.utils.MutableColorScheme
import org.pushingpixels.aurora.theming.utils.getColorSchemeFilter

private class CombinedIconModifier(
    val icon: Painter,
    val enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val skinColors: AuroraSkinColors,
    val colorSchemeBundle: AuroraColorSchemeBundle?,
    val decorationAreaType: DecorationAreaType,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val currModelState: ComponentState,
    val textColor: Color,
    val mutableColorScheme: MutableColorScheme
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        // We start with the enabled state filter strategy
        val enabledFilter: ColorFilter? =
            when (enabledFilterStrategy) {
                IconFilterStrategy.ThemedFollowText -> ColorFilter.tint(color = textColor)
                IconFilterStrategy.ThemedFollowColorScheme -> getColorSchemeFilter(
                    scheme = skinColors.getColorScheme(
                        decorationAreaType = decorationAreaType,
                        componentState = currModelState
                    )
                )

                IconFilterStrategy.Original -> null
            }
        with(icon) {
            draw(size = size, colorFilter = enabledFilter)
        }

        // And then add the active state filter strategy if we have any active state(s)
        // in the model state snapshot
        if (modelStateInfoSnapshot.activeStrength > 0.0f) {
            val activeAlpha = if (activeFilterStrategy != IconFilterStrategy.Original)
                modelStateInfoSnapshot.activeStrength else 1.0f
            val activeColorFilter: ColorFilter? =
                when (activeFilterStrategy) {
                    IconFilterStrategy.Original -> null
                    IconFilterStrategy.ThemedFollowText -> ColorFilter.tint(color = textColor)
                    IconFilterStrategy.ThemedFollowColorScheme -> {
                        populateColorScheme(
                            mutableColorScheme,
                            modelStateInfoSnapshot,
                            currModelState,
                            skinColors,
                            colorSchemeBundle,
                            decorationAreaType
                        )
                        getColorSchemeFilter(
                            scheme = mutableColorScheme,
                        )
                    }
                }

            with(icon) {
                draw(size = size, alpha = activeAlpha, colorFilter = activeColorFilter)
            }
        }
    }
}

@Immutable
private class IconDrawingCache(
    val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraThemedIcon(
    icon: Painter,
    size: DpSize,
    disabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    modifier: Modifier = Modifier
) {
    val drawingCache = remember { IconDrawingCache() }

    val modelStateInfoSnapshot = LocalModelStateInfoSnapshot.current
    val currModelState = modelStateInfoSnapshot.currModelState

    val textColor = LocalTextColor.current
    val colors = AuroraSkin.colors
    val colorSchemeBundle = LocalColorSchemeBundle.current
    val decorationAreaType = AuroraSkin.decorationAreaType

    if (currModelState.isDisabled) {
        // TODO - do we need icon transitions from / to a disabled state?
        when (disabledFilterStrategy) {
            IconFilterStrategy.Original ->
                Box(modifier.size(size).paint(painter = icon))

            IconFilterStrategy.ThemedFollowText -> {
                // For disabled states, the text color already accounts for the
                // disabled state alpha under the current skin configuration
                Box(
                    modifier.size(size)
                        .paint(painter = icon, colorFilter = ColorFilter.tint(color = textColor))
                )
            }

            IconFilterStrategy.ThemedFollowColorScheme -> {
                Box(
                    modifier.size(size).paint(
                        painter = icon,
                        colorFilter = getColorSchemeFilter(
                            scheme = colors.getColorScheme(
                                decorationAreaType = decorationAreaType,
                                componentState = currModelState
                            )
                        )
                    )
                )
            }
        }
    } else {
        // Simple case - both enabled and active filter strategy are ORIGINAL
        if ((enabledFilterStrategy == IconFilterStrategy.Original) &&
            (activeFilterStrategy == IconFilterStrategy.Original)
        ) {
            Box(modifier.size(size).paint(painter = icon))
        } else {
            Box(
                modifier.size(size).then(
                    CombinedIconModifier(
                        icon,
                        enabledFilterStrategy,
                        activeFilterStrategy,
                        colors,
                        colorSchemeBundle,
                        decorationAreaType,
                        modelStateInfoSnapshot,
                        currModelState,
                        textColor,
                        drawingCache.colorScheme
                    )
                )
            )
        }
    }
}

internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfoSnapshot: ModelStateInfoSnapshot,
    currState: ComponentState,
    skinColors: AuroraSkinColors,
    colorSchemeBundle: AuroraColorSchemeBundle? = null,
    decorationAreaType: DecorationAreaType
) {
    val currStateScheme = colorSchemeBundle?.getColorScheme(currState) ?: skinColors.getColorScheme(
        decorationAreaType = decorationAreaType,
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

    for (contribution in modelStateInfoSnapshot.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionScheme = colorSchemeBundle?.getColorScheme(contribution.key) ?: skinColors.getColorScheme(
            decorationAreaType = decorationAreaType,
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
        backgroundFill =
            backgroundFill.interpolateTowards(contributionScheme.backgroundFillColor, 1.0f - amount)
        accentedBackgroundFill = accentedBackgroundFill.interpolateTowards(
            contributionScheme.accentedBackgroundFillColor,
            1.0f - amount
        )
        focusRing = focusRing.interpolateTowards(contributionScheme.focusRingColor, 1.0f - amount)
        line = line.interpolateTowards(contributionScheme.lineColor, 1.0f - amount)
        selectionForeground = selectionForeground.interpolateTowards(
            contributionScheme.selectionForegroundColor,
            1.0f - amount
        )
        selectionBackground = selectionBackground.interpolateTowards(
            contributionScheme.selectionBackgroundColor,
            1.0f - amount
        )
        textBackgroundFill = textBackgroundFill.interpolateTowards(
            contributionScheme.textBackgroundFillColor,
            1.0f - amount
        )
        separatorPrimary = separatorPrimary.interpolateTowards(
            contributionScheme.separatorPrimaryColor,
            1.0f - amount
        )
        separatorSecondary = separatorSecondary.interpolateTowards(
            contributionScheme.separatorSecondaryColor,
            1.0f - amount
        )
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
