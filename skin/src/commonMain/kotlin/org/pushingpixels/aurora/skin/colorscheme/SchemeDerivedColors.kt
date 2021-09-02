/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.skin.colorscheme

import androidx.compose.ui.graphics.Color

/**
 * Interface for derived color scheme colors.
 *
 * @author Kirill Grouchnikov
 */
interface SchemeDerivedColors {
    /**
     * Returns the line color for `this` scheme.
     *
     * @return The line color for `this` scheme.
     */
    val lineColor: Color

    /**
     * Returns the selection background color for `this` scheme.
     *
     * @return The selection background color for `this` scheme.
     */
    val selectionBackgroundColor: Color

    /**
     * Returns the selection foreground color for `this` scheme.
     *
     * @return The selection foreground color for `this` scheme.
     */
    val selectionForegroundColor: Color

    /**
     * Returns the background fill color for `this` scheme.
     *
     * @return The background fill color for `this` scheme.
     */
    val backgroundFillColor: Color

    /**
     * Returns the accented background fill color for `this` scheme.
     *
     * @return The accented background fill color for `this` scheme.
     */
    val accentedBackgroundFillColor: Color

    /**
     * Returns the text background fill color for `this` scheme.
     *
     * @return The text background fill color for `this` scheme.
     */
    val textBackgroundFillColor: Color

    /**
     * Returns the focus ring color for `this` scheme.
     *
     * @return The focus ring color for `this` scheme.
     */
    val focusRingColor: Color

    /**
     * Returns the primary separator color for `this` scheme.
     *
     * @return The primary separator color for `this` scheme.
     */
    val separatorPrimaryColor: Color

    /**
     * Returns the secondary separator color for `this` scheme.
     *
     * @return The secondary separator color for `this` scheme.
     */
    val separatorSecondaryColor: Color

    /**
     * Returns the mark color for `this` scheme. Mark color is used on
     * checkboxes, radio buttons, scrollbar arrows, combo arrows, menu arrows, etc.
     *
     * @return The mark color for `this` scheme.
     */
    val markColor: Color

    /**
     * Returns the echo color for `this` scheme. Echo color is used for
     * drawing slight echo / drop shadow around title pane texts and similar "primary"
     * elements.
     *
     * @return The echo color for `this` scheme.
     */
    val echoColor: Color
}