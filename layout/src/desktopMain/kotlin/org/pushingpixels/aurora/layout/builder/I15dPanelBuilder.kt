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
package org.pushingpixels.aurora.layout.builder

import org.pushingpixels.aurora.layout.CellConstraints
import org.pushingpixels.aurora.layout.ColumnSpec
import org.pushingpixels.aurora.layout.ComponentLambda
import org.pushingpixels.aurora.layout.RowSpec
import org.pushingpixels.aurora.layout.factories.ComponentFactory
import org.pushingpixels.aurora.layout.internal.ResourceBundleAccessor
import org.pushingpixels.aurora.layout.internal.StringResourceAccessor
import java.util.MissingResourceException
import java.util.ResourceBundle

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * A general purpose panel builder that uses the [org.pushingpixels.aurora.layout.FormLayout]
 * to lay out panels. In addition to its superclass [PanelBuilder] this class provides
 * convenience behavior to map resource keys to their associated internationalized (i15d) strings
 * when adding labels, titles and titled separators.
 *
 * Subclasses must implement the conversion from resource key
 * to the localized string in [getResourceString].
 * For example class [I15dPanelBuilder] gets a [ResourceBundle] during
 * construction, and requests strings from that bundle.
 *
 * @see [ResourceBundle]
 */
public open class I15dPanelBuilder(
    componentFactory: ComponentFactory,
    colSpecs: List<ColumnSpec>,
    rowSpecs: List<RowSpec>,
    bundle: ResourceBundle
) : PanelBuilder(componentFactory, colSpecs, rowSpecs) {
    /**
     * Holds the ResourceBundle used to look up internationalized
     * (i15d) String resources.
     */
    private val resources: StringResourceAccessor = ResourceBundleAccessor(bundle)

    // Adding Labels and Separators *****************************************
    /**
     * Adds an internationalized (i15d) textual label to the form using the
     * specified constraints.
     * 
     * @param resourceKey    the resource key for the label's text
     * @param constraints    the label's cell constraints
     */
    public fun i15dLabel(resourceKey: String, constraints: CellConstraints) {
        label(getResourceString(resourceKey), constraints)
    }

    /**
     * Adds an internationalized (i15d) textual label to the form using the
     * specified constraints.
     * 
     * @param resourceKey         the resource key for the label's text
     * @param encodedConstraints  a string representation for the constraints
     */
    public fun i15dLabel(resourceKey: String, encodedConstraints: String) {
        label(getResourceString(resourceKey), encodedConstraints)
    }

    /**
     * Adds an internationalized (i15d) label and component to the panel using
     * the given cell constraints.
     *
     * @param resourceKey           the resource key for the label
     * @param labelConstraints      the label's cell constraints
     * @param component             the component to add
     * @param componentConstraints  the component's cell constraints
     */
    public fun i15dLabel(
        resourceKey: String, labelConstraints: CellConstraints,
        component: ComponentLambda, componentConstraints: CellConstraints
    ) {
        label(
            getResourceString(resourceKey), labelConstraints,
            component, componentConstraints
        )
    }

    // Adding Labels for Read-Only Fields *************************************
    /**
     * Adds an internationalized (i15d) textual label to the form using the
     * specified constraints that is intended to label a read-only component.
     * 
     * @param resourceKey   the resource key for the label's text
     * @param constraints   the label's cell constraints
     */
    public fun i15dROLabel(resourceKey: String, constraints: CellConstraints) {
        readOnlyLabel(getResourceString(resourceKey), constraints)
    }

    /**
     * Adds an internationalized (i15d) textual label to the form using the
     * specified constraints that is intended to label a read-only component.
     * 
     * @param resourceKey         the resource key for the label's text
     * @param encodedConstraints  a string representation for the constraints
     */
    public fun i15dROLabel(resourceKey: String, encodedConstraints: String) {
        readOnlyLabel(resourceKey, encodedConstraints)
    }

    /**
     * Adds an internationalized (i15d) label and component to the panel using
     * the given cell constraints. Intended for read-only components.
     */
    public fun i15dROLabel(
        resourceKey: String, labelConstraints: CellConstraints,
        component: ComponentLambda, componentConstraints: CellConstraints
    ) {
        readOnlyLabel(
            getResourceString(resourceKey), labelConstraints,
            component, componentConstraints
        )
    }

    // Adding Titled Separators ***********************************************
    /**
     * Adds an internationalized (i15d) titled separator to the form using the
     * specified constraints.
     * 
     * @param resourceKey  the resource key for the separator title
     * @param constraints  the separator's cell constraints
     */
    public fun i15dSeparator(resourceKey: String, constraints: CellConstraints) {
        separator(getResourceString(resourceKey), constraints)
    }

    /**
     * Adds an internationalized (i15d)  titled separator to the form using
     * the specified constraints.
     * 
     * @param resourceKey         the resource key for the separator title
     * @param encodedConstraints  a string representation for the constraints
     */
    public fun i15dSeparator(resourceKey: String, encodedConstraints: String) {
        separator(resourceKey, encodedConstraints)
    }

    /**
     * Adds a title to the form using the specified constraints.
     * 
     * @param resourceKey  the resource key for  the separator title
     * @param constraints  the separator's cell constraints
     */
    public fun i15dTitle(resourceKey: String, constraints: CellConstraints) {
        title(getResourceString(resourceKey), constraints)
    }

    /**
     * Adds a title to the form using the specified constraints.
     * 
     * @param resourceKey         the resource key for the separator title
     * @param encodedConstraints  a string representation for the constraints
     * @return the added title label
     */
    public fun i15dTitle(resourceKey: String, encodedConstraints: String) {
        title(resourceKey, encodedConstraints)
    }

    /**
     * Looks up and returns the internationalized (i15d) string for the given
     * resource key, for example from a [ResourceBundle].
     * 
     * @param key  the key to look for in the resource map
     * @return the associated internationalized string, or the resource key
     *      itself in case of a missing resource
     */
    protected fun getResourceString(key: String): String {
        return try {
            this.resources.getString(key)
        } catch (_: MissingResourceException) {
            key
        }
    }
}
