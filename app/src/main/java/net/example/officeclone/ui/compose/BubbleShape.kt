package net.example.officeclone.ui.compose

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

enum class MessageDirection {
    LEFT,
    RIGHT
}

class BubbleShape(
    private val direction: MessageDirection
) : Shape {

    private val offset = 40

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = if (direction == MessageDirection.RIGHT) {
            Path().apply {
                moveTo(x = 0f, y = size.height - offset)
                lineTo(x = 0f, y = size.height)
                lineTo(x = 0f + offset, y = size.height)
            }
        } else {
            Path().apply {
                moveTo(x = 0f, y = size.height - offset)
                lineTo(x = 0f, y = size.height)
                lineTo(x = 0f - offset, y = size.height)
            }
        }

        return Outline.Generic(path)
    }
}