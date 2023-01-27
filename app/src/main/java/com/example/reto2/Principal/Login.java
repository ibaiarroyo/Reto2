package com.example.reto2.Principal;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.reto2.R;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.network.DataManager;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void prueba()
    {
        super.onResume();
        recordarUsuario.setActivated(true);
    }

    private EditText textEmail, textPasswordLogin;
    private CheckBox recordarUsuario;
    Button botonRegistro, iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = new DataManager(this);

        botonRegistro = findViewById(R.id.botonRegistroL);
        //botonLog = findViewById(R.id.botonLogin);
        textEmail = findViewById(R.id.textEmail);
        textPasswordLogin = findViewById(R.id.textPasswordLogin);
        iniciarSesion = findViewById(R.id.botonLogin);
        recordarUsuario = findViewById(R.id.recordarSesion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String login = extras.getString("Login");
            String password = extras.getString("Password");

            textEmail.setText(login);
            textPasswordLogin.setText(password);
        }

        iniciarSesion.setOnClickListener(view ->{
            boolean login = inicioSesion();
            Usuarios usuario = new Usuarios();
            usuario.setEmail(textEmail.getText().toString());
            usuario.setPassword(textPasswordLogin.toString());
            deleteAllFromDB();

            if (recordarUsuario.isChecked()) {
                dataManager.insert(usuario);
            }

            if (login) {
                if (!recordarUsuario.isChecked()) {
                    deleteAllFromDB();
                }
                Intent intentComunity = new Intent(Login.this, MenuUsuario.class);
                intentComunity.putExtra("emailUsuario", usuario.getEmail());
                startActivity(intentComunity);

            } else {
                Toast.makeText(getApplicationContext(), R.string.errorInicioSesion, Toast.LENGTH_SHORT).show();
            }
//            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
//            startActivity(intentLogin);

        });

        recordarUsuario.setOnCheckedChangeListener((compoundButton, checked) -> {
            Usuarios usuario = new Usuarios();
            usuario.setEmail(textEmail.getText().toString());
            usuario.setPassword(textPasswordLogin.getText().toString());
            deleteAllFromDB();
            if (recordarUsuario.isChecked()) {
                deleteAllFromDB();
                dataManager.insert(usuario);
                recordarUsuario.setActivated(true);

            } else if (!recordarUsuario.isChecked()) {
                deleteAllFromDB();
            }
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

//    public void recuerdameSetIntoText() {
//        DataManager dataManager = new DataManager(this);
//
//        List<Usuarios> user = dataManager.selectAllUsers();
//        if (user.size() != 0) {
//            prueba();
//            textEmail.setText(user.get(0).getEmail());
//            textPasswordLogin.setText(user.get(0).getPassword());
//        }
//    }

    public void deleteAllFromDB() {
        DataManager dataManager = new DataManager(this);
        List<Usuarios> usuariosListBorrar = dataManager.selectAllUsers();

        if (usuariosListBorrar.size() != 0) {
            for (int i = 0; i < usuariosListBorrar.size(); i++) {
                dataManager.deleteByEmail(usuariosListBorrar.get(i).getEmail());
            }
        }
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

//    public void setLocale(String lang) {
//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, Login.class);
//        finish();
//        startActivity(refresh);
//    }

}
