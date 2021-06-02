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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.Sides
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.skin.graphiteAquaSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    val icons = arrayOf(
        accessibility_new_24px.factory(),
        account_box_24px.factory(),
        backup_24px.factory(),
        brightness_medium_24px.factory(),
        help_24px.factory(),
        info_24px.factory(),
        keyboard_capslock_24px.factory(),
        location_on_24px.factory(),
        perm_device_information_24px.factory(),
        storage_24px.factory(),
        visibility_24px.factory(),
        waves_24px.factory()
    )
    val currentAuroraSkin = mutableStateOf(graphiteAquaSkin())
    val stateSelection = mutableStateOf(-1)
    val itemCount = 30

    AuroraWindow(
        title = "Aurora Scrollbars",
        skin = currentAuroraSkin,
        size = IntSize(250, 400),
        undecorated = true,
        keyboardShortcuts = mapOf(
            Key.DirectionDown to {
                if (stateSelection.value < 0) {
                    stateSelection.value = 0
                } else if (stateSelection.value < (itemCount - 1)) {
                    stateSelection.value++
                }
            },
            Key.DirectionUp to {
                if (stateSelection.value < 0) {
                    stateSelection.value = itemCount - 1
                } else if (stateSelection.value > 0) {
                    stateSelection.value--
                }
            })
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val currentSkinDisplayName = AuroraSkin.displayName
            val auroraSkins = getAuroraSkins()
            val selectedSkinItem =
                remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

            ComboBoxProjection(
                contentModel = ComboBoxContentModel(
                    items = auroraSkins,
                    selectedItem = selectedSkinItem.value,
                    onTriggerItemSelectedChange = {
                        selectedSkinItem.value = it
                        currentAuroraSkin.value = it.second.invoke()
                    }
                ),
                presentationModel = ComboBoxPresentationModel(
                    displayConverter = { it.first }
                )
            ).project()

            LabelProjection(
                contentModel = LabelContentModel(
                    text = if (stateSelection.value < 0) "No selection"
                    else "Selected item #${stateSelection.value}"
                ),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 12.dp,
                        bottom = 2.dp
                    ),
                    textStyle = TextStyle(fontWeight = FontWeight.Bold)
                )
            ).project()

            Box(modifier = Modifier.fillMaxSize().padding(6.dp)) {
                val itemsList = (0..itemCount).toList()
                val state = rememberLazyListState()
                LazyColumn(
                    Modifier.fillMaxSize()
                        .padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness),
                    state
                ) {
                    items(itemsList) { item ->
                        AuroraBoxWithHighlights(
                            modifier = Modifier.fillMaxWidth().height(32.dp),
                            selected = (stateSelection.value == item),
                            onClick = { stateSelection.value = item },
                            sides = Sides(straightSides = Side.values().toSet()),
                            content = {
                                LabelProjection(
                                    contentModel = LabelContentModel(
                                        text = "Item #$item",
                                        iconFactory = icons[item % icons.size]
                                    ),
                                    presentationModel = LabelPresentationModel(
                                        inheritStateFromParent = true,
                                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                        horizontalGapScaleFactor = 2.0f
                                    )
                                ).project()
                            }
                        )
                    }
                }
                AuroraVerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = state
                    )
                )
            }
        }
    }
}