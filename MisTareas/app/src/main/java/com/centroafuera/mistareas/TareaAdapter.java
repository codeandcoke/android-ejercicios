package com.centroafuera.mistareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TareaAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<Tarea> tareas;
    private LayoutInflater layoutInflater;

    public TareaAdapter(Context contexto, ArrayList<Tarea> tareas) {
        this.contexto = contexto;
        this.tareas = tareas;
        layoutInflater = LayoutInflater.from(contexto);
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView hecha;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            // Hay que inflar el layout de la fila
            convertView = layoutInflater.inflate(R.layout.item_tarea, null);
            viewHolder = new ViewHolder();
            viewHolder.imagen = convertView.findViewById(R.id.ivImagen);
            viewHolder.nombre = convertView.findViewById(R.id.tvNombre);
            viewHolder.hecha = convertView.findViewById(R.id.tvHecha);
            convertView.setTag(viewHolder);
        } else {
            // El layout de la fila ya está inflado
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Tarea tarea = tareas.get(position);
        viewHolder.imagen.setImageBitmap(tarea.getImagen());
        viewHolder.nombre.setText(tarea.getNombre());
        viewHolder.hecha.setText(tarea.estaHecha() ? "Hecha" : "Sin Hacer");

        return convertView;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
