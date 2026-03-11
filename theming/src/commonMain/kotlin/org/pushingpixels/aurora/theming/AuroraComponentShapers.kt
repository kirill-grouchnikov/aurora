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

import org.pushingpixels.aurora.theming.shaper.AuroraComponentShaper
import org.pushingpixels.aurora.theming.shaper.ClassicComponentShaper

class AuroraComponentShapers private constructor() {
    private val componentShaperMap: MutableMap<DecorationAreaType, AuroraComponentShaper> = hashMapOf()

    fun registerComponentShaper(componentShaper: AuroraComponentShaper, vararg areaTypes: DecorationAreaType) {
        for (areaType in areaTypes) {
            componentShaperMap[areaType] = componentShaper
        }
    }

    fun getComponentShaper(areaType: DecorationAreaType): AuroraComponentShaper {
        if (componentShaperMap.containsKey(areaType)) {
            return componentShaperMap[areaType]!!
        } else {
            return componentShaperMap[DecorationAreaType.None]!!
        }
    }

    companion object {
        fun withDefaults(componentShaper: AuroraComponentShaper): AuroraComponentShapers {
            val result = AuroraComponentShapers()
            result.registerComponentShaper(componentShaper, DecorationAreaType.None)
            result.registerComponentShaper(ClassicComponentShaper(),
                DecorationAreaType.TitlePane, DecorationAreaType.Header,
                DecorationAreaType.Toolbar, DecorationAreaType.Footer,
                DecorationAreaType.ControlPane)
            return result
        }

        fun withNoDefaults(componentShaper: AuroraComponentShaper): AuroraComponentShapers {
            val result = AuroraComponentShapers()
            result.registerComponentShaper(componentShaper, DecorationAreaType.None)
            return result
        }
    }
}