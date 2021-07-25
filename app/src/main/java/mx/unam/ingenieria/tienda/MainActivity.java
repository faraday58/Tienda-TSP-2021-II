package mx.unam.ingenieria.tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mx.unam.ingenieria.tienda.fragments.ActualizarFragment;
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
    private FloatingActionButton fab;
    private ActualizarFragment actualizarFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNavigationPrincipal= findViewById(R.id.btmNavigationPrincipal);
        fab= findViewById(R.id.fab);
        inicioFragment= new InicioFragment();
        buscarFragment= new BuscarFragment();
        sesionFragment= new SesionFragment();
        comprarFragment= new ComprarFragment();
        actualizarFragment= new ActualizarFragment();
        operacionLargaFragment= new OperacionLargaFragment();
        fab.setOnClickListener(onClickFab);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,inicioFragment).commit();

        btmNavigationPrincipal.setOnNavigationItemSelectedListener(navListener);

    }

    View.OnClickListener onClickFab= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor,actualizarFragment).commit();
        }
    };

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