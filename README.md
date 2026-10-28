# Mobile Developer Code Challenge - Recipe App (Android)
> Desarrollado por Everardo Misael Enríquez Rodríguez

Aplicación Android nativa desarrollada en Kotlin para consultar, filtrar y geolocalizar recetas de cocina mediante la integración de APIs RESTful y servicios de Google Maps.

## 🚀 Tecnologías y Herramientas
* **Lenguaje:** Kotlin
* **Librerías principales:** OkHttp / Retrofit / Gson, Google Maps SDK, SupportMapFragment.
* **Componentes Android:** RecyclerView, Custom Adapters, Layouts de UI Nativos.
* **Funcionalidades:** Consumo de servicios JSON, filtrado dinámico (por nombre e ingredientes), manejo de mapas e itinerarios con coordenadas dinámicas.

## 📱 Pantallas y Funcionalidades Principales

### 1. Home & Búsqueda
* Carga asíncrona y deserialización de datos JSON desde servicio web.
* Filtrado en tiempo real en la lista de elementos mediante parámetros clave (nombre y etiquetas de ingredientes).

### 2. Detalle de Receta
* Transferencia de datos entre vistas y renderizado dinámico de detalles del platillo.

### 3. Mapeo de Origen / Ubicación
* Fragmento interactivo de **Google Maps SDK** con asignación de marcadores y posicionamiento de cámara dinámico (`SupportMapFragment`).
