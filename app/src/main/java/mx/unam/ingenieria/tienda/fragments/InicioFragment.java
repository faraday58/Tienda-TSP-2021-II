package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.tienda.R;

public class InicioFragment extends Fragment {

    private ListView lstProductos;
    private String [] productos={
                "Zapatos",
                "WebCams",
                "Mochila",
                "SmartWatch"

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio,container,false);

        lstProductos= v.findViewById(R.id.lstProductos);
        RellenarLista();
        lstProductos.setOnItemClickListener(onClickItem);
        return v;
    }

    private  void RellenarLista(){


        ArrayAdapter miAdapter= new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1,productos
                );

        lstProductos.setAdapter(miAdapter);

    }



    AdapterView.OnItemClickListener onClickItem= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(),"Mostrando " + productos[position] ,Toast.LENGTH_SHORT ).show();

        }
    };







}
