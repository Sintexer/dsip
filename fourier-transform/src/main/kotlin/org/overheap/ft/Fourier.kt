package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.r
import kotlin.math.PI


fun formSequenceVector(f: (Double) -> Double, amount: Int): List<Double> {
    val results = mutableListOf<Double>()
    for (i in 0 until amount) {
        val x = i * 2 * PI / amount
        results.add(f(x))
    }
    return results
}

fun generateMatrix(size: Int): Array<Array<Complex>> {
    val matrix: Array<Array<Complex>> = Array(size) { Array(size) { Complex(0, 0) } }

    for (i in 0 until size) {
        for (j in 0 until size) {
            var complex = Complex(0, -1)
            var pow=(-1) * 2 * PI * i * j / size;
            complex = ComplexField.multiply(complex,  2 * PI * i * j / size)
            matrix[i][j] = kscience.kmath.operations.exp(complex)
        }
    }
    return matrix
}


fun createFrequencies(sequence: List<Double>, matrix: Array<Array<Complex>>, size: Int): List<Double> {
    val result = mutableListOf<Double>()
    for (i in 0 until size) {
        val resultCell = sequence
            .zip(matrix[i])
            .map { pair -> pair.second * pair.first }
            .fold(Complex(0, 0)) { acc, value ->
                ComplexField.add(acc, value)
            }
            .r
        result.add(resultCell)
    }
    return result
}



