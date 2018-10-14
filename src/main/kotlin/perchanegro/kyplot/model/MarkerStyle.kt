package perchanegro.kyplot.model

data class MarkerStyle(
    val type: MarkerType = MarkerType.NONE,
    val size: Number = 1.0
) {

    @PlotDslMarker
    class Builder(
        var type: MarkerType = MarkerType.NONE,
        var size: Number = 1.0
    ) {
        fun build() = MarkerStyle(
            type = type,
            size = size
        )
    }

}