package com.example.testkot.Models

import java.io.Serializable

data class RecipeModel(
    var id: Int,
    val recipeName: String,
    val description: String,
    val ingredients: MutableList<String>,
    val tags: String,
    val coordinates: MutableList<Double>,
    val imageName: String) : Serializable