package org.pushingpixels.aurora.layout.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.layout.*

private typealias ButtonLambdaInfo = @Composable FormLayoutScope.(modifier: Modifier) -> Unit

public object ButtonBar {
    public fun builder(): Builder = Builder()

    public class Builder {
        private val colSpecs: MutableList<ColumnSpec> = arrayListOf(
            ColumnSpec(ColumnSpec.Default, Sizes.Zero, FormSpec.DefaultGrow))
        private val buttonLambdas: MutableList<Pair<ButtonLambdaInfo, CellConstraints>> = arrayListOf()

        private var currentCellConstraints: CellConstraints = CellConstraints(gridX = 2, gridY = 1)

        private fun nextColumn(columns: Int = 1) {
            val currGridX = currentCellConstraints.gridX
            currentCellConstraints = CellConstraints(gridX = currGridX + columns)
        }

        public fun addButton(button: @Composable FormLayoutScope.(modifier: Modifier) -> Unit): Builder {
            colSpecs.add(FormSpecs.ButtonColSpec)
            buttonLambdas.add(Pair(button, currentCellConstraints))
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

        @Composable
        public fun build(modifier: Modifier) {
            FormLayout(
                modifier = modifier,
                colSpecs = colSpecs,
                rowSpecs = arrayListOf(RowSpec.decode("center:pref")),
                content = {
                    for ((buttonLambda, buttonExtraModifier) in buttonLambdas) {
                        buttonLambda.invoke(this, Modifier.xy(col = buttonExtraModifier.gridX, row = 1))
                    }
                }
            )
        }
    }
}