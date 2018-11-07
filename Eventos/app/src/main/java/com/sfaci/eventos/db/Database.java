package com.sfaci.eventos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sfaci.eventos.base.Evento;

import java.util.List;

import static com.sfaci.eventos.util.Constantes.DATABASE_NAME;
import static com.sfaci.eventos.util.Constantes.VERSION;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void nuevoEvento(Evento evento) {

    }

    public void eliminarEvento(Evento evento) {

    }

    public void modificarEvento(Evento evento) {

    }

    public List<Evento> getEventos() {

        return null;
    }

    public List<Evento> getEventos(String busqueda) {

        return null;
    }
}
