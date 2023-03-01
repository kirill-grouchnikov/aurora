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
package org.pushingpixels.aurora.component.popup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.theming.AuroraPainters
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.theming.colorscheme.AuroraColorSchemeBundle
import org.pushingpixels.aurora.theming.colorscheme.AuroraSkinColors
import java.awt.Component

internal val Color.awtColor: java.awt.Color
    get() = java.awt.Color(
        this.red, this.green, this.blue, this.alpha
    )

interface BaseCommandMenuHandler<in M : BaseCommandMenuContentModel,
        in P : BaseCommandPopupMenuPresentationModel> {
    fun showPopupContent(
        popupOriginator: Component,
        layoutDirection: LayoutDirection,
        density: Density,
        textStyle: TextStyle,
        fontFamilyResolver: FontFamily.Resolver,
        skinColors: AuroraSkinColors,
        colorSchemeBundle: AuroraColorSchemeBundle?,
        skinPainters: AuroraPainters,
        decorationAreaType: DecorationAreaType,
        compositionLocalContext: CompositionLocalContext,
        anchorBoundsInWindow: Rect,
        popupTriggerAreaInWindow: Rect,
        contentModel: State<M?>,
        presentationModel: P,
        displayPrototypeCommand: BaseCommand?,
        toDismissPopupsOnActivation: Boolean,
        popupPlacementStrategy: PopupPlacementStrategy,
        overlays: Map<Command, CommandButtonPresentationModel.Overlay>
    )
}

@Composable
fun Modifier.auroraPopupMenuRowBackground(
    backgroundFillColorQuery: (Int, AuroraColorScheme) -> Color,
    iconGutterFillColorQuery: ((AuroraColorScheme) -> Color)? = null,
    gutterWidth: Float = 0.0f,
): Modifier {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )

    val backgroundFill = backgroundFillColorQuery.invoke(0, backgroundColorScheme)
    val gutterFill = if (gutterWidth > 0) (iconGutterFillColorQuery?.invoke(backgroundColorScheme) ?: Color.Unspecified) else Color.Unspecified

    return this.then(
        PopupMenuRowBackground(
            backgroundFill = backgroundFill,
            gutterFill = gutterFill,
            gutterWidth = gutterWidth,
        )
    )
}

@Composable
fun Modifier.auroraPopupMenuRowBackground(
    backgroundFillColorQuery: (Int, AuroraColorScheme) -> Color,
    iconGutterFillColorQuery: ((AuroraColorScheme) -> Color)? = null,
    rowIndex: Int,
    gutterWidth: Float = 0.0f,
): Modifier {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )

    val backgroundFill = backgroundFillColorQuery.invoke(rowIndex, backgroundColorScheme)
    val gutterFill = if (gutterWidth > 0) (iconGutterFillColorQuery?.invoke(backgroundColorScheme) ?: Color.Unspecified) else Color.Unspecified

    return this.then(
        PopupMenuRowBackground(
            backgroundFill = backgroundFill,
            gutterFill = gutterFill,
            gutterWidth = gutterWidth,
        )
    )
}

private class PopupMenuRowBackground constructor(
    private val backgroundFill: Color,
    private val gutterFill: Color?,
    private val gutterWidth: Float,
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawRect(color = backgroundFill)

        // Have gutter?
        if (gutterWidth > 0) {
            if (layoutDirection == LayoutDirection.Ltr) {
                drawRect(color = gutterFill!!,
                    topLeft = Offset.Zero,
                    size = Size(width = gutterWidth, height = size.height))
            } else {
                drawRect(color = gutterFill!!,
                    topLeft = Offset(x = size.width - gutterWidth, y = 0.0f),
                    size = Size(width = gutterWidth, height = size.height))
            }
        }
        drawContent()
    }
}
