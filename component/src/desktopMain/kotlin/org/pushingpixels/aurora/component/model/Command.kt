/*
 * Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.component.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.PopupPlacementStrategy
import org.pushingpixels.aurora.component.AuroraButton
import org.pushingpixels.aurora.component.AuroraText
import org.pushingpixels.aurora.component.auroraButtonIconPadding
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.icon.AuroraThemedIcon

interface CommandActionPreview {
    /**
     * Invoked when a command preview has been activated.
     *
     * @param command Command for which the preview has been activated.
     */
    // TODO - remove nullability when buttons are only created from commands
    fun onCommandPreviewActivated(command: Command?)

    /**
     * Invoked when a command preview has been canceled.
     *
     * @param command Command for which the preview has been canceled.
     */
    // TODO - remove nullability when buttons are only created from commands
    fun onCommandPreviewCanceled(command: Command?)
}

data class Command(
    val text: String,
    val iconFactory: AuroraIcon.Factory?,
    var enabled: State<Boolean>?,
    val action: () -> Unit = {},
    val actionPreview: CommandActionPreview? = null,
    val secondaryContentModel: CommandMenuContentModel? = null
)

data class CommandGroup(
    val title: String? = null,
    val command: List<Command>
)

data class CommandMenuContentModel(
    val commands: List<CommandGroup>
) {
    constructor(group: CommandGroup) : this(listOf(group))
}

enum class TextClick {
    ACTION, POPUP
}

data class CommandPresentationModel(
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.DOWNWARD,
    val textClick: TextClick = TextClick.ACTION
)

@Composable
fun AuroraCommandButton(
    command: Command,
    presentationModel: CommandPresentationModel
) {
    AuroraButton(
        enabled = command.enabled?.value ?: true,
        onClick = command.action,
        rolloverTracker = command.actionPreview,
        content = {
            // TODO - content layout will depend on the presentation state
            if (command.iconFactory != null) {
                val icon = command.iconFactory.createNewIcon()
                icon.setSize(10.dp, 10.dp)
                AuroraThemedIcon(
                    icon = icon,
                    modifier = Modifier.auroraButtonIconPadding()
                )
            }
            AuroraText(command.text)
        }
    )
}