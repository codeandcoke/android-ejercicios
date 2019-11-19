package com.centroafuera.mistareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.centroafuera.mistareas.Constantes.BASEDATOS;
import static com.centroafuera.mistareas.Constantes.HECHA;
import static com.centroafuera.mistareas.Constantes.IMAGEN;
import static com.centroafuera.mistareas.Constantes.NOMBRE;
import static com.centroafuera.mistareas.Constantes.TABLA_TAREAS;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private final String[] SELECT = new String[]{_ID, NOMBRE, HECHA, IMAGEN};


    public Database(Context contexto) {
        super(contexto, BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_TAREAS +
                " (" +_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE + " TEXT, " + HECHA + " INTEGER," +
                IMAGEN + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_TAREAS);
        onCreate(db);
    }

    public void nuevaTarea(Tarea tarea) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, tarea.getNombre());
        valores.put(HECHA, tarea.estaHecha());
        valores.put(IMAGEN, Util.getBytes(tarea.getImagen()));
        db.insertOrThrow(TABLA_TAREAS, null, valores);
    }

    public ArrayList<Tarea> getLista(Cursor cursor) {
        ArrayList<Tarea> tareas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Tarea tarea = new Tarea(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getInt(2) >= 1);
            tarea.setImagen(Util.getBitmap(cursor.getBlob(3)));
            tareas.add(tarea);
        }

        return tareas;
    }

    public ArrayList<Tarea> getTareas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareasHechas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareasPendientes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareas(String busqueda) {
        return null;
    }

    public void eliminarTarea(Tarea tareaEliminada) {

    }

    public void modificarTarea(Tarea tarea) {

    }
}
