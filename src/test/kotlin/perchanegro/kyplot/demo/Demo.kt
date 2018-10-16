package perchanegro.kyplot.demo

import perchanegro.kyplot.dsl.*
import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.LineType
import perchanegro.kyplot.model.MarkerType
import perchanegro.kyplot.model.Plot
import java.util.Random
import kotlin.math.*

val random = Random()

fun main() {

    val xs = List(1000) {
        random.nextGaussian()
    }

    val ys = List(1000) {
        random.nextGaussian()
    }

    val abs = xs.zip(ys).map { hypot(it.first, it.second) }

    val xSpace = List(100) { i -> -5 + 10 * i.toDouble() / 100 }
    val gaussianPdf = xSpace.map { exp(-(it * it)/2) / (sqrt(2 * PI)) }

/*
    showPlot {

        scatter(x = xs, y = ys) {
            markerStyle.type = MarkerType.TRIANGLE_RIGHT
            markerStyle.color = Color.RED
            markerStyle.size = 100
        }

        xAxis.limits = between(-5, 5)

        yAxis {
            limits = between(-5, 5)
            tickLabels()
        }

    }


    showHistogram {
        data = abs
        bins = 40
    }

    showPlot {

        title = "Gaussian"

        xAxis.label = "Samples"
        yAxis.label = "Probability density"

        histogram(xs) {
            bins = 40
            normalized = true
            color = Color.BLUE

        }

        line {
            x = xSpace
            y = gaussianPdf

            lineStyle {
                color = Color.BLUE
                type = LineType.DASHED
                width = 2
            }

        }
    }
*/

    showStem(
        x = xs.take(10),
        y = ys.take(10)
    ) {
         lineStyle {
            type = LineType.DASHED
            color = Color.YELLOW
        }

        markerStyle {
            size = 10
            color = Color.GREEN
            type = MarkerType.TRIANGLE_RIGHT
        }
    }
}
