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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.common.AuroraInternalApi

@OptIn(AuroraInternalApi::class)
@Composable
fun resolveAuroraDefaults() =
    resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraTypography(
    defaultTextStyle: TextStyle,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides defaultTextStyle,
        content = content
    )
}
