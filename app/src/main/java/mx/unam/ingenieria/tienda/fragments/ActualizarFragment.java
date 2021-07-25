package mx.unam.ingenieria.tienda.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

import mx.unam.ingenieria.tienda.R;

import static android.app.Activity.RESULT_OK;

public class ActualizarFragment extends Fragment {

    private FirebaseFirestore db;
    private StorageReference mStorageRef;
    private static final int PICK_FOTO = 12;
    private ImageView imgvfoto;
    private EditText edtDescripcion;
    private EditText edtTitulo;
    private Button btnFoto;
    private Button btnActualizar;
    String imagen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_actualizar, container, false);
        btnFoto = v.findViewById(R.id.btnFoto);
        btnActualizar = v.findViewById(R.id.btnActualizar);
        imgvfoto = v.findViewById(R.id.imgbFoto);
        edtDescripcion = v.findViewById(R.id.edtDescripcion);
        edtTitulo = v.findViewById(R.id.edtTitulo);
        mStorageRef= FirebaseStorage.getInstance().getReference();
        db= FirebaseFirestore.getInstance();

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
        //Este es un intent implícito
        Intent intentGaleria = new Intent();
        //Seleccionando tipo de archivo por abrir
        intentGaleria.setType("image/*");
        //Seleccionar la aplicación que abrirá el tipo de archivo
        intentGaleria.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGaleria, "Selecciona una imagen"), PICK_FOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == PICK_FOTO && resultCode == RESULT_OK && data != null && data.getData() != null   ){
            Bitmap bitmap = null;
            Uri directorio= data.getData();
            //Directorio Referencia en Firebase
            final StorageReference directorior= mStorageRef.child("fotos").child(directorio.getLastPathSegment());

            if(Build.VERSION.SDK_INT >= 28)
            {
                //Filtro de la  ruta de la imagen
                ImageDecoder.Source source = ImageDecoder.createSource(getActivity().getContentResolver(),directorio);
                try{
                    bitmap = ImageDecoder.decodeBitmap(source);
                    imgvfoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),directorio);
                    imgvfoto.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            directorior.putFile(directorio).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                    if(!task.isSuccessful())
                    {
                        Toast.makeText(getContext(),"No se pudo subir el archivo en el storage",Toast.LENGTH_SHORT).show();
                        throw new Exception();
                    }
                    return directorior.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Se subió correctamente el archivo",Toast.LENGTH_SHORT).show();
                        Uri downloadLink= task.getResult();
                        imagen= downloadLink.toString();
                        Log.d("Link","Link de descarga: " +imagen);

                    }
                }
            });
        }

    }
}