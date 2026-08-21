package org.pushingpixels.aurora.layout.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.layout.*

public typealias ButtonBarComponentLambda = @Composable FormLayoutScope.(modifier: Modifier) -> Unit

public object ButtonBar {
    public fun builder(): Builder = Builder()

    public class Builder {
        private val colSpecs: MutableList<ColumnSpec> = arrayListOf()
        private val componentLambdas: MutableList<Pair<ButtonBarComponentLambda, CellConstraints>> = arrayListOf()

        private var currentCellConstraints: CellConstraints = CellConstraints(gridX = 1, gridY = 1)

        private fun nextColumn(columns: Int = 1) {
            val currGridX = currentCellConstraints.gridX
            currentCellConstraints = CellConstraints(gridX = currGridX + columns)
        }

        public fun addButton(button: ButtonBarComponentLambda): Builder {
            colSpecs.add(FormSpecs.ButtonColSpec)
            componentLambdas.add(Pair(button, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addRelatedGap(): Builder {
            colSpecs.add(FormSpecs.RelatedGapColSpec)
            nextColumn()
            return this
        }

        public fun addUnrelatedGap(): Builder {
            colSpecs.add(FormSpecs.UnrelatedGapColSpec)
            nextColumn()
            return this
        }

        public fun addGlue(): Builder {
            colSpecs.add(FormSpecs.GlueColSpec)
            nextColumn()
            return this
        }

        public fun addStrut(width: ConstantSize): Builder {
            colSpecs.add(ColumnSpec.createGap(width))
            nextColumn()
            return this
        }

        public fun addFixed(component: ButtonBarComponentLambda): Builder {
            colSpecs.add(FormSpecs.PrefColSpec)
            componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        public fun addGrowing(component: ButtonBarComponentLambda): Builder {
            colSpecs.add(FormSpecs.GrowingButtonColSpec)
            componentLambdas.add(Pair(component, currentCellConstraints))
            nextColumn()
            return this
        }

        @Composable
        public fun build(modifier: Modifier) {
            FormLayout(
                modifier = modifier,
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