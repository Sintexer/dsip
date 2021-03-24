package org.overheap.ft

fun multiply(matrix: List<List<Double>>, vector: List<Double>): List<Double> {
    return (matrix.indices).map { i ->
        vector.zip(matrix[i])
            .map { pair ->
                pair.second * pair.first
            }
            .sum()
    }
}