package mx.unam.ingenieria.tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.unam.ingenieria.tienda.fragments.InicioFragment;
import mx.unam.ingenieria.tienda.fragments.JuegoFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btmNavigationPrincipal;
    private InicioFragment inicioFragment;
    private JuegoFragment juegoFragment;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmNavigationPrincipal=findViewById(R.id.btmNavigationPrincipal);

        inicioFragment= new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,inicioFragment).commit();
        btmNavigationPrincipal.setOnNavigationItemSelectedListener(navListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menOpJuego:
                juegoFragment= new JuegoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,juegoFragment).commit();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case (R.id.nav_home):
                    Toast.makeText(getApplicationContext(),"Inicio",Toast.LENGTH_SHORT).show();
                    break;
                case (R.id.nav_buscar):
                    Toast.makeText(getApplicationContext(),"Buscando",Toast.LENGTH_SHORT).show();
                    break;
                case (R.id.nav_comprar):
                    Toast.makeText(getApplicationContext(),"Carrito de compras",Toast.LENGTH_SHORT).show();
                    break;
                case (R.id.nav_inicia):
                    Toast.makeText(getApplicationContext(),"Registro",Toast.LENGTH_SHORT).show();
                    break;

            }

            return true;
        }
    };



}