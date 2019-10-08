package com.centroafuera.mistareas;

public class Tarea {

    private String nombre;
    private boolean hecha;

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

    @Override
    public String toString() {
        return nombre;
    }
}
