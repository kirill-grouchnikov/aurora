package org.pushingpixels.aurora.theming.decorator

import org.pushingpixels.aurora.theming.decorator.tab.AuroraTabDecorator
import org.pushingpixels.aurora.theming.decorator.tab.DefaultTabDecorator
import org.pushingpixels.aurora.theming.decorator.window.AuroraWindowDecorator
import org.pushingpixels.aurora.theming.decorator.window.DefaultWindowDecorator

data class AuroraDecorators(
    val tabDecorator: AuroraTabDecorator,
    val windowDecorator: AuroraWindowDecorator,
) {
    companion object {
        fun buildDefault(): AuroraDecorators {
            return AuroraDecorators(
                tabDecorator = DefaultTabDecorator(),
                windowDecorator = DefaultWindowDecorator()
            )
        }
    }
}