package com.example.testkot.Models

import android.graphics.drawable.Drawable
import java.util.*

data class RecipeModel(
    val recipeName: String,
    val description: String,
    val ingredients: MutableList<String> ) //{
    //private var recipeName: String = ""
    //private var description: String = ""
    //private var ingredients: MutableList<String> = ArrayList()
//
    //fun setRecipeName(recipeName: String) {
    //    this.recipeName = recipeName
    //}
//
    //fun setDescription(description: String) {
    //    this.description = description
    //}
//
    //fun setIngredients(ingredients: MutableList<String>) {
    //    this.ingredients = ingredients
    //}
//
    //fun getRecipeName():String {
    //    return recipeName
    //}
//
    //fun getDescription():String {
    //    return description
    //}
//
    //fun getIngredients():MutableList<String> {
    //    return ingredients
    //}
//
    //override fun toString(): String {
    //    return "RecipeModel(recipeName='$recipeName', description='$description', ingredients=$ingredients)"
    //}


//}