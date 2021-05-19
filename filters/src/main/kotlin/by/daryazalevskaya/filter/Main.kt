package by.daryazalevskaya.filter

import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val n = 256
    Plots.createPlotsPage(n) { x -> cos(2 * x) + sin(5 * x) + sin(50 * x) }
}