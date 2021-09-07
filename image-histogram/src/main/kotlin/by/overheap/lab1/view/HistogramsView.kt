package by.overheap.lab1.view

import by.overheap.lab1.controller.ImageController
import by.overheap.lab1.histogram.createHistogramColumns
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.paint.Color
import tornadofx.*
import java.io.File

class HistogramsView : View() {

    val controller: ImageController by inject()

    val xAxis = CategoryAxis()
    val yAxis = NumberAxis()

    val redHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val greenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val blueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    val processedRedHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val processedGreenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val processedBlueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    override val root = vbox(20, alignment = Pos.CENTER) {
        prefHeight = 800.0
        spacer { }
        button("Choose file").action {
            val file = chooseFile(filters = arrayOf()).first()
            processImage(file)
        }
        scrollpane {
            hbox {
                vbox(alignment = Pos.CENTER) {
                    label("Before")
                    barchart("R", CategoryAxis(), NumberAxis()) {
                        series("Brightness", redHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                    barchart("G", CategoryAxis(), NumberAxis()) {
                        series("Brightness", greenHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                    barchart("B", CategoryAxis(), NumberAxis()) {
                        series("Brightness", blueHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                }
                vbox(alignment = Pos.CENTER) {
                    barchart("R", CategoryAxis(), NumberAxis()) {
                        series("Brightness", processedRedHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                    barchart("G", CategoryAxis(), NumberAxis()) {
                        series("Brightness", processedGreenHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                    barchart("B", CategoryAxis(), NumberAxis()) {
                        series("Brightness", processedBlueHistogramData)
                        minWidth = 920.0
                        animated = false
                    }
                }

            }
        }

    }

    private fun processImage(file: File) {
        val imageData = controller.processImage(file)
        redHistogramData.setAll(createHistogramColumns(imageData.redPixels).map { it.toChartData() })
        greenHistogramData.setAll(createHistogramColumns(imageData.greenPixels).map { it.toChartData() })
        blueHistogramData.setAll(createHistogramColumns(imageData.bluePixels).map { it.toChartData() })
    }

    private fun Map.Entry<Int, Int>.toChartData() = XYChart.Data<String, Number>(key.toString(), value)

}