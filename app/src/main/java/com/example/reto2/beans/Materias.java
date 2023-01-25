package com.example.reto2.beans;

import java.io.Serializable;
import java.util.List;

public class Materias implements Serializable {
    private static final long serialVersionUID = 5052820099038526797L;

    private Integer idMateria;
    private String nombreMateria;
    private String nivelMateria;
    private String tipoDeClase;
    private String numeroHoras;
    private List<Alumnos> alumno;
    private List<Profesores> idProfe;

    public Materias() {
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNivelMateria() {
        return nivelMateria;
    }

    public void setNivelMateria(String nivelMateria) {
        this.nivelMateria = nivelMateria;
    }

    public String getTipoDeClase() {
        return tipoDeClase;
    }

    public void setTipoDeClase(String tipoDeClase) {
        this.tipoDeClase = tipoDeClase;
    }

    public String getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(String numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public List<Alumnos> getAlumno() {
        return alumno;
    }

    public void setAlumno(List<Alumnos> alumno) {
        this.alumno = alumno;
    }

    public List<Profesores> getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(List<Profesores> idProfe) {
        this.idProfe = idProfe;
    }

    @Override
    public String toString() {
        return "Materias{" +
                "idMateria=" + idMateria +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", nivelMateria='" + nivelMateria + '\'' +
                ", tipoDeClase='" + tipoDeClase + '\'' +
                ", numeroHoras='" + numeroHoras + '\'' +
                ", alumno=" + alumno +
                ", idProfe=" + idProfe +
                '}';
    }
}