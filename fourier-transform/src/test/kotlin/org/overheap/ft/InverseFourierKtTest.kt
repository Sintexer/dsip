package org.overheap.ft

import kscience.kmath.operations.Complex

import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.math.*

class InverseFourierKtTest {

    @Test
    fun testCreateSourceValues() {
        val DELTA = 0.0001
        val size = 32
        val frequencies =
            listOf<Double>(
                0.0, 0.0, 16.0, 0.0, 0.0, 16.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 16.0, 0.0, 0.0, 16.0, 0.0
            )
        val source = listOf<Double>()
        val matrix = generateMatrix(size)
        println(createSourceValues(frequencies, matrix, size))
    

//        Assert.assertEquals(
//            createSourceValues(frequencies, matrix, size).toDoubleArray(),
//            expected,
//            DELTA
//        )
    }


}