package perchanegro.kyplot.model.drawing

import perchanegro.kyplot.model.BarAlignment
import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.PlotDsl

data class Bar(
    val x: Iterable<Number>,
    val heights: Iterable<Number>,
    val width: Double,
    val alignment: BarAlignment,
    val color: Color,
    override val label: String = ""
): Drawing {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var heights: Iterable<Number> = emptyList(),
        var width: Double = 0.8,
        var alignment: BarAlignment = BarAlignment.CENTER,
        var color: Color = Color.Auto,
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            Bar(
                x = x,
                heights = heights,
                width = width,
                alignment = alignment,
                color = color,
                label = label
            )

    }

}