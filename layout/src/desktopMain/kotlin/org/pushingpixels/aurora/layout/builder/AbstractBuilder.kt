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
package org.pushingpixels.aurora.layout.builder

import androidx.compose.foundation.layout.PaddingValues
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.ColumnSpec
import org.pushingpixels.aurora.layout.RowSpec
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An abstract class that minimizes the effort required to implement
 * non-visual builders that use the [org.pushingpixels.aurora.layout.FormLayout].
 *
 * Builders hide details of the FormLayout and provide convenience behavior
 * that assists you in constructing a form, bar, stack.
 * This class provides a cell cursor that helps you traverse a form while
 * you add components. Also, it offers several methods to append custom
 * and logical columns and rows.
 * 
 * @see [ButtonBar.Builder]
 * @see [ButtonStack.Builder]
 * @see [Panel.Builder]
 * @see [DefaultFormBuilder]
 */
public abstract class AbstractBuilder protected constructor(public val componentFactory: ComponentFactory) {
    protected val colSpecs: MutableList<ColumnSpec> = arrayListOf()
    protected val rowSpecs: MutableList<RowSpec> = arrayListOf()
    protected var padding: PaddingValues = PaddingValues.Zero

    /**
     * Holds an instance of [CellConstraints] that will be used to
     * specify the location, extent and alignments of the component to be
     * added next.
     */
    protected var currentCellConstraints: CellConstraints = CellConstraints(gridX = 1, gridY = 1)

    // Accessors ************************************************************

    /**
     * Returns the number of columns in the form.
     * 
     * @return the number of columns
     */
    public fun getColumnCount(): Int {
        return colSpecs.size
    }

    /**
     * Returns the number of rows in the form.
     * 
     * @return the number of rows
     */
    public fun getRowCount(): Int {
        return rowSpecs.size
    }
}
