package perchanegro.kyplot.demo.co2


import perchanegro.kyplot.dsl.*
import perchanegro.kyplot.dsl.showLine
import perchanegro.kyplot.dsl.showPlot
import perchanegro.kyplot.model.Axis
import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.Legend
import java.io.File
import kotlin.math.*

fun loadData(): Pair<List<Double>, List<Double>> {

    val data = File("data/CO2.txt").useLines { lines ->
        lines
            .map { it.toDouble() }
            .toList()
    }

    val t = linearSpace(
        min = 1974.38,
        max = 2016.753,
        count = 2212
    )

    return t to data
}

fun main() {

    val (time, data) = loadData()

    val delta = (2016.753 - 1974.38) / 2211
    val fs = 1 / delta

    showLine(
        title = "Raw data",
        x = time,
        y = data
    )

    val logData = data.map { ln(it) }

    val tendency = polynomialRegression(
        x = time,
        y = logData,
        order = 2
    )

    showPlot {

        title = "Raw data with tendency"

        line(time, logData) {
            label = "Log of data"
        }

        line(time, tendency(time)) {
            label = "Tendency"
        }

        legend.visible = true

    }

    val dataWithoutTendency =  logData - tendency(time)

    showLine(
        title = "Data without tendency",
        x = time,
        y = dataWithoutTendency
    )

    showPlot {

        title = "Data without tendency spectrum"

        spectrumMagnitude(dataWithoutTendency) {
            samplingFrequency = fs
        }

        yAxis.scale = Axis.Scale.LOGARITHMIC

    }

    val timeInYear = time.map { it % 1 }

    val modSpace = linearSpace(0.0, 1.0, 100)

    val cycle = regression(
        x = time,
        y = dataWithoutTendency,
        basisFunctions = listOf(
            Sine(1.0),
            Cosine(1.0),
            Sine(2.0),
            Cosine(2.0)
        )
    )

    showPlot {

        title = "Samples within year and cycle estimation"

        scatter(timeInYear, dataWithoutTendency) {
            label = "Samples"
            markerStyle {
                alpha = 0.6
            }
        }

        line(modSpace, cycle(modSpace)) {
            label = "Regression"
            color = Color.RED
        }

        legend.visible = true

    }

    showLine(
        title = "Residue",
        x = time,
        y = dataWithoutTendency - cycle(time)
    )

    val extendedT = linearSpace(min = 1974.0, max = 2050.0, count = 1000)
    val estimate = (tendency(extendedT) + cycle(extendedT)).map { exp(it) }

    showPlot {

        title = "Raw data and forecasts"

        line(time, data)
        line(extendedT, estimate)

        xAxis {
            label = "year"
        }

        yAxis {
            label = "CO2 concentration (ppm)"
        }

    }


}