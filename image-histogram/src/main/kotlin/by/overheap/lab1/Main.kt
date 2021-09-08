package by.overheap.lab1

import by.overheap.lab1.view.HistogramsView
import tornadofx.App
import tornadofx.launch

class HistogramApp: App(HistogramsView::class)

fun main(args: Array<String>) {
    System.load("D:/Program Files/opencv/build/java/x64/opencv_java453.dll")
    launch<HistogramApp>(args)
}