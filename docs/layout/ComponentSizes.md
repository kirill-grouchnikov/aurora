## Layout - component sizes

The component sizes *min*, *pref*, *default* are used to set column and row sizes that reflect the minimum or maximum sizes of the contained components.

If you specify a column size as *min* sized, `FormLayout` will ask all components in that column for their minimum width and chooses the largest width as the column width. The same applies to rows and the pref component size.

The *default* size aims to give a column the width of the largest maximum width. If container space is scarce, it shrinks the column down to the largest minimum width.

### String Representations

It is recommended to specify column and row sizes in the `FormLayout` constructor using string representations. These strings will be accepted by the `FormLayout`, `ColumnSpec`, `RowSpec` and `Paddings` classes.

```
componentSize ::= MIN | PREF | DEFAULT | M | P | D
```

### Examples
```kotlin
ColumnSpec.decode("min")
ColumnSpec.decode("m")
ColumnSpec.decode("default")
ColumnSpec.decode("d")
ColumnSpec.decode("pref")
ColumnSpec.decode("p")

RowSpec.decode("min")
RowSpec.decode("m")

FormLayout(
  encodedColumnSpecs = "start:pref, 4dlu, fill:default",
  encodedRowSpecs    = "p, 3dlu, p, 3dlu, p")
```

### Next

Continue to [bounded sizes](BoundedSizes.md).
