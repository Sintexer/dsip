package org.overheap.ft

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.lets_plot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.cos
import kotlin.math.sin

fun main() {
   // val function: (Double) -> Double = {x-> 5+2* sin(2*x*PI) +3*cos(4*PI*x)}
    // val amount=4;
    embeddedServer(Netty, port = 8080){
        routing {
//            static {
//                default("lets-plot-images/plot.html")
//            }
            get("/"){call.respond(TextContent(createHtml(), ContentType.Text.Html))}
        }

    }.start(wait = true)

//    val function: (Double) -> Double = {x-> cos(2*x)+sin(5*x)}
//    val amount=32;
//
//    //vector - axis X
//    val vector= formSequenceVector(function, amount)
//    val matrix=generateMatrix(amount);
//    val frequencies=createFrequencies(vector, matrix, amount);
//
//    //vector - axis Y
//    frequencies.forEach { f-> print(f) }
}

fun createHtml(): String{
    val points = getPoints().also{println(it)}
    val function: (Double) -> Double = {x-> cos(2*x)+sin(5*x)}
    val amount=32

    //vector - axis X
    val vector= formSequenceVector (amount, function)
    val data = mapOf (
        "rating" to List<String>(points.size){"A"},
        "cond" to points
    )

    var p = lets_plot(data) +
            geom_density { x = "rating"; color = "cond" } + ggsize(900, 550)
    ggsave(p, "plot.html", path="plots")
    return File("plots/plot.html").readText()
}

fun getPoints(): List<Double> {
    val amount=32

    //vector - axis X
    val vector= formSequenceVector(amount){x-> cos(2*x)+sin(5*x)}
    val matrix=generateMatrix(amount)
    return createFrequencies(vector, matrix, amount)

}