## Components - ribbon global contextual listener

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/taskbar/taskbar-contextualmenu-add.png" width="1363" border=0/>

The `onShowContextualMenuListener` property of the `Ribbon` data class API allows configuring the global contextual menu listener that will be used to populate content of a menu displayed on any mouse event that is a popup trigger (aka right-click).

The application is in charge of implementing the methods in `OnShowContextualMenuListener` listener to return a [`CommandMenuContentModel`](CommandPopupMenu.md) content model that will be used to project the contextual menu.

In the screenshot above the user right-clicked on the "font size" combobox in the "Font" ribbon band. The menu shows commands for adding that combobox to [taskbar](RibbonTaskbar.md), minimizing the ribbon and general ribbon configuration.

Let's take a look at the code behind populating this specific menu (in the main ribbon demo application):

```kotlin
override fun <C : ContentModel, P : PresentationModel> getContextualMenuContentModel(
    ribbon: Ribbon,
    componentProjection: Projection<C, P>
): CommandMenuContentModel {
    val isInTaskbar = ribbon.taskbarElements.find {
        (it is RibbonTaskbarComponent) &&
                (it.componentProjection.contentModel == componentProjection.contentModel)
    } != null
    val componentCommand = if (isInTaskbar) {
        Command(text = resourceBundle.getString("ContextMenu.removeFromTaskbar"),
            action = {
                taskbarElements.removeIf {
                    (it is RibbonTaskbarComponent) &&
                            it.componentProjection.contentModel == componentProjection.contentModel
                }
            }
        )
    } else {
        Command(text = resourceBundle.getString("ContextMenu.addToTaskbar"),
            action = {
                // Special treatment for the font family combobox - to use a different
                // presentation model in the taskbar
                if (componentProjection.contentModel == fontFamilyComboBoxContentModel) {
                    taskbarElements.add(
                        RibbonTaskbarComponent(
                            ComboBoxProjection(
                                contentModel = fontFamilyComboBoxContentModel,
                                presentationModel = ComboBoxPresentationModel(
                                    displayConverter = { it.name }
                                ),
                            )
                        )
                    )
                } else {
                    taskbarElements.add(RibbonTaskbarComponent(componentProjection))
                }
            }
        )
    }
    return build(ribbon, componentCommand)
}
```

We use `Ribbon.taskbarElements` property to determine whether this component projection is already in the taskbar. That determines whether we show the "Add to taskbar" command or "Remove from taskbar" command. This is how you can enable the user to configure the exact taskbar content that they want to always see and access via keytips.

Most of the logic in this implementation is specific to `ComponentProjection`, and you will probably want to have something similar for commands and galleries. The last line adds the projection-independent menu commands:

```kotlin
private fun build(ribbon: Ribbon, vararg commands: Command): CommandMenuContentModel {
    val allCommands: MutableList<Command> = arrayListOf()
    if (commands.isNotEmpty()) {
        allCommands.addAll(commands)
    }

    if (minimizedMode) {
        allCommands.add(Command(
            text = resourceBundle.getString("ContextMenu.showRibbon"),
            action = { minimizedMode = false }
        ))
    } else {
        allCommands.add(Command(
            text = resourceBundle.getString("ContextMenu.hideRibbon"),
            action = { minimizedMode = true }
        ))
    }
    allCommands.add(Command(
        text = resourceBundle.getString("ContextMenu.configureRibbon"),
        action = { println("Configure ribbon option selected") }
    ))

    return CommandMenuContentModel(groups = listOf(CommandGroup(commands = allCommands.toList())))
}
```

The first is the command to minimize / restore the ribbon, using a `MutableState`-backed boolean that is passed as `isMinimized` property to the `Ribbon` data class. The second is a placeholder command to open a custom UI for deep configuration of the entire ribbon content - that is left to the particular application needs.

Here is our global contextual menu shown when the user right-clicks one of the comboboxes in the taskbar:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/taskbar/taskbar-contextualmenu-remove.png" width="1363" border=0/>

and the taskbar updated after "Remove from taskbar" menu command has been activated:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/taskbar/taskbar-contextualmenu-removed.png" width="1363" border=0/>

Here you can see how the available taskbar space is now enough to host additional content (a combobox and a command button) that were previously displayed in the overflow, even removing the need for overflow since the entire taskbar content fits into the available space.
