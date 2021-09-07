package by.overheap.lab1

import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class CvUtils {

    companion object {

        @Throws(Exception::class)
        fun mat2BufferedImage(matrix: Mat?): BufferedImage? {
            val mob = MatOfByte()
            Imgcodecs.imencode(".jpg", matrix, mob)
            val ba = mob.toArray()
            return ImageIO.read(ByteArrayInputStream(ba))
        }
    }

}