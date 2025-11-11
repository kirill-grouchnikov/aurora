## Aurora theming - decoration painters

Menu bars, tool bars, status bars - these are common examples of special containers found in application windows. These containers create functional grouping of application controls and bring order to complex screens. Aurora skinning layer provides a flexible and powerful set of APIs to define the visual appearance of these control groups - called **decoration areas**. At the same time, Aurora decoration painters enforce visual consistency and connections across the components in related areas and states. In addition, Aurora provides a set of published APIs for applications that wish to skin custom composables and preserve visual consistency with the core Aurora composables.

### Decoration areas

The `DecorationAreaType` class contains the available core decoration area types. As a picture is worth a thousand words, the following screenshots illustrate the different decoration area types.

The following screenshot is the main Aurora test application under the Gemini skin (click to see full size version):

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/full.png" width="766"/></a>

The next screenshot shows the `title` decoration area, which in this example includes the title pane of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-title.png" width="766"/>

The next screenshot shows the `header` decoration area, which in this example includes the menu bar of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-header.png" width="766"/>

The next screenshot shows the `toolbar` decoration area, which in this example includes the tool bar of the main window:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-toolbar.png" width="766"/>

The next screenshot shows the `footer` decoration area, which in this example includes the status bar component:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-footer.png" width="766"/>

The next screenshot shows the `control pane` decoration area, which in this example includes the task pane container component:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/areas-control-pane.png" width="766"/>

Let's go back to the original screenshot:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/decoration/full.png" width="766"/>

It illustrates that the specific skin assigns different color tokens to different decoration areas. The controls in those areas get the background and foreground colors based on their assigned color tokens (without any custom application code), thus creating visual distinction between different application areas.

### Application-facing APIs

To use the matching decoration painter in custom painting routines of your application, call the following published Aurora APIs:

* `AuroraSkin.painters` to retrieve the painters associated with the current skin.
* `AuroraPainters.decorationPainter` to retrieve the decoration painter of the current skin.
* `AuroraSkin.decorationAreaType` to retrieve the decoration area type of the composable.
* `AuroraDecorationPainter.paintDecorationArea()` to paint the decoration background on the specific draw scope.

The base class for Aurora decoration painters is `AuroraDecorationPainter`. It has the following method:

```kotlin
fun paintDecorationArea(
    drawScope: DrawScope,
    decorationAreaType: DecorationAreaType,
    componentSize: Size,
    outline: Outline,
    rootSize: Size,
    offsetFromRoot: Offset,
    colorTokens: ContainerColorTokens
)
```

The `outline` parameter specifies the outline to paint, the `colorTokens` specifies the color tokens to be used to compute the colors, while `decorationAreaType` indicates the decoration area type.
