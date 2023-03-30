package com.example.projectv1.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.projectv1.R


import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Start your main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the splash activity so it can't be returned to
            finish()
        }, SPLASH_TIME_OUT)
    }
}




