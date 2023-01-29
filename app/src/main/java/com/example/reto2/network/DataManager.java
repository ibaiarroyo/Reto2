package com.example.reto2.network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import com.example.reto2.R;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "hibernate_reto.db";

    private static final int DB_VERSION = 1;

    //public static final String TABLE_NAME = "usuarios";

    /*private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            EMAIL + " TEXT NOT NULL ," +
            PASSWORD + " TEXT NOT NULL " +
            ");";*/

    private final Context context;

    public DataManager(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    /*TODO SEGUIR A PARTIR DE AQUI*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
