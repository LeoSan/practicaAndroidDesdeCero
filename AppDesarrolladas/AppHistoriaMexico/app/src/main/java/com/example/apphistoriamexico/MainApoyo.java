package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainApoyo extends AppCompatActivity {
   private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apoyo);
        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Declaro elobjeto que contendra la funcionalidad del componente webview
        video = (VideoView)findViewById(R.id.idVideoView);
        //Uri uri = Uri.parse("https://www.youtube.com/watch?v=JysHlMS_Vjs");
        // Uri uri = Uri.parse("http://www.leonardcuenca.experticiacv.com/imagen/slider_3.webm");

        String path = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;

        video.setMediaController(new MediaController(this));
        video.setVideoURI(Uri.parse(path));
        video.requestFocus();
        video.start();
    }


    //Eventos
//Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }

    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }


}// fin de la clase Principal



