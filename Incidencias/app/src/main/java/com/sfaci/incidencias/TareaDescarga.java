package com.sfaci.incidencias;

import android.os.AsyncTask;
import android.widget.Adapter;

import com.sfaci.incidencias.com.sfaci.incidencias.base.Incidencia;
import com.sfaci.incidencias.util.Constantes;
import com.sfaci.incidencias.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import uk.me.jstott.jcoord.LatLng;


public class TareaDescarga extends AsyncTask<Void, Void, Void> {

    private ArrayList<Incidencia> incidencias;
    private IncidenciaAdapter adapter;

    public TareaDescarga(ArrayList<Incidencia> incidencias,
                         IncidenciaAdapter adapter) {
        this.incidencias = incidencias;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {

    try {

        URL url = new URL(Constantes.URL);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        // Lee el fichero de datos y genera una cadena de texto como resultado
        BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String linea = null;

        while ((linea = br.readLine()) != null)
            sb.append(linea + "\n");

        conexion.disconnect();
        br.close();
        String resultado = sb.toString();

        JSONObject json = new JSONObject(resultado);
        JSONArray jsonArray = json.getJSONArray("result");

        String id, titulo, calle, motivo, tramo, observaciones,
                descripcion, tipo;
        String coordenadas;
        String fechaInicio, fechaFin;

        Incidencia incidencia = null;
        for (int i = 0; i < jsonArray.length(); i++) {

            id = jsonArray.getJSONObject(i).getString("id");
            titulo = jsonArray.getJSONObject(i).getString("title");
            calle = jsonArray.getJSONObject(i).getString("link");
            motivo = jsonArray.getJSONObject(i).getString("motivo");
            tramo = jsonArray.getJSONObject(i).getString("tramo");
            observaciones = jsonArray.getJSONObject(i).getString("observaciones");
            descripcion = jsonArray.getJSONObject(i).getString("descripcion");
            tipo = jsonArray.getJSONObject(i).getJSONObject("tipo").getString("title");
            fechaInicio = jsonArray.getJSONObject(i).getString("inicio");
            fechaFin = jsonArray.getJSONObject(i).getString("fin");

            coordenadas = jsonArray.getJSONObject(i).getJSONObject("geometry").getString("coordinates");
            coordenadas = coordenadas.substring(1, coordenadas.length() - 1);
            String latlong[] = coordenadas.split(",");
            uk.me.jstott.jcoord.LatLng latLng = Util.DeUMTSaLatLng(
                    Double.parseDouble(latlong[0]),
                    Double.parseDouble(latlong[1]));

            incidencia = new Incidencia();
            incidencia.setId(Integer.parseInt(id));
            incidencia.setTitulo(titulo);
            incidencia.setCalle(calle);
            incidencia.setMotivo(motivo);
            incidencia.setTramo(tramo);
            incidencia.setObservaciones(observaciones);
            incidencia.setDescripcion(descripcion);
            incidencia.setTipo(tipo);
            incidencia.setLatitud(latLng.getLat());
            incidencia.setLongitud(latLng.getLng());
            incidencia.setInicio(Util.parseFecha(fechaInicio));
            incidencia.setFin(Util.parseFecha(fechaFin));
            incidencias.add(incidencia);
        }
    } catch (MalformedURLException mrule) {
        mrule.printStackTrace();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (JSONException jsone) {
        jsone.printStackTrace();
    } catch (ParseException pe) {
        pe.printStackTrace();
    }

    return null;

    }

    @Override
    protected void onPostExecute(Void resultado) {
        super.onPostExecute(resultado);

        adapter.notifyDataSetChanged();
    }
}
