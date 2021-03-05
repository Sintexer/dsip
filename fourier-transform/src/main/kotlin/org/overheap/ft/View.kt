package org.overheap.ft

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.label.labs
import kscience.kmath.operations.Complex
import kscience.kmath.operations.theta
import java.io.File
import kotlin.math.cos
import kotlin.math.sin

fun createPlotsPage(precision: Int, function: (Double) -> Double): String {
    val points = (0 until precision)
    val values = formValueSequence(precision) { it }.also{println(it)}//TODO think about name
    val valueSequence = formValueSequence(precision, function)
    val complexFrequencies = FT.dft(precision, function)
    println("DFT steps = ${FT.steps}")
    val fftVals = FFT.fft(valueSequence.toComplex())
    println("FFT steps = ${FFT.steps}")
    val ifftVals = FFT.ifft(fftVals)
    println("IFFT steps = ${FFT.steps}")

    val plots = listOf(
        createPlot(values, values.map(function)) +
                labs(title = "Source", x = "t", y = "F(t)"),
        createPlot(points, inverseDft(complexFrequencies)) +
                labs(title = "Inverse Dft", x = "t", y = "F(t)"),
        createPlot(points, ifftVals) +
                labs(title = "Fast ift", x = "t", y = "F(t)"),
        createPlot(points, complexFrequencies.modular()) +
                labs(title = "Dft amplitudes", x = "N", y = "|F[n]|"),
        createPlot(points, fftVals.modular()) +
                labs(title = "Fast ft", x = "t", y = "F(t)"),
        createPlot(points, FT.createPhases(valueSequence)) +
                labs(title = "Dft phases", x = "N", y = "|F[n]|"),
        createPlot(points, fftVals.map { Complex(it.re, it.im.toInt().toDouble()).theta }) +
                labs(title = "Fft phases", x = "N", y = "|F[n]|")
    )

    ggsave(createBunch(plots), "plot.html", path = "plots")
    return File("plots/plot.html").readText()
}
