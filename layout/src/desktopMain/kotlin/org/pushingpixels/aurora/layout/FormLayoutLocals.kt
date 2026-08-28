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
package org.pushingpixels.aurora.layout

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import org.pushingpixels.aurora.layout.factories.ComponentFactory

internal val LocalFormLayoutInitialized = staticCompositionLocalOf { false }

internal val LocalTextMeasurer = staticCompositionLocalOf<TextMeasurer> {
    error("LocalTextMeasurer not provided")
}

internal val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle() }

internal val LocalComponentFactory = staticCompositionLocalOf<ComponentFactory> {
    error("LocalComponentFactory not provided")
}
