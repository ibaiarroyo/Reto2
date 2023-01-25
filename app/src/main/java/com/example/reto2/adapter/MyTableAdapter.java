package com.example.reto2.adapter;

import androidx.annotation.NonNull;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.reto2.R;
import com.example.reto2.beans.Materias;

import java.util.ArrayList;

public class MyTableAdapter extends ArrayAdapter<Materias> {

    private final ArrayList<Materias> listadoMateria;
    private final Context context;

    public MyTableAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Materias> listadoMateria) {
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
        View view = layoutInflater.inflate(R.layout.linea_layout, null);

       // Toast.makeText(context, "AMIGOO MIOOOO", Toast.LENGTH_SHORT).show();
        ((TextView) view.findViewById(R.id.textViewNameLinea)).setText(" "+listadoMateria.get(position).getNombreMateria());

        return view;
    }
}