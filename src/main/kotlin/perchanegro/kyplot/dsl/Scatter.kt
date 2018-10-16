package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.Color
import perchanegro.kyplot.model.LineStyle
import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.drawing.Line
import perchanegro.kyplot.model.drawing.Scatter

inline fun Scatter.Builder.markerStyle(init: MarkerStyle.Builder.()->Unit) {
    markerStyle.apply(init)
}

var Scatter.Builder.color: Color
    get() = markerStyle.color
    set(value) { markerStyle.color = value }

var Scatter.Builder.alpha: Number
    get() = markerStyle.alpha
    set(value) { markerStyle.alpha = value }