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
import com.example.reto2.beans.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "hibernate_reto.db";

    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "usuarios";

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            EMAIL + " TEXT NOT NULL ," +
            PASSWORD + " TEXT NOT NULL " +
            ");";

    private final Context context;

    public DataManager(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    /*TODO SEGUIR A PARTIR DE AQUI*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
    }

    // Select todos los users
    public List<Usuarios> selectAllUsers()
    {
        List<Usuarios> response = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        Usuarios user;
        while (cursor.moveToNext()) {
            user = new Usuarios();

            user.setEmail(cursor.getString(0));
            user.setPassword(cursor.getString(1));
        }

        cursor.close();
        sqLiteDatabase.close();

        return response;
    }

    // Insertar user
    public void insert(Usuarios user)
    {
        ContentValues values = new ContentValues();
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME, null, values);
        sQLiteDatabase.close();
    }

    // Si la tabla existe o esta vacía.
    public boolean ifTableExists() {
        boolean ret = false;
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase = this.getReadableDatabase();
            String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                    TABLE_NAME + "'";
            cursor = sQLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    ret = true;
                }
            }
        } catch (Exception e) {
            // Nothing to do here...
        } finally {
            try {
                assert cursor != null;
                cursor.close();
            } catch (NullPointerException e) {
                // Nothing to do here...
            }
        }
        return ret;
    }

}
