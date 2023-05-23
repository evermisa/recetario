package com.everardoenriquez.recetario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testkot.Models.RecipeModel

class DetailRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_recipe_activity)

        val recipe = intent.getSerializableExtra("RECIPE_OBJ") as? RecipeModel
        val recipeTitle: TextView = findViewById(R.id.recipeTitle)
        val detailInfo: TextView = findViewById(R.id.detailInfo)
        val ingredientsInfo: TextView = findViewById(R.id.ingredientsInfo)
        val viewMapBtn:Button = findViewById(R.id.detail_recipe_view_map_btn)

        if (recipe == null) return

        recipeTitle.text = "${recipe?.recipeName}"
        detailInfo.text = "${recipe?.description}"
        var ingredients = ""
        for (i in recipe.ingredients) {
            ingredients += "* $i \n"
        }
        ingredientsInfo.text = ingredients
        viewMapBtn.setOnClickListener {
            val intent = Intent(this, MapViewer()::class.java)
            intent.putExtra("Latitude", recipe.coordinates[0])
            intent.putExtra("Longitude", recipe.coordinates[1])
            startActivity(intent)
        }
    }
}