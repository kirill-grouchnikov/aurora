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
package org.pushingpixels.aurora.component.ribbon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.IconProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.impl.*
import org.pushingpixels.aurora.component.utils.getLabelPreferredHeight
import org.pushingpixels.aurora.component.utils.getLabelPreferredSingleLineWidth
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.LocalTextStyle
import kotlin.math.max
import kotlin.math.min

data class MetaComponentPresentationModel<out P : PresentationModel>(
    val presentationModel: P,
    val ribbonComponentPresentationModel: RibbonComponentPresentationModel
) : PresentationModel

class RibbonMetaComponentProjection<out C : ContentModel, out P : PresentationModel>(
    val projection: Projection<C, P>,
    val enabled: () -> Boolean,
    val ribbonComponentPresentationModel: RibbonComponentPresentationModel
) : Projection<C, MetaComponentPresentationModel<P>>() {
    override val contentModel: C
        get() = this.projection.contentModel

    override val presentationModel: MetaComponentPresentationModel<P>
        get() = MetaComponentPresentationModel(
            this.projection.presentationModel,
            this.ribbonComponentPresentationModel
        )

    @Composable
    fun project(modifier: Modifier = Modifier) {
        RibbonMetaComponent(
            modifier = modifier,
            originalProjection = this,
            projection = projection,
            enabled = enabled,
            ribbonComponentPresentationModel = ribbonComponentPresentationModel
        )
    }

    @Composable
    override fun reproject(modifier: Modifier) {
        RibbonMetaComponent(
            modifier = modifier,
            originalProjection = this,
            projection = projection,
            enabled = enabled,
            ribbonComponentPresentationModel = ribbonComponentPresentationModel
        )
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun intrinsicWidth(height: Int): Int {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        val hasIcon = (ribbonComponentPresentationModel.icon != null)
        val hasCaption = (ribbonComponentPresentationModel.caption != null)

        var result = 0.0f
        if (hasIcon) {
            result = 16 * density.density
        }
        if (hasIcon && hasCaption) {
            result += DefaultMetaComponentIconTextLayoutGap.value * density.density
        }
        if (hasCaption) {
            result += getLabelPreferredSingleLineWidth(
                contentModel = LabelContentModel(text = ribbonComponentPresentationModel.caption),
                presentationModel = LabelPresentationModel(
                    contentPadding = PaddingValues(0.dp),
                    textMaxLines = 1
                ),
                resolvedTextStyle = resolvedTextStyle,
                layoutDirection = layoutDirection,
                density = density,
                fontFamilyResolver = fontFamilyResolver
            )
        }
        if (hasIcon || hasCaption) {
            result += DefaultMetaComponentLayoutGap.value * density.density
        }
        result += projection.intrinsicWidth(height)

        return result.toInt()
    }

    @OptIn(AuroraInternalApi::class)
    @Composable
    override fun intrinsicHeight(width: Int): Int {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        val textStyle = LocalTextStyle.current
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val resolvedTextStyle = remember { resolveDefaults(textStyle, layoutDirection) }

        var result = 0
        if (ribbonComponentPresentationModel.icon != null) {
            result = (16 * density.density).toInt()
        }
        if (ribbonComponentPresentationModel.caption != null) {
            result = max(
                result, getLabelPreferredHeight(
                    contentModel = LabelContentModel(text = ribbonComponentPresentationModel.caption),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        textMaxLines = 1
                    ),
                    resolvedTextStyle = resolvedTextStyle,
                    layoutDirection = layoutDirection,
                    density = density,
                    fontFamilyResolver = fontFamilyResolver,
                    availableWidth = width.toFloat()
                ).toInt()
            )
        }
        result = max(result, projection.intrinsicHeight(width))
        return result
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun <C : ContentModel, P : PresentationModel> RibbonMetaComponent(
    modifier: Modifier,
    originalProjection: RibbonMetaComponentProjection<C, P>,
    projection: Projection<C, P>,
    enabled: () -> Boolean,
    ribbonComponentPresentationModel: RibbonComponentPresentationModel
) {
    val hasIcon = (ribbonComponentPresentationModel.icon != null)
    val hasCaption = (ribbonComponentPresentationModel.caption != null)

    val rowHeight = LocalRibbonBandRowHeight.current
    val widthNeededForComponent = projection.intrinsicWidth(rowHeight)
    val heightNeededForComponent = projection.intrinsicHeight(widthNeededForComponent)

    val trackBounds = LocalRibbonTrackBounds.current
    val trackKeyTips = LocalRibbonTrackKeyTips.current
    val keyTipChainRoot = LocalRibbonKeyTipChainRoot.current
    val keyTipChainRootKeyTip = LocalRibbonKeyTipChainRootKeyTip.current
    val bandRowHeight = LocalRibbonBandRowHeight.current
    val bandRow = LocalRibbonBandRow.current

    Layout(
        modifier = if (trackBounds) {
            modifier.metaComponentLocator(originalProjection, trackBounds, trackKeyTips, keyTipChainRoot, keyTipChainRootKeyTip)
        } else {
            modifier
        },
        content = {
            if (hasIcon) {
                IconProjection(
                    contentModel = IconContentModel(
                        icon = ribbonComponentPresentationModel.icon,
                        enabled = enabled.invoke()
                    ),
                    presentationModel = IconPresentationModel(
                        iconDimension = DpSize(16.dp, 16.dp),
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowColorTokens,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original
                    )
                ).project()
            }

            if (hasCaption) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = ribbonComponentPresentationModel.caption,
                        enabled = enabled.invoke()
                    ),
                    presentationModel = LabelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        textMaxLines = 1
                    )
                ).project()
            }

            projection.reproject(Modifier)
        },
        measurePolicy = { measurables, constraints ->
            val height = if (constraints.hasFixedHeight) constraints.maxHeight else heightNeededForComponent

            var index = 0
            val iconPlaceable = if (hasIcon) measurables[index++].measure(Constraints()) else null
            val captionPlaceable = if (hasCaption) measurables[index++].measure(Constraints()) else null

            var componentWidth = 0
            var componentOffsetX = 0
            var fullWidth = 0

            if (constraints.hasFixedWidth) {
                val width = constraints.maxWidth
                var widthLeftForComponent = width
                if (hasIcon) {
                    widthLeftForComponent -= iconPlaceable!!.measuredWidth
                }
                if (hasIcon && hasCaption) {
                    widthLeftForComponent -= DefaultMetaComponentIconTextLayoutGap.toPx().toInt()
                }
                if (hasCaption) {
                    widthLeftForComponent -= captionPlaceable!!.measuredWidth
                }
                if (hasIcon || hasCaption) {
                    widthLeftForComponent -= DefaultMetaComponentLayoutGap.toPx().toInt()
                }

                when (ribbonComponentPresentationModel.horizontalAlignment) {
                    HorizontalAlignment.Fill -> {
                        // Give all available horizontal space to the component
                        componentWidth = widthLeftForComponent
                    }

                    HorizontalAlignment.Leading -> {
                        // Give the component as much as it needs, with no offset
                        componentWidth = min(widthLeftForComponent, widthNeededForComponent)
                    }

                    HorizontalAlignment.Center -> {
                        // Give the component as much as it needs, and offset for centered placement
                        componentWidth = min(widthLeftForComponent, widthNeededForComponent)
                        componentOffsetX = (widthLeftForComponent - componentWidth) / 2
                    }

                    HorizontalAlignment.Trailing -> {
                        // Give the component as much as it needs, and offset for trailing placement
                        componentWidth = min(widthLeftForComponent, widthNeededForComponent)
                        componentOffsetX = widthLeftForComponent - componentWidth
                    }
                }
                fullWidth = constraints.maxWidth
            } else {
                componentWidth = widthNeededForComponent
                if (hasIcon) {
                    fullWidth = iconPlaceable!!.measuredWidth
                }
                if (hasIcon && hasCaption) {
                    fullWidth += DefaultMetaComponentIconTextLayoutGap.toPx().toInt()
                }
                if (hasCaption) {
                    fullWidth += captionPlaceable!!.measuredWidth
                }
                if (hasIcon || hasCaption) {
                    fullWidth += DefaultMetaComponentLayoutGap.toPx().toInt()
                }
                fullWidth += componentWidth
            }

            val componentPlaceable = measurables[index].measure(
                Constraints.fixed(
                    width = componentWidth,
                    height = heightNeededForComponent
                )
            )

            if (originalProjection.ribbonComponentPresentationModel.keyTip != null) {
                // Key tip offset logic:
                // If the meta component has caption, the key tip is horizontally centered at the start
                // edge of the caption. Otherwise, it is horizontally centered at the horizontal center
                // of the main component.
                if (hasCaption) {
                    val captionMid = if (layoutDirection == LayoutDirection.Ltr) {
                        if (hasIcon) {
                            iconPlaceable!!.measuredWidth + DefaultMetaComponentIconTextLayoutGap.toPx()
                        } else {
                            0
                        }
                    } else {
                        if (hasIcon) {
                            fullWidth - iconPlaceable!!.measuredWidth - DefaultMetaComponentIconTextLayoutGap.toPx()
                        } else {
                            fullWidth
                        }
                    }
                    KeyTipTracker.trackKeyTipOffset(
                        originalProjection,
                        originalProjection.ribbonComponentPresentationModel.keyTip,
                        originalProjection.enabled.invoke(),
                        false,
                        getAdjustedAnchor(
                            anchor = Offset(captionMid.toFloat(), height / 2.0f),
                            row = bandRow,
                            rowHeight = bandRowHeight
                        ),
                        null,
                        keyTipChainRoot,
                        keyTipChainRootKeyTip,
                        null,
                    )
                } else {
                    val componentMid = if (layoutDirection == LayoutDirection.Ltr) {
                        if (hasIcon) {
                            val iconSpace = iconPlaceable!!.measuredWidth + componentOffsetX
                            iconSpace + (fullWidth - iconSpace) / 2.0f
                        } else {
                            fullWidth / 2.0f
                        }
                    } else {
                        if (hasIcon) {
                            val iconSpace = iconPlaceable!!.measuredWidth + componentOffsetX
                            (fullWidth - iconSpace) / 2.0f
                        } else {
                            fullWidth / 2.0f
                        }
                    }
                    val adjustedAnchor = if (bandRow == RibbonBandRow.None) {
                        // Not in a ribbon band, which means we're in the task bar
                        Offset(if (layoutDirection == LayoutDirection.Ltr) fullWidth.toFloat() else 0.0f, height.toFloat())
                    } else {
                        // In a ribbon band
                        getAdjustedAnchor(
                            anchor = Offset(componentMid, height / 2.0f),
                            row = bandRow,
                            rowHeight = bandRowHeight
                        )
                    }
                    KeyTipTracker.trackKeyTipOffset(
                        originalProjection,
                        originalProjection.ribbonComponentPresentationModel.keyTip,
                        originalProjection.enabled.invoke(),
                        false,
                        adjustedAnchor,
                        null,
                        keyTipChainRoot,
                        keyTipChainRootKeyTip,
                        null
                    )
                }
            }

            layout(width = fullWidth, height = height) {
                var x = 0
                if (hasIcon) {
                    iconPlaceable?.placeRelative(x, (height - iconPlaceable.measuredHeight) / 2)
                    x += iconPlaceable!!.measuredWidth
                }
                if (hasIcon && hasCaption) {
                    x += DefaultMetaComponentIconTextLayoutGap.toPx().toInt()
                }
                if (hasCaption) {
                    captionPlaceable?.placeRelative(x, (height - captionPlaceable.measuredHeight) / 2)
                    x += captionPlaceable!!.measuredWidth
                }
                if (hasIcon || hasCaption) {
                    x += DefaultMetaComponentLayoutGap.toPx().toInt()
                }

                x += componentOffsetX
                componentPlaceable.placeRelative(x, (height - componentPlaceable.measuredHeight) / 2)
            }
        })

    DisposableEffect(projection) {
        onDispose {
            BoundsTracker.untrackBounds(originalProjection)
            KeyTipTracker.untrackKeyTip(originalProjection)
        }
    }
}

@OptIn(AuroraInternalApi::class)
private class MetaComponentLocator(
    val projection: RibbonMetaComponentProjection<*, *>,
    val trackBounds: Boolean,
    val trackKeyTips: Boolean,
    val keyTipChainRoot: Any?,
    val keyTipChainRootKeyTip: String?,
) :
    OnGloballyPositionedModifier {
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        // Convert the top left corner of the component to the root coordinates
        val converted = coordinates.localToRoot(Offset.Zero)

        val bounds = AuroraRect(
            x = converted.x,
            y = converted.y,
            width = coordinates.size.width.toFloat(),
            height = coordinates.size.height.toFloat()
        )
        if (trackBounds) {
            BoundsTracker.trackBounds(projection, bounds)
        }
        if (trackKeyTips) {
            if (projection.presentationModel.ribbonComponentPresentationModel.keyTip != null) {
                KeyTipTracker.trackKeyTipBase(
                    projection,
                    projection.presentationModel.ribbonComponentPresentationModel.keyTip!!,
                    projection.enabled.invoke(),
                    false,
                    bounds,
                    keyTipChainRoot,
                    keyTipChainRootKeyTip,
                    { null }
                )
            }
        }
    }
}

@Composable
private fun Modifier.metaComponentLocator(
    projection: RibbonMetaComponentProjection<*, *>,
    trackBounds: Boolean,
    trackKeyTips: Boolean,
    keyTipChainRoot: Any?,
    keyTipChainRootKeyTip: String?,
) = this.then(MetaComponentLocator(projection, trackBounds, trackKeyTips, keyTipChainRoot, keyTipChainRootKeyTip))

private val DefaultMetaComponentIconTextLayoutGap = 4.dp
private val DefaultMetaComponentLayoutGap = 6.dp
