## Window - title panes

The `windowTitlePaneConfiguration` parameter of the `AuroraWindow` composable controls the presence and appearance of the window title pane.

Aurora supports four configurations of window title panes:

* System
* None
* Aurora plain
* Aurora integrated

### System

Use `AuroraWindowTitlePaneConfigurations.System` to have your window's title pane be provided by the operating system:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-system.png" border=0>

### None

Use `AuroraWindowTitlePaneConfigurations.None` to have no window title pane:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-none.png" border=0>

Note that in this case, your application code is responsible for handling window move, resize, close and other top-level window interaction patterns.

### Aurora plain

Use `AuroraWindowTitlePaneConfigurations.AuroraPlain` to have your window's title pane be provided by Aurora:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-auroraplain.png" border=0>

In this configuration, the title pane displays app icon (if provided), app title and the window control buttons (minimize, maximize / restore and close).

The `AuroraPlain` configuration provides further control over where each part of the title pane will be displayed:

| Parameter | Default value | Meaning |
| --- | --- | --- |
| **titleTextHorizontalGravity** | **Leading** | Horizontal gravity for the title |
| **titleControlButtonGroupHorizontalGravity** | **Trailing** | Horizontal gravity for the control buttons |
| **titleIconHorizontalGravity** | **OppositeControlButtons** | Horizontal gravity for the icon |
| **titlePaneButtonsProvider** | **DefaultTitlePaneButtonsProvider** | Icon provider for the buttons |

The default values for these parameters result in the layout that places:

* The control buttons on the right (**Trailing**)
* The app icon on the opposite side of the control buttons - on the left (**OppositeControlButtons**)
* The app title on the left, right after the app icon (**Leading**)

Here are a few example of configurations that you might want to consider for your applications.

Following the macOS guidelines with

* `textHorizontalGravity = HorizontalGravity.Centered`
* `controlButtonHorizontalGravity = HorizontalGravity.Leading`
* `iconHorizontalGravity = TitleIconHorizontalGravity.NextToTitle`

resulting in

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-layout-macos.png" border=0>

Following the Gnome guidelines with

* `textHorizontalGravity = HorizontalGravity.Centered`
* `controlButtonHorizontalGravity = HorizontalGravity.Trailing`
* `iconHorizontalGravity = TitleIconHorizontalGravity.None`

resulting in

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-layout-gnome.png" border=0>

Following the KDE guidelines with

* `textHorizontalGravity = HorizontalGravity.Centered`
* `controlButtonHorizontalGravity = HorizontalGravity.Trailing`
* `iconHorizontalGravity = TitleIconHorizontalGravity.OppositeControlButtons`

resulting in

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-layout-kde.png" border=0>

### Aurora integrated

Use `AuroraWindowTitlePaneConfigurations.AuroraIntegrated` to have your window's title pane be provided by Aurora with support for integrating your application content into the window title pane:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-auroraintegrated2.png" border=0>

In this configuration, the title pane displays the window control buttons (minimize, maximize / restore and close), and provides APIs to integrate your application content.

The first step in configuring the integrated mode is to configure the `AuroraIntegrated` object itself:

| Parameter | Default value | Meaning |
| --- | --- | --- |
| **titleControlButtonGroupHorizontalGravity** | **Trailing** | Horizontal gravity for the control buttons |
| **titleControlButtonGroupVerticalGravity** | **Centered** | Vertical gravity for the control buttons |
| **titlePaneHeight** | **WindowTitlePaneSizingConstants. MinimumTitlePaneHeight** | Title pane height, cannot be less than `MinimumTitlePaneHeight` |
| **titlePaneButtonsProvider** | **DefaultTitlePaneButtonsProvider** | Icon provider for the buttons |

In the screenshot above, the title pane is configured with:

* `titlePaneHeight = 40.dp`
* `titleControlButtonGroupHorizontalGravity = HorizontalGravity.Leading`
* `titleControlButtonGroupVerticalGravity = VerticalGravity.Centered`

The next step is to configure your application content to not overlap the area occupied by the title pane control buttons. To do that, use `AuroraWindowScope.getTitlePaneControlInsets` API that returns a `PaddingValues` object:

```
val density = LocalDensity.current
val layoutDirection = LocalLayoutDirection.current

val titlePaneControlInsets = getTitlePaneControlInsets(layoutDirection, density)

MyTopPane(
  modifier = Modifier
    .height(40.dp)
    .padding(
      start = titlePaneControlInsets.calculateStartPadding(layoutDirection),
      end = titlePaneControlInsets.calculateEndPadding(layoutDirection)
    )
) {
  ...
}
```

You should account for both sides of the horizontal part of the title pane control insets to account for left-to-right and right-to-left modes, as well as possible changes to your integrated title pane configuration in the future.

The vertical part of title pane control insets will give you the vertical insets of the title pane control buttons within the title pane itself.

The next step is to use the `WindowDraggableArea` composable to allow your users to drag your window by grabbing its title pane area. Now our snippet becomes:

```
val density = LocalDensity.current
val layoutDirection = LocalLayoutDirection.current

val titlePaneControlInsets = getTitlePaneControlInsets(layoutDirection, density)

WindowDraggableArea(
  modifier = Modifier
    .height(40.dp)
    .padding(
      start = titlePaneControlInsets.calculateStartPadding(layoutDirection),
      end = titlePaneControlInsets.calculateEndPadding(layoutDirection)
    )
) {
  ...
}
```

The next, optional step, is to use a combination of `AuroraDecorationArea`, `DecorationAreaType.TitlePane` and `Modifier.auroraBackground` to mark the integrated title pane - and the application content that extends into it - to be styled consistently with how Aurora styles plain title panes. Let's take another look at the screenshot:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-auroraintegrated2.png" border=0>

Here, the part of the application content that is integrated into the title pane is marked with `DecorationAreaType.TitlePane`, and the matching skin visuals are applied on all the components added to it, including the "New chat" button and the text field on the right.

With these changes, our code snippet becomes:

```
val density = LocalDensity.current
val layoutDirection = LocalLayoutDirection.current

val titlePaneControlInsets = getTitlePaneControlInsets(layoutDirection, density)

AuroraDecorationArea(decorationAreaType = DecorationAreaType.TitlePane) {
  WindowDraggableArea(
    modifier = Modifier
      .height(40.dp)
      .auroraBackground()
      .padding(
        start = titlePaneControlInsets.calculateStartPadding(layoutDirection),
        end = titlePaneControlInsets.calculateEndPadding(layoutDirection)
      )
  ) {
    ...
  }
}
```

The final two optional pieces are the APIs for placing visually consistent text and buttons into the integrated title pane:

* `AuroraWindowTitlePaneTitleText` for the title text
* `AuroraWindowTitlePaneButton` for a button that is meant to look like Aurora-provided window title pane control button

In the example above, the "Chat" label in the middle of the title pane is implemented with a `AuroraWindowTitlePaneTitleText` composable.

Let's take a look at another example of an integrated title pane and the moving pieces of the implementation:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-auroraintegrated1.png" border=0>

* Top-level configuration of `AuroraWindow` with `AuroraWindowTitlePaneConfigurations.AuroraIntegrated` that uses `Leading` horizontal and `Centered` vertical gravity for the control buttons.
* Each one of the three vertical panes uses `WindowDraggableArea` to wrap the part of their content that is integrated into the title pane - the refresh button in the left pane, the search box in the middle, and the row of icons in the right.
* There is no "unified" visual appearance to the title pane area, so none of these panes use `AuroraDecorationArea` with `DecorationAreaType.TitlePane` or the `Modifier.auroraBackground`. Instead, each individual pane is wrapped in its own `AuroraDecorationArea` that is used to apply a separate styling on all the elements in that pane (bluish-grey with yellow highlights in the left, metal grey with blue highlights in the middle).
* `AuroraWindowTitlePaneButton` for the refresh button placed into the top-right corner of the left pane.

### Customizing title pane buttons

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/titlepane/titlepane-button-icons.png" border=0>

**Aurora Plain** and **Aurora Integrated** modes also support an additional parameter that allows controlling the visual appearance of the icons used on the title pane buttons.

The **TitlePaneButtonsProvider** and **TitlePaneButtonProvider** define the app-facing APIs for providing the icons for these four actions:

* Close the window
* Maximize the window
* Restore the maximized window to its previous state
* Iconify / minimize the window

You can either implement the **TitlePaneButtonsProvider** interface, or extend the **DefaultTitlePaneButtonsProvider** implementation to tweak the visuals of these four icons based on your design needs.
