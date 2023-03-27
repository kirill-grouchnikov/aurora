## Components - context menus

In addition to displaying popup menus [associated with a command](CommandPopupMenu.md), Aurora provides a custom modifier to display context menus.

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/command-context-menu.png" width="342" border=0/>

Here, right-clicking (or Ctrl+left click on macOS) the label will display this popup menu. Let's see the moving pieces.

### Context menu modifier

Aurora's context menu modifier looks like this:

```kotlin
@Composable
fun Modifier.auroraContextMenu(
    enabled: Boolean = true,
    contentModel: CommandMenuContentModel,
    presentationModel: CommandPopupMenuPresentationModel = CommandPopupMenuPresentationModel(),
    overlays: Map<Command, BaseCommandButtonPresentationModel.Overlay> = mapOf()
): Modifier
```

It accepts the same `CommandMenuContentModel` as the content model and `CommandPopupMenuPresentationModel` as the presentation model.

### Sample code

Let's see the code behind this popup menu:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/command-context-menu.png" width="342" border=0/>

The state behind each one of the toggle commands is:

```kotlin
val count = 10
val toggleStates = remember { mutableStateListOf<Boolean>() }
for (i in 0 until count) {
  toggleStates.add(false)
}

val commands1 = arrayListOf<Command>()
val commands2 = arrayListOf<Command>()
val commands3 = arrayListOf<Command>()

val mf = MessageFormat(resourceBundle.getString("TestMenuItem.text"))
for (i in 0 until count) {
  val command = Command(
    text = mf.format(arrayOf<Any>(i)),
    isActionToggle = true,
    isActionToggleSelected = toggleStates[i],
    onTriggerActionToggleSelectedChange = {
      toggleStates[i] = it
    }
  )

  when (i) {
    0, 1, 2 -> commands1.add(command)
    3, 4, 5, 6 -> commands2.add(command)
    else -> commands3.add(command)
  }
}
```

And now we create our `LabelProjection`, and pass `Modifier.auroraContextMenu` to its `project` function invocation passing the command menu content model and a presentation model with:

- `popupPlacementStrategy` set to `PopupPlacementStrategy.Upward.HAlignStart` to display the context menu above the clicked point (if possible)
- `toDismissOnCommandActivation` set to `false` to allow toggling multiple menu commands without automatic dismissal of the whole context menu on every toggle

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

### Next

Continue to [all custom modifiers](Modifiers.md).
