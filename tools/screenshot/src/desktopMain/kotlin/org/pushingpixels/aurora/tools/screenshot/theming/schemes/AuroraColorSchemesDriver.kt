/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.tools.screenshot.theming.schemes

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.colorscheme.*
import org.pushingpixels.aurora.tools.screenshot.screenshot
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

private fun getSchemeBasedSkin(
    colorScheme: AuroraColorScheme,
    displayName: String? = null
): Pair<String, AuroraSkinDefinition> {
    return Pair(
        displayName ?: colorScheme.displayName,
        if (colorScheme.isDark) robotDefaultDarkSkin(colorScheme) else robotDefaultSkin(colorScheme)
    )
}

private fun getAuroraSchemeBasedSkins(): List<Pair<String, AuroraSkinDefinition>> {
    return listOf(
        getSchemeBasedSkin(AquaColorScheme()),
        getSchemeBasedSkin(BarbyPinkColorScheme()),
        getSchemeBasedSkin(BottleGreenColorScheme()),
        getSchemeBasedSkin(BrownColorScheme()),
        getSchemeBasedSkin(CharcoalColorScheme()),
        getSchemeBasedSkin(CremeColorScheme()),
        getSchemeBasedSkin(DarkVioletColorScheme()),
        getSchemeBasedSkin(DesertSandColorScheme()),
        getSchemeBasedSkin(EbonyColorScheme()),
        getSchemeBasedSkin(JadeForestColorScheme()),
        getSchemeBasedSkin(LightAquaColorScheme()),
        getSchemeBasedSkin(LimeGreenColorScheme()),
        getSchemeBasedSkin(OliveColorScheme()),
        getSchemeBasedSkin(OrangeColorScheme()),
        getSchemeBasedSkin(PurpleColorScheme()),
        getSchemeBasedSkin(RaspberryColorScheme()),
        getSchemeBasedSkin(SepiaColorScheme()),
        getSchemeBasedSkin(SteelBlueColorScheme()),
        getSchemeBasedSkin(SunGlareColorScheme()),
        getSchemeBasedSkin(SunsetColorScheme()),
        getSchemeBasedSkin(TerracottaColorScheme()),
        getSchemeBasedSkin(UltramarineColorScheme()),

        getSchemeBasedSkin(PurpleColorScheme().saturate(-0.4f), "Derived Desaturate"),
        getSchemeBasedSkin(PurpleColorScheme().saturate(0.4f), "Derived Saturate"),
        getSchemeBasedSkin(PurpleColorScheme().hueShift(0.4f), "Derived Hue Shift"),
        getSchemeBasedSkin(PurpleColorScheme().invert(), "Derived Invert"),
        getSchemeBasedSkin(PurpleColorScheme().negate(), "Derived Negate"),
        getSchemeBasedSkin(PurpleColorScheme().shade(0.4f), "Derived Shade"),
        getSchemeBasedSkin(PurpleColorScheme().tint(0.4f), "Derived Tint"),
        getSchemeBasedSkin(PurpleColorScheme().tone(0.4f), "Derived Tone"),
        getSchemeBasedSkin(
            PurpleColorScheme().shift(
                backgroundShiftColor = Color(128, 255, 128),
                backgroundShiftFactor = 0.8f,
                foregroundShiftColor = Color(128, 0, 0),
                foregroundShiftFactor = 0.7f
            ), "Derived Shift"
        ),
    )
}

fun main(args: Array<String>) = auroraApplication {
    val auroraSkins = getAuroraSchemeBasedSkins()
    val counter = AtomicInteger(auroraSkins.size)
    for (auroraSkinDef in auroraSkins) {
        val skinName = auroraSkinDef.first
        val skinDefinition = auroraSkinDef.second
        val filename =
            args[0] + "/" + skinName.replace(" ", "").lowercase(Locale.getDefault()) + ".png"
        println("Writing $filename")
        screenshot(skinDefinition, filename, IconFilterStrategy.Original, counter)
    }
}

