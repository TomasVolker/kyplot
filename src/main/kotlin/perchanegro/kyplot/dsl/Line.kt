package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.LineStyle
import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.drawing.Line

inline fun Line.Builder.lineStyle(init: LineStyle.Builder.()->Unit) {
    lineStyle.apply(init)
}

inline fun Line.Builder.markerStyle(init: MarkerStyle.Builder.()->Unit) {
    markerStyle.apply(init)
}
