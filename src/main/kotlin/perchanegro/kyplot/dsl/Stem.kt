package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.LineStyle
import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.drawing.Line
import perchanegro.kyplot.model.drawing.Stem

inline fun Stem.Builder.lineStyle(init: LineStyle.Builder.()->Unit) {
    lineStyle.apply(init)
}

inline fun Stem.Builder.markerStyle(init: MarkerStyle.Builder.()->Unit) {
    markerStyle.apply(init)
}