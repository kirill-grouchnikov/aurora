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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.DeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.IndeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.demo.svg.tango.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.shaper.ClassicButtonShaper
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.util.*

fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(500.dp, 400.dp)
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
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = resourceBundle.getString("Menu.file"),
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = resourceBundle.getString("Menu.file.new"),
                                    action = { println("New file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.open"),
                                    action = { println("Open file!") }),
                                Command(
                                    text = resourceBundle.getString("Menu.file.save"),
                                    action = { println("Save file!") })
                            )
                        )
                    )
                ),
                Command(
                    text = resourceBundle.getString("Menu.edit"),
                    action = { println("Edit activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.view"),
                    action = { println("View activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.tools"),
                    action = { println("Tools activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.window"),
                    action = { println("Window activated!") }),
                Command(
                    text = resourceBundle.getString("Menu.help"),
                    action = { println("Help activated!") })
            )
        )
    ) {
        DemoProgressContent({ skin = it }, resourceBundle)
    }
}

@Composable
fun AuroraWindowScope.DemoProgressArea(
    modifier: Modifier = Modifier,
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    // TODO - convert this to use ConstraintLayout when (if?) that is available for desktop
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
    ) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
            Column(
                modifier = Modifier.fillMaxHeight().auroraBackground()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AuroraDecorationArea(
                    decorationAreaType = DecorationAreaType.None,
                    buttonShaper = ClassicButtonShaper.Instance
                ) {
                    AuroraSkinSwitcher(onSkinChange)

                    Spacer(modifier = Modifier.width(8.dp))

                    AuroraLocaleSwitcher(resourceBundle)
                }
            }
        }

        VerticalSeparatorProjection(
            contentModel = SeparatorContentModel(),
            presentationModel = SeparatorPresentationModel(
                startGradientAmount = 0.dp,
                endGradientAmount = 0.dp
            )
        ).project(modifier = Modifier.fillMaxHeight())

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Resolve the default text style to get the default font size
            val resolvedTextStyle = resolveAuroraDefaults()
            val fontSize = resolvedTextStyle.fontSize
            // Compute a smaller font size
            val smallerFontSize = TextUnit(fontSize.value - 4.0f, fontSize.type)
            // And create our own text style with smaller font size and bold weight
            val textStyle = TextStyle(
                fontSize = smallerFontSize,
                fontWeight = FontWeight.Bold
            )

            val progress by remember { mutableStateOf(0.2f) }
            val animatedStateProgress = animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressConstants.ProgressAnimationSpec
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Enabled determinate linear progress bar
            LabelProjection(
                contentModel = LabelContentModel(
                    text = resourceBundle.getString("Progress.determinate.enabled")
                        .uppercase()
                ),
                presentationModel = LabelPresentationModel(textStyle = textStyle)
            ).project()
            DeterminateLinearProgressProjection(
                contentModel = DeterminateProgressContentModel(
                    enabled = true,
                    progress = animatedStateProgress.value
                )
            ).project()

            Spacer(modifier = Modifier.height(6.dp))

            // Enabled indeterminate linear progress bar
            LabelProjection(
                contentModel = LabelContentModel(
                    text = resourceBundle.getString("Progress.indeterminate.enabled")
                        .uppercase()
                ),
                presentationModel = LabelPresentationModel(textStyle = textStyle)
            ).project()
            IndeterminateLinearProgressProjection(
                contentModel = IndeterminateProgressContentModel(enabled = true),
            ).project()

            Spacer(modifier = Modifier.height(6.dp))

            // Disabled determinate linear progress bar
            LabelProjection(
                contentModel = LabelContentModel(
                    text = resourceBundle.getString("Progress.determinate.disabled")
                        .uppercase()
                ),
                presentationModel = LabelPresentationModel(textStyle = textStyle)
            ).project()
            DeterminateLinearProgressProjection(
                contentModel = DeterminateProgressContentModel(
                    enabled = false,
                    progress = animatedStateProgress.value
                )
            ).project()

            Spacer(modifier = Modifier.height(6.dp))

            // Disabled indeterminate linear progress bar
            LabelProjection(
                contentModel = LabelContentModel(
                    text = resourceBundle.getString("Progress.indeterminate.disabled")
                        .uppercase()
                ),
                presentationModel = LabelPresentationModel(textStyle = textStyle)
            ).project()
            IndeterminateLinearProgressProjection(
                contentModel = IndeterminateProgressContentModel(enabled = false),
            ).project()
        }

    }
}

@Composable
fun AuroraWindowScope.DemoProgressContent(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    resourceBundle: ResourceBundle
) {
    var alignment by remember { mutableStateOf(DemoAlignment.Center) }
    var style by remember {
        mutableStateOf(
            DemoStyle(
                bold = true,
                italic = true,
                underline = false,
                strikethrough = false,
            )
        )
    }

    val alignmentCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.getString("Justify.center"),
                icon = format_justify_center(),
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Center),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Center
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Center justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Center justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.left"),
                icon = format_justify_left(),
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Left),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Left
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Left justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Left justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.right"),
                icon = format_justify_right(),
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Right),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Right
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Right justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Right justify preview canceled!")
                    }
                }
            ),
            Command(
                text = resourceBundle.getString("Justify.fill"),
                icon = format_justify_fill(),
                isActionToggle = true,
                isActionToggleSelected = (alignment == DemoAlignment.Fill),
                onTriggerActionToggleSelectedChange = {
                    if (it) alignment = DemoAlignment.Fill
                },
                actionPreview = object : CommandActionPreview {
                    override fun onCommandPreviewActivated(command: BaseCommand) {
                        println("Fill justify preview activated!")
                    }

                    override fun onCommandPreviewCanceled(command: BaseCommand) {
                        println("Fill justify preview canceled!")
                    }
                }
            )
        )
    )

    val styleCommands = CommandGroup(
        commands = listOf(
            Command(
                text = resourceBundle.getString("FontStyle.bold.title"),
                icon = format_text_bold(),
                isActionToggle = true,
                isActionToggleSelected = style.bold,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(bold = it)
                    println("Selected bold? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.italic.title"),
                icon = format_text_italic(),
                isActionToggle = true,
                isActionToggleSelected = style.italic,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(italic = it)
                    println("Selected italic? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.underline.title"),
                icon = format_text_underline(),
                isActionToggle = true,
                isActionToggleSelected = style.underline,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(underline = it)
                    println("Selected underline? $it")
                }
            ),
            Command(
                text = resourceBundle.getString("FontStyle.strikethrough.title"),
                icon = format_text_strikethrough(),
                isActionToggle = true,
                isActionToggleSelected = style.strikethrough,
                onTriggerActionToggleSelectedChange = {
                    style = style.copy(strikethrough = it)
                    println("Selected strikethrough? $it")
                }
            )
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
            DemoToolbar(
                alignmentCommands = alignmentCommands,
                styleCommands = styleCommands,
                resourceBundle = resourceBundle
            )
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.None) {
            DemoProgressArea(
                modifier = Modifier.weight(weight = 1.0f, fill = true),
                onSkinChange = onSkinChange,
                resourceBundle = resourceBundle
            )
        }
    }
}



