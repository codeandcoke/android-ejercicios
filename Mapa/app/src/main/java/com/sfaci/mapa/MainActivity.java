package com.sfaci.mapa;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class MainActivity extends Activity
    implements OnMapReadyCallback, MapboxMap.OnMapClickListener{

    MapView mapa;
    MapboxMap mapaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, "pk.eyJ1Ijoic2ZhY2kiLCJhIjoiY2l2dHdha2FqMDAzNzJ0bXdnY3pyM3VoYyJ9.vTeb0q2LjHQdYQxeWxbnbQ");

        setContentView(R.layout.activity_main);
        mapa = (MapView) findViewById(R.id.mapa);
        mapa.onCreate(savedInstanceState);
        mapa.getMapAsync(this);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        mapaBox = mapboxMap;
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
