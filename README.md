
# Aurora libraries

**Aurora** is a collection of libraries for writing modern, elegant and fast [desktop Compose](https://github.com/JetBrains/compose-multiplatform) applications based on
the [Ephemeral](https://github.com/kirill-grouchnikov/ephemeral) design system. It is built with Java 17, and runs on Java 11 and later.

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
* [Window title panes](docs/window/TitlePane.md)
* [Radiance interoperability](docs/window/RadianceInterop.md)

### Theming

* [Component states](docs/theming/skins/componentstates.md)
* Colors
  * [Color tokens](docs/theming/skins/colortokens.md)
  * [Color tokens association kinds](docs/theming/skins/colortokensassociationkinds.md)
  * [Color tokens bundles](docs/theming/skins/colortokensbundles.md)
* Skins
  * [Skin overview](docs/theming/skins/overview.md)
  * [Light skins](docs/theming/skins/toneddown.md)
  * [Dark skins](docs/theming/skins/dark.md)
* Painters
  * [Painter overview](docs/theming/painters/overview.md)
  * [Surface painters](docs/theming/painters/surface.md)
  * [Outline painters](docs/theming/painters/outline.md)
  * [Highlight painters](docs/theming/painters/highlight.md)
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
  * [Command popup menus](docs/component/CommandPopupMenu.md)
* Projections
  * [Model and projection overview](docs/component/ModelProjectionOverview.md)
  * [All component projections](docs/component/ComponentProjections.md)
* Revisiting commands
  * [More on commands](docs/component/MoreCommands.md)
  * [Color selector command](docs/component/ColorSelectorCommand.md)
  * [Custom command projections](docs/component/CustomCommandProjections.md)
* More components
  * [Command strips](docs/component/CommandStrip.md)
  * [Command panels](docs/component/CommandPanel.md)
  * [Breadcrumb bars](docs/component/BreadcrumbBar.md)
  * [Box with highlights](docs/component/BoxWithHighlights.md)
* Miscellaneous
  * [Context menus](docs/component/ContextMenu.md)
  * [All custom modifiers](docs/component/Modifiers.md)
* Ribbon
  * [Overview](docs/component/RibbonOverview.md)
  * [API skeleton](docs/component/RibbonApiSkeleton.md)
  * [Working with command projections](docs/component/RibbonBandCommandProjections.md)
  * [Working with component projections](docs/component/RibbonBandComponentProjections.md)
  * [Working with galleries](docs/component/RibbonBandGalleries.md)
  * [Keytips](docs/component/RibbonKeytips.md)
  * [What happens when you resize the ribbon](docs/component/RibbonResizing.md)
  * [Application menu](docs/component/RibbonApplicationMenu.md)
  * [Anchored commands](docs/component/RibbonAnchoredCommands.md)
  * [Contextual task groups](docs/component/RibbonContextualTaskGroups.md)
  * [Taskbar](docs/component/RibbonTaskbar.md)
  * [Global contextual listener](docs/component/RibbonContextualListener.md)


### SVG transcoding

* [What is SVG transcoding](docs/tools/svg-transcoder/svg-transcoder.md)
* [Gradle plugin for SVG transcoding](docs/tools/svg-transcoder/svg-transcoder-gradle-plugin.md)
* [SVG transcoding internals](docs/tools/svg-transcoder/internals.md)

### Have a question?

* [File a bug](https://github.com/kirill-grouchnikov/aurora/issues)
* [Get in touch](http://www.pushing-pixels.org/about-kirill)

## Aurora artifacts

Aurora artifacts are available in the Maven Central repository under `groupId=org.pushing-pixels`

### Core artifacts

* [![Sonatype Central](https://maven-badges.sml.io/sonatype-central/org.pushing-pixels/aurora-theming/badge.svg?style=plastic&version=2.0.0)](https://central.sonatype.com/artifact/org.pushing-pixels/aurora-theming/2.0.0)
  `aurora-theming`
* [![Sonatype Central](https://maven-badges.sml.io/sonatype-central/org.pushing-pixels/aurora-component/badge.svg?style=plastic&version=2.0.0)](https://central.sonatype.com/artifact/org.pushing-pixels/aurora-component/2.0.0)
  `aurora-component`
* [![Sonatype Central](https://maven-badges.sml.io/sonatype-central/org.pushing-pixels/aurora-window/badge.svg?style=plastic&version=2.0.0)](https://central.sonatype.com/artifact/org.pushing-pixels/aurora-window/2.0.0)
  `aurora-window`

### Tools artifacts

* [![Sonatype Central](https://maven-badges.sml.io/sonatype-central/org.pushing-pixels/aurora-tools-svg-transcoder/badge.svg?style=plastic&version=2.0.0)](https://central.sonatype.com/artifact/org.pushing-pixels/aurora-tools-svg-transcoder/2.0.0) `aurora-tools-svg-transcoder`
* [![Sonatype Central](https://maven-badges.sml.io/sonatype-central/org.pushing-pixels/aurora-tools-svg-transcoder-gradle-plugin/badge.svg?style=plastic&version=2.0.0)](https://central.sonatype.com/artifact/org.pushing-pixels/aurora-tools-svg-transcoder-gradle-plugin/2.0.0) `aurora-tools-svg-transcoder-gradle-plugin`

### Snapshots

Aurora snapshot artifacts [are available in the Maven Central snapshot repository](https://central.sonatype.com/service/rest/repository/browse/maven-snapshots/org/pushing-pixels/) using the `https://central.sonatype.com/repository/maven-snapshots/` URL.

## Building Aurora

[This document](docs/building.md) is an overview of how to build Aurora artifacts locally.

<p align="center">
<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/logo/auroraicon-512.png" width="256" height="256" border=0>
</p>
