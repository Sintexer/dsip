package org.overheap.ft

import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.lets_plot

const val PLOT_WIDTH = 600
const val PLOT_HEIGHT = 325

fun <T> createPlot(data: Map<String, Iterable<T>>): Plot {
    return lets_plot(data){x = "x"; y = "y"} +
            geom_line() + ggsize(PLOT_WIDTH, PLOT_HEIGHT)
}

fun <T, N> createPlot(x: Iterable<T>, y: Iterable<N>): Plot {
    val data = mapOf(
        "x" to x,
        "y" to y
    )
    return createPlot(data)
}

fun <T> createPlot(values: List<T>, f: (T) -> Double): Plot{
    val data = mapOf(
        "x" to values,
        "y" to values.map(f)
    )
    return createPlot(data)
}