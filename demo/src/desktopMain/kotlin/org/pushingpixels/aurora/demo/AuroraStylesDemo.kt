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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.demo.svg.material.content_copy_black_24dp
import org.pushingpixels.aurora.demo.svg.material.content_cut_black_24dp
import org.pushingpixels.aurora.demo.svg.material.content_paste_black_24dp
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(660.dp, 400.dp)
    )
    var skin by remember { mutableStateOf(marinerSkin()) }
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        onCloseRequest = ::exitApplication,
    ) {
        DemoStyleContent({ skin = it }, resourceBundle)
    }
}

private fun encodeChannel(number: Float): String {
    require(!(number < 0 || number > 1.0f)) { "" + number }
    val hex = "0123456789ABCDEF"
    val asInt = (255.0f * number + 0.5f).toInt()
    val hexaDigit1 = hex[asInt / 16]
    val hexaDigit2 = hex[asInt % 16]
    return hexaDigit1.toString() + "" + hexaDigit2
}

private fun decodeCharacter(c: Char): Int {
    val hex = "0123456789ABCDEF"
    return hex.indexOf(c)
}

private fun decodeChannel(channelString: String): Int {
    return decodeCharacter(channelString[0].uppercaseChar()) * 16 +
            decodeCharacter(channelString[1].uppercaseChar())
}

val Color.shorthexa: String
    get() = ("#" + encodeChannel(this.red) + encodeChannel(this.green) + encodeChannel(this.blue))

val String.asColor: Color
    get() = Color(
        alpha = 255,
        red = decodeChannel(this.substring(1, 3)),
        green = decodeChannel(this.substring(3, 5)),
        blue = decodeChannel(this.substring(5, 7))
    )

sealed class Style
object UnsetStyle : Style()
data class SolidStyle(val index: Int) : Style()
data class GradientStyle(val index: Int) : Style()

@Stable
data class StyleData(
    val isInPreview: Boolean,
    val permanentTopColor: Color,
    val permanentBottomColor: Color,
    val previewTopColor: Color,
    val previewBottomColor: Color,
)

@Composable
fun getStylesContentModel(
    styleData: StyleData,
    onStyleDataChanged: (StyleData) -> Unit,
    resourceBundle: ResourceBundle
): CommandPanelContentModel {
    // Colors from http://colrd.com/palette/24070/
    val solidColors = arrayOf(
        "#1a1334".asColor,
        "#25294a".asColor,
        "#01545a".asColor,
        "#017351".asColor,
        "#03c383".asColor,
        "#aad962".asColor,
        "#fbbf45".asColor,
        "#ef6a32".asColor,
        "#ed0345".asColor,
        "#a12a5e".asColor,
        "#710162".asColor,
        "#110141".asColor,
    )

    val selectedStyle = remember { mutableStateOf<Style>(UnsetStyle) }

    val commandGroups: MutableList<CommandGroup> = arrayListOf()
    val solids: MutableList<Command> = arrayListOf()
    val solidMf = MessageFormat(resourceBundle.getString("Colors.solid"))
    for (i in 1..solidColors.size) {
        val color = solidColors[i - 1]
        val command = Command(
            text = solidMf.format(arrayOf<Any>(color.shorthexa)),
            extraText = color.shorthexa,
            icon = ColorSolidIcon(color),
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: BaseCommand) {
                    onStyleDataChanged.invoke(
                        styleData.copy(
                            isInPreview = true,
                            previewTopColor = color,
                            previewBottomColor = color
                        )
                    )
                }

                override fun onCommandPreviewCanceled(command: BaseCommand) {
                    onStyleDataChanged.invoke(styleData.copy(isInPreview = false))
                }
            },
            isActionToggle = true,
            isActionToggleSelected = ((selectedStyle.value as? SolidStyle)?.index == i),
            onTriggerActionToggleSelectedChange = {
                if (it) {
                    onStyleDataChanged.invoke(
                        styleData.copy(
                            permanentTopColor = color,
                            permanentBottomColor = color
                        )
                    )
                    selectedStyle.value = SolidStyle(i)
                }
            }
        )
        solids.add(command)
    }
    commandGroups.add(CommandGroup(resourceBundle.getString("Colors.solids"), solids))

    val gradients: MutableList<Command> = arrayListOf()
    for (i in 1 until solidColors.size) {
        val colorTop = solidColors[i - 1]
        val colorBottom = solidColors[i]
        val command = Command(
            text = "${colorTop.shorthexa} ${colorBottom.shorthexa}",
            extraText = colorTop.shorthexa,
            icon = ColorGradientIcon(colorTop, colorBottom),
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: BaseCommand) {
                    onStyleDataChanged.invoke(
                        styleData.copy(
                            isInPreview = true,
                            previewTopColor = colorTop,
                            previewBottomColor = colorBottom
                        )
                    )
                }

                override fun onCommandPreviewCanceled(command: BaseCommand) {
                    onStyleDataChanged.invoke(styleData.copy(isInPreview = false))
                }
            },
            isActionToggle = true,
            isActionToggleSelected = ((selectedStyle.value as? GradientStyle)?.index == i),
            onTriggerActionToggleSelectedChange = {
                if (it) {
                    onStyleDataChanged.invoke(
                        styleData.copy(
                            permanentTopColor = colorTop,
                            permanentBottomColor = colorBottom
                        )
                    )
                    selectedStyle.value = GradientStyle(i)
                }
            }
        )
        gradients.add(command)
    }
    commandGroups.add(CommandGroup(resourceBundle.getString("Colors.gradients"), gradients))

    return CommandPanelContentModel(
        commandGroups = commandGroups
    )
}

@Composable
fun CommandDemoEditStrip(
    styleData: StyleData,
    onStyleDataChanged: (StyleData) -> Unit,
    resourceBundle: ResourceBundle
) {
    val commandCut =
        Command(
            text = resourceBundle.getString("Edit.cut.text"),
            icon = content_cut_black_24dp(),
            isActionEnabled = true,
            action = { println("Cut!") }
        )
    val commandCopy =
        Command(
            text = resourceBundle.getString("Edit.copy.text"),
            icon = content_copy_black_24dp(),
            isActionEnabled = true,
            action = { println("Copy!") }
        )
    var togglePasteText by remember { mutableStateOf(false) }
    val commandPasteTextOnly = Command(
        text = resourceBundle.getString("Edit.paste.textOnlyText"),
        action = { println("Paste text only") },
        isActionToggle = true,
        isActionToggleSelected = togglePasteText,
        onTriggerActionToggleSelectedChange = {
            println("Selected toggle paste text? $it")
            togglePasteText = it
        }
    )
    val commandPaste =
        Command(
            text = resourceBundle.getString("Edit.paste.text"),
            icon = content_paste_black_24dp(),
            isActionEnabled = true,
            action = { println("Paste!") },
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = listOf(
                        Command(
                            text = resourceBundle.getString("Edit.paste.keepFormattingText"),
                            action = { println("Paste with keep formatting") }),
                        Command(
                            text = resourceBundle.getString("Edit.paste.mergeFormattingText"),
                            action = { println("Paste with merge formatting") }),
                        commandPasteTextOnly
                    )
                ),
                panelContentModel = getStylesContentModel(
                    styleData = styleData,
                    onStyleDataChanged = onStyleDataChanged,
                    resourceBundle = resourceBundle
                )
            ),
            isSecondaryEnabled = true
        )

    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveAuroraDefaults()
    val fontSize = resolvedTextStyle.fontSize
    // Compute a smaller font size
    val smallerFontSize = TextUnit(fontSize.value - 2.0f, fontSize.type)
    // And create our own text style with smaller font size
    val smallerTextStyle = TextStyle(fontSize = smallerFontSize)

    CommandButtonStripProjection(
        contentModel = CommandGroup(
            commands = listOf(
                commandCopy,
                commandCut,
                commandPaste
            )
        ),
        presentationModel = CommandStripPresentationModel(
            orientation = StripOrientation.Horizontal,
            horizontalGapScaleFactor = CommandStripSizingConstants.DefaultGapScaleFactorPrimaryAxis,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
        ),
        overlays = mapOf(
            commandPasteTextOnly to BaseCommandButtonPresentationModel.Overlay(
                toDismissPopupsOnActivation = false
            ),
            commandPaste to BaseCommandButtonPresentationModel.Overlay(
                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                    panelPresentationModel = CommandPopupMenuPanelPresentationModel(
                        layoutSpec = MenuPopupPanelLayoutSpec(columnCount = 5, visibleRowCount = 3),
                        contentPadding = PaddingValues(0.dp),
                        showGroupLabels = true,
                        commandPresentationState = CommandButtonPresentationState.BigFitToIcon,
                        commandIconDimension = DpSize(36.dp, 36.dp),
                        commandTextStyle = smallerTextStyle,
                        commandHorizontalGapScaleFactor = 0.25f,
                        iconActiveFilterStrategy = IconFilterStrategy.Original,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original
                    )
                )
            )
        )
    ).project()
}

@Composable
fun DemoStyleCanvas(
    modifier: Modifier,
    topColor: Color,
    bottomColor: Color
) {
    Canvas(modifier = modifier) {
        drawRect(
            brush = Brush.verticalGradient(
                0.0f to topColor,
                1.0f to bottomColor,
                startY = 0.0f,
                endY = size.height,
                tileMode = TileMode.Clamp
            ),
            topLeft = Offset.Zero,
            size = size,
            style = Fill
        )
    }
}

@Composable
fun DemoStyleCanvas(
    modifier: Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = size,
            style = Fill
        )
    }
}

@Composable
fun AuroraApplicationScope.DemoStyleContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    var styleData by remember {
        mutableStateOf(
            StyleData(
                isInPreview = false,
                permanentTopColor = Color.White,
                permanentBottomColor = Color.White,
                previewTopColor = Color.White,
                previewBottomColor = Color.White,
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
            AuroraDecorationArea(
                decorationAreaType = DecorationAreaType.None,
                buttonShaper = ClassicButtonShaper.Instance
            ) {
                AuroraSkinSwitcher(onSkinChange)

                Spacer(modifier = Modifier.width(8.dp))

                AuroraLocaleSwitcher(resourceBundle)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Animate top and bottom color to preview / permanent based on the preview state
        val topColor = animateColorAsState(
            targetValue = if (styleData.isInPreview) styleData.previewTopColor else styleData.permanentTopColor
        )
        val bottomColor = animateColorAsState(
            targetValue = if (styleData.isInPreview) styleData.previewBottomColor else styleData.permanentBottomColor
        )

        DemoStyleCanvas(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            topColor = topColor.value,
            bottomColor = bottomColor.value
        )

        Box(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
            CommandDemoEditStrip(
                styleData = styleData,
                onStyleDataChanged = { styleData = it },
                resourceBundle = resourceBundle
            )
        }
    }
}



