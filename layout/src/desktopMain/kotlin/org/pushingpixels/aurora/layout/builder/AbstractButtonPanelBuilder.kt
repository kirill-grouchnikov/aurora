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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.FormSpecs
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

public abstract class AbstractButtonPanelBuilder(componentFactory: ComponentFactory): AbstractBuilder(componentFactory) {
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

    @Composable
    protected fun appendGlueColumn() {
        appendColumn(FormSpecs.GlueColSpec)
    }

    @Composable
    protected fun appendRelatedComponentsGapColumn() {
        appendColumn(FormSpecs.RelatedGapColSpec)
    }

    @Composable
    protected fun appendUnrelatedComponentsGapColumn() {
        appendColumn(FormSpecs.UnrelatedGapColSpec)
    }

    @Composable
    protected fun appendGlueRow() {
        appendRow(FormSpecs.GlueRowSpec)
    }

    @Composable
    protected fun appendRelatedComponentsGapRow() {
        appendRow(FormSpecs.RelatedGapRowSpec)
    }

    @Composable
    protected fun appendUnrelatedComponentsGapRow() {
        appendRow(FormSpecs.UnrelatedGapRowSpec)
    }

    @Composable
    public abstract fun button(button: ComponentLambda)

    @Composable
    public abstract fun relatedGap()

    @Composable
    public abstract fun unrelatedGap()

    @Composable
    public open fun buttons(vararg buttons: ComponentLambda?) {
        require(buttons.isNotEmpty()) {
            "The button array must not be empty."
        }
        var needsGap = false
        for (button in buttons) {
            if (button == null) {
                unrelatedGap()
                needsGap = false
                continue
            }
            if (needsGap) {
                relatedGap()
            }
            button(button)
            needsGap = true
        }
    }

    protected fun createButton(text: String, icon: Painter?, action: () -> Unit, isEnabled: Boolean): ComponentLambda {
        return this.componentFactory.createButton(text, icon, action, isEnabled)
    }
}