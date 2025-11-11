## Aurora theming - painters overview

An Aurora **painter** encapsulates common painting logic. For example, in Aurora, borders of buttons, comboboxes, progress bars and more are painted with the same look. Instead of replicating this code across different composable, it is extracted into a common painter. In addition to code reuse, it:

* Makes it easier to tweak an existing painting sequence.
* Lets applications to specify a custom (branding) painter which is applied to all relevant composables.
* Lets applications and third-party developers to provide consistent appearance of custom components without locking themselves to low-level implementation details of Aurora.

Aurora uses four types of painters which are used on different types of controls and window areas. The core library bundles a number of painter implementations which are available as officially supported APIs. Interested applications can also create custom implementation of the relevant painter interfaces and combine them together in a skin that creates a unique visual appearance based on the specific design requirements.

### Painter types

Aurora uses the following painter types:

* [Surface painters](surface.md)
* [Outline painters](outline.md)
* [Highlight painters](highlight.md)
* [Decoration painters](decoration.md)
* [Overlay painters](overlay.md)

The vast majority of Aurora visuals are painted by using these painters. The links above provide more technical information on how to specify custom painters and how to use them to paint custom components and window areas. Applications that wish to provide consistent appearance under different Aurora skins are strongly encouraged to use the published painter APIs.

The Aurora painting layer provides a convenient API for the applications that wish to paint custom composables in a way that is consistent with other Aurora visuals.
