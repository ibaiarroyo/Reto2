package com.example.reto2.network;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PasswordReset  extends NetConfiguration implements Runnable  {
    public PasswordReset(){super();}
    private  String theUrl;
    private Resources res;
    private String loginJson;


    public PasswordReset(Context context, String loginJson, String url) {
        res = context.getResources();;
        this.loginJson = loginJson;
        theUrl = theBaseUrl + url;
    }
    @Override
    public void run() {
        try{
            URL url = new URL(theUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod( "PUT" );
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            try (OutputStream ous = httpURLConnection.getOutputStream()) {
                byte[] output = loginJson.getBytes("utf-8");
                ous.write(output, 0, output.length);
            }
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);


            if (responseCode == 400) {
                System.out.println("ERROR 400, BAD REQUEST ");
//                loginResponse.setMensajeRespuesta(res.getString(R.string.error_registro_user));
            }else if (responseCode == 500) {
                System.out.println("ERROR 500, NO HAY CONEXION");
//                loginResponse.setMensajeRespuesta(res.getString(R.string.error_registro_user));
            } else if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("url");
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader( httpURLConnection.getInputStream() ) );

                StringBuffer stringBuffer = new StringBuffer();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append( inputLine );
                }
                bufferedReader.close();
            }

        }
        catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
