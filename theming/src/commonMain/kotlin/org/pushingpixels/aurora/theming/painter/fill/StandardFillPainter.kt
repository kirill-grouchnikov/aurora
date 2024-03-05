/*
 * Copyright 2020-2024 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.painter.fill

import org.pushingpixels.aurora.common.interpolateTowards

/**
 * Fill painter that paints a subtle gradient appearance.
 *
 * @author Kirill Grouchnikov
 */
open class StandardFillPainter : FractionBasedFillPainter(
    0.0f to { it.darkColor.interpolateTowards(it.midColor, 0.4f) },
    0.5f to { it.midColor },
    1.0f to { it.ultraLightColor },
    displayName = "Standard"
)