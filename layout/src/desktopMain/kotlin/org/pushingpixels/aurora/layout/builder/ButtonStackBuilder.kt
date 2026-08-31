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
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Builds consistent button stacks that comply with popular style guides.
 * Utilizes the [FormLayout] and honors the platform's
 * [org.pushingpixels.aurora.layout.util.LayoutStyle] regarding button sizes, and gaps.<p>
 *
 * <strong>Example:</strong><br> (note that in each of the lambdas you must use the passed in
 * `builderModifier` to have that button properly positioned in the button bar).
 * The following example builds a button stack with <i>Close, Up</i> and
 * <i>Down</i>, where Up and Down are related, and Close is not related
 * to the other buttons, which makes a wide gap for the unrelated and
 * a smaller gap for the related buttons.
 *
 * ```kotlin
 * ButtonStack(modifier, padding) {
 *     button { builderModifier -> closeButton }
 *     unrelatedGap()
 *     button { builderModifier -> upButton }
 *     relatedGap()
 *     button { builderModifier -> downButton }
 * }
 * ```
 *
 * @see [ButtonBarBuilder]
 * @see [org.pushingpixels.aurora.layout.util.LayoutStyle]
 */
public class ButtonStackBuilder(componentFactory: ComponentFactory): AbstractButtonPanelBuilder(componentFactory) {
    @Composable
    public override fun button(button: ComponentLambda) {
        appendRow(FormSpecs.PrefRowSpec)
        componentLambdas.add(Pair(button, currentCellConstraints))
        nextRow()
    }

    @Composable
    public fun button(text: String, icon: Painter? = null, action: () -> Unit, isEnabled: Boolean = true) {
        button(createButton(text, icon, action, isEnabled))
    }

    @Composable
    public override fun relatedGap() {
        appendRelatedComponentsGapRow()
        nextRow()
    }

    @Composable
    public override fun unrelatedGap() {
        appendUnrelatedComponentsGapRow()
        nextRow()
    }

    @Composable
    public fun addGlue() {
        appendGlueRow()
        nextRow()
    }

    @Composable
    public fun addStrut(size: ConstantSize) {
        appendRow(RowSpec(RowSpec.Top, size, FormSpec.NoGrow))
        nextRow()
    }

    @Composable
    public fun addFixed(component: ComponentLambda) {
        appendRow(FormSpecs.PrefRowSpec)
        this.componentLambdas.add(Pair(component, currentCellConstraints))
        nextRow()
    }

    @Composable
    public fun build(modifier: Modifier) {
        val constraintsMapping = componentLambdas.map {
            CellConstraints.xy(col = 1, row = it.second.gridY)
        }
        FormLayout(
            modifier = modifier,
            colSpecs = arrayListOf(FormSpecs.ButtonColSpec),
            rowSpecs = rowSpecs,
            debugConfiguration = this.debugConfiguration,
            constraintsMapping = constraintsMapping,
            content = {
                for ((componentLambda, _) in componentLambdas) {
                    componentLambda.invoke(this)
                }
            }
        )
    }
}

@Composable
public fun ButtonStack(modifier: Modifier, padding: PaddingValues, block: @Composable ButtonStackBuilder.() -> Unit) {
    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val builder = ButtonStackBuilder(LocalComponentFactory.current)
    builder.block()
    builder.build(modifier.padding(padding))
}
