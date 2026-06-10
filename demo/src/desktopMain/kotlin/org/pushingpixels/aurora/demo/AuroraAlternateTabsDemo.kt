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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.pushingpixels.aurora.common.byAlpha
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.TabContentModel
import org.pushingpixels.aurora.component.model.TabsContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TabsProjection
import org.pushingpixels.aurora.demo.svg.radiance_menu
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.AuroraSkinColors
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.ComponentState
import org.pushingpixels.aurora.theming.ContainerColorTokens
import org.pushingpixels.aurora.theming.ContainerColorTokensAssociationKind
import org.pushingpixels.aurora.theming.ContainerColorTokensOverlay
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.decoration.AuroraDecorationArea
import org.pushingpixels.aurora.theming.decorator.AuroraDecorators
import org.pushingpixels.aurora.theming.decorator.tab.DefaultTabDecorator
import org.pushingpixels.aurora.theming.decorator.window.DefaultWindowDecorator
import org.pushingpixels.aurora.theming.geminiSkin
import org.pushingpixels.aurora.theming.painter.decoration.AuroraDecorationPainter
import org.pushingpixels.aurora.theming.shaper.ClassicComponentShaper
import org.pushingpixels.aurora.theming.shaper.OutlineSupplier
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.AuroraWindowScope
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import org.pushingpixels.aurora.window.auroraApplication
import java.text.MessageFormat
import java.util.*

fun geminiSkinWithUnderlineTabs(): AuroraSkinDefinition {
    return geminiSkin().copy(decorators = AuroraDecorators(
        tabDecorator = object: DefaultTabDecorator() {
            override fun getTabExtraPadding(): PaddingValues {
                return PaddingValues(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 3.dp)
            }

            override fun shouldDrawUnbrokenContentEdge(): Boolean {
                return true
            }

            @Composable
            override fun getDecoratedTabContentColor(
                currState: ComponentState,
                activeStates: Map<ComponentState, Float>,
                parentDecorationAreaType: DecorationAreaType,
                tokensOverlayProvider: ContainerColorTokensOverlay.Provider?,
                backgroundAppearanceStrategy: BackgroundAppearanceStrategy
            ): Color {
                val skinColors = AuroraSkin.colors

                val parentSurfaceTokens = skinColors.getNeutralContainerTokens(
                    decorationAreaType = parentDecorationAreaType,
                    associationKind = ContainerColorTokensAssociationKind.Tab
                )

                val alpha = if (currState.isDisabled) parentSurfaceTokens.onContainerDisabledAlpha
                else parentSurfaceTokens.onContainerEnabledAlpha
                return parentSurfaceTokens.onContainer.byAlpha(alpha)
            }

            override fun paintTabSurface(
                drawScope: DrawScope,
                skinColors: AuroraSkinColors,
                decorationAreaType: DecorationAreaType,
                decorationPainter: AuroraDecorationPainter,
                outlineFill: Outline,
                density: Density,
                rootSize: Size,
                offsetFromRoot: Offset,
                size: Size,
                surfaceColorTokens: ContainerColorTokens,
                alpha: Float
            ) {}

            override fun paintTabSurfaceHighlight(
                drawScope: DrawScope,
                outlineSupplier: OutlineSupplier,
                density: Density,
                size: Size,
                surfaceHighlightColorTokens: ContainerColorTokens,
                alpha: Float
            ) {
                with(drawScope) {
                    drawRect(
                        color = surfaceHighlightColorTokens.containerSurfaceHighest,
                        topLeft = Offset(0.0f, size.height - 3.dp.toPx()),
                        size = Size(size.width, 3.dp.toPx()),
                        style = Fill,
                        alpha = alpha
                    )
                }
            }

            override fun paintTabOutline(
                drawScope: DrawScope,
                outlineSupplier: OutlineSupplier,
                density: Density,
                size: Size,
                outlineColor: Color,
                alpha: Float
            ) {}
        },
        windowDecorator = DefaultWindowDecorator()
    ))
}


fun main() = auroraApplication {
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(400.dp, 280.dp)
    )
    val skin = geminiSkinWithUnderlineTabs();
    val resourceBundle by derivedStateOf {
        ResourceBundle.getBundle("org.pushingpixels.aurora.demo.Resources", applicationLocale)
    }

    AuroraWindow(
        skin = skin,
        title = "Aurora tabs",
        state = state,
        windowTitlePaneConfiguration = AuroraWindowTitlePaneConfigurations.AuroraPlain(),
        icon = radiance_menu(),
        iconFilterStrategy = IconFilterStrategy.ThemedFollowText,
        onCloseRequest = ::exitApplication,
    ) {
        DemoTabsContent({ }, resourceBundle)
    }
}


@Composable
fun AuroraWindowScope.DemoAlternateTabsContent(
    resourceBundle: ResourceBundle
) {
    var state by remember { mutableStateOf(0) }
    val mf = MessageFormat(resourceBundle.getString("Tab.text"))

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AuroraDecorationArea(
                decorationAreaType = DecorationAreaType.None,
                componentShaper = ClassicComponentShaper.Instance
            ) {
                AuroraLocaleSwitcher(resourceBundle)
            }
        }
        TabsProjection(contentModel = TabsContentModel(
            tabs = listOf(1..7).flatten().map {
                TabContentModel(
                    text = mf.format(arrayOf<Any>(it)),
                    isEnabled = (it.mod(3) != 0)
                )
            },
            selectedTabIndex = state,
            onTriggerTabSelected = { state = it }
        )).project(modifier = Modifier.fillMaxWidth())

        LabelProjection(contentModel = LabelContentModel(text = "Text tab ${state + 1} selected")).project(
            modifier = Modifier.padding(all = 12.dp).align(Alignment.CenterHorizontally),
        )
    }
}



