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
package org.pushingpixels.aurora.common

@AuroraInternalApi
enum class Platform {
    MacOS, Gnome, KDE, Windows, Unknown;

    companion object {
        val Current: Platform by lazy {
            val name = System.getProperty("os.name")
            if (name?.startsWith("Windows") == true) {
                return@lazy Windows
            }
            if (name?.startsWith("Mac") == true) {
                return@lazy MacOS
            }
            if ("true".equals(System.getenv("KDE_FULL_SESSION"))) {
                return@lazy KDE
            }
            if ("gnome".equals(System.getProperty("sun.desktop"))) {
                return@lazy Gnome
            }
            return@lazy Unknown
        }
    }
}