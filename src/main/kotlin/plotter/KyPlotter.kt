package plotter

import model.*
import aliceinnets.python.jyplot.JyPlot

class KyPlotter {
    val plt = JyPlot().apply {
        fromImports("matplotlib", "cm")
    }

    private fun JyPlot.buildPlot(plot: Plot) {

        with(plot) {
            title(title)
            xlabel(xAxis.label)
            ylabel(yAxis.label)

            when(xAxis.limits) {
                is Plot.Axis.Limits.Explicit -> {
                    xlim(xAxis.limits.min, xAxis.limits.max)
                }
            }

            when(yAxis.limits) {
                is Plot.Axis.Limits.Explicit -> {
                    ylim(yAxis.limits.min, yAxis.limits.max)
                }
            }

            drawingList.forEach { buildDrawing(it) }
        }
    }

    private fun JyPlot.buildDrawing(drawing: Drawing) {
        when(drawing) {
            is Line -> {
                plot(drawing.x, drawing.y)
            }

            is Histogram -> {
                hist(drawing.data, drawing.bins)
            }
        }
    }

    fun show(figure: Figure) {
        with(plt) {
            figure()
            suptitle(figure.title)

            for ((plot, position) in figure.plotList) {

                subplot(
                    position.rowCount,
                    position.columnCount,
                    1 + position.column + position.row * position.columnCount
                )

                buildPlot(plot)
            }

            show()
            exec()
        }
    }
}

