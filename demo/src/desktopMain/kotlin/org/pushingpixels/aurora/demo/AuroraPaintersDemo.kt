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
package org.pushingpixels.aurora.demo

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.common.interpolateTowards
import org.pushingpixels.aurora.common.withAlpha
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonSizingConstants
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.demo.svg.material.history_black_24dp
import org.pushingpixels.aurora.demo.svg.material.mail_outline_black_24dp
import org.pushingpixels.aurora.demo.svg.material.storage_24px
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.vaadin.paintbrush
import org.pushingpixels.aurora.demo.svg.vaadin.palete
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.painter.outline.AuroraOutlinePainter
import org.pushingpixels.aurora.theming.painter.outline.OutlineSupplier
import org.pushingpixels.aurora.theming.painter.surface.AuroraSurfacePainter
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*
import kotlin.math.abs
import kotlin.math.floor

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(600.dp, 320.dp)
    )
    var skin by remember { mutableStateOf(geminiSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        DemoPaintersContent({ skin = it }, resourceBundle)
    }
}


@Composable
fun AuroraWindowScope.DemoPaintersContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    var selected by remember { mutableStateOf(false) }
    var actionEnabled by remember { mutableStateOf(true) }
    var popupEnabled by remember { mutableStateOf(true) }
    var flat by remember { mutableStateOf(false) }

    val backgroundAppearanceStrategy by derivedStateOf {
        if (flat) BackgroundAppearanceStrategy.Flat
        else BackgroundAppearanceStrategy.Always
    }

    Row(modifier = Modifier.fillMaxSize().padding(4.dp)) {

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                AuroraDecorationArea(
                    decorationAreaType = DecorationAreaType.None,
                    buttonShaper = ClassicButtonShaper.Instance
                ) {
                    AuroraSkinSwitcher(onSkinChange = onSkinChange)

                    Spacer(modifier = Modifier.width(8.dp))

                    AuroraLocaleSwitcher(resourceBundle)
                }
            }

            DemoHeader(
                resourceBundle.getString("Group.colortokenoverlays"),
                palete(),
                true
            )

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.success"),
                        icon = history_black_24dp(),
                        action = { println("Success!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Success
                            ),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.warning"),
                        icon = storage_24px(),
                        action = { println("Warning!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Warning
                            ),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.error"),
                        icon = mail_outline_black_24dp(),
                        action = { println("Error!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Error
                            ),
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()

                Spacer(modifier = Modifier.width(24.dp))

                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.success"),
                        icon = history_black_24dp(),
                        action = { println("Success!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Success
                            ),
                        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.warning"),
                        icon = storage_24px(),
                        action = { println("Warning!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Warning
                            ),
                        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
                Spacer(modifier = Modifier.width(8.dp))
                CommandButtonProjection(
                    contentModel = Command(
                        text = resourceBundle.getString("Status.error"),
                        icon = mail_outline_black_24dp(),
                        action = { println("Error!") },
                        isActionEnabled = actionEnabled
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        colorTokensOverlayProvider =
                            ContainerColorTokensOverlay.defaultSystemOverlayProvider(
                                systemContainerType = SystemContainerType.Error
                            ),
                        backgroundAppearanceStrategy = backgroundAppearanceStrategy,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
            }


            DemoHeader(
                resourceBundle.getString("Group.painteroverlays"),
                paintbrush(),
                true
            )

            Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
                StaticSurfacePainterOverlay {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = resourceBundle.getString("Control.button.static"),
                            action = { println("Clicked!") },
                            isActionEnabled = actionEnabled
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
                Spacer(modifier = Modifier.width(8.dp))
                StaticSurfacePainterOverlay {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = resourceBundle.getString("Control.button.static"),
                            action = { println("Clicked!") },
                            isActionEnabled = actionEnabled,
                            isActionToggle = true,
                            isActionToggleSelected = true
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
                Spacer(modifier = Modifier.width(8.dp))
                AnimatedSurfacePainterOverlay {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = resourceBundle.getString("Control.button.animated"),
                            action = { println("Clicked!") },
                            isActionEnabled = actionEnabled
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
                Spacer(modifier = Modifier.width(8.dp))
                AnimatedArrowsSurfacePainterOverlay {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = resourceBundle.getString("Control.button.animated"),
                            action = { println("Clicked!") },
                            isActionEnabled = actionEnabled
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
                Spacer(modifier = Modifier.width(8.dp))
                AnimatedOutlinePainterOverlay {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = resourceBundle.getString("Control.button.animated"),
                            action = { println("Clicked!") },
                            isActionEnabled = actionEnabled
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
                            contentPadding = CommandButtonSizingConstants.WideButtonContentPadding,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
            }
        }
    }
}

@Composable
private fun StaticSurfacePainterOverlay(
    content: @Composable () -> Unit
) {
    val surfacePainterOverlay = object: AuroraSurfacePainter.Overlay {
        override fun paintSurfaceOverlay(
            drawScope: DrawScope,
            size: Size,
            outline: Outline,
            colorTokens: ContainerColorTokens,
            alpha: Float
        ) {
            with (drawScope) {
                val clipPath = Path()
                clipPath.addOutline(outline)
                clipPath(path = clipPath) {
                    val start1 = colorTokens.containerSurfaceLow
                    val start2 = colorTokens.containerSurface
                    val end1 = colorTokens.containerSurface
                    val end2 = colorTokens.containerSurfaceHigh

                    val cellDim = 8.0f * density

                    val rows = (this.size.height / cellDim).toInt()
                    val columns = (this.size.width / cellDim).toInt()

                    if ((rows == 0) || (columns == 0)) {
                        return
                    }

                    for (col in 0..columns) {
                        val columnFactor = col.toFloat() / columns.toFloat()
                        val alt1 = start1.interpolateTowards(end1, 1.0f - columnFactor)
                        val alt2 = start2.interpolateTowards(end2, 1.0f - columnFactor)

                        for (row in 0..rows) {
                            val cellColor = if ((col + row) % 2 == 0) alt1 else alt2
                            drawRect(
                                color = cellColor,
                                topLeft = Offset(x = col * cellDim, y = row * cellDim),
                                size = Size(cellDim, cellDim)
                            )
                        }
                    }
                }
            }
        }
    }

    AuroraPainterOverlays(
        painterOverlays = AuroraPainterOverlays(surfacePainterOverlay = surfacePainterOverlay),
        content = content
    )
}

@Composable
private fun AnimatedSurfacePainterOverlay(
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "animated dots")
    val animationPosition by infiniteTransition.animateFloat(
        initialValue = -4.0f,
        targetValue = 5.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart),
        label = "animationPosition"
    )

    val surfacePainterOverlay = object: AuroraSurfacePainter.Overlay {
        override fun paintSurfaceOverlay(
            drawScope: DrawScope,
            size: Size,
            outline: Outline,
            colorTokens: ContainerColorTokens,
            alpha: Float
        ) {
            with (drawScope) {
                val clipPath = Path()
                clipPath.addOutline(outline)
                clipPath(path = clipPath) {
                    val start = if (colorTokens.isDark) {
                        colorTokens.containerSurfaceHighest
                    } else {
                        colorTokens.containerSurfaceLowest
                    }
                    val end = if (colorTokens.isDark) {
                        colorTokens.containerSurfaceLowest
                    } else {
                        colorTokens.containerSurfaceHighest
                    }

                    val cellDim = 6.0f * density
                    val dotDiameter = 3.0f * density

                    val rows = (this.size.height / cellDim).toInt()
                    val columns = (this.size.width / cellDim).toInt()

                    if ((rows == 0) || (columns == 0)) {
                        return
                    }

                    for (col in 0..columns) {
                        val columnFactor = col.toFloat() / columns.toFloat()
                        val intensity = abs(columnFactor - animationPosition).coerceIn(0.0f, 1.0f)
                        val cellColor = start.interpolateTowards(end, 1.0f - intensity)
                        val dotCenterX = (col + 0.6f) * cellDim

                        for (row in 0..rows) {
                            val dotCenterY = (row + (if (col % 2 == 0) 0.5f else 0.0f)) * cellDim

                            drawCircle(
                                color = cellColor,
                                radius = dotDiameter / 2.0f,
                                center = Offset(dotCenterX, dotCenterY)
                            )
                        }
                    }
                }
            }
        }
    }

    AuroraPainterOverlays(
        painterOverlays = AuroraPainterOverlays(surfacePainterOverlay = surfacePainterOverlay),
        content = content
    )
}

@Composable
private fun AnimatedArrowsSurfacePainterOverlay(
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "animated arrows")
    val animationPosition by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart),
        label = "animationPosition"
    )

    val ArrowMask = arrayOf(
        booleanArrayOf(true, true, true, false, false),
        booleanArrayOf(false, true, true, true, false),
        booleanArrayOf(false, false, true, true, true),
        booleanArrayOf(false, true, true, true, false),
        booleanArrayOf(true, true, true, false, false),
    )

    val ArrowSize = 5
    val ArrowGap = 2
    val Rows = 7

    val offColors = arrayOfNulls<Color>(Rows)
    val onColors = arrayOfNulls<Color>(Rows)

    val surfacePainterOverlay = object: AuroraSurfacePainter.Overlay {
        override fun paintSurfaceOverlay(
            drawScope: DrawScope,
            size: Size,
            outline: Outline,
            colorTokens: ContainerColorTokens,
            alpha: Float
        ) {
            with (drawScope) {

                val clipPath = Path()
                clipPath.addOutline(outline)
                clipPath(path = clipPath) {
                    val dotSize = size.height / (2.0f * Rows + 1)
                    val columns = floor((size.width - 2 * dotSize) / dotSize).toInt()
                    val verticalMargin = dotSize
                    val horizontalMargin = (size.width - columns * dotSize) / 2.0f

                    if (columns == 0) {
                        return
                    }

                    val offTop = if (colorTokens.isDark) {
                        colorTokens.containerSurfaceHighest
                    } else {
                        colorTokens.containerSurfaceLowest
                    }
                    val offBottom = colorTokens.containerSurface
                    val on = colorTokens.onContainerVariant

                    for (row in 0..<Rows) {
                        val rowFactor = row.toFloat() / Rows.toFloat()
                        offColors[row] = offTop.interpolateTowards(offBottom, 1.0f - rowFactor)
                        onColors[row] = offColors[row]!!.interpolateTowards(on, 0.666f)
                    }

                    val firstArrowColumn = (animationPosition * (ArrowSize + ArrowGap)).toInt()
                    for (col in 0..<columns) {
                        val columnWithinArrow = (ArrowSize + ArrowGap + col - firstArrowColumn) %
                                (ArrowSize + ArrowGap)
                        val isInVerticalGap = (columnWithinArrow >= ArrowSize)

                        val dotCenterX = horizontalMargin + 2 * (col + 0.5f) * dotSize

                        for (row in 0..<Rows) {
                            var isInArrow = false
                            if (!isInVerticalGap) {
                                isInArrow = (row >= 1) && (row < (Rows - 1)) &&
                                        ArrowMask[row - 1][columnWithinArrow]
                            }

                            val cellColor = if (isInArrow) onColors[row] else offColors[row]

                            val dotCenterY = verticalMargin +
                                    2 * (row + 0.5f) * dotSize - 0.5f * dotSize

                            drawCircle(
                                color = cellColor!!,
                                radius = dotSize / 2.0f,
                                center = Offset(dotCenterX, dotCenterY)
                            )
                        }
                    }
                }
            }
        }
    }

    AuroraPainterOverlays(
        painterOverlays = AuroraPainterOverlays(surfacePainterOverlay = surfacePainterOverlay),
        content = content
    )
}

@Composable
private fun AnimatedOutlinePainterOverlay(
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "animated outline")
    val animationPosition by infiniteTransition.animateFloat(
        initialValue = -2.0f,
        targetValue = 3.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart),
        label = "animationPosition"
    )

    val density = LocalDensity.current
    val outlinePainterOverlay = object: AuroraOutlinePainter.Overlay {
        override fun paintOutlineOverlay(
            drawScope: DrawScope,
            size: Size,
            outlineSupplier: OutlineSupplier,
            colorTokens: ContainerColorTokens,
            alpha: Float
        ) {
            with (drawScope) {
                val intensity = 1.0f - (2.0f * abs(0.5f - animationPosition)).coerceIn(0.0f, 1.0f)
                val accented = colorTokens.accentOnContainer
                val outlineColor = accented.withAlpha(intensity)

                val outline = outlineSupplier.getOutline(
                    layoutDirection = layoutDirection,
                    size = this.size,
                    density = density,
                    insets = 1.0f,
                    radiusAdjustment = 0.0f,
                    outlineKind = OutlineKind.Outline
                )

                drawOutline(
                    outline = outline,
                    style = Stroke(width = 2.0f),
                    color = outlineColor,
                    alpha = alpha
                )
            }
        }
    }

    AuroraPainterOverlays(
        painterOverlays = AuroraPainterOverlays(outlinePainterOverlay = outlinePainterOverlay),
        content = content
    )
}
