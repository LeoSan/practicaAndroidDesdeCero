package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainApoyo extends AppCompatActivity {
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apoyo);
        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Declaro elobjeto que comtendra la funcionalidad del componente webview
        wv1 = (WebView)findViewById(R.id.idWebViewApoyo);
        //recuperamos Valores
        String URL = "https://www.youtube.com/watch?v=JysHlMS_Vjs";
        //definimos que navegador abrir - Este metodo hace que se abra la pagina en nuestra aplicacion
        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl(URL);
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



