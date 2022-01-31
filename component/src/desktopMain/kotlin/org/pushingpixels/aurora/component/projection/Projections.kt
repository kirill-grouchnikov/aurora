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
package org.pushingpixels.aurora.component.projection

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.theming.LocalWindow

class CommandButtonProjection(
    val contentModel: Command,
    val presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @OptIn(AuroraInternalApi::class)
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        val window = LocalWindow.current
        AuroraCommandButton(
            modifier = modifier,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            command = this.contentModel,
            parentWindow = window,
            extraAction = null,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class CommandButtonStripProjection(
    val contentModel: CommandGroup,
    val presentationModel: CommandStripPresentationModel,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraCommandButtonStrip(
            modifier = modifier,
            commandGroup = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class CommandButtonPanelProjection(
    val contentModel: CommandPanelContentModel,
    val presentationModel: CommandPanelPresentationModel,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        require(
            !presentationModel.showGroupLabels ||
                    (presentationModel.layoutSpec is PanelLayoutSpec.RowFill)
        ) {
            "Column fill layout is not supported when group labels are shown"
        }
        AuroraCommandButtonPanel(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class ComboBoxProjection<E>(
    val contentModel: ComboBoxContentModel<E>,
    val presentationModel: ComboBoxPresentationModel<E>
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraComboBox(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class CheckBoxProjection(
    val contentModel: SelectorContentModel,
    val presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraCheckBox(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class RadioButtonProjection(
    val contentModel: SelectorContentModel,
    val presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraRadioButton(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class CircularProgressProjection(
    val contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    val presentationModel: ProgressCircularPresentationModel = ProgressCircularPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraCircularProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class IndeterminateLinearProgressProjection(
    val contentModel: ProgressIndeterminateContentModel = ProgressIndeterminateContentModel(),
    val presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraIndeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class DeterminateLinearProgressProjection(
    val contentModel: ProgressDeterminateContentModel,
    val presentationModel: ProgressLinearPresentationModel = ProgressLinearPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraDeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class IconProjection(
    val contentModel: IconContentModel,
    val presentationModel: IconPresentationModel = IconPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraIcon(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class LabelProjection(
    val contentModel: LabelContentModel,
    val presentationModel: LabelPresentationModel = LabelPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraLabel(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class VerticalSeparatorProjection(
    val contentModel: SeparatorContentModel = SeparatorContentModel(),
    val presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraVerticalSeparator(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class HorizontalSeparatorProjection(
    val contentModel: SeparatorContentModel = SeparatorContentModel(),
    val presentationModel: SeparatorPresentationModel = SeparatorPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraHorizontalSeparator(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class SliderProjection(
    val contentModel: SliderContentModel,
    val presentationModel: SliderPresentationModel = SliderPresentationModel()
) {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraSlider(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldValueProjection(
    val contentModel: TextFieldValueContentModel,
    val presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraTextField(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TextFieldStringProjection(
    val contentModel: TextFieldStringContentModel,
    val presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraTextField(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}
