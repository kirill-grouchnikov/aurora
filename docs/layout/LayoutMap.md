## Layout - layout map

`LayoutMap` provides a hierarchical variable expansion useful to improve layout consistency, style guide compliance, and layout readability.

A `LayoutMap` maps variable names to layout expression strings. The `FormLayout`, `ColumnSpec`, and `RowSpec` parsers expand variables before an encoded layout specification is parsed and converted into `ColumnSpec` and `RowSpec` values. Variables start with the '@' character. The variable name can be wrapped by braces ('{' and '}'). For example, you can write

```kotlin
FormLayout("pref, @lcg, pref")
FormLayout("pref, @{lcg}, pref")
```

`LayoutMap`s build a chain; each `LayoutMap` has an optional parent map. The root is defined by `LayoutMap.getRoot`. Application-wide variables should be defined in the root `LayoutMap`. If you want to override application-wide variables locally, obtain a `LayoutMap` using `LayoutMap.getRoot` or with the constructor, configure it, and provide it as argument to the `FormLayout`, `ColumnSpec`, and `RowSpec` constructors/factory methods.

By default, the root `LayoutMap` provides the following associations:

| Variable Name | Abbreviations | Orientation | Description |
| :--- | :--- | :--- | :--- |
| label-component-gap | lcg, lcgap | both | gap between a label and the labeled component |
| related-gap | rg, rgap | both | gap between two related components |
| unrelated-gap | ug, ugap | both | gap between two unrelated components |
| button | b | horizontal | button column with minimum width |
| growing-button | gb | horizontal | growing button column |
| dialog-margin | dm, dmargin | both | margin for general dialogs |
| tabbed-dialog-margin | tdm, tdmargin | both | margin for tabbed dialogs |
| glue | glue | both | glue that grows and fills the space between other columns |
| line-gap | lg, lgap | vertical | gap between two lines |
| narrow-line-gap | nlg, nlgap | vertical | narrow gap between two lines |
| paragraph | pg, pgap | vertical | gap between two paragraphs / sections |

Examples:

```kotlin
// Predefined variables
FormLayout(
   modifier = ...,
   encodedColumnSpecs = "pref, @lcgap, pref, @rgap, pref",
   encodedRowSpecs = "p, @lgap, p, @lgap, p") { ... }

// Custom variables
LayoutMap.getRoot().columnPut("half", "39dlu");
LayoutMap.getRoot().columnPut("full", "80dlu");
LayoutMap.getRoot().rowPut("table", "fill:0:grow");
LayoutMap.getRoot().rowPut("table50", "fill:50dlu:grow");
FormLayout(
   modifier = ...,
   encodedColumnSpecs = "pref, @lcgap, @half, 2dlu, @half",
   encodedRowSpecs = "p, @lcgap, @table50") { ... }
FormLayout(
   modifier = ...,
   encodedColumnSpecs = "pref, @lcgap, @full",
   encodedRowSpecs = "p, @lcgap, @table50") { ... }

// Nested variables
LayoutMap.getRoot().columnPut("c-gap-c", "@half, 2dlu, @half");
FormLayout(
   encodedColumnSpecs = "pref, @lcgap, @{c-gap-c}", // -> "pref, @lcgap, @half, 2dlu, @half",
   encodedRowSpecs = "p, @lcgap, @table") { ... }

`LayoutMap` holds two internal maps that associate key strings with expression strings for the columns and rows respectively. Null values are not allowed.

### Tips
* You should carefully override predefined variables, because variable users may expect that these don't change
* Set custom variables in the root `LayoutMap`
* Avoid aliases for custom variables

### Next

Continue to [cortex](Cortex.md).
