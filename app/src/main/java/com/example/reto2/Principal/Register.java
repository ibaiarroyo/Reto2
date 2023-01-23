package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;

public class Register extends AppCompatActivity {
    public EditText nombre;
    public EditText email;
    public EditText password1;
    public EditText password2;
    public Button botonRegistro;
    public Button botonCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.editTextCrearNombre);
        email = findViewById(R.id.editTextCrearEmail);
        password1 = findViewById(R.id.editTextCrearPassword);
        password2 = findViewById(R.id.editTextCrearContraseña2);
        botonRegistro = findViewById(R.id.buttonRegistro);
        botonCancelar= findViewById(R.id.buttonCancelar);

        botonRegistro.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Register.this, Login.class);
            startActivity(intentLogin);

        });
        botonCancelar.setOnClickListener(view ->{
            Intent intentLogi= new Intent(Register.this, Login.class);
            startActivity(intentLogi);

        });


    }
}
