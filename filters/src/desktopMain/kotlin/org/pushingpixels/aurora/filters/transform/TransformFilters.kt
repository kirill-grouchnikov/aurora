/**
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
package org.pushingpixels.aurora.filters.transform

import androidx.compose.ui.graphics.Color
import org.intellij.lang.annotations.Language
import org.jetbrains.skia.RuntimeEffect
import org.pushingpixels.aurora.filters.Filter

// This is a modified version of the original source code by Jerry Huxtable
// available under the MIT license. See the full license under resources/Filters.license.

/**
 * A filter which clips the image to a circle.
 * @param centerX center X as proportion of image width (0-1)
 * @param centerY center Y as proportion of image height (0-1)
 * @param radius radius as proportion of image size (0-1)
 */
class CircleFilter(
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f,
    val radius: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 center;
            uniform float radius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 icenter = imageSize * center;
                float r = radius * min(imageSize.x, imageSize.y) * 0.5;
                float dist = length(fragcoord - icenter);
                if (dist > r) {
                    return half4(0.0, 0.0, 0.0, 0.0);
                }
                return content.eval(fragcoord);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which creates a contour effect at a specified brightness level.
 * @param level contour brightness level (0-1)
 */
class ContourFilter(val level: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float level;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                half luma = dot(c.rgb, half3(0.299, 0.587, 0.114));
                half val = abs(luma - level) < 0.05 ? 1.0 : 0.0;
                return half4(val, val, val, c.a);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which creates a curl-up effect.
 * @param radius curl radius in pixels
 */
class CurlFilter(val radius: Float = 30f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 corner = imageSize;
                vec2 delta = fragcoord - corner;
                float dist = length(delta);
                if (dist > radius) {
                    return content.eval(fragcoord);
                }
                float angle = atan(delta.y, delta.x);
                float curlAngle = (dist / radius) * 3.14159;
                vec2 src = corner + vec2(cos(angle + curlAngle), sin(angle + curlAngle)) * dist;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which displaces pixels by an offset.
 * @param deltaX horizontal displacement in pixels
 * @param deltaY vertical displacement in pixels
 */
class DisplaceFilter(
    val deltaX: Float = 10f,
    val deltaY: Float = 10f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 offset;
            half4 main(vec2 fragcoord) {
                vec2 src = fragcoord + offset;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter that applies a field warp using sine waves.
 * @param amplitude warp amplitude in pixels
 * @param frequency warp frequency
 */
class FieldWarpFilter(
    val amplitude: Float = 10f,
    val frequency: Float = 0.1f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amplitude;
            uniform float frequency;
            half4 main(vec2 fragcoord) {
                float dx = amplitude * sin(fragcoord.y * frequency);
                float dy = amplitude * cos(fragcoord.x * frequency);
                vec2 src = fragcoord + vec2(dx, dy);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which applies a 3D flush effect.
 * @param angle flush angle in degrees
 */
class Flush3DFilter(val angle: Float = 45f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                float a = angle * 3.14159 / 180.0;
                float c = cos(a);
                float s = sin(a);
                // Perspective-like squash
                float z = delta.y * s + 1.0;
                vec2 src = center + vec2(delta.x * c / z, delta.y * c / z);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which creates a kaleidoscope effect by mirroring the image.
 * @param segments number of mirror segments
 */
class KaleidoscopeFilter(val segments: Int = 8) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform int segments;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                float angle = atan(delta.y, delta.x);
                float dist = length(delta);
                float segAngle = 6.28318 / float(segments);
                angle = mod(angle + 3.14159, segAngle) - segAngle * 0.5;
                vec2 src = center + vec2(cos(angle), sin(angle)) * dist;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which maps the image using a displacement map approximation.
 * @param scale displacement scale factor
 */
class MapFilter(val scale: Float = 10f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            half4 main(vec2 fragcoord) {
                half4 c = content.eval(fragcoord);
                vec2 offset = (c.rgb - 0.5) * scale;
                vec2 src = fragcoord + offset.xy;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which offsets the image by a specified amount.
 * @param deltaX horizontal offset in pixels
 * @param deltaY vertical offset in pixels
 */
class OffsetFilter(
    val deltaX: Float = 0f,
    val deltaY: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 offset;
            half4 main(vec2 fragcoord) {
                vec2 src = fragcoord + offset;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which applies a perspective transform.
 * @param tlTopLeftX top-left X offset
 * @param tlTopLeftY top-left Y offset
 * @param trTopRightX top-right X offset
 * @param trTopRightY top-right Y offset
 * @param brBottomRightX bottom-right X offset
 * @param brBottomRightY bottom-right Y offset
 * @param blBottomLeftX bottom-left X offset
 * @param blBottomLeftY bottom-left Y offset
 */
class PerspectiveFilter(
    val tlTopLeftX: Float = 0f,
    val tlTopLeftY: Float = 0f,
    val trTopRightX: Float = 0f,
    val trTopRightY: Float = 0f,
    val brBottomRightX: Float = 0f,
    val brBottomRightY: Float = 0f,
    val blBottomLeftX: Float = 0f,
    val blBottomLeftY: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform vec2 imageSize;
            uniform vec2 tl;
            uniform vec2 tr;
            uniform vec2 br;
            uniform vec2 bl;
            half4 main(vec2 fragcoord) {
                float u = fragcoord.x / imageSize.x;
                float v = fragcoord.y / imageSize.y;
                vec2 p0 = mix(tl, tr, u);
                vec2 p1 = mix(bl, br, u);
                vec2 p = mix(p0, p1, v);
                return content.eval(p);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which distorts an image by pinching it toward or away from the center.
 * @param scale pinch scale factor (>1 = pinch in, <1 = bulge out)
 * @param centerX center X as proportion of image width (0-1)
 * @param centerY center Y as proportion of image height (0-1)
 */
class PinchFilter(
    val scale: Float = 2f,
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float scale;
            uniform vec2 center;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 icenter = imageSize * center;
                vec2 delta = fragcoord - icenter;
                float angle = atan(delta.y, delta.x);
                float radius = length(delta);
                float maxRadius = length(imageSize * 0.5);
                radius *= scale;
                vec2 src = icenter + vec2(cos(angle), sin(angle)) * radius;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which converts between Cartesian and polar coordinates.
 * @param radius maximum radius as proportion of image size
 * @param polar true for polar, false for inverse polar
 */
class PolarFilter(
    val radius: Float = 0.5f,
    val polar: Boolean = true
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform int polar;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                float r = radius * min(imageSize.x, imageSize.y) * 0.5;
                if (polar != 0) {
                    // Cartesian to polar
                    float angle = atan(delta.y, delta.x);
                    float dist = length(delta);
                    vec2 src = center + vec2((angle / 3.14159 + 1.0) * 0.5, dist / r) * center;
                    return content.eval(src);
                } else {
                    // Polar to Cartesian
                    float theta = (delta.y / center.y) * 3.14159;
                    float phi = (delta.x / center.x) * 3.14159 * 2.0 - 3.14159;
                    vec2 src = center + vec2(cos(phi), sin(phi)) * theta * r / 3.14159;
                    return content.eval(src);
                }
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which distorts an image by rippling it in the X or Y directions.
 * Replicates the JH-Labs RippleFilter.
 * @param xAmplitude amplitude of ripple in X direction (pixels)
 * @param yAmplitude amplitude of ripple in Y direction (pixels)
 * @param xWavelength wavelength of ripple in X direction (pixels)
 * @param yWavelength wavelength of ripple in Y direction (pixels)
 * @param waveType type of wave: 0=SINE, 1=SAWTOOTH, 2=TRIANGLE, 3=NOISE
 */
class RippleFilter(
    val xAmplitude: Float = 5f,
    val yAmplitude: Float = 0f,
    val xWavelength: Float = 16f,
    val yWavelength: Float = 16f,
    val waveType: Int = 0
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float xAmplitude;
            uniform float yAmplitude;
            uniform float xWavelength;
            uniform float yWavelength;
            uniform int waveType;
            half4 main(vec2 fragcoord) {
                float nx = fragcoord.y / xWavelength;
                float ny = fragcoord.x / yWavelength;
                float fx, fy;
                if (waveType == 0) {
                    // SINE
                    fx = sin(nx * 6.28318);
                    fy = sin(ny * 6.28318);
                } else if (waveType == 1) {
                    // SAWTOOTH
                    fx = fract(nx);
                    fy = fract(ny);
                } else if (waveType == 2) {
                    // TRIANGLE
                    fx = abs(fract(nx) * 2.0 - 1.0);
                    fy = abs(fract(ny) * 2.0 - 1.0);
                } else {
                    // NOISE approximation
                    fx = fract(sin(dot(vec2(nx, 0.0), vec2(127.1, 311.7))) * 43758.5453) * 2.0 - 1.0;
                    fy = fract(sin(dot(vec2(ny, 0.0), vec2(127.1, 311.7))) * 43758.5453) * 2.0 - 1.0;
                }
                vec2 src = fragcoord + vec2(xAmplitude * fx, yAmplitude * fy);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which rotates an image.
 * @param angle rotation angle in degrees
 * @param centerX center X as proportion of image width (0-1)
 * @param centerY center Y as proportion of image height (0-1)
 */
class RotateFilter(
    val angle: Float = 0f,
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform vec2 center;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 icenter = imageSize * center;
                vec2 delta = fragcoord - icenter;
                float a = -angle * 3.14159 / 180.0;
                float c = cos(a);
                float s = sin(a);
                vec2 src = icenter + vec2(delta.x * c - delta.y * s, delta.x * s + delta.y * c);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter that creates a shapeburst effect — a distance transform from pixel brightness/alpha,
 * colored through a gradient colormap.
 *
 * Based on the original JH-Labs ShapeFilter (Stylize/Shapeburst).
 *
 * @param factor intensity factor for the distance mapping (0-1+)
 * @param colors optional colormap colors; when null, uses grayscale
 * @param useAlpha if true, uses pixel alpha as the source value; otherwise uses brightness
 * @param invert if true, inverts the colormap colors
 * @param merge if true, multiplies result with source pixels instead of replacing
 * @param type shape type: 0=LINEAR, 1=CIRCLE_UP, 2=CIRCLE_DOWN, 3=SMOOTH
 */
class ShapeFilter(
    val factor: Float = 1f,
    val colors: List<Color>? = null,
    val useAlpha: Boolean = true,
    val invert: Boolean = false,
    val merge: Boolean = false,
    val type: Int = 0
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform shader colormap;
            uniform float factor;
            uniform int useAlpha;
            uniform int invert;
            uniform int merge;
            uniform int type;
            uniform int hasColormap;
            uniform vec2 imageSize;
            uniform float maxDist;

            // Chamfer distance constants (scaled by 41)
            const float one = 41.0;
            const float sqrt2 = 57.98;  // 41 * sqrt(2)
            const float sqrt5 = 91.49;  // 41 * sqrt(5)

            half getMapValue(half4 c, int useAlpha) {
                if (useAlpha == 1) {
                    return c.a * 255.0 * 4.1;  // alpha (0-255) * one / 10
                } else {
                    return dot(c.rgb, half3(0.299, 0.587, 0.114)) * 255.0 * 4.1;
                }
            }

            half getMin(half a, half b) {
                return a < b ? a : b;
            }

            half computeDistance(vec2 fragcoord, int useAlpha) {
                // Sample a 9x9 neighborhood to approximate the distance transform
                // by finding the minimum weighted distance to a zero-value pixel
                half minDist = 9999.0;

                for (int dy = -4; dy <= 4; dy++) {
                    for (int dx = -4; dx <= 4; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        half4 c = content.eval(fragcoord + vec2(float(dx), float(dy)));
                        half val = getMapValue(c, useAlpha);
                        if (val <= 0.0) {
                            // Found a zero-value pixel; compute weighted distance
                            float dist;
                            int adx = abs(dx);
                            int ady = abs(dy);
                            int maxD = max(adx, ady);
                            int minD = min(adx, ady);
                            // Chamfer 3-distance: use max for axis-aligned, sqrt2 for diagonal steps
                            if (minD == 0) {
                                dist = float(maxD) * one;
                            } else {
                                // Diagonal component + straight component
                                dist = float(minD) * sqrt2 + float(maxD - minD) * one;
                            }
                            minDist = getMin(minDist, half(dist));
                        }
                    }
                }
                return minDist;
            }

            half applyType(half v, int type) {
                if (type == 1) {
                    // CIRCLE_UP: sin(acos(1 - 2*v) / 2)
                    return sin(acos(1.0 - 2.0 * v) * 0.5);
                } else if (type == 2) {
                    // CIRCLE_DOWN: 1 - sin(acos(2*v - 1) / 2)
                    return 1.0 - sin(acos(2.0 * v - 1.0) * 0.5);
                } else if (type == 3) {
                    // SMOOTH: smoothstep(0, 1, v) = v * v * (3 - 2*v)
                    return v * v * (3.0 - 2.0 * v);
                }
                // LINEAR: no change
                return v;
            }

            half4 main(vec2 fragcoord) {
                half4 src = content.eval(fragcoord);
                half mapVal = getMapValue(src, useAlpha);

                if (mapVal <= 0.0) {
                    // Background — keep original or clear
                    if (merge == 1) {
                        return src;
                    }
                    return half4(0.0, 0.0, 0.0, src.a);
                }

                // Compute distance to nearest zero-value pixel
                half dist = computeDistance(fragcoord, useAlpha);
                if (dist >= 9999.0) {
                    // No zero pixel found in neighborhood; use the source value
                    return src;
                }

                // Normalize distance by maxDist and apply factor
                float v = clamp(factor * float(dist) / maxDist, 0.0, 1.0);

                // Apply type transformation
                v = applyType(half(v), type);

                // Get color from colormap or use grayscale
                half4 col;
                if (hasColormap == 1) {
                    col = colormap.eval(vec2(v * 255.0, 0.0));
                } else {
                    col = half4(v, v, v, 1.0);
                }

                // Invert if needed
                if (invert == 1) {
                    col.rgb = 1.0 - col.rgb;
                }

                // Alpha from source
                half sa;
                if (useAlpha == 1) {
                    sa = src.a;
                } else {
                    sa = dot(src.rgb, half3(0.299, 0.587, 0.114));
                }

                if (merge == 1) {
                    // Multiply colormap color with source
                    col.r = col.r * src.r;
                    col.g = col.g * src.g;
                    col.b = col.b * src.b;
                    col.a = src.a;
                } else {
                    col.a = sa;
                }

                return col;
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which shears an image.
 * @param xShear horizontal shear factor
 * @param yShear vertical shear factor
 */
class ShearFilter(
    val xShear: Float = 0f,
    val yShear: Float = 0f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float xShear;
            uniform float yShear;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                vec2 src = center + vec2(delta.x + delta.y * xShear, delta.y + delta.x * yShear);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which distorts an image into a spherical shape.
 * @param radius sphere radius as proportion of image size (0-1)
 */
class SphereFilter(val radius: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float radius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                float dist = length(delta);
                float r = radius * min(imageSize.x, imageSize.y) * 0.5;
                if (dist > r) {
                    return half4(0.0, 0.0, 0.0, 0.0);
                }
                float theta = (dist / r) * 3.14159 * 0.5;
                float phi = atan(delta.y, delta.x);
                vec2 src = center + vec2(cos(phi), sin(phi)) * r * sin(theta);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which creates a swim/water distortion effect.
 * @param amplitude wave amplitude in pixels
 * @param wavelength wave length in pixels
 */
class SwimFilter(
    val amplitude: Float = 5f,
    val wavelength: Float = 50f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amplitude;
            uniform float wavelength;
            half4 main(vec2 fragcoord) {
                float dx = amplitude * sin(fragcoord.y / wavelength * 6.28318);
                float dy = amplitude * cos(fragcoord.x / wavelength * 6.28318);
                vec2 src = fragcoord + vec2(dx, dy);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which distorts an image by twisting it from the center out.
 * Replicates the JH-Labs TwirlFilter.
 * @param angle angle of twirl in radians (default 0 = no distortion)
 * @param centerX center X as proportion of image width (0-1)
 * @param centerY center Y as proportion of image height (0-1)
 * @param radius radius of the effect in pixels (default 100)
 */
class TwirlFilter(
    val angle: Float = 0f,
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f,
    val radius: Float = 100f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float angle;
            uniform vec2 center;
            uniform float radius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 icenter = imageSize * center;
                float dx = fragcoord.x - icenter.x;
                float dy = fragcoord.y - icenter.y;
                float distance2 = dx*dx + dy*dy;
                vec2 src;
                if (distance2 > radius*radius) {
                    src = fragcoord;
                } else {
                    float dist = sqrt(distance2);
                    float a = atan(dy, dx) + angle * (radius - dist) / radius;
                    src.x = icenter.x + dist * cos(a);
                    src.y = icenter.y + dist * sin(a);
                }
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter which distorts an image using a swirl/warp effect.
 * @param amount warp intensity
 */
class WarpFilter(val amount: Float = 0.5f) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float amount;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 center = imageSize * 0.5;
                vec2 delta = fragcoord - center;
                float dist = length(delta);
                float maxDist = length(center);
                float t = dist / maxDist;
                float angle = atan(delta.y, delta.x) + amount * sin(t * 6.28318);
                vec2 src = center + vec2(cos(angle), sin(angle)) * dist;
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}

/**
 * A filter that simulates water ripples distortion.
 * Replicates the JH-Labs WaterFilter.
 *
 * @param wavelength the wavelength of ripples (default 16)
 * @param amplitude the ripple amplitude (default 10)
 * @param phase the phase offset (default 0)
 * @param centerX the center X of ripples as fraction of width (default 0.5)
 * @param centerY the center Y of ripples as fraction of height (default 0.5)
 * @param radius the ripple radius in pixels (default 50)
 */
class WaterFilter(
    val wavelength: Float = 16f,
    val amplitude: Float = 10f,
    val phase: Float = 0f,
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f,
    val radius: Float = 50f
) : Filter {
    override val effect: RuntimeEffect by lazy {
        @Language("GLSL")
        val shaderDesc = """
            uniform shader content;
            uniform float wavelength;
            uniform float amplitude;
            uniform float phase;
            uniform float centerX;
            uniform float centerY;
            uniform float radius;
            uniform vec2 imageSize;
            half4 main(vec2 fragcoord) {
                vec2 p = fragcoord / imageSize;
                vec2 center = vec2(centerX, centerY);
                float dx = p.x - center.x;
                float dy = p.y - center.y;
                float dist = sqrt(dx * dx + dy * dy);
                float maxR = radius / imageSize.x;
                float amount = 0.0;
                if (dist < maxR) {
                    amount = 1.0 - dist / maxR;
                }
                float angle = atan(dy, dx);
                float wave = sin(dist / (wavelength / imageSize.x) * 6.2831853 + phase);
                float dx2 = wave * amplitude * amount * cos(angle);
                float dy2 = wave * amplitude * amount * sin(angle);
                vec2 src = fragcoord + vec2(dx2, dy2);
                return content.eval(src);
            }
        """
        RuntimeEffect.makeForShader(shaderDesc)
    }

}
