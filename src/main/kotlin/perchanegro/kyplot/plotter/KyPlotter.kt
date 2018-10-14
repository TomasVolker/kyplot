package perchanegro.kyplot.plotter

import aliceinnets.python.Parser
import aliceinnets.python.PythonScriptUtil
import perchanegro.kyplot.model.*
import aliceinnets.python.jyplot.JyPlot
import perchanegro.kyplot.model.drawing.*
import perchanegro.kyplot.model.LineType.*
import perchanegro.kyplot.model.MarkerType.*
import perchanegro.kyplot.model.MarkerFillStyle.*

object KyPlotConfig {

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

            when(xAxis.tickPositions) {
                is Plot.Axis.TickPositions.Explicit -> {
                    xticks(
                        xAxis.tickPositions.tickList.map { it.position },
                        xAxis.tickPositions.tickList.map { it.label }
                    )
                }
            }

            when(yAxis.tickPositions) {
                is Plot.Axis.TickPositions.Explicit -> {
                    yticks(
                        yAxis.tickPositions.tickList.map { it.position },
                        yAxis.tickPositions.tickList.map { it.label }
                    )
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
                    "label" setTo drawing.label,
                    "linewidth" setTo drawing.lineStyle.width,
                    "lineStyle" setTo drawing.lineStyle.type.toPythonText(),
                    "color" setTo drawing.lineStyle.color.toPythonColor(),
                    "alpha" setTo drawing.lineStyle.alpha,
                    "marker" setTo drawing.markerStyle.type.toPythonText(),
                    "markersize" setTo drawing.markerStyle.size
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
                    "Fs" setTo drawing.samplingFrequency,
                    "label" setTo drawing.label
                )
            }
            is SpectrumPhase -> {
                phase_spectrum(
                    drawing.signal,
                    "Fs" setTo drawing.samplingFrequency,
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

fun Color.toPythonColor(): List<Number>? = when(this) {
    is Color.Auto -> null
    is Color.Explicit -> listOf(red, green, blue)
}

fun LineType.toPythonText(): String = when(this) {
    SOLID -> "-"
    DASHED -> "--"
    DASH_DOT -> "-."
    DOTTED -> ":"
}

fun MarkerType.toPythonText(): String? = when(this) {
    MarkerType.NONE -> null
    POINT -> "."
    PIXEL -> ","
    CIRCLE -> "o"
    TRIANGLE_DOWN -> "v"
    TRIANGLE_UP -> "^"
    TRIANGLE_LEFT -> "<"
    TRIANGLE_RIGHT -> ">"
    TRI_DOWN -> "1"
    TRI_UP -> "2"
    TRI_LEFT -> "3"
    TRI_RIGHT -> "4"
    SQUARE -> "s"
    PENTAGON -> "p"
    STAR -> "*"
    HEXAGON_1 -> "h"
    HEXAGON_2 -> "H"
    PLUS -> "+"
    X -> "x"
    X_FILLED -> "X"
    DIAMOND -> "D"
    THIN_DIAMOND -> "d"
    VERTICAL_LINE -> "|"
    HORIZONTAL_LINE -> "_"
}

fun MarkerFillStyle.toPythonText(): String = when(this) {
    MarkerFillStyle.NONE -> "none"
    FULL -> "full"
    LEFT -> "left"
    RIGHT -> "right"
    BOTTOM -> "bottom"
    TOP -> "top"
}