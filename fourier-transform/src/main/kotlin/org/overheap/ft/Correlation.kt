package org.overheap.ft

import kscience.kmath.operations.Complex
import kscience.kmath.operations.conjugate
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val amount = 8
    val matrix = Correlation.createCorrelationMatrix(amount) { x -> cos(2 * x) }
    val vector = Correlation.createCorrelationVector(amount) { x -> sin(5 * x) }
    println(Correlation.createCorrelationResult(matrix, vector))

    val f1={x:Double -> cos(2 * x)}
    println(Correlation.fftCorrelation(amount, f1){ x -> sin(5 * x) })
}

object Correlation {
//    fun createCorrelationMatrix(amount: Int, f: (Double) -> Double): List<List<Double>> {
//        val orderedValues = formValueSequence(amount, f) + List(amount - 1) { 0.0 }
//        val size = orderedValues.size
//        return (0 until size).map { i ->
//            orderedValues.subList(size - i, size) + orderedValues.subList(0, size - i)
//        }
//    }
//
//    fun createCorrelationVector(amount: Int, f: (Double) -> Double): List<Double> {
//        return   formValueSequence(amount, f)  + List(amount - 1) { 0.0 }
//    }
//
    fun createCorrelationResult(matrix: List<List<Double>>, vector: List<Double>): List<Double> {
        return Convolution.createResult(matrix, vector)
    }

    fun createCorrelationVector(amount: Int, f: (Double) -> Double): List<Double> {
        return formValueSequence(amount, f)
    }

    fun createCorrelationMatrix(amount: Int, f: (Double) -> Double): List<List<Double>> {
        val orderedValues = formValueSequence(amount, f)
        val size = orderedValues.size
        return (0 until size).map { i ->
            orderedValues.subList(size - i, size) + orderedValues.subList(0, size - i)
        }
    }
    
    fun fftCorrelation(amount: Int, f1: (Double) -> Double, f2: (Double) -> Double): List<Double> {
        val inverseVector1= formValueSequence(amount, f1).map { Complex(it, 0).conjugate }
        val vector2= formValueSequence(amount,f2).map { Complex(it, 0) }
        return fftOperation(inverseVector1, vector2)
    }
}

fun fftOperation(vector1: List<Complex>, vector2:List<Complex> ) : List<Double> {
    val multiplicationList=vector1
        .zip(vector2)
        .map { pair-> (pair.second*pair.first) }
    return FFT.ifft(multiplicationList)
}