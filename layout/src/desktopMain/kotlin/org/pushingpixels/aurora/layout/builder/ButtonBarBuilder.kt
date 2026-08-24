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
 * ButtonBar.builder()
 *     .addButton { builderModifier -> newButton }
 *     .addRelatedGap()
 *     .addButton { builderModifier -> editButton }
 *     .addRelatedGap()
 *     .addButton { builderModifier -> deleteButton }
 *     .build()
 *
 * // 2) Short hand for example 1)
 * ButtonBar.builder()
 *     .addButtons({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 *     .build()
 *
 * // 3) Build and return a bar with two sections
 * ButtonBar.builder()
 *     .addButton({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 *     .addUnrelatedGap()
 *     .addButton({ builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 *     .build();
 *
 * // 4) Short hand for example 3)
 * ButtonBar.builder()
 *     .addButtons({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton },
 *                null,
 *                { builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 *     .build();
 *
 * // 5) Build and return a complex button bar
 * ButtonBar.builder()
 *     .addButton({ builderModifier -> newButton }, { builderModifier -> editButton }, { builderModifier -> deleteButton })
 *     .addUnrelatedGap()
 *     .addButton({ builderModifier -> moveUpButton }, { builderModifier -> moveDownButton })
 *     .addGlue()
 *     .addGrowing({ builderModifier -> legendComponent })
 *     .build()
 * ```
 *
 * @see [ButtonStack.Builder]
 * @see [org.pushingpixels.aurora.layout.util.LayoutStyle]
 */
public object ButtonBar {
    public fun builder(): Builder {
        require (FormsSetup.ComponentFactoryDefault != null) {
            "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
        }
        return Builder(FormsSetup.ComponentFactoryDefault!!)
    }

    public fun builder(componentFactory: ComponentFactory): Builder = Builder(componentFactory)

    public class Builder(componentFactory: ComponentFactory):
        AbstractButtonPanelBuilder<Builder>(componentFactory) {

        private val componentLambdas: MutableList<Pair<ComponentLambda, CellConstraints>> = arrayListOf()

        public override fun addButton(button: ComponentLambda): Builder {
            appendColumn(FormSpecs.ButtonColSpec)
            componentLambdas.add(Pair(button, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addButton(text: String, icon: Painter? = null, action: (() -> Unit)? = null, isEnabled: Boolean = true) : Builder {
            addButton(createButton(text, icon, action, isEnabled))
            return this
        }

        public override fun addRelatedGap(): Builder {
            appendRelatedComponentsGapColumn()
            nextColumn()
            return this
        }

        public override fun addUnrelatedGap(): Builder {
            appendUnrelatedComponentsGapColumn()
            nextColumn()
            return this
        }

        public fun addGlue(): Builder {
            appendGlueColumn()
            nextColumn()
            return this
        }

        public fun addStrut(width: ConstantSize): Builder {
            appendColumn(ColumnSpec.createGap(width))
            nextColumn()
            return this
        }

        public fun addFixed(component: ComponentLambda): Builder {
            appendColumn(FormSpecs.PrefColSpec)
            this.componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addGrowing(component: ComponentLambda): Builder {
            appendColumn(FormSpecs.GrowingButtonColSpec)
            this.componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        @Composable
        public fun build(modifier: Modifier) {
            FormLayout(
                modifier = modifier.padding(padding),
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
}