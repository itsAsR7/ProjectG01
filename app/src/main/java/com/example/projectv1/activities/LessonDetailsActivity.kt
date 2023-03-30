package com.example.projectv1.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projectv1.R
import com.example.projectv1.data.LessonsDatasource
import com.example.projectv1.models.Lesson

class LessonDetailsActivity : AppCompatActivity() {
    var positionOfLesson:Int = -1

    //finding webview
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)


        // Getting shared preferences
        this.sharedPrefs  = getSharedPreferences("MY-PREFS", MODE_PRIVATE)

        // Checking if the intent is not null
        if (intent != null) {

            // Getting the position of the lesson from the intent
            this.positionOfLesson = intent.getIntExtra("LESSON_POS", -1)

            // Getting the current lesson from the LessonsDatasource
            val datasource = LessonsDatasource.getInstance()
            val currLesson: Lesson = datasource.lessonsList.get(this.positionOfLesson)

            // Populate the UI: TextViews, EditText and WebView
            var lesson_id = findViewById<TextView>(R.id.lesson_id)
            var lesson_title = findViewById<TextView>(R.id.lesson_title)
            var lesson_length = findViewById<TextView>(R.id.lesson_length)
            var lesson_description = findViewById<TextView>(R.id.lesson_description)
            var lesson_notes = findViewById<EditText>(R.id.txt_notes)
            var lesson_saved_notes = findViewById<TextView>(R.id.saved_notes)
            val webView = findViewById<WebView>(R.id.webview)

            // Setting the WebViewClient for the WebView
            webView.webViewClient = WebViewClient()

            // Allowing content and file access in the WebView
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setAllowFileAccess(true);

            // Enabling JavaScript in the WebView
            webView.settings.javaScriptEnabled = true

            // Setting the text of TextViews to show the current lesson details
            lesson_id.setText((currLesson.number+1).toString())
            lesson_title.setText(currLesson.name)
            lesson_length.setText(currLesson.length)
            lesson_description.setText(currLesson.description)

            // Retrieving saved notes and setting them in the notes TextView
            var notesText :String = ""
            if (this.sharedPrefs.contains("NOTES${currLesson.number}") == true) {

                // If notes for the lesson are saved, retrieve them from sharedPrefs
                val notesFromSP = this.sharedPrefs.getString("NOTES${currLesson.number}", "")
                notesText += "${notesFromSP}"
            }
            lesson_saved_notes.setText(notesText)

            // Click handler for Watch Lesson button
            val btnWatchLesson = findViewById<Button>(R.id.btnWatchLesson)
            btnWatchLesson.setOnClickListener {
                // Loading the video URL in the WebView on button click
                webView.loadUrl(currLesson.links)
            }

            // Click handler for Save button
            val btnSave = findViewById<Button>(R.id.btnSave)

            btnSave.setOnClickListener {


                // Updating the notes for the lesson
                currLesson.notes = lesson_notes.text.toString()
                println(currLesson.notes)

                // Updating the saved notes
                lesson_saved_notes.setText(lesson_notes.text)

                // Saving notes to share preference
                with(this.sharedPrefs.edit()) {
                    putString("NOTES${currLesson.number}", lesson_saved_notes.text.toString())

                    // Commit changes
                    apply()

                    // Log message
                    Log.d(TAG, "Success!")
                    val toast = Toast.makeText(applicationContext, "Notes saved!", Toast.LENGTH_LONG)
                    toast.show()
                }
            }

            // Click handler for Mark as Completed button
            val btnCompleted = findViewById<Button>(R.id.btnCompleted)

            var completeBoolean = sharedPrefs.getBoolean("MARK_COMPLETED${currLesson.number}", false)




            if(completeBoolean){
                btnCompleted.setText("COMPLETED")
                btnCompleted.setBackgroundColor(Color.parseColor("#63b611"))
            }

            btnCompleted.setOnClickListener {

                // If isCompleted true the image is displayed
                currLesson.isCompleted = true

                // Saving to shared preference
                with(this.sharedPrefs.edit()) {
                    putBoolean("MARK_COMPLETED${currLesson.number}", currLesson.isCompleted)

                    // Commit changes
                    apply()

                    // Log message

                    btnCompleted.setText("COMPLETED")
                    btnCompleted.setBackgroundColor(Color.parseColor("#63b611"))


                }
            }
        }
    }
}