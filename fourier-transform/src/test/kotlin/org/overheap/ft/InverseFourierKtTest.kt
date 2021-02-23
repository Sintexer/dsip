package org.overheap.ft

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import kotlin.math.cos
import kotlin.math.sin

class InverseFourierKtTest {


    @Test
    fun testCreateSourceValues() {
        val amount = 32
        val vector = formSequenceVector(amount) { x -> cos(2 * x) + sin(5 * x) }

        val DELTA = 0.0001
        val frequencies = createSpectrum(vector, generateMatrix(amount), amount)

        val matrix = generateInverseMatrix(amount)
        assertEquals(
            createSourceValues(frequencies, matrix, amount).toDoubleArray(),
            vector.toDoubleArray(), DELTA
        )
    }

}