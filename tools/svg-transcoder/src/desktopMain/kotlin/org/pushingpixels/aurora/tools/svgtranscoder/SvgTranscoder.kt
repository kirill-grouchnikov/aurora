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

import org.apache.batik.bridge.BridgeContext
import org.apache.batik.bridge.DocumentLoader
import org.apache.batik.bridge.GVTBuilder
import org.apache.batik.bridge.UserAgentAdapter
import org.w3c.dom.Document
import java.io.IOException
import java.io.InputStream
import java.util.logging.Level
import java.util.logging.Logger

/**
 * SVG to Compose transcoder.
 *
 * @author Kirill Grouchnikov.
 */
class SvgTranscoder(private val uri: String, classname: String) : SvgBaseTranscoder(classname) {
    /**
     * Transcodes the SVG image into Compose code. Does nothing if the
     * [.listener] is `null`.
     *
     * @param templateStream Stream with the template content
     */
    fun transcode(templateStream: InputStream): Document? {
        if (_listener == null) return null
        val ua = UserAgentAdapter()
        val loader = DocumentLoader(ua)
        val batikBridgeContext = BridgeContext(ua, loader)
        batikBridgeContext.setDynamicState(BridgeContext.DYNAMIC)
        ua.setBridgeContext(batikBridgeContext)
        val builder = GVTBuilder()
        val svgDoc: Document
        return try {
            svgDoc = loader.loadDocument(uri)
            val gvtRoot = builder.build(batikBridgeContext, svgDoc)
            this.transcode(gvtRoot, templateStream)
            svgDoc
        } catch (ex: IOException) {
            Logger.getLogger(SvgTranscoder::class.java.name).log(Level.SEVERE, null, ex)
            null
        } catch (ex: IllegalArgumentException) {
            Logger.getLogger(SvgTranscoder::class.java.name).log(Level.SEVERE, null, ex)
            null
        } finally {
            try {
                loader.dispose()
                batikBridgeContext.dispose()
            } catch (t: Throwable) {
                Logger.getLogger(SvgTranscoder::class.java.name).log(Level.SEVERE, null, t)
            }
        }
    }
}
