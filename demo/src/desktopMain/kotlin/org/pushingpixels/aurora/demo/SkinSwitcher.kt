package org.pushingpixels.aurora.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.skin.AuroraSkin
import org.pushingpixels.aurora.skin.AuroraSkinDefinition
import org.pushingpixels.aurora.skin.getAuroraSkins

@Composable
fun AuroraSkinSwitcher(auroraSkinDefinition: MutableState<AuroraSkinDefinition>) {
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
}