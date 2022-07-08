/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.theming.utils

import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.Shader

internal fun getDuotoneEffect(): RuntimeEffect {
    // Duotone shader
    val duotoneDesc = """
            uniform shader shaderInput;
            uniform vec4 colorLight;
            uniform vec4 colorDark;
            uniform float alpha;
            
            half4 main(vec2 fragcoord) { 
                vec4 inputColor = shaderInput.eval(fragcoord);
                float luma = dot(inputColor.rgb, vec3(0.299, 0.587, 0.114));
                vec4 duotone = mix(colorLight, colorDark, luma);
                return vec4(duotone.r * alpha, duotone.g * alpha, duotone.b * alpha, alpha);
            }
        """

    return RuntimeEffect.makeForShader(duotoneDesc)
}

internal fun getBrushedMetalShader(): Shader {
    // Fractal noise shader
    val noiseShader = Shader.makeFractalNoise(
        baseFrequencyX = 0.45f,
        baseFrequencyY = 0.45f,
        numOctaves = 4,
        seed = 0.0f
    )

    // Brushed metal shader
    val brushedMetalDesc = """
            uniform shader shaderInput;

            half4 main(vec2 fragcoord) { 
              vec4 inputColor = shaderInput.eval(vec2(0, fragcoord.y));
              // Compute the luma at the first pixel in this row
              float luma = dot(inputColor.rgb, vec3(0.299, 0.587, 0.114));
              // Apply modulation to stretch and shift the texture for the brushed metal look 
              float modulated = abs(cos((0.004 + 0.02 * luma) * (fragcoord.x + 200) + 0.26 * luma) 
                  * sin((0.06 - 0.25 * luma) * (fragcoord.x + 85) + 0.75 * luma));
              // Map 0.0-1.0 range to inverse 0.15-0.3
              float modulated2 = 0.3 - modulated / 6.5;
              half4 result = half4(modulated2, modulated2, modulated2, 1.0);
              return result;
            }
    """
    val brushedMetalEffect = RuntimeEffect.makeForShader(brushedMetalDesc)
    val brushedMetalShader = brushedMetalEffect.makeShader(
        uniforms = null,
        children = arrayOf(noiseShader),
        localMatrix = null,
        isOpaque = false
    )
    return brushedMetalShader
}

internal fun getSpecularRectangularEffect(): RuntimeEffect {
    val specularRectangularDesc = """
            uniform vec4 color;
            uniform float alpha;
            uniform float width;
            uniform float height;
            uniform float topLeftCornerRadius;
            uniform float topRightCornerRadius;
            uniform float gap;

            vec2 start = vec2(0.0, 0.0);
            vec2 control1 = vec2(0.5, 0.1);
            vec2 control2 = vec2(0.6, 0.9);
            vec2 end = vec2(1.0, 1.0);
            
            vec2 spline(vec2 start, vec2 control1, vec2 control2, vec2 end, float t) {
                // https://en.wikipedia.org/wiki/B%C3%A9zier_curve
                float invT = 1.0 - t;
                return start * invT * invT * invT + control1 * 3.0 * t * invT * invT + control2 * 3.0 * t * t * invT + end * t * t * t;
            }

            half4 main(vec2 fragcoord) {
                // leading vertical gap
                if (fragcoord.y <= gap) {
                    return vec4(0.0, 0.0, 0.0, 0.0);
                }
                
                // compute x-alpha based on the distance from left / right edges
                float xalpha = 1.0;
                if (fragcoord.x <= width / 2) {
                    // closer to the left edge
                    float overlayXStart = gap;
                    if ((topLeftCornerRadius > 0.0) && (fragcoord.y <= (gap + topLeftCornerRadius))) {
                        // We are within the vertical span of the top-left corner
                        float dy = gap + topLeftCornerRadius - fragcoord.y;
                        float dx = sqrt(topLeftCornerRadius * topLeftCornerRadius - dy * dy);
                        overlayXStart = gap + topLeftCornerRadius - dx;
                    }
                    if (fragcoord.x <= overlayXStart) {
                        // leading horizontal gap
                        xalpha = 0.0;
                    } else if (fragcoord.x <= (overlayXStart + gap)) {
                        // ramp-up to full alpha horizontally
                        float cfraction = (overlayXStart + gap - fragcoord.x) / gap;
                        xalpha = spline(start, control1, control2, end, 1.0 - cfraction).y;
                    }
                } else {
                    // closer to the right edge
                    float overlayXEnd = width - gap;
                    if ((topRightCornerRadius > 0.0) && (fragcoord.y <= (gap + topRightCornerRadius))) {
                        // We are within the vertical span of the top-right corner
                        float dy = gap + topRightCornerRadius - fragcoord.y;
                        float dx = sqrt(topRightCornerRadius * topRightCornerRadius - dy * dy);
                        overlayXEnd = width - gap - topRightCornerRadius + dx;
                    }
                    if (fragcoord.x >= overlayXEnd) {
                        // trailing horizontal gap
                        xalpha = 0.0;
                    } else if (fragcoord.x >= (overlayXEnd - gap)) {
                        // ramp-down to zero alpha horizontally
                        float cfraction = (fragcoord.x - (overlayXEnd - gap)) / gap;
                        xalpha = spline(start, control1, control2, end, 1.0 - cfraction).y;
                    }
                }
                
                // quick ramp-up
                if (fragcoord.y <= 2 * gap) {
                    float cfraction = (fragcoord.y - gap) / gap;
                    float falpha = xalpha * alpha * spline(start, control1, control2, end, cfraction).y;
                    return vec4(color.r * falpha, color.g * falpha, color.b * falpha, falpha); 
                }
                
                // slower ramp-down
                if (fragcoord.y <= height / 2.0) {
                    float cfraction = (fragcoord.y - 2 * gap) / (height / 2.0 - 2 * gap);
                    float falpha = xalpha * alpha * spline(start, control1, control2, end, 1.0 - cfraction).y;
                    return vec4(color.r * falpha, color.g * falpha, color.b * falpha, falpha); 
                }

                // nothing in bottom half
                return vec4(0.0, 0.0, 0.0, 0.0);
            }
        """

    return RuntimeEffect.makeForShader(specularRectangularDesc)
}
