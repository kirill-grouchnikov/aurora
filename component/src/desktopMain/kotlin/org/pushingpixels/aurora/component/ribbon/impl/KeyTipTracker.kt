/*
 * Copyright 2020-2025 Aurora, Kirill Grouchnikov
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
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
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraRect
import org.pushingpixels.aurora.common.isEmpty
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.theming.utils.paintOutline
import org.pushingpixels.aurora.theming.utils.paintSurface
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.colortokens.AuroraSkinColors
import org.pushingpixels.aurora.theming.painter.outline.InsetKind
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getBaseOutline
import org.pushingpixels.aurora.theming.utils.getClassicCornerRadius
import org.pushingpixels.aurora.theming.utils.getContainerTokens

@AuroraInternalApi
object KeyTipTracker {
    data class KeyTipLink(
        val projection: Projection<ContentModel, PresentationModel>,
        val keyTip: String,
        val isEnabled: Boolean,
        var screenRect: AuroraRect,
        var anchor: Offset,
        var onActivated: (() -> Unit)?,
        val chainRoot: Any?,
        val traversal: Any?
    )

    data class KeyTipChain(
        val links: List<KeyTipLink>,
        val keyTipLookupIndex: Int = 0,
    )

    private val keyTips: MutableList<KeyTipLink> = arrayListOf()

    private val keyTipChains: MutableList<KeyTipChain> = arrayListOf()

    private val chainRoots: MutableList<Any> = arrayListOf()

    fun trackKeyTipBase(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        screenRect: AuroraRect,
        chainRoot: Any?,
        traversal: Any?,
    ) {
        val existing = keyTips.find {
            (it.projection == projection) && (it.keyTip == keyTip)
        }
        if (existing != null) {
            existing.screenRect = screenRect.copy()
        } else {
            keyTips.add(
                KeyTipLink(
                    projection = projection,
                    keyTip = keyTip,
                    isEnabled = isEnabled,
                    screenRect = screenRect,
                    anchor = Offset.Zero,
                    onActivated = null,
                    chainRoot = chainRoot,
                    traversal = traversal,
                )
            )
        }
    }

    fun trackKeyTipOffset(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        anchor: Offset,
        onActivated: (() -> Unit)?,
        chainRoot: Any?,
        traversal: Any?,
    ) {
        val existing = keyTips.find {
            (it.projection == projection) && (it.keyTip == keyTip)
        }
        if (existing != null) {
            existing.anchor = anchor.copy()
            existing.onActivated = onActivated
        } else {
            keyTips.add(
                KeyTipLink(
                    projection = projection,
                    keyTip = keyTip,
                    isEnabled = isEnabled,
                    screenRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
                    anchor = anchor.copy(),
                    onActivated = onActivated,
                    chainRoot = chainRoot,
                    traversal = traversal,
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
        chainRoots.removeLast()
        visibleFlow.value = keyTipChains.isNotEmpty()
        chainDepth.value--
        println("Going back one at new depth ${chainDepth.value}")
    }

    fun hideAllKeyTips() {
        keyTipChains.clear()
        chainRoots.clear()
        visibleFlow.value = false
        chainDepth.value = 0
        println("Cleared all key tips, depth ${chainDepth.value}")
    }

    fun showRootKeyTipChain(ribbon: Ribbon) {
        keyTipChains.add(KeyTipChain(links = keyTips.filter { it.chainRoot == ribbon }))
        chainRoots.add(ribbon)
        visibleFlow.value = true
        chainDepth.value = 1
    }

    fun handleKeyPress(char: Char) {
        if (!isShowingKeyTips()) {
            return
        }
        println("Processing $char at depth ${chainDepth.value}")
        val currChain = getCurrentlyShownKeyTipChain()!!
        val currChainRoot = chainRoots.last()

        // Go over the key tip links and see if there is an exact match
        for (link in currChain.links) {
            val keyTipString = link.keyTip
            // TODO - handle two-character tips
            if (char.lowercaseChar() == keyTipString[0].lowercaseChar()) {
                // Match!
                if (link.isEnabled) {
                    link.onActivated?.invoke()
                    // TODO - activate the element
                    if (link.traversal != null) {
                        val nextChainRoot = link.traversal
                        keyTipChains.add(KeyTipChain(links = keyTips.filter { it.chainRoot == nextChainRoot }))
                        chainRoots.add(nextChainRoot)
                        chainDepth.value++
                        println("Going to next root ${nextChainRoot.javaClass.simpleName} at new depth ${chainDepth.value}")
                    } else {
                        // Match found and activated, and no further traversal available
                        // a) Dismiss all key tip chains
                        hideAllKeyTips()
                        // b) hide all popups
                        AuroraPopupManager.hidePopups(null)
                    }
                }
            }
        }
    }

    private val visibleFlow = MutableStateFlow(false)
    val uiVisibleFlow: StateFlow<Boolean> = visibleFlow
    private val chainDepth = MutableStateFlow(0)
    val uiChainDepth: StateFlow<Int> = chainDepth
}

@AuroraInternalApi
@Composable
fun RibbonKeyTipOverlay(modifier: Modifier, insets: Dp) {
    val decorationAreaType = AuroraSkin.decorationAreaType
    val skinColors = AuroraSkin.colors
    val painters = AuroraSkin.painters

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val textStyle = resolveDefaults(LocalTextStyle.current, layoutDirection)
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val visibilityState by KeyTipTracker.uiVisibleFlow.collectAsState()
    val chainDepth by KeyTipTracker.uiChainDepth.collectAsState()

    if (visibilityState && (chainDepth > 0)) {
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

private object KeyTipOutlineSuppler: OutlineSupplier {
    override fun getOutline(
        layoutDirection: LayoutDirection,
        density: Density,
        size: Size,
        insets: Float,
        radiusAdjustment: Float,
        outlineKind: OutlineKind
    ): Outline {
        val cornerRadius = density.getClassicCornerRadius()
        return getBaseOutline(
            layoutDirection = layoutDirection,
            width = size.width,
            height = size.height,
            radius = cornerRadius - radiusAdjustment,
            sides = Sides(),
            insets = insets,
            outlineKind = outlineKind,
        )
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
    decorationAreaType: DecorationAreaType,
    skinColors: AuroraSkinColors,
    painters: AuroraPainters
) {
    val keyTipFont = Font(Typeface.makeEmpty()).also {
        it.size *= density.density
    }

    val leftPadding = KeyTipPaddingValues.calculateLeftPadding(layoutDirection)
    val topPadding = KeyTipPaddingValues.calculateTopPadding()

    val state = if (keyTipInfo.isEnabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val colorTokens = getContainerTokens(
        colors = skinColors,
        decorationAreaType = decorationAreaType,
        componentState = state,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted
    )
    val surfacePainter = painters.surfacePainter
    val outlinePainter = painters.outlinePainter
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
        val outlineInset = outlinePainter.getOutlineInset(InsetKind.Surface)
        val outlineFill = KeyTipOutlineSuppler.getOutline(
            layoutDirection = layoutDirection,
            density = this,
            size = Size(tipWidth, tipHeight),
            insets = outlineInset,
            radiusAdjustment = 0.0f,
            outlineKind = OutlineKind.Fill)

        paintSurface(
            drawScope = this,
            componentState = state,
            surfacePainter = surfacePainter,
            size = this.size,
            alpha = 1.0f,
            outline = outlineFill,
            colorTokens = colorTokens)

        paintOutline(
            drawScope = this,
            componentState = state,
            outlinePainter = outlinePainter,
            size = this.size,
            alpha = 1.0f,
            outlineSupplier = KeyTipOutlineSuppler,
            colorTokens = colorTokens)

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
                        r = colorTokens.onContainer.red,
                        g = colorTokens.onContainer.green,
                        b = colorTokens.onContainer.blue,
                        a = colorTokens.onContainer.alpha
                    )
                }
            )
        }
    }
}

private val KeyTipPaddingValues = PaddingValues(horizontal = 4.dp, vertical = 3.dp)
