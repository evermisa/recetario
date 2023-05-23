package com.example.testkot.Models

import android.graphics.drawable.Drawable
import java.io.Serializable
import java.util.*

data class RecipeModel(
    var id: Int,
    val recipeName: String,
    val description: String,
    val ingredients: MutableList<String>,
    val tags: String,
    val coordinates: MutableList<Double>,
    val imageName: String) : Serializable