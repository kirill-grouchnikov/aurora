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
package org.pushingpixels.aurora.loading

// This is a modified version of the original source code by Jakub Krehel
// available under the MIT license. See the full license under resources/Loading.license.

/**
 * Default animation durations for each spinner, in milliseconds.
 */
enum class SpinnerName(val duration: Long) {
    Arc(800),
    Atom(1000),
    Blocks(1300),
    BouncingDots(500),
    Cascade(1500),
    CircularDots(800),
    Classic(1200),
    ClassicV2(800),
    Clock(1200),
    Comet(700),
    Compass(500),
    Dual(1000),
    Eclipse(1200),
    Flip(1200),
    Gather(1600),
    Leap(1800),
    LinearDots(900),
    Loading(1000),
    Morph(1200),
    Orbit(750),
    Pulse(1200),
    Radar(1500),
    Ring(800),
    Ripple(1200),
    Slide(2400),
    Snake(1400),
    Swirl(1200),
    Trace(1200),
    Wave(900),
}

/**
 * Returns the effective duration for a spinner, allowing override via [params].
 */
fun SpinnerName.effectiveDuration(params: SpinnerParams): Long {
    return params.duration ?: this.duration
}
