package com.example.reto2.Response;

public class RegisterResponse {

    private String mensajeRespuesta;
    private boolean acceso;
    private int sqlReturn;

    public RegisterResponse() {}

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public int getSqlReturn() {
        return sqlReturn;
    }

    public void setSqlReturn(int sqlReturn) {
        this.sqlReturn = sqlReturn;
    }
}
