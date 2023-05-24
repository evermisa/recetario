package com.everardoenriquez.recetario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testkot.Models.RecipeModel

class DetailRecipeActivity : AppCompatActivity() {
    val imageType = "drawable"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_recipe_activity)

        //Inicialización de los componentes de la vista para asignar sus valores
        val recipe = intent.getSerializableExtra("RECIPE_OBJ") as? RecipeModel
        val detailItemPhoto: ImageView = findViewById(R.id.detailItemPhoto)
        val recipeTitle: TextView = findViewById(R.id.recipeTitle)
        val detailInfo: TextView = findViewById(R.id.detailInfo)
        val ingredientsInfo: TextView = findViewById(R.id.ingredientsInfo)
        val viewMapBtn:Button = findViewById(R.id.detail_recipe_view_map_btn)

        //Validación en caso de que ocurra algún error con el objeto
        if (recipe == null) return

        //Asignación de los valores de la vista, usando los datos obtenidos por el objeto
        val filePhoto = getResources().getIdentifier(
            recipe.imageName, imageType,applicationContext.packageName
        )
        detailItemPhoto.setImageResource(filePhoto)
        recipeTitle.text = recipe.recipeName
        detailInfo.text = recipe.description
        var ingredients = ""
        for (i in recipe.ingredients) {
            ingredients += "■ $i \n"
        }
        ingredientsInfo.text = ingredients

        //Acción del botón para enviar a la pantalla de ver el mapa con las coordenadas obtenidas
        viewMapBtn.setOnClickListener {
            val intent = Intent(this, MapViewer()::class.java)
            intent.putExtra("Latitude", recipe.coordinates[0])
            intent.putExtra("Longitude", recipe.coordinates[1])
            startActivity(intent)
        }
    }
}