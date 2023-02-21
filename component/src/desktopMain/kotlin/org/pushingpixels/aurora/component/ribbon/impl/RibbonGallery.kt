package org.pushingpixels.aurora.component.ribbon.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.ribbon.PresentationPriority
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryContentModel
import org.pushingpixels.aurora.component.ribbon.RibbonGalleryPresentationModel
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy

@Composable
internal fun RibbonGallery(
    modifier: Modifier,
    presentationPriority: PresentationPriority,
    contentModel: RibbonGalleryContentModel,
    presentationModel: RibbonGalleryPresentationModel
) {
    val visibleCount = presentationModel.preferredVisibleCommandCounts[presentationPriority]!!
    Row(modifier = modifier.wrapContentWidth().padding(presentationModel.contentPadding),
        horizontalArrangement = Arrangement.spacedBy(presentationModel.layoutGap)) {
        for (index in 0 until visibleCount) {
            CommandButtonProjection(
                contentModel = contentModel.commandGroups[0].commands[index],
                presentationModel = CommandButtonPresentationModel(
                    presentationState = presentationModel.commandButtonPresentationState,
                    backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat
                )
            ).project()
        }
    }
}