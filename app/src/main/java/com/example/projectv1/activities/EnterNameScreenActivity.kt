package com.example.projectv1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }


    override fun onStart() {
        super.onStart()


        val continueButton: Button = findViewById(R.id.button1)

        Log.d("tagtagtag","enter your name screen onStart()")

        continueButton.setOnClickListener{

            nameFromUi = findViewById(R.id.editText1)
            nameAsString = nameFromUi.text.toString()


            val datasource = UserDataSource.getInstance()

            datasource.name = nameAsString

            val i = Intent(this, LessonListActivity::class.java)
            startActivity(i)



        }

    }
}