package perchanegro.kyplot.demo

import perchanegro.kyplot.dsl.*
import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.MarkerType
import perchanegro.kyplot.model.Plot
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random
import java.util.Random as JavaRandom

fun main() {

    val uniformRandom = List(1000) {
        Random.nextDouble()
    }

    val gaussRandom = List(1000) {
        JavaRandom().nextGaussian()
    }


/*    showHistogram(uniformRandom) {
        bins = 50
    }

    showPlot {
        line {
            label = "myLine 1"
            x = (0 until 1000).toList()
            y = gaussRandom
        }
        line {
            label = "myLine 2"
            x = (0 until 1000).toList()
            y = uniformRandom
        }

        xAxis {
            limits = between(0, 10)
            tickLabels(
                5 to "a",
                6 to "b",
                1.2 to "negro",
                2.5 to "percha"

            )
        }

        yAxis {
            limits = -1 upTo 1
            tickPositions(
                3, 5, 6, 7, 2, 4
            )
        }


    }

    showPlot {
        histogram(gaussRandom)
    }

    showFigure {

        plot {

            position {
                columnCount = 2
                column = 0
            }

            histogram (uniformRandom) {
                bins = 40
            }

        }

        plot {

            position {
                columnCount = 2
                column = 1
            }

            title = "Gaussian Random"
            histogram {

                data = gaussRandom
                bins = 50

            }

        }

    }*/

    showPlot {
        scatter(x = gaussRandom, y = uniformRandom) {
            markerStyle.type = MarkerType.PENTAGON
            markerStyle.color = Color.GREEN
        }
    }



}
