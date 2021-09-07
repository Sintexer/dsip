package by.overheap.lab1.controller

data class ImageData(
    val redPixels: List<Int>,
    val greenPixels: List<Int>,
    val bluePixels: List<Int>,
    val processedRedPixels: List<Int>,
    val processedGreenPixels: List<Int>,
    val processedBluePixels: List<Int>,
)
