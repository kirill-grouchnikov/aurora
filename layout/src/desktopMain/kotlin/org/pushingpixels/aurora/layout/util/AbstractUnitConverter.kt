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

import java.awt.Toolkit
import kotlin.math.roundToInt

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract implementation of the [UnitConverter] interface that
 * minimizes the effort required to convert font-dependent sizes to pixels.
 * 
 * @see DefaultUnitConverter
 * @see Size
 * @see Sizes
 */
public abstract class AbstractUnitConverter : UnitConverter {
    // Unit Converter Implementation *********************************************
    /**
     * Converts Inches and returns pixels using the specified resolution.
     * 
     * @param inch         the Inches
     * @return the given Inches as pixels
     */
    public override fun inchAsPixel(inch: Double): Int {
        return inchAsPixel(inch, getScreenResolution())
    }

    /**
     * Converts Millimeters and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param mm            Millimeters
     * @return the given Millimeters as pixels
     */
    public override fun millimeterAsPixel(mm: Double): Int {
        return millimeterAsPixel(mm, getScreenResolution())
    }

    /**
     * Converts Centimeters and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param cm            Centimeters
     * @return the given Centimeters as pixels
     */
    public override fun centimeterAsPixel(cm: Double): Int {
        return centimeterAsPixel(cm, getScreenResolution())
    }

    /**
     * Converts DTP Points and returns pixels using the resolution of the
     * given component's graphics object.
     * 
     * @param pt            DTP Points
     * @return the given Points as pixels
     */
    public override fun pointAsPixel(pt: Int): Int {
        return pointAsPixel(pt.toDouble(), getScreenResolution())
    }

    /**
     * Converts horizontal dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform, and l&amp;f.
     * 
     * @param dluX  the horizontal dialog units
     * @return the given horizontal dialog units as pixels
     */
    public override fun dialogUnitXAsPixel(dluX: Int): Int {
        return dialogUnitXAsPixel(dluX, getDialogBaseUnitsX())
    }

    /**
     * Converts vertical dialog units and returns pixels.
     * Honors the resolution, dialog font size, platform, and l&amp;f.
     * 
     * @param dluY  the vertical dialog units
     * @return the given vertical dialog units as pixels
     */
    public override fun dialogUnitYAsPixel(dluY: Int): Int {
        return dialogUnitYAsPixel(dluY, getDialogBaseUnitsY())
    }

    // Abstract Behavior *****************************************************
    /**
     * Gets and returns the horizontal dialog base units.
     * Implementations are encouraged to cache previously computed
     * dialog base units.
     * 
     * @return the horizontal dialog base units
     */
    protected abstract fun getDialogBaseUnitsX(): Double

    /**
     * Gets and returns the vertical dialog base units.
     * Implementations are encouraged to cache previously computed
     * dialog base units.
     * 
     * @return the vertical dialog base units
     */
    protected abstract fun getDialogBaseUnitsY(): Double

    /**
     * Converts horizontal dialog units and returns pixels.
     * 
     * @param dluX                  the horizontal dialog units
     * @param dialogBaseUnitsX      the horizontal dialog base units
     * @return the given dialog base units as pixels
     */
    protected fun dialogUnitXAsPixel(dluX: Int, dialogBaseUnitsX: Double): Int {
        return (dluX * dialogBaseUnitsX / 4).roundToInt()
    }

    /**
     * Converts vertical dialog units and returns pixels.
     * 
     * @param dluY                  the vertical dialog units
     * @param dialogBaseUnitsY      the vertical dialog base units
     * @return the given dialog base units as pixels
     */
    protected fun dialogUnitYAsPixel(dluY: Int, dialogBaseUnitsY: Double): Int {
        return (dluY * dialogBaseUnitsY / 8).roundToInt()
    }

    /**
     * Returns the components screen resolution or the default screen
     * resolution if the component is null or has no toolkit assigned yet.
     * 
     * @return the component's screen resolution
     */
    protected fun getScreenResolution(): Int {
        return this.defaultScreenResolution
    }

    protected val defaultScreenResolution: Int
        /**
         * Computes and returns the default resolution.
         * 
         * @return the default screen resolution
         */
        get() {
            if (Companion.defaultScreenResolution == -1) {
                Companion.defaultScreenResolution =
                    Toolkit.getDefaultToolkit().screenResolution
            }
            return Companion.defaultScreenResolution
        }

    protected companion object {
        private const val DTP_RESOLUTION = 72

        // Convenience Methods ***************************************************
        /**
         * Converts Inches and returns pixels using the specified resolution.
         * 
         * @param inch    the Inches
         * @param dpi   the resolution
         * @return the given Inches as pixels
         */
        protected fun inchAsPixel(inch: Double, dpi: Int): Int {
            return (dpi * inch).roundToInt()
        }

        /**
         * Converts Millimeters and returns pixels using the specified resolution.
         * 
         * @param mm    Millimeters
         * @param dpi   the resolution
         * @return the given Millimeters as pixels
         */
        protected fun millimeterAsPixel(mm: Double, dpi: Int): Int {
            return (dpi * mm * 10 / 254).roundToInt()
        }

        /**
         * Converts Centimeters and returns pixels using the specified resolution.
         * 
         * @param cm    Centimeters
         * @param dpi   the resolution
         * @return the given Centimeters as pixels
         */
        protected fun centimeterAsPixel(cm: Double, dpi: Int): Int {
            return (dpi * cm * 100 / 254).roundToInt()
        }

        /**
         * Converts DTP Points and returns pixels using the specified resolution.
         * 
         * @param pt    DTP Points
         * @param dpi   the resolution in dpi
         * @return the given Points as pixels
         */
        protected fun pointAsPixel(pt: Double, dpi: Int): Int {
            return (dpi * pt / DTP_RESOLUTION).roundToInt()
        }

        private var defaultScreenResolution = -1
    }
}
