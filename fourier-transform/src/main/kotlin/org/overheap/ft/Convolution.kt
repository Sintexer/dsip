package org.overheap.ft

import kscience.kmath.operations.Complex
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val amount = 32

    val v = Convolution.createCyclicConvolutionVector(amount) { x -> cos(2 * x) }
    val m = Convolution.createCyclicConvolutionMatrix(amount) { x -> sin(5 * x) }
    println(Convolution.createConvolutionResult(m, v))

    val f1 = { x: Double -> cos(2 * x) }
    println(Convolution.fftConvolution(amount, f1) { x -> sin(5 * x) })
}

object Convolution {

//    fun createLinearConvolutionMatrix(amount: Int, f: (Double) -> Double): List<List<Double>> {
//        val orderedValues = formValueSequence(amount, f).reversed() + List(amount - 1) { 0.0 }
//        val size = orderedValues.size
//        return (0 until size).map { i ->
//            orderedValues.subList(size - i, size) + orderedValues.subList(0, size - i)
//        }
//    }
//
//    fun createLinearConvolutionVector(amount: Int, f: (Double) -> Double): List<Double> {
//        return (List(amount - 1) { 0.0 } + formValueSequence(amount, f))
//    }

    fun createConvolutionResult(matrix: List<List<Double>>, vector: List<Double>): List<Double> {
        return multiply(matrix, vector)
            .map { it / vector.size }
    }


    fun createCyclicConvolutionVector(amount: Int, f: (Double) -> Double): List<Double> {
        val vector = formValueSequence(amount, f).toDoubleArray()
        vector.reverse(1, amount)
        return vector.toList()
    }

    fun createCyclicConvolutionMatrix(amount: Int, f: (Double) -> Double): List<List<Double>> {
        val orderedValues = formValueSequence(amount, f)
        val size = orderedValues.size
        return (0 until size).map { i ->
            orderedValues.toList().subList(i, size) + orderedValues.toList().subList(0, i)
        }
    }

    fun fftConvolution(amount: Int, f1: (Double) -> Double, f2: (Double) -> Double): List<Double> {
        val vector1 = FFT.fft(formValueSequence(amount, f1).map { Complex(it, 0) })
        val vector2 = FFT.fft(formValueSequence(amount, f2).map { Complex(it, 0) })
        return fftOperation(vector1, vector2)
    }

}
