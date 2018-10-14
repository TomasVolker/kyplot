package perchanegro.kyplot.model

import perchanegro.kyplot.model.drawing.Drawing

data class Plot(
    val title: String = "",
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val drawingList: List<Drawing> = emptyList(),
    val position: PlotPosition = PlotPosition(),
    val grid: Grid = Grid()
) {

    @PlotDslMarker
    class Builder(
        var title: String = "",
        var xAxis: Axis = Axis(),
        var yAxis: Axis = Axis(),
        var drawingList: MutableList<Drawing.Builder> = mutableListOf(),
        var position: PlotPosition.Builder = PlotPosition.Builder(),
        var grid: Grid.Builder = Grid.Builder()
    ) {

        fun build() = Plot(
            title = title,
            xAxis = xAxis,
            yAxis = yAxis,
            drawingList = drawingList.map { it.build() },
            position = position.build(),
            grid = grid.build()
        )

    }

    data class Axis(
        val label: String = "",
        val limits: Limits = Limits.Auto,
        val tickPositions: TickPositions = TickPositions.Auto
    ) {

        sealed class Limits {
            object Auto: Limits()
            data class Explicit(val min: Number, val max: Number): Limits()
        }

        sealed class TickPositions {
            object Auto: TickPositions()
            data class Explicit(val tickList: List<Tick>): TickPositions()
        }

        data class Tick(val position: Number, val label: String = "")

        @PlotDsl
        class Builder(
            var label: String = "",
            var limits: Limits = Limits.Auto,
            var tickPositions: TickPositions = TickPositions.Auto
        ) {

            infix fun Number.upTo(upper: Number) = Limits.Explicit(this, upper)

            fun build() = Axis(
                label = label,
                limits = limits,
                tickPositions = tickPositions
            )
        }
    }


    data class Grid(
        val lineStyle: LineStyle = LineStyle(),
        val visible: Boolean = true
    ) {

        @PlotDslMarker
        class Builder(
            var lineStyle: LineStyle.Builder = LineStyle.Builder(),
            var visible: Boolean = true
        ) {

            fun build() =
                Grid(
                    lineStyle = lineStyle.build(),
                    visible = visible
                )

        }

    }

}