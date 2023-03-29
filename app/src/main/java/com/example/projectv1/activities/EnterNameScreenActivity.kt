package com.example.projectv1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.util.Log
import com.example.projectv1.R
import com.example.projectv1.data.UserDataSource

class EnterNameScreenActivity : AppCompatActivity() {

    lateinit var nameFromUi: EditText
    lateinit var nameAsString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_your_name_screen)

        var continueButton: Button = findViewById(R.id.button1)
        continueButton.setOnClickListener{

            nameFromUi = findViewById(R.id.editText1)
            nameAsString = nameFromUi.text.toString()

            val sharedPref = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("username", nameAsString)
                commit()
            }

            val i = Intent(this, LessonListActivity::class.java)
            startActivity(i)
        }
    }
    override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

            val i = Intent(this, WelcomeBackActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}