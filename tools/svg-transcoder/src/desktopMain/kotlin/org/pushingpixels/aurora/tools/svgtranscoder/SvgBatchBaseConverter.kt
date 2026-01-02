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
import java.io.PrintWriter
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

abstract class SvgBatchBaseConverter {
    internal fun getInputArgument(args: Array<String>, argumentName: String?, defaultValue: String?): String? {
        for (arg in args) {
            val split = arg.split("=").toTypedArray()
            if (split.size != 2) {
                println("Argument '$arg' unsupported")
                println(CHECK_DOCUMENTATION)
                exitProcess(1)
            }
            if (split[0].compareTo(argumentName!!) == 0) {
                return split[1]
            }
        }
        return defaultValue
    }

    internal fun transcodeAllFilesInFolder(
        inputFolder: File, outputFolder: File,
        outputClassNamePrefix: String, outputFileNameExtension: String,
        outputPackageName: String,
        templateFile: String
    ) {

        var totalCount = 0
        var successCount = 0

        inputFolder
            .walk(direction = FileWalkDirection.TOP_DOWN)
            .maxDepth(1)
            .filter { it.isFile && it.name.endsWith("svg") }
            .forEach { file ->
                val svgClassName = (outputClassNamePrefix + file.name.substring(0, file.name.length - 4))
                    .replace('-', '_')
                    .replace(' ', '_')
                val classFilename = outputFolder.absolutePath + File.separator +
                        svgClassName + outputFileNameExtension
                println("Processing ${file.absolutePath} to $classFilename")
                totalCount++
                try {
                    PrintWriter(classFilename).use { writer ->
                        SvgBatchBaseConverter::class.java.getResourceAsStream(templateFile).use { templateStream ->
                            Objects.requireNonNull(templateStream, "Couldn't load $templateFile")
                            val latch = CountDownLatch(1)
                            val uri = file.toURI().toURL().toString()
                            val transcoder = SvgTranscoder(uri, svgClassName)
                            transcoder.setPackageName(outputPackageName)
                            transcoder.setListener(object : TranscoderListener {
                                override val writer = writer

                                override fun finished() {
                                    latch.countDown()
                                }
                            })
                            transcoder.transcode(templateStream)
                            // Limit the processing to 10 seconds to prevent infinite hang
                            latch.await(10, TimeUnit.SECONDS)
                            successCount++
                        }
                    }
                } catch (t: Throwable) {
                    t.printStackTrace(System.err)
                }

            }
        println()
        println("Processed $successCount out of $totalCount SVG files")
        println()
    }

    companion object {
        const val CHECK_DOCUMENTATION = "Check the documentation for the parameters to pass"
    }
}
