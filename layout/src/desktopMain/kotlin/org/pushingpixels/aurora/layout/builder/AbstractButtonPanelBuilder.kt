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

import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

public abstract class AbstractButtonPanelBuilder<out B: AbstractButtonPanelBuilder<B>>(componentFactory: ComponentFactory): AbstractBuilder<B>(componentFactory) {
    protected fun nextColumn(columns: Int = 1) {
        val currGridX = this.currentCellConstraints.gridX
        val currGridY = this.currentCellConstraints.gridY
        this.currentCellConstraints = CellConstraints(gridX = currGridX + columns, gridY = currGridY)
    }

    protected fun nextRow(rows: Int = 1) {
        val currGridX = this.currentCellConstraints.gridX
        val currGridY = this.currentCellConstraints.gridY
        this.currentCellConstraints = CellConstraints(gridX = currGridX, gridY = currGridY + rows)
    }

    protected fun appendColumn(columnSpec: ColumnSpec) {
        this.colSpecs.add(columnSpec)
    }

    protected fun appendGlueColumn() {
        appendColumn(FormSpecs.GlueColSpec)
    }

    protected fun appendRelatedComponentsGapColumn() {
        appendColumn(FormSpecs.RelatedGapColSpec)
    }

    protected fun appendUnrelatedComponentsGapColumn() {
        appendColumn(FormSpecs.UnrelatedGapColSpec)
    }

    protected fun appendRow(rowSpec: RowSpec) {
        this.rowSpecs.add(rowSpec)
    }

    protected fun appendGlueRow() {
        appendRow(FormSpecs.GlueRowSpec)
    }

    protected fun appendRelatedComponentsGapRow() {
        appendRow(FormSpecs.RelatedGapRowSpec)
    }

    protected fun appendUnrelatedComponentsGapRow() {
        appendRow(FormSpecs.UnrelatedGapRowSpec)
    }

    public abstract fun addButton(button: ComponentLambda): B

    public abstract fun addRelatedGap(): B

    public abstract fun addUnrelatedGap(): B

    public open fun addButtons(vararg buttons: ComponentLambda?): AbstractButtonPanelBuilder<B> {
        require(buttons.isNotEmpty()) {
            "The button array must not be empty."
        }
        var needsGap = false
        for (button in buttons) {
            if (button == null) {
                addUnrelatedGap()
                needsGap = false
                continue
            }
            if (needsGap) {
                addRelatedGap()
            }
            addButton(button)
            needsGap = true
        }
        return this as B
    }

    protected fun createButton(text: String, icon: Painter?, action: (() -> Unit)?, isEnabled: Boolean): ComponentLambda {
        return this.componentFactory.createButton(text, icon, action, isEnabled)
    }
}