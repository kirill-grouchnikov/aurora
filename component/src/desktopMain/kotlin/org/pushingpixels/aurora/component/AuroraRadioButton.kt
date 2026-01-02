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
package org.pushingpixels.aurora.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.utils.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.MutableContainerColorTokens
import org.pushingpixels.aurora.theming.utils.paintOutline
import org.pushingpixels.aurora.theming.utils.paintSurface

@Immutable
@OptIn(AuroraInternalApi::class)
private class RadioButtonDrawingCache(
    val colorTokens: MutableContainerColorTokens = MutableContainerColorTokens()
)

private object RadioButtonMarkOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        return Outline.Rounded(
            roundRect = RoundRect(
                left = 0.5f + insets,
                top = 0.5f + insets,
                right = size.width - 0.5f - insets,
                bottom = size.height - 0.5f - insets,
                radiusX = (size.width - 1.0f) / 2.0f - insets,
                radiusY = (size.height - 1.0f) / 2.0f - insets
            )
        )
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun radioButtonIntrinsicSize(
    contentModel: SelectorContentModel,
    presentationModel: SelectorPresentationModel
): Size {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = LocalTextStyle.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

    var contentWidth: Dp = presentationModel.markSize
    contentWidth += SelectorSizingConstants.SelectorMarkTextGap *
            presentationModel.horizontalGapScaleFactor
    contentWidth += (getLabelPreferredSingleLineWidth(
        contentModel = LabelContentModel(text = contentModel.text),
        presentationModel = LabelPresentationModel(
            contentPadding = PaddingValues(0.dp),
            textStyle = resolvedTextStyle,
            textMaxLines = 1,
            textOverflow = TextOverflow.Visible
        ),
        resolvedTextStyle = resolvedTextStyle,
        layoutDirection = layoutDirection,
        density = density,
        fontFamilyResolver = fontFamilyResolver
    ) / density.density).dp

    val width = presentationModel.contentPadding.calculateStartPadding(layoutDirection) +
            contentWidth +
            presentationModel.contentPadding.calculateEndPadding(layoutDirection)

    var contentHeight: Dp = presentationModel.markSize
    contentHeight = max(
        contentHeight,
        (getLabelPreferredHeight(
            contentModel = LabelContentModel(text = contentModel.text),
            presentationModel = LabelPresentationModel(
                contentPadding = PaddingValues(0.dp),
                textStyle = resolvedTextStyle,
                textMaxLines = 1,
                textOverflow = TextOverflow.Visible
            ),
            resolvedTextStyle = resolvedTextStyle,
            layoutDirection = layoutDirection,
            density = density,
            fontFamilyResolver = fontFamilyResolver,
            availableWidth = Float.MAX_VALUE
        ) / density.density).dp,
    )
    val height = presentationModel.contentPadding.calculateTopPadding() +
            contentHeight + presentationModel.contentPadding.calculateBottomPadding()

    return Size(
        width.value * density.density,
        height.value * density.density
    )
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraRadioButton(
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    contentModel: SelectorContentModel,
    presentationModel: SelectorPresentationModel
) {
    val drawingCache = remember { RadioButtonDrawingCache() }
    val rollover by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentState = remember {
        mutableStateOf(
            ComponentState.getState(
                isEnabled = contentModel.enabled,
                isRollover = rollover,
                isSelected = contentModel.selected,
                isPressed = isPressed
            )
        )
    }

    val density = LocalDensity.current

    val markAlpha = remember { mutableStateOf(if (contentModel.selected) 1.0f else 0.0f) }

    // Transition for the selection state
    val selectionTransition = updateTransition(contentModel.selected)
    val selectedFraction by selectionTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the rollover state
    val rolloverTransition = updateTransition(rollover)
    val rolloverFraction by rolloverTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the pressed state
    val pressedTransition = updateTransition(isPressed)
    val pressedFraction by pressedTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // Transition for the enabled state
    val enabledTransition = updateTransition(contentModel.enabled)
    val enabledFraction by enabledTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = AuroraSkin.animationConfig.regular)
        }
    ) {
        when (it) {
            false -> 0.0f
            true -> 1.0f
        }
    }

    // TODO - figure out why the animations are not running without looking
    //  at the result (and how it looks like in the new animation APIs)
    @Suppress("UNUSED_VARIABLE")
    val totalFraction = selectedFraction + rolloverFraction +
            pressedFraction + enabledFraction

    val modelStateInfo = remember { ModelStateInfo(currentState.value) }
    val transitionInfo = remember { mutableStateOf<TransitionInfo?>(null) }

    StateTransitionTracker(
        modelStateInfo = modelStateInfo,
        currentState = currentState,
        transitionInfo = transitionInfo,
        enabled = contentModel.enabled,
        selected = contentModel.selected,
        rollover = rollover,
        pressed = isPressed,
        duration = AuroraSkin.animationConfig.regular
    )

    if (transitionInfo.value != null) {
        LaunchedEffect(currentState.value) {
            val transitionFloat = Animatable(transitionInfo.value!!.from)
            val result = transitionFloat.animateTo(
                targetValue = transitionInfo.value!!.to,
                animationSpec = tween(durationMillis = transitionInfo.value!!.duration)
            ) {
                modelStateInfo.updateActiveStates(value)
            }

            if (result.endReason == AnimationEndReason.Finished) {
                modelStateInfo.updateActiveStates(1.0f)
                modelStateInfo.clear(currentState.value)
            }
        }
    }

    // The toggleable modifier is set on the checkbox mark, as well as on the
    // content so that the whole thing is clickable to toggle the control.
    val decorationAreaType = AuroraSkin.decorationAreaType
    Row(
        modifier = modifier
            .padding(presentationModel.contentPadding)
            .auroraRichTooltip(
                richTooltip = contentModel.richTooltip,
                presentationModel = presentationModel.richTooltipPresentationModel
            )
            .selectable(
                selected = contentModel.selected,
                onClick = { contentModel.onClick.invoke() },
                enabled = contentModel.enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = null
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Populate the cached color tokens for filling the radio button mark
        // based on the current model state info
        populateColorTokens(
            colorTokens = drawingCache.colorTokens,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            associationKind = ContainerColorTokensAssociationKind.Mark,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            treatEnabledAsActive = false,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted)

        // Mark color
        val markColor = getStateAwareColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Mark,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Muted,
        ) { it.onContainer }

        // Checkmark alpha is the combined strength of all the
        // states that have the selection bit turned on
        markAlpha.value =
            modelStateInfo.stateContributionMap
                .filter { it.key.isFacetActive(ComponentStateFacet.Selection) }
                .map { it.value }
                .sumOf { it.contribution.toDouble() }
                .toFloat()
        val onContainerAlpha = if (currentState.value.isDisabled)
            drawingCache.colorTokens.onContainerDisabledAlpha else 1.0f

        // Text color. Note that the text doesn't "participate" in state changes that
        // involve rollover, selection or pressed bits
        val textColor = getTextColor(
            modelStateInfo = modelStateInfo,
            currState = currentState.value,
            colors = AuroraSkin.colors,
            tokensOverlayProvider = presentationModel.colorTokensOverlayProvider,
            decorationAreaType = decorationAreaType,
            associationKind = ContainerColorTokensAssociationKind.Default,
            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Never,
            skipFlatCheck = false,
            inactiveContainerType = ContainerType.Neutral,
            isTextInFilledArea = false
        )

        val surfacePainter = AuroraSkin.painters.surfacePainter
        val surfacePainterOverlay = AuroraSkin.painterOverlays?.surfacePainterOverlay
        val outlinePainter = AuroraSkin.painters.outlinePainter
        val outlinePainterOverlay = AuroraSkin.painterOverlays?.outlinePainterOverlay

        Canvas(
            Modifier.wrapContentSize(Alignment.Center).size(presentationModel.markSize)
        ) {
            val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
            val outlineFill = RadioButtonMarkOutlineSuppler.getOutline(
                layoutDirection = layoutDirection,
                density = density,
                size = this.size,
                insets = outlineInset,
                radiusAdjustment = 0.0f,
                outlineKind = OutlineKind.Surface)

            paintSurface(
                drawScope = this,
                componentState = currentState.value,
                surfacePainter = surfacePainter,
                surfacePainterOverlay = surfacePainterOverlay,
                size = this.size,
                alpha = 1.0f,
                outline = outlineFill,
                colorTokens = drawingCache.colorTokens)

            paintOutline(
                drawScope = this,
                componentState = currentState.value,
                outlinePainter = outlinePainter,
                outlinePainterOverlay = outlinePainterOverlay,
                size = this.size,
                alpha = 1.0f,
                outlineSupplier = RadioButtonMarkOutlineSuppler,
                colorTokens = drawingCache.colorTokens)

            // Draw the radio mark with the alpha that corresponds to the current
            // selection and potential transition
            val markCenter = this.size.width / 2.0f
            val markRadius = this.size.width / 4.5f
            val outlineMark = Outline.Rounded(
                roundRect = RoundRect(
                    left = markCenter - markRadius, top = markCenter - markRadius,
                    right = markCenter + markRadius, bottom = markCenter + markRadius,
                    radiusX = markRadius, radiusY = markRadius
                )
            )

            // Note that we apply alpha twice - once for the selected / checked
            // state or transition, and the second time based on the enabled state
            drawOutline(
                outline = outlineMark,
                color = markColor.withAlpha(markAlpha.value),
                style = Fill,
                alpha = onContainerAlpha
            )
        }
        Spacer(modifier = Modifier.width(SelectorSizingConstants.SelectorMarkTextGap *
                presentationModel.horizontalGapScaleFactor))
        // Pass our text color and model state snapshot to the children
        CompositionLocalProvider(
            LocalTextColor provides textColor,
            LocalModelStateInfoSnapshot provides modelStateInfo.getSnapshot(currentState.value)
        ) {
            Box(
                modifier = Modifier.requiredSizeIn(
                    minWidth = 0.dp,
                    minHeight = presentationModel.markSize
                )
            ) {
                AuroraText(text = contentModel.text)
            }
        }
    }
}
