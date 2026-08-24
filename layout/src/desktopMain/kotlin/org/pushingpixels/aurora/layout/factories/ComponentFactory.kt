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

import androidx.compose.ui.graphics.painter.Painter
import org.pushingpixels.aurora.layout.ComponentLambda

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * An interface that defines the factory methods as used by the
 * [PanelBuilder] and its subclasses.<p>
 *
 * @see    DefaultComponentFactory
 * @see    PanelBuilder
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
    public fun createButton(text: String, icon: Painter?, action: (() -> Unit)?, isEnabled: Boolean): ComponentLambda
}