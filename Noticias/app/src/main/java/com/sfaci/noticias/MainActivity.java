package com.sfaci.noticias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sfaci.noticias.adapters.NoticiaAdapter;
import com.sfaci.noticias.base.Noticia;
import com.sfaci.noticias.db.BaseDatos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NoticiaAdapter adapter;
    private ArrayList<Noticia> noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticias = new ArrayList<>();
        ListView lvNoticias = (ListView) findViewById(R.id.lvNoticias);
        adapter = new NoticiaAdapter(this, noticias);
        lvNoticias.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        noticias.clear();

        BaseDatos db = new BaseDatos(this);
        noticias.addAll(db.obtenerNoticias());

        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_alta:
                Intent intent = new Intent(this, AltaNoticia.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
