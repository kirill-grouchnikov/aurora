## Layout - basics

### Alignments
Forms allows to align component horizontally to the *start*, *center*, *end* and vertically to the *top*, *center*, *bottom*. And it can *fill* the space available in the cell or cells.

### Basic Sizes

Forms provides a rich set of sizes: 75px is a *constant size* of 75 pixels, pref is a *component size*. The latter describes that a column or row gets the maximum of the preferred sizes of all components in that column or row. For columns it measures the width aspect of the size, rows measure the height.

### Growing

If the container is larger than the preferred container size you have extra space. You can specify how to distribute this extra space: let a single column or row grow, or multipe columns/rows. In the latter case you can define resize *weights* to give different columns/rows different portions of the extra space.

### Span

A component can span a single or multiple columns and rows. You specify its display area by a `CellConstraints` object that accepts a column width and row height or matching modifiers. By default the column and row span is 1, so the a component is in a single cell.

**Note**: components that span multiple columns or rows do not affect the size of these columns/rows. However, they may expand the container.

### Grouping

An essential layout feature for symmetric, and more generally, balanced design is to give columns and rows the same width/height. Forms allows to specify groups of columns and rows that shall get the same size. You can set a 2-dimensional array of column and row indices that describes a set of such groups.

### Units

The constant sizes used with the Forms accept different units: *Pixel*, *Points*, *Millimeter*, *Centimeter*, *Inches*, *Dialog Units*. For the final layout all units (except Pixels) must be mapped to pixels. Points, Millimeters, Centimeters and Inches honor the screen resolution; Dialog Units [scale](Dlu.md) with the font, font size too. Generally you should avoid Pixel sizes, because they do not retain proportions if the resolution or dialog font changes. Most constant sizes use Dialog Units; they help you retain proportions for a wide range of application environments. This is especially important in multi-platform applications. The other resolution-independent sizes are useful only if you want to specify a size that shall grow and shrink with the resolution but not the font size.

### Cell Alignments

Forms applies column and row alignments to all contained cells to reduce the amount of code. For example, you can specify that all labels in a column shall be right-aligned with a single command. In addition it uses default alignments for columns and rows that are used if you do not specify an alignment explicitly. The column default is *fill*, row default is *center*.

### Component Sizes
An essential layout manager feature is to adjust column and row sizes to the component minimum and preferred widths and heights. Forms provides three ways to describe that a column or row shall reflect these component sizes. A *Min* column size specifies that the column width will be the maximum of all minimum width of the components in that column. The same applies to rows, just with a different orientation, and to *Pref* with the preferred width/height. The *Default* size is like *Pref* but shrinks down to *Min* if the container space is scarce.

### Bounded Sizes

Bounded sizes allow to specify a lower and/or upper bound for a size. This can be used to make layouts 'stable' over different panels. For example if you have a set of tabs and label columns in all tabs, you can specify that all label columns shall have a minimum width of 50dlu. If you switch the tabs, the panels don't 'jump' back and forth but seem to be located at the same place.

### Next

Continue to [building](Building.md).
