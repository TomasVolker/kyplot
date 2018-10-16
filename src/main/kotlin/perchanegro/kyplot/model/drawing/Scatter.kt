package perchanegro.kyplot.model.drawing

import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.PlotDsl

data class Scatter(
    val x: Iterable<Number>,
    val y: Iterable<Number>,
    override val label: String = "",
    val markerStyle: MarkerStyle = MarkerStyle()
): Drawing {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var y: Iterable<Number> = emptyList(),
        var label: String = "",
        var markerStyle: MarkerStyle.Builder = MarkerStyle.Builder()
    ): Drawing.Builder {

        override fun build() =
            Scatter(
                x = x,
                y = y,
                label = label,
                markerStyle = markerStyle.build()
            )

    }

}