package com.example.reto2.beans;

import java.io.Serializable;

public class Cursos implements Serializable {
    private static final long serialVersionUID = 5052820099038526797L;

    private Integer idCurso;
    private String nombreCurso;
    private String nivelCurso;
    private String nombreProfe;
    private Profesores profesores;
    private String idProfesor;

    public Cursos() {
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNivelCurso() {
        return nivelCurso;
    }

    public void setNivelCurso(String nivelCurso) {
        this.nivelCurso = nivelCurso;
    }

    public String getNombreProfe() {
        return nombreProfe;
    }

    public void setNombreProfe(String nombreProfe) {
        this.nombreProfe = nombreProfe;
    }

    public Profesores getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesores profesores) {
        this.profesores = profesores;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "idCurso=" + idCurso +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", nivelCurso='" + nivelCurso + '\'' +
                ", nombreProfe='" + nombreProfe + '\'' +
                ", profesores=" + profesores +
                ", idProfesor='" + idProfesor + '\'' +
                '}';
    }
}
