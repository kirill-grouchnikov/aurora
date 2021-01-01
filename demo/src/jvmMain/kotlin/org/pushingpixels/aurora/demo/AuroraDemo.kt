/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.demo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.*
import org.pushingpixels.aurora.component.*
import org.pushingpixels.aurora.icon.AuroraIcon
import org.pushingpixels.aurora.icon.AuroraThemedFollowTextIcon
import org.pushingpixels.aurora.icon.AuroraThemedIcon
import org.pushingpixels.aurora.skin.marinerSkin
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow

fun main() {
    AuroraWindow(
        skin = marinerSkin(),
        title = "Aurora Demo",
        size = IntSize(660, 680),
        undecorated = true
    ) {
        DemoContent()
    }
}

data class Person(val firstName: String, val lastName: String)

@Composable
fun DemoArea(modifier: Modifier = Modifier, selected: Boolean = false) {
    // TODO - convert this to use ConstraintLayout
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(8.dp)
    ) {
        val enabled = remember { mutableStateOf(true) }
        AuroraCheckBox(
            selected = true,
            onSelectedChange = { enabled.value = !enabled.value }
        ) {
            AuroraText(text = "content enabled")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraCheckBox(
                    selected = selected,
                    onSelectedChange = { println("Selected checkbox? $it") },
                    enabled = enabled.value
                ) {
                    AuroraText(text = "sample check")
                }
                Spacer(modifier = Modifier.width(8.dp))
                AuroraRadioButton(
                    selected = selected,
                    onSelectedChange = { println("Selected radio? $it") },
                    enabled = enabled.value
                ) {
                    AuroraText(text = "sample radio")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = selected,
                    onSelectedChange = { println("Selected toggle? $it") }) {
                    // This is a full-color icon. Use original colors for enabled and active states,
                    // and color scheme based filtering for disabled states
                    AuroraThemedIcon(
                        icon = computer.of(10.dp, 10.dp),
                        disabledFilterStrategy = IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
                        enabledFilterStrategy = IconFilterStrategy.ORIGINAL,
                        activeFilterStrategy = IconFilterStrategy.ORIGINAL,
                        modifier = Modifier.auroraButtonIconPadding()
                    )
                    AuroraText("toggle")
                }
                AuroraButton(
                    enabled = enabled.value,
                    onClick = { println("Clicked!") },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.NEVER
                ) {
                    AuroraText("never")
                }
                AuroraButton(
                    enabled = enabled.value,
                    onClick = { println("Clicked!") },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.FLAT
                ) {
                    AuroraThemedFollowTextIcon(
                        icon = account_box_24px.of(10.dp, 10.dp),
                        modifier = Modifier.auroraButtonIconPadding()
                    )
                    AuroraText("flat")
                }
                AuroraButton(
                    enabled = enabled.value,
                    onClick = { println("Clicked!") },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                ) {
                    AuroraThemedFollowTextIcon(
                        icon = keyboard_capslock_24px.of(10.dp, 10.dp),
                        modifier = Modifier.auroraButtonIconPadding()
                    )
                    AuroraText("always")
                }

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCircularProgress(enabled = enabled.value)

                Spacer(modifier = Modifier.width(8.dp))

                AuroraCircularProgress(modifier = Modifier.size(14.dp), enabled = enabled.value)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = false,
                    onSelectedChange = { println("Selected bold? $it") },
                    sides = ButtonSides(straightSides = setOf(Side.END)),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
                ) {
                    AuroraIcon(
                        icon = Format_text_bold.of(10.dp, 10.dp),
                    )
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = true,
                    onSelectedChange = { println("Selected italic? $it") },
                    sides = ButtonSides(
                        straightSides = setOf(Side.START, Side.END),
                        openSides = setOf(Side.START, Side.END)
                    ),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
                ) {
                    AuroraIcon(
                        icon = Format_text_italic.of(10.dp, 10.dp),
                    )
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = false,
                    onSelectedChange = { println("Selected under? $it") },
                    sides = ButtonSides(
                        straightSides = setOf(Side.START, Side.END),
                        openSides = setOf(Side.START, Side.END)
                    ),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
                ) {
                    AuroraIcon(
                        icon = Format_text_underline.of(10.dp, 10.dp),
                    )
                }
                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = false,
                    onSelectedChange = { println("Selected strike? $it") },
                    sides = ButtonSides(straightSides = setOf(Side.START)),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
                ) {
                    AuroraIcon(
                        icon = Format_text_strikethrough.of(10.dp, 10.dp),
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = true,
                    onSelectedChange = { println("Selected small? $it") },
                    sides = ButtonSides(straightSides = Side.values().toSet()),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(all = 2.dp)
                ) {
                    AuroraThemedFollowTextIcon(icon = star_black_48dp.of(12.dp, 12.dp))
                }

                Spacer(modifier = Modifier.width(8.dp))

                AuroraToggleButton(
                    enabled = enabled.value,
                    selected = true,
                    onSelectedChange = { println("Selected small? $it") },
                    sides = ButtonSides(straightSides = Side.values().toSet()),
                    sizingStrategy = ButtonSizingStrategy.COMPACT,
                    contentPadding = PaddingValues(all = 6.dp)
                ) {
                    AuroraThemedFollowTextIcon(icon = star_black_48dp.of(12.dp, 12.dp))
                }

                Spacer(modifier = Modifier.width(20.dp))

                AuroraComboBox(
                    enabled = enabled.value,
                    items = listOf("one", "two", "three"),
                    displayConverter = { it },
                    onItemSelected = { println("$it selected!") },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )

                Spacer(modifier = Modifier.width(8.dp))

                AuroraComboBox(
                    enabled = enabled.value,
                    items = listOf(Person("Bob", "Loblaw"), Person("Paige", "Turner"), Person("Donaldson", "Duck")),
                    displayConverter = { it.lastName + ", " + it.firstName },
                    onItemSelected = { println("$it selected!") },
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.ALWAYS
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                AuroraIndeterminateLinearProgress(
                    enabled = enabled.value
                )
            }
        }
    }
}

@Composable
fun DemoContent() {
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.HEADER) {
            DemoArea(selected = false)
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.TOOLBAR) {
            DemoArea(selected = true)
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.NONE) {
            DemoArea(selected = true)
        }
        AuroraDecorationArea(decorationAreaType = DecorationAreaType.FOOTER) {
            DemoArea(selected = false)
        }
    }
}



