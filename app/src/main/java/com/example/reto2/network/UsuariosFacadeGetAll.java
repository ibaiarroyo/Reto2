package com.example.reto2.network;

import com.example.reto2.beans.Usuarios;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UsuariosFacadeGetAll extends NetConfiguration implements Runnable {


    private ArrayList<Usuarios> response;

    public UsuariosFacadeGetAll() {
        super();
    }

    @Override
    public void run() {

        final String theUrl = theBaseUrl + "/auth/login";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

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

                Usuarios usuario;
                for (int i = 0; i < mainArray.length(); i++) {
                    JSONObject object = mainArray.getJSONObject(i);

                    usuario = new Usuarios();
                    usuario.setIdUser((Integer) object.getInt("idUser"));
                    usuario.setEmail(object.getString("email"));
                    usuario.setNombreUser(object.getString("nombreUser"));
                    usuario.setPassword(object.getString("password"));

                    this.response.add(usuario);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public ArrayList<Usuarios> getResponse() {
        return response;
    }
}
