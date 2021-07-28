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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.launch
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
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.skin.twilightSkin
import org.pushingpixels.aurora.window.AuroraWindow

fun LazyListState.isItemFullyVisible(index: Int): Boolean {
    val layoutInfo = this.layoutInfo
    val visibleItemsInfo = layoutInfo.visibleItemsInfo

    // Is it in visible items?
    val visibleItemInfo = visibleItemsInfo.find { it.index == index } ?: return false

    val visibleStart = visibleItemInfo.offset
    val visibleSize = visibleItemInfo.size
    val viewportStart = layoutInfo.viewportStartOffset
    val viewportEnd = layoutInfo.viewportEndOffset

    // Is the top / start of the item visible in the viewport?
    if (visibleStart < viewportStart) {
        return false
    }
    // Is the bottom / end of the item visible in the viewport?
    if ((visibleStart + visibleSize) > viewportEnd) {
        return false
    }

    return true
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = WindowSize(250.dp, 400.dp)
    )
    val skin = mutableStateOf(twilightSkin())

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val stateSelection = mutableStateOf(-1)
    val itemCount = 30

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

    AuroraWindow(
        skin = skin,
        title = "Aurora Scrollbars",
        state = state,
        undecorated = true,
        onCloseRequest = ::exitApplication,
        onKeyEvent = {
            var handled = false
            // Register a keyboard event handler to process Up and Down arrows for list traversal
            if (it.type == KeyEventType.KeyDown) {
                when (it.key) {
                    Key.DirectionDown -> {
                        if (stateSelection.value < 0) {
                            stateSelection.value = 0
                        } else {
                            stateSelection.value =
                                (stateSelection.value + 1).coerceAtMost(itemCount - 1)
                        }
                        scope.launch {
                            if (!lazyListState.isItemFullyVisible(stateSelection.value)) {
                                lazyListState.animateScrollToItem(stateSelection.value)
                            }
                        }
                        handled = true
                    }
                    Key.DirectionUp -> {
                        if (stateSelection.value < 0) {
                            stateSelection.value = itemCount - 1
                        } else {
                            stateSelection.value = (stateSelection.value - 1).coerceAtLeast(0)
                        }
                        scope.launch {
                            if (!lazyListState.isItemFullyVisible(stateSelection.value)) {
                                lazyListState.animateScrollToItem(stateSelection.value)
                            }
                        }
                        handled = true
                    }
                }
            }
            handled
        }
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
                        skin.value = it.second.invoke()
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
                        top = 10.dp,
                        bottom = 8.dp
                    ),
                    textStyle = TextStyle(fontWeight = FontWeight.Bold)
                )
            ).project()

            HorizontalSeparatorProjection().project(modifier = Modifier.fillMaxWidth())

            Box(modifier = Modifier.fillMaxSize().padding(6.dp)) {
                val itemsList = (0 until itemCount).toList()
                val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
                    decorationAreaType = AuroraSkin.decorationAreaType
                )
                val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
                val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness),
                    state = lazyListState
                ) {
                    itemsIndexed(itemsList) { index, item ->
                        AuroraBoxWithHighlights(
                            modifier = Modifier.fillMaxWidth().height(32.dp)
                                .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
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
                        scrollState = lazyListState
                    )
                )
            }
        }
    }
}
