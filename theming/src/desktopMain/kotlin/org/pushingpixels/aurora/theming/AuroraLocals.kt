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
package org.pushingpixels.aurora.theming

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper

data class ModelStateInfoSnapshot(
    val currModelState: ComponentState,
    val stateContributionMap: Map<ComponentState, Float>,
    val activeStrength: Float
)

@AuroraInternalApi
val LocalModelStateInfoSnapshot = staticCompositionLocalOf<ModelStateInfoSnapshot> {
    error("LocalModelStateInfoSnapshot not provided")
}

@AuroraInternalApi
val LocalColorTokensOverlayProvider = staticCompositionLocalOf<ContainerColorTokensOverlay.Provider?> {
    error("LocalColorTokensOverlayProvider not provided")
}

@AuroraInternalApi
val LocalTextColor = staticCompositionLocalOf<Color> {
    error("LocalTextColor not provided")
}

@AuroraInternalApi
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle() }

@Immutable
data class AnimationConfig(
    val short: Int = 150,
    val regular: Int = 250
)

@AuroraInternalApi
val LocalAnimationConfig = staticCompositionLocalOf { AnimationConfig() }

@AuroraInternalApi
val LocalSkinColors = staticCompositionLocalOf<AuroraSkinColors> {
    error("LocalSkinColors not provided")
}
@AuroraInternalApi
val LocalButtonShaper  = staticCompositionLocalOf<AuroraButtonShaper> {
    error("LocalButtonShaper not provided")
}
@AuroraInternalApi
val LocalPainters = staticCompositionLocalOf<AuroraPainters> {
    error("LocalPainters not provided")
}
@AuroraInternalApi
val LocalPainterOverlays = staticCompositionLocalOf<AuroraPainterOverlays?> {
    null
}
@AuroraInternalApi
val LocalDecorationAreaType = staticCompositionLocalOf<DecorationAreaType> {
    error("LocalDecorationAreaType not provided")
}
@AuroraInternalApi
val LocalDisplayName = staticCompositionLocalOf<String> {
    error("LocalDisplayName not provided")
}
@AuroraInternalApi
val LocalWindow = staticCompositionLocalOf<ComposeWindow> {
    error("LocalWindow not provided")
}
@AuroraInternalApi
val LocalWindowSize = staticCompositionLocalOf<DpSize> {
    error("LocalWindowSize not provided")
}
@AuroraInternalApi
val LocalTopWindowSize = staticCompositionLocalOf<DpSize> {
    error("LocalTopWindowSize not provided")
}
@AuroraInternalApi
val LocalPopupMenu = staticCompositionLocalOf<AuroraSwingPopupMenu?> {
    null
}

