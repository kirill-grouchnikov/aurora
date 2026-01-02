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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import org.pushingpixels.aurora.common.AuroraInternalApi

@AuroraInternalApi
enum class RibbonBandRow {
    Top, Middle, Bottom, None
}

@AuroraInternalApi
val LocalRibbonBandRowHeight = staticCompositionLocalOf {
    0
}

@AuroraInternalApi
val LocalRibbonTrackBounds = staticCompositionLocalOf {
    false
}

@AuroraInternalApi
val LocalRibbonTrackKeyTips = staticCompositionLocalOf {
    false
}

@AuroraInternalApi
val LocalRibbonKeyTipChainRoot = staticCompositionLocalOf<Any?> {
    null
}

@AuroraInternalApi
val LocalRibbonKeyTipChainRootKeyTip = staticCompositionLocalOf<String?> {
    null
}

@AuroraInternalApi
val LocalRibbonBandRow = staticCompositionLocalOf {
    RibbonBandRow.None
}

@AuroraInternalApi
val LocalRibbonBandRectOnScreen = staticCompositionLocalOf {
    Rect(Offset.Zero, Size.Zero)
}
