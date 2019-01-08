package com.sfaci.eventos.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sfaci.eventos.R;
import com.sfaci.eventos.adapters.EventoAdapter;
import com.sfaci.eventos.base.Evento;
import com.sfaci.eventos.util.Constantes;

import org.json.JSONArray;
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

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<Evento> eventos;
    private EventoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventos = new ArrayList<>();
        ListView lvEventos = (ListView) findViewById(R.id.lvEventos);
        adapter = new EventoAdapter(this,
                R.layout.item_evento, eventos);
        lvEventos.setAdapter(adapter);
        lvEventos.setOnItemClickListener(this);

        DescargaDatos descarga = new DescargaDatos();
        descarga.execute(Constantes.URL);
    }

    @Override
    protected void  onResume() {
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

        Evento evento = eventos.get(posicion);
        Intent intent = new Intent(this, MapaActivity.class);
        intent.putExtra("nombre", evento.getNombre());
        intent.putExtra("latitud", evento.getLatitud());
        intent.putExtra("longitud", evento.getLongitud());
        startActivity(intent);
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
                JSONArray jsonArray = json.getJSONArray("@graph");
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Evento evento = new Evento();
                        evento.setNombre(jsonArray.getJSONObject(i).getString("title"));
                        evento.setDescripcion(jsonArray.getJSONObject(i).getString("description"));
                        evento.setDireccion(jsonArray.getJSONObject(i).getString("event-location"));
                        evento.setPrecio(Float.parseFloat(jsonArray.getJSONObject(i).getString("price")));
                        evento.setFecha(new Date());
                        evento.setLatitud(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("latitude"));
                        evento.setLongitud(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("longitude"));
                        eventos.add(evento);
                    } catch (JSONException jsone) {
                        jsone.printStackTrace();
                    }
                }
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

            adapter.notifyDataSetChanged();
        }
    }



}
