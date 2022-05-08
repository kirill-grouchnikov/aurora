/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.TabContentModel
import org.pushingpixels.aurora.component.model.TabsContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TabsProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

@ExperimentalUnitApi
fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(400.dp, 280.dp)
    )
    val skin = mutableStateOf(marinerSkin())
    val resourceBundle = derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora tabs",
        state = state,
        undecorated = true,
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        DemoTabsContent(skin, resourceBundle)
    }
}


@ExperimentalUnitApi
@Composable
fun AuroraWindowScope.DemoTabsContent(
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>,
    resourceBundle: State<ResourceBundle>
) {
    var state by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)) {
            AuroraSkinSwitcher(auroraSkinDefinition)
        }
        TabsProjection(contentModel = TabsContentModel(
            tabs = listOf(
                TabContentModel(text = "Tab 1"),
                TabContentModel(text = "Tab 2"),
                TabContentModel(text = "Tab 3", isEnabled = false),
                TabContentModel(text = "Tab 4"),
                TabContentModel(text = "Tab 5"),
                TabContentModel(text = "Tab 6"),
                TabContentModel(text = "Tab 7")
            ),
            selectedTabIndex = state,
            onTriggerTabSelected = { state = it }
        )).project(modifier = Modifier.fillMaxWidth())

        LabelProjection(contentModel = LabelContentModel(text = "Text tab ${state + 1} selected")).project(
            modifier = Modifier.padding(all = 12.dp).align(Alignment.CenterHorizontally),
        )
    }
}



