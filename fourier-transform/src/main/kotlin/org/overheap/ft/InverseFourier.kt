package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.r
import kotlin.math.PI


fun createSourceValues(amplitudes: List<Complex>, matrix: List<List<Complex>>)=
    multiply(amplitudes, matrix).map { it.re/amplitudes.size }


fun createInverseDftMatrix(size: Int) = createMatrix(size, Complex(0, 1))

fun inverseDft(frequencies: List<Complex>): List<Double> {
    val matrix = createInverseDftMatrix(frequencies.size)
    return multiply(frequencies, matrix).real().map { it / frequencies.size }
}

