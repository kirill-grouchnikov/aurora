/*
 * Copyright (c) 2020 Mosaic, Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.utils

import androidx.compose.ui.graphics.Color
import org.pushingpixels.mosaic.ColorSchemeAssociationKind
import org.pushingpixels.mosaic.colorscheme.MosaicColorScheme
import org.pushingpixels.mosaic.colorscheme.MosaicColorSchemeBundle
import org.pushingpixels.mosaic.components.ModelStateInfo

internal data class MutableColorScheme(
    val displayName: String,
    var backgroundStart: Color,
    var backgroundEnd: Color,
    var foreground: Color
) : MosaicColorScheme {
    constructor(displayName: String, background: Color, foreground: Color) :
            this(displayName, background, background, foreground)

    override val backgroundColorEnd: Color
        get() = backgroundEnd
    override val backgroundColorStart: Color
        get() = backgroundStart
    override val foregroundColor: Color
        get() = foreground

    override fun displayName(): String {
        return displayName
    }

    override fun shift(
        backgroundShiftColor: Color,
        backgroundShiftFactor: Float,
        foregroundShiftColor: Color,
        foregroundShiftFactor: Float
    ): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun shade(shadeFactor: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun tone(toneFactor: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun tint(tintFactor: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun saturate(saturateFactor: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun blendWith(otherScheme: MosaicColorScheme, likenessToThisScheme: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun negate(): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun invert(): MosaicColorScheme {
        throw UnsupportedOperationException()
    }

    override fun hueShift(hueShiftFactor: Float): MosaicColorScheme {
        throw UnsupportedOperationException()
    }
}

internal fun populateColorScheme(
    colorScheme: MutableColorScheme,
    modelStateInfo: ModelStateInfo,
    associationKind: ColorSchemeAssociationKind,
    colorSchemeBundle: MosaicColorSchemeBundle
) {
    val currState = modelStateInfo.currModelState
    val currStateScheme = colorSchemeBundle.getColorScheme(associationKind, currState, true)!!

    var backgroundStart = currStateScheme.backgroundColorStart
    var backgroundEnd = currStateScheme.backgroundColorEnd
    var foreground = currStateScheme.foregroundColor

    println("Starting with $currState at $backgroundStart")

    for (contribution in modelStateInfo.stateContributionMap) {
        if (contribution.key == currState) {
            // Already accounted for the currently active state
            continue
        }
        val amount = contribution.value.contribution
        if (amount == 0.0f) {
            // Skip a zero-amount contribution
            continue
        }
        // Get the color scheme that matches the contribution state
        val contributionScheme =
            colorSchemeBundle.getColorScheme(associationKind, contribution.key, true)!!
        // And interpolate the colors
        backgroundStart = getInterpolatedColor(
            backgroundStart, contributionScheme.backgroundColorStart, 1.0f - amount
        )
        backgroundEnd = getInterpolatedColor(
            backgroundEnd, contributionScheme.backgroundColorEnd, 1.0f - amount
        )
        foreground = getInterpolatedColor(
            foreground, contributionScheme.foregroundColor, 1.0f - amount
        )

        println("\tcontribution of $amount from ${contribution.key} to $backgroundStart")
    }

    // Update the mutable color scheme with the interpolated colors
    colorScheme.backgroundStart = backgroundStart
    colorScheme.backgroundEnd = backgroundEnd
    colorScheme.foreground = foreground
}