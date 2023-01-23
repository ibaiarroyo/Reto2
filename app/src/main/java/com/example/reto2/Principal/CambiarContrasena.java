package com.example.reto2.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.reto2.R;

public class CambiarContrasena extends AppCompatActivity {

    Button Cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        Cancelar = findViewById(R.id.buttonCancelarCambioContraseña);

        Cancelar.setOnClickListener(view ->{
            Intent intentCpas = new Intent(CambiarContrasena.this, MenuUsuario.class);
            startActivity(intentCpas);

        });
    }

}