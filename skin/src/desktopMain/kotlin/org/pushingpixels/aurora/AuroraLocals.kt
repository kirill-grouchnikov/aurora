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
package org.pushingpixels.aurora

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.shaper.AuroraButtonShaper

data class ModelStateInfoSnapshot(
    val currModelState: ComponentState,
    val stateContributionMap: Map<ComponentState, Float>,
    val activeStrength: Float
)

val LocalModelStateInfoSnapshot = staticCompositionLocalOf<ModelStateInfoSnapshot> {
    error("LocalModelStateInfoSnapshot not provided")
}

val LocalTextColor = staticCompositionLocalOf<Color> {
    error("LocalTextColor not provided")
}

val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle() }

@Immutable
data class AnimationConfig(
    val short: Int = 150,
    val regular: Int = 250
)

val LocalAnimationConfig = staticCompositionLocalOf { AnimationConfig() }
val LocalSkinColors = staticCompositionLocalOf<AuroraSkinColors> {
    error("LocalSkinColors not provided")
}
val LocalButtonShaper  = staticCompositionLocalOf<AuroraButtonShaper> {
    error("LocalButtonShaper not provided")
}
val LocalPainters = staticCompositionLocalOf<AuroraPainters> {
    error("LocalPainters not provided")
}
val LocalDecorationAreaType = staticCompositionLocalOf<DecorationAreaType> {
    error("LocalDecorationAreaType not provided")
}
val LocalDisplayName = staticCompositionLocalOf<String> {
    error("LocalDisplayName not provided")
}

