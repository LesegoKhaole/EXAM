package com.example.exam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {

    private lateinit var minimum: EditText
    private lateinit var maximum: EditText
    private lateinit var weather: EditText
    private lateinit var submit: Button
    private lateinit var clear: Button
    private lateinit var back: Button
    private lateinit var displayTextView: TextView
    private val minTemps = mutableListOf<String>()
    private val maxTemps = mutableListOf<String>()
    private val weatherConditions = mutableListOf<String>()
    private var currentIndex = 0
    private val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        updateHintTexts()

        clear.setOnClickListener {

            minTemps.clear()
            maxTemps.clear()
            weatherConditions.clear()
            currentIndex++
            updateDisplay()
            clearInputFields()
            updateHintTexts()

        }

        back.setOnClickListener {

                // Declare Variables for the UI elements being used
                // Create the explicit intent
                val intent = Intent(this, enterScreen::class.java)
                // Start the activity
                startActivity(intent)

        }



        submit.setOnClickListener {
            val newMin = minimum.toString().trim()
            val newMax = maximum.toString().trim()
            val newWeather = weather.text.toString().trim()



            if (newMin.isNotEmpty() && newMax.isNotEmpty() && newWeather.isNotEmpty() && currentIndex < 7 && newMin>newMax) {
                Toast.makeText(
                    this@MainScreen,
                    "A New Week means New Data",
                    Toast.LENGTH_SHORT
                )
                    .show()
                    .run {}
                minTemps.clear()
                maxTemps.clear()
                weatherConditions.clear()
                currentIndex++
                updateDisplay()
                clearInputFields()
                updateHintTexts()

            }

            if (newMin.isNotEmpty() && newMax.isNotEmpty() && newWeather.isNotEmpty() && currentIndex < 7 && newMin>newMax) {
                Toast.makeText(
                    this@MainScreen,
                    "Minimum Temperature can't be higher than Maximum",
                    Toast.LENGTH_SHORT
                ).show()
                    .run {}
            }

            if (newMin.isNotEmpty() && newMax.isNotEmpty() && newWeather.isNotEmpty() && currentIndex < 7 && newMax<newMin) {
                Toast.makeText(
                    this@MainScreen,
                    "Maximum Temperature can't be lower than Minimum",
                    Toast.LENGTH_SHORT
                )
                    .show()
                    .run {}
            }

            if (newMin.isNotEmpty() && newMax.isNotEmpty() && newWeather.isNotEmpty() && currentIndex < 7 && newMin<newMax) {
                minTemps.add(newMin)
                maxTemps.add(newMax)
                weatherConditions.add(newWeather)
                currentIndex++
                updateDisplay()
                clearInputFields()
                updateHintTexts()
            }

        }
    }

    private fun updateHintTexts() {
        if (currentIndex < 7) {
            maximum.hint = "The max for ${daysOfWeek[currentIndex]}"
            minimum.hint = "The min for ${daysOfWeek[currentIndex]}"
            weather.hint = "The weather for ${daysOfWeek[currentIndex]}"
        }
    }

    private fun clearInputFields() {
        minimum.text.clear()
        maximum.text.clear()
        weather.text.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun updateDisplay() {
        val displayText = StringBuilder("Current list:\n")
        for (i in 0 until currentIndex) {
            displayText.append("${daysOfWeek[i]}: Min - ${minTemps[i]}, Max - ${maxTemps[i]}, Weather - ${weatherConditions[i]}\n")
        }
        displayTextView.text = displayText.toString()
    }
}

