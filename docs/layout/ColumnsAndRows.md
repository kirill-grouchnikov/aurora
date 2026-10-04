## Layout - columns and rows

Columns and rows are specified by three parts:
* a mandatory initial size
* an optional default alignment
* an optional resizing behavior

For example, you can specify that a column is start aligned, has an initial size of 50 Dialog Units and grows:

```
ColumnSpec.decode("start:50dlu:grow")
```

### Resizing Behavior

Columns and rows can grow if the layout container becomes larger than the preferred size. By default, columns and rows won't resize. The extra space is distributed over the columns and rows that have a resize weight larger than 0.0, where each column gets a space proportional to its weight.

### String Representations

String representations for column and row specifications can be used in the `FormLayout` constructors as well as in classes `ColumnSpec` and `RowSpec`.

```
columnSpec     ::= [columnAlignment:] size [:resizeBehavior]
rowSpec        ::= [rowAlignment   :] size [:resizeBehavior]
resizeBehavior ::= NONE | GROW | GROW() | G()
```

### Examples

```kotlin
ColumnSpec.decode("16px")
ColumnSpec.decode("start:16px")
ColumnSpec.decode("start:16px:grow")

FormLayout(
  encodedColumnSpecs = "end:max(50dlu;pref), 4dlu, max(100dlu;min)",
  encodedRowSpecs    = "p, 3dlu, p, 3dlu, fill:200dlu:grow")
```

### Next

Continue to [cell constraints](CellConstraints.md).
