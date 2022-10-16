## Aurora theming - color scheme association kinds

Color scheme association kinds in Aurora are best illustrated by a simple example:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/color-scheme-association-kinds.png" width="96" height="96"/>

This is a screenshot of a checkbox composable icon under 72 point font. This checkmark icon has three different visual areas: inner fill, border and the "V" mark. Each one of these areas is painted with a different [color scheme](colorschemes.md), and this is allowed by using the relevant **color scheme association kinds**.

The `ColorSchemeAssociationKind` is the base class for core and custom color scheme association kinds. Where is this class used?

* The first usage is in the skin definition. The main `AuroraSkinColors` APIs allow associating different color schemes with different visual areas of Aurora composables.
* The specific composables query the component skin for the color schemes that match the relevant visual areas.

Let's go back to the checkbox icon example above. How do we use the color scheme association kinds to specify three different color schemes for painting this checkmark icon?

As detailed in the [skin documentation](overview.md), each skin has a number of [color scheme bundles](colorschemebundles.md). This means that two checkboxes with the same model state (`selected` in our case) can have different visuals, depending on the [decoration areas](../painters/decoration.md) they reside in. In the definition of the specific color scheme bundle, you can specify different [color schemes](colorschemes.md) for different component states. This means that a selected checkbox can use colors different from those of a rollover selected checkbox.

In our case, we want to specify different color schemes for different visual areas of **selected** checkboxes in the default decoration area. The relevant method in the `AuroraColorSchemeBundle` is:

```kotlin
  /**
   * Registers the color scheme to be used for the specified visual area of
   * controls under the specified states. For example, if the light orange
   * scheme has to be used for gradient fill for rollover selected and rollover
   * controls, the parameters would be:
   *
   * <ul>
   * <li><code>scheme</code>=light orange scheme</li>
   * <li>
   * <code>associationKind</code>=[ColorSchemeAssociationKind.Fill]</li>
   * <li>
   * <code>states</code>=[ComponentState.RolloverSelected], [ComponentState.RolloverUnselected]
   * </li>
   * </ul>
   *
   * @param scheme
   *            Color scheme.
   * @param associationKind
   *            Color scheme association kind that specifies the visual areas
   *            of controls to be painted with this color scheme.
   * @param states
   *            Component states that further restrict the usage of the
   *            specified color scheme.
   */
   fun registerColorScheme(
         scheme: AuroraColorScheme,
         associationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
         vararg states: ComponentState
   )
```

* The inner fill is specified by the `ColorSchemeAssociationKind.Fill`
* The border is specified by the `ColorSchemeAssociationKind.Border`
* The mark is specified by the `ColorSchemeAssociationKind.Mark`

Going back once again to the original image:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/color-scheme-association-kinds.png" width="96" height="96"/>

Here is the outline of the relevant configuration code:

```kotlin
val activeScheme = ...
val defaultScheme = ...
val disabledScheme = ...

val defaultBundle = new AuroraColorSchemeBundle(
    activeScheme, defaultScheme, disabledScheme)

val selectedBorderScheme = ...
defaultBundle.registerColorScheme(selectedBorderScheme,
    ColorSchemeAssociationKind.Border, ComponentState.Selected)

val selectedMarkScheme = ...
defaultBundle.registerColorScheme(selectedMarkScheme,
    ColorSchemeAssociationKind.Mark, ComponentState.Selected)
```

Note that there is no explicit usage of the `ColorSchemeAssociationKind.Fill` value. This illustrates the **fallback** mechanism. In this particular case, the second parameter to the `AuroraColorSchemeBundle` constructor is used as the fallback color scheme for inner fills under all component states. The fallback mechanism also extends to the other color scheme association kinds.

Here is the constructor signature of the `ColorSchemeAssociationKind`:

```kotlin
  /**
   * Creates a new association kind.
   *
   * @param name
   *            Association kind name.
   * @param fallback
   *            Fallback association kind. This is used when no color scheme
   *            is associated with this kind. For example, [.TabBorder]
   *            specifies that its fallback is [.Border]. When the
   *            tabbed pane composable is painting the tabs, it will
   *            try to use the color scheme associated with
   *            [.TabBorder]. If none was registered, it will fall back
   *            to use the color scheme associated with [.Border], and
   *            if that is not registered as well, will use the color scheme
   *            associated with [.Fill].
   */
  public ColorSchemeAssociationKind(val name: String,
      val fallback: ColorSchemeAssociationKind?)
```

The second parameter specifies what should happen when the color scheme bundle definition does not have an explicitly registered color scheme for the specific color scheme association kind under the specific component state.

For example, the `ColorSchemeAssociationKind.Mark` has the `ColorSchemeAssociationKind.Border` as its fallback. This means that if you want to use the same color scheme for painting both borders and marks, you need to only call the `AuroraColorSchemeBundle.registerColorScheme` API with the `ColorSchemeAssociationKind.Border` value.

The registered associations are used by the Aurora composables during their drawing. Specifically for the checkbox, the composable queries the three relevant association kinds (`ColorSchemeAssociationKind.Fill`, `ColorSchemeAssociationKind.Border` and `ColorSchemeAssociationKind.Mark`) and uses the relevant painters ([fill](../painters/fill.md) and [border](../painters/border.md)) to paint the matching visual areas.

Applications that want to provide [custom skinning](../painters/custom-skinning.md) of their UIs can use the following two supported APIs in order to get the relevant color schemes.

First, get the current skin colors from `AuroraSkin.colors`. Then, use the following API in the obtained `AuroraSkinColors` object to get the color scheme for the relevant visual area:

```kotlin
  /**
   * Returns the color scheme to be used for painting the specified visual
   * area of the component under the specified component state.
   *
   * @param comp
   *            Component.
   * @param associationKind
   *            Color scheme association kind.
   * @param componentState
   *            Component state.
   * @return Color scheme to be used for painting the specified visual area of
   *         the component under the specified component state.
   */
   fun getColorScheme(
         decorationAreaType: DecorationAreaType,
         associationKind: ColorSchemeAssociationKind,
         componentState: ComponentState
   ): AuroraColorScheme
```			
Note that the second method always returns a non-`null` value, using the fallback mechanism discussed above to return the matching color scheme.
