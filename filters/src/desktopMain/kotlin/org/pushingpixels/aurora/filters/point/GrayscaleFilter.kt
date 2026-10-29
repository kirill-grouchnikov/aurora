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
package org.pushingpixels.aurora.filters.point

import org.intellij.lang.annotations.Language
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which converts an image to grayscale using the standard luma weights
 * (0.299*R + 0.587*G + 0.114*B).
 */
object GrayscaleFilter : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                return half4(luma, luma, luma, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }
}
