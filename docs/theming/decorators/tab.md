## Aurora theming - tab decorators

The base interface for Aurora window decorators is `AuroraTabDecorator`. The tab decorator from the current skin is responsible for the visuals of tabs - paddings, background fill, highlight indicator of the selected tab, outlines, etc.

Aurora provides a default implementation of this interface in `DefaultTabDecorator`. Skins that target more custom visuals can either implement the `AuroraTabDecorator` interface from scratch, or extend the `DefaultTabDecorator` class.

In the image below, the top one shows the default visuals of tabs under the Gemini skin. The bottom one shows custom visuals of tabs:

* No outline for the selected tab
* The highlight indicator is a thick underline
* That needs a larger bottom padding for all tabs

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/theming/decorators/tabs.png" width="586"/>

### Management API

If you wish to use the window decorator of the current skin to provide additional custom painting in your application, call:

* `AuroraSkin.decorators` to retrieve the decorators associated with the current skin.
* `AuroraDecorators.tabDecorator` to retrieve the tab decorator of the current skin.
* Call the relevant method(s) on the `AuroraWindowDecorator`.
