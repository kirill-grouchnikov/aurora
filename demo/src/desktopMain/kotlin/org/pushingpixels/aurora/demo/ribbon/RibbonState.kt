/*
 * Copyright 2020-2023 Aurora, Kirill Grouchnikov
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
package org.pushingpixels.aurora.demo.ribbon

enum class DocumentStyle {
    Style1, Style2, Style3, Style4, Style5, Style6, Style7, Style8, Style9, Style10,
    Style11, Style12, Style13, Style14, Style15, Style16, Style17, Style18, Style19, Style20,
    Style21, Style22, Style23, Style24, Style25, Style26, Style27, Style28, Style29, Style30
}

enum class FontFamily {
    Calibri, Columbus, Consolas, Cornelius,
    Cleopatra, Cornucopia, California, Calendula,
    Coriander, Callisto, Cajun, Congola,
    Candella, Cambria
}

data class RibbonState(
    val documentStyle: DocumentStyle,
    val fontFamily: FontFamily
)
