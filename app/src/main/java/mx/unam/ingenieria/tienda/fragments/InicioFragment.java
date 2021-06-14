package mx.unam.ingenieria.tienda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;

public class InicioFragment extends Fragment {

    private static final String FILE_NAME ="productos.txt" ;
    private TextView txtvProductos;
    private ListView lstvProductos;
    private String productoSelect;
    private String [] productos= {
            "Tenis",
            "Zapatos",
            "Plataformas"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_inicio,container,false);
        lstvProductos= v.findViewById(R.id.lstvProductos);
        txtvProductos=v.findViewById(R.id.txtvSelect);
        llenarLista();
        lstvProductos.setOnItemClickListener(onClickItem);
        registerForContextMenu(lstvProductos);


        return v;
    }



    AdapterView.OnItemClickListener onClickItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(),"Mostrando " + productos[position] ,Toast.LENGTH_SHORT ).show();

        }
    };



    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_archivos,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        productoSelect= lstvProductos.getItemAtPosition(info.position).toString();
        switch (item.getItemId()){
            case R.id.menContCargar:
                CargarProductoSeleccionados();
                break;
            case R.id.menContSalvar:
                Salvar();
                break;

        }


        return super.onContextItemSelected(item);
    }

    private void llenarLista() {


        ArrayAdapter miAdapter= new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1,
                productos);
        lstvProductos.setAdapter(miAdapter);

    }

    public void Salvar()
    {
        FileOutputStream fos= null;

        try {
            fos= getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(productoSelect.getBytes());
            Toast.makeText(getContext(),"Salvando Archivos en "+
                    getActivity().getFilesDir() + "/"+FILE_NAME ,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void CargarProductoSeleccionados()
    {
        FileInputStream fis= null;
        try {
            fis=getActivity().openFileInput(FILE_NAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            String texto;
            while ((texto=br.readLine()) !=null){
                sb.append(texto).append("\n");
            }
            txtvProductos.setText(sb.toString());
            Toast.makeText(getContext(),""+texto ,Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null)
            {
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
