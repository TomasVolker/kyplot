import Model.Figure
import Model.Line
import Model.Plot
import Model.PositionedPlot
import aliceinnets.python.jyplot.JyPlot

fun main(args: Array<String>) {
    val figure = Figure(
        "figure title",
        listOf(
            PositionedPlot(
                Plot(
                    title = "first plot",
                    drawingList = listOf(
                        Line(
                            x = (0..10).toList(),
                            y = (0..10).map { it }
                        ),
                        Line(
                            x = (0..10).toList(),
                            y = (0..10).map { it * it }
                        ),
                        Line(
                            x = (0..10).toList(),
                            y = (0..10).map { it * it * it }
                        )
                    ),
                    xAxis = Plot.Axis(
                        label = "x",
                        limits = Plot.Axis.Limits.Explicit(2,15)
                    ),
                    yAxis = Plot.Axis(
                        label = "y"
                    )
                )
            )
        )
    )

    KyPlotter().show(figure)
}