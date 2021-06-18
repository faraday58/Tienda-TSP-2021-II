package mx.unam.ingenieria.tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.unam.ingenieria.tienda.fragments.BuscarFragment;
import mx.unam.ingenieria.tienda.fragments.ComprarFragment;
import mx.unam.ingenieria.tienda.fragments.InicioFragment;
import mx.unam.ingenieria.tienda.fragments.OperacionLargaFragment;
import mx.unam.ingenieria.tienda.fragments.SesionFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView btmNavigationPrincipal;
    private InicioFragment inicioFragment;
    private BuscarFragment buscarFragment;
    private SesionFragment sesionFragment;
    private ComprarFragment comprarFragment;
    private OperacionLargaFragment operacionLargaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNavigationPrincipal= findViewById(R.id.btmNavigationPrincipal);
        inicioFragment= new InicioFragment();
        buscarFragment= new BuscarFragment();
        sesionFragment= new SesionFragment();
        comprarFragment= new ComprarFragment();
        operacionLargaFragment= new OperacionLargaFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,inicioFragment).commit();

        btmNavigationPrincipal.setOnNavigationItemSelectedListener(navListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_opciones_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuOpOperaciones:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,operacionLargaFragment).commit();
                break;
        }



        return true;
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case  R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,inicioFragment).commit();
                    break;
                case R.id.nav_buscar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,buscarFragment).commit();
                    break;
                case R.id.nav_comprar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,comprarFragment).commit();
                    break;
                case R.id.nav_inicia:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,sesionFragment).commit();
            }

            return true;
        }
    };




}