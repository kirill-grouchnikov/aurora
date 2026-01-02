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

import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.demo.svg.material.*
import java.text.MessageFormat
import java.util.*

fun getQuickStylesContentModel(resourceBundle: ResourceBundle): CommandPanelContentModel {
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

    val groupMf = MessageFormat(resourceBundle.getString("Group.title"))
    val commandMf = MessageFormat(resourceBundle.getString("Group.entry"))

    val commandGroups: MutableList<CommandGroup> = arrayListOf()
    for (groupIndex in 1..4) {
        val commands: MutableList<Command> = arrayListOf()
        for (i in 1..15) {
            val command = Command(
                text = commandMf.format(arrayOf<Any>(i, 15)),
                icon = icons[i % icons.size],
                action = { println("Invoked action on $i") },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Action preview activated for ${command.text}!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Action preview canceled for ${command.text}!")
                    }
                }
            )
            commands.add(command)
        }
        commandGroups.add(CommandGroup(groupMf.format(arrayOf<Any>(groupIndex)), commands))
    }
    return CommandPanelContentModel(commandGroups = commandGroups)
}
