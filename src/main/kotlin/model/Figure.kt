package model

import plotter.KyPlotter
import kotlin.random.Random

@DslMarker
annotation class PlotDslMarker

data class Figure(
    val title: String = "",
    val plotList: List<PositionedPlot> = emptyList()
) {
    @PlotDslMarker
    class Builder(
        var title: String = "",
        var plotList: MutableList<PositionedPlot.Builder> = mutableListOf()
    ) {
        fun build() = Figure(title, plotList.map { it.build() })
    }
}

inline fun figure(init: Figure.Builder.()->Unit): Figure =
    Figure.Builder()
        .apply(init)
        .build().also { KyPlotter().show(it) }

inline fun Figure.Builder.positionedPlot(init: PositionedPlot.Builder.()->Unit): PositionedPlot.Builder =
    PositionedPlot.Builder()
        .apply(init)
        .also { plotList.add(it)}

inline fun PositionedPlot.Builder.plot(title: String = "", init: Plot.Builder.()->Unit): Plot.Builder =
    Plot.Builder()
        .apply {
            this.title = title
            init()
        }
        .also { plot = it }

inline fun PositionedPlot.Builder.position(init: PlotPosition.Builder.()->Unit): PlotPosition.Builder =
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

inline fun simplePlot(init: Plot.Builder.()->Unit) =
    figure {
        positionedPlot {
            plot(init = init)
        }
    }

inline fun plotHistogram(
    data: Iterable<Number> = emptyList(),
    init: Histogram.Builder.()->Unit = {}
) {
    simplePlot {
        histogram(data, init)
    }
}


inline fun plotLine(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    init: Line.Builder.()->Unit = {}
) {
    simplePlot {
        line(
            x = x,
            y = y,
            init = init
        )
    }
}

fun main() {

    val uniformRandom = List(1000) {
        Random.nextDouble()
    }

    val gaussRandom = List(1000) {
        java.util.Random().nextGaussian()
    }
    plotHistogram(gaussRandom)
    plotHistogram(uniformRandom) { bins = 50}

    simplePlot {
        line {
            y = gaussRandom
            x = (0 until 1000).toList()
        }
        line {
            y = uniformRandom
            x = (0 until 1000).toList()
        }
    }

    simplePlot {
        histogram(uniformRandom)
    }
    figure {
        positionedPlot {

            plot {
                histogram (uniformRandom)
            }

            position {

                rowCount = 1
                columnCount = 2
                row = 0
                column = 1

            }

        }

        positionedPlot {

            plot {

                title = "Gaussian Random"
                histogram {

                    data = gaussRandom
                    bins = 50

                }

            }

            position {

                rowCount = 1
                columnCount = 2
                row = 0
                column = 0

            }

        }
    }
}