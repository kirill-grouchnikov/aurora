
# Aurora libraries

Aurora is a collection of libraries for writing modern, elegant and fast [desktop Compose](https://github.com/JetBrains/compose-jb) applications.

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nebula.png" width="340" height="280" border=0>
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/gemini.png" width="340" height="280" border=0>
</p>

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/graphitechalk.png" width="340" height="280" border=0>
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/skins/nightshade.png" width="340" height="280" border=0>
</p>

## Documentation

### Window top-level APIs

* [Hello World](docs/window/HelloWorld.md)
* [Aurora Application](docs/window/Application.md)
* [Aurora Window](docs/window/Window.md)
* [Radiance interoperability](docs/window/RadianceInterop.md)

### Theming

* [Component states](docs/theming/skins/componentstates.md)
* Colors
  * [Color schemes](docs/theming/skins/colorschemes.md)
  * [Color scheme association kinds](docs/theming/skins/colorschemeassociationkinds.md)
  * [Color scheme file format](docs/theming/skins/colorschemes-fileformat.md)
  * [Color scheme bundles](docs/theming/skins/colorschemebundles.md)
* Skins
  * [Skin overview](docs/theming/skins/overview.md)
  * [Light skins](docs/theming/skins/toneddown.md)
  * [Dark skins](docs/theming/skins/dark.md)
* Painters
  * [Painter overview](docs/theming/painters/overview.md)
  * [Fill painters](docs/theming/painters/fill.md)
  * [Border painters](docs/theming/painters/border.md)
  * [Decoration painters](docs/theming/painters/decoration.md)
  * [Overlay painters](docs/theming/painters/overlay.md)
* [Themed states](docs/theming/themed-states.md)

### Components

* Intro
  * [Basic concepts](docs/component/Intro.md)
  * [Sample app](docs/component/Sample.md)
  * [Another sample app](docs/component/AnotherSample.md)
* Commands
  * [Command overview](docs/component/Command.md)
  * [Command projections](docs/component/CommandProjections.md)
  * [Command button presentation models](docs/component/CommandButtonPresentation.md)
* Projections
  * [Model and projection overview](docs/component/ModelProjectionOverview.md)
  * [All component projections](docs/component/ComponentProjections.md)
* More components
  * [Command strips](docs/component/CommandStrip.md)
  * [Command panels](docs/component/CommandPanel.md)
  * [Command popup menus](docs/component/CommandPopupMenu.md)
  * [Breadcrumb bars](docs/component/BreadcrumbBar.md)
  * [Box with highlights](docs/component/BoxWithHighlights.md)
* Miscellaneous
  * [Context menus](docs/component/ContextMenu.md)
  * [All custom modifiers](docs/component/Modifiers.md)

### SVG transcoding

* [What is SVG transcoding](docs/tools/svg-transcoder/svg-transcoder.md)
* [Gradle plugin for SVG transcoding](docs/tools/svg-transcoder/svg-transcoder-gradle-plugin.md)
* [SVG transcoding internals](docs/tools/svg-transcoder/internals.md)

### Have a question?

* [File a bug](https://github.com/kirill-grouchnikov/aurora/issues)
* [Get in touch](http://www.pushing-pixels.org/about-kirill)

## Aurora artifacts

Aurora artifacts are available in the central Maven repository under `groupId=org.pushing-pixels`

### Core artifacts

* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-theming/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-theming)
  `aurora-theming`
* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-component/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-component)
  `aurora-component`
* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-window/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-window)
  `aurora-window`

### Tools artifacts

* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-tools-svg-transcoder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-tools-svg-transcoder) `aurora-tools-svg-transcoder`
* [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-tools-svg-transcoder-gradle-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pushing-pixels/aurora-tools-svg-transcoder-gradle-plugin) `aurora-tools-svg-transcoder-gradle-plugin`

### Snapshots

Aurora snapshot artifacts are available in the [Sonatype repository](https://oss.sonatype.org/content/repositories/snapshots/org/pushing-pixels/).

## Building Aurora

[This document](docs/building.md) is an overview of how to build Aurora artifacts locally.

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/logo/auroraicon-512.png" width="256" height="256" border=0>
</p>
