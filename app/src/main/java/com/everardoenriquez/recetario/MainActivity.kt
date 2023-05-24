package com.everardoenriquez.recetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.everardoenriquez.recetario.views.RecipeListItemAdapter
import com.example.testkot.Models.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    var recipes: MutableList<RecipeModel> = ArrayList()
    var mHandler: Handler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de componentes del layout
        val searchBar: EditText = findViewById(R.id.editTextText)
        val recipeListRecycler: RecyclerView = findViewById(R.id.recipeListRecycler)
        val recipeAdapter = RecipeListItemAdapter (recipes){ s -> adapterOnClick(s)}

        //Creación del request que se mandará al servicio para obtener los datos requeridos
        val gson = Gson()
        val body: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")
        val request = Request.Builder()
            .url("http://demo6224271.mockable.io/")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.println(Log.ERROR,"IOException", "${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.code() == 200) {
                    //Obtenemos la información del servicio en un JSON y lo convertimos a objeto
                    val recipeArrayType = object : TypeToken<Array<RecipeModel>>() {}.type
                    val jsonArray: Array<RecipeModel> =
                        gson.fromJson(response.body()?.string(), recipeArrayType)
                    for (i in jsonArray.indices) {
                        recipes.add(jsonArray[i])
                    }
                    mHandler.post {
                        recipeListRecycler.adapter = recipeAdapter
                    }
                } else {
                    Log.println(Log.ERROR,"${response.code()}", "${response.body()?.string()}")
                }
            }
        })

        searchBar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                //Metodo de filtrado dependiendo de lo que se escriba en el texto de filtrado
                val recipeFilter: List<RecipeModel> = recipes.filter {
                        recp -> recp.recipeName.contains(searchBar.text, true) ||
                        recp.tags.contains(searchBar.text, true)
                }
                recipeAdapter.onFilter(recipeFilter)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(st: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })
    }

    private fun adapterOnClick(s: RecipeModel) {
        val intent = Intent(this, DetailRecipeActivity()::class.java)
        intent.putExtra("RECIPE_OBJ", s)
        startActivity(intent)
    }
}