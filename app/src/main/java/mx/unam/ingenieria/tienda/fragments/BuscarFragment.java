package mx.unam.ingenieria.tienda.fragments;

import android.icu.lang.UProperty;
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

    private RecyclerView rcvFragBuscar;
    ArrayList<MuestraProducto> productos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_buscar,container,false);
        rcvFragBuscar = v.findViewById(R.id.rcvFragBuscar);
        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvFragBuscar.setLayoutManager(llm);
        InicializarMuestrasProductos();
        InicializarAdaptador();
        return v;
    }

    private void InicializarAdaptador()
    {
        AdaptadorMuestraProducto adaptador= new AdaptadorMuestraProducto(this.getActivity(),productos);
        rcvFragBuscar.setAdapter(adaptador);

    }


    private void InicializarMuestrasProductos()
    {
        productos= new ArrayList<>();
        productos.add(new MuestraProducto("Entrenar",R.drawable.tenisblancos,"Justo el momento para iniciar"));
        productos.add(new MuestraProducto("Salir",R.drawable.teniscasual,"A recorrer la ciudad "));
        productos.add(new MuestraProducto("Trabajo",R.drawable.zapatos, "Vamos al trabajo"));

    }
}
