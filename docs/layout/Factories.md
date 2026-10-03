## Layout - factories

`FormLayout` provides factories that can create frequently used layouts, panels and button bars. You should favor these factories over direct DSL configuration calls to increase the consistency of your layouts and in turn applications.

### FormSpecs

`FormSpecs` provides frequently used column and row specifications and can create standardized form layouts. For example `FormSpecs.GlupColSpec` is a `ColumnSpec` that represents a glue, i. e. a gap with initial size `0px` that grows.

### Sizes

`Sizes` provides a collection of predefined [dlu-based](Dlu.md) constant sizes. For example `DluX4` along the X axis and `DluY4` along the Y axis.

### Paddings

`Paddings` provides a collection of prepared and reusable `PaddingValues` instances, for example `Dlu4` and `Dialog`.


### Next

Continue to [alignments](Alignments.md).
