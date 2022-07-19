#
#  Copyright 2020-2022 Aurora, Kirill Grouchnikov. All Rights Reserved.
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

AURORA_VERSION=1.2-SNAPSHOT
KOTLIN_VERSION=1.7.0
KOTLIN_COROUTINES_VERSION=1.6.4
COMPOSE_VERSION=1.2.0-alpha01-dev748
CLASSPATH=../../drop/$AURORA_VERSION/aurora-screenshot-desktop-$AURORA_VERSION.jar:../../demo/build/libs/aurora-demo-desktop.jar:../build/libs/:../../build/libs/*

java -cp $CLASSPATH org.pushingpixels.aurora.tools.screenshot.theming.schemes.AuroraColorSchemesDriverKt \
    ../../docs/images/theming/colorschemes
