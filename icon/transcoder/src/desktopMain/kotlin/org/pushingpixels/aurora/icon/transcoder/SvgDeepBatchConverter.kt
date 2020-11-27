/*
 * Copyright (c) 2020 Aurora, Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.aurora.icon.transcoder

import org.pushingpixels.aurora.icon.transcoder.KotlinLanguageRenderer
import org.pushingpixels.aurora.icon.transcoder.LanguageRenderer
import org.pushingpixels.aurora.icon.transcoder.SvgBatchBaseConverter
import java.io.File
import java.io.IOException
import java.nio.file.NoSuchFileException
import java.util.*
import java.util.stream.Stream

class SvgDeepBatchConverter : SvgBatchBaseConverter() {
    protected fun processFolder(
        inputFolder: File, outputFolder: File?,
        outputClassNamePrefix: String?, outputFileNameExtension: String?,
        outputPackageName: String?, languageRenderer: LanguageRenderer?,
        templateFile: String?
    ) {
        println(
            "******************************************************************************"
        )
        println("Processing")
        println("\tsource folder: " + inputFolder.absolutePath)
        println("\tpackage name: $outputPackageName")
        println(
            "******************************************************************************"
        )

        // Transcode all SVG files in this folder
        transcodeAllFilesInFolder(
            inputFolder, outputFolder!!, outputClassNamePrefix, outputFileNameExtension!!,
            outputPackageName!!, languageRenderer!!, templateFile!!
        )

        // Now scan the folder for sub-folders
        for (inputSubfolder in inputFolder.listFiles { directory: File?, name: String? ->
            File(directory, name).isDirectory
        }) {
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
                "$outputPackageName.$subfolderName", languageRenderer,
                templateFile
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
                System.exit(1)
            }
            val converter = SvgDeepBatchConverter()
            val sourceRootFolderName = converter.getInputArgument(args, "sourceRootFolder", null)
            Objects.requireNonNull(sourceRootFolderName, "Missing source folder. " + CHECK_DOCUMENTATION)
            val outputRootPackageName = converter.getInputArgument(args, "outputRootPackageName", null)
            Objects.requireNonNull(outputRootPackageName, "Missing output package name. " + CHECK_DOCUMENTATION)
            val templateFile = converter.getInputArgument(args, "templateFile", null)
            Objects.requireNonNull(templateFile, "Missing template file. " + CHECK_DOCUMENTATION)
            val languageRenderer: LanguageRenderer = KotlinLanguageRenderer()
            val outputFileNameExtension = ".kt"
            val outputClassNamePrefix = converter.getInputArgument(args, "outputClassNamePrefix", "")
            val outputRootFolderName = converter.getInputArgument(args, "outputRootFolder", sourceRootFolderName)
            val inputRootFolder = File(sourceRootFolderName)
            if (!inputRootFolder.exists()) {
                throw NoSuchFileException(sourceRootFolderName)
            }
            val outputRootFolder = File(outputRootFolderName)
            if (!outputRootFolder.exists()) {
                throw NoSuchFileException(outputRootFolderName)
            }
            println(
                "******************************************************************************"
            )
            println("Processing")
            println("\tsource root folder: $sourceRootFolderName")
            println("\troot package name: $outputRootPackageName")
            println(
                "******************************************************************************"
            )
            println()
            converter.processFolder(
                inputRootFolder, outputRootFolder, outputClassNamePrefix, outputFileNameExtension,
                outputRootPackageName, languageRenderer, templateFile
            )
        }
    }
}