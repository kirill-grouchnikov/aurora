package org.pushingpixels.aurora.layout.builder

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.factories.ComponentFactory

public object ButtonBar {
    public fun builder(): Builder {
        require (FormsSetup.ComponentFactoryDefault != null) {
            "Configure `FormsSetup.ComponentFactoryDefault` with a non-null component factory before creating this builder"
        }
        return Builder(FormsSetup.ComponentFactoryDefault!!)
    }

    public fun builder(componentFactory: ComponentFactory): Builder = Builder(componentFactory)

    public class Builder(componentFactory: ComponentFactory): AbstractBuilder(componentFactory) {
        private val componentLambdas: MutableList<Pair<ComponentLambda, CellConstraints>> = arrayListOf()

        private fun nextColumn(columns: Int = 1) {
            val currGridX = this.currentCellConstraints.gridX
            this.currentCellConstraints = CellConstraints(gridX = currGridX + columns)
        }

        public fun addButton(button: ComponentLambda): Builder {
            this.colSpecs.add(FormSpecs.ButtonColSpec)
            componentLambdas.add(Pair(button, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addRelatedGap(): Builder {
            this.colSpecs.add(FormSpecs.RelatedGapColSpec)
            nextColumn()
            return this
        }

        public fun addUnrelatedGap(): Builder {
            this.colSpecs.add(FormSpecs.UnrelatedGapColSpec)
            nextColumn()
            return this
        }

        public fun addGlue(): Builder {
            this.colSpecs.add(FormSpecs.GlueColSpec)
            nextColumn()
            return this
        }

        public fun addStrut(width: ConstantSize): Builder {
            this.colSpecs.add(ColumnSpec.createGap(width))
            nextColumn()
            return this
        }

        public fun addFixed(component: ComponentLambda): Builder {
            this.colSpecs.add(FormSpecs.PrefColSpec)
            this.componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addGrowing(component: ComponentLambda): Builder {
            this.colSpecs.add(FormSpecs.GrowingButtonColSpec)
            this.componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun padding(padding: PaddingValues): Builder {
            this.padding = padding
            return this
        }

        @Composable
        public fun build(modifier: Modifier) {
            FormLayout(
                modifier = modifier.padding(padding),
                colSpecs = colSpecs,
                rowSpecs = arrayListOf(RowSpec.decode("center:pref")),
                content = {
                    for ((componentLambda, buttonExtraModifier) in componentLambdas) {
                        componentLambda.invoke(this, Modifier.xy(col = buttonExtraModifier.gridX, row = 1))
                    }
                }
            )
        }
    }
}