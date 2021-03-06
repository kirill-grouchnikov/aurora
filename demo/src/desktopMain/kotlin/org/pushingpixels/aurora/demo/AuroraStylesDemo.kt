/*
 * Copyright 2020-2021 Aurora, Kirill Grouchnikov
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.demo.svg.material.content_copy_black_24dp
import org.pushingpixels.aurora.demo.svg.material.content_cut_black_24dp
import org.pushingpixels.aurora.demo.svg.material.content_paste_black_24dp
import org.pushingpixels.aurora.skin.getAuroraSkins
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraWindow

@ExperimentalUnitApi
fun main() {
    val skin = mutableStateOf(marinerSkin())
    AuroraWindow(
        skin = skin,
        title = "Aurora Demo",
        size = IntSize(660, 400),
        undecorated = true
    ) {
        DemoStyleContent(skin)
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

@Composable
fun getStylesContentModel(
    permanentTopColor: MutableState<Color>,
    permanentBottomColor: MutableState<Color>,
    isInPreview: MutableState<Boolean>,
    previewTopColor: MutableState<Color>,
    previewBottomColor: MutableState<Color>
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
    for (i in 1..solidColors.size) {
        val color = solidColors[i - 1]
        val command = Command(
            text = "Solid ${color.shorthexa}",
            extraText = color.shorthexa,
            iconFactory = ColorSolidIcon.factory(color),
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command) {
                    isInPreview.value = true
                    previewTopColor.value = color
                    previewBottomColor.value = color
                }

                override fun onCommandPreviewCanceled(command: Command) {
                    isInPreview.value = false
                }
            },
            isActionToggle = true,
            isActionToggleSelected = ((selectedStyle.value as? SolidStyle)?.index == i),
            onTriggerActionToggleSelectedChange = {
                if (it) {
                    permanentTopColor.value = color
                    permanentBottomColor.value = color
                    selectedStyle.value = SolidStyle(i)
                }
            }
        )
        solids.add(command)
    }
    commandGroups.add(CommandGroup("Solids", solids))

    val gradients: MutableList<Command> = arrayListOf()
    for (i in 1 until solidColors.size) {
        val colorTop = solidColors[i - 1]
        val colorBottom = solidColors[i]
        val command = Command(
            text = "${colorTop.shorthexa} ${colorBottom.shorthexa}",
            extraText = colorTop.shorthexa,
            iconFactory = ColorGradientIcon.factory(colorTop, colorBottom),
            actionPreview = object : CommandActionPreview {
                override fun onCommandPreviewActivated(command: Command) {
                    isInPreview.value = true
                    previewTopColor.value = colorTop
                    previewBottomColor.value = colorBottom
                }

                override fun onCommandPreviewCanceled(command: Command) {
                    isInPreview.value = false
                }
            },
            isActionToggle = true,
            isActionToggleSelected = ((selectedStyle.value as? GradientStyle)?.index == i),
            onTriggerActionToggleSelectedChange = {
                if (it) {
                    permanentTopColor.value = colorTop
                    permanentBottomColor.value = colorBottom
                    selectedStyle.value = GradientStyle(i)
                }
            }
        )
        gradients.add(command)
    }
    commandGroups.add(CommandGroup("Gradients", gradients))

    return CommandPanelContentModel(
        commandGroups = commandGroups
    )
}

@ExperimentalUnitApi
@Composable
fun CommandDemoEditStrip(
    permanentTopColor: MutableState<Color>,
    permanentBottomColor: MutableState<Color>,
    isInPreview: MutableState<Boolean>,
    previewTopColor: MutableState<Color>,
    previewBottomColor: MutableState<Color>
) {
    val commandCut =
        Command(
            text = "Cut",
            iconFactory = content_cut_black_24dp.factory(),
            isActionEnabled = true,
            action = { println("Cut!") }
        )
    val commandCopy =
        Command(
            text = "Copy",
            iconFactory = content_copy_black_24dp.factory(),
            isActionEnabled = true,
            action = { println("Copy!") }
        )
    var togglePasteText by remember { mutableStateOf(false) }
    val commandPasteTextOnly = Command(
        text = "Text only",
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
            text = "Paste",
            iconFactory = content_paste_black_24dp.factory(),
            isActionEnabled = true,
            action = { println("Paste!") },
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = listOf(
                        Command(
                            text = "Keep Formatting",
                            action = { println("Paste with keep formatting") }),
                        Command(
                            text = "Merge Formatting",
                            action = { println("Paste with merge formatting") }),
                        commandPasteTextOnly
                    )
                ),
                panelContentModel = getStylesContentModel(
                    permanentTopColor,
                    permanentBottomColor,
                    isInPreview,
                    previewTopColor,
                    previewBottomColor
                )
            ),
            isSecondaryEnabled = true
        )

    // Resolve the default text style to get the default font size
    val resolvedTextStyle = resolveDefaults(LocalTextStyle.current, LocalLayoutDirection.current)
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
            commandPasteTextOnly to CommandButtonPresentationModel.Overlay(
                toDismissPopupsOnActivation = false
            ),
            commandPaste to CommandButtonPresentationModel.Overlay(
                popupMenuPresentationModel = CommandPopupMenuPresentationModel(
                    panelPresentationModel = CommandPanelPresentationModel(
                        contentPadding = PaddingValues(0.dp),
                        showGroupLabels = true,
                        commandPresentationState = CommandButtonPresentationState.FitToIcon,
                        commandIconSize = 36.dp,
                        commandTextStyle = smallerTextStyle,
                        commandHorizontalGapScaleFactor = 0.25f,
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconActiveFilterStrategy = IconFilterStrategy.Original,
                        iconEnabledFilterStrategy = IconFilterStrategy.Original,
                        maxColumns = 5,
                        maxRows = 3
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

@ExperimentalUnitApi
@Composable
fun DemoStyleContent(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        val currentSkinDisplayName = AuroraSkin.displayName
        val auroraSkins = getAuroraSkins()
        val selectedSkinItem =
            remember { mutableStateOf(auroraSkins.first { it.first == currentSkinDisplayName }) }

        ComboBoxProjection(
            contentModel = ComboBoxContentModel(
                items = auroraSkins,
                selectedItem = selectedSkinItem.value,
                onTriggerItemSelectedChange = {
                    selectedSkinItem.value = it
                    auroraSkinDefinition.value = it.second.invoke()
                }
            ),
            presentationModel = ComboBoxPresentationModel(
                displayConverter = { it.first }
            )
        ).project()

        Spacer(modifier = Modifier.height(12.dp))

        val permanentTopColor = remember { mutableStateOf(Color.White) }
        val permanentBottomColor = remember { mutableStateOf(Color.White) }
        val isInPreview = remember { mutableStateOf(false) }
        val previewTopColor = remember { mutableStateOf(Color.White) }
        val previewBottomColor = remember { mutableStateOf(Color.White) }

        // Animate top and bottom color to preview / permanent based on the preview state
        val topColor = animateColorAsState(
            targetValue = if (isInPreview.value) previewTopColor.value else permanentTopColor.value
        )
        val bottomColor = animateColorAsState(
            targetValue = if (isInPreview.value) previewBottomColor.value else permanentBottomColor.value
        )

        DemoStyleCanvas(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            topColor = topColor.value,
            bottomColor = bottomColor.value
        )

        Box(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp)) {
            CommandDemoEditStrip(
                permanentTopColor, permanentBottomColor,
                isInPreview, previewTopColor, previewBottomColor
            )
        }
    }
}



