# 
#  Copyright 2020-2026 Aurora, Kirill Grouchnikov. All Rights Reserved.
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

export JAVA_HOME=`/usr/libexec/java_home -v 22`
alias JAVA="java"

AURORA_VERSION=2.5-SNAPSHOT
KOTLIN_VERSION=2.3.0
KOTLIN_COROUTINES_VERSION=1.10.2
COMPOSE_VERSION=1.10.0
CLASSPATH=../drop/$AURORA_VERSION/aurora-svg-transcoder-desktop-$AURORA_VERSION.jar:../build/libs/batik-all-1.19.jar:../build/libs/xml-apis-1.4.01.jar:../build/libs/xml-apis-ext-1.3.04.jar:../build/libs/xmlgraphics-commons-2.11.jar:../build/libs/kotlin-stdlib-$KOTLIN_VERSION.jar:../build/libs/kotlin-stdlib-common-$KOTLIN_VERSION.jar:../build/libs/kotlinx-coroutines-core-jvm-$KOTLIN_COROUTINES_VERSION.jar:../build/libs/ui-graphics-desktop-$COMPOSE_VERSION.jar:../build/libs/ui-geometry-desktop-$COMPOSE_VERSION.jar

# Don't convert the demo transcoding to deep traversal since one of the
# folders needs class name prefix while others don't
java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg \
    outputPackageName=org.pushingpixels.aurora.demo.svg \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/filetypes \
    outputPackageName=org.pushingpixels.aurora.demo.svg.filetypes \
    outputClassNamePrefix=ext_ \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/flags \
    outputPackageName=org.pushingpixels.aurora.demo.svg.flags \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/material \
    outputPackageName=org.pushingpixels.aurora.demo.svg.material \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/random \
    outputPackageName=org.pushingpixels.aurora.demo.svg.random \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/tango \
    outputPackageName=org.pushingpixels.aurora.demo.svg.tango \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter \
    sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg/vaadin \
    outputPackageName=org.pushingpixels.aurora.demo.svg.vaadin \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ

java -Djava.awt.headless=true -cp $CLASSPATH \
    org.pushingpixels.aurora.tools.svgtranscoder.SvgDeepBatchConverter \
    sourceRootFolder=../tools/screenshot/src/desktopMain/kotlin/org/pushingpixels/aurora/tools/screenshot/svg \
    outputRootPackageName=org.pushingpixels.aurora.tools.screenshot.svg \
    templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ
