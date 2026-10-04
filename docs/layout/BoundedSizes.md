## Layout - bounded sizes

Bounded sizes are used to restrict a layout element's initial size. They add context related layout information that the element does not or cannot provide. To use a bounded size, you typically combine a component size (min, pref or default) with a constant size as lower or upper bound.

For example, a well designed command button honors the button's label and icon size, but also uses a minimum width. The latter increases the design consistency and often makes it easier to click on buttons with short labels. A button is used in different contexts: form, button bar, button stack, toolbar with icons, toolbar with texts, toolbar in large button mode, etc. The button implementation can only provide a single intrinsic size that often lacks the context. And so, the button lacks information about context-related layout requirements.

### String Representations

String representations for bounded sizes are used in the `FormLayout` constructors as well as in classes `ColumnSpec`, `RowSpec` and `Paddings`. The string encodings allow to set only a lower or upper bound, where the related Kotlin objects allow to set both bounds at the same time, which is needed rarely.

```
boundedSize ::=   MIN(constantSize;componentSize)
                | MAX(constantSize;componentSize)
```

**Note**: The string representation uses `max` to specify a minimum size, and `min` to specify a maximum size. Think of this as a function - like Kotlin's `max` and `min`. The max function provides a lower bound, the min function an upper bound.

### Examples

```kotlin
ColumnSpec.decode("max(50dlu;pref)")  // maximum of 50dlu and maximum size

RowSpec.decode("min(100dlu;pref)")    // minimum of 100dlu and maximum size

FormLayout(
  encodedColumnSpecs = "max(50dlu;pref), 4dlu, max(100dlu;min)",
  encodedRowSpecs    = "p, 3dlu, p, 3dlu, max(200dlu;p):grow")
```

### Next

Continue to [columns and rows](ColumnsAndRows.md).
