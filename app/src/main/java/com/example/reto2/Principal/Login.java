package com.example.reto2.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.R;
import com.example.reto2.network.DataManager;

import java.util.List;

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

        botonLog.setOnClickListener(view ->{
            Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
            startActivity(intentLogin);

        });
        botonRegistro.setOnClickListener(view ->{
            Intent intentRegis = new Intent(Login.this, Register.class);
            startActivity(intentRegis);

        });


        botonLog.setOnClickListener(view -> {
            //boolean login =inicioSesion();
        });

    }

    /*private Boolean inicioSesion() {

        UsuariosFacade usuariosFacade = new UsuariosFacade();
        Thread thread = new Thread(usuariosFacade);
        try {
            thread.start();
            thread.join(); // Awaiting response from the server...
        } catch (InterruptedException e) {
            // Nothing to do here...
        }

        String usuarioString = editUser.getText().toString();
        String password = editPassword.getText().toString();
        boolean existe = false;

        List<Usuario> personas = usuariosFacade.getResponse();

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
    */
}
