package com.example.reto2.network;

import android.content.Context;
import android.content.res.Resources;

import com.example.reto2.beans.Alumnos;
import com.example.reto2.beans.Apuntes;
import com.example.reto2.beans.Cursos;
import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MateriasFacadeGetById extends NetConfiguration implements Runnable {


    private String theUrl;
    private Resources res;
    private String materiasJson;
    private ArrayList<Materias> response;
    private long idUser;

    public MateriasFacadeGetById() {
        super();
    }

    public MateriasFacadeGetById(long idUser) {
        this.idUser = idUser;
    }

    public MateriasFacadeGetById(Context context, String materiasJson, String url) {
        res = context.getResources();
        this.materiasJson = materiasJson;
        theUrl = theBaseUrl +url;
    }

    private Materias materia;
    @Override
    public void run() {
        try {
            // The URL
            String url2 = theUrl + "/" + idUser;
            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            // Sending...
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == 418) {
                // I am not a teapot...
                this.materia = null;

            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                // Response...
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuffer materia = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    materia.append(inputLine);
                }
                bufferedReader.close();

                // Processing the JSON...
                String theUnprocessedJSON = materia.toString();

                JSONArray mainArray = new JSONArray(theUnprocessedJSON);
                this.materia = new Materias();

                Materias favorito;
                for( int i = 0; i < mainArray.length(); i++){
                    JSONObject object = mainArray.getJSONObject(i);

                    favorito = new Materias();
                    favorito.setIdMateria((Integer) object.getInt("idMateria"));
                    favorito.setNombreMateria((String) object.getString("nombreMateria"));
                    favorito.setNivelMateria((String) object.getString("nivelMateria"));
                    favorito.setTipoDeClase((String) object.getString("tipoDeClase"));
                    favorito.setNumeroHoras((Integer) object.getInt("numeroHoras"));
                }

                //TODO - Aqui yo entiendo que faltaria la lista de alumnos y profes
                //TODO - no se si poner las dos directamente asi o hay que hacer algo mas
                //mater.setAlumno((List<Alumnos> ) object.getJSONArray("alumno"));
                //mater.setIdProfe((List<Profesores>) object.getJSONArray("idProfe"));


            //TODO - AL DEVOLVER LOS DATOS NO ESTOY SEGURO SI ESTA BIEN, CREO QUE NO DEVUELVE NADA

            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public ArrayList<Materias> getResponse() {
        return response;
    }
}

