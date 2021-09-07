package by.overheap.lab1.histogram

fun createHistogramColumns(pixels: List<Int>): List<Map.Entry<Int, Int>> {
    return pixels.groupingBy { it }
        .eachCount()
        .entries
        .sortedBy { it.key }
}