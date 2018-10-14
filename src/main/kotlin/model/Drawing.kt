package model

sealed class Drawing {
    interface Builder {
        fun build(): Drawing
    }
}

data class Line(
    val x: Iterable<Number>,
    val y: Iterable<Number>
): Drawing() {

    @PlotDslMarker
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var y: Iterable<Number> = emptyList()
    ): Drawing.Builder {

        override fun build() = Line(x, y)

    }

}

data class Histogram(
    val data: Iterable<Number>,
    val bins: Int = 10
): Drawing() {

    @PlotDslMarker
    class Builder(
        var data: Iterable<Number> = emptyList(),
        var bins: Int = 10
    ): Drawing.Builder {

        override fun build() = Histogram(data, bins)

    }

}

