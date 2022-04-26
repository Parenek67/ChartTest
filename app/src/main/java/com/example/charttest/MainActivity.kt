package com.example.charttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private var isDrawValues = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //time()
        lineChart = findViewById(R.id.linegraph)
        val lineDataSet = LineDataSet(dataValues(), "Data values")
            lineDataSet.valueTextSize = 15F
            lineDataSet.color = ContextCompat.getColor(this, R.color.purple_200)
            lineDataSet.setDrawFilled(true)
            lineDataSet.setDrawValues(false)
            lineDataSet.fillDrawable = ContextCompat.getDrawable(this, R.drawable.zalivka)
            lineDataSet.lineWidth = 3F
            lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.purple_200))
            lineDataSet.circleHoleColor = ContextCompat.getColor(this, R.color.purple_200)
            lineDataSet.circleRadius = 3.7F


        val dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)
        lineChart.data = lineData
        lineChart.setDrawBorders(true)
        lineChart.xAxis.textSize = 14F
        lineChart.axisLeft.textSize = 14F
        lineChart.axisRight.setDrawLabels(false)

        lineChart.setOnClickListener{
            lineDataSet.setDrawValues(isDrawValues)
            isDrawValues = !isDrawValues
        }

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.labelRotationAngle = 90F
        xAxis.setLabelCount(6, true)

        lineChart.description.isEnabled = false
        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.isScaleYEnabled = false
        //lineChart.xAxis.labelCount = 6
        lineChart.xAxis.valueFormatter = Formatter()
        lineChart.invalidate()
    }

    private fun dataValues(): MutableList<Entry>{
        val dataList = mutableListOf<Entry>()
        val xList = listOf("June 21 2014", "June 22 2014", "June 23 2014", "June 24 2014", "June 25 2014", "June 26 2014")
        val yList = listOf(81F, 88.5F, 82F, 71F, 94F, 90F)
        try {
            for ((index, value) in xList.withIndex()){
                Log.d("tag", Date(value).time.toFloat().toString())
                dataList.add(Entry(time(value), yList.get(index)))
            }
        }
        catch (e: Exception){
            Log.d("tag", "абоба")
        }

        /*dataList.add(Entry(1F, 81F))
        dataList.add(Entry(2F, 88.5F))
        dataList.add(Entry(4F, 82F))
        dataList.add(Entry(5.65F, 71F))
        dataList.add(Entry(7F, 94F))*/
        Log.d("tag", dataList.size.toString())
        return dataList
    }

    private fun time(date: String): Float{
        //val date = "Tue Apr 23 16:08:28 GMT+05:30 2013"
        //val date = "June 21 2014"
        val d = Date(date)
        val millis: Long = d.time
        Log.d("tag", millis.toString())
        Log.d("tag", Date(millis).toString())
        return millis.toFloat()
    }
}