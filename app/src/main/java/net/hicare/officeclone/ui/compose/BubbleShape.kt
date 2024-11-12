package net.hicare.officeclone.ui.compose

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BubbleShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(x = 0f, y = size.height - 30)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f + 30, y = size.height)
        }

        return Outline.Generic(path)
    }
}