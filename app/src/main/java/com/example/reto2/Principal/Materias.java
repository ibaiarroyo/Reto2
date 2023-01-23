package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.beans.Materia;

import java.util.ArrayList;

public class Materias extends AppCompatActivity {
    Button volver;
    private ArrayList<Materia> listado = new ArrayList<>();
    private ListView listaMaterias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        volver= findViewById(R.id.buttonVolver);

        volver.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Materias.this, MenuUsuario.class);
            startActivity(intentLogin);

        });

            /*Intent intentaComunity = new Intent(Materias.this, Comunity.class);
            startActivity(intentaComunity);*/
    }


}