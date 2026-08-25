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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.resolveDefaults
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

    private val componentLambdas: MutableList<Pair<ComponentLambda, CellConstraints>> = arrayListOf()

    public override fun button(button: ComponentLambda) {
        appendColumn(FormSpecs.ButtonColSpec)
        componentLambdas.add(Pair(button, currentCellConstraints))
        nextColumn()
    }

    public fun button(text: String, icon: Painter? = null, action: (() -> Unit)? = null, isEnabled: Boolean = true) {
        button(createButton(text, icon, action, isEnabled))
    }

    public override fun relatedGap() {
        appendRelatedComponentsGapColumn()
        nextColumn()
    }

    public override fun unrelatedGap() {
        appendUnrelatedComponentsGapColumn()
        nextColumn()
    }

    public fun glue() {
        appendGlueColumn()
        nextColumn()
    }

    public fun strut(width: ConstantSize) {
        appendColumn(ColumnSpec.createGap(width))
        nextColumn()
    }

    public fun fixed(component: ComponentLambda) {
        appendColumn(FormSpecs.PrefColSpec)
        this.componentLambdas.add(Pair(component, currentCellConstraints))
        nextColumn()
    }

    public fun growing(component: ComponentLambda) {
        appendColumn(FormSpecs.GrowingButtonColSpec)
        this.componentLambdas.add(Pair(component, currentCellConstraints))
        nextColumn()
    }

    @Composable
    public fun build(modifier: Modifier) {
        FormLayout(
            modifier = modifier,
            colSpecs = colSpecs,
            rowSpecs = arrayListOf(RowSpec.decode("center:pref")),
            content = {
                for ((componentLambda, componentBuilderModifier) in componentLambdas) {
                    componentLambda.invoke(this, Modifier.xy(col = componentBuilderModifier.gridX, row = 1))
                }
            }
        )
    }
}

@Composable
public fun ButtonBar(modifier: Modifier, padding: PaddingValues, block: @Composable ButtonBarBuilder.() -> Unit) {
    require (FormsSetup.ComponentFactoryDefault != null) {
        "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
    }

    val textMeasurer = rememberTextMeasurer()
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
    Sizes.textStyle = resolvedTextStyle
    Sizes.textMeasurer = textMeasurer
    Sizes.density = LocalDensity.current

    val builder = ButtonBarBuilder(FormsSetup.ComponentFactoryDefault!!)
    builder.block()
    builder.build(modifier.padding(padding))
}
