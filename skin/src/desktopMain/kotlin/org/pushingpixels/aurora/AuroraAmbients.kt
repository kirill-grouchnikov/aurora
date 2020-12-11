/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.aurora

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.shaper.AuroraButtonShaper

data class ModelStateInfoSnapshot(
    val currModelState: ComponentState,
    val stateContributionMap: Map<ComponentState, Float>,
    val activeStrength: Float
)

val AmbientModelStateInfoSnapshot = ambientOf<ModelStateInfoSnapshot>()

val AmbientTextColor = ambientOf<Color>()


@Immutable
data class AnimationConfig(
    val short: Int = 150,
    val regular: Int = 250
)

val AmbientAnimationConfig = staticAmbientOf { AnimationConfig() }
val AmbientSkinColors = staticAmbientOf<AuroraSkinColors>()
val AmbientButtonShaper  = staticAmbientOf<AuroraButtonShaper>()
val AmbientPainters = staticAmbientOf<Painters>()

@Immutable
data class DecorationArea(
    val type: DecorationAreaType = DecorationAreaType.NONE
)

val AmbientDecorationArea = staticAmbientOf { DecorationArea() }

