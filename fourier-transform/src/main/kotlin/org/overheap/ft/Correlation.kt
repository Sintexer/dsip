package org.overheap.ft

import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val amount=8
    val matrix= createCorrelationMatrix(amount) { x -> cos (2* x) }
   val vector= createCorrelationVector(amount){ x -> sin (5* x) }
    println(createCorrelationResult(matrix, vector))
}

fun createCorrelationMatrix(amount: Int, f: (Double) -> Double): List<List<Double>> {
    val orderedValues = formValueSequence(amount, f)
   // val orderedValues= listOf<Double>(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
    return (0 until amount).map { i ->
        orderedValues.subList(amount-i, amount)+orderedValues.subList(0, amount-i)
    }
}

fun createCorrelationVector(amount: Int, f: (Double) -> Double): List<Double> {
    return formValueSequence(amount, f)
}

fun createCorrelationResult(matrix:List<List<Double>>, vector: List<Double>):List<Double> {
    return multiply(matrix, vector).map { it/vector.size }
}