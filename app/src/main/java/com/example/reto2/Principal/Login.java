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

                //attemps++;

//                if ((!etNombre.getText().toString().equals("")) && (!etpassword.getText().toString().equals(""))) {

//                    if ((!etNombre.getText().toString().equals("Rodrigo"))
//                            && (!etpassword.getText().toString().equals("123456"))) {


//                        if (checkBox.isChecked()) {
//                            Boolean boolIsChecked = checkBox.isChecked();
//                            SharedPreferences.Editor editor = mPrefs.edit();
//                            editor.putString("pref_name", etNombre.getText().toString());
//                            editor.putString("pref_pass", etpassword.getText().toString());
//                            editor.putBoolean("pref_check", boolIsChecked);
//                            editor.apply();
//                            Toast.makeText(getApplicationContext(), "Se ha guardado",
//                                    Toast.LENGTH_SHORT).show();

//                        } else {
//                            mPrefs.edit().clear().apply();

//                        }

//                        Log.i("Username", etNombre.getText().toString());
//                        Log.i("Password", etpassword.getText().toString());

                        Context context = getApplicationContext();
                        String string = "Username: " + textEmail.getText().toString()
                                + "\nPassword: " + textPasswordLogin.getText().toString();

//                    Meter aqui conexion login

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
//                                    overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

                                }
                                else{
                                    System.out.println("Te dejo entrar por pena pringado");
                                }

                            } catch (InterruptedException e) {
                                // Nothing to do here...
                            }

                        }

                        textEmail.getText().clear();
                        textPasswordLogin.getText().clear();

                    }

//            boolean login = inicioSesion();
//            Usuarios usuario = new Usuarios();
//            usuario.setEmail(textEmail.getText().toString());
//            usuario.setPassword(textPasswordLogin.toString());
//            deleteAllFromDB();
//
//            if (recordarUsuario.isChecked()) {
//                dataManager.insert(usuario);
//            }
//
//            if (login) {
//                if (!recordarUsuario.isChecked()) {
//                    deleteAllFromDB();
//                }
//                Intent intentComunity = new Intent(Login.this, MenuUsuario.class);
//                intentComunity.putExtra("emailUsuario", usuario.getEmail());
//                startActivity(intentComunity);
//
//            } else {
//                Toast.makeText(getApplicationContext(), R.string.errorInicioSesion, Toast.LENGTH_SHORT).show();
//            }
////            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
////            startActivity(intentLogin);

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

//    private Boolean inicioSesion() {
//
//        UsuariosFacadeGetAll usuariosFacadeGetAll = new UsuariosFacadeGetAll();
//        Thread thread = new Thread(usuariosFacadeGetAll);
//        try {
//            thread.start();
//            thread.join(); // Awaiting response from the server...
//        } catch (InterruptedException e) {
//            // Nothing to do here...
//        }
//
//        String usuarioString = textEmail.getText().toString();
//        String password = textPasswordLogin.getText().toString();
//        boolean existe = false;
//
//        Usuarios personas = usuariosFacadeGetAll.getResponse();
//
//        for (int i = 0; i < personas.size(); i++) {
//            if (personas.get(i).getEmail().equalsIgnoreCase(usuarioString)) {
//                if (personas.get(i).getPassword().equals(password)) {
//                    existe = true;
//                    break;
//                }
//            }
//        }
//        return existe;
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



}
