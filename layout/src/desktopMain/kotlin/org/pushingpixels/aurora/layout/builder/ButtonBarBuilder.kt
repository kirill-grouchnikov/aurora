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
 * Builds consistent button bars that comply with popular style guides.
 * Utilizes the [FormLayout] and honors the platform's
 * [org.pushingpixels.aurora.layout.util.LayoutStyle] regarding button sizes, and gaps.<p>
 *
 * <strong>Examples:</strong> (note that in each of the lambdas you must use the passed in
 * `builderModifier` to have that button properly positioned in the button bar)
 * ```kotlin
 * // 1) Build and emit a bar with three related buttons
 * ButtonBar(modifier, padding) {
 *     button { builderModifier -> newButton }
 *     relatedGap()
 *     button { builderModifier -> editButton }
 *     relatedGap()
 *     button { builderModifier -> deleteButton }
 * }
 *
 * // 2) Short hand for example 1)
 * ButtonBar(modifier, padding) {
 *     buttons({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 * }
 *
 * // 3) Build and return a bar with two sections
 * ButtonBar(modifier, padding) {
 *     button({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 *     unrelatedGap()
 *     button({ builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 * }
 *
 * // 4) Short hand for example 3)
 * ButtonBar(modifier, padding) {
 *     buttons({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton },
 *                null,
 *                { builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 * }
 *
 * // 5) Build and return a complex button bar
 * ButtonBar(modifier, padding) {
 *     button({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 *     unrelatedGap()
 *     button({ builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 *     glue()
 *     growing({ builderModifier -> legendComponent })
 * }
 * ```
 *
 * @see [ButtonStackBuilder]
 * @see [org.pushingpixels.aurora.layout.util.LayoutStyle]
 */
public class ButtonBarBuilder(componentFactory: ComponentFactory): AbstractButtonPanelBuilder(componentFactory) {
    @Composable
    public override fun button(button: ComponentLambda) {
        appendColumn(FormSpecs.ButtonColSpec)
        componentLambdas.add(Pair(button, currentCellConstraints))
        nextColumn()
    }

    @Composable
    public fun button(text: String, icon: Painter? = null, action: () -> Unit, isEnabled: Boolean = true) {
        button(createButton(text, icon, action, isEnabled))
    }

    @Composable
    public override fun relatedGap() {
        appendRelatedComponentsGapColumn()
        nextColumn()
    }

    @Composable
    public override fun unrelatedGap() {
        appendUnrelatedComponentsGapColumn()
        nextColumn()
    }

    @Composable
    public fun glue() {
        appendGlueColumn()
        nextColumn()
    }

    @Composable
    public fun strut(width: ConstantSize) {
        appendColumn(ColumnSpec.createGap(width))
        nextColumn()
    }

    @Composable
    public fun fixed(component: ComponentLambda) {
        appendColumn(FormSpecs.PrefColSpec)
        this.componentLambdas.add(Pair(component, currentCellConstraints))
        nextColumn()
    }

    @Composable
    public fun growing(component: ComponentLambda) {
        appendColumn(FormSpecs.GrowingButtonColSpec)
        this.componentLambdas.add(Pair(component, currentCellConstraints))
        nextColumn()
    }

    @Composable
    public fun build(modifier: Modifier) {
        val constraintsMapping = componentLambdas.map {
            CellConstraints.xy(col = it.second.gridX, row = 1)
        }
        FormLayout(
            modifier = modifier,
            colSpecs = colSpecs,
            rowSpecs = arrayListOf(RowSpec.decode("center:pref")),
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
public fun ButtonBar(modifier: Modifier, padding: PaddingValues, block: @Composable ButtonBarBuilder.() -> Unit) {
    require(LocalFormLayoutInitialized.current) {
        "Initialize the FormLayout parameters via `FormCortex` first"
    }

    val builder = ButtonBarBuilder(LocalComponentFactory.current)
    builder.block()
    builder.build(modifier.padding(padding))
}
