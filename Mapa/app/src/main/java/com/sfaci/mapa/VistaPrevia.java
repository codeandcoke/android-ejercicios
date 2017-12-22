package com.sfaci.mapa;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class VistaPrevia extends AppCompatActivity {

    MapView mapaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, "pk.eyJ1Ijoic2ZhY2kiLCJhIjoiY2l2dHdha2FqMDAzNzJ0bXdnY3pyM3VoYyJ9.vTeb0q2LjHQdYQxeWxbnbQ");
        setContentView(R.layout.activity_vista_previa);

        mapaView = (MapView) findViewById(R.id.mapa);
        mapaView.onCreate(savedInstanceState);
    }


}
