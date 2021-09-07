package by.daryazalevskaya.fwt

import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.lets_plot

object Plots {

    private const val PLOT_WIDTH = 600
    private const val PLOT_HEIGHT = 325

    private const val OFFSET_X = 40
    private const val OFFSET_Y = 40

    private fun createPlot(data: Map<String, Iterable<Any?>>): Plot {
        return lets_plot(data) { x = "x"; y = "y" } +
                geom_line() + ggsize(PLOT_WIDTH, PLOT_HEIGHT)
    }

    private fun <T, N> createPlot(x: Iterable<T>, y: Iterable<N>): Plot {
        val data = mapOf(
            "x" to x,
            "y" to y
        )
        return createPlot(data)
    }

    private fun createBunch(plots: List<Plot>): GGBunch {
        return GGBunch().also {
            plots.forEachIndexed { i, plot ->
                it.addPlot(plot, OFFSET_X, OFFSET_Y + PLOT_HEIGHT * i)
            }
        }
    }

    fun createPlotsPage(precision: Int, function: (Double) -> Double) {

        val points = (0 until precision)
        val functionValues = Fwt.formFunctionValues(precision, function)
        val fwt=Fwt.buildFwt(functionValues)
        val ifwt=Fwt.buildFwt(fwt)

        val plots = listOf(
            createPlot(points, functionValues) +
                    labs(title = "Source", x = "t", y = "F(t)"),
            createPlot(points, fwt) +
                    labs(title = "FWT", x = "t", y = "FWT"),
            createPlot(points, ifwt) +
                    labs(title = "IFWT", x = "t", y = "I FWT")
        )

        ggsave(createBunch(plots), "plot.html", path = "plots")
    }
}