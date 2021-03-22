package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;

public class InicioFragment extends Fragment {

    ListView lstvProductos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_inicio,container,false);
        lstvProductos= v.findViewById(R.id.lstvProductos);
        llenarLista();

        return v;
    }

    private void llenarLista() {
       String [] productos= {
               "Tenis",
               "Zapatos",
               "Plataformas"
       };

        ArrayAdapter miAdapter= new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1,
                productos);
        lstvProductos.setAdapter(miAdapter);

    }


}
