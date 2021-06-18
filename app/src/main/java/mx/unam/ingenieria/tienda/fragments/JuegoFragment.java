package mx.unam.ingenieria.tienda.fragments;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.tienda.R;

public class JuegoFragment extends Fragment {

    private ImageView[][] matImv= new ImageView[4][4];
    private int[][] tablero= {
        {1,1,2,2},
        {3,3,4,4},
        {5,5,6,6},
        {7,7,8,8}
    };

    //Si se puede dar click sobre una carta y destaparla
    //se desactiva después de destapar dos cartas diferentes
    // se activa nuevamente después de 3 segundos
    private boolean Activa;
    //Indica si se le da click por primera vez o segunda vez a una carta
    private int nclik;
    //Guardar la coordenada de la primera carta donde se hace el click
    private  int primerax=-1,primeray=-1;
    //Recurperar todas las cartas destapadas, por default en false
    private boolean[][] destapada= new boolean[4][4];
    //Manejador de Hilo
    private Handler manejador= new Handler(Looper.myLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_juego,container,false);
        matImv[0][0]= v.findViewById(R.id.imageView1);
        matImv[0][1]= v.findViewById(R.id.imageView2);
        matImv[0][2]= v.findViewById(R.id.imageView3);
        matImv[0][3]= v.findViewById(R.id.imageView4);

        matImv[1][0]= v.findViewById(R.id.imageView5);
        matImv[1][1]= v.findViewById(R.id.imageView6);
        matImv[1][2]= v.findViewById(R.id.imageView7);
        matImv[1][3]= v.findViewById(R.id.imageView8);

        matImv[2][0]= v.findViewById(R.id.imageView9);
        matImv[2][1]= v.findViewById(R.id.imageView10);
        matImv[2][2]= v.findViewById(R.id.imageView11);
        matImv[2][3]= v.findViewById(R.id.imageView12);

        matImv[3][0]= v.findViewById(R.id.imageView13);
        matImv[3][1]= v.findViewById(R.id.imageView14);
        matImv[3][2]= v.findViewById(R.id.imageView15);
        matImv[3][3]= v.findViewById(R.id.imageView16);
        revolverCartas();
        return v;
    }

    private void revolverCartas() {
        for(int i =0; i< 100; i++)
        {
            int x1= (int)(Math.random()*4);
            int y1= (int)(Math.random()*4);
            int x2= (int)(Math.random()*4);
            int y2= (int)(Math.random()*4);
            int aux=tablero[x1][y1];
            tablero[x1][y1]=tablero[x2][y2];
            tablero[x2][y2]=tablero[x1][y1];

        }

    }


    public void onClickCarta(View v){
        if(Activa){
            nclik++;
            if(nclik==2) {
                if (v == matImv[primerax][primeray]) // Es verdadera si se da clik dos veces a la misma carta
                {
                    nclik--;
                    return;
                }
            }
             verificarCartaSeleccionada(v);
            if (nclik==2){
                congelar3Segundos();
            }



        }


    }

    private void congelar3Segundos() {
        nclik=0;
        Activa=false;
        primerax=-1;
        primeray=-1;

        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                Activa= true;
                for(int i=0; i< 4;i++)
                    for(int j=0; j < 4; j++){
                        if(destapada[i][j]){
                            destapar(matImv[i][j],tablero[i][j]);
                        }
                        else{
                            matImv[i][j].setImageResource(R.drawable.oculto);
                        }
                    }
            }
        },3000);

    }

    private void verificarCartaSeleccionada(View v) {

        for( int i=0; i < 4 ; i++){
            for(int j=0;j < 4 ; j++){
                if(matImv[i][j] == v  ){ //Carta seleccionada
                    destapar(matImv[i][j],tablero[i][j] ); //Se destapa la carta seleccionada
                    if(nclik == 1){ //Si es la primera vez que se selecciona la carta, entonces se guarda la coordenada
                        primerax=i;
                        primeray=j;
                        return;
                    }else{
                        if(tablero[i][j] == tablero[primerax][primeray] ){//Si es la segunda vez que se selecciona y coincide con la primera carta

                            destapada[i][j]=true; //Imagen que se acaba de seleccionar
                            destapada[primerax][primeray]=true;//Imagen Anterior
                            verificarSiGana();
                            nclik=0;
                            return;

                        }
                    }


                }

            }
        }
    }

    private void verificarSiGana() {
        int destapadas = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (destapada[i][j]) {
                    destapadas++;    //Contando el total de cartas destapadas
                }
            }

        }

        if (destapadas == 16)
        {
            Toast.makeText(getContext(),"Ya ganaste....",Toast.LENGTH_SHORT).show();
        }

    }

    private void destapar(ImageView imageView, int tabl) {

        switch (tabl){
            case 1:
                imageView.setImageResource(R.drawable.imagen1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.imagen2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.imagen3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.imagen4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.imagen5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.imagen6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.imagen7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.imagen8);
                break;
        }
    }


}
