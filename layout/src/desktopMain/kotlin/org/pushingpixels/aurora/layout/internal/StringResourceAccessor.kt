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

// This is a modified version of the original source code by Karsten Lentzsch
// and JGoodies Software GmbH available under the BSD license. See the full
// license under resources/Forms.license.

/**
 * Describes an object that can access a String resource via a key.
 *
 * This interface is used by the forms APIs to access Strings from
 * [ResourceBundle]s. Since [ResourceBundle]
 * does not implement this interface, a wrapper can be used that implements
 * this interface and just delegates to the [ResourceBundle] to look up
 * the String for a given resource key.
 *
 */
internal interface StringResourceAccessor {
    /**
     * Looks up and returns a String associated with the given resource key.
     * If no arguments are provided, the plain [String] is returned.
     * Otherwise the string will be formatted using [String.format]
     * with the given arguments.
     * 
     * @param key   the key in the resource bundle
     * @param args  optional format arguments forwarded to `String#format`
     * @return the String value found for the given resource key,
     * formatted with the optional arguments - if any
     * 
     * @see format
     */
    public fun getString(key: String, vararg args: Any): String
}
