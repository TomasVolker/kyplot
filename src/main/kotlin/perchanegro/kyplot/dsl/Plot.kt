package perchanegro.kyplot.dsl

import perchanegro.kyplot.model.Plot
import perchanegro.kyplot.model.PlotPosition
import perchanegro.kyplot.model.drawing.*

inline fun Plot.Builder.position(init: PlotPosition.Builder.()->Unit) {
    position.apply(init)
}

inline fun <T: Drawing.Builder> Plot.Builder.drawing(
    drawing: T,
    init: T.()->Unit = {}
) {
    drawingList.add(drawing.apply(init))
}

inline fun Plot.Builder.line(
    x: Iterable<Number> = emptyList(),
    y: Iterable<Number> = emptyList(),
    init: Line.Builder.()->Unit = {}
) {
    drawing(Line.Builder()) {
        this.x = x
        this.y = y
        init()
    }
}

inline fun Plot.Builder.spectrumMagnitude(
    signal: Iterable<Number> = emptyList(),
    samplingFrequency: Number = 1.0,
    init: SpectrumMagnitude.Builder.()->Unit = {}
) {
    drawing(SpectrumMagnitude.Builder()) {
        this.signal = signal
        this.samplingFrequency = samplingFrequency
        init()
    }
}


inline fun Plot.Builder.spectrumPhase(
    signal: Iterable<Number> = emptyList(),
    samplingFrequency: Number = 1.0,
    init: SpectrumPhase.Builder.()->Unit = {}
) {
    drawing(SpectrumPhase.Builder()) {
        this.signal = signal
        this.samplingFrequency = samplingFrequency
        init()
    }
}

inline fun Plot.Builder.histogram(
    data: Iterable<Number> = emptyList(),
    init: Histogram.Builder.()->Unit = {}
) {
    drawing(Histogram.Builder()) {
        this.data = data
        init()
    }
}



inline fun showPlot(init: Plot.Builder.()->Unit) {
    showFigure {
        plot(init = init)
    }
}