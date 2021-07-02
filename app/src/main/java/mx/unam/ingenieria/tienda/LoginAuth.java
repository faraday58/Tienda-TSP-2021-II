package mx.unam.ingenieria.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

public class LoginAuth  extends AppCompatActivity {
    private static final int RC_SIGN_IN=123;
    private List<AuthUI.IdpConfig> proveedores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CargaProveedores();
        login();

    }

    private void login() {
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario != null) {
            Toast.makeText(this, "Inicia sesión: " + usuario.getDisplayName(), Toast.LENGTH_LONG).show();
            Intent intMain = new Intent(this, MainActivity.class);
            startActivity(intMain);
        } else
        {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(proveedores)
                    .build(),RC_SIGN_IN
            );

        }



    }

    private void CargaProveedores() {

        proveedores= Arrays.asList(//Enumeración en Java
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
    }


}
