package com.example.projectv1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import com.example.projectv1.R

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

            if (nameAsString.isBlank()) {
                // Display an error message to the user
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPref = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("username", nameAsString)
                    commit()
                }

                val i = Intent(this, LessonListActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(i)
                finish()
            }
        }
    }
}