package mx.unam.ingenieria.tienda.recyclerview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;


public class AdaptadorMuestraProducto extends RecyclerView.Adapter<AdaptadorMuestraProducto.ViewHolder> {

    ArrayList<MuestraProducto> productos;


    public AdaptadorMuestraProducto(Activity activity, ArrayList<MuestraProducto> productos){
        this.productos= productos;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_producto,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MuestraProducto producto= (MuestraProducto) productos.get(position);
            holder.txtvCtitulo.setText(producto.getTitulo());
            holder.txtvCVdescribe.setText(producto.getDescripcion());
            holder.imgButProducto.setImageResource(producto.getImagen());

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    //Esta clase asocia la vista del cardview
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtvCtitulo;
        private ImageButton imgButProducto;
        private TextView txtvCVdescribe;
        private Button btnCVColeccion;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvCtitulo= itemView.findViewById(R.id.txtvCtitulo);
            imgButProducto= itemView.findViewById(R.id.imgButProducto);
            txtvCVdescribe= itemView.findViewById(R.id.txtvCVdescribe);
            btnCVColeccion= itemView.findViewById(R.id.btnCVColeccion);

        }
    }

}
