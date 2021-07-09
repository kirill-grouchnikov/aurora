package org.pushingpixels.aurora.component.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.colorscheme.AuroraColorScheme
import org.pushingpixels.aurora.colorscheme.AuroraSkinColors
import org.pushingpixels.aurora.common.HashMapKey
import org.pushingpixels.aurora.icon.AuroraIcon

/**
 * Icon with transition-aware capabilities. Has a delegate that does the actual painting based on
 * the transition color schemes.
 *
 * @author Kirill Grouchnikov
 */
class TransitionAwareIcon(
    val decorationAreaType: DecorationAreaType,
    val skinColors: AuroraSkinColors,
    val buttonBackgroundAppearanceStrategy: BackgroundAppearanceStrategy,
    val modelStateInfoSnapshot: ModelStateInfoSnapshot,
    val delegate: (AuroraColorScheme) -> ImageBitmap,
    val colorSchemeAssociationKindDelegate: ((ComponentState) -> ColorSchemeAssociationKind)?,
    val density: Density,
    val uniqueIconTypeId: String
) : AuroraIcon {

    abstract class TransitionAwareIconFactory: AuroraIcon.Factory {
        override fun createNewIcon(): AuroraIcon {
            throw UnsupportedOperationException()
        }

        abstract fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): AuroraIcon
    }

    private val markEnabledIcon = this.delegate.invoke(
        //ModelStateInfoSnapshot(ComponentState.ENABLED, emptyMap(), 1.0f),
        skinColors.getColorScheme(
            decorationAreaType,
            ColorSchemeAssociationKind.Mark, ComponentState.Enabled
        )
    )

    private val iconWidth = (markEnabledIcon.width / density.density).dp
    private val iconHeight = (markEnabledIcon.height / density.density).dp

    /**
     * Returns the current icon to paint.
     *
     * @return Icon to paint.
     */
    private val iconToPaint: ImageBitmap
        get() {
            val activeStates = modelStateInfoSnapshot.stateContributionMap
            var currState = modelStateInfoSnapshot.currModelState
            val buttonNeverPainted =
                (buttonBackgroundAppearanceStrategy == BackgroundAppearanceStrategy.Never)
            if (buttonNeverPainted) {
                if (currState.isFacetActive(ComponentStateFacet.Enable)) currState =
                    ComponentState.Enabled
            }
            val baseAssociationKind =
                colorSchemeAssociationKindDelegate?.invoke(currState)
                    ?: ColorSchemeAssociationKind.Mark
            val baseScheme =
                skinColors.getColorScheme(decorationAreaType, baseAssociationKind, currState)
            val baseAlpha: Float = skinColors.getAlpha(decorationAreaType, currState)
            val keyBase = HashMapKey(uniqueIconTypeId, baseScheme.displayName, baseAlpha)
            var layerBase = iconMap[keyBase]
            if (layerBase == null) {
                val baseFullOpacity = delegate.invoke(baseScheme)
                if (baseAlpha == 1.0f) {
                    layerBase = baseFullOpacity
                    iconMap[keyBase] = layerBase
                } else {
                    layerBase = ImageBitmap(
                        width = baseFullOpacity.width,
                        height = baseFullOpacity.height
                    )
                    val baseCanvas = Canvas(layerBase)
                    baseCanvas.drawImage(
                        image = baseFullOpacity,
                        topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                        paint = Paint().also { it.alpha = baseAlpha })

                    iconMap[keyBase] = layerBase
                }
            }
            if (currState.isDisabled || activeStates.size == 1 || buttonNeverPainted) {
                // Simple cases - disabled component, only one active state, or the button
                // never paints its background
                return layerBase
            }

            val result = ImageBitmap(width = layerBase.width, height = layerBase.height)
            val canvas = Canvas(result)

            // draw the base layer
            canvas.drawImage(
                image = layerBase,
                topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                paint = Paint()
            )

            // draw the other active layers
            for ((activeState, stateContribution) in activeStates) {
                if (activeState === currState) {
                    continue
                }
                if (stateContribution > 0.0f) {
                    val associationKind =
                        colorSchemeAssociationKindDelegate?.invoke(activeState)
                            ?: ColorSchemeAssociationKind.Mark
                    val scheme =
                        skinColors.getColorScheme(decorationAreaType, associationKind, activeState)
                    val alpha = skinColors.getAlpha(decorationAreaType, activeState)
                    val key = HashMapKey(uniqueIconTypeId, scheme.displayName, alpha)
                    var layer = iconMap.get(key)
                    if (layer == null) {
                        val fullOpacity = delegate.invoke(scheme)
                        if (alpha == 1.0f) {
                            layer = fullOpacity
                            iconMap[key] = layer
                        } else {
                            layer = ImageBitmap(
                                width = fullOpacity.width,
                                height = fullOpacity.height
                            )
                            val layerCanvas = Canvas(layer)
                            layerCanvas.drawImage(
                                image = fullOpacity,
                                topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                                paint = Paint().also { it.alpha = alpha })

                            iconMap[key] = layer
                        }
                    }
                    canvas.drawImage(
                        image = layer,
                        topLeftOffset = Offset(x = 0.0f, y = 0.0f),
                        paint = Paint().also { it.alpha = stateContribution })
                }
            }
            return result
        }

    override fun paintIcon(drawScope: DrawScope) {
        drawScope.drawImage(iconToPaint)
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
        // TODO - add functionality if this class still needs to exist
    }

    companion object {
        /**
         * Icon cache to speed up the subsequent icon painting. The basic assumption is that the
         * [.delegate] returns an icon that paints the same for the same parameters.
         */
        private val iconMap = hashMapOf<HashMapKey, ImageBitmap>()
    }
}