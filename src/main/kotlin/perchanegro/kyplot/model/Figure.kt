package perchanegro.kyplot.model

import perchanegro.kyplot.plotter.KyPlot

@DslMarker
annotation class PlotDslMarker

data class Figure(
    val title: String = "",
    val plotList: List<Plot> = emptyList()
) {
    @PlotDslMarker
    class Builder(
        var title: String = "",
        var plotList: MutableList<Plot.Builder> = mutableListOf()
    ) {
        fun build() = Figure(title, plotList.map { it.build() })
    }
}

fun Figure.show() {
    KyPlot().show(this)
}
