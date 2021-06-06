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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.application
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.window.AuroraWindow

fun getCommandPanelContentModel(): CommandPanelContentModel {
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

    val size1 = 20
    val size2 = 10

    val commandList1 = arrayListOf<Command>()
    for (index in 1 until size1) {
        commandList1.add(
            Command(
                text = "test $index/$size1",
                action = { println("test $index/$size1 activated!") },
                iconFactory = icons[index % icons.size]
            )
        )
    }
    val group1 = CommandGroup(title = "Group 1", commands = commandList1)

    val commandList2 = arrayListOf<Command>()
    for (index in 1 until size2) {
        commandList2.add(
            Command(
                text = "test $index/$size2",
                action = { println("test $index/$size2 activated!") },
                iconFactory = icons[index % icons.size]
            )
        )
    }
    val group2 = CommandGroup(title = "Group 2", commands = commandList2)

    return CommandPanelContentModel(commandGroups = listOf(group1, group2),
        commandActionPreview = object : CommandActionPreview {
            override fun onCommandPreviewActivated(command: Command) {
                println("Action preview activated for ${command.text}!")
            }

            override fun onCommandPreviewCanceled(command: Command) {
                println("Action preview canceled for ${command.text}!")
            }
        }
    )
}

@ExperimentalComposeUiApi
fun main() {
    application {
        val currentAuroraSkin = mutableStateOf(businessSkin())

        AuroraWindow(
            title = "Aurora Command Panel",
            skin = currentAuroraSkin,
            size = IntSize(1000, 400),
            undecorated = true
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

                val commandPanelContentModel = remember { getCommandPanelContentModel() }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier.background(color = Color.Red.withAlpha(0.5f))
                            .fillMaxWidth(fraction = 0.5f)
                    ) {
                        CommandButtonPanelProjection(
                            contentModel = commandPanelContentModel,
                            presentationModel = CommandPanelPresentationModel(
                                layoutFillMode = PanelLayoutFillMode.RowFill,
                                maxColumns = 5,
                                showGroupLabels = true,
                                commandPresentationState = CommandButtonPresentationState.Medium,
                                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                            )
                        ).project()
                    }

                    Box(
                        modifier = Modifier.background(color = Color.Blue.withAlpha(0.5f))
                            .fillMaxWidth()
                    ) {
                        CommandButtonPanelProjection(
                            contentModel = commandPanelContentModel,
                            presentationModel = CommandPanelPresentationModel(
                                layoutFillMode = PanelLayoutFillMode.ColumnFill,
                                maxRows = 6,
                                showGroupLabels = false,
                                commandPresentationState = CommandButtonPresentationState.Big,
                                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                            )
                        ).project()
                    }
                }
            }
        }
    }
}