package org.overheap.ft

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import jetbrains.letsPlot.GGBunch
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.geom.geom_line
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.lets_plot
import kscience.kmath.operations.Complex
import java.io.File
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    embeddedServer(Netty, port = 8080){
        routing {

            get("/"){call.respond(TextContent(createHtml(), ContentType.Text.Html))}
        }
    }.start(wait = true)
}

fun createHtml(): String{
    val amount=32
    val offsetX = 40
    val offsetY = 40
    val step = PLOT_HEIGHT

    val points = (0 until amount).map { it * 2 * PI/ amount }.also { println(it) }
    val values = getPoints(amount).also{println(it)}
    val function: (Double) -> Double = {x-> cos(2*x)+sin(5*x)}

    val bunch = GGBunch()
    val plots = mutableListOf<Plot>()
    plots.add(createPlot(points, function))
    plots.add(createPlot(points, getInversePoints(values)))

    println(values)
    val data = mapOf (
        "x" to (0 until amount),
        "y" to values.modular()
    )

    var p = createPlot(data) +
            labs(title = "Dft amplitudes", x = "N", y="|F[n]|")
    plots.add(p)
    plots.forEachIndexed{ i,plot ->
        bunch.addPlot(plot, offsetX, offsetY+ PLOT_HEIGHT*i)
    }

    ggsave(bunch, "plot.html", path="plots")
    return File("plots/plot.html").readText()
}

fun getPoints(amount: Int): List<Complex> {

    //vector - axis X
    val vector= formSequenceVector(amount){x-> cos(2*x)+sin(5*x)}
    val matrix=generateMatrix(amount)
    return createComplexFrequencies(vector, matrix, amount)
}

fun getInversePoints(frequencies: List<Complex>): List<Double> {
    val matrix= generateInverseMatrix(frequencies.size)
    return createSourceValues(frequencies, matrix, frequencies.size).also { println(it) }
}