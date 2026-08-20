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
package org.pushingpixels.aurora.layout.util

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An interface that describes how to convert general sizes to pixel sizes.
 * For example, *dialog units* require a conversion that honors
 * the font and resolution. The [Sizes] class
 * delegates all size conversions to an implementation of this interface.
 * 
 * @see Sizes
 * @see ConstantSize
 * @see AbstractUnitConverter
 * @see DefaultUnitConverter
 */
public interface UnitConverter {
    /**
     * Converts Inches and returns pixels using the specified resolution.
     * 
     * @param inch         the Inches
     * @return the given Inches as pixels
     */
    public fun inchAsPixel(inch: Double): Int

    /**
     * Converts Millimeters and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param mm         Millimeters
     * @return the given Millimeters as pixels
     */
    public fun millimeterAsPixel(mm: Double): Int

    /**
     * Converts Centimeters and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param cm         Centimeters
     * @return the given Centimeters as pixels
     */
    public fun centimeterAsPixel(cm: Double): Int

    /**
     * Converts DTP Points and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param pt          DTP Points
     * @return the given Points as pixels
     */
    public fun pointAsPixel(pt: Int): Int

    /**
     * Converts horizontal dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform and look&amp;feel.
     * 
     * @param dluX       the horizontal dialog units
     * @return the given horizontal dialog units as pixels
     */
    public fun dialogUnitXAsPixel(dluX: Int): Int

    /**
     * Converts vertical dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform and look&amp;feel.
     * 
     * @param dluY       the vertical dialog units
     * @return the given vertical dialog units as pixels
     */
    public fun dialogUnitYAsPixel(dluY: Int): Int
}
