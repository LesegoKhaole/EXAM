package com.example.exam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




@Suppress("DEPRECATION")
class enterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val talk = findViewById<TextView>(R.id.talk)
        val yes = findViewById<Button>(R.id.yes)
        val no = findViewById<Button>(R.id.no)
        //Ensure the button triggers the execution of the function within its curly braces
// that define the star and end in which the button's effects are permitted and established
        yes.setOnClickListener {

            talk.text = buildString {
                append("Good Luck !")
            }



            // Declare Variables for the UI elements being used
            val handler = Handler()
            // Delay the navigation to the next screen by 10 seconds

            handler.postDelayed({

                // Declare Variables for the UI elements being used
                // Create the explicit intent
                val intent = Intent(this, MainScreen::class.java)


                // Start the activity
                startActivity(intent)
            }, 2500) // 10 seconds delay in milliseconds
        }


//Ensure the button triggers the execution of the function within its curly braces
// that define the star and end in which the button's effects are permitted and established
        no.setOnClickListener {

            //Display gif image using the functions embedded within the Thread and execute with the function start


            talk.text = buildString {
                append("Until Next Time !")
            }
            //Display gif image using the functions embedded within the Thread and execute with the function start

            // Delay for 5 seconds and then close the app
            Handler().postDelayed({
                finishAffinity()
            }, 5000)
        }


    }
}