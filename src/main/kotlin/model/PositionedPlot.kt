package model

data class PositionedPlot(
    val plot: Plot = Plot(),
    val position: PlotPosition = PlotPosition()
) {
    @PlotDslMarker
    class Builder(
            var plot: Plot.Builder = Plot.Builder(),
            var position: PlotPosition.Builder = PlotPosition.Builder()
    ) {
        fun build() = PositionedPlot(plot.build(), position.build())
    }
}