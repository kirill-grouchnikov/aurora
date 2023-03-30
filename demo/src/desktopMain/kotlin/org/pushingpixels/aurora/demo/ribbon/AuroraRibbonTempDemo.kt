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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.*
import org.pushingpixels.aurora.component.ribbon.impl.RibbonTaskbar
import org.pushingpixels.aurora.demo.AuroraSkinSwitcher
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.edit_clear
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
            RibbonState(
                documentStyle = DocumentStyle.Style2,
                fontFamily = FontFamily.Calibri
            )
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
            val styleGalleryInlinePresentationModel = RibbonGalleryPresentationModel(
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
            ribbonState.documentStyleGalleryInlineState = remember {
                RibbonGalleryInlineState(
                    contentModel = styleGalleryContentModel,
                    presentationModel = styleGalleryInlinePresentationModel,
                    presentationPriority = PresentationPriority.Top
                )
            }

            val styleGalleryTaskbarPresentationModel = RibbonGalleryPresentationModel(
                popupLayoutSpec = MenuPopupPanelLayoutSpec(columnCount = 4, visibleRowCount = 2),
                commandButtonPresentationState = RibbonBandCommandButtonPresentationStates.BigFixed
            )

            SampleGallery(
                contentModel = styleGalleryContentModel,
                presentationModel = styleGalleryInlinePresentationModel,
                ribbonState = ribbonState
            )

            for (taskBarMaxWidth in 50..300 step 70) {
                Spacer(Modifier.height(8.dp))
                Taskbar(
                    maxWidth = taskBarMaxWidth.dp,
                    builder = builder,
                    resourceBundle = resourceBundle,
                    ribbonState = ribbonState,
                    onRibbonStateChange = {
                        ribbonState = it
                    },
                    galleryContentModel = styleGalleryContentModel,
                    galleryPresentationModel = styleGalleryTaskbarPresentationModel,
                    galleryInlineState = ribbonState.documentStyleGalleryInlineState
                )
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.weight(weight = 1.0f, fill = true))
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                AuroraSkinSwitcher({ skin = it }, PopupPlacementStrategy.Upward.HAlignStart)
            }
        }
    }
}

@Composable
private fun SampleGallery(
    contentModel: RibbonGalleryContentModel,
    presentationModel: RibbonGalleryPresentationModel,
    ribbonState: RibbonState
) {
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
        RibbonGalleryProjection(
            contentModel = contentModel,
            presentationModel = presentationModel
        ).project(
            presentationPriority = PresentationPriority.Top,
            inlineState = ribbonState.documentStyleGalleryInlineState
        )
    }
}

@Composable
private fun Taskbar(
    maxWidth: Dp,
    builder: RibbonBuilder,
    ribbonState: RibbonState,
    onRibbonStateChange: (RibbonState) -> Unit,
    resourceBundle: ResourceBundle,
    galleryContentModel: RibbonGalleryContentModel,
    galleryPresentationModel: RibbonGalleryPresentationModel,
    galleryInlineState: RibbonGalleryInlineState
) {
    val taskbarElements: List<RibbonTaskbarElement> =
        listOf(
            RibbonTaskbarCommandProjection(
                CommandButtonProjection(
                    contentModel = builder.pasteCommand,
                    presentationModel = CommandButtonPresentationModel()
                )
            ),
            RibbonTaskbarCommandProjection(
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = edit_clear(),
                        action = { println("Taskbar Clear activated") },
                        isActionEnabled = false
                    ),
                    presentationModel = CommandButtonPresentationModel()
                )
            ),
            RibbonTaskbarComponentProjection(
                ComboBoxProjection(
                    contentModel = ComboBoxContentModel(
                        items = FontFamily.values().toList(),
                        selectedItem = ribbonState.fontFamily,
                        onTriggerItemSelectedChange = {
                            onRibbonStateChange(ribbonState.copy(fontFamily = it))
                            println("New font family selection -> ${it.name}")
                        },
                        richTooltip = RichTooltip(title = resourceBundle.getString("Fonts.tooltip.title")),
                    ),
                    presentationModel = ComboBoxPresentationModel(displayConverter = { it.name }),
                )
            ),
            // Add the same gallery we have in the first ribbon task to the taskbar, configuring
            // its popup presentation with a 4x2 grid of slightly smaller buttons (instead of a 3x3
            // grid of slightly larger ones in the in-task gallery popup).
            // Content preview and selection is controlled by the same model and is kept in sync
            // along all usages of the gallery content model in our ribbon.
            RibbonTaskbarGalleryProjection(
                galleryProjection = RibbonGalleryProjection(
                    contentModel = galleryContentModel,
                    presentationModel = galleryPresentationModel
                ),
                galleryInlineState = galleryInlineState
            )
        )

    RibbonTaskbar(
        modifier = Modifier.height(32.dp).background(Color(0xFFFFDAB3)).padding(horizontal = 6.dp),
        maxWidth = maxWidth,
        elements = taskbarElements
    )
}



