package perchanegro.kyplot.model.drawing

import perchanegro.kyplot.model.*

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

data class Stem(
    val x: Iterable<Number>,
    val y: Iterable<Number>,
    val markerStyle: MarkerStyle,
    val lineStyle: LineStyle = LineStyle(),
    override val label: String = ""
): Drawing() {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var y: Iterable<Number> = emptyList(),
        var markerStyle: MarkerStyle.Builder = MarkerStyle.Builder(),
        var lineStyle: LineStyle.Builder = LineStyle.Builder(),
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            Stem(
                x = x,
                y = y,
                markerStyle = markerStyle.build(),
                lineStyle = lineStyle.build(),
                label = label
            )
    }

}

data class Histogram(
    val data: Iterable<Number>,
    val bins: Int = 10,
    override val label: String = "",
    val normalized: Boolean = false,
    val color: Color = Color.Auto
): Drawing() {

    @PlotDsl
    class Builder(
        var data: Iterable<Number> = emptyList(),
        var bins: Int = 10,
        var label: String = "",
        var normalized: Boolean = false,
        var color: Color = Color.Auto
    ): Drawing.Builder {

        override fun build() =
            Histogram(
                data = data,
                bins = bins,
                label = label,
                normalized = normalized,
                color = color
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

data class Scatter(
    val x: Iterable<Number>,
    val y: Iterable<Number>,
    override val label: String = "",
    val markerStyle: MarkerStyle = MarkerStyle()
): Drawing() {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var y: Iterable<Number> = emptyList(),
        var label: String = "",
        var markerStyle: MarkerStyle.Builder = MarkerStyle.Builder()
    ): Drawing.Builder {

        override fun build() =
            Scatter(
                x = x,
                y = y,
                label = label,
                markerStyle = markerStyle.build()
            )

    }

}

data class Bar(
    val x: Iterable<Number>,
    val heights: Iterable<Number>,
    val width: Double,
    val alignment: BarAlignment,
    val color: Color,
    override val label: String = ""
): Drawing() {

    @PlotDsl
    class Builder(
        var x: Iterable<Number> = emptyList(),
        var heights: Iterable<Number> = emptyList(),
        var width: Double = 0.8,
        var alignment: BarAlignment = BarAlignment.CENTER,
        var color: Color = Color.Auto,
        var label: String = ""
    ): Drawing.Builder {

        override fun build() =
            Bar(
                x = x,
                heights = heights,
                width = width,
                alignment = alignment,
                color = color,
                label = label
            )

    }

}

