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
package org.pushingpixels.aurora.component.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.ButtonSides
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.component.AuroraComboBox
import org.pushingpixels.aurora.component.AuroraCommandButton
import org.pushingpixels.aurora.component.AuroraCommandButtonStrip
import org.pushingpixels.aurora.component.projection.BaseProjection
import org.pushingpixels.aurora.component.projection.CommandBasedProjection

class CommandButtonProjection(
    command: Command,
    commandButtonPresentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : CommandBasedProjection<Command, CommandButtonPresentationModel>(
    contentModel = command,
    presentationModel = commandButtonPresentationModel,
    overlays = overlays
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraCommandButton(
            modifier = Modifier,
            command = this.contentModel,
            parentWindow = null,
            extraAction = null,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf(),
            textStyle = null,
            buttonSides = ButtonSides(
                straightSides = if (presentationModel.isMenu) Side.values().toSet() else emptySet()
            )
        )
    }
}

class CommandButtonStripProjection(
    commandGroup: CommandGroup,
    commandStripPresentationModel: CommandStripPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : CommandBasedProjection<CommandGroup, CommandStripPresentationModel>(
    contentModel = commandGroup,
    presentationModel = commandStripPresentationModel,
    overlays = overlays
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraCommandButtonStrip(
            modifier = Modifier,
            commandGroup = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class ComboBoxProjection<E>(
    contentModel: ComboBoxContentModel<E>,
    presentationModel: ComboBoxPresentationModel<E>
) : BaseProjection<ComboBoxContentModel<E>, ComboBoxPresentationModel<E>>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraComboBox(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}
