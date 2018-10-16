package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.LineStyle
import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.drawing.Line
import perchanegro.kyplot.model.drawing.Stem

inline fun Line.Builder.lineStyle(init: LineStyle.Builder.()->Unit) {
    lineStyle.apply(init)
}

inline fun Line.Builder.markerStyle(init: MarkerStyle.Builder.()->Unit) {
    markerStyle.apply(init)
}

var Line.Builder.color: Color
    get() = lineStyle.color
    set(value) { lineStyle.color = value }
