package com.example.reto2.Principal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.Logear.Logear;
import com.example.reto2.R;
import com.example.reto2.adapter.MyTableAdapter;
import com.example.reto2.network.MateriasFacadeGetAll;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.ArrayList;

public class Materias extends AppCompatActivity {

    private ArrayList<com.example.reto2.beans.Materias> listado = new ArrayList<>();
    private ListView listaMaterias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        //TODO ESTO FUNCIONA PERO NO CONEXIONA CON LA BBDD
       // Materia materia = new Materia();
        //materia.getNombre();
        //materia.setId(1);
        //materia.setNombre("COSA");

        //ArrayList<Materia> listado = new ArrayList<>();
        //listado.add(materia);
        //TODO ESTO FUNCIONA PERO NO CONEXIONA CON LA BBDD

//        MyTableAdapter myTableAdapter = new MyTableAdapter(this, R.id.NomMatTextView,listado);
//        ((ListView)findViewById(R.id.NomMatTextView)).setAdapter( myTableAdapter );
//
//        (findViewById( R.id.buttonVolver)).setOnClickListener( v -> {
//            Intent intentVolver = new Intent(Materias.this, MenuUsuario.class);
//            startActivity(intentVolver);
//        });

        listaMaterias = findViewById(R.id.NomMatTextView);

        MyTableAdapter myTableAdapter = new MyTableAdapter(this, R.layout.materias_layout,listado);
        listaMaterias.setAdapter(myTableAdapter);


        (findViewById( R.id.buttonVolver)).setOnClickListener( v -> {
            Intent intentVolver = new Intent(Materias.this, MenuUsuario.class);
            startActivity(intentVolver);
        });


        if (isConnected()) {
            MateriasFacadeGetAll materiasFacadeGetAll = new MateriasFacadeGetAll();

            Thread thread = new Thread(materiasFacadeGetAll);
            try {
                thread.start();
                thread.join(); // Awaiting response from the server...
                int position = 1;

                    int duartion = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(this, "HEYYYYY", duartion);
                    Intent intent = new Intent(Materias.this, Materias.class);
                    startActivity(intent);
                    //startForResult.launch(intent);

            } catch (InterruptedException e) {
                // Nothing to do here...
            }

            listado.addAll(materiasFacadeGetAll.getResponse());

        }

    }
    public boolean isConnected() {
        boolean ret = false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if ((networkInfo != null) && (networkInfo.isAvailable()) && (networkInfo.isConnected()))
                ret = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
        return ret;
    }

}