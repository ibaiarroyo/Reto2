package com.example.reto2.beans;

import java.util.List;

public class Profesores {

    private Integer idProf;
    private Integer idUser;
    private String disponibilidad;
    private String titulacion;
    private String nivelEnsenanza;
    private String nombreprofe;
    private List<Apuntes> apuntes;
    private Usuarios usuarios;
    private List<Materias> materiaImpartida;
    private List<Cursos> cursoImp;

    public Profesores() {
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public String getNivelEnsenanza() {
        return nivelEnsenanza;
    }

    public void setNivelEnsenanza(String nivelEnsenanza) {
        this.nivelEnsenanza = nivelEnsenanza;
    }

    public String getNombreprofe() {
        return nombreprofe;
    }

    public void setNombreprofe(String nombreprofe) {
        this.nombreprofe = nombreprofe;
    }

    public List<Apuntes> getApuntes() {
        return apuntes;
    }

    public void setApuntes(List<Apuntes> apuntes) {
        this.apuntes = apuntes;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Materias> getMateriaImpartida() {
        return materiaImpartida;
    }

    public void setMateriaImpartida(List<Materias> materiaImpartida) {
        this.materiaImpartida = materiaImpartida;
    }

    public List<Cursos> getCursoImp() {
        return cursoImp;
    }

    public void setCursoImp(List<Cursos> cursoImp) {
        this.cursoImp = cursoImp;
    }

    @Override
    public String toString() {
        return "Profesores{" +
                "idProf=" + idProf +
                ", idUser=" + idUser +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", titulacion='" + titulacion + '\'' +
                ", nivelEnsenanza='" + nivelEnsenanza + '\'' +
                ", nombreprofe='" + nombreprofe + '\'' +
                ", apuntes=" + apuntes +
                ", usuarios=" + usuarios +
                ", materiaImpartida=" + materiaImpartida +
                ", cursoImp=" + cursoImp +
                '}';
    }
}

