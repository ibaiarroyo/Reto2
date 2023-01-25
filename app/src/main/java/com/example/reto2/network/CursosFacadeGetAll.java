package com.example.reto2.network;

import com.example.reto2.beans.Cursos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CursosFacadeGetAll extends NetConfiguration implements Runnable {


    private ArrayList<Cursos> response;

    public CursosFacadeGetAll() {
        super();
    }

    @Override
    public void run() {

        final String theUrl = theBaseUrl + "/cursos";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.response = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = response.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);

                this.response = new ArrayList<>();

                Cursos curso;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    curso = new Cursos();
                    curso.setIdCurso((Integer) object.getInt("idcurso"));
                    curso.setNombreCurso(object.getString("nombreCurso"));
                    curso.setNivelCurso(object.getString("nivelCurso"));
                    curso.setNombreProfe(object.getString("nombreProfe"));
                    curso.setIdProfesor(object.getString("idProfesor"));

                    this.response.add(curso);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<Cursos> getResponse() {
        return response;
    }
}