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

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /*TODO SEGUIR A PARTIR DE AQUI*/

    // Seleccionar todos los users
    public List<Usuarios> selectAllUsers() {
        List<Usuarios> ret = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Usuarios user;
        while (cursor.moveToNext()) {
            user = new Usuarios();
            user.setEmail(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            ret.add(user);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
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

    public boolean update(Usuarios user) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(EMAIL, user.getEmail());
        args.put(PASSWORD, user.getPassword());
        String whereClause = EMAIL + "=" + user.getIdUser();
        return sQLiteDatabase.update(TABLE_NAME, args, whereClause, null) > 0;
    }

    public void deleteByEmail(String email) {

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();

        sQLiteDatabase.delete(TABLE_NAME, EMAIL + "=?",
                new String[]{email});
        sQLiteDatabase.close();
    }

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
