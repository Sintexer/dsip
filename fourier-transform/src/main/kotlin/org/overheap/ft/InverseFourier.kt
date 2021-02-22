package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kotlin.math.PI

fun generateInverseMatrix(size: Int): Array<Array<Complex>> {
    val matrix: Array<Array<Complex>> = Array(size) { Array(size) { Complex(0, 0) } }

    for (i in 0 until size) {
        for (j in 0 until size) {
            var complex = Complex(0, 1)
            complex = ComplexField.multiply(complex, 2 * PI * i * j / size)
            matrix[i][j] = kscience.kmath.operations.exp(complex)
        }
    }
    return matrix
}

fun createSourceValues(frequencies: List<Double>, matrix: Array<Array<Complex>>, size: Int): List<Double> {
  //  val l=createFrequencies(frequencies, matrix, size)
    return createFrequencies(frequencies, matrix, size).map{it/size}
}