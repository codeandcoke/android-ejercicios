package com.sfaci.eventos.base;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.sfaci.eventos.util.Util;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {

    private long id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private float precio;
    private Date fecha;
    private int aforo;
    private transient Bitmap imagen;

    public Evento() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String toString() {
        return nombre;
    }
}
