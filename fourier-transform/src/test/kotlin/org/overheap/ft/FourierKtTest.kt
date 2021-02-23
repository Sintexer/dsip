package org.overheap.ft

import kscience.kmath.operations.Complex
import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider

import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import kotlin.math.*

class FourierKtTest {

    val DELTA = 0.001;

    @DataProvider(name = "vectors")
    fun vectorsData(): Array<Array<Any>> {
        val cos45 = sqrt(2.0) / 2;
        return arrayOf(
            arrayOf(
                4,
                { x: Double -> cos(3 * x) + sin(2 * x) },
                listOf(1.0, 0.0, -1.0, 0.0)
            ),
            arrayOf(
                8,
                { x: Double -> cos(3 * x) + sin(2 * x) },
                listOf(1.0, -cos45 + 1.0, 0.0, cos45 - 1.0, -1.0, cos45 + 1.0, 0.0, -cos45 - 1.0)
            )
        )
    }

    @Test(dataProvider = "vectors")
    fun testFormSequenceVector1(amount: Int, f: (Double) -> Double, expected: List<Double>) {
        val vector = formSequenceVector(amount, f)
        val pairList = vector.zip(expected)
        assertTrue(pairList.all { (el1, el2) -> abs(el1 - el2) <= DELTA })
    }

    @DataProvider(name = "valuesData")
    fun valuesData(): Array<Array<Any>> {
        val x: Double = 2.0 * PI / 5
        return arrayOf(
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
        )
    }

    @Test(dataProvider = "valuesData")
    fun testFormSequenceVector(times: Int, f: (Double) -> Double, expected: DoubleArray) {
        assertEquals(
            formSequenceVector(times, f).toDoubleArray(),
            expected,
            DELTA
        )
    }

    @DataProvider(name = "matrix")
    fun matrices(): Array<Array<Any>> {
        val cos45 = sqrt(2.0) / 2;
        return arrayOf(
            arrayOf(
                4, arrayOf(
                    arrayOf(Complex(1, 0), Complex(1, 0), Complex(1, 0), Complex(1, 0)),
                    arrayOf(Complex(1, 0), Complex(0, -1), Complex(-1, 0), Complex(0, 1)),
                    arrayOf(Complex(1, 0), Complex(-1, 0), Complex(1, 0), Complex(-1, 0)),
                    arrayOf(Complex(1, 0), Complex(0, 1), Complex(-1, 0), Complex(0, -1))
                )
            ),
        )
    }

    @Test(dataProvider = "matrix")
    fun testGenerateMatrix(amount: Int, expected: Array<Array<Complex>>) {
        val matrix = generateMatrix(amount)
        for (i in 0 until amount) {
            for (j in 0 until amount) {
                assertEquals(matrix[i][j].re, expected[i][j].re, DELTA)
                assertEquals(matrix[i][j].im, expected[i][j].im, DELTA)
            }
        }
    }

    @DataProvider(name = "frequencies")
    fun frequencies(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(
                4, arrayOf(
                    arrayOf(Complex(1, 0), Complex(1, 0), Complex(1, 0), Complex(1, 0)),
                    arrayOf(Complex(1, 0), Complex(0, -1), Complex(-1, 0), Complex(0, 1)),
                    arrayOf(Complex(1, 0), Complex(-1, 0), Complex(1, 0), Complex(-1, 0)),
                    arrayOf(Complex(1, 0), Complex(0, 1), Complex(-1, 0), Complex(0, -1))
                ),
                listOf(1.0, 0.0, -1.0, 0.0),
                doubleArrayOf(0.0, 2.0, 0.0, 2.0)
            ),
        )
    }

    @Test(dataProvider = "frequencies")
    fun testCreateFrequencies(size: Int, matrix: Array<Array<Complex>>, sequence: List<Double>, expected: DoubleArray) {
        assertEquals(
            createAmplitudes(sequence, matrix, size).toDoubleArray(),
            expected,
            DELTA
        )
    }
}
