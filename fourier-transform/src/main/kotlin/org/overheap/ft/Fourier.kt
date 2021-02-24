package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.exp
import kscience.kmath.operations.r
import kotlin.math.PI

fun formDftFrequencies(amount: Int, f: (Double) -> Double): List<Complex> {
    val values = formValueSequence(amount, f)
    val matrix = createDftMatrix(amount)
    return createComplexFrequencies(values, matrix)
}

fun inverseDft(frequencies: List<Complex>): List<Double> {
    val matrix = createInverseDftMatrix(frequencies.size)
    return createSourceValues(frequencies, matrix, frequencies.size).also { println(it) }
}

fun formValueSequence(amount: Int, f: (Double) -> Double) = (0 until amount).map { f(it*2*PI/amount) }

fun createDftMatrix(size: Int) = createMatrix(size, Complex(0, -1))

fun createInverseDftMatrix(size: Int) = createMatrix(size, Complex(0, 1))

private fun createMatrix(size: Int, base: Complex): List<List<Complex>> {
    val pow = 2 * PI / size
    return (0 until size).map { i ->
        (0 until size).map { j ->
            exp(base * i * j * pow)
        }
    }
}

fun createComplexFrequencies(sequence: List<Double>, matrix: List<List<Complex>>) =
    multiply(sequence.map{Complex(it, 0)}, matrix)

fun multiply(sequence: List<Complex>, matrix: List<List<Complex>>): List<Complex> {
    return (matrix.indices).map{i ->
        sequence.zip(matrix[i])
            .map { pair -> pair.second * pair.first }
            .fold(Complex(0, 0), ComplexField::add)
    }
}

fun Iterable<Complex>.modular() = this.map { it.r }


