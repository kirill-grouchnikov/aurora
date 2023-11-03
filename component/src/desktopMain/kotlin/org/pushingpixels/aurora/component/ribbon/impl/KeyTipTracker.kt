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
package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.skia.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.common.isEmpty
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.component.utils.DrawingCache
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.MutableColorScheme

@AuroraInternalApi
object KeyTipTracker {
    data class KeyTipLink(
        val projection: Projection<ContentModel, PresentationModel>,
        val keyTip: String,
        val isEnabled: Boolean,
        var screenRect: AuroraRect,
        var anchor: Offset,
        val chainRoot: Any? = null,
        val traversal: (() -> KeyTipChain)? = null
    )

    data class KeyTipChain(
        val links: List<KeyTipLink>,
        val keyTipLookupIndex: Int = 0,
        val parent: (() -> KeyTipChain)? = null
    )

    private val keyTips: MutableList<KeyTipLink> = arrayListOf()

    private val keyTipChains: MutableList<KeyTipChain> = arrayListOf()

    fun trackKeyTipBase(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        screenRect: AuroraRect,
        chainRoot: Any?,
    ) {
        val existing = keyTips.find {
            (it.projection == projection) && (it.keyTip == keyTip)
        }
        if (existing != null) {
            existing.screenRect = screenRect.copy()
        } else {
            keyTips.add(KeyTipLink(
                projection = projection,
                keyTip = keyTip,
                isEnabled = isEnabled,
                screenRect =  screenRect,
                anchor = Offset.Zero,
                chainRoot = chainRoot))
        }
    }

    fun trackKeyTipOffset(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        anchor: Offset,
        chainRoot: Any?,
    ) {
        val existing = keyTips.find {
            (it.projection == projection) && (it.keyTip == keyTip)
        }
        if (existing != null) {
            existing.anchor = anchor.copy()
        } else {
            keyTips.add(
                KeyTipLink(
                    projection = projection,
                    keyTip = keyTip,
                    isEnabled = isEnabled,
                    screenRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
                    anchor = anchor.copy(),
                    chainRoot = chainRoot,
                )
            )
        }
    }

    fun untrackKeyTip(
        projection: Projection<ContentModel, PresentationModel>
    ) {
        keyTips.removeIf {
            (it.projection == projection)
        }
    }

    internal fun getKeyTips(): List<KeyTipLink> = keyTips

    internal fun getCurrentlyShownKeyTipChain(): KeyTipChain? {
        if (keyTipChains.isEmpty()) {
            return null
        }
        return keyTipChains.last()
    }

    fun isShowingKeyTips(): Boolean = keyTipChains.isNotEmpty()

    fun showPreviousChain() {
        if (keyTipChains.isEmpty()) {
            return
        }
        keyTipChains.removeLast()
        visibleFlow.value = keyTipChains.isNotEmpty()
    }

    fun hideAllKeyTips() {
        keyTipChains.clear()
        visibleFlow.value = false
    }

    fun showRootKeyTipChain(ribbon: Ribbon) {
        keyTipChains.add(KeyTipChain(links = keyTips.filter { it.chainRoot == ribbon }))
        visibleFlow.value = true
    }

    fun handleKeyPress(char: Char) {
        if (isShowingKeyTips()) {
            println("Handling key press $char")
        }
    }

    val visibleFlow = MutableStateFlow(false)
    val uiVisibleFlow: StateFlow<Boolean> = visibleFlow
}

@Immutable
private class KeyTipDrawingCache(
    override val colorScheme: MutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )
) : DrawingCache

@AuroraInternalApi
@Composable
fun RibbonKeyTipOverlay(modifier: Modifier, insets: Dp) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters

    val drawingCache = remember { KeyTipDrawingCache() }

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = resolveDefaults(LocalTextStyle.current, layoutDirection)
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val visibilityState by KeyTipTracker.visibleFlow.collectAsState()

    if (visibilityState) {
        Canvas(modifier = modifier) {
            val currentlyShownKeyTipChain = KeyTipTracker.getCurrentlyShownKeyTipChain()
            if (currentlyShownKeyTipChain != null) {
                for (tracked in currentlyShownKeyTipChain.links) {
                    if (!tracked.screenRect.isEmpty) {
                        drawKeyTip(
                            tracked,
                            textStyle,
                            density,
                            fontFamilyResolver,
                            layoutDirection,
                            insets,
                            drawingCache,
                            decorationAreaType,
                            skinColors,
                            painters
                        )
                    }
                }
            }
        }
    }
}

internal fun getKeyTipSize(
    keyTip: String,
    textStyle: TextStyle,
    density: Density,
    fontFamilyResolver: FontFamily.Resolver,
    layoutDirection: LayoutDirection
): Pair<Size, Float> {
    val leftPadding = KeyTipPaddingValues.calculateLeftPadding(layoutDirection)
    val rightPadding = KeyTipPaddingValues.calculateRightPadding(layoutDirection)
    val topPadding = KeyTipPaddingValues.calculateTopPadding()
    val bottomPadding = KeyTipPaddingValues.calculateBottomPadding()

    // Compute how much space the keytip text needs
    val paragraph = Paragraph(
        text = keyTip, style = textStyle, constraints = Constraints(maxWidth = Int.MAX_VALUE),
        density = density, maxLines = 1, fontFamilyResolver = fontFamilyResolver
    )

    val tipWidth =
        leftPadding.value * density.density + paragraph.maxIntrinsicWidth + rightPadding.value * density.density
    val tipHeight = topPadding.value * density.density + paragraph.height + bottomPadding.value * density.density

    return Pair(Size(tipWidth, tipHeight), paragraph.firstBaseline)
}

@OptIn(AuroraInternalApi::class)
internal fun getAdjustedAnchor(
    anchor: Offset,
    row: RibbonBandRow,
    rowHeight: Int
): Offset {
    return when (row) {
        RibbonBandRow.Top -> anchor.copy(y = 0.0f)
        RibbonBandRow.Middle -> anchor.copy(y = rowHeight / 2.0f)
        RibbonBandRow.Bottom -> anchor.copy(y = rowHeight.toFloat())
        RibbonBandRow.None -> anchor
    }
}

@OptIn(AuroraInternalApi::class)
internal fun DrawScope.drawKeyTip(
    keyTipInfo: KeyTipTracker.KeyTipLink,
    textStyle: TextStyle,
    density: Density,
    fontFamilyResolver: FontFamily.Resolver,
    layoutDirection: LayoutDirection,
    insets: Dp,
    drawingCache: DrawingCache,
    decorationAreaType: DecorationAreaType,
    skinColors: AuroraSkinColors,
    painters: AuroraPainters
) {
    val keyTipFont = Font(Typeface.makeDefault()).also {
        it.size *= density.density
    }

    val leftPadding = KeyTipPaddingValues.calculateLeftPadding(layoutDirection)
    val topPadding = KeyTipPaddingValues.calculateTopPadding()

    val state = if (keyTipInfo.isEnabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val fillScheme = skinColors.getColorScheme(decorationAreaType, state)
    val borderScheme = skinColors.getColorScheme(decorationAreaType, ColorSchemeAssociationKind.Border, state)
    val alpha = skinColors.getAlpha(decorationAreaType, state)
    val fillPainter = painters.fillPainter
    val borderPainter = painters.borderPainter
    val buttonShaper = ClassicButtonShaper.Instance

    val tipSizingInfo = getKeyTipSize(keyTipInfo.keyTip, textStyle, density, fontFamilyResolver, layoutDirection)
    val tipWidth = tipSizingInfo.first.width
    val tipHeight = tipSizingInfo.first.height
    val baseline = tipSizingInfo.second

    val fullOffsetX = keyTipInfo.screenRect.x + keyTipInfo.anchor.x - tipWidth / 2 - insets.toPx()
    val fullOffsetY = keyTipInfo.screenRect.y + keyTipInfo.anchor.y - tipHeight / 2 - insets.toPx()

    withTransform({
        translate(left = fullOffsetX, top = fullOffsetY)
    }) {
        val fillOutline = buttonShaper.getButtonOutline(
            layoutDirection = layoutDirection,
            width = tipWidth,
            height = tipHeight,
            extraInsets = 0.5f,
            isInner = false,
            sides = Sides(),
            outlineKind = OutlineKind.Fill,
            density = this
        )

        val outlineBoundingRect = fillOutline.bounds
        if (outlineBoundingRect.isEmpty) {
            return@withTransform
        }

        // Populate the cached color scheme for filling the combobox
        drawingCache.colorScheme.ultraLight = fillScheme.ultraLightColor
        drawingCache.colorScheme.extraLight = fillScheme.extraLightColor
        drawingCache.colorScheme.light = fillScheme.lightColor
        drawingCache.colorScheme.mid = fillScheme.midColor
        drawingCache.colorScheme.dark = fillScheme.darkColor
        drawingCache.colorScheme.ultraDark = fillScheme.ultraDarkColor
        drawingCache.colorScheme.isDark = fillScheme.isDark
        drawingCache.colorScheme.foreground = fillScheme.foregroundColor
        fillPainter.paintContourBackground(
            this, this.size, fillOutline, drawingCache.colorScheme, alpha
        )

        // Populate the cached color scheme for drawing the border
        drawingCache.colorScheme.ultraLight = borderScheme.ultraLightColor
        drawingCache.colorScheme.extraLight = borderScheme.extraLightColor
        drawingCache.colorScheme.light = borderScheme.lightColor
        drawingCache.colorScheme.mid = borderScheme.midColor
        drawingCache.colorScheme.dark = borderScheme.darkColor
        drawingCache.colorScheme.ultraDark = borderScheme.ultraDarkColor
        drawingCache.colorScheme.isDark = borderScheme.isDark
        drawingCache.colorScheme.foreground = borderScheme.foregroundColor

        val borderOutline = buttonShaper.getButtonOutline(
            layoutDirection = layoutDirection,
            width = tipWidth,
            height = tipHeight,
            extraInsets = 0.5f,
            isInner = false,
            sides = Sides(),
            outlineKind = OutlineKind.Border,
            density = this
        )

        val innerBorderOutline = if (borderPainter.isPaintingInnerOutline)
            buttonShaper.getButtonOutline(
                layoutDirection = layoutDirection,
                width = tipWidth,
                height = tipHeight,
                extraInsets = 1.0f,
                isInner = true,
                sides = Sides(),
                outlineKind = OutlineKind.Border,
                density = this
            ) else null

        borderPainter.paintBorder(
            this, this.size, borderOutline, innerBorderOutline, drawingCache.colorScheme, alpha
        )

        this.drawIntoCanvas { canvas ->
            val nativeCanvas = canvas.nativeCanvas
            nativeCanvas.drawTextLine(
                line = TextLine.make(
                    text = keyTipInfo.keyTip,
                    font = keyTipFont
                ),
                x = leftPadding.toPx(),
                y = topPadding.toPx() + baseline,
                paint = Paint().also { skiaPaint ->
                    skiaPaint.color4f = Color4f(
                        r = fillScheme.foregroundColor.red,
                        g = fillScheme.foregroundColor.green,
                        b = fillScheme.foregroundColor.blue,
                        a = fillScheme.foregroundColor.alpha
                    )
                }
            )
        }
    }
}

private val KeyTipPaddingValues = PaddingValues(horizontal = 4.dp, vertical = 3.dp)
