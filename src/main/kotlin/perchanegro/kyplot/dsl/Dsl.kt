package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.drawing.Histogram
import perchanegro.kyplot.model.drawing.Line
import perchanegro.kyplot.model.drawing.Scatter

inline fun showHistogram(
    data: Iterable<Number> = emptyList(),
    title: String = "",
    init: Histogram.Builder.()->Unit = {}
) {
    showPlot(title) {
        histogram(
            data = data,
            init = init
        )
    }
}


inline fun showLine(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    title: String = "",
    init: Line.Builder.()->Unit = {}
) {
    showPlot(title) {
        line(
            x = x,
            y = y,
            init = init
        )
    }
}

inline fun showScatter(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    title: String = "",
    init: Scatter.Builder.()->Unit = {}
) {
    showPlot(title) {
        scatter(
            x = x,
            y = y,
            init = init
        )
    }
}


