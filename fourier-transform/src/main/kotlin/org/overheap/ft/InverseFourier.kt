package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.ComplexField
import kscience.kmath.operations.r
import kotlin.math.PI

//fun createSourceValues(frequencies: List<Complex>, matrix: List<List<Complex>>, size: Int): List<Double> {
//    return (0 until size).map{i ->
//        frequencies.zip(matrix[i])
//            .map { pair -> pair.second * pair.first }
//            .fold(Complex(0, 0), ComplexField::add)
//            .re/size
//    }
//}