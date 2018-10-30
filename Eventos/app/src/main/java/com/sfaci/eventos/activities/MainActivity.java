package com.sfaci.eventos.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sfaci.eventos.R;
import com.sfaci.eventos.adapters.EventoAdapter;
import com.sfaci.eventos.base.Evento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    List<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poblarArrayList();

        ListView lvEventos = findViewById(R.id.lvEventos);
        EventoAdapter adapter = new EventoAdapter(this,
                R.layout.item_evento, eventos);
        lvEventos.setAdapter(adapter);
    }

    private void poblarArrayList() {

        eventos = new ArrayList<>();
        Evento evento = new Evento();
        evento.setNombre("Estudiar");
        evento.setDescripcion("Estudiar en la biblioteca");
        evento.setPrecio(0);
        evento.setDireccion("La biblioteca");
        evento.setFecha(new Date());
        evento.setAforo(2400);
        eventos.add(evento);
        Evento evento2 = new Evento();
        evento2.setNombre("Instalar el ADSL de Fernando");
        evento2.setDescripcion("Poner en marcha el ADSL");
        evento2.setPrecio(50);
        evento2.setDireccion("Casa de Fernando");
        evento2.setFecha(new Date());
        evento2.setAforo(100);
        eventos.add(evento2);
    }
}
