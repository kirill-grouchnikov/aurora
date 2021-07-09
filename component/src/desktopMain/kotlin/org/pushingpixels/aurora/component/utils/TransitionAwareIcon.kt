package org.pushingpixels.aurora.component.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.pushingpixels.aurora.ColorSchemeAssociationKind
import org.pushingpixels.aurora.DecorationAreaType
import org.pushingpixels.aurora.ModelStateInfoSnapshot
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.utils.MutableColorScheme

/**
 * Icon with transition-aware capabilities. Has a delegate that does the actual
 * painting based on the transition color schemes.
 *
 * @author Kirill Grouchnikov
 */
class TransitionAwareIcon(
    val iconSize: Dp,
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val paintDelegate: (drawScope: DrawScope, iconSize: Dp, colorScheme: AuroraColorScheme) -> Unit,
    val density: Density
) : AuroraIcon {

    abstract class TransitionAwareIconFactory: AuroraIcon.Factory {
        override fun createNewIcon(): AuroraIcon {
            throw UnsupportedOperationException()
        }

        abstract fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon
    }

    private val iconWidth = iconSize
    private val iconHeight = iconSize

    private val mutableColorScheme = MutableColorScheme(
        displayName = "Internal mutable",
        isDark = false
    )

    override fun paintIcon(drawScope: DrawScope) {
        populateColorScheme(colorScheme = mutableColorScheme,
            modelStateInfo = modelStateInfoSnapshot,
            skinColors = skinColors,
            decorationAreaType = decorationAreaType,
            associationKind = ColorSchemeAssociationKind.Mark)
        paintDelegate.invoke(drawScope, iconSize, mutableColorScheme)
    }

    override fun getWidth(): Dp {
        return iconWidth
    }

    override fun getHeight(): Dp {
        return iconHeight
    }

    @Composable
    override fun setSize(width: Dp, height: Dp) {
        // This icon only "pretends" to be resizable
    }

    override fun setColorFilter(colorFilter: ((Color) -> Color)?) {
        // No op
    }
}