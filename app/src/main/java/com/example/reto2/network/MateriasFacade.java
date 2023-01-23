package com.example.reto2.network;



import com.example.reto2.beans.Materia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MateriasFacade extends NetConfiguration implements Runnable {


    private ArrayList<Materia> response;

    public MateriasFacade() {
        super();
    }

    @Override
    public void run() {

        final String theUrl = theBaseUrl + "/materias";
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

                Materia materia;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    materia = new Materia();
                    materia.setId((Integer) object.getInt("id"));
                    materia.setNombre(object.getString("nombre"));
                    materia.setNivel(object.getString("nivel"));
                    materia.setTipoClase(object.getString("tipo de clase"));
                    materia.setHoras(object.getString("horas"));
                    this.response.add(materia);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<Materia> getResponse() {
        return response;
    }
}