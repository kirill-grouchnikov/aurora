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
package org.pushingpixels.aurora.demo.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.layout.*
import org.pushingpixels.aurora.layout.Sizes.dluX
import org.pushingpixels.aurora.layout.Sizes.dluY
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication

fun main() = auroraApplication {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(500.dp, 400.dp)
        ),
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        val rowSpec1 = RowSpec(RowSpec.Fill, dluY(14), 0.0)
        val rowSpec2 = RowSpec(Sizes.ComponentSize.Preferred)
        val colSpec1 = ColumnSpec(dluX(30))
        val colSpec2 = ColumnSpec(Sizes.ComponentSize.Preferred)

        FormLayout(
            modifier = Modifier.fillMaxSize(),
            colSpecs = listOf(colSpec1, colSpec2),
            rowSpecs = listOf(rowSpec1, rowSpec2)
        ) {
            val rowSpec1 = RowSpec(dluY(14))
            val rowSpec2 = RowSpec(RowSpec.Center, dluY(14), 0.0)
            val rowSpec3 = RowSpec(RowSpec.Center, dluY(14), FormSpec.NoGrow)
            val rowSpec4 = RowSpec.decode("pref")
            val rowSpec5 = RowSpec.decode("top:31dlu")
            val rowSpec6 = RowSpec.decode("center:max(20dlu;pref):grow")

            println(rowSpec1.toString())
            println(rowSpec2.toString())
            println(rowSpec3.toString())
            println(rowSpec4.toString())
            println(rowSpec5.toString())
            println(rowSpec6.toString())

            // This box gets its width from the first column (fixed at 30dlu) and its height from the first row
            // (fixed at 14dlu)
            Box(modifier = Modifier.background(Color.Green).xywh(col = 1, row = 1))
            // This box gets its height from the first row (fixed at 14dlu)
            Box(modifier = Modifier.width(20.dp).background(Color.Blue).xywh(col = 2, row = 1))
            // This box gets its width from spanning the two columns. The first column is fixed at 30dlu, and the
            // second column has its width defined by the second box's fixed width from its modifier
            Box(modifier = Modifier.height(30.dp).background(Color.Yellow).xywh(col = 1, row = 2, colSpan = 2))
        }
    }
}
