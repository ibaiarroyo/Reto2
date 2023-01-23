package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.adapter.MyTableAdapter;
import com.example.reto2.beans.Materia;

import java.util.ArrayList;

public class Materias extends AppCompatActivity {

    //private ArrayList<Materia> listado = new ArrayList<>();
    //private ListView listaMaterias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        Materia materia = new Materia();
        materia.setId(1);
        materia.setNombre("COSA");

        ArrayList<Materia> listado = new ArrayList<>();
        listado.add(materia);

        MyTableAdapter myTableAdapter = new MyTableAdapter(this, R.id.NomMatTextView,listado);
        ((ListView)findViewById(R.id.NomMatTextView)).setAdapter( myTableAdapter );

        (findViewById( R.id.buttonVolver)).setOnClickListener( v -> {
            Intent intentVolver = new Intent(Materias.this, MenuUsuario.class);
            startActivity(intentVolver);
        });

            /*Intent intentaComunity = new Intent(Materias.this, Comunity.class);
            startActivity(intentaComunity);*/
    }


}