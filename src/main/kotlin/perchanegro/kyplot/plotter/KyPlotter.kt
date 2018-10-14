package perchanegro.kyplot.plotter

import aliceinnets.python.Parser
import aliceinnets.python.PythonCode
import aliceinnets.python.PythonScriptUtil
import perchanegro.kyplot.model.*
import aliceinnets.python.jyplot.JyPlot
import perchanegro.kyplot.model.drawing.*

object KyplotConfig {

    var pythonPath: String
        get() = PythonScriptUtil.getPythonPath()
        set(value) = PythonScriptUtil.setPythonPath(value)

}

class KyPlot(pathname: String = ""): JyPlot(pathname) {

    init {
        fromImports("matplotlib", "cm")
    }

    fun show(figure: Figure) {

        figure()
        suptitle(figure.title)

        for (plot in figure.plotList) {

            with(plot.position) {

                subplot(
                    rowCount,
                    columnCount,
                    1 + column + row * columnCount
                )

            }

            buildPlot(plot)
        }

        show()
        exec()

    }

    private fun buildPlot(plot: Plot) {

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

    private fun buildDrawing(drawing: Drawing) {
        when(drawing) {
            is Line -> {
                plot(
                    drawing.x,
                    drawing.y,
                    "label" setTo drawing.label
                )
            }
            is Histogram -> {
                hist(
                    drawing.data,
                    drawing.bins,
                    "label" setTo drawing.label
                )
            }
            is SpectrumMagnitude -> {
                magnitude_spectrum(
                    drawing.signal,
                    drawing.samplingFrequency,
                    "label" setTo drawing.label
                )
            }
            is SpectrumPhase -> {
                phase_spectrum(
                    drawing.signal,
                    drawing.samplingFrequency,
                    "label" setTo drawing.label
                )
            }
        }
    }

    private infix fun String.setTo(value: Any?) =
            "$this=${value.toPythonExpression()}"

    private fun Any?.toPythonExpression(): String =
        Parser.toPythonExpression(this)

}


