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
package org.pushingpixels.aurora.component.projection

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.popup.BaseCommandMenuHandler
import org.pushingpixels.aurora.component.utils.popup.ColorSelectorCommandMenuPopupHandler
import org.pushingpixels.aurora.component.utils.popup.GeneralCommandMenuPopupHandler
import org.pushingpixels.aurora.theming.LocalTextStyle

abstract class Projection<out C : ContentModel, out P : PresentationModel> {
    @Composable
    abstract fun reproject(modifier: Modifier)

    @Composable
    open fun intrinsicWidth(height: Int): Int = 0

    @Composable
    open fun intrinsicHeight(width: Int): Int = 0
}

abstract class BaseCommandButtonProjection<out C : BaseCommand,
        out P: BaseCommandButtonPresentationModel, out CBP: BaseCommandButtonProjection<C, P, CBP>>(
    open val contentModel: C,
    open val presentationModel: P,
    open val secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : Projection<C, P>() {
    @Composable
    protected fun <MC : BaseCommandMenuContentModel,
            MP : BaseCommandPopupMenuPresentationModel> project(
        modifier: Modifier = Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay ?= null,
        actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupHandler: BaseCommandMenuHandler<MC, MP>
    ) {
        AuroraCommandButton(
            modifier = modifier,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            command = this.contentModel,
            presentationModel = if (primaryOverlay != null) this.presentationModel.overlayWith(primaryOverlay) else
                this.presentationModel,
            popupHandler = popupHandler,
            secondaryOverlays = this.secondaryOverlays ?: mapOf()
        )
    }

    abstract fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): CBP

    @Composable
    abstract fun reproject(
        modifier: Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay,
        actionInteractionSource: MutableInteractionSource,
        popupInteractionSource: MutableInteractionSource,
    )

    @OptIn(AuroraInternalApi::class)
    @Composable
    private fun intrinsicSize(): Size {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        val layoutManager = presentationModel.presentationState.createLayoutManager(
            layoutDirection = layoutDirection,
            density = density,
            textStyle = resolvedTextStyle,
            fontFamilyResolver = fontFamilyResolver
        )
        val preLayoutInfo =
            layoutManager.getPreLayoutInfo(
                contentModel,
                presentationModel
            )
        return layoutManager.getPreferredSize(
            contentModel, presentationModel, preLayoutInfo
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return this.intrinsicSize().width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return this.intrinsicSize().height.toInt()
    }
}

class CommandButtonProjection(
    contentModel: Command,
    presentationModel: CommandButtonPresentationModel = CommandButtonPresentationModel(),
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<Command, CommandButtonPresentationModel, CommandButtonProjection>(
    contentModel, presentationModel, secondaryOverlays
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        actionInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            popupHandler = GeneralCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): CommandButtonProjection {
        return CommandButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = secondaryOverlays
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() },
            popupHandler = GeneralCommandMenuPopupHandler
        )
    }

    @Composable
    override fun reproject(
        modifier: Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay,
        actionInteractionSource: MutableInteractionSource,
        popupInteractionSource: MutableInteractionSource,
    ) {
        super.project(
            modifier = modifier,
            primaryOverlay = primaryOverlay,
            actionInteractionSource = actionInteractionSource,
            popupInteractionSource = popupInteractionSource,
            popupHandler = GeneralCommandMenuPopupHandler,
        )
    }
}

class ColorSelectorCommandButtonProjection(
    contentModel: ColorSelectorCommand,
    presentationModel: ColorSelectorCommandButtonPresentationModel = ColorSelectorCommandButtonPresentationModel(),
    secondaryOverlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
) : BaseCommandButtonProjection<ColorSelectorCommand, ColorSelectorCommandButtonPresentationModel, ColorSelectorCommandButtonProjection>(
    contentModel, presentationModel, secondaryOverlays
) {
    private fun checkModel() {
        require(contentModel.secondaryContentModel.entries.all {
            when (it) {
                is ColorSelectorPopupMenuSection -> it.colors.size == presentationModel.popupMenuPresentationModel.colorColumns
                is ColorSelectorPopupMenuSectionWithDerived -> it.colors.size == presentationModel.popupMenuPresentationModel.colorColumns
                else -> true
            }
        }) {
            "Color section in the secondary content model does not match the column count in popup presentation model"
        }

        require(contentModel.secondaryContentModel.entries.all {
            when (it) {
                is ColorSelectorPopupMenuSectionWithDerived -> it.derivedCount >= 1
                else -> true
            }
        }) {
            "Needs to pass a non-trivial number of derived colors"
        }
    }

    @Composable
    fun project(
        modifier: Modifier = Modifier,
        popupInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        checkModel()

        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = ColorSelectorCommandMenuPopupHandler,
        )
    }

    override fun copy(primaryOverlay: BaseCommandButtonPresentationModel.Overlay): ColorSelectorCommandButtonProjection {
        return ColorSelectorCommandButtonProjection(
            contentModel = this.contentModel,
            presentationModel = this.presentationModel.overlayWith(primaryOverlay),
            secondaryOverlays = this.secondaryOverlays
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        checkModel()

        super.project(
            modifier = modifier,
            primaryOverlay = null,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = remember { MutableInteractionSource() },
            popupHandler = ColorSelectorCommandMenuPopupHandler,
        )
    }

    @Composable
    override fun reproject(
        modifier: Modifier,
        primaryOverlay: BaseCommandButtonPresentationModel.Overlay,
        actionInteractionSource: MutableInteractionSource,
        popupInteractionSource: MutableInteractionSource
    ) {
        checkModel()

        super.project(
            modifier = modifier,
            primaryOverlay = primaryOverlay,
            actionInteractionSource = remember { MutableInteractionSource() },
            popupInteractionSource = popupInteractionSource,
            popupHandler = ColorSelectorCommandMenuPopupHandler,
        )
    }
}

class CommandButtonStripProjection(
    val contentModel: CommandGroup,
    val presentationModel: CommandStripPresentationModel,
    val overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraCommandButtonStrip(
            modifier = modifier,
            commandGroup = this.contentModel,
            presentationModel = this.presentationModel,
            overlays = this.overlays ?: mapOf()
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return commandButtonStripIntrinsicSize(this.contentModel, this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return commandButtonStripIntrinsicSize(this.contentModel, this.presentationModel).height.toInt()
    }
}

class CommandButtonPanelProjection(
    val contentModel: CommandPanelContentModel,
    val presentationModel: CommandPanelPresentationModel,
    val overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay>? = null
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

class BreadcrumbBarProjection(
    val contentModel: BreadcrumbBarContentModel,
    val presentationModel: BreadcrumbBarPresentationModel = BreadcrumbBarPresentationModel()
) : Projection<BreadcrumbBarContentModel, BreadcrumbBarPresentationModel>() {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        horizontalScrollState: ScrollState = rememberScrollState(0)
    ) {
        AuroraBreadcrumbBar(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            horizontalScrollState = horizontalScrollState
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraBreadcrumbBar(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            horizontalScrollState = rememberScrollState(0)
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return breadcrumbBarIntrinsicSize(this.contentModel, this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return breadcrumbBarIntrinsicSize(this.contentModel, this.presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraComboBox(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return comboBoxIntrinsicSize(this.contentModel, this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return comboBoxIntrinsicSize(this.contentModel, this.presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraCheckBox(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return checkBoxIntrinsicSize(contentModel, presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return checkBoxIntrinsicSize(contentModel, presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraTriStateCheckBox(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return triStateCheckBoxIntrinsicSize(contentModel, presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return triStateCheckBoxIntrinsicSize(contentModel, presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraRadioButton(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return radioButtonIntrinsicSize(contentModel, presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return radioButtonIntrinsicSize(contentModel, presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraSwitch(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraCircularProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return circularProgressIntrinsicSize(this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return circularProgressIntrinsicSize(this.presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraIndeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return linearProgressIntrinsicSize(this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return linearProgressIntrinsicSize(this.presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraDeterminateLinearProgress(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }

    @Composable
    override fun intrinsicWidth(height: Int): Int {
        return linearProgressIntrinsicSize(this.presentationModel).width.toInt()
    }

    @Composable
    override fun intrinsicHeight(width: Int): Int {
        return linearProgressIntrinsicSize(this.presentationModel).height.toInt()
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

    @Composable
    override fun reproject(modifier: Modifier) {
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraTabs(
            modifier = modifier,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel,
            horizontalScrollState = rememberScrollState(0)
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraTextField(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
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

    @Composable
    override fun reproject(modifier: Modifier) {
        AuroraTextField(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}

