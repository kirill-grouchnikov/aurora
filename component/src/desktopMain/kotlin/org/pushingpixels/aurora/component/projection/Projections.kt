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
package org.pushingpixels.aurora.component.projection

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.Sides
import org.pushingpixels.aurora.Side
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*

class CommandButtonProjection(
    contentModel: Command,
    presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : CommandBasedProjection<Command, CommandButtonPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel,
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
            buttonSides = Sides(
                straightSides = if (presentationModel.isMenu) Side.values().toSet() else emptySet()
            )
        )
    }
}

class CommandButtonStripProjection(
    contentModel: CommandGroup,
    presentationModel: CommandStripPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : CommandBasedProjection<CommandGroup, CommandStripPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel,
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

class CommandButtonPanelProjection(
    contentModel: CommandPanelContentModel,
    presentationModel: CommandPanelPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : CommandBasedProjection<CommandPanelContentModel, CommandPanelPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel,
    overlays = overlays
) {
    @Composable
    override fun project() {
        require(!presentationModel.showGroupLabels ||
                (presentationModel.layoutFillMode == PanelLayoutFillMode.RowFill)) {
            "Column fill layout is not supported when group labels are shown"
        }
        // TODO - pass the app-side modifier
        AuroraCommandButtonPanel(
            modifier = Modifier,
            contentModel = this.contentModel,
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

class CheckBoxProjection(
    contentModel: SelectorContentModel,
    presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) : BaseProjection<SelectorContentModel, SelectorPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraCheckBox(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class RadioButtonProjection(
    contentModel: SelectorContentModel,
    presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) : BaseProjection<SelectorContentModel, SelectorPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraRadioButton(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class CircularProgressProjection(
    contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    presentationModel: ProgressCircularPresentationModel = ProgressCircularPresentationModel()
) : BaseProjection<ProgressIndeterminateContentModel, ProgressCircularPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraCircularProgress(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class IndeterminateLinearProgressProjection(
    contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) : BaseProjection<ProgressIndeterminateContentModel, ProgressLinearPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraIndeterminateLinearProgress(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class DeterminateLinearProgressProjection(
    contentModel: ProgressDeterminateContentModel,
    presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) : BaseProjection<ProgressDeterminateContentModel, ProgressLinearPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraDeterminateLinearProgress(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class LabelProjection(
    contentModel: LabelContentModel,
    presentationModel: LabelPresentationModel = LabelPresentationModel()
) : BaseProjection<LabelContentModel, LabelPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraLabel(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class VerticalSeparatorProjection(
    contentModel: SeparatorContentModel = SeparatorContentModel(),
    presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) : BaseProjection<SeparatorContentModel, SeparatorPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraVerticalSeparator(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class HorizontalSeparatorProjection(
    contentModel: SeparatorContentModel = SeparatorContentModel(),
    presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) : BaseProjection<SeparatorContentModel, SeparatorPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraHorizontalSeparator(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class SliderProjection(
    contentModel: SliderContentModel,
    presentationModel: SliderPresentationModel = SliderPresentationModel()
) : BaseProjection<SliderContentModel, SliderPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraSlider(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldValueProjection(
    contentModel: TextFieldValueContentModel,
    presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) : BaseProjection<TextFieldValueContentModel, TextFieldPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraTextField(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldStringProjection(
    contentModel: TextFieldStringContentModel,
    presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) : BaseProjection<TextFieldStringContentModel, TextFieldPresentationModel>(
    contentModel = contentModel,
    presentationModel = presentationModel
) {
    @Composable
    override fun project() {
        // TODO - pass the app-side modifier
        AuroraTextField(
            modifier = Modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}
