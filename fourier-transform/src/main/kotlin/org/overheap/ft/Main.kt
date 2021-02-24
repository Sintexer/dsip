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
import kotlin.math.cos
import kotlin.math.sin

fun Iterable<*>.println() = println(joinToString(prefix = "[", postfix = "]"))

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respond(
                    TextContent(
                        createPlotsPage(32) { x ->
                            cos(2 * x) + sin(5 * x)
                        },
                        ContentType.Text.Html
                    )
                )
            }
        }
    }.start(wait = true)
}