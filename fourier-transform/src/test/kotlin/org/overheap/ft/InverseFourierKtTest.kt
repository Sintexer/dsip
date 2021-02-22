package org.overheap.ft

import kscience.kmath.operations.Complex

import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.math.*

class InverseFourierKtTest {


    @Test
    fun testCreateSourceValues() {
        val amount=32
        val vector= formSequenceVector(amount){x-> cos(2*x)+sin(5*x)}

        val DELTA = 0.0001
        val frequencies = createComplexFrequencies(vector, generateMatrix(amount), amount)
        val source = listOf<Double>()
        val matrix = generateInverseMatrix(amount)
       println(createSourceValues(frequencies, matrix, amount))

//        assertEquals(createSourceValues(frequencies, matrix, amount).toDoubleArray(),
//        vector.toDoubleArray(), DELTA)
    }

}