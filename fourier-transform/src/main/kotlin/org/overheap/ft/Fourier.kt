package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.r
import kscience.kmath.operations.theta
import kotlin.math.PI

fun formSequenceVector(amount: Int, f: (Double) -> Double): List<Double> {
    val pow = 2 * PI / amount
    return (0 until amount).map { f(it * pow) }
}

fun generateMatrix(size: Int): Array<Array<Complex>> {
    val matrix: Array<Array<Complex>> = Array(size) { Array(size) { Complex(0, 0) } }

    for (i in 0 until size) {
        for (j in 0 until size) {
            var complex = Complex(0, -1)
            complex = ComplexField.multiply(complex, 2 * PI * i * j / size)
            matrix[i][j] = kscience.kmath.operations.exp(complex)
        }
    }
    return matrix
}


fun createAmplitudes(sequence: List<Double>, matrix: Array<Array<Complex>>, size: Int): List<Double> {
    return createSpectrum(sequence, matrix, size).map { it.r }
}

fun createPhases(sequence: List<Double>, matrix: Array<Array<Complex>>, size: Int): List<Double> {
    return createSpectrum(sequence, matrix, size).map { it.theta }
}

fun createSpectrum(sequence: List<Double>, matrix: Array<Array<Complex>>, size: Int): List<Complex> {
    return (0 until size).map{i ->
        sequence.zip(matrix[i])
            .map { pair -> pair.second * pair.first }
            .fold(Complex(0, 0), ComplexField::add)
    }
}





