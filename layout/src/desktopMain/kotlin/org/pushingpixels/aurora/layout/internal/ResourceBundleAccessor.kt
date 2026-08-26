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
package org.pushingpixels.aurora.layout.internal

import java.util.*

/**
 * Turns a ResourceBundle into a [StringResourceAccessor].
 *
 * **Note:** This class is not part of the public JGoodies Common API.
 * It's intended for implementation purposes only.
 * The class's API may change at any time.
 * 
 * @author  Karsten Lentzsch
 * 
 * @since 1.8
 */
internal class ResourceBundleAccessor(private val bundle: ResourceBundle) : StringResourceAccessor {
    /**
     * {@inheritDoc}
     *
     * In case the resource key is missing in the bundle, this implementation returns the key itself.
     *
     * @return the String value found for the given resource key, formatted with the optional arguments
     * - if any, or - if the key is missing in the bundle -  the resource key itself
     */
    override fun getString(key: String, vararg args: Any): String {
        try {
            val string = bundle.getString(key)
            return if (args.isEmpty()) string else String.format(string, args)
        } catch (_: MissingResourceException) {
            return key
        }
    }
}
