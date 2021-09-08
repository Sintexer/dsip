package by.overheap.lab1

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class FileProcess {

    val jpg = "jpg"

    fun readMat(path: String): Mat {
        return Imgcodecs.imread(path)
    }

    @Throws(IOException::class)
    fun write(bufferedImage: BufferedImage?, filename: String?) {
        if (bufferedImage != null) {
            val outputfile = File(filename)
            ImageIO.write(bufferedImage, jpg, outputfile)
        } else {
            println("Not write to file")
        }
    }

    @Throws(Exception::class)
    fun write(mat: Mat?, fileName: String?) {
        val bufferedImage  = CvUtils.mat2BufferedImage(mat)
        this.write(bufferedImage, fileName)
    }
}