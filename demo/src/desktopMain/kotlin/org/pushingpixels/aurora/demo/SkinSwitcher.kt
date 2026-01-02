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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.PopupPlacementStrategy
import org.pushingpixels.aurora.theming.getAuroraSkins

@Composable
fun AuroraSkinSwitcher(
    onSkinChange: (AuroraSkinDefinition) -> Unit,
    popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart
) {
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
                onSkinChange.invoke(it.second.invoke())
            }
        ),
        presentationModel = ComboBoxPresentationModel(
            displayConverter = { it.first },
            popupPlacementStrategy = popupPlacementStrategy
        )
    ).project()
}
