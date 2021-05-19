package by.daryazalevskaya.filter

import kscience.kmath.operations.Complex

object Convolution {

    fun fftConvolution(amount: Int, f1: (Double) -> Double, f2: (Double) -> Double): List<Double> {
        val vector1 = formFunctionValues(amount, f1).map { Complex(it, 0) }
        val vector2 = formFunctionValues(amount, f2).map { Complex(it, 0) }
        return fftOperation(vector1, vector2)
    }

    fun fftConvolution(values1: List<Double>, values2: List<Double>): List<Double> {
        val vector1 = values1.map { Complex(it, 0) }
        val vector2 = values2.map { Complex(it, 0) }
        return fftOperation(vector1, vector2)
    }

    private fun fftOperation(vector1: List<Complex>, vector2: List<Complex>): List<Double> {
        val multiplicationList = vector1
            .zip(vector2)
            .map { pair -> (pair.second * pair.first) }
        return FFT.ifft(multiplicationList)
    }

}