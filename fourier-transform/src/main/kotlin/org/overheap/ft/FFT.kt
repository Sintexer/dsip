package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.conjugate
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val amount=32
   // FFT.fft(formValueSequence(amount) { x -> cos(2 * x) + sin(5 * x) }.map { Complex(it, 0) }).println()
    println(formValueSequence(amount) { x -> cos(2 * x) + sin(5 * x) })
    //println(FFT.ifft(FFT.fft(formValueSequence(amount) { x -> cos(2 * x) + sin(5 * x) }.map { Complex(it, 0) }), amount))
}

object FFT {
    fun fft(vector: List<Complex>): List<Complex> {
        steps = 0
        return fftRecursive(vector)
    }

    var steps = 0

    private fun fftRecursive(vector: List<Complex>): List<Complex> {
        val n = vector.size
        if (n < 2)
            return vector

        var (evens, odds) = vector.indices
            .groupBy { it % 2 }
            .map { it.value.map { vector[it] } }

        evens = fftRecursive(evens)
        odds = fftRecursive(odds)

        val pairs = (0 until n / 2).map {
            ++steps
            val w = Complex(cos(it * 2 * PI / n), sin(it * 2 * PI / n))
            val offset = odds[it] * w
            val base = evens[it]
            listOf(base + offset, base - offset)
        }

        val (left, right) = pairs.flatMap { it }
            .withIndex()
            .partition { it.index % 2 == 0 }
        return left.map { it.value } + right.map { it.value }
    }

    fun ifft(vector: List<Complex>): List<Double> {
        val inverseVector = vector.map { it.conjugate }
        return fft(inverseVector).map { it.re / vector.size }
    }
}

private operator fun Complex.plus(double: Double) = this + Complex(double, 0)



