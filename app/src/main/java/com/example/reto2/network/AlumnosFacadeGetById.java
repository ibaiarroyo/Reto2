package com.example.reto2.network;

import com.example.reto2.beans.Alumnos;
import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Usuarios;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AlumnosFacadeGetById extends NetConfiguration implements Runnable {
    private Alumnos alumno;
    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/alumnos/{idAlum}";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.alumno = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer alumno = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    alumno.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = alumno.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Alumnos apunteById= new Alumnos();
                this.alumno = new Alumnos();

                Alumnos alum;

                JSONObject object = mainArray.getJSONObject(apunteById.getIdAlum());

                alum = new Alumnos();
                alum.setIdAlum((Integer) object.getInt("idAlum"));
                alum.setIdUser((Integer) object.getInt("idUser"));
                alum.setNombreAlum((String) object.getString("nombreAlum"));
                alum.setDisponibilidad((String)object.getString("disponibilidad"));
                alum.setTipoClase((String)object.getString("tipoClase"));
                alum.setMateriaElegida((List<Materias>)object.getJSONArray("materiaElegida"));
                alum.setCursoElegido((List<Cursos>)object.getJSONArray("cursoElegido"));
                alum.setNivelMateria((String)object.getString("nivelMateria"));
                //TODO - Aqui yo entiendo que faltaria la lista de Usuarios lo pongo abajo
                //TODO - comentado por que no se si es asi
                //alum.setUsuarios((Usuarios) object.get("usuarios"));


                //TODO - AL DEVOLVER LOS DATOS NO ESTOY SEGURO SI ESTA BIEN, CREO QUE NO DEVUELVE NADA

            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Alumnos getResponse() {
        return alumno;
    }
}
