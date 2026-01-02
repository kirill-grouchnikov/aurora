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
package org.pushingpixels.aurora.tools.svgtranscoder.gradle

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File

open class TranscodeTask : TranscodeBaseTask() {
    @get:Input
    @set:Option(
        option = "outputPackageName",
        description = "Configures the output package name."
    )
    var outputPackageName: String? = null

    @get:Input
    @set:Option(
        option = "outputClassNamePrefix",
        description = "Configures the output class name prefix."
    )
    var outputClassNamePrefix = ""

    @get:InputDirectory
    @set:Option(
        option = "inputDirectory",
        description = "Configures the input directory."
    )
    var inputDirectory: File? = null

    @get:OutputDirectory
    @set:Option(
        option = "outputDirectory",
        description = "Configures the output directory."
    )
    var outputDirectory: File? = null

    @TaskAction
    fun transcode() {
        val logger = logger
        logger.trace("Working on files in " + inputDirectory!!.absolutePath)
        if (!inputDirectory!!.exists()) {
            return
        }
        outputDirectory!!.mkdirs()
        logger.trace(
            "Processing " + inputDirectory!!.absolutePath + " to " + outputPackageName
        )
        val templateFileName =
            "/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ"
        transcodeAllFilesInFolder(
            inputDirectory!!, outputDirectory!!,
            outputClassNamePrefix, outputPackageName!!, templateFileName
        )
    }
}