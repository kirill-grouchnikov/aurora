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
CLASSPATH=../../drop/$AURORA_VERSION/aurora-screenshot-desktop-$AURORA_VERSION.jar:../../demo/build/libs/aurora-demo-desktop.jar:../../build/libs/*

java -cp $CLASSPATH org.pushingpixels.aurora.tools.screenshot.theming.skins.AuroraSkinsDriverKt \
    ../../docs/images/theming/skins
