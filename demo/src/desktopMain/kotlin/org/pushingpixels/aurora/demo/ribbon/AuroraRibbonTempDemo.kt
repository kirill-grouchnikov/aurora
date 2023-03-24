/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.ribbon

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.MenuPopupPanelLayoutSpec
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.demo.AuroraSkinSwitcher
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(800.dp, 480.dp)
    )
    var skin by remember { mutableStateOf(nebulaSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    var ribbonState by remember {
        mutableStateOf(
            RibbonState(documentStyle = DocumentStyle.Style2)
        )
    }
    val builder = RibbonBuilder(
        resourceBundle = resourceBundle,
        density = LocalDensity.current.density,
        ribbonState = ribbonState,
        onRibbonStateUpdate = { newState ->
            println("New state is ${newState.documentStyle.name}")
            ribbonState = newState
        })

    AuroraWindow(
        skin = skin,
        title = "Aurora Ribbon Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        Column {
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.Header) {
                Row(modifier = Modifier.fillMaxWidth().auroraBackground().padding(12.dp)) {
                    builder.getApplicationMenuCommandButtonProjection().project()
                }
            }

            val styleGalleryContentModel = builder.styleGalleryContentModel
            val styleGalleryPresentationModel = RibbonGalleryPresentationModel(
                preferredVisibleCommandCounts = mapOf(
                    PresentationPriority.Low to 1,
                    PresentationPriority.Medium to 2,
                    PresentationPriority.Top to 2
                ),
                popupLayoutSpec = MenuPopupPanelLayoutSpec(
                    columnCount = 3, visibleRowCount = 3
                ),
                commandButtonPresentationState = RibbonBandCommandButtonPresentationStates.BigFixedLandscape,
                commandButtonTextOverflow = TextOverflow.Ellipsis,
                expandKeyTip = "L"
            )
            val styleGalleryInlineState = remember {
                RibbonGalleryInlineState(
                    contentModel = styleGalleryContentModel,
                    presentationModel = styleGalleryPresentationModel,
                    presentationPriority = PresentationPriority.Top
                )
            }
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                RibbonGalleryProjection(
                    contentModel = styleGalleryContentModel,
                    presentationModel = styleGalleryPresentationModel
                ).project(
                    presentationPriority = PresentationPriority.Top,
                    inlineState = styleGalleryInlineState
                )
            }

            Spacer(Modifier.weight(weight = 1.0f, fill = true))
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                AuroraSkinSwitcher({ skin = it }, PopupPlacementStrategy.Upward.HAlignStart)
            }
        }
    }

}



