package by.daryazalevskaya.fwt

import kotlin.math.*


object Fwt {

    fun formValueSequence(amount: Int, f: (Double) -> Double) = (0 until amount).map { f(it * 2 * PI / amount) }

    fun buildFwt(values: List<Double>): List<Double> {
        var result = values.toMutableList()
        val n = values.size
        for (i in 0 until log2(n.toDouble()).toInt()) {
            val temp = result.toMutableList()
            val nextBlockSize = n / 2.0.pow(i + 1).toInt()
            for (j in values.indices) {
                if (j % (nextBlockSize * 2) < nextBlockSize) temp[j] = result[j] + result[nextBlockSize + j]
                else temp[j] = result[j - nextBlockSize] - result[j]
            }
            result = temp.toMutableList()
        }

        return result.map { it.div(values.size) }
    }
}

fun main() {
    print(Fwt.buildFwt(Fwt.formValueSequence(32) { x -> cos(2 * x) + sin(5 * x) }))
}