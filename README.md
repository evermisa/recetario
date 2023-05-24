# Introducción

Aplicación elaborada para "Code Challenge Yape – Mobile Developer" hecha en kotlin por Everardo Misael Enriquez Rodriguez

## Contenido

Este programa cuenta con 3 pantallas principales las cuáles son: 

### Home

En esta pantalla es donde se muestra el listado de recetas, así como un buscador que filtra por el nombre del platillo o los ingredientes que la componen.

Para el funcionamiento de está pantalla primero cree una lista de objetos "RecipeModel" el cuál almacenará todas las recetas obtenidas.

```kotlin
var recipes: MutableList<RecipeModel> = ArrayList()
```

Está información será llenada en la respuesta del servicio a la hora de obtener los datos en JSON, y posteriormente convertida al listado "recipes" presentado arriba

```kotlin
override fun onResponse(call: Call, response: Response) {
    if (response.code() == 200) {
        //Obtenemos la información del servicio en un JSON y lo convertimos a objeto
        val recipeArrayType = object : TypeToken<Array<RecipeModel>>() {}.type
        var jsonArray: Array<RecipeModel> =
            gson.fromJson(response.body()?.string(), recipeArrayType)
        for (i in jsonArray.indices) {
            recipes.add(jsonArray.get(i))
        }
        mHandler.post(Runnable {
            recipeListRecycler.adapter = recipeAdapter
        })
    } else {
        Log.println(Log.ERROR,"${response.code()}", "${response.body()?.string()}")
    }
}
```

Teniendo estos datos almacenados los podremos filtrar a través de una función personalizada en el adapter, en este caso tomaremos 2 valores para hacer la búsqueda y el filtrado, que son el nombre del platillo y los ingredientes de este

```kotlin
var recipeFilter: List<RecipeModel> = recipes.filter {
        s -> s.recipeName.contains(searchBar.text, true) ||
        s.tags.contains(searchBar.text, true)
}
recipeAdapter.onFilter(recipeFilter)
```

### Pantalla de detalle

En está pantalla obtenemos los datos de la pantalla anterior al dar clic sobre algún elemento, obtenemos la información recolectada y la colocamos sobre los diferentes elementos.

```kotlin
detailItemPhoto.setImageResource(filePhoto)
recipeTitle.text = recipe.recipeName
detailInfo.text = recipe.description
var ingredients = ""
for (i in recipe.ingredients) {
    ingredients += "■ $i \n"
}
ingredientsInfo.text = ingredients
```

### Pantalla de mapa

Para está pantalla utilizamos un fragment en el xml que nos permita colocar un mapa de google sobre ella

```xml
<fragment
    android:id="@+id/map_view"
    android:name="com.google.android.gms.maps.SupportMapFragment"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:layout_margin="5dp"
    map:cameraZoom="10"
    app:layout_constraintBottom_toBottomOf="parent"
    map:layout_constraintEnd_toEndOf="parent"
    map:layout_constraintStart_toStartOf="parent"
    map:layout_constraintTop_toBottomOf="@+id/title" />
```

Por último, con los datos obtenidos del objeto en la pantalla anterior, encontramos las coordenadas y las pasamos al programa con el siguiente código

```kotlin
override fun onMapReady(p0: GoogleMap) {
    //Se asginan las coordenadas obtenidas al mapa y se agrega un marcador
    var coordinates = LatLng(latitude,longitude)
    p0.addMarker(MarkerOptions().position(coordinates).title("Coords"))
    p0.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
}
```
