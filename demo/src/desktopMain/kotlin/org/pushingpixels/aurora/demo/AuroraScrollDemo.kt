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
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*

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

@ExperimentalComposeUiApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(350.dp, 400.dp)
    )
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var skin by remember { mutableStateOf(geminiSkin()) }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val stateSelection = mutableStateOf(-1)
    val itemCount = 30

    val icons = arrayOf(
        accessibility_new_24px(),
        account_box_24px(),
        backup_24px(),
        brightness_medium_24px(),
        help_24px(),
        info_24px(),
        keyboard_capslock_24px(),
        location_on_24px(),
        perm_device_information_24px(),
        storage_24px(),
        visibility_24px(),
        waves_24px()
    )

    AuroraWindow(
        skin = skin,
        title = "Aurora Scrollbars",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
        onKeyEvent = {
            // Keyboard event handler to process Up and Down arrows for list traversal
            var handled = false
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
            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
                AuroraDecorationArea(
                    decorationAreaType = DecorationAreaType.None,
                    buttonShaper = ClassicButtonShaper.Instance
                ) {
                    AuroraSkinSwitcher({ skin = it })

                    Spacer(modifier = Modifier.width(8.dp))

                    AuroraLocaleSwitcher(resourceBundle)
                }
            }

            val selectedMf = MessageFormat(resourceBundle.getString("Selected.entry"))
            LabelProjection(
                contentModel = LabelContentModel(
                    text = if (stateSelection.value < 0) resourceBundle.getString("Selected.none")
                    else selectedMf.format(arrayOf<Any>(stateSelection.value))
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

            val commandMf = MessageFormat(resourceBundle.getString("Group.entrySimple"))
            Box(modifier = Modifier.fillMaxSize().padding(6.dp)) {
                val itemsList = (0 until itemCount).toList()
                val backgroundColorTokens = AuroraSkin.colors.getNeutralContainerTokens(
                    decorationAreaType = AuroraSkin.decorationAreaType)
                val backgroundEvenRows = backgroundColorTokens.containerSurface
                val backgroundOddRows = if (backgroundColorTokens.isDark) {
                    backgroundColorTokens.containerSurfaceHigh
                } else {
                    backgroundColorTokens.containerSurfaceLow
                }
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
                            sides = Sides.ClosedRectangle,
                            content = {
                                LabelProjection(
                                    contentModel = LabelContentModel(
                                        text = commandMf.format(arrayOf<Any>(index)),
                                        icon = icons[item % icons.size]
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
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(scrollState = lazyListState)
                )
            }
        }
    }
}
