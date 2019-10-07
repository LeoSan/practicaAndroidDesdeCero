package com.example.myappgrabadora_42;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btnRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecorder = (Button)findViewById(R.id.btnRecorder);


        //Permiso para la aplicacion esto hace que se abra una pestaña diciendole si desea darle permisos
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

    }


    //Grabadora
    public  void Recorder(View view){

        //Valido si existe una grabacion
        if (grabacion == null){
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            //creo el objeto para iniciar la grabacion
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//No afecta nada el formato de salida al mp3
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);// ??
            grabacion.setOutputFile(archivoSalida);

            try{
                grabacion.prepare();
                grabacion.start();
            }catch (IOException e){
                Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show();
            }

            btnRecorder.setBackgroundResource(R.drawable.rec);
            Toast.makeText(this, "Inicio Grabacion ", Toast.LENGTH_LONG).show();
        }else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btnRecorder.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(this, "Grabacion finalizada ", Toast.LENGTH_LONG).show();
        }


    }

    public void Reproducir(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();

        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();

            //inicia la reproduccion
            mediaPlayer.start();
            Toast.makeText(this, "Reproducir ", Toast.LENGTH_LONG).show();

        }catch (IOException e){
            Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show();
        }

    }


}
