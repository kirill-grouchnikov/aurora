## Aurora theming - color scheme bundles

A **color scheme bundle** is a set of information that allows painting controls in a specific decoration area. The `AuroraColorSchemeBundle` contains all the APIs supported by Aurora color scheme bundles.

### Basics

The `ComponentState` is the base class for core and custom [component states](componentstates.md). A color scheme bundle is created with three major color schemes - for active, enabled and disabled controls. If no state-specific color schemes are registered on the color scheme bundle, the major color schemes are used for all component states. A color scheme bundle is created with the following constructor:

```kotlin
  /**
   * Creates a new color scheme bundle.
   *
   * @param activeColorScheme
   *            The active color scheme of this bundle.
   * @param enabledColorScheme
   *            The enabled color scheme of this bundle.
   * @param disabledColorScheme
   *            The disabled color scheme of this bundle.
   */
   class AuroraColorSchemeBundle(
     private val activeColorScheme: AuroraColorScheme,
     private val enabledColorScheme: AuroraColorScheme,
     private val disabledColorScheme: AuroraColorScheme
   )
```

Here is a screenshot of three buttons (active, default and disabled) under the core [Business Black Steel skin](toneddown.md#business-black-steel):

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/states/component-states-basic.png" width="306" height="138" />

Here is the relevant code snippet from the definition of this skin:

```kotlin
val businessSchemes = getColorSchemes(
    AuroraSkin::class.java.getResourceAsStream(
        "/org/pushingpixels/aurora/theming/business.colorschemes"
    )
)

val activeScheme = businessSchemes["Business Black Steel Active"]
val enabledScheme = businessSchemes["Business Black Steel Enabled"]
val disabledScheme = businessSchemes["Business Black Steel Disabled"]

// the default color scheme bundle
val defaultSchemeBundle = AuroraColorSchemeBundle(
    activeScheme, enabledScheme, disabledScheme)
```

### More states

The following API allows specifying a custom color scheme for a specific component state:

```kotlin
  /**
   * Registers a color scheme for the specific component state.
   *
   * @param stateColorScheme
   *     Color scheme for the specified component state.
   * @param states
   *     Component states.
   */
   fun registerColorScheme(
         scheme: AuroraColorScheme,
         associationKind: ColorSchemeAssociationKind = ColorSchemeAssociationKind.Fill,
         vararg states: ComponentState
   )
```      

For example, you can use this API if you want to visualy distinguish between buttons in rollover state and rollover selected state. Here is a screenshot of buttons in different states under the emulated Office Silver 2007 skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/states/component-states-extended.png" width="306" height="286" />

Here is the relevant code snippet:

```kotlin
bundle.registerColorScheme(
    rolloverScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.RolloverUnselected
)
bundle.registerColorScheme(
    rolloverSelectedScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.RolloverSelected
)
bundle.registerColorScheme(
    selectedScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.Selected
)
bundle.registerColorScheme(
    pressedScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.PressedUnselected
)
bundle.registerColorScheme(
    pressedSelectedScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.PressedSelected
)
```

It is possible to specify a custom alpha value for controls in some states. This can be useful if you want to use the same color scheme for both default and disabled states, and have disabled controls painted with a custom alpha translucency (making them blend with the background). Use the following API:

```kotlin
/**
 * Registers an alpha channel value for the specific component states.
 *
 * @param alpha  Alpha channel value.
 * @param states Component states.
 */
fun registerAlpha(alpha: Float, vararg states: ComponentState)
```      
Here is sample code from the [Autumn skin](toneddown.md#autumn) that uses the same color scheme for default and disabled states, setting alpha channel to 60% for the disabled states:

```kotlin
val schemes = getColorSchemes(
    AuroraSkin::class.java.getResourceAsStream(
        "/org/pushingpixels/aurora/theming/autumn.colorschemes"
    )
)

val activeScheme = schemes["Autumn Active"]
val enabledScheme = schemes["Autumn Enabled"]
val disabledScheme = enabledScheme

val defaultSchemeBundle = AuroraColorSchemeBundle(
    activeScheme, enabledScheme, disabledScheme
)

defaultSchemeBundle.registerAlpha(0.6f, ComponentState.DisabledUnselected, ComponentState.DisabledSelected)
defaultSchemeBundle.registerColorScheme(
    disabledScheme, ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledUnselected
)
defaultSchemeBundle.registerColorScheme(
    activeScheme,
    ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledSelected
)
```

### Highlights

The [highlight painters](../painters/highlight.md) are used to paint highlight areas on `AuroraBoxWithHighlights` composables. Use the following APIs to specify custom highlight color schemes for specific component states, along with custom alpha values:

```kotlin
  /**
   * Registers a highlight color scheme for the specific component state if
   * the component state is not <code>null</code>, or a global highlight color
   * scheme otherwise.
   *
   * @param stateHighlightScheme
   *     Highlight color scheme for the specified component state.
   * @param states
   *     Component states. If <code>null</code>, the specified color scheme
   *     will be applied for all states left unspecified.
   */
  fun registerHighlightColorScheme(stateHighlightScheme: AuroraColorScheme, vararg states: ComponentState)

  /**
   * Registers a highlight alpha channel value for the specific component states.
   *
   * @param alpha  Highlight alpha channel value.
   * @param states Component states.
   */
  fun registerHighlightAlpha(alpha: Float, vararg states: ComponentState)
```

Here is an example of using these APIs to set state-specific alpha values for highlights in the [Business Black Steel skin](toneddown.md#business-black-steel):

```kotlin
val headerSchemeBundle = AuroraColorSchemeBundle(
    activeHeaderScheme, enabledHeaderScheme, disabledScheme
)
headerSchemeBundle.registerAlpha(
    0.5f,
    ComponentState.DisabledUnselected, ComponentState.DisabledSelected
)
headerSchemeBundle.registerColorScheme(
    enabledHeaderScheme,
    ColorSchemeAssociationKind.Fill,
    ComponentState.DisabledUnselected, ComponentState.DisabledSelected
)
headerSchemeBundle.registerHighlightAlpha(0.6f, ComponentState.RolloverUnselected)
headerSchemeBundle.registerHighlightAlpha(0.8f, ComponentState.Selected)
headerSchemeBundle.registerHighlightAlpha(0.95f, ComponentState.RolloverSelected)
headerSchemeBundle.registerHighlightColorScheme(
    activeScheme, ComponentState.RolloverUnselected,
    ComponentState.Selected, ComponentState.RolloverSelected
)
```        

### Finer grained control

As described in the [color scheme association kind documentation](colorschemeassociationkinds.md), Aurora composables have different visual areas. Even such a simple example as a checkbox icon has three different visual areas: inner fill, border and the "V" mark:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/color-scheme-association-kinds.png" width="96" height="96"/>

Use the following API to specify custom color schemes to be used for specific visual areas under specific component states:

```kotlin
  /**
   * Registers the color scheme to be used for the specified visual area of
   * controls under the specified states. For example, if the light orange
   * scheme has to be used for gradient fill of rollover selected and rollover
   * controls, the parameters would be:
   *
   * <ul>
   * <li><code>scheme</code>=light orange scheme</li>
   * <li>
   * <code>associationKind</code>=[ColorSchemeAssociationKind.Fill]</li>
   * <li>
   * <code>states</code>=[ComponentState.RolloverSelected], [ComponentState.RolloverUnSelected]
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

Here is an example of using this API in the [Gemini skin](toneddown.md#gemini) skin to specify a custom color scheme to be used on borders of controls in the disabled states:

```kotlin
defaultSchemeBundle.registerColorScheme(
    lightGrayBorderScheme,
    ColorSchemeAssociationKind.Border,
    ComponentState.DisabledSelected, ComponentState.DisabledUnselected
)
```

### Derived bundles

It is possible to create a derived color scheme bundle. Note that a color scheme bundle is a delicate collection of different color schemes and alpha values carefully chosen to work together in providing visually appealing appearance and consistent animation sequences. In some cases, creating a derived color scheme bundle will result in poor visuals.

You can use the following API to create a derived color scheme bundle:

```kotlin
  /**
   * Creates a new color scheme bundle that has the same settings as this
   * color scheme bundle with the addition of applying the specified color
   * scheme transformation on all the relevant color schemes
   *
   * @param transform
   *     Color scheme transformation.
   * @return The new color scheme bundle.
   */
  fun transform(transform: (AuroraColorScheme) -> AuroraColorScheme): AuroraColorSchemeBundle
```
