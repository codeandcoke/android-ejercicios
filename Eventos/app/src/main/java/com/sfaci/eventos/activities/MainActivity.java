package com.sfaci.eventos.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sfaci.eventos.R;
import com.sfaci.eventos.adapters.EventoAdapter;
import com.sfaci.eventos.base.Evento;
import com.sfaci.eventos.db.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    List<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(this);
        eventos = db.getEventos();

        ListView lvEventos = findViewById(R.id.lvEventos);
        EventoAdapter adapter = new EventoAdapter(this,
                R.layout.item_evento, eventos);
        lvEventos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_alta_eventos:
                Intent intent = new Intent(this, AltaEventos.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
