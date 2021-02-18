package org.overheap.ft

import kotlin.math.cos
import kotlin.math.sin

fun main() {
   // val function: (Double) -> Double = {x-> 5+2* sin(2*x*PI) +3*cos(4*PI*x)}
    // val amount=4;

    val function: (Double) -> Double = {x-> cos(2*x)+sin(5*x)}
    val amount=32;

    //vector - axis X
    val vector= formSequenceVector(function, amount)
    val matrix=generateMatrix(amount);
    val frequencies=createFrequencies(vector, matrix, amount);

    //vector - axis Y
    frequencies.forEach { f-> print(f) }
}