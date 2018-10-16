package perchanegro.kyplot.plotter

import aliceinnets.python.Parser
import aliceinnets.python.PythonScriptUtil
import perchanegro.kyplot.model.*
import aliceinnets.python.jyplot.JyPlot
import perchanegro.kyplot.model.drawing.*
import perchanegro.kyplot.model.LineType.*
import perchanegro.kyplot.model.MarkerType.*
import perchanegro.kyplot.model.MarkerFillStyle.*
import perchanegro.kyplot.model.Legend.Position.*

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

            drawingList.forEach { buildDrawing(it) }

            xlabel(xAxis.label)
            ylabel(yAxis.label)

            xscale(xAxis.scale.toPythonText())
            yscale(yAxis.scale.toPythonText())

            when(xAxis.limits) {
                is Axis.Limits.Explicit -> {
                    xlim(xAxis.limits.min, xAxis.limits.max)
                }
            }

            when(yAxis.limits) {
                is Axis.Limits.Explicit -> {
                    ylim(yAxis.limits.min, yAxis.limits.max)
                }
            }

            when(xAxis.tickPositions) {
                is Axis.TickPositions.Explicit -> {
                    xticks(
                        xAxis.tickPositions.tickList.map { it.position },
                        xAxis.tickPositions.tickList.map { it.label.toPythonExpression() }
                    )
                }
            }

            when(yAxis.tickPositions) {
                is Axis.TickPositions.Explicit -> {
                    yticks(
                        yAxis.tickPositions.tickList.map { it.position },
                        yAxis.tickPositions.tickList.map { it.label.toPythonExpression() }
                    )
                }
            }

            if (grid.visible) {

                grid(
                    "linestyle" setTo grid.lineStyle.type.toPythonText(),
                    "linewidth" setTo grid.lineStyle.width,
                    "alpha" setTo grid.lineStyle.alpha
                )

                if (grid.lineStyle.color !is Color.Auto) {
                    grid(
                        "color" setTo grid.lineStyle.color.toPythonColor()
                    )
                }

            }

            if (legend.visible) {
                legend("loc" setTo legend.position.toPythonString())
            }

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
                    "markersize" setTo drawing.markerStyle.size,
                    "zorder" setTo 3
                )
            }
            is Histogram -> {
                hist(
                    drawing.data,
                    drawing.bins,
                    "label" setTo drawing.label,
                    "density" setTo drawing.normalized,
                    "zorder" setTo 3,
                    "color" setTo drawing.color.toPythonColor()
                )
            }
            is SpectrumMagnitude -> {
                magnitude_spectrum(
                    drawing.signal,
                    "label" setTo drawing.label,
                    "Fs" setTo drawing.samplingFrequency
                )
            }
            is SpectrumPhase -> {
                phase_spectrum(
                    drawing.signal,
                    "label" setTo drawing.label,
                    "Fs" setTo drawing.samplingFrequency
                )
            }
            is PowerSpectralDensity -> {
                psd(
                    drawing.signal,
                    "label" setTo drawing.label,
                    "Fs" setTo drawing.samplingFrequency
                )
            }
            is CrossSpectralDensity -> {
                csd(
                    drawing.signal1,
                    drawing.signal2,
                    "label" setTo drawing.label,
                    "Fs" setTo drawing.samplingFrequency
                )
            }
            is Scatter -> {
                scatter(
                    drawing.x,
                    drawing.y,
                    "marker" setTo drawing.markerStyle.type.toPythonText(),
                    "label" setTo drawing.label,
                    "alpha" setTo drawing.markerStyle.alpha,
                    "color" setTo  drawing.markerStyle.color.toPythonColor(),
                    "zorder" setTo 3 // To show over the grid
                )
            }
            is Bar -> {
                bar(
                    drawing.x,
                    drawing.heights,
                    "label" setTo drawing.label,
                    "align" setTo drawing.alignment.toPythonText(),
                    "width" setTo drawing.width,
                    "color" setTo (drawing.color.toPythonColor() ?: Color.BLUE.toPythonColor())
                )
            }
            is Stem -> {
                stem(
                    drawing.x,
                    drawing.y,
                    "label" setTo drawing.label,
                    "linefmt" setTo (drawing.lineStyle.color.toPythonString() + drawing.lineStyle.type.toPythonText()),
                    "markerfmt" setTo ((drawing.markerStyle.type.toPythonText() ?: ".") +
                            drawing.markerStyle.color.toPythonString())
                )
            }
        }
    }

    private infix fun String.setTo(value: Any?) =
            "$this=${value.toPythonExpression()}"

    private fun Any?.toPythonExpression(): String =
        Parser.toPythonExpression(this)

}

fun Axis.Scale.toPythonText(): String = when(this) {
    Axis.Scale.LINEAR -> "linear"
    Axis.Scale.LOGARITHMIC -> "log"
}

fun Color.toPythonColor(): List<Number>? = when(this) {
    is Color.Auto -> null
    is Color.Explicit -> listOf(red.apply {  }, green, blue)
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
    MarkerFillStyle.RIGHT -> "right"
    BOTTOM -> "bottom"
    TOP -> "top"
}

fun BarAlignment.toPythonText(): String = when(this) {
    BarAlignment.CENTER -> "center"
    BarAlignment.EDGE -> "edge"
}

fun Color.toPythonString(): String? =
    when(this) {
        Color.BLUE -> "b"
        Color.GREEN -> "g"
        Color.RED -> "r"
        Color.CYAN -> "c"
        Color.MAGENTA -> "m"
        Color.YELLOW -> "y"
        Color.BLACK -> "k"
        Color.Auto -> ""
        else -> null
    }

fun Legend.Position.toPythonString(): String? =
    when(this) {
        AUTO -> "best"
        UPPER_RIGHT -> "upper right"
        UPPER_LEFT -> "upper left"
        LOWER_LEFT -> "lower left"
        LOWER_RIGHT -> "lower right"
        CENTER_LEFT -> "center left"
        CENTER_RIGHT -> "center right"
        CENTER_LOWER -> "lower center"
        CENTER_UPPER -> "upper center"
        CENTER -> "center"
    }