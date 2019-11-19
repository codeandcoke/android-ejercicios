package com.centroafuera.mistareas;

import android.graphics.Bitmap;

import java.time.LocalDate;

public class Tarea {

    private long id;
    private String nombre;
    private boolean hecha;
    private Bitmap imagen;

    public Tarea(long id, String nombre, boolean hecha) {
        this.id = id;
        this.nombre = nombre;
        this.hecha = hecha;
    }

    public Tarea(String nombre) {
        this.nombre = nombre;
        hecha = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaHecha() {
        return hecha;
    }

    public void hacer() {
        hecha = true;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
