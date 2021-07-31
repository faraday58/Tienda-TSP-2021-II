package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;
import mx.unam.ingenieria.tienda.recyclerview.AdaptadorMuestraProducto;
import mx.unam.ingenieria.tienda.recyclerview.MuestraProducto;

public class BuscarFragment extends Fragment {

    private RecyclerView rcvProductos;
    private AdaptadorMuestraProducto mAdapter;
    private FirebaseFirestore mFirestore;  //Es el objeto o instancia que manipula a la base de datos
    private EditText edtBuscar;
    private ImageButton imgbBuscar;

    //private ArrayList<MuestraProducto> productos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar,container,false);
        edtBuscar= v.findViewById(R.id.edtBuscar);
        imgbBuscar= v.findViewById(R.id.imgBuscar);
        rcvProductos = v.findViewById(R.id.rcvProductos);
        mFirestore=FirebaseFirestore.getInstance(); //Abre la conexión con la base de datos


        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvProductos.setLayoutManager(llm);
  //      InicializarMuestraProductos();
        InicializarAdaptador();
        imgbBuscar.setOnClickListener(onClickBuscar);
        return v;
    }


    View.OnClickListener onClickBuscar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String nombre=edtBuscar.getText().toString();
            mAdapter= new AdaptadorMuestraProducto(Consulta(nombre),getContext());
            mAdapter.notifyDataSetChanged();
            rcvProductos.setAdapter(mAdapter);

        }
    };

    private void InicializarAdaptador() {

        mAdapter= new AdaptadorMuestraProducto(Consulta(),getContext());
        mAdapter.notifyDataSetChanged();
        rcvProductos.setAdapter(mAdapter);
      //  AdaptadorMuestraProducto adaptador= new AdaptadorMuestraProducto(productos);
       // rcvProductos.setAdapter(adaptador);
    }

    private FirestoreRecyclerOptions Consulta() {
        //Query query= mFirestore.collection("Producto").whereEqualTo("titulo","Trabajo");
        Query query= mFirestore.collection("Producto");
        FirestoreRecyclerOptions<MuestraProducto> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraProducto>()
                .setQuery(query,MuestraProducto.class).build();
        return firestoreRecyclerOptions;
    }

    private FirestoreRecyclerOptions Consulta(String busca) {
        //Query query= mFirestore.collection("Producto").whereEqualTo("titulo","Trabajo");
        Query query= mFirestore.collection("Producto").whereEqualTo("titulo",busca);
        FirestoreRecyclerOptions<MuestraProducto> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraProducto>()
                .setQuery(query,MuestraProducto.class).build();
        return firestoreRecyclerOptions;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    //
    /*
    private void InicializarMuestraProductos() {
        productos= new ArrayList<>();
        productos.add(new MuestraProducto("Estrena",R.drawable.teniscasual,"Justo el momento para iniciar"));
        productos.add(new MuestraProducto("Salir",R.drawable.tenisblancos,"Justo para salir"));
        productos.add(new MuestraProducto("Trabajo",R.drawable.zapatos,"El mejor gusto"));
    }

*/


}
