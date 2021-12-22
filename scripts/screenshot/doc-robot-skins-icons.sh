#
#  Copyright (c) 2020-2021 Aurora, Kirill Grouchnikov. All Rights Reserved.
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

export JAVA_HOME=`/usr/libexec/java_home -v 11`
alias JAVA="java"

AURORA_VERSION=1.1-SNAPSHOT
KOTLIN_VERSION=1.6.10
KOTLIN_COROUTINES_VERSION=1.5.2
COMPOSE_VERSION=1.0.1-rc2
CLASSPATH=../../drop/$AURORA_VERSION/aurora-screenshot-desktop-$AURORA_VERSION.jar:../../demo/build/libs/aurora-demo-desktop.jar:../../drop/$AURORA_VERSION/aurora-screenshot-common-$AURORA_VERSION.jar:../../drop/$AURORA_VERSION/aurora-screenshot-theming-$AURORA_VERSION.jar:../../drop/$AURORA_VERSION/aurora-screenshot-component-$AURORA_VERSION.jar:../../drop/$AURORA_VERSION/aurora-screenshot-window-$AURORA_VERSION.jar:../build/libs/batik-all-1.14.jar:../build/libs/xml-apis-1.4.01.jar:../build/libs/xml-apis-ext-1.3.04.jar:../../build/libs/xmlgraphics-commons-2.6.jar:../../build/libs/kotlin-stdlib-$KOTLIN_VERSION.jar:../../build/libs/kotlinx-coroutines-core-jvm-$KOTLIN_COROUTINES_VERSION.jar:../../build/libs/ui-graphics-desktop-$COMPOSE_VERSION.jar:../../build/libs/*

java -cp $CLASSPATH org.pushingpixels.aurora.tools.screenshot.theming.skins.AuroraSkinsIconFilterDriverKt \
    ../../docs/images/theming/skins
