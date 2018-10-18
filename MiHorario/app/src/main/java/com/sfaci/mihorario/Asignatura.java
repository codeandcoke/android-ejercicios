package com.sfaci.mihorario;

public class Asignatura {

    private String nombre;
    private String dia;
    private String hora;

    public Asignatura(String nombre, String dia, String hora) {
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
