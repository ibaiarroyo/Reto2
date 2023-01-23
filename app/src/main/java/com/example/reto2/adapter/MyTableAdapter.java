package com.example.reto2.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reto2.R;
import com.example.reto2.beans.Materia;

import java.util.ArrayList;

public class MyTableAdapter extends ArrayAdapter<Materia> {

    private final ArrayList<Materia> listadoMateria;
    private final Context context;

    public MyTableAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Materia> listadoMateria) {
        super(context, resource, listadoMateria);
        this.listadoMateria = listadoMateria;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_materias, null);

        Toast.makeText(context, "AMIGOO MIOOOO", Toast.LENGTH_SHORT).show();
        ((TextView) view.findViewById(R.id.NomMatTextview)).setText("  "+listadoMateria.get(position).getNombre());


        return view;
    }
}