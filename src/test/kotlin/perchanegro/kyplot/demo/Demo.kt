package perchanegro.kyplot.demo

import perchanegro.kyplot.dsl.*
import kotlin.random.Random
import java.util.Random as JavaRandom

fun main() {

    val uniformRandom = List(1000) {
        Random.nextDouble()
    }

    val gaussRandom = List(1000) {
        JavaRandom().nextGaussian()
    }


    showHistogram(uniformRandom) {
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

    }

}
