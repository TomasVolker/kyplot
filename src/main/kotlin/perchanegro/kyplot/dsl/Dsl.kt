package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.*
import perchanegro.kyplot.model.drawing.Histogram
import perchanegro.kyplot.model.drawing.Line

inline fun showFigure(init: Figure.Builder.()->Unit): Figure =
    Figure.Builder()
        .apply(init)
        .build()
        .also { it.show() }

inline fun Figure.Builder.plot(init: Plot.Builder.()->Unit): Plot.Builder =
    Plot.Builder()
        .apply(init)
        .also { plotList.add(it)}

inline fun Plot.Builder.position(init: PlotPosition.Builder.()->Unit): PlotPosition.Builder =
    PlotPosition.Builder()
        .apply(init)
        .also { position = it }

inline fun Plot.Builder.line(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    init: Line.Builder.()->Unit
): Line.Builder =
    Line.Builder()
        .apply {
            this.x = x
            this.y = y
            init()
        }
        .also { drawingList.add(it) }

inline fun Plot.Builder.histogram(
    data: Iterable<Number> = emptyList(),
    init: Histogram.Builder.()->Unit = {}
): Histogram.Builder =

    Histogram.Builder()
        .apply {
            this.data = data
            init()
        }
        .also { drawingList.add(it) }

inline fun showPlot(init: Plot.Builder.()->Unit) =
    showFigure {
        plot(init = init)
    }

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
