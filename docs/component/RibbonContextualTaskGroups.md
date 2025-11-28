## Components - ribbon contextual task groups

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/ribbon-start.png" width="1181" border=0/>

When you use the ribbon container in your application, you need to ask yourself what is the logical structure of all the available operations - or commands - that your users can activate to interact with the application content. At the top level of such structure you would find the ribbon tasks - "Page Layout", "Write", "Animations" and "Wrapped" in the screenshot above. At the second level you would find the ribbon bands - "Clipboard", "Quick Styles", "Font", "Document" and "Find (toggle)" for the "Page Layout" task.

However, some of those top-level groups of operations (or tasks) are only applicable when a very specific condition occurs in the user interaction with the application content. For example, a text editor might expose a rich variety of operations available on embedded tables or charts. But it does not necessarily make sense to always display those operations at the top level of your ribbon UI.

This is where contextual task groups come in play. They allow you to dynamically show and hide one or more tasks based on those specific conditions.

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/contextualtasks/contextual-task-added.png" width="1359" border=0/>

The screenshot above shows a contextual task group named "Group 1" that has two tasks ("Group 1A" and "Group 1B") that was marked as visible. For as long as this contextual task group remains visible, the user can interact with its content. Here is how it looks like when the mouse is moved over the task toggle button of its first task - using group-specific hue color for consistent visual delineation of the entire group:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/contextualtasks/contextual-task-rollover.png" width="1359" border=0/>

And when that task toggle button is activated, the associated task becomes selected:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/contextualtasks/contextual-task-selected.png" width="1359" border=0/>

You can have more than one contextual task group visible:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/contextualtasks/contextual-task-another-added.png" width="1359" border=0/>

This one has green hue color that is used for highlighting its title in the window title pane, as well as for the task toggle button when it's activated to be selected:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/contextualtasks/contextual-task-another-selected.png" width="1359" border=0/>

### Working with contextual task groups

First, add your contextual tasks to the `RibbonContextualTaskGroup`. Each contextual task is defined with the same `RibbonTask` data class as you use for your regular ribbon tasks.

```kotlin
val contextualTaskGroup1 = RibbonContextualTaskGroup(
    title = resourceBundle.getString("Group1.textTaskGroupTitle"),
    hueColor = Color.Red,
    tasks = listOf(
        RibbonTask(
            title = resourceBundle.getString("Task11.textTaskTitle"),
            bands = listOf(actionBand, arrangeBand, previewBand, transitionBand),
            resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin,
            keyTip = "XA",
            isActive = (ribbonState.selectedTask == Task.Contextual11),
            onClick = { ribbonState = ribbonState.copy(selectedTask = Task.Contextual11) }
        ),
        RibbonTask(
            title = resourceBundle.getString("Task12.textTaskTitle"),
            bands = listOf(...),
            resizeSequencingPolicy = CoreRibbonResizeSequencingPolicies.RoundRobin,
            keyTip = "XB",
            isActive = (ribbonState.selectedTask == Task.Contextual12),
            onClick = { ribbonState = ribbonState.copy(selectedTask = Task.Contextual12) }
        )
    )
)
val contextualTaskGroup2 = RibbonContextualTaskGroup(...)

val contextualTaskGroups = mutableListOf<RibbonContextualTaskGroup>()
if (contextualTaskGroup1Visible) {
   contextualTaskGroups.add(contextualTaskGroup1)
}
if (contextualTaskGroup2Visible) {
   contextualTaskGroups.add(contextualTaskGroup2)
}

```

and then you pass your contextual task group list to the ribbon:

```kotlin
val ribbon = Ribbon(
    tasks = listOf(pageLayoutTask, writeTask, animationsTask),
    contextualTaskGroups = contextualTaskGroups,
    taskbarElements = taskbarElements,
    taskbarKeyTipPolicy = DefaultRibbonTaskbarKeyTipPolicy(),
    anchoredCommands = builder.getAnchoredCommands(),
    applicationMenuCommandButtonProjection = applicationMenuCommandButtonProjection,
    isMinimized = minimizedMode,
    onShowContextualMenuListener = onShowContextualMenuListener
)
```

All contextual task groups passed to the `contextualTaskGroups` property are visible in the UI. Use higher level state tracking for the boolean (or more complex) logic that tracks whether a specific contextual task group should be passed to the `Ribbon` data class constructor, based on your specific app logic.

In your real application, the "trigger" condition may be selection or deselection of certain elements in the main content area / canvas - such as the aforementioned tables and charts in a text editor.

### Next

Continue to [ribbon taskbar](RibbonTaskbar.md).
