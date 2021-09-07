package by.daryazalevskaya.filter

import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.lets_plot
import kscience.kmath.operations.Complex
import kscience.kmath.operations.r

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
        val filterPoints = (0 until Filter.M)

        val functionValues = formFunctionValues(precision, function)
        val frequencies = FFT.fft(functionValues.map { Complex(it, 0) })

        val impulses = Filter.impulses();

        val AFC = FFT.fft(impulses.map { Complex(it, 0) }).map { it.r };
        val fir = FFT.fft(Filter.fir(functionValues).map { Complex(it, 0) }).map { it.r }
        val iir = Filter.iir(frequencies.map { it.r })

        val plots = listOf(
            createPlot(points, functionValues) +
                    labs(title = "Source", x = "t", y = "F(t)"),
            createPlot(points, frequencies.map { it.r }) +
                    labs(title = "Frequencies", x = "t", y = "F(t)"),
            createPlot(filterPoints, impulses) +
                    labs(title = "Impulse", x = "i", y = "h(i)"),
            createPlot(filterPoints, AFC) +
                    labs(title = "AFC", x = "f", y = "A"),
            createPlot(points, fir) +
                    labs(title = "Filter FIR", x = "n", y = "y(n)"),
            createPlot(points, iir) +
                    labs(title = "Filter IIR", x = "n", y = "y(n)")
        )

        ggsave(createBunch(plots), "plot.html", path = "plots")

    }
}