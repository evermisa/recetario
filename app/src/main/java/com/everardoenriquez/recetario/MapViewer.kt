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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_viewer)

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        var coords:LatLng = LatLng(25.7518001,-100.2233057)
        p0.addMarker(MarkerOptions().position(coords).title("Coords"))
        p0.moveCamera(CameraUpdateFactory.newLatLng(coords))
    }
}