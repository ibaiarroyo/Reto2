package com.example.reto2.network;

import com.example.reto2.beans.Alumnos;
import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MateriasFacadeGetById extends NetConfiguration implements Runnable {
    private Materias materia;
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
                this.materia = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer materia = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    materia.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = materia.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Materias materiaById= new Materias();
                this.materia = new Materias();

                Materias mater;

                JSONObject object = mainArray.getJSONObject(materiaById.getIdMateria());

                mater = new Materias();
                mater.setIdMateria((Integer) object.getInt("idMateria"));
                mater.setNombreMateria((String) object.getString("nombreMateria"));
                mater.setNivelMateria((String) object.getString("nivelMateria"));
                mater.setTipoDeClase((String) object.getString("tipoDeClase"));
                mater.setNumeroHoras((Integer) object.getInt("numeroHoras"));

                //TODO - Aqui yo entiendo que faltaria la lista de alumnos y profes
                //TODO - no se si poner las dos directamente asi o hay que hacer algo mas
                //mater.setAlumno((List<Alumnos> ) object.getJSONArray("alumno"));
                //mater.setIdProfe((List<Profesores>) object.getJSONArray("idProfe"));


            //TODO - AL DEVOLVER LOS DATOS NO ESTOY SEGURO SI ESTA BIEN, CREO QUE NO DEVUELVE NADA

            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Materias getResponse() {
        return materia;
    }
}

