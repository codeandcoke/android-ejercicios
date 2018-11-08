package com.sfaci.eventos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sfaci.eventos.R;
import com.sfaci.eventos.base.Evento;

import java.util.List;

public class EventoAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int idLayout;
    private List<Evento> eventos;

    public EventoAdapter(Context contexto, int idLayout,
                         List<Evento> eventos) {

        inflater = LayoutInflater.from(contexto);
        this.idLayout = idLayout;
        this.eventos = eventos;
    }

    static class ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvDescripcion;
        TextView tvPrecio;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);

            holder = new ViewHolder();
            holder.ivImagen = convertView.findViewById(R.id.ivImagen);
            holder.tvNombre = convertView.findViewById(R.id.tvNombre);
            holder.tvDescripcion = convertView.findViewById(R.id.tvDescripcion);
            holder.tvPrecio = convertView.findViewById(R.id.tvPrecio);

            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        Evento evento = eventos.get(posicion);
        holder.ivImagen.setImageBitmap(evento.getImagen());
        holder.tvNombre.setText(evento.getNombre());
        holder.tvDescripcion.setText(evento.getDescripcion());
        holder.tvPrecio.setText(String.valueOf(evento.getPrecio()));

        return convertView;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return eventos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return eventos.get(posicion).getId();
    }
}
