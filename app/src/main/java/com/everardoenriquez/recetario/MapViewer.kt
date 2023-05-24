package com.everardoenriquez.recetario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapViewer : AppCompatActivity(), OnMapReadyCallback  {
    var latitude:Double = 0.0
    var longitude:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_viewer)

        //Obtención de valores de latitude y longitud para paso posterior al mapa
        latitude = intent.getDoubleExtra("Latitude", 0.0)
        longitude = intent.getDoubleExtra("Longitude",0.0)

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        //Se asginan las coordenadas obtenidas al mapa y se agrega un marcador
        var coordinates = LatLng(latitude,longitude)
        p0.addMarker(MarkerOptions().position(coordinates).title("Coords"))
        p0.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
    }
}