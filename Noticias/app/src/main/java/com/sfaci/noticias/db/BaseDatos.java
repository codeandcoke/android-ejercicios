package com.sfaci.noticias.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sfaci.noticias.base.Noticia;
import com.sfaci.noticias.util.Util;

import static com.sfaci.noticias.util.Constantes.AUTOR;
import static com.sfaci.noticias.util.Constantes.FECHA;
import static com.sfaci.noticias.util.Constantes.TABLA_NOTICIAS;
import static com.sfaci.noticias.util.Constantes.TEXTO;
import static com.sfaci.noticias.util.Constantes.TITULO;


public class BaseDatos extends SQLiteOpenHelper {

    private static final String NOMBRE = "noticias.db";
    private static final int VERSION = 1;

    public BaseDatos(Context context) {
        super(context, NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_NOTICIAS + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT," +
                TITULO + " TEXT, " + TEXTO + " TEXT, " +
                AUTOR + " TEXT, " + FECHA + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLA_NOTICIAS);
        onCreate(db);
    }

    public void insertarNoticia(Noticia noticia) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(TITULO, noticia.getTitulo());
        valores.put(TEXTO, noticia.getTexto());
        valores.put(AUTOR, noticia.getAutor());
        valores.put(FECHA, Util.formatFecha(noticia.getFecha()));

        db.insertOrThrow(TABLA_NOTICIAS, null, valores);
    }
}
