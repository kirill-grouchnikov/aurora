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

package org.pushingpixels.aurora.window

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import org.pushingpixels.aurora.common.AuroraInternalApi
import org.pushingpixels.aurora.common.AuroraPopupManager
import org.pushingpixels.aurora.common.AuroraSwingPopupMenu
import org.pushingpixels.aurora.common.Platform
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.utils.TransitionAwarePainter
import org.pushingpixels.aurora.component.utils.TransitionAwarePainterDelegate
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.AuroraButtonShaper
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.theming.utils.ContainerType
import org.pushingpixels.aurora.theming.utils.getContainerColorTokensFilter
import java.awt.*
import java.awt.event.AWTEventListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.event.WindowEvent
import java.util.*
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.roundToInt

object WindowSizingConstants {
    val DecoratedBorderThickness = 4.dp

    // The amount of space that the cursor is changed on.
    val CornerDragWidth = 16.dp

    // Region from edges that dragging is active from.
    val BorderDragThickness = 5.dp
}

object WindowTitlePaneSizingConstants {
    // The height of the title pane
    val MinimumTitlePaneHeight = 32.dp

    // Title pane content padding (for the area that hosts the title text and the buttons)
    val TitlePaneContentPadding = PaddingValues(start = 8.dp, end = 8.dp)

    // Icon size for each title pane control button (minimize, maximize, etc)
    val TitlePaneButtonIconSize = 18.dp

    // Icon size for the app icon
    val TitlePaneAppIconSize = 16.dp

    // Gap between minimize and maximize / restore buttons
    val TitlePaneButtonIconRegularGap = 4.dp

    // Gap between maximize / restore and close buttons
    val TitlePaneButtonIconLargeGap = 8.dp

    // Content padding for each title pane control button
    val TitlePaneButtonContentPadding =
        PaddingValues(start = 0.dp, end = 1.dp, top = 1.dp, bottom = 2.dp)
}

private val TitlePaneButtonPresentationModel = CommandButtonPresentationModel(
    presentationState = CommandButtonPresentationState.Small,
    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
    contentPadding = WindowTitlePaneSizingConstants.TitlePaneButtonContentPadding,
    horizontalGapScaleFactor = 1.0f,
    verticalGapScaleFactor = 1.0f,
    iconEnabledFilterStrategy = IconFilterStrategy.Original
)

@Composable
fun AuroraWindowScope.AuroraWindowTitlePaneButton(titlePaneCommand: Command,
    tokensOverlayProvider: ContainerColorTokensOverlay.Provider? = null) {
    CommandButtonProjection(
        contentModel = titlePaneCommand,
        presentationModel = TitlePaneButtonPresentationModel.copy(
            colorTokensOverlayProvider = tokensOverlayProvider)
    ).project()
}

@Composable
fun AuroraWindowScope.AuroraWindowTitlePaneTitleText(
    titleTextConfiguration: AuroraWindowTitlePaneConfigurations.TitlePaneTitleTextConfiguration,
    title: String
) {
    val skinColors = AuroraSkin.colors

    val colorTokens =
        skinColors.getNeutralContainerTokens(DecorationAreaType.TitlePane)
    val titleTextStyle = titleTextConfiguration.getTitleTextStyle(colorTokens)
    LabelProjection(
        contentModel = LabelContentModel(text = title),
        presentationModel = LabelPresentationModel(
            textStyle = titleTextStyle,
            textMaxLines = 1,
            textOverflow = TextOverflow.Ellipsis
        )
    ).project()
}

@Composable
internal fun AuroraWindowScope.WindowTitlePaneTextAndIcon(
    titleTextConfiguration: AuroraWindowTitlePaneConfigurations.TitlePaneTitleTextConfiguration,
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain
) {
    val layoutDirection = LocalLayoutDirection.current

    val skinColors = AuroraSkin.colors
    val iconHorizontalGravity = windowConfiguration.getTitlePaneIconGravity()
    val showsIcon = (icon != null) && (iconHorizontalGravity != TitleIconHorizontalGravity.None)

    Layout(modifier = Modifier.fillMaxWidth(),
        content = {
            if (showsIcon) {
                val colorTokens = skinColors.getNeutralContainerTokens(DecorationAreaType.TitlePane)
                val colorFilter: ColorFilter? = when (iconFilterStrategy) {
                    IconFilterStrategy.ThemedFollowText ->
                        ColorFilter.tint(color = colorTokens.onContainer)

                    IconFilterStrategy.ThemedFollowColorTokens ->
                        getContainerColorTokensFilter(colorTokens)

                    IconFilterStrategy.Original -> null
                }
                Box(
                    modifier = Modifier.size(WindowTitlePaneSizingConstants.TitlePaneAppIconSize)
                        .paint(painter = icon, colorFilter = colorFilter)
                )
            }

            AuroraWindowTitlePaneTitleText(titleTextConfiguration = titleTextConfiguration, title = title)
        }) { measurables, constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val titleTextHorizontalGravity = windowConfiguration.getTitlePaneTextGravity()

        val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()
        val iconSizePx = WindowTitlePaneSizingConstants.TitlePaneAppIconSize.toPx().roundToInt()
        val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
        val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()
        val controlButtonsWidth = 3 * buttonSizePx + regularGapPx + largeGapPx
        val fullTitleWidth = width + controlButtonsWidth

        val iconMeasurable = if (showsIcon) measurables[0] else null
        val titleMeasurable = if (showsIcon) measurables[1] else measurables[0]

        val iconPlaceable = iconMeasurable?.measure(Constraints.fixed(width = iconSizePx, height = iconSizePx))
        val maxTitleWidth = kotlin.math.max(
            0, when (titleTextHorizontalGravity) {
                // Centered - the available horizontal space is determined from the center of the
                // whole title pane outwards to the left and right edge, which is effectively bounded
                // by the horizontal space taken by the control buttons
                HorizontalGravity.Centered -> width - 2 * controlButtonsWidth
                // Leading or trailing - whatever horizontal space is left in this container after the icon
                else -> width - buttonSizePx
            }
        )
        val titlePlaceable = titleMeasurable.measure(
            Constraints(
                minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0, maxHeight = height
            )
        )

        layout(width = width, height = height) {
            val ltr = (layoutDirection == LayoutDirection.Ltr)
            val controlButtonsOnRight = windowConfiguration.areTitlePaneControlButtonsOnRight(layoutDirection)

            when (titleTextHorizontalGravity) {
                HorizontalGravity.Centered -> {
                    val titleX = (fullTitleWidth - titlePlaceable.width) / 2
                    titlePlaceable.place(titleX, (height - titlePlaceable.height) / 2)
                    if (iconPlaceable != null) {
                        val iconX = when (iconHorizontalGravity) {
                            TitleIconHorizontalGravity.NextToTitle -> if (ltr) titleX - iconPlaceable.width else titleX + titlePlaceable.width
                            TitleIconHorizontalGravity.OppositeControlButtons -> if (controlButtonsOnRight) 0 else width - iconPlaceable.width
                            else -> 0
                        }
                        iconPlaceable.place(iconX, (height - iconPlaceable.height) / 2)
                    }
                }

                HorizontalGravity.Leading -> {
                    if (iconPlaceable == null) {
                        val titleX = if (ltr) 0 else width - titlePlaceable.width
                        titlePlaceable.place(titleX, (height - titlePlaceable.height) / 2)
                    } else {
                        val iconX: Int
                        val titleX: Int
                        // I for icon, B for control buttons block in the layout diagrams
                        if (ltr) {
                            if (controlButtonsOnRight) {
                                // No matter what the icon horizontal gravity
                                // | I | Title     | B |
                                iconX = 0
                                titleX = iconPlaceable.width
                            } else {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // | B | I | Title     |
                                    iconX = 0
                                    titleX = iconPlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | B | Title     | I |
                                    iconX = width - iconPlaceable.width
                                    titleX = 0
                                }
                            }
                        } else {
                            if (!controlButtonsOnRight) {
                                // No matter what the icon horizontal gravity
                                // | B |     Title | I |
                                iconX = width - iconPlaceable.width
                                titleX = iconX - titlePlaceable.width
                            } else {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // |     Title | I | B |
                                    iconX = width - iconPlaceable.width
                                    titleX = iconX - titlePlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | I |     Title | B |
                                    iconX = 0
                                    titleX = width - titlePlaceable.width
                                }
                            }
                        }
                        iconPlaceable.place(iconX, (height - iconPlaceable.height) / 2)
                        titlePlaceable.place(titleX, (height - titlePlaceable.height) / 2)
                    }
                }

                HorizontalGravity.Trailing -> {
                    if (iconPlaceable == null) {
                        val titleX = if (ltr) width - titlePlaceable.width else 0
                        titlePlaceable.place(titleX, (height - titlePlaceable.height) / 2)
                    } else {
                        val iconX: Int
                        val titleX: Int
                        // I for icon, B for control buttons block in the layout diagrams
                        if (ltr) {
                            if (controlButtonsOnRight) {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // |      | I | Title | B |
                                    titleX = width - titlePlaceable.width
                                    iconX = titleX - iconPlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | I |       Title | B |
                                    titleX = width - titlePlaceable.width
                                    iconX = 0
                                }
                            } else {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // | B |      | I | Title |
                                    titleX = width - titlePlaceable.width
                                    iconX = titleX - iconPlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | B |       Title | I |
                                    iconX = width - iconPlaceable.width
                                    titleX = iconX - titlePlaceable.width
                                }
                            }
                        } else {
                            if (!controlButtonsOnRight) {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // | B | Title | I |    |
                                    titleX = 0
                                    iconX = titlePlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | B | Title     | I |
                                    titleX = 0
                                    iconX = width - iconPlaceable.width
                                }
                            } else {
                                if (iconHorizontalGravity == TitleIconHorizontalGravity.NextToTitle) {
                                    // | Title | I |    | B |
                                    titleX = 0
                                    iconX = titlePlaceable.width
                                } else {
                                    // Icon horizontal gravity is OppositeControlButtons
                                    // | I | Title     | B |
                                    iconX = 0
                                    titleX = iconPlaceable.width
                                }
                            }
                        }
                        iconPlaceable.place(iconX, (height - iconPlaceable.height) / 2)
                        titlePlaceable.place(titleX, (height - titlePlaceable.height) / 2)
                    }
                }

                else -> {
                    // Can't get here
                }
            }
        }
    }
}

@Composable
private fun AuroraWindowScope.WindowPlainTitlePane(
    titleTextConfiguration: AuroraWindowTitlePaneConfigurations.TitlePaneTitleTextConfiguration,
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowConfiguration: AuroraWindowTitlePaneConfigurations.AuroraPlain
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val extendedState = (window as? Frame)?.extendedState
    val isMaximized =
        remember { mutableStateOf(((extendedState != null) && ((extendedState and Frame.MAXIMIZED_BOTH) != 0))) }
    val skinColors = AuroraSkin.colors

    AuroraDecorationArea(
        decorationAreaType = DecorationAreaType.TitlePane,
        buttonShaper = ClassicButtonShaper.Instance
    ) {
        Layout(
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowTitlePaneSizingConstants.MinimumTitlePaneHeight)
                .auroraBackground()
                .padding(WindowTitlePaneSizingConstants.TitlePaneContentPadding),
            content = {
                WindowDraggableArea(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {
                    WindowTitlePaneTextAndIcon(
                        titleTextConfiguration = titleTextConfiguration,
                        title = title,
                        icon = icon,
                        iconFilterStrategy = iconFilterStrategy,
                        windowConfiguration = windowConfiguration
                    )
                }

                // Minimize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.extendedState = JFrame.ICONIFIED
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .iconifyButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density
                            )
                        }
                    }),
                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider
                        .getContainerColorTokensOverlayProvider())

                // Maximize / Unmaximize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        val current = (window as? Frame)
                        if (current != null) {
                            if (current.extendedState == JFrame.MAXIMIZED_BOTH) {
                                current.extendedState = JFrame.NORMAL
                            } else {
                                // Note that on some older releases of Java, maximizing a custom-decorated frame
                                // results in that frame overlapping the system taskbar. This behavior has been
                                // fixed in Java 15 with https://bugs.openjdk.java.net/browse/JDK-8176359,
                                // https://bugs.openjdk.java.net/browse/JDK-8231564 and
                                // https://bugs.openjdk.java.net/browse/JDK-8243925.
                                // In addition, https://bugs.openjdk.org/browse/JDK-8231564 backported it to
                                // earlier Java versions.
                                // Since there is no reliable way to detect whether the current runtime has a fix
                                // for this issue, do not try to work around it. If your application is running
                                // into this issue, you will need to use a version of Java that has the fix for it.
                                current.extendedState = JFrame.MAXIMIZED_BOTH
                            }
                            isMaximized.value = !isMaximized.value
                        }
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return if (isMaximized.value) {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .restoreButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            } else {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .maximizeButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            }
                        }
                    }),
                    tokensOverlayProvider = if (isMaximized.value) {
                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider
                    } else {
                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider
                    }.getContainerColorTokensOverlayProvider())

                // Close button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.dispatchEvent(
                            WindowEvent(
                                window,
                                WindowEvent.WINDOW_CLOSING
                            )
                        )
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .closeButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.closeButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density,
                            )
                        }
                    }),
                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.closeButtonProvider
                        .getContainerColorTokensOverlayProvider())
            }) { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()

            val buttonMeasureSpec = Constraints.fixed(width = buttonSizePx, height = buttonSizePx)

            var childIndex = 0

            val titleBoxMeasurable = measurables[childIndex++]
            val minimizeButtonMeasurable = measurables[childIndex++]
            val maximizeButtonMeasurable = measurables[childIndex++]
            val closeButtonMeasurable = measurables[childIndex]

            val minimizeButtonPlaceable = minimizeButtonMeasurable.measure(buttonMeasureSpec)
            val maximizeButtonPlaceable = maximizeButtonMeasurable.measure(buttonMeasureSpec)
            val closeButtonPlaceable = closeButtonMeasurable.measure(buttonMeasureSpec)

            val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
            val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()

            val titleWidth = kotlin.math.max(
                0, width -
                        (minimizeButtonPlaceable.width + regularGapPx +
                                maximizeButtonPlaceable.width + largeGapPx +
                                closeButtonPlaceable.width)
            )

            val titleBoxPlaceable = titleBoxMeasurable.measure(
                Constraints.fixed(width = titleWidth, height = height)
            )

            layout(width = width, height = height) {
                val controlButtonsOnRight = windowConfiguration.areTitlePaneControlButtonsOnRight(layoutDirection)

                val buttonY = (height - buttonSizePx) / 2

                var x = if (controlButtonsOnRight) width else 0
                if (controlButtonsOnRight) {
                    x -= buttonSizePx
                }
                closeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-largeGapPx - buttonSizePx) else largeGapPx
                maximizeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-regularGapPx - buttonSizePx) else regularGapPx
                minimizeButtonPlaceable.place(x = x, y = buttonY)
                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                if (controlButtonsOnRight) {
                    titleBoxPlaceable.place(0, 0)
                } else {
                    titleBoxPlaceable.place(x, 0)
                }
            }
        }
    }
}

@Composable
private fun AuroraWindowScope.WindowIntegratedTitlePane(
    windowConfiguration: AuroraWindowTitlePaneConfigurations.AuroraIntegrated
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val extendedState = (window as? Frame)?.extendedState
    val isMaximized =
        remember { mutableStateOf(((extendedState != null) && ((extendedState and Frame.MAXIMIZED_BOTH) != 0))) }
    val skinColors = AuroraSkin.colors

    AuroraDecorationArea(
        decorationAreaType = DecorationAreaType.TitlePane,
        buttonShaper = ClassicButtonShaper.Instance
    ) {
        Layout(
            modifier = Modifier
                .fillMaxWidth()
                .height(windowConfiguration.titlePaneHeight)
                .padding(WindowTitlePaneSizingConstants.TitlePaneContentPadding),
            content = {
                Box(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {}

                // Minimize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.extendedState = JFrame.ICONIFIED
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .iconifyButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density
                            )
                        }
                    }),
                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.iconifyButtonProvider
                    .getContainerColorTokensOverlayProvider())

                // Maximize / Unmaximize button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        val current = (window as? Frame)
                        if (current != null) {
                            if (current.extendedState == JFrame.MAXIMIZED_BOTH) {
                                current.extendedState = JFrame.NORMAL
                            } else {
                                // Workaround for https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4737788
                                // to explicitly compute maximized bounds so that our window
                                // does not overlap the taskbar0
                                val screenBounds = current.graphicsConfiguration.bounds
                                // Prior to Java 15, we need to account for screen resolution which is given as
                                // scaleX and scaleY on default transform of the window's graphics configuration.
                                // See https://bugs.openjdk.java.net/browse/JDK-8176359,
                                // https://bugs.openjdk.java.net/browse/JDK-8231564 and
                                // https://bugs.openjdk.java.net/browse/JDK-8243925 that went into Java 15.
                                val isWindows = System.getProperty("os.name")?.startsWith("Windows")
                                val maximizedWindowBounds =
                                    if ((isWindows == true) && (Runtime.version().feature() < 15))
                                        Rectangle(
                                            0, 0,
                                            (screenBounds.width * current.graphicsConfiguration.defaultTransform.scaleX).toInt(),
                                            (screenBounds.height * current.graphicsConfiguration.defaultTransform.scaleY).toInt(),
                                        ) else screenBounds
                                // Now account for screen insets (taskbar and anything else that should not be
                                // interfered with by maximized windows)
                                val screenInsets = current.toolkit.getScreenInsets(current.graphicsConfiguration)
                                // Set maximized bounds of our window
                                current.maximizedBounds = Rectangle(
                                    maximizedWindowBounds.x + screenInsets.left,
                                    maximizedWindowBounds.y + screenInsets.top,
                                    maximizedWindowBounds.width - screenInsets.left - screenInsets.right,
                                    maximizedWindowBounds.height - screenInsets.top - screenInsets.bottom
                                )
                                // And now we can set our extended state
                                current.extendedState = JFrame.MAXIMIZED_BOTH
                            }
                            isMaximized.value = !isMaximized.value
                        }
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return if (isMaximized.value) {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .restoreButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            } else {
                                TransitionAwarePainter(
                                    iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                    decorationAreaType = DecorationAreaType.TitlePane,
                                    skinColors = skinColors,
                                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                        .maximizeButtonProvider.getContainerColorTokensOverlayProvider(),
                                    inactiveContainerType = ContainerType.Neutral,
                                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                    modelStateInfoSnapshot = modelStateInfoSnapshot,
                                    paintDelegate = { drawScope, iconSize, colorTokens ->
                                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider.drawIcon(
                                            drawScope, iconSize, colorTokens
                                        )
                                    },
                                    density = density,
                                )
                            }
                        }
                    }),
                    tokensOverlayProvider = if (isMaximized.value) {
                        windowConfiguration.titlePaneButtonsProvider.restoreButtonProvider
                    } else {
                        windowConfiguration.titlePaneButtonsProvider.maximizeButtonProvider
                    }.getContainerColorTokensOverlayProvider())

                // Close button
                AuroraWindowTitlePaneButton(titlePaneCommand = Command(
                    text = "",
                    action = {
                        (window as? Frame)?.dispatchEvent(
                            WindowEvent(
                                window,
                                WindowEvent.WINDOW_CLOSING
                            )
                        )
                    },
                    icon = object : TransitionAwarePainterDelegate() {
                        @OptIn(AuroraInternalApi::class)
                        override fun createNewIcon(modelStateInfoSnapshot: ModelStateInfoSnapshot): Painter {
                            return TransitionAwarePainter(
                                iconSize = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize,
                                decorationAreaType = DecorationAreaType.TitlePane,
                                skinColors = skinColors,
                                tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider
                                    .closeButtonProvider.getContainerColorTokensOverlayProvider(),
                                inactiveContainerType = ContainerType.Neutral,
                                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                                modelStateInfoSnapshot = modelStateInfoSnapshot,
                                paintDelegate = { drawScope, iconSize, colorTokens ->
                                    windowConfiguration.titlePaneButtonsProvider.closeButtonProvider.drawIcon(
                                        drawScope, iconSize, colorTokens
                                    )
                                },
                                density = density,
                            )
                        }
                    }),
                    tokensOverlayProvider = windowConfiguration.titlePaneButtonsProvider.closeButtonProvider
                        .getContainerColorTokensOverlayProvider())
            }) { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()

            val buttonMeasureSpec = Constraints.fixed(width = buttonSizePx, height = buttonSizePx)

            var childIndex = 0

            val titleBoxMeasurable = measurables[childIndex++]
            val minimizeButtonMeasurable = measurables[childIndex++]
            val maximizeButtonMeasurable = measurables[childIndex++]
            val closeButtonMeasurable = measurables[childIndex]

            val minimizeButtonPlaceable = minimizeButtonMeasurable.measure(buttonMeasureSpec)
            val maximizeButtonPlaceable = maximizeButtonMeasurable.measure(buttonMeasureSpec)
            val closeButtonPlaceable = closeButtonMeasurable.measure(buttonMeasureSpec)

            val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
            val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()

            val titleWidth = width -
                    (minimizeButtonPlaceable.width + regularGapPx +
                            maximizeButtonPlaceable.width + largeGapPx +
                            closeButtonPlaceable.width)

            val titleBoxPlaceable = titleBoxMeasurable.measure(
                Constraints.fixed(width = titleWidth, height = height)
            )

            layout(width = width, height = height) {
                val controlButtonsOnRight = windowConfiguration.areTitlePaneControlButtonsOnRight(layoutDirection)

                val buttonY = when (windowConfiguration.titleControlButtonGroupVerticalGravity) {
                    VerticalGravity.Top -> 0
                    VerticalGravity.Bottom -> height - buttonSizePx
                    VerticalGravity.Centered -> (height - buttonSizePx) / 2
                }

                var x = if (controlButtonsOnRight) width else 0
                if (controlButtonsOnRight) {
                    x -= buttonSizePx
                }
                closeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-largeGapPx - buttonSizePx) else largeGapPx
                maximizeButtonPlaceable.place(x = x, y = buttonY)

                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                x += if (controlButtonsOnRight) (-regularGapPx - buttonSizePx) else regularGapPx
                minimizeButtonPlaceable.place(x = x, y = buttonY)
                if (!controlButtonsOnRight) {
                    x += buttonSizePx
                }

                if (controlButtonsOnRight) {
                    titleBoxPlaceable.place(0, 0)
                } else {
                    titleBoxPlaceable.place(x, 0)
                }
            }
        }
    }
}

@Composable
private fun AuroraWindowScope.WindowInnerContent(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfiguration,
    menuCommands: CommandGroup? = null,
    content: @Composable AuroraWindowScope.() -> Unit
) {
    if (windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.AuroraIntegrated) {
        Box(Modifier.fillMaxSize().auroraBackground()) {
            // Wrap the entire content in NONE decoration area. App code can set its
            // own decoration area types on specific parts.
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
                content()
            }
            WindowIntegratedTitlePane(windowTitlePaneConfiguration)
        }
    } else {
        Column(Modifier.fillMaxSize().auroraBackground()) {
            if (windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.AuroraPlain) {
                WindowPlainTitlePane(windowTitlePaneConfiguration.titlePaneTitleTextConfiguration,
                    title, icon, iconFilterStrategy, windowTitlePaneConfiguration)
            }
            if (menuCommands != null) {
                AuroraWindowMenuBar(menuCommands)
            }
            // Wrap the entire content in NONE decoration area. App code can set its
            // own decoration area types on specific parts.
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
                content()
            }
        }
    }
}

internal fun Modifier.auroraWindowBorder(colorTokens: ContainerColorTokens): Modifier = drawBehind {
    val width: Float = size.width
    val height: Float = size.height
    val thickness = WindowSizingConstants.DecoratedBorderThickness.toPx()

    if ((width > thickness) && (height > thickness)) {
        drawRect(
            color = colorTokens.containerSurface,
            topLeft = Offset(thickness / 2.0f, thickness / 2.0f),
            size = Size(width - thickness, height - thickness),
            style = Stroke(width = thickness)
        )

        val quarterThickness = thickness / 4.0f
        // bottom and right as outline
        drawLine(
            color = colorTokens.containerOutline,
            start = Offset(x = 0f, y = height - quarterThickness / 2.0f),
            end = Offset(x = width, y = height - quarterThickness / 2.0f),
            strokeWidth = quarterThickness,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = colorTokens.containerOutline,
            start = Offset(x = width - quarterThickness / 2.0f, y = 0f),
            end = Offset(x = width - quarterThickness / 2.0f, y = height),
            strokeWidth = quarterThickness,
            cap = StrokeCap.Butt
        )
        // top and left as outline variant
        drawLine(
            color = colorTokens.containerOutlineVariant,
            start = Offset(x = 0f, y = quarterThickness / 2.0f),
            end = Offset(x = width - quarterThickness, y = quarterThickness / 2.0f),
            strokeWidth = quarterThickness,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = colorTokens.containerOutlineVariant,
            start = Offset(x = quarterThickness / 2.0f, y = 0f),
            end = Offset(x = quarterThickness / 2.0f, y = height - quarterThickness),
            strokeWidth = quarterThickness,
            cap = StrokeCap.Butt
        )
    }
}

@AuroraInternalApi
@Composable
fun AuroraWindowScope.AuroraWindowContent(
    title: String,
    icon: Painter?,
    iconFilterStrategy: IconFilterStrategy,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfiguration,
    menuCommands: CommandGroup? = null,
    content: @Composable AuroraWindowScope.() -> Unit
) {

    val skinColors = AuroraSkin.colors
    val neutralColorTokens = skinColors.getNeutralContainerTokens(DecorationAreaType.TitlePane)

    when (windowTitlePaneConfiguration) {
        is AuroraWindowTitlePaneConfigurations.AuroraIntegrated,
        is AuroraWindowTitlePaneConfigurations.AuroraPlain -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .auroraWindowBorder(neutralColorTokens)
                    .padding(WindowSizingConstants.DecoratedBorderThickness)
            ) {
                WindowInnerContent(
                    title,
                    icon,
                    iconFilterStrategy,
                    windowTitlePaneConfiguration,
                    menuCommands,
                    content
                )
            }
        }

        else -> {
            WindowInnerContent(
                title,
                icon,
                iconFilterStrategy,
                windowTitlePaneConfiguration,
                menuCommands,
                content
            )
        }
    }

    val awtEventListener = remember(this, window) {
        AWTEventListener { event ->
            val src = event.source
            if ((event is KeyEvent) && (event.id == KeyEvent.KEY_RELEASED)
                && (event.keyCode == KeyEvent.VK_ESCAPE)
            ) {
                AuroraPopupManager.hideLastPopup()
            }
            if ((event is MouseEvent) && (event.id == MouseEvent.MOUSE_PRESSED) && (src is Component)) {
                // This can be in our custom popup menu or in the top-level window
                var originator = SwingUtilities.getAncestorOfClass(AuroraSwingPopupMenu::class.java, src)
                    ?: SwingUtilities.getWindowAncestor(src)
                if (originator is JFrame) {
                    originator = originator.rootPane
                }
                if (originator != null) {
                    val eventLocation = event.locationOnScreen
                    SwingUtilities.convertPointFromScreen(eventLocation, originator)

                    if (!AuroraPopupManager.isShowingPopupFrom(
                            originator = originator,
                            pointInOriginator = Offset(eventLocation.x.toFloat(), eventLocation.y.toFloat())
                        )
                    ) {
                        AuroraPopupManager.hidePopups(originator)
                    }
                }
            }
            if (event.javaClass.simpleName == "UngrabEvent") {
                // Not the cleanest, but works for now
                AuroraPopupManager.hidePopups(null)
            }
        }
    }

    DisposableEffect(this, window) {
        // 0x80000000 is the mask for the internal sun.awt.SunToolkit.GRAB_EVENT_MASK which we need
        // to detect when our window is "ungrabbed". When that happens, we should hide all popups
        // shown from our window.
        Toolkit.getDefaultToolkit().addAWTEventListener(
            awtEventListener,
            AWTEvent.KEY_EVENT_MASK or AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_WHEEL_EVENT_MASK or 0x80000000
        )

        onDispose {
            Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener)
        }
    }
}

interface AuroraLocaleScope {
    var applicationLocale: Locale
}

class AuroraApplicationScope(
    private val original: ApplicationScope,
    private val currLocale: MutableState<Locale>
) : ApplicationScope, AuroraLocaleScope {
    override var applicationLocale: Locale
        get() = currLocale.value
        set(value) {
            Locale.setDefault(value)
            currLocale.value = value
        }

    override fun exitApplication() {
        original.exitApplication()
    }
}

interface AuroraWindowScope : WindowScope, AuroraLocaleScope {
    val auroraWindowTitlePaneConfiguration: AuroraWindowTitlePaneConfiguration
}

internal class AuroraWindowScopeImpl(
    private val applicationScope: AuroraApplicationScope,
    original: WindowScope,
    titlePaneConfiguration: AuroraWindowTitlePaneConfiguration
) : AuroraWindowScope {
    override var applicationLocale: Locale
        get() = applicationScope.applicationLocale
        set(value) {
            applicationScope.applicationLocale = value
        }

    override val window = original.window

    override val auroraWindowTitlePaneConfiguration = titlePaneConfiguration
}

fun auroraApplication(content: @Composable AuroraApplicationScope.() -> Unit) {
    application {
        val currLocale = mutableStateOf(Locale.getDefault())
        CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (ComponentOrientation.getOrientation(currLocale.value).isLeftToRight)
                        LayoutDirection.Ltr else LayoutDirection.Rtl
        ) {
            AuroraApplicationScope(this, currLocale).content()
        }
    }
}

sealed class AuroraWindowTitlePaneConfiguration

object AuroraWindowTitlePaneConfigurations {
    /**
     * Window title pane is provided by the operating system.
     */
    data object System : AuroraWindowTitlePaneConfiguration()

    /**
     * No window title pane. The application UI is responsible for resizing, dragging and other
     * top-level user interaction with the window.
     */
    data object None : AuroraWindowTitlePaneConfiguration()

    interface TitlePaneButtonProvider {
        /** Draws the icon for this button. */
        fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens)

        fun getContainerColorTokensOverlayProvider() : ContainerColorTokensOverlay.Provider? = null
    }

    interface TitlePaneButtonsProvider {
        val closeButtonProvider: TitlePaneButtonProvider
        val restoreButtonProvider: TitlePaneButtonProvider
        val iconifyButtonProvider: TitlePaneButtonProvider
        val maximizeButtonProvider: TitlePaneButtonProvider
    }

    open class DefaultTitlePaneButtonsProvider : TitlePaneButtonsProvider {
        override val closeButtonProvider: TitlePaneButtonProvider
            get() = object : TitlePaneButtonProvider {
                override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                    drawCloseIcon(drawScope, iconSize, colorTokens)
                }
            }

        override val restoreButtonProvider: TitlePaneButtonProvider
            get() = object : TitlePaneButtonProvider {
                override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                    drawRestoreIcon(drawScope, iconSize, colorTokens)
                }
            }

        override val iconifyButtonProvider: TitlePaneButtonProvider
            get() = object : TitlePaneButtonProvider {
                override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                    drawMinimizeIcon(drawScope, iconSize, colorTokens)
                }
            }

        override val maximizeButtonProvider: TitlePaneButtonProvider
            get() = object : TitlePaneButtonProvider {
                override fun drawIcon(drawScope: DrawScope, iconSize: Dp, colorTokens: ContainerColorTokens) {
                    drawMaximizeIcon(drawScope, iconSize, colorTokens)
                }
            }
    }

    interface TitlePaneTitleTextConfiguration {
        fun getTitleTextStyle(colorTokens: ContainerColorTokens): TextStyle
    }

    class DefaultTitlePaneTitleTextConfiguration: TitlePaneTitleTextConfiguration {
        override fun getTitleTextStyle(colorTokens: ContainerColorTokens): TextStyle {
            return TextStyle(
                color = colorTokens.onContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }

    /**
     * Plain title pane provided by Aurora with application icon, application title and
     * three control buttons: minimize, maximize / restore and close. Use the gravity attributes
     * to configure the positioning of these elements along the horizontal axis.
     */
    data class AuroraPlain(
        val titleTextHorizontalGravity: HorizontalGravity = HorizontalGravity.Leading,
        val titleControlButtonGroupHorizontalGravity: HorizontalGravity = HorizontalGravity.Trailing,
        val titleIconHorizontalGravity: TitleIconHorizontalGravity = TitleIconHorizontalGravity.OppositeControlButtons,
        val titlePaneButtonsProvider: TitlePaneButtonsProvider = DefaultTitlePaneButtonsProvider(),
        val titlePaneTitleTextConfiguration: TitlePaneTitleTextConfiguration = DefaultTitlePaneTitleTextConfiguration()
    ) : AuroraWindowTitlePaneConfiguration() {
        @OptIn(AuroraInternalApi::class)
        internal fun areTitlePaneControlButtonsOnRight(layoutDirection: LayoutDirection): Boolean {
            return when (this.titleControlButtonGroupHorizontalGravity) {
                HorizontalGravity.Platform -> {
                    when (Platform.Current) {
                        Platform.MacOS -> layoutDirection == LayoutDirection.Rtl
                        else -> layoutDirection == LayoutDirection.Ltr
                    }
                }

                HorizontalGravity.Leading -> layoutDirection == LayoutDirection.Rtl
                else -> layoutDirection == LayoutDirection.Ltr
            }
        }

        @OptIn(AuroraInternalApi::class)
        internal fun getTitlePaneIconGravity(): TitleIconHorizontalGravity {
            return when (this.titleIconHorizontalGravity) {
                TitleIconHorizontalGravity.Platform -> {
                    when (Platform.Current) {
                        Platform.MacOS -> TitleIconHorizontalGravity.NextToTitle
                        Platform.Gnome -> TitleIconHorizontalGravity.None
                        else -> TitleIconHorizontalGravity.OppositeControlButtons
                    }
                }

                else -> this.titleIconHorizontalGravity
            }
        }

        @OptIn(AuroraInternalApi::class)
        internal fun getTitlePaneTextGravity(): HorizontalGravity {
            return when (this.titleTextHorizontalGravity) {
                HorizontalGravity.Platform -> {
                    when (Platform.Current) {
                        Platform.Windows -> HorizontalGravity.Leading
                        else -> HorizontalGravity.Centered
                    }
                }

                else -> this.titleTextHorizontalGravity
            }
        }
    }

    /**
     * Integrated title pane provided by Aurora with three control buttons: minimize,
     * maximize / restore and close. Use the gravity attributes
     * to configure the positioning of these buttons, and the height attribute to configure
     * the title pane height.
     *
     * Use [getTitlePaneControlInsets] to obtain the insets that should be applied to the
     * application content that is integrated into the title pane area so that it does not
     * overlap with the window control buttons.
     *
     * Use [WindowDraggableArea] to designate the part of your integrated title pane content
     * that can be used to drag the window around the screen by grabbing it with the mouse.
     *
     * Optionally, use [auroraBackground] modifier on your integrated title pane content for
     * the visual appearance of the title pane area.
     *
     * Optionally, use [AuroraWindowTitlePaneTitleText] for creating a title label that is
     * styled consistently with the current Aurora skin.
     *
     * Optionally, use [AuroraWindowTitlePaneButton] for creating a control button that is
     * styled consistently with the title pane control buttons provided by Aurora.
     */
    data class AuroraIntegrated(
        val titleControlButtonGroupHorizontalGravity: HorizontalGravity = HorizontalGravity.Trailing,
        val titleControlButtonGroupVerticalGravity: VerticalGravity = VerticalGravity.Centered,
        val titlePaneHeight: Dp = WindowTitlePaneSizingConstants.MinimumTitlePaneHeight,
        val titlePaneButtonsProvider: TitlePaneButtonsProvider = DefaultTitlePaneButtonsProvider(),
        val titlePaneTitleTextConfiguration: TitlePaneTitleTextConfiguration = DefaultTitlePaneTitleTextConfiguration()
    ) : AuroraWindowTitlePaneConfiguration() {
        @OptIn(AuroraInternalApi::class)
        internal fun areTitlePaneControlButtonsOnRight(layoutDirection: LayoutDirection): Boolean {
            return when (this.titleControlButtonGroupHorizontalGravity) {
                HorizontalGravity.Platform -> {
                    when (Platform.Current) {
                        Platform.MacOS -> layoutDirection == LayoutDirection.Rtl
                        else -> layoutDirection == LayoutDirection.Ltr
                    }
                }

                HorizontalGravity.Leading -> layoutDirection == LayoutDirection.Rtl
                else -> layoutDirection == LayoutDirection.Ltr
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
fun AuroraApplicationScope.AuroraWindow(
    skin: AuroraSkinDefinition,
    onCloseRequest: () -> Unit,
    state: WindowState = rememberWindowState(),
    visible: Boolean = true,
    title: String = "Untitled",
    icon: Painter? = null,
    iconFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    menuCommands: CommandGroup? = null,
    windowTitlePaneConfiguration: AuroraWindowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.System,
    resizable: Boolean = true,
    enabled: Boolean = true,
    focusable: Boolean = true,
    alwaysOnTop: Boolean = false,
    onPreviewKeyEvent: (androidx.compose.ui.input.key.KeyEvent) -> Boolean = { false },
    onKeyEvent: (androidx.compose.ui.input.key.KeyEvent) -> Boolean = { false },
    content: @Composable AuroraWindowScope.() -> Unit
) {
    if (windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.AuroraIntegrated) {
        if (windowTitlePaneConfiguration.titlePaneHeight < WindowTitlePaneSizingConstants.MinimumTitlePaneHeight) {
            throw IllegalArgumentException("Integrated windows must have at least ${WindowTitlePaneSizingConstants.MinimumTitlePaneHeight} tall title pane")
        }
        if (menuCommands != null) {
            throw IllegalArgumentException("Integrated windows do not support menus")
        }
    }

    val density = mutableStateOf(Density(1.0f, 1.0f))

    val decoratedBySystem = (windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.System)
    Window(
        onCloseRequest = onCloseRequest,
        state = state,
        visible = visible,
        title = title,
        icon = icon,
        undecorated = !decoratedBySystem,
        resizable = resizable,
        enabled = enabled,
        focusable = focusable,
        alwaysOnTop = alwaysOnTop,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent
    ) {
        val swingComponentOrientation = ComponentOrientation.getOrientation(applicationLocale)
        val composeLayoutDirection = if (swingComponentOrientation.isLeftToRight)
            LayoutDirection.Ltr else LayoutDirection.Rtl
        // Get the current composition context
        CompositionLocalProvider(
            LocalWindow provides window,
            LocalWindowSize provides state.size,
            LocalTopWindowSize provides state.size,
            LocalLayoutDirection provides composeLayoutDirection
        ) {
            val auroraWindowScope = AuroraWindowScopeImpl(this@AuroraWindow, this, windowTitlePaneConfiguration)
            AuroraSkin(
                displayName = skin.displayName,
                decorationAreaType = DecorationAreaType.None,
                colors = skin.colors,
                buttonShaper = skin.buttonShaper,
                painters = skin.painters,
                animationConfig = AuroraSkin.animationConfig
            ) {
                density.value = LocalDensity.current
                auroraWindowScope.AuroraWindowContent(
                    title = title,
                    icon = icon,
                    iconFilterStrategy = iconFilterStrategy,
                    windowTitlePaneConfiguration = windowTitlePaneConfiguration,
                    menuCommands = menuCommands,
                    content = content
                )
            }
        }

        LaunchedEffect(Unit) {
            if ((windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.AuroraIntegrated) ||
                (windowTitlePaneConfiguration is AuroraWindowTitlePaneConfigurations.AuroraPlain)
            ) {
                val lastCursor = mutableStateOf(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))
                val awtInputHandler = AWTInputHandler(
                    density = density.value,
                    window = window,
                    rootPane = window.rootPane,
                    lastCursor = lastCursor
                )

                Toolkit.getDefaultToolkit().addAWTEventListener(
                    awtInputHandler,
                    AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK
                )
            }
        }
    }
}

@OptIn(AuroraInternalApi::class)
@Composable
internal fun AuroraSkin(
    displayName: String = AuroraSkin.displayName,
    decorationAreaType: DecorationAreaType,
    colors: AuroraSkinColors = AuroraSkin.colors,
    buttonShaper: AuroraButtonShaper = AuroraSkin.buttonShaper,
    painters: AuroraPainters = AuroraSkin.painters,
    animationConfig: AnimationConfig = AuroraSkin.animationConfig,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDisplayName provides displayName,
        LocalDecorationAreaType provides decorationAreaType,
        LocalSkinColors provides colors,
        LocalButtonShaper provides buttonShaper,
        LocalPainters provides painters,
        LocalAnimationConfig provides animationConfig
    ) {
        content()
    }
}

fun AuroraWindowScope.getTitlePaneControlInsets(
    layoutDirection: LayoutDirection,
    density: Density
): PaddingValues {
    if (this.auroraWindowTitlePaneConfiguration !is AuroraWindowTitlePaneConfigurations.AuroraIntegrated) {
        throw IllegalStateException("Title pane control insets only available under integrated title pane kind")
    }

    val integratedConfiguration =
        this.auroraWindowTitlePaneConfiguration as AuroraWindowTitlePaneConfigurations.AuroraIntegrated

    with(density) {
        val controlButtonsOnRight = integratedConfiguration.areTitlePaneControlButtonsOnRight(layoutDirection)
        val buttonSizePx = WindowTitlePaneSizingConstants.TitlePaneButtonIconSize.toPx().roundToInt()
        val regularGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconRegularGap.toPx().roundToInt()
        val largeGapPx = WindowTitlePaneSizingConstants.TitlePaneButtonIconLargeGap.toPx().roundToInt()
        val controlButtonsWidth = 3 * buttonSizePx + regularGapPx + largeGapPx

        val leftInsetPx = if (controlButtonsOnRight) 0 else controlButtonsWidth
        val rightInsetPx = if (controlButtonsOnRight) controlButtonsWidth else 0

        val y = when (integratedConfiguration.titleControlButtonGroupVerticalGravity) {
            VerticalGravity.Top -> 0
            VerticalGravity.Centered -> (integratedConfiguration.titlePaneHeight.toPx()
                .roundToInt() - buttonSizePx) / 2

            VerticalGravity.Bottom -> integratedConfiguration.titlePaneHeight.toPx().roundToInt() - buttonSizePx
        }

        val topInsetPx = y
        val bottomInsetPx = integratedConfiguration.titlePaneHeight.toPx().roundToInt() - y - buttonSizePx

        return PaddingValues(
            start = (if (layoutDirection == LayoutDirection.Ltr) leftInsetPx else rightInsetPx).toDp() +
                    WindowTitlePaneSizingConstants.TitlePaneContentPadding.calculateStartPadding(layoutDirection),
            end = (if (layoutDirection == LayoutDirection.Ltr) rightInsetPx else leftInsetPx).toDp() +
                    WindowTitlePaneSizingConstants.TitlePaneContentPadding.calculateEndPadding(layoutDirection),
            top = topInsetPx.toDp(),
            bottom = bottomInsetPx.toDp()
        )
    }
}