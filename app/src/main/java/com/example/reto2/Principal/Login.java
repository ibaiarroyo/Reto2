package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.network.DataManager;

public class Login extends AppCompatActivity {
    public Button botonRegistro;
    public Button botonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = new DataManager(this);

        botonRegistro = findViewById(R.id.botonRegistroL);
        botonLog= findViewById(R.id.botonLogin);

        botonLog.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
            startActivity(intentLogin);

        });
        botonRegistro.setOnClickListener(view ->{
            Intent intentRegis = new Intent(Login.this, Register.class);
            startActivity(intentRegis);

        });

    }}
