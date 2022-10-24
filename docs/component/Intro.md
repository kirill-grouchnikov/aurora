## Components - introduction

Most of Aurora's composable components are created as projections. Projections are built from content models and presentation models.

A **content model** describes the basic elements of a piece of your model realm, how the user interacts with it, and what happens when that interaction happens.

A **presentation model** describes how to "convert" (or project) that content model into a composable that can be added to the application UI hierarchy to present the data backed by that content model and react to the user interaction.

A **projection** is the act of "combining" a content model and a presentation model and creating a composable.

In a nutshell:

- **content model + presentation model &#8594; projection**
- **projection &#8594; one or more composables**

### Example

Let's take a look at these three concepts in action:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/command-basics.png" width="650" border=0/>

Here is how the action button in the bottom left (big icon with "Action" text underneath) is created:

```kotlin
val commandActionOnly =
  Command(
      text = resourceBundle.getString("Action.text"),
      extraText = resourceBundle.getString("Action.textExtra"),
      icon = accessories_text_editor(),
      action = { println("Action activated!") }
  )

val presentationModel = CommandButtonPresentationModel(
  presentationState = CommandButtonPresentationState.Big)

CommandButtonProjection(
  contentModel = commandActionOnly,
  presentationModel = presentationModel
).project()
```

First, we create a `Command` which is a content model. It sets text, extra text and icon factory as the basic elements that describe this piece of application model realm, as well as the action lambda that gets invoked when the user interacts with the button on the screen.

Then we construct a `CommandButtonPresentationModel` which is a presentation model. It specifies that we want to use the `Big` presentation state (big icon + one or two lines of text underneath).

Then, we combine them together by calling `CommandButtonProjection.project()` that is a [`@Composable` function](https://developer.android.com/jetpack/compose/tutorial) that will display a command button on the screen in the component hierarchy of our application.

### Next

Continue to a [more detailed sample walkthrough](Sample.md).
