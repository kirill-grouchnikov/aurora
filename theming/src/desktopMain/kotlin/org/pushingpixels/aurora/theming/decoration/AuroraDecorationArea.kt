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
package org.pushingpixels.aurora.theming.decoration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraDecorationArea(
    decorationAreaType: DecorationAreaType,
    buttonShaper: AuroraButtonShaper? = null,
    content: @Composable () -> Unit
) {
    val buttonShaperToUse = buttonShaper ?: when (decorationAreaType) {
        DecorationAreaType.TitlePane,
        DecorationAreaType.Header,
        DecorationAreaType.Toolbar,
        DecorationAreaType.Footer -> ClassicButtonShaper()

        else -> AuroraSkin.buttonShaper
    }
    CompositionLocalProvider(
        LocalDisplayName provides AuroraSkin.displayName,
        LocalDecorationAreaType provides decorationAreaType,
        LocalSkinColors provides AuroraSkin.colors,
        LocalButtonShaper provides buttonShaperToUse,
        LocalPainters provides AuroraSkin.painters,
        LocalAnimationConfig provides AuroraSkin.animationConfig
    ) {
        content()
    }
}
