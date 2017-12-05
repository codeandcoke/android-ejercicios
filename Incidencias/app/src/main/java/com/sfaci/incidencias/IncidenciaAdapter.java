package com.sfaci.incidencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sfaci.incidencias.com.sfaci.incidencias.base.Incidencia;

import java.util.ArrayList;


public class IncidenciaAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Incidencia> incidencias;

    public IncidenciaAdapter(Context contexto,
                             ArrayList<Incidencia> incidencias) {

        inflater = LayoutInflater.from(contexto);
        this.incidencias = incidencias;
    }

    static class ViewHolder {
        ImageView icono;
        TextView titulo;
        TextView calle;
        TextView motivo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.incidencia, null);

            holder = new ViewHolder();
            holder.icono = (ImageView) convertView.findViewById(R.id.ivIcono);
            holder.titulo = (TextView) convertView.findViewById(R.id.tvTitulo);
            holder.calle = (TextView) convertView.findViewById(R.id.tvCalle);
            holder.motivo = (TextView) convertView.findViewById(R.id.tvMotivo);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Incidencia incidencia = incidencias.get(position);
        holder.titulo.setText(incidencia.getTitulo());
        holder.calle.setText(incidencia.getCalle());
        holder.motivo.setText(incidencia.getMotivo());

        return convertView;
    }

    @Override
    public int getCount() {
        return incidencias.size();
    }

    @Override
    public Object getItem(int i) {
        return incidencias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return incidencias.get(i).getId();
    }
}
