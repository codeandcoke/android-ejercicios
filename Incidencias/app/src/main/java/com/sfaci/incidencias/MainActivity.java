package com.sfaci.incidencias;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sfaci.incidencias.com.sfaci.incidencias.base.Incidencia;
import com.sfaci.incidencias.util.Constantes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Incidencia> incidencias;
    private IncidenciaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvIncidencias = (ListView) findViewById(R.id.lvIncidencias);
        incidencias = new ArrayList<>();
        adapter = new IncidenciaAdapter(this, incidencias);
        lvIncidencias.setAdapter(adapter);
    }


}
