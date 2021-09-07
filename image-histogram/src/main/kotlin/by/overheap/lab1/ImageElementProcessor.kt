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
        var negativeArr = IntArray(src.channels() * src.rows() * src.cols())
        negativeArr = negativeArr.map { 255 - it }.toIntArray()
        negativeMat.put(0, 0, negativeArr)
        return negativeMat
    }

    fun channelDivider(src: Mat): List<List<Int>> {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        return matChannels.map { channel ->
            channel
                .get(0, 0)
                .toList()
                .map { it.toInt() }
        }
    }

    fun minFilter(src: Mat): Mat {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        matChannels.forEach {

            for (i in 1 until it.rows() - 1) {
                for (j in 1 until it.cols() - 1) {
                    val neighbours = arrayOf(
                        it.get(i - 1, j - 1)[0], it.get(i - 1, j)[0], it.get(i - 1, j + 1)[0],
                        it.get(i, j - 1)[0], it.get(i, j)[0], it.get(i, j + 1)[0],
                        it.get(i + 1, j)[0], it.get(i + 1, j)[0], it.get(i, j + 1)[0]
                    )
                    it.put(i, j, neighbours.minOrNull()!!)
                }
            }
        }

        Core.merge(matChannels, src)
        return src
    }

    fun maxFilter(src: Mat): Mat {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        matChannels.forEach {

            for (i in 1 until it.rows() - 1) {
                for (j in 1 until it.cols() - 1) {
                    val neighbours = arrayOf(
                        it.get(i - 1, j - 1)[0], it.get(i - 1, j)[0], it.get(i - 1, j + 1)[0],
                        it.get(i, j - 1)[0], it.get(i, j)[0], it.get(i, j + 1)[0],
                        it.get(i + 1, j)[0], it.get(i + 1, j)[0], it.get(i, j + 1)[0]
                    )
                    it.put(i, j, neighbours.maxOrNull()!!)
                }
            }
        }

        Core.merge(matChannels, src)
        return src
    }

    fun maxMinFilter(src : Mat) : Mat {

        val mins = minFilter(src)
        return maxFilter(mins)
    }

}