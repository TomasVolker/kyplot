package perchanegro.kyplot.model

import perchanegro.kyplot.model.MarkerType.*
import perchanegro.kyplot.model.LineType.*
import perchanegro.kyplot.model.MarkerFillStyle.*

enum class LineType {
    SOLID,
    DASHED,
    DASH_DOT,
    DOTTED
}

enum class MarkerType {
    POINT,
    PIXEL,
    CIRCLE,
    TRIANGLE_DOWN,
    TRIANGLE_UP,
    TRIANGLE_LEFT,
    TRIANGLE_RIGHT,
    TRI_DOWN,
    TRI_UP,
    TRI_LEFT,
    TRI_RIGHT,
    SQUARE,
    PENTAGON,
    STAR,
    HEXAGON_1,
    HEXAGON_2,
    PLUS,
    X,
    X_FILLED,
    DIAMOND,
    THIN_DIAMOND,
    VERTICAL_LINE,
    HORIZONTAL_LINE
}


fun LineType.toPythonText(): String = when(this) {
    SOLID -> "-"
    DASHED -> "--"
    DASH_DOT -> "-."
    DOTTED -> ":"
}

fun MarkerType.toPythonText(): String = when(this) {
    POINT -> "."
    PIXEL -> ","
    CIRCLE -> "o"
    TRIANGLE_DOWN -> "v"
    TRIANGLE_UP -> "^"
    TRIANGLE_LEFT -> "<"
    TRIANGLE_RIGHT -> ">"
    TRI_DOWN -> "1"
    TRI_UP -> "2"
    TRI_LEFT -> "3"
    TRI_RIGHT -> "4"
    SQUARE -> "s"
    PENTAGON -> "p"
    STAR -> "*"
    HEXAGON_1 -> "h"
    HEXAGON_2 -> "H"
    PLUS -> "+"
    X -> "x"
    X_FILLED -> "X"
    DIAMOND -> "D"
    THIN_DIAMOND -> "d"
    VERTICAL_LINE -> "|"
    HORIZONTAL_LINE -> "_"
}

enum class MarkerFillStyle {
    NONE,
    FULL,
    LEFT,
    RIGHT,
    BOTTOM,
    TOP
}

fun MarkerFillStyle.toPythonText(): String = when(this) {
    NONE -> "none"
    FULL -> "full"
    LEFT -> "left"
    RIGHT -> "right"
    BOTTOM -> "bottom"
    TOP -> "top"
}