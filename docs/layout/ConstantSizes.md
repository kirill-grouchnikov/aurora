## Layout - constant sizes

Constant sizes are used to set the size of gaps and other fixed layout elements. They can also be used in bounded sizes as lower or upper bound.

### Units

Constant sizes are specified by a value plus unit that is one of: *Pixel*, *Points*, *Inches*, *Millimeter*, *Centimeter* and *Dialog Units*. In string representations the units are abbreviated as: *px*, *pt*, *in*, *mm*, *cm* and *dlu*.

### Constant Non-Pixel Sizes

Constant sizes are constant for a given dialog font, font size and screen resolution. Pixel sizes map to a fixed dimension in pixels - even if these parameters change. This is undesired in screen design, especially in multi-platform environments. Therefore you should favor non-pixel sizes over pixel sizes.

If you move an application from Windows to Mac or Linux, you want to retain the overall appearance and layout proportions. And if you just change your Windows desktop font settings, you want all layout elements grow appropriately: labels, fields, buttons, but also: gaps, borders, minimum sizes and dialog dimensions.

In core Android and core Compose there are two connected, but distinct units of measurement - `dp` for everything but text, and `sp` for text. The idea behind the `dp` unit is to abstract away the difference in the pixel density of the underlying hardware. The `sp` unit is identical to `dp` in the default configuration, but also scales based on the user's current choice of system text scaling.

The end result is that while text content scales up based on the system text scaling, other metrics do not retain proportions relative to the text - gaps, borders, dialog sizes, and custom minimum and maximum sizes. In addition, if you specify layout elements in pixel units, they will not retain those proportions as well.

For example, a well designed OK button shall have a minimum width; `75px` is appropriate for an 8pt Tahoma font on Windows with 96dpi. But on 120dpi and 10pt Arial, 75 pixels are perceived as quite narrow. [Dialog Units](Dlu.md) allow to specify such a size in a way that it grows and shrinks with the environment. For example, the MS layout style guide suggests a command button minimum width of `50dlu`, which maps to `75px` in the first context and to `100px` in the second.

`Points`, `Inches`, `Millimeters` and `Centimeters` are intended for sizes that shall grow with the screen resolution but that are independent of the font and font size.

### Unit Conversion

Class `Sizes` provides methods to convert non-pixel sizes to pixels. The actual mapping is performed by a customizable `Unit converter`.

### Logical Constant Sizes

Logical sizes can be used to specify a size that changes with the current platform or style guide. For example, if you want to specify a button's minimum width, you may want to use `50dlu` on Windows and `68px` on a Mac. Or you define the gap between two related text field rows as `3dlu` on Windows and `4px` on a Mac.

The `FormLayout` itself provides no means for such an abstraction. However, you can use logical sizes via the DSL APIs, factories and layout style. You can obtain the current `LayoutStyle` from that class via `current`. The result implementation returns style guide-specific logical sizes for frequently used gaps and minimum widths and heights; you can quickly access these constants via the `FormSpecs`. The `ButtonBar` DSL implementation uses logical sizes to honor style guide settings for button minimum width, and gaps between related and unrelated components.

### String Representations

It is recommended to specify column and row sizes in the `FormLayout` constructor using string representations. These strings will be accepted by the `FormLayout`, `ColumnSpec`, `RowSpec` and `Paddings` classes.

```
constantSize ::= integerUnit | doubleUnit
integerUnit  ::= PX | PT | DLU
doubleUnit   ::= IN | MM | CM
```

### Examples

```kotlin
ColumnSpec.decode("50dlu")
ColumnSpec.decode("75px")

RowSpec.decode("2in")
RowSpec.decode("100px")

FormLayout(
  encodedColumnSpecs = "100dlu, 4dlu, 200dlu",
  encodedRowSpecs    = " 14dlu, 3dlu,  14dlu")
```

### Next

Continue to [component sizes](ComponentSizes.md).
