/*
 * Copyright 2020-2022 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.component.model

import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.io.InputStream

/**
 * Content provider for a breadcrumb bar.
 */
interface BreadcrumbBarContentProvider<T> {
    /** Returns the display text for the item, or for the root is null is passed. */
    fun getDisplayText(item: T?): String

    /** Returns the icon for the item, or for the root is null is passed. */
    fun getIcon(item: T?): Painter? = null

    /**
     * Returns the choice elements that correspond to the specified path. If the
     * path is empty, `null` should be returned. If path is
     * `null`, the "root" elements should be returned
     *
     * @param path Breadcrumb bar path.
     * @return The choice elements that correspond to the specified path
     */
    suspend fun getPathChoices(item: T?): List<T>

    /**
     * Returns the leaf elements that correspond to the specified path. If the
     * path is empty, `null` should be returned. If path is
     * `null`, leaf content of the "root" elements should be returned. Most probably, if
     * your root is more than one element, you should be returning null in here.
     *
     * @param path Breadcrumb bar path.
     * @return The leaf elements that correspond to the specified path
     */
    suspend fun getLeaves(item: T): List<T>

    /**
     * Returns the input stream with the leaf content. Some implementations may
     * return `null` if this is not applicable.
     *
     * @param leaf Leaf.
     * @return Input stream with the leaf content. May be `null` if
     * this is not applicable.
     */
    suspend fun getLeafContent(leaf: T): InputStream? {
        return null
    }
}

data class BreadcrumbBarPresentationModel(
    val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme,
    val maxVisibleChoiceCommands: Int = 10,
) : PresentationModel
