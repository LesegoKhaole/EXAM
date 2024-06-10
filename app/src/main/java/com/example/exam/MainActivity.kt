package com.example.exam

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Thread {
            // Declare Variables for the UI elements being used
            val source = ImageDecoder.createSource(resources, R.drawable.suncloud)
            val drawable = ImageDecoder.decodeDrawable(source)
            val suncloud : ImageView = findViewById(R.id.suncloud)
            suncloud.post {
                suncloud.setImageDrawable(drawable)
                (drawable as? AnimatedImageDrawable)?.start()
            }
        }.start()


        // Declare the use of a handler function to manage and maintain a timed sequence delay
        val handler = Handler()
        // Delay the navigation to the next screen by 10 seconds



        //the delay is executed due to the the postDelayed function which prevents the execution of a set function
        // until the milliseconds timer is reached then the execution of the mentioned function begins\\
        handler.postDelayed({
            // Declare Variables for the UI elements being used
            // Create the explicit intent
            val intent = Intent(this@MainActivity, enterScreen::class.java)



            // Start the activity
            startActivity(intent)

        }, 2500) // 2.5 seconds delay in milliseconds



    }
}