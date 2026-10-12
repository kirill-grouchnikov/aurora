## Loading - overview

The `aurora-loading` module provides a port of Jakub Krehel's [Loading](https://github.com/jakubkrehel/loading). The original implementation is for TypeScript, available under the MIT license.

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/loading/loaders.png" width="752"/>

This module has 29 configurable loaders to use in your application. Each loader can be configured with:

* The incoming `modifier` for size, background, alignment, etc
* The `color` to be used on the visuals
* The `duration` of a single animation cycle in milliseconds

Some loaders accept additional parameters for more specific configuration.

Visit Jakub's [playground](https://loading.dev/) to see the original loaders in action.

**Note:** This module is a multiplatform library, and can be used for targets outside of desktop, such as web.
See [this online demo](https://pushing-pixels.org/demos/aurora-loaders-demo/index.html) that is using the Wasm/JS target of this module.
