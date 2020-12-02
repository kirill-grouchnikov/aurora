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
class SvgTranscoder(val uri: String, classname: String) : SvgBaseTranscoder(classname) {
    /**
     * Transcodes the SVG image into Compose code. Does nothing if the
     * [.listener] is `null`.
     *
     * @param templateStream Stream with the template content
     */
    fun transcode(templateStream: InputStream?): Document? {
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