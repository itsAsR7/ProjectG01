package com.example.projectv1.models

import android.graphics.drawable.Drawable
import android.net.Uri

data class Lesson(
    val number: Int, val img_number: Int,
    val name: String,
    val length: String,
    var isCompleted: Boolean = false, val img_isCompleted: Int,
    val description:String,
    var notes:String,
    var links:String) { }