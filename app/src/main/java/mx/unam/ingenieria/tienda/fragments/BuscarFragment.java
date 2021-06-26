package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;
import mx.unam.ingenieria.tienda.recyclerview.AdaptadorMuestraProducto;
import mx.unam.ingenieria.tienda.recyclerview.MuestraProducto;

public class BuscarFragment extends Fragment {

    private RecyclerView rcvProductos;
    private ArrayList<MuestraProducto> productos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar,container,false);
        rcvProductos = v.findViewById(R.id.rcvProductos);
        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvProductos.setLayoutManager(llm);
        InicializarMuestraProductos();
        InicializarAdaptador();

        return v;
    }

    private void InicializarAdaptador() {
        AdaptadorMuestraProducto adaptador= new AdaptadorMuestraProducto(productos);
        rcvProductos.setAdapter(adaptador);
    }

    private void InicializarMuestraProductos() {
        productos= new ArrayList<>();
        productos.add(new MuestraProducto("Estrena",R.drawable.teniscasual,"Justo el momento para iniciar"));
        productos.add(new MuestraProducto("Salir",R.drawable.tenisblancos,"Justo para salir"));
        productos.add(new MuestraProducto("Trabajo",R.drawable.zapatos,"El mejor gusto"));
    }




}
