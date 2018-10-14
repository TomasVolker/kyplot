package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.Plot

fun Plot.Axis.Builder.between(lower: Number, upper: Number) =
    Plot.Axis.Limits.Explicit(lower, upper)

fun Plot.Axis.Builder.auto() = Plot.Axis.Limits.Auto

fun Plot.Axis.Builder.tickLabels(labels: Iterable<Pair<Number, String>>) {

    this.tickPositions = Plot.Axis.TickPositions.Explicit(
        labels.map {
            Plot.Axis.Tick(
                position = it.first,
                label = it.second
            )
        }
    )

}

fun Plot.Axis.Builder.tickLabels(vararg labels: Pair<Number, String>) {
    tickLabels(labels.toList())
}

fun Plot.Axis.Builder.tickPositions(positions: Iterable<Number>) {

    this.tickPositions = Plot.Axis.TickPositions.Explicit(
        positions.map {
            Plot.Axis.Tick(it, "%.2f".format(it.toDouble()))
        }
    )

}

fun Plot.Axis.Builder.tickPositions(vararg positions: Number) {
    tickPositions(positions.toList())
}
