package org.overheap.ft

import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.lets_plot

const val PLOT_WIDTH = 600
const val PLOT_HEIGHT = 325

const val OFFSET_X = 40
const val OFFSET_Y = 40

fun createPlot(data: Map<String, Iterable<Any?>>): Plot {
    return lets_plot(data) { x = "x"; y = "y" } +
            geom_line() + ggsize(PLOT_WIDTH, PLOT_HEIGHT)
}

fun <T, N> createPlot(x: Iterable<T>, y: Iterable<N>): Plot {
    val data = mapOf(
        "x" to x,
        "y" to y
    )
    return createPlot(data)
}

fun createBunch(plots: List<Plot>): GGBunch {
    return GGBunch().also {
        plots.forEachIndexed { i, plot ->
            it.addPlot(plot, OFFSET_X, OFFSET_Y + PLOT_HEIGHT * i)
        }
    }
}