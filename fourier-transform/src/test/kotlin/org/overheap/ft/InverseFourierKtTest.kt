package org.overheap.ft


import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import kotlin.math.cos
import kotlin.math.sin

class InverseFourierKtTest {

    @Test
    fun testCreateSourceValues() {
        val amount=32
        val orig= formValueSequence(amount){ x-> cos(2*x)+sin(5*x)}
        val dft= FT.dft(amount){ x-> cos(2*x)+sin(5*x)}

        val DELTA = 0.0001
        val matrix = createInverseDftMatrix(amount)


        assertEquals(
            createSourceValues(dft, matrix).toDoubleArray(),
            orig.toDoubleArray(), DELTA
        )
    }

}