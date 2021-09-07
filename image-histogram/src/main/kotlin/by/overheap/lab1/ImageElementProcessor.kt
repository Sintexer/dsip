package by.overheap.lab1

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ImageElementProcessor {

    fun toBlackWhite(src: Mat): Mat {

        val blackWhiteMat = Mat()
        Imgproc.threshold(src, blackWhiteMat, 100.0, 255.0, Imgproc.THRESH_BINARY or Imgproc.THRESH_OTSU)
        return blackWhiteMat
    }

    fun toNegative(src: Mat): Mat {

        val negativeMat = Mat(src.rows(), src.cols(), src.type())
        val negativeArr = IntArray(src.channels() * src.rows() * src.cols())
        negativeArr.map { 255 - it }
        negativeMat.put(0, 0, negativeArr)
        return negativeMat
    }

    fun channelDivider(src: Mat): List<List<Int>> {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        return matChannels.map { channel ->
            val pixels = ByteArray(channel.rows()*channel.cols())
            channel.get(0, 0, pixels)
            pixels.map{ it.toUByte().toInt() }
        }
    }

}