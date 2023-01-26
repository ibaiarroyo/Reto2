package com.example.reto2.Principal;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.network.DataManager;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {

    private EditText textEmail, textPasswordLogin;
    Button botonRegistro, botonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = new DataManager(this);

        botonRegistro = findViewById(R.id.botonRegistroL);
        botonLog= findViewById(R.id.botonLogin);
        textEmail.findViewById(R.id.textEmail);
        textPasswordLogin.findViewById(R.id.textPasswordLogin);

        botonLog.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
            startActivity(intentLogin);

        });
        botonRegistro.setOnClickListener(view ->{
            Intent intentRegis = new Intent(Login.this, Register.class);
            startActivity(intentRegis);

        });

    }

    private Boolean inicioSesion() {

        UsuariosFacadeGetAll usuariosFacadeGetAll = new UsuariosFacadeGetAll();
        Thread thread = new Thread(usuariosFacadeGetAll);
        try {
            thread.start();
            thread.join(); // Awaiting response from the server...
        } catch (InterruptedException e) {
            // Nothing to do here...
        }

        String usuarioString = textEmail.getText().toString();
        String password = textPasswordLogin.getText().toString();
        boolean existe = false;

        List<Usuarios> personas = usuariosFacadeGetAll.getResponse();

        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getEmail().equalsIgnoreCase(usuarioString)) {
                if (personas.get(i).getPassword().equals(password)) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }

    // Eliminar usuario de la ddbb
    /*
    public void deleteAllFromDB() {
        DataManager dataManager = new DataManager(this);
        List<Usuarios> usuariosListBorrar = dataManager.selectAllUsers();

        if (usuariosListBorrar.size() != 0) {
            for (int i = 0; i < usuariosListBorrar.size(); i++) {
                dataManager.deleteByEmail(usuariosListBorrar.get(i).getEmail());
            }
        }
    }
    */

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Login.class);
        finish();
        startActivity(refresh);
    }

}
