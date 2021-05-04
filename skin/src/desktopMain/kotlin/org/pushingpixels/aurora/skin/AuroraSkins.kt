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
package org.pushingpixels.aurora.skin

import org.pushingpixels.aurora.AuroraSkinDefinition
import org.pushingpixels.aurora.LocalSkinColors

fun getAuroraSkins(): List<Pair<String, () -> AuroraSkinDefinition>> {
    return listOf(
        Pair("Autumn", ::autumnSkin),
        Pair("Business", ::businessSkin),
        Pair("Business Black Steel", ::businessBlackSteelSkin),
        Pair("Business Blue Steel", ::businessBlueSteelSkin),
        Pair("Cerulean", ::ceruleanSkin),
        Pair("Creme", ::cremeSkin),
        Pair("Creme Coffee", ::cremeCoffeeSkin),
        Pair("Dust", ::dustSkin),
        Pair("Dust Coffee", ::dustCoffeeSkin),
        Pair("Gemini", ::geminiSkin),
        Pair("Graphite", ::graphiteSkin),
        Pair("Graphite Aqua", ::graphiteAquaSkin),
        Pair("Graphite Chalk", ::graphiteChalkSkin),
        Pair("Graphite Electric", ::graphiteElectricSkin),
        Pair("Graphite Glass", ::graphiteGlassSkin),
        Pair("Graphite Gold", ::graphiteGoldSkin),
        Pair("Graphite Sienna", ::graphiteSiennaSkin),
        Pair("Graphite Sunset", ::graphiteSunsetSkin),
        Pair("Green Magic", ::greenMagicSkin),
        Pair("Magellan", ::magellanSkin),
        Pair("Mariner", ::marinerSkin),
        Pair("Mist Aqua", ::mistAquaSkin),
        Pair("Mist Silver", ::mistSilverSkin),
        Pair("Moderate", ::moderateSkin),
        Pair("Nebula", ::nebulaSkin),
        Pair("Nebula Amethyst", ::nebulaAmethystSkin),
        Pair("Nebula Brick Wall", ::nebulaBrickWallSkin),
        Pair("Night Shade", ::nightShadeSkin),
        Pair("Raven", ::ravenSkin),
        Pair("Sahara", ::saharaSkin),
        Pair("Sentinel", ::sentinelSkin),
        Pair("Twilight", ::twilightSkin),
    )
}
