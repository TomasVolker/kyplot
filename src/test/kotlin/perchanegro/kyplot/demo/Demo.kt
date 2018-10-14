package perchanegro.kyplot.demo

import perchanegro.kyplot.dsl.*
import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.LineType
import perchanegro.kyplot.model.MarkerType
import perchanegro.kyplot.model.drawing.Line
import kotlin.random.Random
import java.util.Random as JavaRandom

var Line.Builder.color: Color
    get() = lineStyle.color
    set(value) { lineStyle.color = value }

fun main() {

    val random = JavaRandom()

    val uniformRandom = List(1000) {
        random.nextDouble()
    }

    val gaussRandom = List(1000) {
        random.nextGaussian()
    }

    showLine {

        x = uniformRandom
        y = gaussRandom

        color = Color.RED

        lineStyle {
            width = 5
        }

    }


    showPlot {

        line {
            label = "myLine 1"
            x = (0 until 1000).toList()
            y = gaussRandom

            lineStyle {
                color = Color.rgb(0.1, 0.5, 0.1)
                width = 0.8
                type = LineType.DOTTED
            }

            markerStyle {
                type = MarkerType.CIRCLE
                size = 5
            }

        }
        line {
            label = "myLine 2"
            x = (0 until 1000).toList()
            y = uniformRandom

            lineStyle {
                color = Color.BLUE
                alpha = 0.8
                width = 2
                type = LineType.DASH_DOT
            }

        }
    }
/*
    showPlot {
        spectrumMagnitude(gaussRandom)
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
*/
}
