package com.example.projectv1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.projectv1.R

class WelcomeBackActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var progressText: TextView

    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_welcome_back)

        welcomeText = findViewById(R.id.welcome_message)
        progressText = findViewById(R.id.progress_info)

        // Retrieve user data from SharedPreferences
        val sharedPrefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
        val userName = sharedPrefs.getString("username", null)

        // Display welcome message with user's name
        welcomeText.text = "Welcome back, $userName!"

        // Display progress information

        val completedLessons = sharedPrefs
            .getAll()
            .filterKeys { it.startsWith("MARK_COMPLETED") }
            .count { it.value == true }
        val totalLessons = 6 // replace with actual total number of lessons
        val progress = (completedLessons.toDouble() / totalLessons.toDouble()) * 100
        progressText.text = "You have completed $completedLessons out of $totalLessons lessons (${progress.toInt()}% progress)."

        // Set up "Proceed to Lessons" button
        val proceedButton: Button = findViewById(R.id.proceed_button)
        proceedButton.setOnClickListener {
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        // Set up "Reset" button
        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            // Delete saved user data from SharedPreferences
            val editor = sharedPrefs.edit()
            editor.clear()
            editor.apply()



            val intent = Intent(this, EnterNameScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
