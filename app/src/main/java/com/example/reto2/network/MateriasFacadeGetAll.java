package com.example.reto2.network;



import com.example.reto2.beans.Materias;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MateriasFacadeGetAll extends NetConfiguration implements Runnable {


    private ArrayList<Materias> response;

    public MateriasFacadeGetAll() {
        super();
    }

    @Override
    public void run() {

        final String theUrl = theBaseUrl + "/materias/web";

        System.out.println("HEYYYYYYY Qeu miras");

        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            // Sending...
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);

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

                Materias materia;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    materia = new Materias();
                    materia.setIdMateria((Integer) object.getInt("idMateria"));
                    materia.setNombreMateria(object.getString("nombreMateria"));
                    materia.setNivelMateria(object.getString("nivelMateria"));
                    materia.setTipoDeClase(object.getString("tipoDeClase"));
                    materia.setNumeroHoras(object.getInt("numeroHoras"));

                    this.response.add(materia);
                    System.out.println(response);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<Materias> getResponse() {
        return response;
    }
}
