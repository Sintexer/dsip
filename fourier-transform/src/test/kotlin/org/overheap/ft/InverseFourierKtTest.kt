package org.overheap.ft

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
        println(frequencies )
        val source = listOf<Double>()
        val matrix = generateInverseMatrix(amount)
        println(createSourceValues(frequencies, matrix, amount))

//        assertEquals(createSourceValues(frequencies, matrix, amount).toDoubleArray(),
//        vector.toDoubleArray(), DELTA)
    }

}