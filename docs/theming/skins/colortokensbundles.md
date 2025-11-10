## Aurora theming - color tokens bundles

A **color tokens bundle** is a set of information that allows painting controls in a specific decoration area. The `ContainerColorTokensBundle` contains all the APIs officially supported by Aurora color tokens bundles.

### Basics

The `ComponentState` is the base class for core and custom [component states](componentstates.md). A color tokens bundle is created with three major color tokens - for active, muted and neutral containers. If no state-specific color tokens are registered on the color tokens bundle, the major color tokens are used for all component states. A color tokens bundle is created with the following constructor:

```kotlin
/**
 * Creates a new color tokens bundle.
 *
 * @param activeContainerTokens Active color tokens of this bundle.
 * @param mutedContainerTokens Muted color tokens of this bundle.
 * @param neutralContainerTokens Neutral color tokens of this bundle.
 * @param isSystemDark `true` if the system tokens should be created in dark mode.
 */
constructor(
    activeContainerTokens: ContainerColorTokens,
    mutedContainerTokens: ContainerColorTokens,
    neutralContainerTokens: ContainerColorTokens,
    isSystemDark: Boolean
)
```

Here is a screenshot of three buttons (active, enabled and disabled) under the core [Cerulean skin](light-skins.md#cerulean):

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/states/component-states-basic.png" width="306" height="138" />

Here is the relevant code snippet from the definition of this skin:

```kotlin
val ceruleanDefaultBundle = ContainerColorTokensBundle(
    activeContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFD2E0EDu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    mutedContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFECECEDu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    neutralContainerTokens = getContainerTokens(
        seed = Hct.fromInt(0xFFFBFCFCu.toInt()),
        containerConfiguration = ContainerConfiguration.defaultLight()),
    isSystemDark = false)
```

### More states

The following API allows specifying custom color tokens for a specific component state:

```kotlin
/**
 * Registers the container color tokens to be used for controls in specified active states.
 * For example, if light orange color tokens are to be used for rollover selected and rollover
 * controls in highlights, the parameters would be:
 *
 *  * `stateContainerTokens`=light orange color tokens
 *  * `associationKind`=[ContainerColorTokensAssociationKind.Highlight]
 *  * `states`=[ComponentState.RolloverSelected], [ComponentState.RolloverUnselected]
 *
 * @param colorTokens  Container color tokens for the specified active component states.
 * @param associationKind Color tokens association kind that specifies the visual areas
 *      of controls to be painted with this color tokens.
 * @param activeStates Component states that further restrict the usage of the
 *      specified color tokens.
 */
fun registerActiveContainerTokens(
    colorTokens: ContainerColorTokens,
    associationKind: ContainerColorTokensAssociationKind = ContainerColorTokensAssociationKind.Default,
    vararg activeStates: ComponentState
)
```      

For example, you can use this API if you want to visualy distinguish between buttons in rollover state and rollover selected state. Here is a screenshot of buttons in different states under the emulated Office Silver 2007 skin:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/states/component-states-extended.png" width="306" height="286" />

Here is the relevant code snippet:

```kotlin
officeSilverDefaultBundle.registerActiveContainerTokens(
    colorTokens = rolloverContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Default,
    ComponentState.RolloverUnselected)
officeSilverDefaultBundle.registerActiveContainerTokens(
    colorTokens = rolloverSelectedContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Default,
    ComponentState.RolloverSelected)
officeSilverDefaultBundle.registerActiveContainerTokens(
    colorTokens = selectedContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Default,
    ComponentState.Selected)
officeSilverDefaultBundle.registerActiveContainerTokens(
    colorTokens = pressedContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Default,
    ComponentState.PressedUnselected)
officeSilverDefaultBundle.registerActiveContainerTokens(
    colorTokens = pressedSelectedContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Default,
    ComponentState.PressedSelected)
```

Controls in disabled states are drawn using the following alpha tokens:

* `containerSurfaceDisabledAlpha`
* `containerOutlineDisabledAlpha`
* `onContainerDisabledAlpha`

### Highlights

The [highlight painters](../painters/highlight.md) are used to paint highlight areas on `AuroraBoxWithHighlights` composables. such components as lists, tables, table headers, trees and menus. Use the following API to specify custom highlight color tokens for specific component states:

```kotlin
/**
 * Registers the container color tokens to be used for controls in specified active states.
 * For example, if light orange color tokens are to be used for rollover selected and rollover
 * controls in highlights, the parameters would be:
 *
 *  * `stateContainerTokens`=light orange color tokens
 *  * `associationKind`=[ContainerColorTokensAssociationKind.Highlight]
 *  * `states`=[ComponentState.RolloverSelected], [ComponentState.RolloverUnselected]
 *
 * @param colorTokens  Container color tokens for the specified active component states.
 * @param associationKind Color tokens association kind that specifies the visual areas
 *      of controls to be painted with this color tokens.
 * @param activeStates Component states that further restrict the usage of the
 *      specified color tokens.
 */
fun registerActiveContainerTokens(
    colorTokens: ContainerColorTokens,
    associationKind: ContainerColorTokensAssociationKind = ContainerColorTokensAssociationKind.Default,
    vararg activeStates: ComponentState
)
```

Here is an example of using these APIs to set custom highlight color tokens in the [Mariner skin](light-skins.md#mariner):

```kotlin
marinerDefaultBundle.registerActiveContainerTokens(
    colorTokens = marinerSelectedHighlightContainerTokens,
    associationKind = ContainerColorTokensAssociationKind.Highlight,
    ComponentState.Selected)
```        

This API can also be used for configuring custom color tokens for other association kinds, like the `Mark` in the [Magellan skin](dark-skins.md#magellan):

```kotlin
magellanDefaultBundle.registerActiveContainerTokens(magellanGreenContainerTokens,
    ContainerColorTokensAssociationKind.Mark,
    ComponentState.Selected)
magellanDefaultBundle.registerActiveContainerTokens(magellanGreenRolloverContainerTokens,
    ContainerColorTokensAssociationKind.Mark,
    ComponentState.RolloverSelected, ComponentState.RolloverUnselected)
magellanDefaultBundle.registerActiveContainerTokens(magellanPressedContainerTokens,
    ContainerColorTokensAssociationKind.Mark,
    ComponentState.PressedUnselected, ComponentState.PressedSelected)
```
