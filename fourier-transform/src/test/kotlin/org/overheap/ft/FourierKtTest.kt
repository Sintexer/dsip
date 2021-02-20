package org.overheap.ft

import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.math.PI

class FourierKtTest {

    val DELTA = 0.001

    @DataProvider(name = "valuesData")
    fun valuesData(): Iterator<Array<Any>> {
        val x: Double = 2.0 * PI / 5
        return arrayListOf(
            arrayOf(
                5,
                { num: Double -> num },
                doubleArrayOf(0.0, x, 2 * x, 3 * x, 4 * x)
            ),
            arrayOf(
                5,
                { num: Double -> num * 2 },
                doubleArrayOf(0.0, x * 2, 4 * x, 6 * x, 8 * x)
            )
        ).iterator()
    }

    @Test(dataProvider = "valuesData")
    fun testFormSequenceVector(times: Int, f: (Double)-> Double, expected: DoubleArray ) {
        assertEquals(
            formSequenceVector(times, f).toDoubleArray(),
            expected,
            DELTA
        )
    }

    @Test
    fun testGenerateMatrix() {
    }

    @Test
    fun testCreateFrequencies() {
    }
}