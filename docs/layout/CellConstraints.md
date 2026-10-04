## Layout - cell constraints

Each component managed by a `FormLayout` is associated with an instance of `CellConstraints` that specifies a component’s display area and alignment. The column and row origins are mandatory, but as we will see later, often a non-visual builder will automatically create the `CellConstraints` for you.

By default the column and row span is just 1, and the alignments are inherited from the related column and row. If possible, you should specify the alignment for the column and row, not for the component; this way you can reduce the amount of alignment specifications significantly.

`CellConstraints` objects can be constructed in different ways using a mixture of ints, objects and strings. It is recommended to specify the origin and span using ints and the alignment with strings - to increase the code readability.

### Spanning Multiple Columns/Rows

You can let components span multiple columns or rows, for example by using the `CellConstraints.xywh` function where you specify the x and y position of the leading cell and the width and height of the display area.

**Note:** these components do not affect the size of the spanned columns or rows, nevertheless, they may expand the whole container. See also the FAQ for details and how to handle this situation.

### Examples
1) Creation methods intended for use in hand written code

```kotlin
// second column, first row
CellConstraints.xy(col = 2, row = 1)
// aligned to end and bottom
CellConstraints.xywh(col = 2, row = 1, colSpan = 1, rowSpan = 1,
  encodedAlignments = "end, bottom")
// abbreviated
CellConstraints.xywh(col = 2, row = 1, colSpan = 1, rowSpan = 1,
  encodedAlignments = "e, b")
// spans 4 columns and 3 rows
CellConstraints.xywh(col = 2, row = 1, colSpan = 4, rowSpan = 3)
CellConstraints.xywh(col = 2, row = 1, colSpan = 4, rowSpan = 3,
  encodedAlignments = "end, bottom"))
CellConstraints.xywh(col = 2, row = 1, colSpan = 4, rowSpan = 3,
  encodedAlignments = "e, b")
```

2) Constructors intended for DSLs

```kotlin
CellConstraints(gridX = 2, gridY = 1)
CellConstraints(gridX = 2, gridY = 1, gridWidth = 4, gridHeight = 3)
CellConstraints(gridX = 2, gridY = 1, gridWidth = 4, gridHeight = 3,
  hAlign = CellConstraints.Alignment.End,
  vAlign = CellConstraints.Alignment.Bottom)
```

3) Constructors intended for building UIs from external formats (XML)

```kotlin
CellConstraints.fromConstraints("2, 1")
CellConstraints.fromConstraints("2, 1, e, b")
CellConstraints.fromConstraints("2, 1, 4, 3")
CellConstraints.fromConstraints("2, 1, 4, 3, e, b")
```

### Next

Continue to [groups](Groups.md).
