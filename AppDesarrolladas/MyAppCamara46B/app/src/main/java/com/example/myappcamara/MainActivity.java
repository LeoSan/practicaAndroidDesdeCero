package com.example.myappcamara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    private ImageView imagen;
    private Button botonCargar;

    //Ruta para tomar la foto
    private final String CARPETA_RAIZ = "my_images";
    private final String RUTA_IMAGEN = CARPETA_RAIZ + "/.";
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView)findViewById(R.id.imagenId);
        botonCargar = (Button)findViewById(R.id.btnCargarImagen);


        // Si tiene Permisos Activa el boton y ejecuta la orden
        if (validarPermisos()){
            botonCargar.setEnabled(true);
        }else{
            botonCargar.setEnabled(false);
        }
    }// fin del OnCreate

    //Metodo -> Forma Peculiar del onclick, lo hice asi porque no logro desde el layout cargarlo. Establecer la función directa cargarImagen()
    public void onClick(View view) {
        cargarImagen();
    }


    // Metodo -> Permite validar los permsisos
    private boolean validarPermisos() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return  true;
        }

        if ( (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) )
        {
            return  true;
        }

        if ( (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) ||  (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) ){
            cargarDialogoRecomendacion();
        }else{

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
                return  true;
            }

        }

        return  false;
    }

    // Metodo -> Permite cargar un dialogo permite que el usuario pueda elegir Si tomar la foto , cargar una imagen de galerias, cancelar
    private void cargarDialogoRecomendacion() {

        AlertDialog.Builder alertOpciones  = new AlertDialog.Builder(MainActivity.this);
        alertOpciones.setTitle("Permisos desactivados");
        alertOpciones.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la app");
        alertOpciones.setPositiveButton("Aceptar", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
                }
            }
        });
        alertOpciones.show();
    }




    // Metodo -> Permite cargar el dialogo y dependiendo la opciójn carga la imagen o toma la foto (IINICIO)
    private void cargarImagen() {
        // Forma para cargar el alert Opciones
        final  CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones  = new AlertDialog.Builder(MainActivity.this);
        alertOpciones.setTitle("Seleccione una Puta opción");

        //Activa el Alert opciones
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar Foto")){
                       tomarFotografia();
                    //Toast.makeText(getApplicationContext(), "Tomar Foto", Toast.LENGTH_SHORT).show();

                }else{
                    if( opciones[i].equals("Cargar Imagen") ){
                        //Alternativa 1 -> para cargar una imagen de la galeria
                        Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //Alternativa 2 -> para cargar una imagen de la galeria
                        // Intent intent = new Intent( Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        // permite escoger de la galeria
                        startActivityForResult(intent.createChooser(intent, "Seleccione tu imagen de la galeria"), 10);

                    }else{
                        dialog.dismiss();
                    }

                }

            }
        });

        //Esto permite levantar alert Show con opciones
        alertOpciones.show();

    }// fin del metodo cargarImagen()

    // Metodo -> Permite tomar la foto. Tengo fallas.
    private void tomarFotografia() {
        Uri photoURI = null;
       try {
           File fileImagen = new File(Environment.getExternalStorageState(), RUTA_IMAGEN);
           boolean isCreada = fileImagen.exists();
           String nombreImagen = "";

           if (isCreada == false){
               isCreada = fileImagen.mkdirs();

           }

           if (isCreada == true){
               nombreImagen = System.currentTimeMillis() / 1000 +".jpg " ;
           }

           // Ruta de almacenamiento. donde se alamacenará la imagen
           path = Environment.getExternalStorageState()+File.separator + RUTA_IMAGEN + File.separator + nombreImagen;
           File imagen = new File(path);
           photoURI = FileProvider.getUriForFile(MainActivity.this, getString(R.string.file_provider_authority), imagen);


           //Esta línea permite lanzar el uso de la camara
           Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           // intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen)); // original
           takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI); // nueva
           // todo -> Inicia el lanzamiento y llamo el metodo de la linea 233
           startActivityForResult(takePictureIntent, 20);

       }catch (Exception e){

           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);


       }

    }

    // Metodo -> Permite set a los componentes si los resultados son exitosos.
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data );

        if (resultCode  == RESULT_OK){

            switch (requestCode){
                case 10://todo -> Aqui set la imagen de galeria en el imageView del layout
                    Uri miPath = data.getData();
                    imagen.setImageURI(miPath);
                    break;

                case 20://todo-> Aqui set la imagen capturada desde la camara
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener(){
                                @Override
                                public  void onScanCompleted(String s, Uri uri){
                                    Log.i("Ruta de Almacenamiento", "Path:"+path);
                                }
                            });

                    Bitmap bitmap = new BitmapFactory().decodeFile(path);
                    imagen.setImageBitmap(bitmap);
                    break;
            }

        }

    }// fin del metodo set.

}// fin de la clase.
