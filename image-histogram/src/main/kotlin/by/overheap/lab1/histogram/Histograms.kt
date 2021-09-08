package by.overheap.lab1.histogram

fun createHistogramColumns(pixels: List<Int>, colsAmount: Int = 20): List<Map.Entry<Int, Int>> {
    return pixels.groupingBy { (it / 256.0 * colsAmount).toInt() + 1 }
        .eachCount()
        .entries
        .sortedBy { it.key }
}