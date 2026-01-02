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
