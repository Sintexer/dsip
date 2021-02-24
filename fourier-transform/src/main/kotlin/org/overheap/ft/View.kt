package org.overheap.ft

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.label.labs
import java.io.File
import kotlin.math.cos
import kotlin.math.sin

fun createPlotsPage(precision: Int, function: (Double) -> Double): String {
    val points = (0 until precision)
    val values = formValueSequence(precision) { it }.also{println(it)}//TODO think about name
    val complexFrequencies = dft(precision, function)

    val fftVal = FFT.fft(values.toComplex()).modular()

    val plots = listOf(
        createPlot(values, values.map(function)) +
                labs(title = "Source", x = "t", y = "F(t)"),
        createPlot(points, inverseDft(complexFrequencies)) +
                labs(title = "Inverse Dft", x = "t", y = "F(t)"),
        createPlot(points, fftVal) +
                labs(title = "Fast ft", x = "t", y = "F(t)"),
        createPlot(points, complexFrequencies.modular()) +
                labs(title = "Dft amplitudes", x = "N", y = "|F[n]|"),
        createPlot(points, complexFrequencies.theta()) +
                labs(title = "Dft phases", x = "N", y = "|F[n]|")
    )

    ggsave(createBunch(plots), "plot.html", path = "plots")
    return File("plots/plot.html").readText()
}

fun main() {
    val precision = 32
    val function = { x:Double -> cos(2 * x) + sin(5 * x)}
    val points = (0 until precision)
    val values = formValueSequence(precision) { it }.also{println(it)}
    val complexFrequencies = dft(precision, function).println()

    val fftVal = FFT.fft(values.toComplex()).println()
}