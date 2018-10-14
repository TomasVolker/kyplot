package model

data class Plot(
    val title: String = "",
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val drawingList: List<Drawing> = emptyList()
) {

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

    @PlotDslMarker
    class Builder(
        var title: String = "",
        var xAxis: Axis = Axis(),
        var yAxis: Axis = Axis(),
        var drawingList: MutableList<Drawing.Builder> = mutableListOf()
    ) {
        fun build() = Plot(
            title = title,
            xAxis = xAxis,
            yAxis = yAxis,
            drawingList = drawingList.map { it.build() }
        )
    }
}