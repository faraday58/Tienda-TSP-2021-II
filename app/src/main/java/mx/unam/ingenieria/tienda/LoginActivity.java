package mx.unam.ingenieria.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    }


    View.OnClickListener onClickListenerIngresar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String usuario="armando";
            String password="12345";

            if( usuario.equals( editTextTextPersonName.getText().toString())   && password.equals(editTextTextPassword.getText().toString())  )
            {
                Toast.makeText(getApplicationContext(),"Ingreso de manera correcta",Toast.LENGTH_SHORT).show();
                Intent intentMainActivity = new Intent(LoginActivity.this,MainActivity.class  );
                startActivity(intentMainActivity);
                finish();
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