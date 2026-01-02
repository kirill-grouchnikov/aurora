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
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.MutableContainerColorTokens
import org.pushingpixels.aurora.theming.utils.getContainerColorTokensFilter
import org.pushingpixels.aurora.theming.utils.getContainerTokens

@OptIn(AuroraInternalApi::class)
private class CombinedIconModifier(
    val icon: Painter,
    val enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val skinColors: AuroraSkinColors,
    val tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    val decorationAreaType: DecorationAreaType,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val textColor: Color,
    val mutableContainerColorTokens: MutableContainerColorTokens
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        // We start with the enabled state filter strategy
        val enabledFilter: ColorFilter? =
            when (enabledFilterStrategy) {
                IconFilterStrategy.ThemedFollowText -> ColorFilter.tint(color = textColor)
                IconFilterStrategy.ThemedFollowColorTokens ->
                    getContainerColorTokensFilter(
                        colorTokens = getContainerTokens(
                            colors = skinColors,
                            tokensOverlayProvider = tokensOverlayProvider,
                            decorationAreaType = decorationAreaType,
                            componentState = modelStateInfoSnapshot.currModelState,
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            inactiveContainerType = ContainerType.Neutral
                        )
                    )
                IconFilterStrategy.Original -> null
            }
        with(icon) {
            draw(size = size, colorFilter = enabledFilter)
        }

        // And then add the active state filter strategy if we have any active state(s)
        // in the model state snapshot
        val skipForThemedFollowText = ((enabledFilterStrategy == IconFilterStrategy.ThemedFollowText)
                && (activeFilterStrategy == IconFilterStrategy.ThemedFollowText))
        if (!skipForThemedFollowText && modelStateInfoSnapshot.activeStrength > 0.0f) {
            val activeAlpha = if (activeFilterStrategy != IconFilterStrategy.Original)
                modelStateInfoSnapshot.activeStrength else 1.0f
            val activeColorFilter: ColorFilter? =
                when (activeFilterStrategy) {
                    IconFilterStrategy.Original -> null
                    IconFilterStrategy.ThemedFollowText -> ColorFilter.tint(color = textColor)
                    IconFilterStrategy.ThemedFollowColorTokens -> {
                        populateColorTokens(
                            colorTokens = mutableContainerColorTokens,
                            colors = skinColors,
                            tokensOverlayProvider = tokensOverlayProvider,
                            decorationAreaType = decorationAreaType,
                            modelStateInfoSnapshot = modelStateInfoSnapshot,
                            associationKind = ContainerColorTokensAssociationKind.Default,
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            treatEnabledAsActive = false,
                            skipFlatCheck = false,
                            inactiveContainerType = ContainerType.Muted)
                        getContainerColorTokensFilter(
                            colorTokens = mutableContainerColorTokens,
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
@OptIn(AuroraInternalApi::class)
private class IconDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraThemedIcon(
    icon: Painter,
    size: DpSize,
    disabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
    enabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    activeFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    modifier: Modifier = Modifier
) {
    val drawingCache = remember { IconDrawingCache() }

    val modelStateInfoSnapshot = LocalModelStateInfoSnapshot.current
    val currModelState = modelStateInfoSnapshot.currModelState

    val textColor = LocalTextColor.current
    val colors = AuroraSkin.colors
    val tokensOverlayProvider = LocalColorTokensOverlayProvider.current
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

            IconFilterStrategy.ThemedFollowColorTokens -> {
                Box(
                    modifier.size(size).paint(
                        painter = icon,
                        colorFilter = getContainerColorTokensFilter(
                            colorTokens = getContainerTokens(
                                colors = colors,
                                tokensOverlayProvider = tokensOverlayProvider,
                                decorationAreaType = decorationAreaType,
                                componentState = modelStateInfoSnapshot.currModelState,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                                inactiveContainerType = ContainerType.Neutral
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
                        tokensOverlayProvider,
                        decorationAreaType,
                        modelStateInfoSnapshot,
                        textColor,
                        drawingCache.colorTokens
                    )
                )
            )
        }
    }
}

