package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.exp
import kscience.kmath.operations.pow
import java.lang.Math.pow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

//fun fft(vector: List<Double>): List<Double> {
//    if(vector.size<2)
//        return vector
//    (val odd, val even) = vector.withIndex().groupBy { it.index % 2 }
//        .map{it.value.map{it.value}}
//        .map(::fft)
//
//}

fun main() {
    FFT.fft(formValueSequence(32, {it}).map { Complex(it, 0) }).println()
}

object FFT {
    fun fft(vector: List<Complex>) = fftRecursive(vector, Complex(0.0, 2.0))
    fun ifft(vector: List<Complex>) = fftRecursive(vector, Complex(0.0, -2.0))

    private fun fftRecursive(vector: List<Complex>, direction: Complex): List<Complex> {
        if (vector.size < 2)
            return vector
        val n = vector.size
        var (evens, odds) = vector.indices
            .groupBy { it % 2 }
            .map { it.value.map { vector[it] } }

        evens = fftRecursive(evens, direction)
        odds = fftRecursive(odds, direction)

        val pairs = (0 until n / 2).map {
            val w = exp(it*2*PI/n)
            val offset = odds[it] * w
            val base = evens[it]
            listOf(base + offset, base - offset)
        }
        val (left, right) = pairs.flatMap { it }
            .withIndex()
            .partition { it.index % 2 == 0 }
        return left.map { it.value } + right.map { it.value }
    }
}

private operator fun Complex.plus(double: Double) = this + Complex(double, 0)
