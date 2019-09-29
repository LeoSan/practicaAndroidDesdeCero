package com.example.appreproductor_40;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // Instacionamos las Variables para crear el reproductor
    Button play_pause, btn_repetir;  // Como usaremos eventos de los botones creamo las instancias
    MediaPlayer mp;      // Es el objeto que permitira reproducir el audio
    ImageView iv;        // las imagenes seran dinamicas por lo que creamos su  objeto
    int repetir = 2, posicion = 0; // Variables tipo banderas

    MediaPlayer vectormp [] = new MediaPlayer[6]; // cantidad de pistas de audios


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button)findViewById(R.id.tbn_play);
        btn_repetir = (Button)findViewById(R.id.btn_repetir);
        iv = (ImageView)findViewById(R.id.img_portada);

        //llamo al metodo para arrancar las canciones
        cancionesIniciar();



    }


    // Metodo que  reproduce
    public void PlayPause(View view){

        if ( vectormp[posicion].isPlaying() ){

            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();

        }else{

            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Reproducir", Toast.LENGTH_SHORT).show();
        }


    }

    // Metodo que  reproduce
    public void Pause(View view){

        if ( vectormp[posicion] != null ){

            vectormp[posicion].stop();
            //llamo al metodo para arrancar las canciones
            cancionesIniciar();
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }

    }

    // Metodo que  reproduce
    public void Repetir(View view){

        if ( repetir == 1 ){

            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;

        }else{
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }

    }

    // Metodo que  reproduce
    public void Siguiente(View view){

        if ( posicion < vectormp.length -1 ){

            if ( vectormp[posicion].isPlaying() ){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();

                if (posicion == 0 ){
                    iv.setBackgroundResource(R.drawable.portada1);
                }else if (posicion == 1){
                    iv.setBackgroundResource(R.drawable.portada2);
                }else if(posicion == 2){
                    iv.setBackgroundResource(R.drawable.portada3);
                }

            }else{
                posicion++;
                if (posicion == 0 ){
                    iv.setBackgroundResource(R.drawable.portada1);
                }else if (posicion == 1){
                    iv.setBackgroundResource(R.drawable.portada2);
                }else if(posicion == 2){
                    iv.setBackgroundResource(R.drawable.portada3);
                }
            }


        }else{
            Toast.makeText(this, "No hay Mas canciones", Toast.LENGTH_SHORT).show();
        }

    }


    // Metodo para la  canciòn anterior
    public void Anterior(View view){
        if (posicion >= 1){
            if (vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                //llamo al metodo para arrancar las canciones
                cancionesIniciar();
                posicion--;
                vectormp[posicion].start();
                if (posicion == 0 ){
                    iv.setBackgroundResource(R.drawable.portada1);
                }else if (posicion == 1){
                    iv.setBackgroundResource(R.drawable.portada2);
                }else if(posicion == 2){
                    iv.setBackgroundResource(R.drawable.portada3);
                }

            }else{
                posicion--;
                if (posicion == 0 ){
                    iv.setBackgroundResource(R.drawable.portada1);
                }else if (posicion == 1){
                    iv.setBackgroundResource(R.drawable.portada2);
                }else if(posicion == 2){
                    iv.setBackgroundResource(R.drawable.portada3);
                }

            }

        }else{
            Toast.makeText(this, "No hay Mas canciones", Toast.LENGTH_SHORT).show();
        }

    }

    // Metodo que  permite colocar las canciones desde inicio
    public void cancionesIniciar(){

        vectormp[0] = MediaPlayer.create(this, R.raw.marcha);
        vectormp[1] = MediaPlayer.create(this, R.raw.musica02);
        vectormp[2] = MediaPlayer.create(this, R.raw.musica03);
        vectormp[3] = MediaPlayer.create(this, R.raw.race);
        vectormp[4] = MediaPlayer.create(this, R.raw.sound);
        vectormp[5] = MediaPlayer.create(this, R.raw.tea);

    }



}
