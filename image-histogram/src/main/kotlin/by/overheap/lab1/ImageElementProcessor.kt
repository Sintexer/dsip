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
        var negativeArr = ByteArray(src.channels() * src.rows() * src.cols())
        src.get(0,0, negativeArr)
        negativeArr = negativeArr.map { (255 - it).toByte() }.toByteArray()
        negativeMat.put(0, 0, negativeArr)
        return negativeMat
    }

    fun channelDivider(src: Mat): List<List<Int>> {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        return matChannels.map { channel ->
            val pixels = ByteArray(channel.rows() * channel.cols())
            channel.get(0, 0, pixels)
            //pixels.forEach { println(it) }
            pixels.map { it.toUByte().toInt() }

        }
    }

    fun minFilter(src: Mat): Mat {

        return filterByLambdaResult(src) { arr -> arr.minOrNull()!! }
    }

    fun maxFilter(src: Mat): Mat {

        return filterByLambdaResult(src) { arr -> arr.maxOrNull()!! }
    }

    fun maxMinFilter(src: Mat): Mat {

        val mins = minFilter(src)
        return maxFilter(mins)
    }

    private fun filterByLambdaResult(src: Mat, f: (DoubleArray) -> Double): Mat {

        val matChannels = mutableListOf<Mat>()
        Core.split(src, matChannels)
        matChannels.forEach {

            for (i in 1 until it.rows() - 1) {
                for (j in 1 until it.cols() - 1) {
                    val neighbours = arrayOf(
                        it.get(i - 1, j - 1)[0], it.get(i - 1, j)[0], it.get(i - 1, j + 1)[0],
                        it.get(i, j - 1)[0], it.get(i, j)[0], it.get(i, j + 1)[0],
                        it.get(i + 1, j)[0], it.get(i + 1, j)[0], it.get(i, j + 1)[0]
                    ).toDoubleArray()
                    val lambdaResult = f(neighbours)
                    it.put(i, j, lambdaResult)
                }
            }
        }

        Core.merge(matChannels, src)
        return src
    }

}