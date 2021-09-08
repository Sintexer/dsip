package by.overheap.lab1.view

import by.overheap.lab1.controller.ImageController
import by.overheap.lab1.histogram.createHistogramColumns
import javafx.beans.property.SimpleBooleanProperty
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*
import java.io.File

class HistogramsView : View() {

    private val controller: ImageController by inject()

    private val redHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val greenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val blueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    private val minRedHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val minGreenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val minBlueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    private val minMaxRedHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val minMaxGreenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val minMaxBlueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    private val maxRedHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val maxGreenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    private val maxBlueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    private lateinit var sourceImageUrl: String
    private var isDisabledViewImage = SimpleBooleanProperty(true)

    override val root = vbox(20, alignment = Pos.CENTER) {
        prefHeight = 900.0
        spacer { }
        hbox(30, alignment = Pos.CENTER) {
            button("Choose file").action {
                val file = chooseFile(filters = arrayOf()).first()
                processImage(file)
            }
            button("Show source image") {
                isDisable = true
                disableWhen { isDisabledViewImage }
            }.action {
                find<ImageViewerFragment>(
                    mapOf(
                        ImageViewerFragment::imageUrl to sourceImageUrl,
                        ImageViewerFragment::windowName to "Source image"
                    )
                )
                    .openWindow(resizable = false)
            }
        }
        barchart("Source", CategoryAxis(), NumberAxis()) {
            series("R", redHistogramData)
            series("G", greenHistogramData)
            series("B", blueHistogramData)
            minHeight = 400.0
            minWidth = 1600.0
            animated = false
        }
        scrollpane {
            vbox(alignment = Pos.CENTER) {
                barchart("Min filtered", CategoryAxis(), NumberAxis()) {
                    series("R", minRedHistogramData)
                    series("G", minGreenHistogramData)
                    series("B", minBlueHistogramData)
                    minHeight = 400.0
                    minWidth = 1600.0
                    animated = false
                }
                barchart("Min-max filtered", CategoryAxis(), NumberAxis()) {
                    series("R", minMaxRedHistogramData)
                    series("G", minMaxGreenHistogramData)
                    series("B", minMaxBlueHistogramData)
                    minHeight = 400.0
                    minWidth = 1600.0
                    animated = false
                }
                barchart("Max filtered", CategoryAxis(), NumberAxis()) {
                    series("R", maxRedHistogramData)
                    series("G", maxGreenHistogramData)
                    series("B", maxBlueHistogramData)
                    minHeight = 400.0
                    minWidth = 1600.0
                    animated = false
                }
            }

        }

    }

    private fun processImage(file: File) {
        val imageData = controller.processImage(file)
        imageData.source.let { pixels ->
            redHistogramData.setAll(createHistogramColumns(pixels.red).map { it.toChartData() })
            greenHistogramData.setAll(createHistogramColumns(pixels.green).map { it.toChartData() })
            blueHistogramData.setAll(createHistogramColumns(pixels.blue).map { it.toChartData() })
        }
        sourceImageUrl = file.toURI().toURL().toString()
        isDisabledViewImage.set(false)
    }

}

class ImageViewerFragment : Fragment() {

    val imageUrl: String by param()
    val windowName: String by param()
    private var windowHeight: Double = 500.0
    private var windowWidth: Double = 500.0

    override val root = scrollpane {
        title = windowName
        maxHeight = 900.0
        maxWidth = 1800.0
        vbox {
            imageview(imageUrl, lazyload = false).also { i ->
                windowHeight = i.image.height
                windowWidth = i.image.width
            }
            prefHeight = windowHeight
            prefWidth = windowWidth
        }
    }
}

private fun Map.Entry<Int, Int>.toChartData() = XYChart.Data<String, Number>(key.toString(), value)