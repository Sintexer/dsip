package org.overheap.ft

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respond(
                    TextContent(
                        createHtml() { x ->
                            cos(2 * x) + sin(5 * x)
                        },
                        ContentType.Text.Html
                    )
                )
            }
        }
    }.start(wait = true)
}