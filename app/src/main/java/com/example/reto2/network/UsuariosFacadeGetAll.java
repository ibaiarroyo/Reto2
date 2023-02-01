package com.example.reto2.network;

import android.content.Context;
import android.content.res.Resources;

import com.example.reto2.Logear.Logear;
import com.example.reto2.Response.UsuariosResponse;
import com.example.reto2.beans.Alumnos;
import com.example.reto2.beans.Profesores;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UsuariosFacadeGetAll extends NetConfiguration implements Runnable {

    //private String theUrl = theBaseUrl + "/auth/login";

    private String theUrl;
    private Resources res;
    private Logear response;
    private String usuarioJson;
    private UsuariosResponse usuarioResponse;

    public UsuariosFacadeGetAll() {
        super();
    }

    public UsuariosFacadeGetAll(Context context, String usuarioJson, String url) {
        res = context.getResources();
        this.usuarioJson = usuarioJson;
        theUrl = theBaseUrl + url;
    }

    @Override
    public void run() {

        try {
            // The URL
            URL url = new URL(theUrl);
            System.out.println(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod( "POST" );
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            System.out.println("aceite de aceituna negra");
            System.out.println(usuarioJson);
            try (OutputStream ous = httpURLConnection.getOutputStream()) {
                byte[] output = usuarioJson.getBytes("utf-8");
                ous.write(output, 0, output.length);
            }
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);

            usuarioResponse = new UsuariosResponse();

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

                JSONObject jsonUser = new JSONObject(theUnprocessedJSON);

                Logear logear = new Logear();
                logear = new Logear();
                logear.setId(jsonUser.getLong("idUser"));
                logear.setEmail(jsonUser.getString("email"));

                this.response = logear;

            } else {

                System.out.println("Das pena");

            }

    } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public Logear getResponse() {
        return response;
    }
}
