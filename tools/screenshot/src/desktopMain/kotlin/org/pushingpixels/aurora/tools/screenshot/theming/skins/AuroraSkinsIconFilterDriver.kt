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
package org.pushingpixels.aurora.tools.screenshot.theming.skins

import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.getAuroraSkins
import org.pushingpixels.aurora.tools.screenshot.screenshot
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) = auroraApplication {
    val auroraSkins = getAuroraSkins()
    val counter = AtomicInteger(auroraSkins.size)
    for (auroraSkinDef in auroraSkins) {
        val skinName = auroraSkinDef.first
        val skinDefinition = auroraSkinDef.second
        val filename =
            args[0] + "/" + skinName.replace(" ", "")
                .lowercase(Locale.getDefault()) + "-filtered.png"
        println("Writing $filename")
        screenshot(
            skinDefinition.invoke(),
            filename,
            IconFilterStrategy.ThemedFollowColorTokens,
            counter
        )
    }
}

