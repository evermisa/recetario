package com.everardoenriquez.recetario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_recipe_activity)

        val viewMapBtn:Button = findViewById(R.id.detail_recipe_view_map_btn)
        viewMapBtn.setOnClickListener({
            val intent = Intent(this, MapViewer()::class.java)
            startActivity(intent)
        })
    }
}