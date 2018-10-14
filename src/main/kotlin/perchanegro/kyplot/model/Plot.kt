package perchanegro.kyplot.model

import perchanegro.kyplot.model.drawing.Drawing

data class Plot(
    val title: String = "",
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val drawingList: List<Drawing> = emptyList(),
    val position: PlotPosition = PlotPosition()
) {

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

    data class Grid(val oneThing: Any) {
        //TODO
    }

    @PlotDsl
    class Builder(
        var title: String = "",
        var xAxis: Axis.Builder = Axis.Builder(),
        var yAxis: Axis.Builder = Axis.Builder(),
        var drawingList: MutableList<Drawing.Builder> = mutableListOf(),
        var position: PlotPosition.Builder = PlotPosition.Builder()
    ) {

        fun build() = Plot(
            title = title,
            xAxis = xAxis.build(),
            yAxis = yAxis.build(),
            drawingList = drawingList.map { it.build() },
            position = position.build()
        )

    }
}