package com.example.reto2.beans;

public class Apuntes {

    private Integer idApunte;
    private String nombreApunte;
    private String nivelEstudio;
    private String url;
    private Profesores profesores;
    private Integer idProfesor;


    public  Apuntes() {

    }

    public Integer getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(Integer idApunte) {
        this.idApunte = idApunte;
    }

    public String getNombreApunte() {
        return nombreApunte;
    }

    public void setNombreApunte(String nombreApunte) {
        this.nombreApunte = nombreApunte;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Profesores getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesores profesores) {
        this.profesores = profesores;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return "Apuntes{" +
                "idApunte=" + idApunte +
                ", nombreApunte='" + nombreApunte + '\'' +
                ", nivelEstudio='" + nivelEstudio + '\'' +
                ", url='" + url + '\'' +
                ", profesores=" + profesores +
                ", idProfesor=" + idProfesor +
                '}';
    }
}
