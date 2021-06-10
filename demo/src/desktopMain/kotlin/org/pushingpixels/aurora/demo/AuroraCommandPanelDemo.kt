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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.application
import org.pushingpixels.aurora.AuroraSkin
import org.pushingpixels.aurora.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.IconFilterStrategy
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonPanelProjection
import org.pushingpixels.aurora.demo.svg.material.*
import org.pushingpixels.aurora.skin.businessSkin
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.window.AuroraWindow

fun getCommandPanelContentModel(vararg groupSizes: Int): CommandPanelContentModel {
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

    val commandGroups = arrayListOf<CommandGroup>()

    var groupIndex = 1
    for (groupSize in groupSizes) {
        val commandList = arrayListOf<Command>()
        for (index in 1 until groupSize) {
            commandList.add(
                Command(
                    text = "test $index/$groupSize",
                    action = { println("test $index/$groupSize activated!") },
                    iconFactory = icons[index % icons.size]
                )
            )
        }
        val group = CommandGroup(title = "Group $groupIndex", commands = commandList)
        commandGroups.add(group)
        groupIndex++
    }

    return CommandPanelContentModel(commandGroups = commandGroups,
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

                val commandPanelContentModel = remember { getCommandPanelContentModel(20, 10, 15) }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(fraction = 0.5f)
                    ) {
                        CommandButtonPanelProjection(
                            contentModel = commandPanelContentModel,
                            presentationModel = CommandPanelPresentationModel(
                                layoutFillMode = PanelLayoutFillMode.RowFill,
                                maxColumns = 5,
                                showGroupLabels = true,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                commandPresentationState = CommandButtonPresentationState.Medium,
                                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                            )
                        ).project()
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CommandButtonPanelProjection(
                            contentModel = commandPanelContentModel,
                            presentationModel = CommandPanelPresentationModel(
                                layoutFillMode = PanelLayoutFillMode.ColumnFill,
                                maxRows = 6,
                                showGroupLabels = false,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
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