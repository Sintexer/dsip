package by.overheap.lab1

import org.opencv.core.CvType

fun main() {

    System.load("D:/Program Files/opencv/build/java/x64/opencv_java453.dll")

    val imageElementProcessor = ImageElementProcessor()
    val fileProcess = FileProcess()

    var mat = fileProcess.readMat("max.jpg")
    //val neg = imageElementProcessor.toNegative(mat)
    //fileProcess.write(neg, "neg.jpg")

    //println(mat.dump())
//    fileProcess.write(mat, "grey-result.jpg")
    val max = imageElementProcessor.maxFilter(mat)

//    println("filter")
//    println(max.dump())
    fileProcess.write(max, "min-result.jpg")
}