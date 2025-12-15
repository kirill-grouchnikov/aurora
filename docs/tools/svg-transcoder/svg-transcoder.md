## SVG Transcoder - SVG icons for Aurora applications

### Overview

The goal of this project is to enable usage of vector-based icons in Aurora applications.

The original icon format supported by the transcoder is SVG, and you need the [matching version](#dependency-versions-for-the-aurora-svg-transcoder) of [Apache Batik](https://xmlgraphics.apache.org/batik/) and its dependencies.

### Offline transcoding of SVG content

SVG has a wide feature surface which, depending on the complexity of your SVG sources, can lead to significant initial parsing and rendering time of the icon content.

Aurora SVG transcoder allows you to convert an SVG source into a corresponding Kotlin class that contains a sequence of matching Compose canvas draw calls to render the original content. Aurora SVG transcoder ships a built-in template file that generates a class that implements the core [`Painter`](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/painter/Painter) interface. An instance of the generated class can be passed to any relevant Aurora API, including the matching command button calls. At runtime, the content will be automatically reconfigured based on the new display size of the icon.

#### Batch transcoding all SVG files in a single folder

The `SvgBatchConverter` class is the entry point into the offline batch converter pipeline for a single folder. It has the following parameters:

* [Mandatory] `sourceFolder=` The location of the folder that contains source SVG images
* [Mandatory] `outputPackageName=` The package name for the transcoded classes
* [Mandatory] `templateFile=` The path of the template file
* [Optional] `outputFolder=xyz` The location of the transcoded classes. If not specified, output files will be placed in the `sourceFolder` alongside the original SVG files.
* [Optional] `outputClassNamePrefix=` The prefix for the class names of the transcoded classes

Assuming your `AURORA_VERSION` variable points to the latest version of the Aurora libraries, here is how you would run the converter pipeline for a single SVG source folder (note that the dependencies versions need to match the Aurora version, see [the list below](#dependency-versions-for-the-aurora-svg-transcoder)):

<code>java <b>-cp</b> ../drop/$AURORA_VERSION/aurora-svg-transcoder-desktop-$AURORA_VERSION.jar:../build/libs/batik-all-1.19.jar:../build/libs/xml-apis-1.4.01.jar:../build/libs/xml-apis-ext-1.3.04.jar:../build/libs/xmlgraphics-commons-2.11.jar:../build/libs/kotlin-stdlib-$KOTLIN_VERSION.jar:../build/libs/kotlin-stdlib-common-$KOTLIN_VERSION.jar:../build/libs/kotlinx-coroutines-core-jvm-$KOTLIN_COROUTINES_VERSION.jar:../build/libs/ui-graphics-desktop-$COMPOSE_VERSION.jar:../build/libs/ui-geometry-desktop-$COMPOSE_VERSION.jar org.pushingpixels.aurora.tools.svgtranscoder.SvgBatchConverter <b>sourceFolder=</b>../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg <b>outputPackageName=</b>org.pushingpixels.aurora.demo.svg <b>templateFile=</b>/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ
</code>

The first part is enumerating all the jar files required for the converter - Aurora, Batik and Kotlin. In this sample script, the Aurora SVG transcoder jar is under `drop` folder after running the `gradlew copyJars` command. The Batik dependencies are under `build/libs` after running the `gradlew getDependencies` command.

The second part passes the mandatory parameters:

* `../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg` as the location of the source SVG images - and the output transcoded classes
* `org.pushingpixels.aurora.demo.svg` as the package name for the transcoded classes
* `/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ` as the path of the template file

#### Recursive batch transcoding all SVG files under a folder

The `SvgDeepBatchConverter` class is the entry point into the offline recursive batch converter pipeline. It has the following parameters:

* [Mandatory] `sourceRootFolder=` The location of the root folder to traverse for SVG images
* [Mandatory] `outputRootPackageName=` The root package name for the transcoded classes
* [Mandatory] `templateFile=` The path of the template file
* [Optional] `outputRootFolder=xyz` The root location of the transcoded classes. If not specified, output files will be placed under the `sourceRootFolder` alongside the original SVG files.
* [Optional] `outputClassNamePrefix=` The prefix for the class names of the transcoded classes

Assuming your `AURORA_VERSION` variable points to the latest version of the Aurora libraries, here is how you would run the converter pipeline recursively for all SVG files under a folder (note that the dependencies versions need to match the Aurora version, see [the list below](#dependency-versions-for-the-aurora-svg-transcoder)):

<code>java <b>-cp</b> ../drop/$AURORA_VERSION/aurora-svg-transcoder-desktop-$AURORA_VERSION.jar:../build/libs/batik-all-1.19.jar:../build/libs/xml-apis-1.4.01.jar:../build/libs/xml-apis-ext-1.3.04.jar:../build/libs/xmlgraphics-commons-2.11.jar:../build/libs/kotlin-stdlib-$KOTLIN_VERSION.jar:../build/libs/kotlin-stdlib-common-$KOTLIN_VERSION.jar:../build/libs/kotlinx-coroutines-core-jvm-$KOTLIN_COROUTINES_VERSION.jar:../build/libs/ui-graphics-desktop-$COMPOSE_VERSION.jar:../build/libs/ui-geometry-desktop-$COMPOSE_VERSION.jar org.pushingpixels.aurora.tools.svgtranscoder.SvgDeepBatchConverter <b>sourceRootFolder=</b>../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg <b>outputRootPackageName=</b>org.pushingpixels.aurora.demo.svg <b>templateFile=</b>/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ
</code>

The first part is enumerating all the jar files required for the converter - Aurora, Batik and Kotlin. In this sample script, the Aurora SVG transcoder jar is under `drop` folder after running the `gradlew copyJars` command. The Batik dependencies are under `build/libs` after running the `gradlew getDependencies` command.

The second part passes the mandatory parameters:

* `../demo/src/desktopMain/kotlin/org/pushingpixels/aurora/demo/svg` as the root folder of the source SVG images - and the output transcoded classes
* `org.pushingpixels.aurora.demo.svg` as the root package name for the transcoded classes
* `/org/pushingpixels/aurora/tools/svgtranscoder/AuroraSvgTranscoderTemplate.templ` as the path of the template file

#### An important note on offline transcoding

The intended usage and the scope of the Aurora SVG transcoder is to convert reasonably sized icons for usages as supporting imagery without the additional runtime overhead of bundling a full-fledged SVG parsing and rendering library.

SVG content can be arbitrarily complex. For example, [this Spanish flag](https://en.wikipedia.org/wiki/File:Flag_of_Spain.svg) is a 59KB SVG file. It is transcoded by Aurora to a 734KB Kotlin file and, depending on your heap settings, will either result in `OutOfMemoryError` exception during compilation, or in a generated drawing sequence that exceeds method limitations.  There are more complex flags, such as [Ecuador](https://en.wikipedia.org/wiki/File:Flag_of_Ecuador.svg) with a lot more details or [Afghanistan](https://en.wikipedia.org/wiki/File:Flag_of_Afghanistan.svg) that has a huge path with more than 8K elements in it (all the white outlines of mosque, wheat and inscription is a single path). Such files produce a Kotlin class that can't be compiled due to too many symbols in it.

The Aurora SVG transcoder **does not** provide support for such huge SVG files. At some point, the binary size of the compiled transcoded classes is at the same order of magnitude as simply bundling the original SVGs and the full Batik distribution.

### SVG format support

The following table summarizes the level of support for different SVG elements.

| SVG element | Status |
| --- | --- |
| `<animate>` | Not supported |
| `<circle>` | Supported |
| `<clipPath>` | Supported |
| `<ellipse>` | Supported |
| `<filter>` | Out of scope |
| `<hatch>` | [Not supported](https://issues.apache.org/jira/browse/BATIK-1259) by Batik |
| `<image>` | Supported |
| `<line>` | Supported |
| `<linearGradient>` | Supported |
| `<marker>` | Supported |
| `<mask>` | Out of scope |
| `<path>` | Supported |
| `<pattern>` | Supported |
| `<polygon>` | Supported |
| `<polyline>` | Supported |
| `<radialGradient>` | Supported |
| `<rect>` | Supported |
| `<solidcolor>` | Supported |
| `<text>` | Supported |

### Dependency versions for the Aurora SVG transcoder

* For **2.0.0**, use
  * `batik-all-1.19.jar`
  * `xml-apis-1.4.01.jar`
  * `xml-apis-ext-1.3.04.jar`
  * `xmlgraphics-commons-2.11.jar`
* For **1.3.0**, use
  * `batik-all-1.16.jar`
  * `xml-apis-1.4.01.jar`
  * `xml-apis-ext-1.3.04.jar`
  * `xmlgraphics-commons-2.7.jar`
* For **1.2.0**, use
  * `batik-all-1.15.jar`
  * `xml-apis-1.4.01.jar`
  * `xml-apis-ext-1.3.04.jar`
  * `xmlgraphics-commons-2.7.jar`
* For **1.1.0**, use
  * `batik-all-1.14.jar`
  * `xml-apis-1.4.01.jar`
  * `xml-apis-ext-1.3.04.jar`
  * `xmlgraphics-commons-2.6.jar`
* For **1.0.1**, use
  * `batik-all-1.14.jar`
  * `xml-apis-1.4.01.jar`
  * `xml-apis-ext-1.3.04.jar`
  * `xmlgraphics-commons-2.6.jar`

These can be downloaded manually from the net, or doing the following:

* Download the matching Aurora `X.Y.Z` source archive
* Run `./gradlew`
* Run `./gradlew copyJars`
* Run `./gradlew getDependencies`

At this point, the Aurora SVG transcoder jar will be under `drop/X.Y.Z` and the dependencies will be under `build/libs`.

### Taking a deeper look at the transcoder internals

If you want to know more about the internal implementation details of the transcoder, [click here](internals.md).
