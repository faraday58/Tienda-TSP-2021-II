package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.tienda.MainActivity;
import mx.unam.ingenieria.tienda.R;


public class OperacionLargaFragment extends Fragment  {
    Button btnContar;
    TextView txtvContador;
    long suma=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_operacion_larga,container,false);
        btnContar= v.findViewById(R.id.btnContar);
        txtvContador=v.findViewById(R.id.txtvContador);
        btnContar.setOnClickListener(onClick);
        return v;
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtvContador.setText("Espere un momento ...");
           // Thread hilo = new Thread();
            //hilo.start();
            OperacionLarga();
        }
    };


    private void OperacionLarga(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(long i=0; i <= 1_000_000_000;i++  )
                {
                    suma+=i;
                }
                Log.d("Resultado","Total: " +suma);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtvContador.setText( String.valueOf( suma));
                    }
                });
            }
        }).start();
    }

/*
    @Override
    public void run() {

        for(long i=0; i <= 1_000_000_000;i++  )
        {
            suma+=i;
        }
        Log.d("Resultado","Total: " +suma);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText( String.valueOf( suma));
            }
        });
    }
    */

}
