## Layout - alignments

Column alignments are: *start*, *center*, *end*, *fill*.
And row alignments are: *top*, *center*, *bottom*, *fill*.

The *fill* alignment expands a component to span its display area, so that the component's left-hand side is left-aligned, and the right-hand side is right-aligned. The same applies to top/bottom for rows.

### Applying Defaults

`FormLayout` aims to minimize the effort to specify alignments. Therefore it 1) tries to reuse alignments, 2) provide good defaults for form oriented layouts.

1. The column and row alignments are applied to all components that are located in a single cell of that column/row. A component can override its column/row alignment by setting an individual alignment via a `CellConstraints` object or a matching modifier.
2. By default, the column alignment is set to `fill`. The implicit alignment for rows is `center`. And so, if you don't specify column and row alignments, your components will be horizontally filled and vertically centered. This is a good default for most text fields, combo boxes, buttons and it works fine with labels too.

### Alignment Constants

The classes `ColumnSpec` and `RowSpec` provide constant values for the alignment values mentioned above.

### String Representations

It is recommended to specify column and row alignments in the `FormLayout` constructor using string representations. These strings will be accepted by the `FormLayout`, `ColumnSpec`, `RowSpec` and `CellConstraints` classes and by many DSL scope functions.

```
columnAlignment ::= START | CENTER | END  | FILL | S | C | E | F
rowAlignment    ::= TOP  | CENTER | BOTTOM | FILL | T | C | B | F
```

### Examples

```kotlin
ColumnSpec.Start
ColumnSpec.Fill
ColumnSpec("start")
ColumnSpec("f")

RowSpec.Bottom
RowSpec.Center
RowSpec("bottom")
RowSpec("c")

FormLayout(
  encodedColumnSpecs = "start:pref, 4dlu, fill:pref",
  encodedRowSpecs    = "top:pref, 3dlu, center:pref")

CellConstraints(gridX = 2, gridY = 3,
  hAlign = ColumnSpec.Start, vAlign = RowSpec.Top)
CellConstraints.xywh(col = 2, row = 3,
  colSpan = 1, rowSpan = 1,
  encodedAlignment = "start, top")
CellConstraints.xywh(col = 2, row = 3,
  colSpan = 1, rowSpan = 1,
  encodedAlignment = "s, t")
```

### Next

Continue to [constant sizes](ConstantSizes.md).
