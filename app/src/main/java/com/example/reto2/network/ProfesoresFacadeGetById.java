package com.example.reto2.network;

import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;
import com.example.reto2.beans.Usuarios;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProfesoresFacadeGetById extends NetConfiguration implements Runnable {
    private Profesores profesor;
    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/profesores/{idUser}";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.profesor = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer profesor = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    profesor.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = profesor.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Profesores profesorById= new Profesores();
                this.profesor = new Profesores();

                Profesores profe;

                JSONObject object = mainArray.getJSONObject(profesorById.getIdUser());

                profe = new Profesores();
                profe.setIdProf((Integer) object.getInt("idProfe"));
                profe.setIdUser((Integer) object.getInt("idUser"));
                profe.setDisponibilidad((String) object.getString("disponibilidad"));
                profe.setTitulacion((String) object.getString("titulacion"));
                profe.setNivelEnsenanza((String) object.getString("nivelEnsenanza"));
                profe.setNombreprofe((String) object.getString("nombreProfe"));
                profe.setApuntes((List<Apuntes>) object.getJSONArray("apuntes"));
                profe.setMateriaImpartida((List<Materias>) object.getJSONArray("materiaImpartida"));
                profe.setCursoImp((List<Cursos> ) object.getJSONArray("cursoImp"));

        //TODO - AL DEVOLVER LOS DATOS NO ESTOY SEGURO SI ESTA BIEN, CREO QUE NO DEVUELVE NADA

            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Profesores getResponse() {
        return profesor;
    }
}

