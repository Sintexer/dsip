package by.overheap.lab1.view

import by.overheap.lab1.controller.ImageController
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class HistogramsView : View() {

    val controller: ImageController by inject()

    val redHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val greenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val blueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    val processedRedHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val processedGreenHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()
    val processedBlueHistogramData = mutableListOf<XYChart.Data<String, Number>>().asObservable()

    override val root = vbox(20, alignment = Pos.BASELINE_CENTER) {
        spacer {  }
        button("Choose file").action {
            chooseFile(filters = arrayOf()).forEach{println(it)}
        }
        label("Brightness before")
        hbox {
            barchart("R", CategoryAxis(), NumberAxis()) {
                series("", redHistogramData)
            }
            barchart("G", CategoryAxis(), NumberAxis()) {
                series("", greenHistogramData)
            }
            barchart("B", CategoryAxis(), NumberAxis()) {
                series("", blueHistogramData)
            }
        }
        label("Brightness after")
        hbox {
            barchart("R", CategoryAxis(), NumberAxis()) {
                series("", processedRedHistogramData)
            }
            barchart("G", CategoryAxis(), NumberAxis()) {
                series("", processedGreenHistogramData)
            }
            barchart("B", CategoryAxis(), NumberAxis()) {
                series("", processedBlueHistogramData)
            }
        }
    }



}