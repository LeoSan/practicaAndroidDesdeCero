package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class MainMapa extends AppCompatActivity {

    TextView OrdenEstado01,OrdenEstado02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mapa);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Relacion Interfaz controlador
        OrdenEstado01     = (TextView)findViewById(R.id.OrdenEstado01);
        OrdenEstado02     = (TextView)findViewById(R.id.OrdenEstado02);


        System.out.println("---------------- Estoy MainMapa.java  -------------------------------");
    }
    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }

    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){

        //stopReproducir();
        Intent siguiente = new Intent(this, MainContacto.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        //stopReproducir();
        Intent interfaz = new Intent(this, MainApoyo.class);
        startActivity(interfaz);
        finish();
    }

    //Apunta al Activity de Preguntas (Reto al Saber)
    public void vistaEstaConfig (View view){

        //stopReproducir();
        Intent siguiente = new Intent(this, MainEstadisticaConfiguracion.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }


    //Apunta al Activity de Preguntas (Reto al Saber)
    public void abrirMaps (View view){
        String[ ] sEstadosMapas = new  String[31];// Son 32 estados

        sEstadosMapas[01] = "https://www.google.com.mx/maps/place/Aguascalientes/@22.2977604,-104.0706238,6z/data=!4m5!3m4!1s0x8429e6a5760913b9:0x662e2fa6b1f4151f!8m2!3d21.8853247!4d-102.2915131";
        sEstadosMapas[02] = "https://www.google.com.mx/maps/place/Baja+California/@30.3449022,-117.7752789,7z/data=!3m1!4b1!4m5!3m4!1s0x80d7700ca877ddd3:0xfca4fd9f0318de8e!8m2!3d30.8406338!4d-115.2837585";
        sEstadosMapas[03] = "https://www.google.com.mx/maps/place/Baja+California+Sur/@25.4238927,-114.5630432,7z/data=!3m1!4b1!4m5!3m4!1s0x86afd339b5a21ec9:0x71dfb33aa9a75918!8m2!3d26.0444446!4d-111.6660725";

        String urlEstado =   sEstadosMapas[  Integer.parseInt((String) OrdenEstado02.getText()) ] ;

        //Genero el enlace
        Uri uri = Uri.parse(urlEstado);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

}//fin  de la clase

