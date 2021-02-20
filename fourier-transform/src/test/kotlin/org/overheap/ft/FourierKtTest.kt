package org.overheap.ft

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
        val vector = formSequenceVector(function, amount)
        val resultList = listOf(1.0, 0.0, -1.0, 0.0)
        val pairList=vector.zip(resultList)
        assertTrue(pairList.all { (el1, el2) -> abs(el1 - el2) <= 0.00001 })
    }

    @Test
    fun testFormSequenceVector1() {
        val function: (Double) -> Double = { x -> cos(3 * x) + sin(2 * x) }
        val amount = 8
        val vector = formSequenceVector(function, amount)
        val cos45 = sqrt(2.0) / 2;
        val resultList = listOf(1.0, -cos45 + 1.0, 0.0, cos45 - 1.0, -1.0, cos45 + 1.0, 0.0, -cos45 - 1.0)
        val pairList=vector.zip(resultList)
        assertTrue(pairList.all { (el1, el2) -> abs(el1 - el2) <= 0.00001 })
    }

    @Test
    fun testGenerateMatrix() {
    }

    @Test
    fun testCreateFrequencies() {
    }
}