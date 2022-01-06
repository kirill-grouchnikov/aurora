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

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.io.InputStream
import java.util.*

/**
 * A single item in the breadcrumb bar model.
 */
data class BreadcrumbItem<T>(val displayName: String, val icon: Painter?, val data: T)

/**
 * Content provider for a breadcrumb bar.
 */
interface BreadcrumbBarContentProvider<T> {
    /**
     * Returns the choice elements that correspond to the specified path. If the
     * path is empty, `null` should be returned. If path is
     * `null`, the "root" elements should be returned
     *
     * @param path Breadcrumb bar path.
     * @return The choice elements that correspond to the specified path
     */
    suspend fun getPathChoices(path: List<BreadcrumbItem<T>>): List<BreadcrumbItem<T>>

    /**
     * Returns the leaf elements that correspond to the specified path. If the
     * path is empty, `null` should be returned. If path is
     * `null`, leaf content of the "root" elements should be returned. Most probably, if
     * your root is more than one element, you should be returning null in here.
     *
     * @param path Breadcrumb bar path.
     * @return The leaf elements that correspond to the specified path
     */
    suspend fun getLeaves(path: List<BreadcrumbItem<T>>): List<BreadcrumbItem<T>>

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

/**
 * Model for the breadcrumb bar component.
 */
class BreadcrumbBarContentModel<T>(val items: MutableList<BreadcrumbItem<T>>) {
    /**
     * Returns the index of the specified item.
     *
     * @param item Item.
     * @return Index of the item if it is in the model or -1 if it is not.
     */
    fun indexOf(item: BreadcrumbItem<T>): Int {
        return items.indexOf(item)
    }

    /**
     * Removes the last item in this model.
     */
    fun removeLast() {
        items.removeLast()
    }

    /**
     * Resets this model, removing all the items.
     */
    fun reset() {
        items.clear()
    }

    /**
     * Returns the number of items in this model.
     *
     * @return Number of items in this model.
     */
    val itemCount: Int = items.size

    /**
     * Returns the model item at the specified index.
     *
     * @param index Item index.
     * @return The model item at the specified index. Will return
     * `null` if the index is negative or larger than the
     * number of items.
     */
    fun getItem(index: Int): BreadcrumbItem<T>? {
        if (index < 0) return null
        return if (index >= itemCount) null else items[index]
    }

    /**
     * Replaces the current item list with the specified list.
     *
     * @param items New contents of the model.
     */
    fun replace(items: List<BreadcrumbItem<T>>) {
        this.items.clear()
        for (i in items.indices) {
            this.items.add(items[i])
        }
    }

    /**
     * Adds the specified item at the end of the path.
     *
     * @param item Item to add.
     */
    fun add(item: BreadcrumbItem<T>) {
        items.add(item)
    }
}


data class BreadcrumbBarPresentationModel(
    val presentationState: CommandButtonPresentationState = CommandButtonPresentationState.Medium,
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
    val iconActiveFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconEnabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.Original,
    val iconDisabledFilterStrategy: IconFilterStrategy = IconFilterStrategy.ThemedFollowColorScheme
) : PresentationModel
