package com.example.reto2.network;

import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Profesores;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApuntesFacadeGetAll extends NetConfiguration implements Runnable {
    private ArrayList<Apuntes> response;

    public ApuntesFacadeGetAll() {
        super();
    }

    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/apuntes";
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
                List<Apuntes> listApuntes = new ArrayList <Apuntes>();
                this.response = new ArrayList<>();

                Apuntes apunte;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    apunte = new Apuntes();
                    apunte.setIdApunte((Integer) object.getInt("idApunte"));
                    apunte.setNombreApunte((String) object.getString("nombreApunte"));
                    apunte.setNivelEstudio((String)object.getString("nivelEstudio"));
                    apunte.setUrl((String)object.getString("url"));
                //TODO - Este set de profesores no se si se haria asi.
                    apunte.setProfesores((Profesores)object.get("profesores"));
                    listApuntes.add(apunte);


                    this.response.add(apunte);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<Apuntes> getResponse() {
        return response;
    }
}

