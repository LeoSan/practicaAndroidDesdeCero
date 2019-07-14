package com.example.miviewweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainWeb extends AppCompatActivity {

    private TextView inpNombre;
    WebView wv1; // no se le pone modificador de acceso ya que no necesitamos un acceso tan restringido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);

        //Declaro elobjeto que comtendra la funcionalidad del componente webview
        wv1 = (WebView)findViewById(R.id.webView);

        //recuperamos Valores
        String URL = getIntent().getStringExtra("sitioWeb");

        //definimos que navegador abrir - Este metodo hace que se abra la pagina en nuestra aplicacion
        wv1.setWebViewClient(new WebViewClient());

        wv1.loadUrl("http://"+URL);
    }

    public void Cerrar(View view){
        finish();
    }
}
