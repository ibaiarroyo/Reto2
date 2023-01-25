package com.example.reto2.Principal;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.network.AlumnosFacadeGetAll;
import com.example.reto2.network.DataManager;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {
    private EditText editUser;
    private EditText editPassword;
    public Button botonRegistro;
    public Button botonLog;
    //public Button elegirIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = new DataManager(this);
       // editUser = findViewById(R.id.textUserLogin);
        //editPassword = findViewById(R.id.textPasswordLogin);
        botonRegistro = findViewById(R.id.botonRegistroL);
        botonLog= findViewById(R.id.botonLogin);
/*
        botonLog.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
            startActivity(intentLogin);

        });

 */
        botonLog.setOnClickListener(view ->{
            boolean login = inicioSesion();
            Usuarios usuario = new Usuarios();
            usuario.setEmail(editUser.getText().toString());
            usuario.setPassword(editPassword.getText().toString());
           // deleteAllFromDB();
           /* if (recordarUsuario.isChecked()) {
                dataManager.insert(usuario);
            }
*/
            if (login) {
                /*if (!recordarUsuario.isChecked()) {
                    deleteAllFromDB();
                }
                */

                Intent intentComunity = new Intent(Login.this, Materias.class);
                intentComunity.putExtra("emailUsuario", usuario.getEmail());
                startActivity(intentComunity);

            } else {
                Toast.makeText(getApplicationContext(), R.string.errorInicioSesion, Toast.LENGTH_SHORT).show();
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

        String usuarioString = editUser.getText().toString();
        String password = editPassword.getText().toString();
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

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        this.recreate();
    }

}
