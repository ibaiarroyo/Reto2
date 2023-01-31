package com.example.reto2.Principal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2.Logear.Registro;
import com.example.reto2.R;
import com.example.reto2.beans.Usuarios;
import com.example.reto2.network.UsuariosFacadeGetAll;

import java.util.List;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    public EditText nombre, email, password1, password2;
    public Button botonRegistro, botonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.editTextCrearNombre);
        email = findViewById(R.id.editTextCrearEmail);
        password1 = findViewById(R.id.editTextCrearPassword);
        password2 = findViewById(R.id.editTextCrearContraseña2);
        botonRegistro = findViewById(R.id.buttonRegistro);
        botonCancelar= findViewById(R.id.buttonCancelar);

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result != null && result.getResultCode() == RESULT_OK) {}
            }
        });


//        Usuarios usuario = new Usuarios();

        botonRegistro.setOnClickListener(new View.OnClickListener() {

            String remail = email.getText().toString().toLowerCase();
            String rnombreUser = nombre.getText().toString().toLowerCase();
            String rpassword = password1.getText().toString();
            String rpassword2 = password2.getText().toString();

            boolean errorEmail = validarEmail(remail);

            @Override
            public void onClick(View view) {
                if( validarRegistro()){
                    if(isConnected()){
                        Registro registro = new Registro(Register.this, Register.this.generateRegisterJson());
                        Thread thread = new Thread(registro);
                        try {
                            thread.start();
                            thread.join(); // Awaiting response from the server...
                        } catch (InterruptedException e) {
                            // Nothing to do here...
                        }
                        // Processing the answer
                        //RegisterResponse registerResponse = registro.getRegisterResponse();
                        Intent intentalogin = new Intent(Register.this, Login.class);
                        intentalogin.putExtra("Login", remail);
                        intentalogin.putExtra("Password", rpassword);
                        System.out.println(remail);
                        System.out.println(rpassword);
                        //startActivity(intent);
                        startForResult.launch(intentalogin);
                        Toast.makeText(Register.this, R.string.registradocorrectamente, Toast.LENGTH_SHORT).show();

//                        Toast.makeText(RegisterActivity.this, registerResponse.getMensajeRespuesta(), Toast.LENGTH_LONG).show();
                    }

                }
            }

//            Intent intentLogin = new Intent(Register.this, Login.class);
//            startActivity(intentLogin);

        });

        botonCancelar.setOnClickListener(view ->{
            Intent intentLogi= new Intent(Register.this, Login.class);
            startActivity(intentLogi);
        });

    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public String generateRegisterJson() {
        return "{" +
                "\"email\": \"" + email.getText().toString() + "\", " +
                "\"nombreUser\": \"" + nombre.getText().toString() + "\", " +
                "\"password\": \"" + password1.getText().toString() + "\" " +
                "}";
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

    public boolean validarRegistro() {

        boolean retorno = true;

        String campo1 = nombre.getText().toString();
        String campo2 = email.getText().toString();
        String campo3 = password1.getText().toString();
        String campo4 = password2.getText().toString();

        if (campo1.isEmpty()) {
            nombre.setError("Nombre has introducido tu nombre");
            retorno = false;
        }  else if (campo2.isEmpty()) {
            email.setError("Email no introducido");
            retorno = false;

        } else if (campo3.isEmpty()) {
            password1.setError("Primer Passsword no introducido");
            retorno = false;
            return retorno;
        } else if (campo4.isEmpty()) {
            password2.setError("Segundo Passsword no introducido");
            retorno = false;
        }
        if (!campo3.equals(campo4)) {
            password2.setError("No coinciden las contraseñas");
            retorno = false;
        }
        return retorno;

    }

}
