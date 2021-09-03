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

AURORA_VERSION=0.0.49-SNAPSHOT
CLASSPATH=../drop/$AURORA_VERSION/aurora-svg-transcoder-desktop-0.0.47-$AURORA_VERSION.jar

java -cp $CLASSPATH org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverterMain sourceFolder=../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg outputPackageName=org.pushingpixels.aurora.demo.svg templateFile=/org/pushingpixels/aurora/tools/svgtranscoder/SvgTranscoderTemplateResizable.templ
