package Model

sealed class Drawing {
}

data class Line(
    val x: Iterable<Number>,
    val y: Iterable<Number>
): Drawing()

data class Plot(
    val title: String = "",
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val drawingList: List<Drawing> = emptyList()) {

    data class Axis(
        val label: String = "",
        val limits: Limits = Limits.Auto
    ) {

        sealed class Limits {
            object Auto: Limits()
            data class Explicit(val min: Number, val max: Number): Limits()
        }
    }

    data class Grid(val oneThing: Any) {
        //TODO
    }
}

data class PlotPosition (
    val gridWidth: Int = 1,
    val gridHeight: Int = 1,
    val row: Int = 0,
    val column: Int = 0
)

data class PositionedPlot(
    val plot: Plot = Plot(),
    val position: PlotPosition = PlotPosition()
)

data class Figure(
    val title: String = "",
    val plotList: List<PositionedPlot> = emptyList()
)
