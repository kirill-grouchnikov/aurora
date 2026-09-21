## Layout - dialog units

The concept of a **dialog unit** or **DLU** goes back to the days of [Win32 development](https://learn.microsoft.com/en-us/previous-versions/windows/desktop/bb226789(v=vs.85)) for Windows. From that page:

<blockquote>
A dialog unit is the device-independent measure to use for layout. One horizontal dialog unit is equal to one-fourth of the average character width for the current system font. One vertical dialog unit is equal to one-eighth of an average character height for the current system font.
</blockquote>

The two main units of measurement in Compose - `dp` and `sp` trace their origins to [Android](https://developer.android.com/training/multiscreen/screendensities).

The first one is a **device independent pixel** that abstracts away the pixel density of the underlying screen. A higher resolution screen will pack more pixels in the same amount of physical space compared to a lower resolution screen. One `dp` unit abstracts this difference and operates in the physical space - much like its spiritual predecessor from the world of physical print - the `DPI` unit or dots per inch.

The second one is **scalable pixel**. In the default configuration, it is the same size as `dp`. However, it resizes based on the user's preferred text size.

In core Android and core Compose, the `sp` unit is reserved *only* for text sizes, and *never* for layout sizes. In the world of FormLayout, the `dlu` unit is the logical equivalent of the `sp` unit, and is **the recommended** way of configuring *all* layout sizes.

To stay true to the origins of the `dlu` unit, FormLayout supports axis-specific resolution - for horizontal and vertical dialog units.

The available APIs are:

* `Int.dluX` and `Int.dluY` extension properties
* `Sizes.dluX` and `Sizes.dluY` conversion functions
* A number of predefined `Sizes.DluX` and `Sizes.DluY` constansts

### Next

Continue to a [more detailed sample walkthrough](Sample.md).
