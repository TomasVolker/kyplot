package plotter

import model.*
import java.util.Random as JavaRandom

import kotlin.random.Random

fun main(args: Array<String>) {

    val uniformRandom = List(1000) {
        Random.nextDouble()
    }

    val gaussRandom = List(1000) {
        JavaRandom().nextGaussian()
    }

    val figure = Figure(
        "figure title",
        listOf(
            PositionedPlot(
                plot = Plot(
                        title = "first plot",
                        drawingList = listOf(
                            Line(
                                x = (0..10).toList(),
                                y = (0..10).map { it * it }
                            )
                        ),
                        xAxis = Plot.Axis(
                            label = "data",
                            limits = Plot.Axis.Limits.Explicit(2,15)
                        ),
                        yAxis = Plot.Axis(
                            label = "bind"
                        )
                ),
                position = PlotPosition(
                    columnCount = 2,
                    rowCount = 2,
                    row = 0,
                    column = 0
                )
            ),

            PositionedPlot(
                plot = Plot(
                    title = "second plot",
                    drawingList = listOf(
                        Line(
                            x = (0..10).toList(),
                            y = (0..10).map { it }
                        )
                    ),
                    xAxis = Plot.Axis(
                        label = "data"
                    ),
                    yAxis = Plot.Axis(
                        label = "bind"
                    )
                ),
                position = PlotPosition(
                    columnCount = 2,
                    rowCount = 2,
                    row = 0,
                    column = 1
                )
            ),

            PositionedPlot(
                plot = Plot(
                    title = "second plot",
                    drawingList = listOf(
                        Histogram(
                            data = gaussRandom,
                            bins = 50
                        )
                    ),
                    xAxis = Plot.Axis(
                        label = "data"
                    ),
                    yAxis = Plot.Axis(
                        label = "bind"
                    )
                ),
                position = PlotPosition(
                    rowCount = 2,
                    columnCount = 1,
                    row = 1,
                    column = 0
                )
            )
        )
    )

    KyPlotter().show(figure)
}