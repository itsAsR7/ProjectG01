package com.example.projectv1.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectv1.R
import com.example.projectv1.models.Lesson

class LessonAdapter(context: Context, private val lessons: List<Lesson>) : ArrayAdapter<Lesson>(context, 0, lessons) {

    // getCount(): return the size of the list of lessons
    override fun getCount(): Int {
        return lessons.size
    }

    // getItem(): return the Lesson object at a given position
    override fun getItem(position: Int): Lesson? {
        return lessons[position]
    }

    // getItemId(): return the position as a long value
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // getView(): populate the list view with the Lesson objects
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Check if the view already exists, otherwise inflate it
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.lesson_list, parent, false)

        // Get the Lesson object at the current position
        val lesson = lessons[position]

        // Find the ImageView for the completed lesson icon
        var img_number = view.findViewById<ImageView>(R.id.img_lesson_no)
        img_number.setImageResource(lesson.img_number)

        // Find the TextView for the lesson name and set its value to the lessonName
        val lessonName = view.findViewById<TextView>(R.id.lesson_name)
        lessonName.text = lesson.name

        // Find the TextView for the lesson length and set its value to the lessonLength
        val lessonLength = view.findViewById<TextView>(R.id.lesson_length)
        lessonLength.text = lesson.length

        // Find the ImageView for the completed lesson icon
        var img_isCompleted = view.findViewById<ImageView>(R.id.img_isCompleted)

        // Getting the value to check if lesson is marked as completed from shared preferences
        var mark:Boolean = false
        var sharedPrefs:SharedPreferences  = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        if (sharedPrefs.contains("MARK_COMPLETED${lesson.number}") == true) {
            // - b. if yes, then retrieve
            mark = sharedPrefs.getBoolean("MARK_COMPLETED${lesson.number}", false)
        }

        // Setting the completed lesson icon if the lesson is marked as completed
        if (mark) {
            img_isCompleted.setImageResource(R.drawable.completed)
        }

        return view
    }
}
