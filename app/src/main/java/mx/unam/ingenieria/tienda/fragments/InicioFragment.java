package mx.unam.ingenieria.tienda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.tienda.R;

public class InicioFragment extends Fragment {

    private static final String FILE_NAME = "archivo.txt" ;
    private FirebaseFirestore mFirestore;
    private EditText edtBuscar;
    private TextView txtvProducto;
    private ListView lstProductos;
    private ImageButton imgbBuscar;
    /*
    private String [] productos={
                "Zapatos",
                "WebCams",
                "Mochila",
                "SmartWatch"
    };*/

    private ArrayList<String> productos;

    private String productoSelect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio,container,false);
        imgbBuscar= v.findViewById(R.id.imgbBusca);
        productos= new ArrayList<>();
        mFirestore= FirebaseFirestore.getInstance();
        edtBuscar= v.findViewById(R.id.edtBuscar);
        lstProductos= v.findViewById(R.id.lstProductos);
        txtvProducto= v.findViewById(R.id.txtvArchivo);
        lstProductos.setOnItemClickListener(onClickItem);
        registerForContextMenu(lstProductos);
        imgbBuscar.setOnClickListener(onClickBuscar);
        return v;
    }
    View.OnClickListener onClickBuscar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String busqueda= edtBuscar.getText().toString();
            productos.clear();

            Buscar(busqueda);
        }
    };

    private void Buscar(String busqueda)
    {
         mFirestore.collection("Ofertas").whereEqualTo("titulo",busqueda).get()
                 .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                     @Override
                     public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                         for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
                         {
                             productos.add(documentSnapshot.get("nombre").toString());
                         }
                         RellenarLista();

                     }
                 });


    }



    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_archivos,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        productoSelect= lstProductos.getItemAtPosition(info.position).toString();

        switch (item.getItemId()){
            case R.id.menContSalvar:
                Salvar();
                break;
            case R.id.menConCargar:
                Cargar();
                break;

        }

        return super.onContextItemSelected(item);
    }

    private void Cargar() {
        FileInputStream fis = null;

        try {
            fis= getActivity().openFileInput(FILE_NAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            String texto;
            while((texto=br.readLine())!=null  )
            {
                sb.append(texto).append("\n");
            }
            txtvProducto.setText(sb.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void Salvar()  {
        FileOutputStream fos= null;

        try {
            fos=getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(productoSelect.getBytes());
            Toast.makeText(getContext(),"Salvando el archivo en "+
                    getActivity().getFilesDir()+ "/"+FILE_NAME,Toast.LENGTH_LONG ).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!= null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private  void RellenarLista(){


        ArrayAdapter miAdapter= new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1,productos
                );

        lstProductos.setAdapter(miAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    AdapterView.OnItemClickListener onClickItem= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(),"Mostrando " + productos.get(position) ,Toast.LENGTH_SHORT ).show();

        }
    };

}
