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

import java.io.File
import java.nio.file.NoSuchFileException
import java.util.*
import java.util.stream.Stream
import kotlin.system.exitProcess

class SvgBatchConverter : SvgBatchBaseConverter() {}

object SvgBatchConverterMain {
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
        Objects.requireNonNull(sourceFolderName, "Missing source folder. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION)
        val outputPackageName: String? = converter.getInputArgument(args, "outputPackageName", null)
        Objects.requireNonNull(outputPackageName, "Missing output package name. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION)
        val templateFile: String? = converter.getInputArgument(args, "templateFile", null)
        Objects.requireNonNull(templateFile, "Missing template file. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION)
        val outputFileNameExtension = ".kt"
        val outputClassNamePrefix: String = converter.getInputArgument(args, "outputClassNamePrefix", "")!!
        val outputFolderName: String? = converter.getInputArgument(args, "outputFolder", sourceFolderName)
        Objects.requireNonNull(outputFolderName, "Missing output folder. " + SvgBatchBaseConverter.CHECK_DOCUMENTATION)
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
