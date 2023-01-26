package com.example.reto2.network;

import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CursosFacadeGetById extends NetConfiguration implements Runnable {
    private Cursos curso;
    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/materias/{id}";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.curso = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer curso = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    curso.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = curso.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Cursos cursoById= new Cursos();
                this.curso = new Cursos();

                Cursos curs;

                JSONObject object = mainArray.getJSONObject(cursoById.getIdCurso());

                curs = new Cursos();
                curs.setIdCurso((Integer) object.getInt("idCurso"));
                curs.setNombreCurso((String) object.getString("nombreCurso"));
                curs.setNivelCurso((String) object.getString("nivelCurso"));
                curs.setNombreProfe((String) object.getString("nombreProfe"));

                //TODO - Aqui yo entiendo que faltaria la lista de profesores y su id??
                //TODO - no se si poner las dos directamente asi o hay que hacer algo mas
                //curs.setProfesores((Profesores) object.get("profesores"));
                //curs.setIdProfesor((String) object.get("idProfesor"));

                //TODO - AL DEVOLVER LOS DATOS NO ESTOY SEGURO SI ESTA BIEN, CREO QUE NO DEVUELVE NADA

            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Cursos getResponse() {
        return curso;
    }
}

