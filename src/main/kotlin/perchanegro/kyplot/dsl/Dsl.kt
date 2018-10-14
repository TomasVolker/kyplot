package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.drawing.Histogram
import perchanegro.kyplot.model.drawing.Line

inline fun showHistogram(
    data: Iterable<Number> = emptyList(),
    init: Histogram.Builder.()->Unit = {}
) {
    showPlot {
        histogram(
            data = data,
            init = init
        )
    }
}


inline fun showLine(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    init: Line.Builder.()->Unit = {}
) {
    showPlot {
        line(
            x = x,
            y = y,
            init = init
        )
    }
}