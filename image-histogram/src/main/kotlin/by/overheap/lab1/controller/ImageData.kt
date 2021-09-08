package by.overheap.lab1.controller

data class ImageData(
    val source: PixelsData = PixelsData(),
    val min: PixelsData = PixelsData(),
    val minMax: PixelsData = PixelsData(),
    val max: PixelsData = PixelsData()
)
