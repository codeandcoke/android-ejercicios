package com.sfaci.noticias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sfaci.noticias.R;
import com.sfaci.noticias.base.Noticia;

import java.util.ArrayList;

/**
 * Created by dam on 26/10/17.
 */

public class NoticiaAdapter extends BaseAdapter {

    private ArrayList<Noticia> noticias;
    private Context contexto;
    private LayoutInflater inflater;

    public NoticiaAdapter(Context contexto,
                          ArrayList<Noticia> noticias) {
        this.contexto = contexto;
        this.noticias = noticias;
        inflater = LayoutInflater.from(contexto);

    }

    static class ViewHolder {
        TextView tvTitulo;
        TextView tvFragmento;
        TextView tvAutorFecha;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.noticia, null);

            viewHolder = new ViewHolder();
            viewHolder.tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
            viewHolder.tvFragmento = (TextView) view.findViewById(R.id.tvFragmento);
            viewHolder.tvAutorFecha = (TextView) view.findViewById(R.id.tvAutorFecha);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return null;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
