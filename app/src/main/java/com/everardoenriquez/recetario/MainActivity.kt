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
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import java.util.stream.Stream

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    val recipes: MutableList<RecipeModel> = ArrayList()
    val jsonString:String = "[{\"nombre\":\"John\",\"descripción\":\"asda\",\"ingredientes\":[\"Ajo\",\"Pepino\"],\"coordenadas\":[123.1231,123.1122]},{\"nombre\":\"2\",\"descripción\":\"3\",\"ingredientes\":[\"Ajo\",\"Pepino\"],\"coordenadas\":[123.1231,123.1122]}]"
    val jsonStringPruebas:String = "[{\"recipeName\":\"Primero\",\"description\":\"Uno\",\"ingredients\":[\"1\",\"2\"]},{\"recipeName\":\"Segundo\",\"description\":\"Dos\",\"ingredients\":[\"1\",\"2\"]},{\"recipeName\":\"Tercero\",\"description\":\"Tres\",\"ingredients\":[\"1\",\"2\"]}]"
    val jsonDefinitive:String = "[\n" +
            "  {\n" +
            "  \t\"id\": 1,\n" +
            "    \"recipeName\": \"Pizza\",\n" +
            "    \"description\": \"Preparación culinaria elaborado con un pan plano, habitualmente de forma circular, cubierto con salsa de tomate y queso y horneado a altas temperaturas\",\n" +
            "    \"ingredients\": [\n" +
            "      \"1 kilogramo de harina\",\n" +
            "      \"1 cucharadita de sal fina\",\n" +
            "      \"2 tazas y media de agua tibia\",\n" +
            "      \"2 cucharadas soperas de aceite de oliva\",\n" +
            "      \"30 gramos de levadura fresca\",\n" +
            "      \"100 gramos de peperoni\"\n" +
            "    ],\n" +
            "    \"tags\":\"harina sal agua aceite de oliva levadura fresca peperoni\",\n" +
            "    \"coordinates\": [\n" +
            "      40.8224684,\n" +
            "      14.1959819\n" +
            "    ],\n" +
            "    \"imageName\":\"pizza\"\n" +
            "  },\n" +
            "  {\n" +
            "  \t\"id\": 2,\n" +
            "    \"recipeName\": \"Sushi\",\n" +
            "    \"description\": \"Pequeña porción de arroz en forma cilindrica envuelta en alga nori y relleno con algún ingrediente a elección\",\n" +
            "    \"ingredients\": [\n" +
            "      \"100 gramos de arroz para sushi\",\n" +
            "      \"Alga nori\",\n" +
            "      \"50 gramos de salmón fresco\",\n" +
            "      \"1 aguacate\",\n" +
            "      \"1 pepino\",\n" +
            "      \"1 cucharada de vinagre\",\n" +
            "      \"1 cucharada sal\",\n" +
            "      \"1 cucharada azúcar\"\n" +
            "    ],\n" +
            "    \"tags\":\"arroz salmón aguacate pepino vinagre sal azúcar\",\n" +
            "    \"coordinates\": [\n" +
            "      30.6573849,\n" +
            "      101.0457356\n" +
            "    ],\n" +
            "    \"imageName\":\"sushi\"\n" +
            "  },\n" +
            "  {\n" +
            "  \t\"id\": 3,\n" +
            "    \"recipeName\": \"Paella\",\n" +
            "    \"description\": \"Platillo tradicionalmente elaborado con Arroz Valencia de Grano Corto se utilizaba como receta de aprovechamiento para consumir las sobras de otras comidas\",\n" +
            "    \"ingredients\": [\n" +
            "      \"3 Cucharadas Aceite de oliva\",\n" +
            "\t    \"6 Piezas Salchicha de cerdo cortadas en rebanadas\",\n" +
            "\t    \"1 Pieza Pechuga de pollo deshuesada y cortada en cubos\",\n" +
            "\t    \"200 Gramos Cerdo y carne de cerdo cortada en cubos\",\n" +
            "\t    \"200 Gramos Chorizo cortada en trozas\",\n" +
            "\t    \"100 Gramos Ejotes limpios partidos en 3\",\n" +
            "\t    \"1 Pieza Pimiento morrón cortado en tiras\",\n" +
            "\t    \"1/2 Cucharadita Cebolla en polvo\",\n" +
            "\t    \"2 Cucharadas Sazonador Líquido\",\n" +
            "\t    \"1 Cucharadita Ajo en polvo\",\n" +
            "\t    \"2 1/2 Tazas Arroz precocido\",\n" +
            "\t    \"6 Tazas Agua\",\n" +
            "\t    \"1 Cucharadita Azafrán en polvo\",\n" +
            "\t    \"2 Cucharadas *Consomé de pollo en polvo\",\n" +
            "\t    \"3 Piezas Pescado cortados en cubos\",\n" +
            "\t    \"20 Piezas Camarón grandes y lavados\",\n" +
            "\t    \"100 Gramos Almeja limpias\",\n" +
            "\t    \"250 Gramos Mejillón azul limpios\"\n" +
            "    ],\n" +
            "    \"tags\":\"Aceite de oliva Salchicha de cerdo Pechuga de pollo deshuesada carne de cerdo Ejotes Pimiento morrón Cebolla Sazonador Ajo Arroz Agua Azafrán Consomé de pollo Pescado Camarón Almeja Mejillón\",\n" +
            "    \"coordinates\": [\n" +
            "      39.4079343,\n" +
            "      -0.5263232\n" +
            "    ],\n" +
            "    \"imageName\":\"paella\"\n" +
            "  },\n" +
            "  {\n" +
            "  \t\"id\": 4,\n" +
            "    \"recipeName\": \"Burrito\",\n" +
            "    \"description\": \"Tortilla de harina de trigo que se rellena con carne de vaca deshebrada, jitomate, chile y otros ingredientes\",\n" +
            "    \"ingredients\": [\n" +
            "      \"2 cucharadas soperas de aceite\",\n" +
            "\t  \"150 gramos de cebolla blanca\",\n" +
            "\t  \"2 pimientos verdes\",\n" +
            "\t  \"2 pimientos rojos\",\n" +
            "\t  \"medio kilogramo de carne picada\",\n" +
            "\t  \"2 gramos de sal\",\n" +
            "\t  \"3 pizcas de pimienta molida\",\n" +
            "\t  \"300 gramos de queso para fundir\",\n" +
            "\t  \"media docena de tortillas de harina\",\n" +
            "\t  \"80 gramos de frijoles refritos\"\n" +
            "    ],\n" +
            "    \"tags\":\"aceite cebolla pimientos verdes pimientos rojos carne picada sal pimienta molidaqueso tortillas de harina frijoles refritos\",\n" +
            "    \"coordinates\": [\n" +
            "      28.6711327,\n" +
            "      -106.2294322\n" +
            "    ],\n" +
            "    \"imageName\":\"pizza\"\n" +
            "  },\n" +
            "  {\n" +
            "  \t\"id\": 5,\n" +
            "    \"recipeName\": \"Hamburguesa\",\n" +
            "    \"description\": \"Sándwich de hamburguesa con un pan blando y redondo al que se le suelen añadir otros ingredientes o condimentos, como tomate, cebolla, queso, ketchup, mostaza, etc.\",\n" +
            "    \"ingredients\": [\n" +
            "      \"500 gr carne molida\",\n" +
            "\t  \"4 cucharadas de aceite\",\n" +
            "\t  \"1 cebolla chica picada en cubos pequeños\",\n" +
            "\t  \"1 cucharadita de Ají de Color Gourmet\",\n" +
            "\t  \"1 cucharadita de Ajo Granulado Gourmet\",\n" +
            "\t  \"1 cucharadita de Sal de Mar Gourmet\",\n" +
            "\t  \"1 cucharadita de Pimienta Blanca Gourmet\",\n" +
            "\t  \"1 cucharada de Orégano Entero Gourmet\",\n" +
            "\t  \"2 cucharaditas de mostaza\",\n" +
            "\t  \"2 cucharaditas de Salsa Inglesa Gourmet\",\n" +
            "\t  \"1 huevo\"\n" +
            "    ],\n" +
            "    \"tags\":\"carne molida aceite cebolla Ají Ajo Granulado Sal de Mar Pimienta Blanca Orégano mostaza Salsa Inglesa huevo\",\n" +
            "    \"coordinates\": [\n" +
            "      53.5585268,\n" +
            "      9.5981799\n" +
            "    ],\n" +
            "    \"imageName\":\"hamburguesa\"\n" +
            "  }\n" +
            "]"

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
        var jsonArray: Array<RecipeModel> = gson.fromJson(jsonDefinitive, recipeArrayType)
        //var jsonArray = JSONArray(jsonStringPruebas)
        for (i in jsonArray.indices) {
            //val jsonObject = jsonArray.getJSONObject(i)
            //val rpms: RecipeModel = gson.fromJson(jsonArray.getJSONObject(i), RecipeModel())
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
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")
        val request = Request.Builder()
            .url("http://demo6224271.mockable.io/")
            .post(body)
            .build()

        Toast.makeText(applicationContext,"La tostada",Toast.LENGTH_LONG).show()
        val recipeListRecycler: RecyclerView = findViewById(R.id.recipeListRecycler)
        val recipeAdapter = RecipeListItemAdapter (recipes){ s -> adapterOnClick(s)}
        //val headerAdapter = HeaderAdapter()
        //val concatAdapter = ConcatAdapter(headerAdapter, recipeAdapter)
        recipeListRecycler.adapter = recipeAdapter

        /*client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                textView.text = "${e.message}"
            }
            override fun onResponse(call: Call, response: Response) {
                //textView.text = "${response.code()}"
                //textView.text = "${response.body()?.string()}"
                val recipeArrayType = object : TypeToken<Array<RecipeModel>>() {}.type
                var jsonArray: Array<RecipeModel> = gson.fromJson(response.body()?.string(), recipeArrayType)
                for (i in 0..jsonArray.size-1) {
                    recipes.add(jsonArray.get(i))
                }
                /*var concat = ""
                for (x in recipes) {
                    concat += x.recipeName
                }*/
                //val recipeAdapter = RecipeListItemAdapter (recipes){ s -> adapterOnClick(s)}
                //val headerAdapter = HeaderAdapter()
                //val concatAdapter = ConcatAdapter(headerAdapter, recipeAdapter)
                //recipeListRecycler.adapter = concatAdapter
            }
        })*/
        //textView.text = "${recipes.size}"
        //recipeListRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        //textView.text = "${gson.toJson(recipes)}"
        //recipeListRecycler?.adapter = RecipeListItemAdapter(recipes)


        var searchBar: EditText = findViewById(R.id.editTextText)
        searchBar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                var recipeFilter: List<RecipeModel> = recipes.filter { s -> s.recipeName.contains(searchBar.text, true) || s.tags.contains(searchBar.text, true) }//.stream().filter{ s -> s.recipeName.contains(searchBar.text) }
                //textView.text = "${searchBar.text} esto es: ${gson.toJson(recipeFilter)}"
                ////var recipeFilter: Stream<RecipeModel>? = recipes.stream().filter{ s:RecipeModel -> s.getRecipeName() == s.toString() }
                ////Log.println(Log.INFO,"Buscando:", s.toString())
                ////Log.println(Log.INFO,"Recipe", gson.toJson(recipeFilter))
                recipeAdapter.onFilter(recipeFilter)
                //concatAdapter.notifyDataSetChanged()
                ///***recipeListRecycler.adapter = concatAdapter
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(st: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })

        //val intent = Intent(this, DetailRecipeActivity()::class.java)
        ////intent.putExtra(FLOWER_ID, flower.id)
        //startActivity(intent)
        //adapterOnClick(recipes[0])
    }

    private fun adapterOnClick(s: RecipeModel) {
        val intent = Intent(this, DetailRecipeActivity()::class.java)
        intent.putExtra("RECIPE_OBJ", s)
        startActivity(intent)
    }
}