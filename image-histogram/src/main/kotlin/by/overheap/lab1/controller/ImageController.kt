package by.overheap.lab1.controller

import by.overheap.lab1.ImageElementProcessor
import org.opencv.imgcodecs.Imgcodecs
import tornadofx.Controller
import java.io.File

class ImageController : Controller() {

    fun processImage(imageFile: File): ImageData {
        val imageMatrix = Imgcodecs.imread(imageFile.path)
        val imageChannels = ImageElementProcessor().channelDivider(imageMatrix)
        val (reds, greens, blues) = imageChannels
        return ImageData(reds, greens, blues, listOf(), listOf(), listOf())
    }

}