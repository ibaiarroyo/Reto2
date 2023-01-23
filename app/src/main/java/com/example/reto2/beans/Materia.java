package com.example.reto2.beans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.reto2.R;

import java.io.Serializable;

public class Materia implements Serializable {
    private static final long serialVersionUID = 5052820099038526797L;

    private Integer id;
    private String Nombre;
    private String Nivel;
    private String TipoClase;
    private String Horas;

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Nivel='" + Nivel + '\'' +
                ", TipoClase='" + TipoClase + '\'' +
                ", Horas='" + Horas + '\'' +
                ", Lugar='" + Lugar + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getTipoClase() {
        return TipoClase;
    }

    public void setTipoClase(String tipoClase) {
        TipoClase = tipoClase;
    }

    public String getHoras() {
        return Horas;
    }

    public void setHoras(String horas) {
        Horas = horas;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    private String Lugar;
}