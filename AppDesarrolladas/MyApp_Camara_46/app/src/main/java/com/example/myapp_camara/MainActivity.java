package com.example.myapp_camara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageButton imageButton;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageButton = (ImageButton)findViewById(R.id.imageButton);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    }

    // Metodo para tomar la foto

    public void metodoTomaFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Cierra de manera momentanea el activity actual
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go

            try {
                photoFile = createImageFile();
                Toast.makeText(this, "Entro Al metodo Crea el metodo  ", Toast.LENGTH_SHORT).show();

            } catch (IOException ex) {
                // Error occurred while creating the File
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.exit(0);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){// Esto es la version 7 O IGUAL  EN ADELANTE

                    Toast.makeText(this, "Entro a esta version 1  " + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();

                    String authorities = getApplicationContext().getPackageName() + ".provider";
                    Uri photoURI = FileProvider.getUriForFile(this,  authorities, photoFile);
                //    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,  photoURI);
                    //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,  photoURI.toString());

                }else{// VERSION INFERIOR A 7
                    Toast.makeText(this, "Entro a esta version 2   " + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,  Uri.fromFile(photoFile));
                }

               // startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }else{
                Toast.makeText(this, "----- NO CREO EL ARCHIVO -", Toast.LENGTH_SHORT).show();
            }

        }
    }


    // Metodo para generar el backup

    private File createImageFile() throws IOException{
        //Crreamos una imagen con su nombre
        String timeStamp  = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return  image;
    }






    // Muestra la imagen tomada al imageView

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }



}
