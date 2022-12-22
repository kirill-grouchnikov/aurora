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

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.ribbon.RibbonApplicationMenuContentModel
import org.pushingpixels.aurora.theming.LocalPopupMenu

abstract class Projection<out C : ContentModel, out P : PresentationModel>

sealed class BaseCommandButtonProjection<out P : BaseCommandMenuContentModel, out M : BaseCommand<P>>(
    open val contentModel: M,
    open val presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    open val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : Projection<M, CommandButtonPresentationModel>()

class CommandButtonProjection(
    contentModel: Command,
    presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<CommandMenuContentModel, Command>(
    contentModel, presentationModel, overlays
) {
    @OptIn(AuroraInternalApi::class)
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        val popupMenu = LocalPopupMenu.current
        AuroraCommandButton(
            modifier = modifier,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            command = this.contentModel,
            parentPopupMenu = popupMenu,
            extraAction = null,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }
}

class ColorSelectorCommandButtonProjection(
    contentModel: ColorSelectorCommand,
    presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<ColorSelectorPopupMenuContentModel, ColorSelectorCommand>(
    contentModel, presentationModel, overlays
) {
    @OptIn(AuroraInternalApi::class)
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
    }
}

class RibbonApplicationMenuCommandButtonProjection(
    contentModel: RibbonApplicationMenuCommand,
    presentationModel: CommandButtonPresentationModel,
    overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null,
    secondaryStates: Map<Command, CommandButtonPresentationState>? = null
) : BaseCommandButtonProjection<RibbonApplicationMenuContentModel, RibbonApplicationMenuCommand>(
    contentModel, presentationModel, overlays
) {
    @OptIn(AuroraInternalApi::class)
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
    }
}

class CommandButtonStripProjection(
    val contentModel: CommandGroup,
    val presentationModel: CommandStripPresentationModel,
    val overlays: Map<Command, CommandButtonPresentationModel.Overlay>? = null
) : Projection<CommandGroup, CommandStripPresentationModel>() {
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
) : Projection<CommandPanelContentModel, CommandButtonPresentationModel>() {
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
) : Projection<ComboBoxContentModel<E>, ComboBoxPresentationModel<E>>() {
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
) : Projection<SelectorContentModel, SelectorPresentationModel>() {
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

class TriStateCheckBoxProjection(
    val contentModel: TriStateSelectorContentModel,
    val presentationModel: SelectorPresentationModel = SelectorPresentationModel()
) : Projection<TriStateSelectorContentModel, SelectorPresentationModel>() {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraTriStateCheckBox(
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
) : Projection<SelectorContentModel, SelectorPresentationModel>() {
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

class SwitchProjection(
    val contentModel: SwitchContentModel,
    val presentationModel: SwitchPresentationModel = SwitchPresentationModel()
) : Projection<SwitchContentModel, SwitchPresentationModel>() {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraSwitch(
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
) : Projection<ProgressIndeterminateContentModel, ProgressCircularPresentationModel>() {
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
) : Projection<ProgressIndeterminateContentModel, ProgressLinearPresentationModel>() {
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
) : Projection<ProgressDeterminateContentModel, ProgressLinearPresentationModel>() {
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
) : Projection<IconContentModel, IconPresentationModel>() {
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
) : Projection<LabelContentModel, LabelPresentationModel>() {
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
) : Projection<SeparatorContentModel, SeparatorPresentationModel>() {
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
) : Projection<SeparatorContentModel, SeparatorPresentationModel>() {
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
) : Projection<SliderContentModel, SliderPresentationModel>() {
    @Composable
    fun project(modifier: Modifier = Modifier) {
        AuroraSlider(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

class TabsProjection(
    val contentModel: TabsContentModel,
    val presentationModel: TabsPresentationModel = TabsPresentationModel()
) : Projection<TabsContentModel, TabsPresentationModel>() {
    @Composable
    fun project(modifier: Modifier = Modifier, horizontalScrollState: ScrollState = rememberScrollState(0)) {
        AuroraTabs(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            horizontalScrollState = horizontalScrollState
        )
    }
}

class TextFieldValueProjection(
    val contentModel: TextFieldValueContentModel,
    val presentationModel: TextFieldPresentationModel = TextFieldPresentationModel()
) : Projection<TextFieldValueContentModel, TextFieldPresentationModel>() {
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
) : Projection<TextFieldStringContentModel, TextFieldPresentationModel>() {
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

