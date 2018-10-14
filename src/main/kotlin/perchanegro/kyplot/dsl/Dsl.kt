package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.drawing.Histogram
import perchanegro.kyplot.model.drawing.Line
import perchanegro.kyplot.model.drawing.Scatter

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

inline fun showScatter(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    init: Scatter.Builder.()->Unit = {}
) {
    showPlot {
        scatter(
            x = x,
            y = y,
            init = init
        )
    }
}


