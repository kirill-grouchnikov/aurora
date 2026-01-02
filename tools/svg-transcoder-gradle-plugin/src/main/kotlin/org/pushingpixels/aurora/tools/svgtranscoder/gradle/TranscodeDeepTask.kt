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

open class TranscodeDeepTask : TranscodeBaseTask() {
    @get:Input
    @set:Option(
        option = "outputRootPackageName",
        description = "Configures the output root package name."
    )
    var outputRootPackageName: String? = null

    @get:Input
    @set:Option(
        option = "outputClassNamePrefix",
        description = "Configures the output class name prefix."
    )
    var outputClassNamePrefix = ""

    @get:InputDirectory
    @set:Option(
        option = "inputRootDirectory",
        description = "Configures the input root directory."
    )
    var inputRootDirectory: File? = null

    @get:OutputDirectory
    @set:Option(
        option = "outputRootDirectory",
        description = "Configures the output root directory."
    )
    var outputRootDirectory: File? = null

    @TaskAction
    fun transcode() {
        val logger = logger
        logger.trace("Working on files in " + inputRootDirectory!!.absolutePath)
        if (!inputRootDirectory!!.exists()) {
            return
        }
        outputRootDirectory!!.mkdirs()
        logger.trace(
            "Processing " + inputRootDirectory!!.absolutePath + " to " + outputRootPackageName
        )
        val templateFileName =
            "/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ"
        processFolder(
            inputRootDirectory!!, outputRootDirectory!!, outputClassNamePrefix,
            outputRootPackageName!!, templateFileName
        )
    }

    protected fun processFolder(
        inputFolder: File, outputFolder: File,
        outputClassNamePrefix: String, outputPackageName: String,
        templateFile: String
    ) {
        logger.trace("Working on files in " + inputFolder.absolutePath)

        // Transcode all SVG files in this folder
        transcodeAllFilesInFolder(
            inputFolder, outputFolder, outputClassNamePrefix,
            outputPackageName, templateFile
        )

        // Now scan the folder for sub-folders
        for (inputSubfolder in inputFolder.listFiles { directory: File?, name: String? ->
            File(directory, name).isDirectory
        }) {
            val subfolderName = inputSubfolder.name
            logger.trace("Going into sub-folder $subfolderName")

            // Mirror the input subfolder structure to the output
            val outputSubfolder = File(outputFolder, subfolderName)
            if (!outputSubfolder.exists()) {
                outputSubfolder.mkdir()
            }

            // And recursively process SVG content (and possible folders)
            processFolder(
                inputSubfolder, outputSubfolder, outputClassNamePrefix,
                "$outputPackageName.$subfolderName",
                templateFile
            )
        }
        logger.trace("")
    }
}