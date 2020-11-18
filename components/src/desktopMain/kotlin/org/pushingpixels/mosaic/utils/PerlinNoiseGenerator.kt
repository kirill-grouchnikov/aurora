/*
 * Copyright (c) 2020 Mosaic Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.mosaic.utils

import kotlin.math.floor

/**
 * A class for producing Perlin-inspired noise. The code written by Ken Perlin.
 *
 * @author Ken Perlin http://mrl.nyu.edu/~perlin/
 */
internal object PerlinNoiseGenerator {
    /**
     * Returns noise for the specified coordinates.
     *
     * @param x
     * X coordinate.
     * @param y
     * Y coordinate.
     * @param z
     * Z coordinate.
     * @return Noise for the specified coordinates.
     */
    fun noise(x: Double, y: Double, z: Double): Double {
        var x = x
        var y = y
        var z = z
        val X = floor(x).toInt() and 255
        // FIND UNIT CUBE THAT
        val Y = floor(y).toInt() and 255
        // CONTAINS POINT.
        val Z = floor(z).toInt() and 255
        x -= floor(x) // FIND RELATIVE X,Y,Z
        y -= floor(y) // OF POINT IN CUBE.
        z -= floor(z)
        val u = fade(x)
        // COMPUTE FADE CURVES
        val v = fade(y)
        // FOR EACH OF X,Y,Z.
        val w = fade(z)
        val A = p[X] + Y
        val AA = p[A] + Z
        val AB = p[A + 1] + Z
        // HASH COORDINATES
        // OF
        val B = p[X + 1] + Y
        val BA = p[B] + Z
        val BB = p[B + 1] + Z // THE 8 CUBE
        // CORNERS,
        return lerp(
            w, lerp(
                v, lerp(
                    u, grad(
                        p[AA], x, y, z
                    ),  // AND ADD
                    grad(p[BA], x - 1, y, z)
                ),  // BLENDED
                lerp(
                    u, grad(
                        p[AB], x, y - 1, z
                    ),  // RESULTS
                    grad(p[BB], x - 1, y - 1, z)
                )
            ),  // FROM 8
            lerp(
                v, lerp(
                    u, grad(
                        p[AA + 1], x, y, z - 1
                    ),  // CORNERS
                    grad(p[BA + 1], x - 1, y, z - 1)
                ),  // OF CUBE
                lerp(
                    u, grad(
                        p[AB + 1], x, y - 1, z - 1
                    ), grad(
                        p[BB + 1], x - 1, y - 1, z - 1
                    )
                )
            )
        )
    }

    /**
     * Fades the specified value.
     *
     * @param t
     * Value to fade.
     * @return faded value.
     */
    fun fade(t: Double): Double {
        return t * t * t * (t * (t * 6 - 15) + 10)
    }

    /**
     * Interpolates the specified value.
     *
     * @param t
     * Value.
     * @param a
     * Starting interpolation value.
     * @param b
     * Ending interpolation value.
     * @return Interpolated value.
     */
    fun lerp(t: Double, a: Double, b: Double): Double {
        return a + t * (b - a)
    }

    /**
     * @param hash
     * @param x
     * @param y
     * @param z
     * @return
     */
    fun grad(hash: Int, x: Double, y: Double, z: Double): Double {
        val h = hash and 15 // CONVERT LO 4 BITS OF HASH CODE
        val u = if (h < 8) x else y
        // INTO 12 GRADIENT DIRECTIONS.
        val v = if (h < 4) y else if (h == 12 || h == 14) x else z
        return (if (h and 1 == 0) u else -u) + if (h and 2 == 0) v else -v
    }

    /**
     * Permutations.
     */
    val p = IntArray(512)

    /**
     * Permutations.
     */
    val permutation = intArrayOf(
        151, 160, 137, 91, 90, 15, 131, 13, 201,
        95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37,
        240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62,
        94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56,
        87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139,
        48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133,
        230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25,
        63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200,
        196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3,
        64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255,
        82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42,
        223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153,
        101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79,
        113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242,
        193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249,
        14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204,
        176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222,
        114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180
    )

    init {
        for (i in 0..255) {
            p[i] = permutation[i]
            p[256 + i] = p[i]
        }
    }
}