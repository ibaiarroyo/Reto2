package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.adapter.MyTableAdapter;

import java.util.ArrayList;

public class MenuUsuario extends AppCompatActivity {

    TextView textViewMat;
    Button cambiopass,verMat;

    private ArrayList<Materias> listado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        cambiopass = findViewById(R.id.buttonEditPass2);
        verMat = findViewById(R.id.buttonVerMat);
        textViewMat = findViewById(R.id.textNombreMat);


        cambiopass.setOnClickListener(view ->{
            Intent intentCpass = new Intent(MenuUsuario.this, CambiarContrasena.class);
            startActivity(intentCpass);

        });
        verMat.setOnClickListener(view ->{
           /* Intent intentAmat = new Intent(MenuUsuario.this, Materias.class);
            startActivity(intentAmat);
            */
            /*Intent intentaComunity = new Intent(MenuUsuario.this, Comunity.class);
            startActivity(intentaComunity);*/
            Intent intentaMaterias = new Intent(MenuUsuario.this, Materias.class);
            startActivity(intentaMaterias);

        });



    }
}
