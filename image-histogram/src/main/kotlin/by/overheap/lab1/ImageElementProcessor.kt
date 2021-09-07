package by.overheap.lab1

import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ImageElementProcessor {

    fun toBlackWhite(src: Mat) : Mat {

        val blackWhiteMat = Mat()
        Imgproc.threshold(src, blackWhiteMat, 100.0, 255.0, Imgproc.THRESH_BINARY or Imgproc.THRESH_OTSU)
        return blackWhiteMat
    }

    fun toNegative(src: Mat) : Mat {

        val negativeMat = Mat()
        src.convertTo(src, -1, -1.0, 255.0)
        return src
    }
}