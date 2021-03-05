package org.overheap.ft

import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val amount=8
    val matrix= createConvolutionMatrix(amount) { x -> cos (2* x) }
    val vector= createConvolutionVector(amount){ x -> sin (5* x) }
   println(createConvolutionResult(matrix, vector))
}

fun createConvolutionMatrix(amount: Int, f: (Double) -> Double): List<List<Double>>{
    val orderedValues = formValueSequence(amount, f)
    return (0 until amount).map { i ->
        (0 until amount).map { j ->
            if (i + j >= amount) orderedValues[i + j-amount]
            else orderedValues[i + j]
        }
    }
}

fun createConvolutionVector(amount: Int, f: (Double) -> Double):List<Double> {
    val list= formValueSequence(amount, f).toDoubleArray()
    list.reverse(1, amount)
    return list.toList()
}

fun createConvolutionResult(matrix:List<List<Double>>, vector: List<Double>):List<Double> {
    return multiply(matrix, vector).map { it/vector.size }
}

fun multiply(matrix:List<List<Double>>, vector: List<Double>):List<Double> {
    return (matrix.indices).map { i ->
        vector.zip(matrix[i])
            .map { pair -> pair.second * pair.first }
            .fold(0.0, Double::plus)
    }
}
