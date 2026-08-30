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
package org.pushingpixels.aurora.layout.factories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.ComponentLambda

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An interface that defines the factory methods as used by the
 * [org.pushingpixels.aurora.layout.builder.PanelBuilder] and its subclasses.<p>
 *
 * @see    [org.pushingpixels.aurora.layout.builder.PanelBuilder]
 */
public interface ComponentFactory {
    /**
     * Creates and returns a button that is bound to the given parameters.
     *
     * @param text         the button text
     * @param icon         optional button icon
     * @param action       optional button action
     * @param isEnabled    button enabled status
     * @return the created button
     */
    public fun createButton(text: String, icon: Painter?, action: () -> Unit, isEnabled: Boolean): ComponentLambda

    /**
     * Creates and returns a label.
     *
     * @param text         the label text
     * @return the created label
     */
    public fun createLabel(text: String): ComponentLambda

    /**
     * Creates and returns a label that is intended to label a read-only component.
     *
     * @param text         the label text
     * @return the created label intended for read-only components
     */
    public fun createReadOnlyLabel(text: String): ComponentLambda

    /**
     * Creates and returns a title label.
     *
     * @param text         the label text
     * @return the emphasized title label
     */
    public fun createTitle(text: String): ComponentLambda

    /**
     * Creates and returns a label intended for pane headers that uses
     * a larger font and a special foreground color.
     *
     * @param text         the label text
     * @return the label intended for pane headers
     */
    public fun createHeaderLabel(text: String): ComponentLambda

    /**
     * Creates and returns a labeled separator. Useful to separate
     * paragraphs in a panel.
     *
     * @param text         the label text
     * @param arrangement  text arrangement. One of [Arrangement.Start], [Arrangement.End], [Arrangement.Center].
     * @return the title label with separator(s)
     */
    public fun createSeparator(text: String, arrangement: Arrangement.Horizontal): ComponentLambda
}