package org.pushingpixels.aurora.tools.screenshot

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.tools.screenshot.svg.tango.*
import org.pushingpixels.aurora.window.AuroraApplicationScope
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindowContent
import org.pushingpixels.aurora.window.AuroraWindowScope
import java.util.*

private val ToolbarIconSize = 22.dp

private class ScreenshotScope(
    private val applicationScope: AuroraApplicationScope,
    original: WindowScope
) : AuroraWindowScope {
    override var applicationLocale: Locale
        get() = applicationScope.applicationLocale
        set(value) {
            applicationScope.applicationLocale = value
        }

    override val window = original.window
}

@Composable
private fun AuroraApplicationScope.ScreenshotWindow(
    windowScope: WindowScope,
    skin: AuroraSkinDefinition,
    state: WindowState,
    title: String,
    icon: Painter,
    menuCommands: CommandGroup,
    content: @Composable AuroraWindowScope.() -> Unit
) {
    val density = LocalDensity.current
    val screenshotScope = ScreenshotScope(this@ScreenshotWindow, windowScope)
    CompositionLocalProvider(
        LocalWindowSize provides state.size,
        LocalDensity provides density,
        LocalDecorationAreaType provides DecorationAreaType.None,
        LocalDisplayName provides skin.displayName,
        LocalSkinColors provides skin.colors,
        LocalButtonShaper provides skin.buttonShaper,
        LocalPainters provides skin.painters,
        LocalAnimationConfig provides AnimationConfig(),
    ) {
        screenshotScope.AuroraWindowContent(
            title = title,
            icon = icon,
            iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
            undecorated = true,
            menuCommands = menuCommands,
            content = content
        )
    }
}

@Composable
fun AuroraApplicationScope.ScreenshotContent(
    windowScope: WindowScope,
    skin: AuroraSkinDefinition,
    state: WindowState,
    title: String,
    icon: Painter
) {
    ScreenshotWindow(
        windowScope = windowScope,
        skin = skin,
        state = state,
        title = title,
        icon = icon,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(text = "Skins", action = {}),
                Command(text = "Test", action = {})
            )
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
                ScreenshotToolbar()
            }
            Row(
                modifier = Modifier.fillMaxSize().auroraBackground().padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Hello screenshot!!!",
                        action = {}
                    )
                ).project()
            }
        }
    }
}

@Composable
private fun ScreenshotToolbar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        CommandButtonProjection(
            contentModel = Command(
                text = "Cut",
                icon = edit_cut(),
                action = { println("Cut!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Copy",
                icon = edit_copy(),
                isActionEnabled = false,
                action = { println("Copy!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Paste",
                icon = edit_paste(),
                action = { println("Paste!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Select All",
                icon = edit_select_all(),
                action = { println("Select all!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Delete",
                icon = edit_delete(),
                action = { println("Delete!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()

        Spacer(modifier = Modifier.width(4.dp))
        VerticalSeparatorProjection().project(modifier = Modifier.height(ToolbarIconSize))
        Spacer(modifier = Modifier.width(4.dp))

        CommandButtonProjection(
            contentModel = Command(
                text = "Center",
                icon = format_justify_center(),
                action = { println("Center!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Left",
                icon = format_justify_left(),
                action = { println("Left!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Right",
                icon = format_justify_right(),
                action = { println("Right!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Fill",
                icon = format_justify_fill(),
                action = { println("Fill!") }
            ),
            presentationModel = CommandButtonPresentationModel(
                presentationState = CommandButtonPresentationState.SmallFitToIcon,
                backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                iconDimension = ToolbarIconSize
            )
        ).project()
    }
}
