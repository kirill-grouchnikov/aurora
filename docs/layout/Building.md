## Layout - building

The API structure of `Layout` blocks has been designed to talk to a container, not a human. `FormLayout` framework separates concerns: the layout task from the layout specification and the panel building process. Therefore it provides a set of non-visual DSL-based APIs that assist you in building panels and that can shield you from details of the layout manager.

When constructing a panel you talk to a DSL-based scope which in turn talks to the layout manager. This leads to a smaller layout manager API and to more flexibility. The builders that ship with the core Aurora distribution can create frequently used components, provide a cursor to keep track of the grid location where the next component will be added, and they can assist you in style guide compliance.

### Plain Building

You can use the pure `FormLayout` without any DSL-based wrapper APIs. It is good style to use a DSL-based API, because you often write less code and so increase the code readability.

### Panel DSL

The `Panel` is a general purpose DSL for many types of Compose panels. It is recommend for building panels that cannot be build well with a specialized DSL like the `ButtonBar` or `DefaultForm`. Typically you specify the columns and rows before you add the components. If the panel has many rows, consider using a row variable to address the current row.

### Row Counter

If your layout has rows, hard-coded row numbers become difficult to maintain - at least if you often add and remove rows. You can introduce a variable to track the current row. Since this style makes the code harder to understand, you should use row variables judiscously.

### Dynamic Rows

Almost always columns should be defined statically; they describe the essence of form-oriented grids. In contrast, forms have often quite simple row structures: just a sequence of component rows and gap rows like `pref, 3dlu, pref, 3dlu, pref, ...` In this case you may consider creating the rows dynamically. Use this style judiciously, because it makes the layout harder to understand for other readers.

### DefaultForm DSL

The `DefaultForm` DSL builds consistent forms quickly. It combines frequently used panel building steps: add a new row, add a label, proceed to the next data column, then add a component. Typically you specify the columns statically and use the scope's `append` functions to create the rows on-the-fly. This saves a lot of code and works well with a large set of editors and viewers. However, use this DSL only if appropriate; your code should not be cluttered by DSL commands.

### Custom Rows and DefaultForm

All DSL wrappers allow to append rows dynamically. If you create rows dynamically with the `DefaultForm` there are basically three ways to add custom rows. Such rows are required for components that grow or that are larger than the default rows. You can add: 1) a single custom row (plus gap before), 2) a standard row plus custom row, or 3) multiple standard rows. These approaches differ in the way, labels and components will be aligned, and in the flexibility available for the alignment in the custom row.

### Indent Column and DefaultForm

It is good design practice to indicate panel sections with a leading indent for all component rows, not the separators. The `DefaultForm` provides the `leadingColumnOffset` field that allows to specify that the components added by the builder should leave out the first offset columns - which is often 1.

### Debugging a Panel Layout

`FormLayout` and all core DSL wrappers support configuring an optional `FormLayoutDebugConfiguration` that can help you debug a panel layout implementation. The debug panel paints lines to mark the columns and rows. The debug utils print detailed information about the column and row specifications, column and row groups, component constraints and the grid bounds. The printed information 'unfold' all implicit specifications for column and row specifications, and default alignments that have been applied to individual cells.

### ButtonBar DSL

The `ButtonBar` DSL builds consistent button bars that comply with style guides. It ensures minimum widths, uses logical gaps for related and unrelated buttons, and allows to add gridded and ungridded buttons - with default or narrow margins.

### ButtonStack DSL

The `ButtonStack` DSL builds consistent button stacks that comply with style guides. It ensures minimum widths, uses logical gaps for related and unrelated buttons, and allows to add gridded and ungridded buttons - with default or narrow margins.

### Next

Continue to [factories](Factories.md).
