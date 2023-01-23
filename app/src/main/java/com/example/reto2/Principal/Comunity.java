package com.example.reto2.Principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.reto2.R;
import com.example.reto2.adapter.MyTableAdapter;
import com.example.reto2.beans.Materia;
import com.example.reto2.network.MateriasFacade;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class Comunity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // listaMaterias = findViewById(R.id.listViewHome);

        //TODO NO ESTAMOS LLAMANDO A ESTE ACTIVITY

        //listaMaterias = findViewById(R.id.NomMatTextView);

        Materia materia = new Materia();
        materia.setId(1);
        materia.setNombre("COSA");

        ArrayList<Materia> listado = new ArrayList<>();
        listado.add(materia);


        /*MyTableAdapter myTableAdapter = new MyTableAdapter(this, R.layout.activity_materias, listado);
        listaMaterias.setAdapter(myTableAdapter);
*/
        if (isConnected()) {
            MateriasFacade materiasFacade = new MateriasFacade();
            Thread thread = new Thread(materiasFacade);
            try {
                thread.start();
                thread.join(); // Awaiting response from the server...
            } catch (InterruptedException e) {
                // Nothing to do here...
            }
            listado.addAll(materiasFacade.getResponse());
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



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}