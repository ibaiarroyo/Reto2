package com.example.reto2.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reto2.R;
import com.example.reto2.network.PasswordReset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResetPass extends AppCompatActivity {

    private EditText emailR;
    private Button botonResetear, botonCancelarReset;

    Intent IntenPassNo = new Intent();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);


        emailR= findViewById(R.id.editTextEmailPassReset);
        botonResetear = findViewById(R.id.buttonDeReset);
        botonCancelarReset=findViewById(R.id.buttonCancelreset);



        botonCancelarReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntenPassNo = new Intent(ResetPass.this, Login.class);
                startActivity(IntenPassNo);

            }
        });


        botonResetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailR.getText().toString();
                System.out.println(generateSongJson());

                    if (isConnected()) {
                        PasswordReset reset = new PasswordReset(ResetPass.this, ResetPass.this.generateSongJson(), ResetPass.this.datosUserb(email));

                        Thread thread = new Thread(reset);
                        try {
                            System.out.println("hola");

                            thread.start();
                            thread.join(); // Awaiting response from the server...
                        } catch (InterruptedException e) {
                            // Nothing to do here...
                        }

                    }




            }
        });

    }

    public String generateSongJson() {

        return "{" +
                "\"email\": \"" + emailR.getText().toString()  + "\"" +
                "}";
    }

    public String datosUserb(String email){
        return "/auth/enviarEmail/"+email;
    }

    public boolean isConnected() {
        boolean ret = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                    .getSystemService( Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if ((networkInfo != null) && (networkInfo.isAvailable()) && (networkInfo.isConnected()))
                ret = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_communication), Toast.LENGTH_SHORT).show();
        }
        return ret;
    }
}


