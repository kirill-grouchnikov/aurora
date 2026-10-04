## Layout - groups

Column and row groups are used to specify that a set of columns or rows will get the same width or height. This is an essential feature to implement symmetric, and more generally, balanced design.

### Example

In the following example it is ensured that columns 2 and 4 get the same width, rows 1 and 4 get the same height as well as rows 2 and 3:

```kotlin
FormLayout(
  encodedColumnSpecs = "p, d, p, d",
  encodedRowSpecs = "p, p, p, p",
  colGroupIndices = arrayOf(intArrayOf(2, 4), intArrayOf(3, 7)),
  rowGroupIndices = arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))
) {
  ...
}
```

### Next

Continue to [layout map](LayoutMap.md).
