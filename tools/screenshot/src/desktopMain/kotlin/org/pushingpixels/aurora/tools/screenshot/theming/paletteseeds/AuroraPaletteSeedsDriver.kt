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
package org.pushingpixels.aurora.tools.screenshot.theming.paletteseeds

import androidx.compose.ui.graphics.Color
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.tools.screenshot.screenshot
import org.pushingpixels.aurora.window.auroraApplication
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

private fun getLightSeedBasedSkin(seed: Color, displayName: String): Pair<String, AuroraSkinDefinition> {
    return Pair(displayName, robotDefaultLightSkin(accentColor = seed, name = displayName))
}

private fun getDarkSeedBasedSkin(seed: Color, displayName: String): Pair<String, AuroraSkinDefinition> {
    return Pair(displayName, robotDefaultDarkSkin(accentColor = seed, name = displayName))
}

private fun getAuroraLightSeedBasedSkins(): List<Pair<String, AuroraSkinDefinition>> {
    return auroraLightColors.map {
        getLightSeedBasedSkin(seed = it.value, displayName = it.key)
    }
}

private fun getAuroraDarkSeedBasedSkins(): List<Pair<String, AuroraSkinDefinition>> {
    return auroraDarkColors.map {
        getDarkSeedBasedSkin(seed = it.value, displayName = it.key)
    }
}

fun main(args: Array<String>) = auroraApplication {
    val folder = args[0]
    File(folder).mkdirs()

    val auroraLightSkins = getAuroraLightSeedBasedSkins()
    val lightCounter = AtomicInteger(auroraLightSkins.size)
    for (auroraLightSkinDef in auroraLightSkins) {
        val skinName = auroraLightSkinDef.first
        val skinDefinition = auroraLightSkinDef.second
        val filename =
            args[0] + "/" + skinName.replace(" ", "").lowercase(Locale.getDefault()) + ".png"
        println("Writing $filename")
        screenshot(skinDefinition, filename, IconFilterStrategy.Original, lightCounter)
    }

    val auroraDarkSkins = getAuroraDarkSeedBasedSkins()
    val darkCounter = AtomicInteger(auroraDarkSkins.size)
    for (auroraDarkSkinDef in auroraDarkSkins) {
        val skinName = auroraDarkSkinDef.first
        val skinDefinition = auroraDarkSkinDef.second
        val filename =
            args[0] + "/" + skinName.replace(" ", "").lowercase(Locale.getDefault()) + ".png"
        println("Writing $filename")
        screenshot(skinDefinition, filename, IconFilterStrategy.Original, darkCounter)
    }
}

