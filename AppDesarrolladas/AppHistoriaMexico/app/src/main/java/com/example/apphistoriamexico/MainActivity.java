package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Metodos dinamicos
         reproducirMusicaIntro();


    }//Fin del onCreate

    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }


    public void reproducirMusicaIntro(){
        mp = MediaPlayer.create(this, R.raw.introhistoria );
        mp.start();
        mp.setLooping(true);
    }

    public void stopReproducir(){
        mp.stop();
        mp.release();
    }


    //Apunta al Activity de Guia (Cuestionario)
    public void vistaGuia (View view){

        stopReproducir();

        Intent siguiente = new Intent(this, MainGuia.class);
        //Metodo que me permite enviar variables a otro Activity
        siguiente.putExtra("IdActividad", "1");
        startActivity(siguiente);
        finish();
    }

    //Apunta al Activity de Preguntas (Reto al Saber)
    public void vistaPreguntas (View view){

        stopReproducir();

        Intent siguiente = new Intent(this, MainGuia.class);
        //Metodo que me permite enviar variables a otro Activity
        siguiente.putExtra("IdActividad", "2");
        startActivity(siguiente);
        finish();
    }

    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){

        stopReproducir();

        Intent siguiente = new Intent(this, MainContacto.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        stopReproducir();
        Intent interfaz = new Intent(this, MainApoyo.class);
        startActivity(interfaz);
        finish();
    }

    //Apunta al Activity de Preguntas (Reto al Saber)
    public void vistaEstaConfig (View view){

        stopReproducir();
        Intent siguiente = new Intent(this, MainEstadisticaConfiguracion.class);
        startActivity(siguiente);
        finish();
    }



}//fin de la clase
