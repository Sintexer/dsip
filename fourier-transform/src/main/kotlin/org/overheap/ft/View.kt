package org.overheap.ft

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.label.labs
import java.io.File

fun createHtml(function: (Double) -> Double): String {
    val precision = 32

    val points = (0 until precision)
    val values = formValueSequence(precision) { it }.also{println(it)}//TODO think about name
    val complexFrequencies = formDftFrequencies(precision, function)

    val plots = listOf(
        createPlot(values, values.map(function)) +
                labs(title = "Source", x = "t", y = "F(t)"),
        createPlot(points, inverseDft(complexFrequencies)) +
                labs(title = "Inverse Dft", x = "t", y = "F(t)"),
        createPlot(points, complexFrequencies.modular()) +
                labs(title = "Dft amplitudes", x = "N", y = "|F[n]|")
    )

    ggsave(createBunch(plots), "plot.html", path = "plots")
    return File("plots/plot.html").readText()
}