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
package org.pushingpixels.aurora.tools.svgtranscoder

import java.io.File
import java.io.IOException
import java.nio.file.NoSuchFileException
import java.util.*
import java.util.stream.Stream
import kotlin.system.exitProcess

class SvgDeepBatchConverter : SvgBatchBaseConverter() {
    private fun processFolder(
        inputFolder: File, outputFolder: File,
        outputClassNamePrefix: String, outputFileNameExtension: String,
        outputPackageName: String, templateFile: String
    ) {
        println("******************************************************************************")
        println("Processing")
        println("\tsource folder: " + inputFolder.absolutePath)
        println("\tpackage name: $outputPackageName")
        println("******************************************************************************")

        // Transcode all SVG files in this folder
        transcodeAllFilesInFolder(
            inputFolder, outputFolder, outputClassNamePrefix, outputFileNameExtension,
            outputPackageName, templateFile
        )

        // Now scan the folder for sub-folders
        inputFolder
            .walk(direction = FileWalkDirection.TOP_DOWN)
            .maxDepth(1)
            .filter { it.isDirectory && (it.absolutePath != inputFolder.absolutePath) }
            .forEach { inputSubfolder ->
                val subfolderName = inputSubfolder.name
                println("Going into sub-folder $subfolderName")

                // Mirror the input subfolder structure to the output
                val outputSubfolder = File(outputFolder, subfolderName)
                if (!outputSubfolder.exists()) {
                    outputSubfolder.mkdir()
                }

                // And recursively process SVG content (and possible folders)
                processFolder(
                    inputSubfolder, outputSubfolder, outputClassNamePrefix, outputFileNameExtension,
                    "$outputPackageName.$subfolderName", templateFile
                )
            }

        println()
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size < 3) {
                println("=== Usage ===")
                Stream.of(
                    "java " + SvgDeepBatchConverter::class.java.canonicalName,
                    "  sourceRootFolder=xyz - points to the root folder to traverse for SVG images",
                    "  outputRootPackageName=xyz - the root package name for the transcoded classes",
                    "  templateFile=xyz - the template file for creating the transcoded classes",
                    "  outputRootFolder=xyz - optional root location of output files. If not specified, output files will be placed under the 'sourceRootFolder'",
                    "  outputClassNamePrefix=xyz - optional prefix for the class name of each transcoded class"
                ).forEach { x: String? -> println(x) }
                println(CHECK_DOCUMENTATION)
                exitProcess(1)
            }
            val converter = SvgDeepBatchConverter()
            val sourceRootFolderName = converter.getInputArgument(args, "sourceRootFolder", null)
            Objects.requireNonNull(sourceRootFolderName, "Missing source folder. $CHECK_DOCUMENTATION")
            val outputRootPackageName = converter.getInputArgument(args, "outputRootPackageName", null)
            Objects.requireNonNull(outputRootPackageName, "Missing output package name. $CHECK_DOCUMENTATION")
            val templateFile = converter.getInputArgument(args, "templateFile", null)
            Objects.requireNonNull(templateFile, "Missing template file. $CHECK_DOCUMENTATION")
            val outputFileNameExtension = ".kt"
            val outputClassNamePrefix = converter.getInputArgument(args, "outputClassNamePrefix", "")!!
            val outputRootFolderName = converter.getInputArgument(args, "outputRootFolder", sourceRootFolderName)
            val inputRootFolder = File(sourceRootFolderName)
            if (!inputRootFolder.exists()) {
                throw NoSuchFileException(sourceRootFolderName)
            }
            val outputRootFolder = File(outputRootFolderName)
            if (!outputRootFolder.exists()) {
                throw NoSuchFileException(outputRootFolderName)
            }
            println("******************************************************************************")
            println("Processing")
            println("\tsource root folder: $sourceRootFolderName")
            println("\troot package name: $outputRootPackageName")
            println("******************************************************************************")
            println()
            converter.processFolder(
                inputRootFolder, outputRootFolder, outputClassNamePrefix, outputFileNameExtension,
                outputRootPackageName!!, templateFile!!
            )
        }
    }
}
