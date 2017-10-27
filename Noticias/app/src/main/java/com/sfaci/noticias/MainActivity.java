package com.sfaci.noticias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import com.sfaci.noticias.adapters.NoticiaAdapter;
import com.sfaci.noticias.base.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Noticia> noticias = new ArrayList<>();

        // Poblar la lista de noticias
        // De la Base de Datos o de donde sea
        Noticia noticia = new Noticia();
        noticia.setTitulo("Atropello en el tranvía");
        noticia.setTexto("Han atropellado a un tio hoy");
        noticia.setAutor("Guillermo Mate");
        noticias.add(noticia);
        noticia = new Noticia();
        noticia.setTitulo("Atropello en el tranvía2");
        noticia.setTexto("Han atropellado a un tio hoy2");
        noticia.setAutor("Guillermo Mate2");
        noticias.add(noticia);

        ListView lvNoticias = (ListView) findViewById(R.id.lvNoticias);
        NoticiaAdapter adapter = new NoticiaAdapter(this, noticias);
        lvNoticias.setAdapter(adapter);
    }
}
