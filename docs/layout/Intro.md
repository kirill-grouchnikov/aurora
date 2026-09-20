## Layout - introduction

The `aurora-layout` module provides a port of Karsten Lentzsch's [FormLayout](https://www.jgoodies.com/freeware/libraries/forms/) from his JGoodies Forms library. The original implementation is for Swing, available under the BSD license.

If you are familiar with `FormLayout`, the main differences coming into Aurora's port of it to Compose are:

* Using a custom DSL with scope-based functions instead of the builder-based API
* Using `@` instead of `$` to refer to core and custom layout variables
* Using `start` and `end` instead of `left` and `right` for column alignment
* Using paddings instead of borders for better alignment with Compose terminology
* No `I15d` (internationalized) builder to provide a more flexible support for Swing-based `ResourceBundle`s, Compose-based [string resources](https://kotlinlang.org/docs/multiplatform/compose-multiplatform-resources.html) and app-specific localization solutions


### Next

Continue to [dialog units](Dlu.md).
