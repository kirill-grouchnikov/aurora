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

class SvgBatchConverter : SvgBatchBaseConverter() {
    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size < 3) {
                println("=== Usage ===")
                Stream.of(
                    "java " + SvgBatchConverter::class.java.canonicalName,
                    "  sourceFolder=xyz - points to a folder with SVG images",
                    "  outputPackageName=xyz - the package name for the transcoded classes",
                    "  templateFile=xyz - the template file for creating the transcoded classes",
                    "  outputFolder=xyz - optional location of output files. If not specified, output files will be placed in the 'sourceFolder'",
                    "  outputClassNamePrefix=xyz - optional prefix for the class name of each transcoded class"
                ).forEach { x: String? -> println(x) }
                println(SvgBatchBaseConverter.CHECK_DOCUMENTATION)
                exitProcess(1)
            }
            val converter = SvgBatchConverter()
            val sourceFolderName: String? = converter.getInputArgument(args, "sourceFolder", null)
            Objects.requireNonNull(
                sourceFolderName,
                "Missing source folder. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION
            )
            val outputPackageName: String? =
                converter.getInputArgument(args, "outputPackageName", null)
            Objects.requireNonNull(
                outputPackageName,
                "Missing output package name. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION
            )
            val templateFile: String? = converter.getInputArgument(args, "templateFile", null)
            Objects.requireNonNull(
                templateFile,
                "Missing template file. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION
            )
            val outputFileNameExtension = ".kt"
            val outputClassNamePrefix: String =
                converter.getInputArgument(args, "outputClassNamePrefix", "")!!
            val outputFolderName: String? =
                converter.getInputArgument(args, "outputFolder", sourceFolderName)
            Objects.requireNonNull(
                outputFolderName,
                "Missing output folder. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION
            )
            val inputFolder = File(sourceFolderName)
            if (!inputFolder.exists()) {
                throw NoSuchFileException(sourceFolderName)
            }
            val outputFolder = File(outputFolderName)
            if (!outputFolder.exists()) {
                throw NoSuchFileException(outputFolderName)
            }
            println(
                "******************************************************************************"
            )
            println("Processing")
            println("\tsource folder: $sourceFolderName")
            println("\tpackage name: $outputPackageName")
            println(
                "******************************************************************************"
            )
            converter.transcodeAllFilesInFolder(
                inputFolder, outputFolder, outputClassNamePrefix, outputFileNameExtension,
                outputPackageName!!, templateFile!!
            )
        }
    }
}
