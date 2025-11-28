## Components - ribbon resizing

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize0.png" width="1452" border=0/>

The ribbon is a highly dynamic container that adapts the layout of its content (selected ribbon task, taskbar, etc) to the available space. As the developer of your application, you are providing two things:

- The content itself
- Hints for resizability

We have seen these hints earlier in [command projection](RibbonBandCommandProjections.md) and [gallery](RibbonBandGalleries.md) ribbon APIs. Let's take another look.

### Priority hints for command projections

```kotlin
val clipboardBand = RibbonBand(
    title = resourceBundle.getString("Clipboard.textBandTitle"),
    icon = edit_paste(),
    expandCommand = Command(...),
    expandCommandKeyTip = "FO",
    collapsedStateKeyTip = "ZC",
    resizePolicies = listOf(CoreRibbonResizePolicies.Mirror, CoreRibbonResizePolicies.Mid2Low),
    groups = listOf(
        RibbonBandCommandGroup(
            commandProjections = listOf(
                CommandButtonProjection(
                    contentModel = pasteCommand,
                    presentationModel = CommandButtonPresentationModel(...)
                ) at PresentationPriority.Top,
                CommandButtonProjection(
                    contentModel = cutCommand,
                    presentationModel = CommandButtonPresentationModel(...)
                ) at PresentationPriority.Medium,
                CommandButtonProjection(
                    contentModel = copyCommand,
                    presentationModel = CommandButtonPresentationModel(...)
                ) at PresentationPriority.Medium,
                CommandButtonProjection(
                    contentModel = formatCommand,
                    presentationModel = CommandButtonPresentationModel(...)
                ) at PresentationPriority.Medium
            )
        )
    )
)
```

Here we have four command projections added to the first "Clipboard" ribbon band. One projection is added at `PresentationPriority.Top`, and the other three at `PresentationPriority.Medium`.

It is a hint to the ribbon's built-in layout logic that is used to decide how to layout content in this ribbon band, and what the presentation state of each command projection should be. In this particular case, `PresentationPriority.Top` results in the "Paste" command projection to be displayed in `Big` presentation state. When there is enough horizontal space, the other three are is displayed at `Medium` presentation state. But if the ribbon is resized to be more narrow, those projections will be in the `Small` presentation state instead (displaying only their icon) - we'll see screenshots shortly.

### Priority hints for in-ribbon galleries

And here is another look at specifying presentation priorities for the [in-ribbon gallery](RibbonBandGalleries.md) in the "Quick Styles" band:

```kotlin
val styleGalleryInlineMetaPresentationModel = RibbonGalleryPresentationModel(
    popupLayoutSpec = MenuPopupPanelLayoutSpec(
        columnCount = 3, visibleRowCount = 3
    ),
    commandButtonPresentationState = RibbonBandCommandButtonPresentationStates.BigFixedLandscape,
    commandButtonTextOverflow = TextOverflow.Ellipsis,
    expandKeyTip = "L",
    collapsedVisibleCountLow = 1,
    collapsedVisibleCountMedium = 2,
    collapsedVisibleCountTop = 2
)

val styleGalleryInlineProjection = RibbonGalleryProjection(
    contentModel = styleGalleryContentModel,
    presentationModel = styleGalleryInlineMetaPresentationModel,
    inlineState = ribbonState.documentStyleGalleryInlineState
    secondaryOverlays = mapOf(...)
)

RibbonBand(
    title = resourceBundle.getString("QuickStyles.textBandTitle"),
    icon = preferences_desktop_theme(),
    collapsedStateKeyTip = "ZS",
    resizePolicies = CoreRibbonResizePolicies.getCorePoliciesRestrictive(),
    groups = listOf(
        RibbonBandCommandGroup(
            commandProjections = listOf(...),
            galleries = listOf(
                styleGalleryProjection at PresentationPriority.Top
            )
        )
    )
)
```

The gallert presentation model defines how many (projected) commands we want to be displayed inline (not in the expanded / popup state) for every presentation priority. As the ribbon is resized, this mapping will be used to show more of fewer of the projected commands. The last part adds the gallery projection at the `PresentationPriority.Top`, so that we start with 2 visible projected commands when there is enough horizontal space.

But what happens when the ribbon is resized, and available horizontal space is reduced?

### Adaptive resizing

What happens when the user starts resizing the ribbon frame, progressively reducing the amount of horizontal space available to the ribbon content? In more traditional containers, once the content does not fit in the available space, scrolling kicks in.

The ribbon uses a more dynamic model of adapting to increased or reduced horizontal space, with the following (rough) model of what happens when space is reduced:

- Display "less" of the same content. For example, a stack of three `Medium` buttons that display icon + text becomes a stack of three `Small` buttons that only display icons.
- Replace the ribbon band content with a single large popup button that, when activated, shows the full content in a popup.
- Only as the last resort kick in the horizontal scrolling at the ribbon level.

From the application perspective, this model is controlled by two APIs:

- `RibbonTask.resizeSequencingPolicy` of type `RibbonBandResizeSequencingPolicy`
- `AbstractRibbonBand.resizePolicies` of type `List<RibbonBandResizePolicy>`

### Resize sequencing policy

The resize sequencing policy defines which ribbon band will be chosen next when the ribbon is shrunk / expanded. It is installed with the `RibbonTask.resizeSequencingPolicy` property.

The `CoreRibbonResizeSequencingPolicies` object provides two built in resize sequencing policies:

- `RoundRobin` under which the ribbon bands are being collapsed in a cyclic fashion, distributing the collapsed pixels between the different bands.
- `CollapseFromLast` under which the ribbon bands are being collapsed from last (right under LTR) to first (left under LTR).

The choice of the resize sequencing policy is a design decision left to the application developer. `RoundRobin` is the default policy. However, depending on the content of your specific ribbon you may want to use `CollapseFromLast` which, overall, introduces less "jumping around" of the content across the entire ribbon surface as it is resized.

### Resize policy

The `RibbonBandResizePolicy` is the base interface for resize policies used in general and flow ribbon bands. The resize policy defines a single visual state of the given ribbon band. For every control in the specific ribbon band (command button, gallery etc), the resize policy defines what is its presentation state - based on the priority hints explained above.

The resize policies are installed with the `AbstractRibbonBand.resizePolicies` property. The order of the resize policies in this list is important. The first entry in the list must be the most permissive policy that returns the largest value from its `getPreferredWidth(AbstractRibbonBand, Int, Int)`. Each successive entry in the list must return the value smaller than its predecessors.

The `CoreRibbonResizePolicies` object provides a number of built in resize policies that respect the hints passed to
`RibbonBandCommandGroup` properties. There are three types of built in resize policies:

- Resize policies for the `FlowRibbonBand`s. The `FlowOneRow`, `FlowTwoRows` and `FlowThreeRows` allow placing the flow ribbon band content in one, two and three rows respectively.
- Resize policies for the `RibbonBand`s. The
`BaseCoreRibbonBandResizePolicy` is the base class for these policies. These policies respect the `PresentationPriority` associated with command buttons and ribbon galleries. They can also change the presentation state of the command buttons and the number of visible buttons in the ribbon galleries.
- The collapsed policy that replaces the entire content of the ribbon band with a single popup button. This is done when there is not enough horizontal space to show the content of the ribbon band under the most restrictive resize policy. Activating the popup button will show the original content under the most permissive resize policy in a popup. This policy is implemented in the  `Icon` resize policy.

In addition to the specific resize policies, the
`CoreRibbonResizePolicies` object provides three core resize policies lists for `RibbonBand`s:

- `getCorePoliciesPermissive()` returns a list that starts with a resize policy that shows all command
buttons in the `BIG` presentation state and ribbon galleries
with the largest number of visible buttons, fully utilizing the available screen space.
- `getCorePoliciesRestrictive()` returns a list that starts with a resize policy that respects the priority hint associated with the specific components.
- `getCorePoliciesNone()` returns
a list that only has a <code>mirror</code> resize policy that respects the priority hint associated with the specific components.

In addition, the `getCoreFlowPoliciesRestrictive(int)` returns a restrictive resize policy for `FlowRibbonBand`s. The list starts with the two-row policy, goes to the three-row policy and then finally to the collapsed policy.

### Hints + resize policies in action

Starting with the original content displayed with enough available width:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize0.png" width="1452" border=0/>

Now we shrink the ribbon a bit:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize1.png" width="1365" border=0/>

Take a look at the rightmost band ("Find (toggle)"). It is now in iconified state, being displayed as a single popup button. This is due to the matching list of resize policies installed on it:

```kotlin
resizePolicies = listOf(CoreRibbonResizePolicies.Mirror, CoreRibbonResizePolicies.Icon),
```

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize2.png" width="1146" border=0/>

Under the default `RoundRobin` resize sequencing policy, the "Document" band is the next one to shrink. It is using `CoreRibbonResizePolicies.getCorePoliciesRestrictive()` list of resize policies that, at this first shrink step, leaves buttons with `Top` priority hint as `Big`, but changes the buttons with `Medium` priority hint from `Medium` (icon + text) to `Small` (icon only).

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize3.png" width="1097" border=0/>

The "Font" band is the next one to shrink. It is using the default `CoreRibbonResizePolicies.getCoreFlowPoliciesRestrictive()` list of resize policies that is configured to "allow" its content to stay at two rows at this iteration. Since that is the case, the `RoundRobin` resize sequencing policy goes to the next band - "Quick Styles".

Taking another look at the hint mapping for the in-ribbon gallery in that band:

```kotlin
val styleGalleryInlineMetaPresentationModel = RibbonGalleryPresentationModel(
    popupLayoutSpec = MenuPopupPanelLayoutSpec(
        columnCount = 3, visibleRowCount = 3
    ),
    commandButtonPresentationState = RibbonBandCommandButtonPresentationStates.BigFixedLandscape,
    commandButtonTextOverflow = TextOverflow.Ellipsis,
    expandKeyTip = "L",
    collapsedVisibleCountLow = 1,
    collapsedVisibleCountMedium = 2,
    collapsedVisibleCountTop = 2
)
```

We see that the gallery has requested to still display two commands in this reduced state. But the buttons next to it went from `Medium` to `Small` presentation state, losing their texts.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize4.png" width="1045" border=0/>

Now the `Medium` buttons in the "Clipboard" band went to `Small` presentation state, losing their texts.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize5.png" width="960" border=0/>

The pass skips the last "Find (toggle)" ribbon band (since it's already in iconified state), and switches the three `Big` buttons in the "Document" band to a vertical stack of three `Medium` ones.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize6.png" width="900" border=0/>

This pass skips all the way back to the "Document" band again - based on the resize policies associated with the first three ribbon bands that, in this particular case, the application design considers more important. Now the vertical stack of three `Medium` buttons has switched to three `Small` ones.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize7.png" width="846" border=0/>

Now it's time for the "Font" flow band to switch from 2-row to 3-row layout. All the content is still there. It's just a bit more compact.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize8.png" width="765" border=0/>

This pass has switched the in-ribbon gallery in "Quick Styles" to display only one button. Yet again, all the gallery content is still there. You can still scroll up and down, and open the larger grid in the popup.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize9.png" width="728" border=0/>

This pass skips over the higher priority "Clipboard" band and goes to iconify the "Document" band content.

Let's shrink the ribbon a bit more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize10.png" width="635" border=0/>

The ribbon resize policies set on the "Font" band keep it at the 3-row layout still, and the "Quick Styles" band is iconified.

Let's shrink the ribbon even more:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/ribbon/bands/bands-resize11.png" width="459" border=0/>

Now all the ribbon bands except for the "Clipboard" are iconified. What is going to happen when we shrink the ribbon again? Let's take a look at the resize policies set on the "Clipboard" band:

```kotlin
resizePolicies = listOf(CoreRibbonResizePolicies.Mirror, CoreRibbonResizePolicies.Mid2Low),
```

Here, the design places such an importance on this band that it never goes to the iconified state. The next phase here is to kick in horizontal scrolling on this entire ribbon task content, leaving the "Clipboard" content as it is shown in the last screenshot above.

### Conclusion

The ribbon container has a powerful set of APIs that allows extending the default resize behavior based on the relative importance of elements in each specific ribbon task. You can go with the default resize policies, or you can go as detailed as you want to fully control what gets resized, and in which order that resizing happens.

### Next

Continue to [ribbon application menu](RibbonApplicationMenu.md).
