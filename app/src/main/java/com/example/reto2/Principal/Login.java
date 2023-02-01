package com.example.reto2.Principal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.reto2.Logear.Logear;
import com.example.reto2.R;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.network.AlumnosFacadeGetAll;
import com.example.reto2.network.DataManager;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void prueba() {
        super.onResume();
        recordarUsuario.setActivated(true);
    }

    private EditText textEmail, textPasswordLogin;
    private CheckBox recordarUsuario;
    Button botonRegistro, iniciarSesion, elegirIdioma;

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
        elegirIdioma = findViewById(R.id.cambiarIdioma);

        recuerdameSetIntoText();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String login = extras.getString("Login");
            String password = extras.getString("Password");

            textEmail.setText(login);
            textPasswordLogin.setText(password);
        }

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result != null && result.getResultCode() == RESULT_OK) {


                }
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //boolean login = inicioSesion();
                Usuarios usuario = new Usuarios();
                usuario.setEmail(textEmail.getText().toString());
                usuario.setPassword(textPasswordLogin.getText().toString());
                deleteAllFromDB();
                if (recordarUsuario.isChecked()) {
                    dataManager.insert(usuario);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.errorInicioSesion, Toast.LENGTH_SHORT).show();
                }

                Context context = getApplicationContext();
                String string = "Username: " + textEmail.getText().toString()
                        + "\nPassword: " + textPasswordLogin.getText().toString();

//              Empieza el login
                if (isConnected()) {
                    System.out.println("Has entrado");
                    UsuariosFacadeGetAll login = new UsuariosFacadeGetAll(Login.this, Login.this.generateSongJson(),Login.this.datosUserb());

                    Thread thread = new Thread(login);
                    try {
                        thread.start();
                        thread.join(); // Awaiting response from the server...
                        int position = 1;
                        System.out.println("Estas dentro");
                        Logear lista = login.getResponse();
                        if(lista != null){
                            System.out.println("Entras por la puerta grande primo");
                            int duartion = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, string, duartion);
                            Intent intent = new Intent(Login.this, MenuUsuario.class);
                            startForResult.launch(intent);
//                          overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                        } else{
                            System.out.println("Te dejo entrar por pena pringado");
                        }
                    } catch (InterruptedException e) {
                        // Nothing to do here...
                    }
                }
                textEmail.getText().clear();
                textPasswordLogin.getText().clear();
            }
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

        iniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //boolean login = inicioSesion();
                Usuarios usuario = new Usuarios();
                usuario.setEmail(textEmail.getText().toString());
                usuario.setPassword(textPasswordLogin.getText().toString());
                deleteAllFromDB();
                if (recordarUsuario.isChecked()) {
                    dataManager.insert(usuario);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.errorInicioSesion, Toast.LENGTH_SHORT).show();
                }

                Context context = getApplicationContext();
                String string = "Username: " + textEmail.getText().toString()
                        + "\nPassword: " + textPasswordLogin.getText().toString();

//              Empieza el login
                if (isConnected()) {
                    System.out.println("Has entrado");
                    UsuariosFacadeGetAll login = new UsuariosFacadeGetAll(Login.this, Login.this.generateSongJson(),Login.this.datosUserb());

                    Thread thread = new Thread(login);
                    try {
                        thread.start();
                        thread.join(); // Awaiting response from the server...
                        int position = 1;
                        System.out.println("Estas dentro");
                        Logear lista = login.getResponse();
                        if(lista != null){
                            System.out.println("Entras por la puerta grande primo");
                            int duartion = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, string, duartion);
                            Intent intent = new Intent(Login.this, MenuUsuario.class);
                            startForResult.launch(intent);
//                          overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                        } else{
                            System.out.println("Te dejo entrar por pena pringado");
                        }
                    } catch (InterruptedException e) {
                        // Nothing to do here...
                    }
                }
                textEmail.getText().clear();
                textPasswordLogin.getText().clear();
            }
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

    }

    public void recuerdameSetIntoText() {
        DataManager dataManager = new DataManager(this);

        List<Usuarios> user = dataManager.selectAllUsers();
        if (user.size() != 0) {
            prueba();
            textEmail.setText(user.get(0).getEmail());
            textPasswordLogin.setText(user.get(0).getPassword());
        }
    }

    public void deleteAllFromDB() {
        DataManager dataManager = new DataManager(this);
        List<Usuarios> usuariosListBorrar = dataManager.selectAllUsers();

        if (usuariosListBorrar.size() != 0) {
            for (int i = 0; i < usuariosListBorrar.size(); i++) {
                dataManager.deleteByEmail(usuariosListBorrar.get(i).getEmail());
            }
        }
    }

    public String generateSongJson() {

        return "{" +
                "\"email\": \"" + textEmail.getText().toString() + "\", " +
                "\"password\": \"" + textPasswordLogin.getText().toString()  + "\"" +
                "}";
    }

    public String datosUserb(){
        return "/auth/login";
    }

    public boolean isConnected() {
        boolean ret = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                    .getSystemService( Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if ((networkInfo != null) && (networkInfo.isAvailable()) && (networkInfo.isConnected()))
                ret = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_communication), Toast.LENGTH_SHORT).show();
        }
        return ret;
    }

    public void setLocale(String lang) {
//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, Login.class);
//        finish();
//        startActivity(refresh);

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale.setDefault(myLocale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = myLocale;
        res.updateConfiguration(config, dm);
        Intent refresh = new Intent(this, Login.class);
        finish();
        startActivity(refresh);

    }

}