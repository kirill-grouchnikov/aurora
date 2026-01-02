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
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.common.*
import org.pushingpixels.aurora.component.model.ContentModel
import org.pushingpixels.aurora.component.model.PresentationModel
import org.pushingpixels.aurora.component.projection.Projection
import org.pushingpixels.aurora.component.ribbon.Ribbon
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.utils.*

@AuroraInternalApi
object KeyTipTracker {
    data class KeyTipLink(
        val projection: Projection<ContentModel, PresentationModel>,
        val keyTip: String,
        val isEnabled: Boolean,
        val isInPopup: Boolean,
        var screenRect: AuroraRect,
        var anchor: Offset,
        var onActivated: (() -> Unit)?,
        val chainRoot: Any?,
        val chainRootKeyTip: String?,
        val traversal: Any?,
    )

    data class KeyTipChain(
        val links: List<KeyTipLink>,
        val keyTipLookupIndex: Int = 0,
    ) {
//        fun dump() {
//            println("Chain with ${this.links.size} links")
//            println("\t ${this.links.joinToString { it.keyTip }}")
//        }
    }

    private val keyTips: MutableList<KeyTipLink> = arrayListOf()

    private val keyTipChains: MutableList<KeyTipChain> = arrayListOf()

    private val chainRoots: MutableList<Any> = arrayListOf()

    fun trackKeyTipInPopup(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        onActivated: (() -> Unit)?,
        chainRoot: Any?,
        chainRootKeyTip: String?,
        traversal: Any?,
    ) {
        //println("Tracking $keyTip from $chainRootKeyTip")
        val existing = keyTips.find {
            (it.projection == projection) && (it.keyTip == keyTip)
        }
        if (existing == null) {
            keyTips.add(
                KeyTipLink(
                    projection = projection,
                    keyTip = keyTip,
                    isEnabled = isEnabled,
                    isInPopup = true,
                    screenRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
                    anchor = Offset.Zero,
                    onActivated = onActivated,
                    chainRoot = chainRoot,
                    chainRootKeyTip = chainRootKeyTip,
                    traversal = traversal,
                )
            )
        }
    }

    fun trackKeyTipBase(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        isInPopup: Boolean,
        screenRect: AuroraRect,
        chainRoot: Any?,
        chainRootKeyTip: String?,
        traversal: Any?,
    ) {
        //println("Tracking $keyTip from $chainRootKeyTip")
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
                    isInPopup = isInPopup,
                    screenRect = screenRect,
                    anchor = Offset.Zero,
                    onActivated = null,
                    chainRoot = chainRoot,
                    chainRootKeyTip = chainRootKeyTip,
                    traversal = traversal,
                )
            )
        }
    }

    fun trackKeyTipOffset(
        projection: Projection<ContentModel, PresentationModel>,
        keyTip: String,
        isEnabled: Boolean,
        isInPopup: Boolean,
        anchor: Offset,
        onActivated: (() -> Unit)?,
        chainRoot: Any?,
        chainRootKeyTip: String?,
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
                    isInPopup = isInPopup,
                    screenRect = AuroraRect(0.0f, 0.0f, 0.0f, 0.0f),
                    anchor = anchor.copy(),
                    onActivated = onActivated,
                    chainRoot = chainRoot,
                    chainRootKeyTip = chainRootKeyTip,
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
        return currentlyShownKeyTipChain.value
    }

    fun isShowingKeyTips(): Boolean = keyTipChains.isNotEmpty()

    fun showPreviousChain() {
        if (keyTipChains.isEmpty()) {
            return
        }
        keyTipChains.removeLast()
        currentlyShownKeyTipChain.value = if (keyTipChains.isNotEmpty()) keyTipChains.last() else null
        chainRoots.removeLast()
        visibleFlow.value = keyTipChains.isNotEmpty()
        chainDepthFlow.value--
        //println("Going back one at new depth ${chainDepthFlow.value}")
    }

    fun hideAllKeyTips() {
        keyTipChains.clear()
        currentlyShownKeyTipChain.value = null
        chainRoots.clear()
        visibleFlow.value = false
        chainDepthFlow.value = 0
        //println("Cleared all key tips, depth ${chainDepthFlow.value}")
    }

    fun showRootKeyTipChain(ribbon: Ribbon) {
        val rootKeyTipChain = KeyTipChain(links = keyTips.filter { it.chainRoot == ribbon })
        keyTipChains.add(rootKeyTipChain)
        currentlyShownKeyTipChain.value = rootKeyTipChain
        chainRoots.add(ribbon)
        visibleFlow.value = true
        chainDepthFlow.value = 1
    }

    fun handleKeyPress(coroutineScope: CoroutineScope, char: Char) {
        if (!isShowingKeyTips()) {
            return
        }
        //println("Processing $char at depth ${chainDepthFlow.value}")
        val currChain = currentlyShownKeyTipChain.value!!
        val currChainRoot = chainRoots.last()

        // Go over the key tip links and see if there is an exact match
        for (link in currChain.links) {
            val keyTipString = link.keyTip
            if ((char.lowercaseChar() == keyTipString[currChain.keyTipLookupIndex].lowercaseChar()) &&
                (keyTipString.length == (currChain.keyTipLookupIndex + 1))) {
                // exact match
                if (link.isEnabled) {
                    link.onActivated?.invoke()
                    if (link.traversal != null) {
                        coroutineScope.launch {
                            delay(100)
                            val nextChainRoot = link.traversal
//                            println("All tips = ${keyTips.size} elements")
//                            println("\t ${keyTips.joinToString { it.keyTip + "[" + it.chainRoot!!.javaClass.simpleName + "@" + it.chainRoot.hashCode() + "]" }}")

                            val newKeyTipChain = KeyTipChain(links = keyTips.filter { it.chainRootKeyTip == link.keyTip })
                            keyTipChains.add(newKeyTipChain)
                            currentlyShownKeyTipChain.value = newKeyTipChain
                            chainRoots.add(nextChainRoot)
                            chainDepthFlow.value++
//                            println("Going to next root ${nextChainRoot.javaClass.simpleName}@${nextChainRoot.hashCode()} at new depth ${chainDepthFlow.value}")
//                            newKeyTipChain.dump()
                        }
                    } else {
                        // Match found and activated, and no further traversal available
                        // a) Dismiss all key tip chains
                        hideAllKeyTips()
                        // b) hide all popups
                        AuroraPopupManager.hidePopups(null)
                    }
                }
                return
            }
        }

        // go over the key tip links and look for key tips that have
        // the specified character as the prefix
        if (currChain.keyTipLookupIndex == 0) {
            val secondaryKeyTipChain = KeyTipChain(
                links = currChain.links.filter {
                    it.keyTip[0].lowercaseChar() == char.lowercaseChar()
                },
                keyTipLookupIndex = 1
            )
            coroutineScope.launch {
                delay(100)
//                        val nextChainRoot = link.traversal
                keyTipChains.add(secondaryKeyTipChain)
                currentlyShownKeyTipChain.value = secondaryKeyTipChain
//                        chainRoots.add(chainRoots.)
//                        chainDepthFlow.value++
            }
        }
    }

    private val visibleFlow = MutableStateFlow(false)
    val uiVisibleFlow: StateFlow<Boolean> = visibleFlow
    private val chainDepthFlow = MutableStateFlow(0)
    val uiChainDepthFlow: StateFlow<Int> = chainDepthFlow
    private val currentlyShownKeyTipChain = MutableStateFlow<KeyTipTracker.KeyTipChain?>(null)
    val uiCurrentlyShownKeyTipChain = currentlyShownKeyTipChain
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
    val textMeasurer = rememberTextMeasurer(cacheSize = 50)

    val visibilityState by KeyTipTracker.uiVisibleFlow.collectAsState()
    val chainDepth by KeyTipTracker.uiChainDepthFlow.collectAsState()
    val currentlyShownKeyTipChain by KeyTipTracker.uiCurrentlyShownKeyTipChain.collectAsState()

    if (visibilityState && (chainDepth > 0)) {
        Canvas(modifier = modifier) {
            if (currentlyShownKeyTipChain != null) {
//                currentlyShownKeyTipChain!!.dump()
                for (tracked in currentlyShownKeyTipChain!!.links) {
                    if (!tracked.screenRect.isEmpty && !tracked.isInPopup) {
                        drawKeyTip(
                            tracked,
                            textStyle,
                            density,
                            textMeasurer,
                            fontFamilyResolver,
                            layoutDirection,
                            insets,
                            decorationAreaType,
                            skinColors,
                            null,
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
    textMeasurer: TextMeasurer,
    fontFamilyResolver: FontFamily.Resolver,
    layoutDirection: LayoutDirection,
    insets: Dp,
    decorationAreaType: DecorationAreaType,
    skinColors: AuroraSkinColors,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
    painters: AuroraPainters
) {
    val leftPadding = KeyTipPaddingValues.calculateLeftPadding(layoutDirection)
    val topPadding = KeyTipPaddingValues.calculateTopPadding()

    val state = if (keyTipInfo.isEnabled) ComponentState.Enabled else ComponentState.DisabledUnselected
    val colorTokens = getContainerTokens(
        colors = skinColors,
        tokensOverlayProvider = tokensOverlayProvider,
        decorationAreaType = decorationAreaType,
        componentState = state,
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
        inactiveContainerType = ContainerType.Muted
    )
    val surfacePainter = painters.surfacePainter
    val outlinePainter = painters.outlinePainter

    val tipSizingInfo = getKeyTipSize(keyTipInfo.keyTip, textStyle, density, fontFamilyResolver, layoutDirection)
    val tipWidth = tipSizingInfo.first.width
    val tipHeight = tipSizingInfo.first.height

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
            outlineKind = OutlineKind.Surface)

        paintSurface(
            drawScope = this,
            componentState = state,
            surfacePainter = surfacePainter,
            surfacePainterOverlay = null,
            size = Size(tipWidth, tipHeight),
            alpha = 1.0f,
            outline = outlineFill,
            colorTokens = colorTokens)

        paintOutline(
            drawScope = this,
            componentState = state,
            outlinePainter = outlinePainter,
            outlinePainterOverlay = null,
            size = Size(tipWidth, tipHeight),
            alpha = 1.0f,
            outlineSupplier = KeyTipOutlineSuppler,
            colorTokens = colorTokens)

        val keyTipTextColor = if (!state.isDisabled) colorTokens.onContainer
            else colorTokens.onContainer.withAlpha(colorTokens.onContainerDisabledAlpha)
        drawText(
            textMeasurer = textMeasurer,
            text = keyTipInfo.keyTip,
            topLeft = Offset(leftPadding.toPx(), topPadding.toPx()),
            style = textStyle.copy(color = keyTipTextColor),
            overflow = TextOverflow.Visible,
            softWrap = false,
            maxLines = 1,
        )
    }
}

private val KeyTipPaddingValues = PaddingValues(horizontal = 4.dp, vertical = 3.dp)
