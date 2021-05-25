/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.AuroraHorizontalScrollbar
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        title = "Aurora Scrollbars",
        skin = businessSkin(),
        size = IntSize(250, 400),
        undecorated = true
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp)
        ) {
            val stateVertical = rememberScrollState(0)
            val stateHorizontal = rememberScrollState(0)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 12.dp, bottom = 12.dp)
                    .verticalScroll(stateVertical)
                    .horizontalScroll(stateHorizontal)
            ) {
                Column {
                    for (item in 0..30) {
                        TextBox("Item #$item")
                        if (item < 30) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
            AuroraVerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd)
                    .fillMaxHeight(),
                adapter = remember(stateVertical) {
                    ScrollbarAdapter(stateVertical)
                }
            )
            AuroraHorizontalScrollbar(
                modifier = Modifier.align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                adapter = remember(stateHorizontal) {
                    ScrollbarAdapter(stateHorizontal)
                }
            )
        }
    }
}

@Composable
fun TextBox(text: String = "Item") {
    Box(
        modifier = Modifier.height(32.dp)
            .width(400.dp)
            .background(color = Color(100, 100, 100, 20))
            .padding(start = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        LabelProjection(contentModel = LabelContentModel(text = text)).project()
    }
}