package com.everardoenriquez.recetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.everardoenriquez.recetario.views.HeaderAdapter
import com.everardoenriquez.recetario.views.RecipeListItemAdapter
import com.example.testkot.Models.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.stream.Stream

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    val recipes: MutableList<RecipeModel> = ArrayList()
    val jsonString:String = "[{\"nombre\":\"John\",\"descripción\":\"asda\",\"ingredientes\":[\"Ajo\",\"Pepino\"],\"coordenadas\":[123.1231,123.1122]},{\"nombre\":\"2\",\"descripción\":\"3\",\"ingredientes\":[\"Ajo\",\"Pepino\"],\"coordenadas\":[123.1231,123.1122]}]"
    val jsonStringPruebas:String = "[{\"recipeName\":\"Primero\",\"description\":\"Uno\",\"ingredients\":[\"1\",\"2\"]},{\"recipeName\":\"Segundo\",\"description\":\"Dos\",\"ingredients\":[\"1\",\"2\"]},{\"recipeName\":\"Tercero\",\"description\":\"Tres\",\"ingredients\":[\"1\",\"2\"]}]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var month: List<String> = arrayOf("January", "February", "March")
        //var monthList: List<String> = month.filter { s -> s == "January" }
        /*
        https://www.bezkoder.com/kotlin-parse-json-gson/
val jsonList =
		"""[{"title":"Kotlin Tutorial #1","author":"bezkoder","categories":["Kotlin, Basic"]},
			{"title":"Kotlin Tutorial #2","author":"bezkoder","categories":["Kotlin, Practice"]}]"""

	val gson = Gson()
	val arrayTutorialType = object : TypeToken<Array<Tutorial>>() {}.type

	var tutorials: Array<Tutorial> = gson.fromJson(jsonList, arrayTutorialType)
	tutorials.forEachIndexed  { idx, tut -> println("> Item ${idx}:\n${tut}") }

        val rpm = RecipeModel()
        rpm.setRecipeName("Hola mundo")
        rpm.setIngredients("Que onda")
        recipes.add(rpm)

        val rpm2 = RecipeModel()
        rpm2.setRecipeName("Hello world")
        rpm2.setIngredients("Whazza")
        recipes.add(rpm2)
*/

        val gson = Gson()
        val recipeArrayType = object : TypeToken<Array<RecipeModel>>() {}.type
        var jsonArray: Array<RecipeModel> = gson.fromJson(jsonStringPruebas, recipeArrayType)
        //var jsonArray = JSONArray(jsonStringPruebas)
        for (i in 0..jsonArray.size-1) {
            //val jsonObject = jsonArray.getJSONObject(i)
            //val rpms: RecipeModel = gson.fromJson(jsonArray.getJSONObject(i), RecipeModel())
            recipes.add(jsonArray.get(i))
            recipes.add(jsonArray.get(i))
            recipes.add(jsonArray.get(i))
            //var toImprimir = jsonObject.get("ingredientes").toString()
            //Log.println(Log.INFO,"El JSON $i", toImprimir)
        }

        for (x in recipes) {
            ////Log.println(Log.INFO,"Recipe", x.getRecipeName())
            ////Log.println(Log.INFO,"Description", x.getDescription())
            ////Log.println(Log.INFO,"Ingredients", x.getIngredients().toString())
        }

        var textView: TextView = findViewById(R.id.textView)
        val request = Request.Builder()
            .url("http://demo6224271.mockable.io/")
            //.method("POST",null)
            .build()

        Toast.makeText(applicationContext,"La tostada",Toast.LENGTH_LONG).show()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //textView.text = "${e.message}"
            }
            override fun onResponse(call: Call, response: Response) {
                //textView.text = "${response.code()}"
                //textView.text = "${response.body()?.string()}"
            }
        })
        //textView.text = "${recipes.size}"

        val recipeListRecycler: RecyclerView = findViewById(R.id.recipeListRecycler)
        val recipeAdapter = RecipeListItemAdapter (recipes){ s -> adapterOnClick(s)}
        val headerAdapter = HeaderAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, recipeAdapter)
        recipeListRecycler.adapter = concatAdapter
        //recipeListRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        //textView.text = "${gson.toJson(recipes)}"
        //recipeListRecycler?.adapter = RecipeListItemAdapter(recipes)

        var searchBar: EditText = findViewById(R.id.editTextText)
        searchBar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                var recipeFilter: List<RecipeModel>? = recipes.filter { s -> s.recipeName.contains(searchBar.text) }//.stream().filter{ s -> s.recipeName.contains(searchBar.text) }
                textView.text = "${searchBar.text} esto es: ${gson.toJson(recipeFilter)}"
                ////var recipeFilter: Stream<RecipeModel>? = recipes.stream().filter{ s:RecipeModel -> s.getRecipeName() == s.toString() }
                ////Log.println(Log.INFO,"Buscando:", s.toString())
                ////Log.println(Log.INFO,"Recipe", gson.toJson(recipeFilter))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(st: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })

        val intent = Intent(this, DetailRecipeActivity()::class.java)
        //intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
    }

    private fun adapterOnClick(s: RecipeModel) {
        val intent = Intent(this, DetailRecipeActivity()::class.java)
        //intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
    }
}