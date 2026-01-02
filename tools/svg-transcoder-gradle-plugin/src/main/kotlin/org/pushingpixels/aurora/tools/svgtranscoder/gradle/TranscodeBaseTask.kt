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

import org.gradle.api.DefaultTask
import org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchBaseConverter
import org.pushingpixels.aurora.tools.svgtranscoder.SvgTranscoder
import org.pushingpixels.aurora.tools.svgtranscoder.TranscoderListener
import java.io.File
import java.io.PrintWriter
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

abstract class TranscodeBaseTask : DefaultTask() {
    protected fun transcodeAllFilesInFolder(
        inputFolder: File, outputFolder: File,
        outputClassNamePrefix: String,
        outputPackageName: String, templateFileName: String
    ) {
        val svgFiles: Array<File> =
            inputFolder.listFiles { _, name -> name.endsWith(".svg") }
                ?: return
        for (file in svgFiles) {
            var svgClassName = (outputClassNamePrefix
                    + file.name.substring(0, file.name.length - 4))
            svgClassName = svgClassName.replace('-', '_')
            svgClassName = svgClassName.replace(' ', '_')
            val classFilename =
                outputFolder.toString() + File.separator + svgClassName + ".kt"
            logger.trace("Processing " + file.name)
            try {
                PrintWriter(classFilename).use { writer ->
                    SvgBatchBaseConverter::class.java
                        .getResourceAsStream(templateFileName).use { templateStream ->
                            if (templateStream == null) {
                                logger.error("Couldn't load $templateFileName")
                                return
                            }
                            val latch = CountDownLatch(1)
                            val transcoder = SvgTranscoder(
                                file.toURI().toURL().toString(), svgClassName
                            )
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
                        }
                }
            } catch (e: Exception) {
                logger.error("Transcoding failed", e)
            }
        }
    }
}