package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.Figure
import perchanegro.kyplot.model.Plot
import perchanegro.kyplot.model.show

inline fun showFigure(init: Figure.Builder.()->Unit) {
    Figure.Builder()
        .apply(init)
        .build()
        .show()
}

inline fun Figure.Builder.plot(title: String = "", init: Plot.Builder.()->Unit) {
    plotList.add(
        Plot.Builder().apply {
            this.title = title
            init()
        }
    )
}