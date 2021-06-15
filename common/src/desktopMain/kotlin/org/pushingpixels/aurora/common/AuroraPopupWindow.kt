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
package org.pushingpixels.aurora.common

import androidx.compose.desktop.ComposeWindow

private const val AURORA_POPUP_WINDOW_KEY = "aurora.popupWindow"

public fun ComposeWindow.markAsAuroraPopup() {
    this.rootPane.putClientProperty(AURORA_POPUP_WINDOW_KEY, true)
}

public val ComposeWindow.isAuroraPopup: Boolean
    get() = (this.rootPane.getClientProperty(AURORA_POPUP_WINDOW_KEY) == true)
