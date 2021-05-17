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
package org.pushingpixels.aurora.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalDensity
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.bitmapfilter.ColorBitmapFilter
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.utils.ColorSchemeBitmapFilter

private fun Modifier.auroraThemedIconPaint(iconBitmap: ImageBitmap, textColor: Color, alpha: Float) =
    this.then(AuroraThemedByTextIconModifier(iconBitmap = iconBitmap, textColor = textColor, alpha = alpha))

private fun Modifier.auroraThemedIconPaint(iconBitmap: ImageBitmap, colorScheme: AuroraColorScheme, alpha: Float) =
    this.then(AuroraThemedByColorSchemeIconModifier(iconBitmap = iconBitmap, colorScheme = colorScheme, alpha = alpha))

private class AuroraThemedByTextIconModifier(
    val iconBitmap: ImageBitmap, val textColor: Color, val alpha: Float
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        val filtered = ColorBitmapFilter.getColorFilter(color = textColor, alpha = alpha).filter(iconBitmap)
        drawImage(filtered)
    }
}

private class AuroraThemedByColorSchemeIconModifier(
    val iconBitmap: ImageBitmap, val colorScheme: AuroraColorScheme, val alpha: Float
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        // TODO - cache the filter instances
        val filtered = ColorSchemeBitmapFilter(
            scheme = colorScheme, originalBrightnessFactor = 0.5f, alpha = alpha
        ).filter(iconBitmap)
        drawImage(filtered)
    }
}

private class AlphaIconModifier(val iconBitmap: ImageBitmap, val alpha: Float) : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawImage(image = iconBitmap, alpha = alpha)
    }
}

@Composable
fun AuroraThemedFollowTextIcon(icon: AuroraIcon, modifier: Modifier = Modifier) {
    AuroraThemedIcon(
        icon = icon,
        disabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
        enabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
        activeFilterStrategy = IconFilterStrategy.ThemedFollowText,
        modifier = modifier
    )
}

@Composable
fun AuroraThemedIcon(
    icon: AuroraIcon,
    disabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    modifier: Modifier = Modifier
) {
    val modelStateInfoSnapshot = LocalModelStateInfoSnapshot.current
    val currModelState = modelStateInfoSnapshot.currModelState

    val textColor = LocalTextColor.current
    val density = LocalDensity.current.density
    val colors = AuroraSkin.colors
    val decorationAreaType = AuroraSkin.decorationAreaType

    val iconBitmap = remember { icon.toBitmap(density) }
    if (currModelState.isDisabled) {
        // TODO - do we need icon transitions from / to a disabled state?
        when (disabledFilterStrategy) {
            IconFilterStrategy.Original ->
                Box(
                    modifier.size(
                        width = icon.getWidth(),
                        height = icon.getHeight()
                    ).auroraIconPaint(icon)
                )
            IconFilterStrategy.ThemedFollowText ->
                // For disabled states, the text color already accounts for the
                // disabled state alpha under the current skin configuration
                Box(
                    modifier.size(
                        width = icon.getWidth(),
                        height = icon.getHeight()
                    ).auroraThemedIconPaint(iconBitmap, textColor, 1.0f)
                )
            IconFilterStrategy.ThemedFollowColorScheme ->
                Box(
                    modifier.size(
                        width = icon.getWidth(),
                        height = icon.getHeight()
                    ).auroraThemedIconPaint(
                        iconBitmap, colors.getColorScheme(
                            decorationAreaType = decorationAreaType,
                            componentState = currModelState
                        ), 1.0f
                    )
                )
        }
    } else {
        // Simple case - both enabled and active filter strategy are ORIGINAL
        if ((enabledFilterStrategy == IconFilterStrategy.Original) &&
            (activeFilterStrategy == IconFilterStrategy.Original)
        ) {
            Box(
                modifier.size(
                    width = icon.getWidth(),
                    height = icon.getHeight()
                ).auroraIconPaint(icon)
            )
        } else {
            // We start with the enabled state filter strategy
            val enabledIconModifier = when (enabledFilterStrategy) {
                IconFilterStrategy.Original ->
                    AlphaIconModifier(
                        iconBitmap = iconBitmap,
                        alpha = 1.0f
                    )
                IconFilterStrategy.ThemedFollowText ->
                    AuroraThemedByTextIconModifier(
                        iconBitmap = iconBitmap,
                        textColor = textColor,
                        alpha = 1.0f
                    )
                IconFilterStrategy.ThemedFollowColorScheme ->
                    AuroraThemedByColorSchemeIconModifier(
                        iconBitmap = iconBitmap,
                        colorScheme = colors.getColorScheme(
                            decorationAreaType = decorationAreaType,
                            componentState = currModelState
                        ),
                        alpha = 1.0f
                    )
            }

            // And then add the active state filter strategy if we have any active state(s)
            // in the model state snapshot
            val activeIconModifier = if (modelStateInfoSnapshot.activeStrength > 0.0f)
                when (activeFilterStrategy) {
                    IconFilterStrategy.Original ->
                        AlphaIconModifier(
                            iconBitmap = iconBitmap,
                            alpha = modelStateInfoSnapshot.activeStrength
                        )
                    IconFilterStrategy.ThemedFollowText ->
                        AuroraThemedByTextIconModifier(
                            iconBitmap = iconBitmap,
                            textColor = textColor,
                            alpha = modelStateInfoSnapshot.activeStrength
                        )
                    IconFilterStrategy.ThemedFollowColorScheme ->
                        getCombinedActiveStatesThemedByColorSchemeModifier(
                            iconBitmap = iconBitmap,
                            modelStateInfoSnapshot = modelStateInfoSnapshot,
                            colors = colors,
                            decorationAreaType = decorationAreaType
                        )
                } else null

            val iconModifier = if (activeIconModifier == null) enabledIconModifier else
                CombinedModifier(enabledIconModifier, activeIconModifier)

            Box(
                modifier.size(
                    width = icon.getWidth(),
                    height = icon.getHeight()
                ).then(iconModifier)
            )
        }
    }
}

private fun getCombinedActiveStatesThemedByColorSchemeModifier(
    iconBitmap: ImageBitmap,
    modelStateInfoSnapshot: ModelStateInfoSnapshot,
    colors: AuroraSkinColors,
    decorationAreaType: DecorationAreaType,
): Modifier? {
    return modelStateInfoSnapshot.stateContributionMap
        .filter { (state, contribution) ->
            // For all active states that have non-zero contribution
            state.isActive && contribution > 0.0f
        }.map { (state, contribution) ->
            // Create a matching modifier that would paint the themed version of the
            // original icon based on that contribution
            AuroraThemedByColorSchemeIconModifier(
                iconBitmap = iconBitmap,
                colorScheme = colors.getColorScheme(
                    decorationAreaType = decorationAreaType,
                    componentState = state
                ),
                alpha = contribution
            )
        }.fold<Modifier, Modifier?>(initial = null) { result, modifier ->
            // And fold all the modifiers into one chain
            result?.then(modifier) ?: modifier
        }
}