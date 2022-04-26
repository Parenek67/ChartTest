package com.example.charttest

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class Formatter : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        val emissionsMilliSince1970Time = value.toLong()

        // Show time in local version
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val dateTimeFormat: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        return dateTimeFormat.format(timeMilliseconds)
    }
}