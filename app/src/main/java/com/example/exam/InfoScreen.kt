package com.example.exam

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoScreen : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private lateinit var averageTempTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_screen) // Create this layout file



        val minTemps = intent.getStringArrayListExtra("minTemps") ?: emptyList()
        val maxTemps = intent.getStringArrayListExtra("maxTemps") ?: emptyList()
        val weatherConditions = intent.getStringArrayListExtra("weatherConditions") ?: emptyList()

        displayWeekInfo(minTemps, maxTemps, weatherConditions)
        calculateAverageTemperature(minTemps, maxTemps)
    }

    @SuppressLint("SetTextI18n")
    private fun displayWeekInfo(minTemps: List<String>, maxTemps: List<String>, weatherConditions: List<String>) {
        val displayText = StringBuilder("Week Information:\n")
        val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        for (i in daysOfWeek.indices) {
            displayText.append("${daysOfWeek[i]}: Min - ${minTemps[i]}, Max - ${maxTemps[i]}, Weather - ${weatherConditions[i]}\n")
        }
        displayTextView.text = displayText.toString()
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun calculateAverageTemperature(minTemps: List<String>, maxTemps: List<String>) {
        var totalTempSum = 0.0
        for (i in minTemps.indices) {
            totalTempSum += (minTemps[i].toDoubleOrNull() ?: 0.0) + (maxTemps[i].toDoubleOrNull() ?: 0.0)
        }
        val averageTemp = totalTempSum / (minTemps.size * 2) // Divide by total number of temperatures
        averageTempTextView.text = "Average Temperature for the Week: ${String.format("%.2f", averageTemp)}"
    }
}