package com.sfaci.noticias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sfaci.noticias.base.Noticia;
import com.sfaci.noticias.db.BaseDatos;
import com.sfaci.noticias.util.Util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AltaNoticia extends AppCompatActivity
    implements View.OnClickListener{

    boolean modificar;
    int idNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_noticia);

        Intent intent = getIntent();
        Noticia noticia = (Noticia) intent.getSerializableExtra("noticia");
        if (noticia != null) {
            modificar = true;
            rellenarFormulario(noticia);
        }

        Button btAnadir = (Button) findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText etTitulo = (EditText) findViewById(R.id.etTitulo);
        EditText etTexto = (EditText) findViewById(R.id.etTexto);
        EditText etAutor = (EditText) findViewById(R.id.etAutor);
        EditText etFecha = (EditText) findViewById(R.id.etFecha);

        String titulo = etTitulo.getText().toString();
        String texto = etTexto.getText().toString();
        String autor = etAutor.getText().toString();
        String fecha = etFecha.getText().toString();

        try {
            Noticia noticia = new Noticia();
            noticia.setTitulo(titulo);
            noticia.setTexto(texto);
            noticia.setAutor(autor);
            noticia.setFecha(Util.parseFecha(fecha));

            BaseDatos db = new BaseDatos(this);

            if (modificar) {
                noticia.setId(idNoticia);
                db.modificarNoticia(noticia);
            }
            else {
                db.insertarNoticia(noticia);
            }
        } catch (ParseException pe) {
            Toast.makeText(this, R.string.mensaje_parseo_fecha,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void rellenarFormulario(Noticia noticia) {

        EditText etTitulo = (EditText) findViewById(R.id.etTitulo);
        EditText etTexto = (EditText) findViewById(R.id.etTexto);
        EditText etAutor = (EditText) findViewById(R.id.etAutor);
        EditText etFecha = (EditText) findViewById(R.id.etFecha);

        etTitulo.setText(noticia.getTitulo());
        etTexto.setText(noticia.getTexto());
        etFecha.setText(Util.formatFecha(noticia.getFecha()));
        etAutor.setText(noticia.getAutor());

        idNoticia = noticia.getId();
    }
}
