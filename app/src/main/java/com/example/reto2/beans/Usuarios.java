package com.example.reto2.beans;

import java.util.Set;

public class Usuarios {
    private Integer idUser;
    private String email;
    private String nombreUser;
    private String password;
    private Set<Profesores> alumno;
    private Set<Profesores> profesor;
    private boolean isEnabled;

    public Usuarios() {

    }

    public Usuarios(Integer idUser,
                    String email,
                    String nombreUser,
                    String password,
                    Set<Profesores> alumno,
                    Set<Profesores> profesor,
                    boolean isEnabled) {
        this.idUser = idUser;
        this.email = email;
        this.nombreUser = nombreUser;
        this.password = password;
        this.alumno = alumno;
        this.profesor = profesor;
        this.isEnabled = isEnabled;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profesores> getAlumno() {
        return alumno;
    }

    public void setAlumno(Set<Profesores> alumno) {
        this.alumno = alumno;
    }

    public Set<Profesores> getProfesor() {
        return profesor;
    }

    public void setProfesor(Set<Profesores> profesor) {
        this.profesor = profesor;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "idUser=" + idUser +
                ", nombreUser='" + nombreUser + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", alumno=" + alumno +
                ", profesor=" + profesor +
                '}';
    }
}
