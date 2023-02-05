package com.example.reto2.Principal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
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
    Button botonRegistro, iniciarSesion, elegirIdioma, contrasenaOlvidada;
    Animation splash_top;
    TextView textViewM;
    ImageView logo;

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
        contrasenaOlvidada = findViewById(R.id.buttonForgotPass);
        logo = findViewById(R.id.logo_image);
        textViewM = findViewById(R.id.textViewMail);

        final LoadingDialog loadingDialog = new LoadingDialog(Login.this);

        splash_top = AnimationUtils.loadAnimation(this, R.anim.splash_top);

        logo.setAnimation(splash_top);

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

                loadingDialog.startLoadingDialog();
                Handler handler= new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },2000);
                Intent intentLogin = new Intent(Login.this, MenuUsuario.class);
                startActivity(intentLogin);

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
                        Logear lista = login.getResponse();
                        if(lista != null){
                            int duartion = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, string, duartion);
                            Intent intent = new Intent(Login.this, MenuUsuario.class);
                            startForResult.launch(intent);
                        } else{
                            System.out.println("No has podido ingresar");
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

        elegirIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Login.this, elegirIdioma);
                popupMenu.getMenuInflater().inflate(R.menu.menu_idioma, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.en:
                                setLocale("en");
                                break;
                            case R.id.es:
                                setLocale("es");
                                break;
                            case R.id.eu:
                                setLocale("eu");
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        botonRegistro.setOnClickListener(view ->{
            Intent intentRegis = new Intent(Login.this, Register.class);
            startActivity(intentRegis);
        });

        contrasenaOlvidada.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent IntenPass = new Intent(Login.this, ResetPass.class);
                startActivity(IntenPass);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opcion_materias,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.compartir){
            Toast.makeText(this, "Has copiado el correo de contacto en el portapapeles!.", Toast.LENGTH_SHORT).show();

            // obtenemos el texto del textViewM
            String text = textViewM.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text",  text);
            clipboard.setPrimaryClip(clip);

        }else if(id==R.id.salir){
            Toast.makeText(this, "Has cerrado la aplicacion", Toast.LENGTH_SHORT).show();
            finish();

        }
        return super.onOptionsItemSelected(item);

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
