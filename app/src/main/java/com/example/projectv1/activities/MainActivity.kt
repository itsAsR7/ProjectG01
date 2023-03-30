package com.example.projectv1.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.projectv1.R


import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val prefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
            val nameEntered = prefs.getString("username", "")

            val intent = if (nameEntered != "") {
                // User has entered their name before, show the Welcome Back screen
                Intent(this, WelcomeBackActivity::class.java)
            } else {
                // User has not entered their name before, show the Enter Name screen
                Intent(this, EnterNameScreenActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}




