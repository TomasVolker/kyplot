package perchanegro.kyplot.model.drawing

import perchanegro.kyplot.model.LineStyle
import perchanegro.kyplot.model.MarkerStyle
import perchanegro.kyplot.model.PlotDsl

sealed class Drawing {

    abstract val label: String

    interface Builder {
        fun build(): Drawing
    }
}

data class Line(
    val x: Iterable<Number>,
    val y: Iterable<Number>,
    override val label: String = "",
    val lineStyle: LineStyle = LineStyle(),
    val markerStyle: MarkerStyle = MarkerStyle()
): Drawing() {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var y: Iterable<Number> = emptyList(),
        var label: String = "",
        var lineStyle: LineStyle.Builder = LineStyle.Builder(),
        var markerStyle: MarkerStyle.Builder = MarkerStyle.Builder()
    ): Drawing.Builder {

        override fun build() =
            Line(
                x = x,
                y = y,
                label = label,
                lineStyle = lineStyle.build(),
                markerStyle = markerStyle.build()
            )

    }

}

data class Histogram(
    val data: Iterable<Number>,
    val bins: Int = 10,
    override val label: String = ""
): Drawing() {

    @PlotDsl
    class Builder(
        var data: Iterable<Number> = emptyList(),
        var bins: Int = 10,
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            Histogram(
                data = data,
                bins = bins,
                label = label
            )

    }

}

data class SpectrumMagnitude(
    val signal: Iterable<Number>,
    val samplingFrequency: Number = 1.0,
    override val label: String = ""
): Drawing() {

    @PlotDsl
    class Builder(
        var signal: Iterable<Number> = emptyList(),
        var samplingFrequency: Number = 1.0,
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            SpectrumMagnitude(
                signal = signal,
                samplingFrequency = samplingFrequency,
                label = label
            )

    }

}

data class SpectrumPhase(
    val signal: Iterable<Number>,
    val samplingFrequency: Number = 1.0,
    override val label: String = ""
): Drawing() {

    @PlotDsl
    class Builder(
        var signal: Iterable<Number> = emptyList(),
        var samplingFrequency: Number = 1.0,
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            SpectrumPhase(
                signal = signal,
                samplingFrequency = samplingFrequency,
                label = label
            )

    }

}