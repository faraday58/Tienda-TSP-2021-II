package mx.unam.ingenieria.tienda.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.grpc.Context;
import mx.unam.ingenieria.tienda.R;

import static android.app.Activity.RESULT_OK;

public class ActualizarFragment extends Fragment {

    private static final int PICK_FOTO =12 ;
    private FirebaseFirestore db;
    private StorageReference mStorageRef;
    private ImageView imgvFoto;
    private EditText edtTitulo;
    private EditText edtDescripcion;
    private Button btnFoto;
    private Button btnActualizar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_actualizar,container,false);

        db= FirebaseFirestore.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();
        imgvFoto= v.findViewById(R.id.imgvFoto);
        edtTitulo= v.findViewById(R.id.edtTitulo);
        edtDescripcion= v.findViewById(R.id.edtDescripcion);
        btnActualizar= v.findViewById(R.id.btnActualizar);
        btnFoto= v.findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(onClickFoto);
        return v;

    }

    View.OnClickListener onClickFoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AbrirGaleria();
        }
    };

    private void AbrirGaleria() {
        Intent intentGaleria= new Intent();
        intentGaleria.setType("image/*");
        intentGaleria.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGaleria,"Seleccionar imagen"),PICK_FOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(  requestCode == PICK_FOTO && resultCode == RESULT_OK  && data != null && data.getData() != null   )
        {
            Bitmap bitmap = null;
            Uri directorio= data.getData();
            final StorageReference director=mStorageRef.child("fotos").child(directorio.getLastPathSegment());



            if(Build.VERSION.SDK_INT >= 28)
            {



            }


        }


    }
}
