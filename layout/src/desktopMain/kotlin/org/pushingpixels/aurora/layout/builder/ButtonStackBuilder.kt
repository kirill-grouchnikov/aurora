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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

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
 * ButtonStack.builder()
 *     .addButton { builderModifier -> closeButton }
 *     .addUnrelatedGap()
 *     .addButton { builderModifier -> upButton }
 *     .addRelatedGap()
 *     .addButton { builderModifier -> downButton }
 *     .build()
 * }
 * ```
 *
 * @see [ButtonBar.Builder]
 * @see [org.pushingpixels.aurora.layout.util.LayoutStyle]
 */
public object ButtonStack {
    public fun builder(): Builder {
        require (FormsSetup.ComponentFactoryDefault != null) {
            "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
        }
        return Builder(FormsSetup.ComponentFactoryDefault!!)
    }

    public fun builder(componentFactory: ComponentFactory): Builder = Builder(componentFactory)

    public class Builder(componentFactory: ComponentFactory): AbstractButtonPanelBuilder<Builder>(componentFactory) {
        private val componentLambdas: MutableList<Pair<ComponentLambda, CellConstraints>> = arrayListOf()

        public override fun addButton(button: ComponentLambda): Builder {
            appendRow(FormSpecs.PrefRowSpec)
            componentLambdas.add(Pair(button, currentCellConstraints))
            nextRow()
            return this
        }

        public fun addButton(text: String, icon: Painter? = null, action: (() -> Unit)? = null, isEnabled: Boolean = true) : Builder {
            addButton(createButton(text, icon, action, isEnabled))
            return this
        }

        public override fun addRelatedGap(): Builder {
            appendRelatedComponentsGapRow()
            nextRow()
            return this
        }

        public override fun addUnrelatedGap(): Builder {
            appendUnrelatedComponentsGapRow()
            nextRow()
            return this
        }

        public fun addGlue(): Builder {
            appendGlueRow()
            nextRow()
            return this
        }

        public fun addStrut(size: ConstantSize): Builder {
            appendRow(RowSpec(RowSpec.Top, size, FormSpec.NoGrow))
            nextRow()
            return this
        }

        public fun addFixed(component: ComponentLambda): Builder {
            appendRow(FormSpecs.PrefRowSpec)
            this.componentLambdas.add(Pair(component, currentCellConstraints))
            nextRow()
            return this
        }

        @Composable
        public fun build(modifier: Modifier) {
            FormLayout(
                modifier = modifier.padding(padding),
                colSpecs = arrayListOf(FormSpecs.ButtonColSpec),
                rowSpecs = rowSpecs,
                content = {
                    for ((componentLambda, componentBuilderModifier) in componentLambdas) {
                        componentLambda.invoke(this, Modifier.xy(col = 1, row = componentBuilderModifier.gridY))
                    }
                }
            )
        }
    }
}