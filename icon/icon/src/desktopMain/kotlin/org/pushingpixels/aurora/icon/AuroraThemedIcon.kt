/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
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
        disabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
        enabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
        activeFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_TEXT,
        modifier = modifier
    )
}

@Composable
fun AuroraThemedIcon(
    icon: AuroraIcon,
    disabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
    enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
    activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.ORIGINAL,
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
            IconFilterStrategy.ORIGINAL ->
                Box(
                    modifier.preferredSize(
                        width = icon.getWidth().dp,
                        height = icon.getHeight().dp
                    ).auroraIconPaint(icon)
                )
            IconFilterStrategy.THEMED_FOLLOW_TEXT ->
                // For disabled states, the text color already accounts for the
                // disabled state alpha under the current skin configuration
                Box(
                    modifier.preferredSize(
                        width = icon.getWidth().dp,
                        height = icon.getHeight().dp
                    ).auroraThemedIconPaint(iconBitmap, textColor, 1.0f)
                )
            IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME ->
                Box(
                    modifier.preferredSize(
                        width = icon.getWidth().dp,
                        height = icon.getHeight().dp
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
        if ((enabledFilterStrategy == IconFilterStrategy.ORIGINAL) &&
            (activeFilterStrategy == IconFilterStrategy.ORIGINAL)
        ) {
            Box(
                modifier.preferredSize(
                    width = icon.getWidth().dp,
                    height = icon.getHeight().dp
                ).auroraIconPaint(icon)
            )
        } else {
            // We start with the enabled state filter strategy
            val enabledIconModifier = when (enabledFilterStrategy) {
                IconFilterStrategy.ORIGINAL ->
                    AlphaIconModifier(
                        iconBitmap = iconBitmap,
                        alpha = 1.0f
                    )
                IconFilterStrategy.THEMED_FOLLOW_TEXT ->
                    AuroraThemedByTextIconModifier(
                        iconBitmap = iconBitmap,
                        textColor = textColor,
                        alpha = 1.0f
                    )
                IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME ->
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
                    IconFilterStrategy.ORIGINAL ->
                        AlphaIconModifier(
                            iconBitmap = iconBitmap,
                            alpha = modelStateInfoSnapshot.activeStrength
                        )
                    IconFilterStrategy.THEMED_FOLLOW_TEXT ->
                        AuroraThemedByTextIconModifier(
                            iconBitmap = iconBitmap,
                            textColor = textColor,
                            alpha = modelStateInfoSnapshot.activeStrength
                        )
                    IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME ->
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
                modifier.preferredSize(
                    width = icon.getWidth().dp,
                    height = icon.getHeight().dp
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