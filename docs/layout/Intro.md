## Layout - introduction

The `aurora-layout` module provides a port of Karsten Lentzsch's [FormLayout](https://www.jgoodies.com/freeware/libraries/forms/) from his JGoodies Forms library. The original implementation is for Swing, available under the BSD license.

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/layout/form-layout-demo.png" width="546"/>

`FormLayout` is a powerful, flexible and precise container that aligns components vertically and horizontally in a dynamic rectangular grid of cells, with each component occupying one or more cells.

To define a form layout you specify the form's columns, rows and optionally column groups and row groups. Everything that applies to columns applies to rows too - just with a different orientation. `FormLayout` uses the same API, algorithms and implementation for columns and rows.

If you are familiar with the original Swing-based `FormLayout` APIs, the main differences coming into Aurora's port of it to Compose are:

* Using a custom DSL with scope-based functions instead of the builder-based API
* Using `@` instead of `$` to refer to core and custom layout variables
* Using `start` and `end` instead of `left` and `right` for column alignment
* Using paddings instead of borders for better alignment with Compose terminology
* No `I15d` (internationalized) builder, leaving it to the app developer to decide between using Swing-based `ResourceBundle`s, Compose-based [string resources](https://kotlinlang.org/docs/multiplatform/compose-multiplatform-resources.html) or app-specific localization solutions


### Next

Continue to [dialog units](Dlu.md).
