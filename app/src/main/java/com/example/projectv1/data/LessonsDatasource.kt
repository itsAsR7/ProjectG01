package com.example.projectv1.data

import com.example.projectv1.models.Lesson
import com.example.projectv1.R

class LessonsDatasource {

    var lessonsList:MutableList<Lesson> = mutableListOf(
        Lesson(0, R.drawable.one,
            "Introduction to Android Development", "01:21", false ,
            R.drawable.completed,
            "This course introduces the basics of Android app development, including the Android architecture and UI design.",
            "",
            "https://www.youtube.com/embed/NCoekcDxbrI"),

        Lesson(1, R.drawable.two,
            "Layouts and Views", "21:42", false ,
            R.drawable.completed,
            "This course covers Layouts and Views, including designing user interfaces with XML layouts,  and implementing various views such as buttons, text fields, and images.",
            "",
            "https://www.youtube.com/embed/9-cGAXbuoRI"),

        Lesson(2, R.drawable.three,
            "Activities and Intents", "36:06", false ,
            R.drawable.completed,
            "This course covers Activities and Intents, exploring how to create and manage screens and transfer data between them using Intent objects.",
            "",
            "https://www.youtube.com/embed/3dsAuLkDTUc"),

        Lesson(3, R.drawable.four,
            "RecyclerViews and Adapters", "07:28", false ,
            R.drawable.completed,
            "This course covers the implementation of RecyclerViews and Adapters, including how to efficiently display large data sets and handle user interactions.",
            "",
            "https://www.youtube.com/embed/gGFvbvkZiMs"),

        Lesson(4, R.drawable.five,
            "Fragments and Navigation", "17:28", false ,
            R.drawable.completed,
            "This course covers the information to create and navigate between different screens in using fragments and the navigation component.",
            "",
            "https://www.youtube.com/embed/-vAI7RSPxOA"),

        Lesson(5, R.drawable.six,
            "Data Persistence and Storage", "12:18", false ,
            R.drawable.completed,
            "This course covers the fundamentals of data persistence and storage in Android development, including SharedPreferences, and file storage.",
            "",
            "https://www.youtube.com/embed/jcO6p5TlcGs")
    )

    companion object {
        @Volatile
        private lateinit var instance: LessonsDatasource

        fun getInstance(): LessonsDatasource {
            synchronized(this) {
                if (!Companion::instance.isInitialized) {
                    instance = LessonsDatasource()
                }
                return instance
            }
        }
    }

}