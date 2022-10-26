## Components - all modifiers

Modifiers are a ubiquitous and versatile way to extend and augment composables. Modifiers are used to specify layout behavior, provide custom drawing, process user input, etc.

Aurora provides a number of modifiers for consistent user experience across different surfaces in your application.

### Drawing background of decoration areas and containers

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/painters/overlay/gemini.png" width="646"/>

The app in this screenshot is using Aurora's [decoration areas](../theming/painters/decoration.md) to create visual grouping of certain functional areas. Under the Gemini skin, the title bar and the menu bar use dark grey background fill, the toolbar is using dark blue fill, and the footer is using a medium grey fill.

Here is the layout skeleton:

```kotlin
Column(modifier = Modifier.fillMaxSize()) {
  AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
    DemoToolbar(
      alignmentCommands = alignmentCommands,
      styleCommands = styleCommands,
      resourceBundle = resourceBundle,
      iconDimension = 20.dp
    )
  }
  Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
  AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
    DemoSkeletonFooter(auroraSkinDefinition = auroraSkinDefinition)
  }
}
```

`AuroraDecorationArea` is not a layout composable itself. Instead, it sets up the internal bits for Aurora to apply the right visuals to all the projected child composables. To get the right visuals for the background fill of the contents of `AuroraDecorationArea`, use `Modifier.auroraBackground()` on the top-level container that you add:

```kotlin
@Composable
fun DemoSkeletonFooter(
    modifier: Modifier = Modifier,
    auroraSkinDefinition: MutableState<AuroraSkinDefinition>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .auroraBackground()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Spacer(modifier.weight(weight = 1.0f, fill = true))
        AuroraSkinSwitcher(auroraSkinDefinition, PopupPlacementStrategy.Upward.HAlignStart)
    }
}
```

### Showing context menus

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/command-context-menu.png" width="342" border=0/>

Use `Modifier.auroraContextMenu()` to display context menus. See [context menu documentation](ContextMenu.md) for more information.

```kotlin
LabelProjection(
  contentModel = LabelContentModel(
    text = resourceBundle.getString("ContextMenu.show"),
    enabled = contentEnabled.value
  )
).project(
  modifier = Modifier.auroraContextMenu(
    enabled = contentEnabled.value,
    contentModel = CommandMenuContentModel(
      groups = listOf(
        CommandGroup(commands = commands1),
        CommandGroup(commands = commands2),
        CommandGroup(commands = commands3)
      )
    ),
    presentationModel = CommandPopupMenuPresentationModel(
      popupPlacementStrategy = PopupPlacementStrategy.Upward.HAlignStart,
      toDismissOnCommandActivation = false
    )
  )
)
```

### Displaying rich tooltips

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/command-tooltips.png" width="657" border=0/>

Use `Modifier.auroraRichTooltip()` to display rich tooltip on hover:

```kotlin
Row(
  modifier = modifier
      .padding(presentationModel.contentPadding)
      .auroraRichTooltip(
          richTooltip = contentModel.richTooltip,
          presentationModel = presentationModel.richTooltipPresentationModel
      )
      ///
    ,
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = presentationModel.horizontalAlignment.arrangement
) {
```
