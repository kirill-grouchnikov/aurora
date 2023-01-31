## Components - content models, presentation models, projections

Let's take a deeper look all the pieces required to create an Aurora component.

### Content model

All content models implement the `ContentModel` interface.

Here is a content model data class for Aurora's comboboxes:

```kotlin
data class ComboBoxContentModel<E>(
    val items: List<E>,
    val selectedItem: E,
    val richTooltip: RichTooltip? = null,
    val enabled: Boolean = true,
    val onTriggerItemSelectedChange: (E) -> Unit
): ContentModel
```

### Presentation model

A presentation model implements the `PresentationModel` interface, and should aim to provide reasonable default values for most of the presentation attributes.

Default values for attributes that are related to layout metrics (minimum size, content paddings, margins, etc) can be grouped into a public `object` for code reusal.

Here is such an object for the default combobox presentation layout metrics:

```kotlin
object ComboBoxSizingConstants {
    val DefaultComboBoxArrowWidth = 10.dp
    val DefaultComboBoxArrowHeight = 7.dp
    val DefaultComboBoxContentArrowGap = 6.dp
    val DefaultComboBoxContentPadding =
        PaddingValues(start = 8.dp, top = 3.dp, end = 8.dp, bottom = 4.dp)
    val DefaultComboBoxContentWidth = 60.dp
    val DefaultComboBoxContentHeight = 16.dp
}
```

And Aurora's combobox presentation model:

```kotlin
data class ComboBoxPresentationModel<E>(
    val backgroundAppearanceStrategy: BackgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always,
    val displayConverter: (E) -> String,
    val displayIconConverter: ((E) -> Painter)? = null,
    val contentPadding: PaddingValues = ComboBoxSizingConstants.DefaultComboBoxContentPadding,
    val horizontalAlignment: HorizontalAlignment = HorizontalAlignment.Leading,
    val horizontalGapScaleFactor: Float = 1.0f,
    val popupPlacementStrategy: PopupPlacementStrategy = PopupPlacementStrategy.Downward.HAlignStart,
    val popupMaxVisibleItems: Int = 8,
    val richTooltipPresentationModel: RichTooltipPresentationModel = RichTooltipPresentationModel()
): PresentationModel
```

### Projection

A projection class constructor takes two mandatory parameters - content model and presentation model, and optionally a list of overlays.

A projection class should have a `@Composable` function that projects the content model onto the screen using the presentation model and a `Modifier`. Optionally, this `project` function can accept one or more `MutableInteractionSource` objects to allow the calling code to observe or modify the interaction state of the projected composable.

Here is Aurora's combobox projection class:

```kotlin
class ComboBoxProjection<E>(
    val contentModel: ComboBoxContentModel<E>,
    val presentationModel: ComboBoxPresentationModel<E>
) {
    @Composable
    fun project(
        modifier: Modifier = Modifier,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ) {
        AuroraComboBox(
            modifier = modifier,
            interactionSource = interactionSource,
            contentModel = this.contentModel,
            presentationModel = this.presentationModel
        )
    }
}
```

Note that, by design, Aurora's actual composable components (such as the `AuroraComboBox` function in here) are not part of the public API. This forces the application code to structure the data around these core building blocks:

A **content model** describes the basic elements of a piece of your model realm, how the user interacts with it, and what happens when that interaction happens.

A **presentation model** describes how to "convert" (or project) that content model into a composable that can be added to the application UI hierarchy to present the data backed by that content model and react to the user interaction.

A **projection** is the act of "combining" a content model and a presentation model and creating a composable.

In a nutshell:

- **content model + presentation model &#8594; projection**
- **projection &#8594; one or more composables**

Bringing it all together, here is a sample Aurora's combobox:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/combo-dropdown.png" width="234" border=0/>

```kotlin
// Example of a combobox with display converter for more complex data
data class Person(val firstName: String, val lastName: String)

val personComboItems =
    listOf(
        Person("Bob", "Loblaw"),
        Person("Paige", "Turner"),
        Person("Donaldson", "Duck")
    )
val personComboSelectedItem = remember { mutableStateOf(personComboItems[0]) }
ComboBoxProjection(
    contentModel = ComboBoxContentModel(
        enabled = contentEnabled.value,
        items = personComboItems,
        selectedItem = personComboSelectedItem.value,
        onTriggerItemSelectedChange = {
            personComboSelectedItem.value = it
            println("$it selected!")
        }
    ),
    presentationModel = ComboBoxPresentationModel(
        displayConverter = { it.lastName + ", " + it.firstName },
        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Always
    )
).project()
```

Here we have a data class with first and last name attributes. Our `ComboBoxContentModel` keeps track of the currently selected item and updates it in `onTriggerItemSelectedChange` lambda. And our `ComboBoxPresentationModel` provides a custom `displayConverter` that uses the combination of the first and last name for each `Person` object in the content model.

### Next

Continue to [all component projections](ComponentProjections.md).
