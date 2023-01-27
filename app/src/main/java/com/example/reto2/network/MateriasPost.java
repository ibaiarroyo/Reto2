package com.example.reto2.network;

import com.example.reto2.beans.Materias;
import com.example.reto2.beans.Profesores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MateriasPost extends NetConfiguration implements Runnable {

    private final String theUrl = theBaseUrl + "/materias";
    private Materias materia;
    private int response;

    public MateriasPost(Materias materiaCons) {
        materia = materiaCons;
    }

    @Override
    public void run() {
        try {

            URL url = new URL(theUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            String jsonInputString = materia.toString();
            try (OutputStream postSend = httpURLConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                postSend.write(input, 0, input.length);
            }

            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==500){

                response=responseCode;

            }else if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer response = new StringBuffer();
                String inputLine;

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }


    }

    public int getResponse() {
        System.out.println("RESPONSE"+response);
        return response;

    }


}