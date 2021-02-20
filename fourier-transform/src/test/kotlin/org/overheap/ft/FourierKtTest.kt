package org.overheap.ft

import org.testng.annotations.DataProvider

import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class FourierKtTest {

    @Test
    fun testFormSequenceVector() {
        val function: (Double) -> Double = { x -> cos(3 * x) + sin(2 * x) }
        val amount = 4
        val vector = formSequenceVector(amount, function)
        val resultList = listOf(1.0, 0.0, -1.0, 0.0)
        val pairList = vector.zip(resultList)
        assertTrue(pairList.all { (el1, el2) -> abs(el1 - el2) <= 0.00001 })
    }

    @Test
    fun testFormSequenceVector1() {
        val function: (Double) -> Double = { x -> cos(3 * x) + sin(2 * x) }
        val amount = 8
        val vector = formSequenceVector(amount, function)
        val cos45 = sqrt(2.0) / 2;
        val resultList = listOf(1.0, -cos45 + 1.0, 0.0, cos45 - 1.0, -1.0, cos45 + 1.0, 0.0, -cos45 - 1.0)
        val pairList = vector.zip(resultList)
        assertTrue(pairList.all { (el1, el2) -> abs(el1 - el2) <= 0.00001 })
    }

//    val DELTA = 0.001
//
//    @DataProvider(name = "valuesData")
//    fun valuesData(): Iterator<Array<Any>> {
//        val x: Double = 2.0 * PI / 5
//        return arrayListOf(
//            arrayOf(
//                5,
//                { num: Double -> num },
//                doubleArrayOf(0.0, x, 2 * x, 3 * x, 4 * x)
//            ),
//            arrayOf(
//                5,
//                { num: Double -> num * 2 },
//                doubleArrayOf(0.0, x * 2, 4 * x, 6 * x, 8 * x)
//            )
//        ).iterator()
//    }
//
//    @Test(dataProvider = "valuesData")
//    fun testFormSequenceVector(times: Int, f: (Double)-> Double, expected: DoubleArray ) {
//        assertEquals(
//            formSequenceVector(times, f).toDoubleArray(),
//            expected,
//            DELTA
//        )
//    }
//
//    @Test
//    fun testGenerateMatrix() {
//    }
//
//    @Test
//    fun testCreateFrequencies() {
//    }
}
