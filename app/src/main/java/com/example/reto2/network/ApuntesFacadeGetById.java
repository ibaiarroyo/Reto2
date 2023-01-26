package com.example.reto2.network;

import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Cursos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApuntesFacadeGetById extends NetConfiguration implements Runnable {
    private Apuntes apunte;
    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/apuntes/{idApunte}";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.apunte = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer apunte = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    apunte.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = apunte.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Apuntes apunteById= new Apuntes();
                this.apunte = new Apuntes();

                Apuntes apunt;

                JSONObject object = mainArray.getJSONObject(apunteById.getIdApunte());

                apunt = new Apuntes();
                apunt.setIdApunte((Integer) object.getInt("idApunte"));
                apunt.setNombreApunte((String) object.getString("nombreApunte"));
                apunt.setNivelEstudio((String) object.getString("nivelEstudio"));
                apunt.setUrl((String)object.getString("url"));

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
    public Apuntes getResponse() {
        return apunte;
    }
}
