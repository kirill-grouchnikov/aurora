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

import org.pushingpixels.aurora.icon.transcoder.LanguageRenderer.MethodArgument
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Stream

class KotlinLanguageRenderer : LanguageRenderer {
    override val statementEnd: String
        get() = ""

    override fun getObjectCreation(className: String): String {
        return className
    }

    override fun getObjectCreationNoParams(className: String): String {
        return "$className()"
    }

    override fun getObjectCast(objectName: String, classToCastTo: String): String {
        return "($objectName as $classToCastTo)"
    }

    override fun getObjectNoNull(objectName: String): String {
        return "$objectName!!"
    }

    override fun startPrimitiveArrayOf(primitiveTypeName: String): String {
        return primitiveTypeName + "ArrayOf("
    }

    override fun startGenericArrayOf(className: String): String {
        return "arrayOf("
    }

    override fun endArray(): String {
        return ")"
    }

    override fun startVariableDefinition(variableTypeName: String): String {
        return "val "
    }

    override fun startSetterAssignment(propertyName: String): String {
        return ".$propertyName = "
    }

    override fun endSetterAssignment(): String {
        return ""
    }

    override fun getGetter(propertyName: String): String {
        return ".$propertyName"
    }

    override fun startMethod(methodName: String, vararg arguments: MethodArgument): String {
        return "private fun " + methodName + "(" +
                Stream.of(*arguments)
                    .map(Function<MethodArgument, String> { e: MethodArgument -> e.name + " : " + e.type })
                    .collect(Collectors.joining(",")) +
                ") {"
    }

    override fun endMethod(): String {
        return "}"
    }

    override fun getPrimitiveTypeFor(clazz: Class<*>): String {
        if (clazz == Int::class.javaPrimitiveType) return "Int"
        if (clazz == Double::class.javaPrimitiveType) return "Double"
        if (clazz == Float::class.javaPrimitiveType) return "Float"
        if (clazz == Boolean::class.javaPrimitiveType) return "Boolean"
        if (clazz == Char::class.javaPrimitiveType) return "Char"
        throw UnsupportedOperationException(clazz.toString())
    }
}