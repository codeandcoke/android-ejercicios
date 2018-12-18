package com.sfaci.eventos.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sfaci.eventos.R;
import com.sfaci.eventos.adapters.EventoAdapter;
import com.sfaci.eventos.base.Evento;
import com.sfaci.eventos.util.Constantes;
import com.sfaci.eventos.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private List<Evento> eventos;
    private EventoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventos = new ArrayList<>();
        ListView lvEventos = findViewById(R.id.lvEventos);
        adapter = new EventoAdapter(this,
                R.layout.item_evento, eventos);
        lvEventos.setAdapter(adapter);

        DescargaDatos descarga = new DescargaDatos();
        descarga.execute(Constantes.URL);
    }

    @Override
    protected void  onResume() {
        super.onResume();

    }

    private class DescargaDatos extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog;
        private String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conexion = (HttpURLConnection)
                        url.openConnection();
                // Lee el fichero de datos y genera una cadena de texto como resultado
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linea = null;

                while ((linea = br.readLine()) != null)
                    sb.append(linea + "\n");

                conexion.disconnect();
                br.close();
                resultado = sb.toString();

                // TODO Parsear el JSON
                JSONObject json = new JSONObject(resultado);


            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle(R.string.mensaje_cargando);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (dialog != null)
                dialog.dismiss();

            Toast.makeText(getApplicationContext(), String.valueOf(resultado.length()), Toast.LENGTH_LONG).show();
        }
    }



}
