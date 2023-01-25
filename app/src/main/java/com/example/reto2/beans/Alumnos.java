package com.example.reto2.beans;

import java.util.List;

public class Alumnos {

    private Integer idAlum;
    private Integer idUser;
    private String nombreAlum;
    private String disponibilidad;
    private String tipoClase;
    private List<Materias> materiaElegida;
    private List<Cursos> cursoElegido;
    private String nivelMateria;
    private Usuarios usuarios;

    public  Alumnos() {

    }

    public Integer getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(Integer idAlum) {
        this.idAlum = idAlum;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNombreAlum() {
        return nombreAlum;
    }

    public void setNombreAlum(String nombreAlum) {
        this.nombreAlum = nombreAlum;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }

    public List<Materias> getMateriaElegida() {
        return materiaElegida;
    }

    public void setMateriaElegida(List<Materias> materiaElegida) {
        this.materiaElegida = materiaElegida;
    }

    public List<Cursos> getCursoElegido() {
        return cursoElegido;
    }

    public void setCursoElegido(List<Cursos> cursoElegido) {
        this.cursoElegido = cursoElegido;
    }

    public String getNivelMateria() {
        return nivelMateria;
    }

    public void setNivelMateria(String nivelMateria) {
        this.nivelMateria = nivelMateria;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }


    @Override
    public String toString() {
        return "Alumnos{" +
                "idAlum=" + idAlum +
                ", idUser=" + idUser +
                ", nombreAlum='" + nombreAlum + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", tipoClase='" + tipoClase + '\'' +
                ", materiaElegida=" + materiaElegida +
                ", cursoElegido=" + cursoElegido +
                ", nivelMateria='" + nivelMateria + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}

