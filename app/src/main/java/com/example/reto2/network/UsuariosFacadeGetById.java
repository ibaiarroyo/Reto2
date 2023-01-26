package com.example.reto2.network;

import com.example.reto2.beans.Usuarios;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UsuariosFacadeGetById extends NetConfiguration implements Runnable {
    private Usuarios usuario;
    @Override
    public void run() {
        final String theUrl = theBaseUrl + "/usuarios/{idUser}";
        try {
            // The URL
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.usuario = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer usuario = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    usuario.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = usuario.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                Usuarios usuarioById= new Usuarios();
                this.usuario = new Usuarios();

                Usuarios user;

                JSONObject object = mainArray.getJSONObject(usuarioById.getIdUser());

                user = new Usuarios();
                user.setIdUser((Integer) object.getInt("idUser"));
                user.setNombreUser((String) object.getString("nombreUser"));
                user.setEmail((String) object.getString("email"));
                user.setPassword((String) object.getString("password"));




            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public Usuarios getResponse() {
        return usuario;
    }
}

