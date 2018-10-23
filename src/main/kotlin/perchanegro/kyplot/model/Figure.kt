package perchanegro.kyplot.model

import perchanegro.kyplot.plotter.KyPlot
import tomasvolker.kyscript.readKyScript

@DslMarker
annotation class PlotDsl

data class Figure(
    val title: String = "",
    val plotList: List<Plot> = emptyList()
) {
    @PlotDsl
    class Builder(
        var title: String = "",
        var plotList: MutableList<Plot.Builder> = mutableListOf()
    ) {
        fun build() = Figure(title, plotList.map { it.build() })
    }
}

fun Figure.show() {
    readKyScript(
        KyPlot().also {
            it.show(this)
        }.build()
    )
}
