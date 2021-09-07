package by.daryazalevskaya.filter

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object Filter {

    val M = 256;
    val fc = 0.4

    private fun unnormalizedImpulses(): List<Double> {
        val arr = (0 until M).map {
            if (it - M / 2 == 0) 2 * PI * fc
            else sin(2 * PI * fc * (it - M / 2)) / (it - M / 2)
        }.toMutableList()
        (0 until M).forEach { arr[it] *= hammingWindow(it) }
        return arr
    }

    private fun hammingWindow(index: Int): Double {
        return 0.54 - 0.46 * cos(2 * PI * index / M)
    }

    private fun gainFilterCoefficients(values: List<Double>): List<Double> {
        val sum = values.sum()
        return values.map { it / sum }
    }

    fun impulses(): List<Double> {
        val unnormalizedImpulses = unnormalizedImpulses()
        return gainFilterCoefficients(unnormalizedImpulses)
    }

    fun fir(values: List<Double>): List<Double> {
        val h = impulses() + List<Double>(values.size-M){0.0}

   //     val result = MutableList(values.size) { 0.0 }

//        for (i in 0 until values.size) {
//            for (j in 0 until h.size-1) {
//                if (i-j>=0) {
//                    result[i]+=h[j]*values[i-j]
//                }
//            }
//        }

//
//        for (i in M until values.size) {
//            for (j in 0 until M) {
//                result[i] += values[i - j] * h[j]
//            }
//        }
//        return result
        return Convolution.fftConvolution(h, values)
    }

    fun iir(values: List<Double>): List<Double> {
        val f = 0.14
        val bw = 0.2
        val r = 1 - 3 * bw
        val k = (1 - 2 * r * cos(2 * PI * f) + r * r) / (2 - 2 * cos(2 * PI * f))

        val a0 = 1 - k
        val a1 = 2 * (k - r) * cos(2 * PI * f)
        val a2 = r * r - k
        val b1 = 2 * r * cos(2 * PI * f)
        val b2 = -(r * r)

        val result = MutableList(values.size) { 0.0 }
        (2 until values.size).forEach {
            result[it] =
                a0 * values[it] + a1 * values[it - 1] + a2 * values[it - 2] * +b1 * result[it - 1] + b2 * result[it - 2]
        }

        return result
    }


}