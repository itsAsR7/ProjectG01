package com.example.projectv1.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.projectv1.R
import android.util.Log


import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000 // 1 sec



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        val username = sharedPref.getString("username", null)

        if (username != null) {
            Handler().postDelayed({ //this is executed once the timer is over//

                val i = Intent(this, EnterNameScreenActivity::class.java)
                startActivity(i)
                finish()
            }, SPLASH_TIME_OUT)
        } else {
            Handler().postDelayed({ //this is executed once the timer is over//

                val i = Intent(this, WelcomeBackActivity::class.java)
                startActivity(i)
                finish()
            }, SPLASH_TIME_OUT)
        }

    }
}



