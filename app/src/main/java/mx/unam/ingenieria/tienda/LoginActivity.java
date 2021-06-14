package mx.unam.ingenieria.tienda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;
    private Button btnIngresar;
    private Button btnRegistrar;
    private String usuario;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextPersonName= findViewById(R.id.editTextTextPersonName);
        editTextTextPassword= findViewById(R.id.editTextTextPassword);
        btnIngresar= findViewById(R.id.btnIngresar);
        btnRegistrar= findViewById(R.id.btnRegistrar);
        btnIngresar.setOnClickListener(onClickListenerIngresar);
        btnRegistrar.setOnClickListener(onClickListenerRegistar);
        usuario=this.getIntent().getStringExtra("usuario");
        password=this.getIntent().getStringExtra("password");

        cargarPreferencias();

    }

    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();

        String user= preferences.getString("user","Ingrese su nombre de usuario");
        String pass= preferences.getString("pass","");
        editTextTextPersonName.setText(user);
        editTextTextPassword.setText(pass);

    }



    public AlertDialog dialogoGuardarUsuario(){
        AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Indique si desea guardar su \n \n" +
                "nombre de usuario y contraseña " +
                "en este dispositivo")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    iniciarMain();
            }
        })
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences= getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putString("user",usuario);
                        editor.putString("pass",password);
                        editor.commit();
                        iniciarMain();

                    }
                });

        return builder.create();
    }


    private void iniciarMain(){
        Toast.makeText(getApplicationContext(),"Ingreso de manera correcta",Toast.LENGTH_SHORT).show();
        Intent intentMainActivity = new Intent(LoginActivity.this,MainActivity.class  );
        startActivity(intentMainActivity);
        finish();
    }

    View.OnClickListener onClickListenerIngresar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if( usuario.equals( editTextTextPersonName.getText().toString())   && password.equals(editTextTextPassword.getText().toString())  )
            {
                dialogoGuardarUsuario().show();
            }else
            {
                Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
            }
            
           }
    };

    View.OnClickListener onClickListenerRegistar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentRegistrar=new  Intent(LoginActivity.this,RegistroActivity.class);
            startActivity(intentRegistrar);

        }
    };




}