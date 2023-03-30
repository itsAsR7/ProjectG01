package com.example.projectv1.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import com.example.projectv1.R
import com.example.projectv1.adapters.LessonAdapter
import com.example.projectv1.data.LessonsDatasource
import com.example.projectv1.models.Lesson

class LessonListActivity : AppCompatActivity() {

    val TAG = "MY-APP"

    lateinit var lessonListView: ListView
    lateinit var toggleButton: Switch
    lateinit var lessonAdapter:LessonAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        // Finding views by their respective Ids
        lessonListView = findViewById(R.id.lesson_list)
        toggleButton = findViewById(R.id.btn_toggle)

    }

    override fun onStart() {
        super.onStart()

        // Populate the list view with lessons
        val lessonsList:MutableList<Lesson>
                = LessonsDatasource.getInstance().lessonsList

        // Setting the adapter for the ListView
        lessonAdapter = LessonAdapter(this, lessonsList)

        // Setting the adapter for the ListView
        lessonListView.adapter = lessonAdapter

        // Handling click events of list view items
        lessonListView.setOnItemClickListener { adapterView, view, i, l ->

            // Logging the clicked lesson
            Log.d(TAG, "lesson clicked on row ${i}")
            // i = row number
            // i = position of the item in the array

            // If to handle list item click events
            if(toggleButton.isChecked == true) {
                // getting instance of the lesson
                // getting data from shared preference

                var lessonIndex = -1
                val datasource = LessonsDatasource.getInstance()
                if(i == 0) {
                    lessonIndex = 0
                    val intent = Intent(this, LessonDetailsActivity::class.java)
                    intent.putExtra("LESSON_POS", i)
                    startActivity(intent)
                }else if(i > 0){
                    lessonIndex = i-1
                }

                var mark:Boolean = false
                var sharedPrefs: SharedPreferences = getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)

                // Code to handle sequential progression functionality
                if (sharedPrefs.contains("MARK_COMPLETED${lessonIndex}") == true) {
                    // - b. if yes, then retrieve
                    mark = sharedPrefs.getBoolean("MARK_COMPLETED${lessonIndex}", false)
                }
                val prevLesson: Lesson = datasource.lessonsList.get(lessonIndex)
                if(mark) {
                    val intent = Intent(this, LessonDetailsActivity::class.java)
                    intent.putExtra("LESSON_POS", i)
                    startActivity(intent)
                }
                Toast.makeText(this, "Please Complete Lecture "+(lessonIndex+1),
                    Toast.LENGTH_SHORT).show()

            } else {

                // Code to handle normal lesson click functionality
                val intent = Intent(this, LessonDetailsActivity::class.java)
                intent.putExtra("LESSON_POS", i)
                startActivity(intent)
            }
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, WelcomeBackActivity::class.java)
        startActivity(intent)
        finish()
    }
}


