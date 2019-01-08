package com.sfaci.eventos.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.sfaci.eventos.R;

public class MapaActivity extends Activity
        implements OnMapReadyCallback, MapboxMap.OnMapClickListener {

    MapView mapa;
    MapboxMap mapaBox;
    double latitud, longitud;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, "pk.eyJ1Ijoic2ZhY2kiLCJhIjoiY2pwdTZiNDcxMDQ3NTQ4cXJicW51a2VjMSJ9.eeBDqqDQ8ELlz34avq9awg");

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        latitud = intent.getDoubleExtra("latitud", 0);
        longitud = intent.getDoubleExtra("longitud", 0);

        setContentView(R.layout.activity_mapa);
        mapa = (MapView) findViewById(R.id.mapa);
        mapa.onCreate(savedInstanceState);
        mapa.getMapAsync(this);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        mapaBox = mapboxMap;
        mapaBox.addMarker(new MarkerOptions()
            .setPosition(new LatLng(latitud, longitud))
            .setTitle(nombre));

        CameraPosition posicion = new CameraPosition.Builder()
                .target(new LatLng(latitud, longitud)) // Fija la posición
                .zoom(15) // Fija el nivel de zoom
                .tilt(30) // Fija la inclinación de la cámara
                .build();
        mapaBox.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                        posicion), 7000);
        addListener();
    }

    private void addListener() {
        mapaBox.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
        mapaBox.addMarker(new MarkerOptions()
                .position(point)
                .title("Eiffel Tower")
        );
    }
}
