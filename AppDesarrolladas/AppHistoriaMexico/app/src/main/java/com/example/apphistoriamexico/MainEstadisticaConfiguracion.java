package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainEstadisticaConfiguracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estadistica_configuracion);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){
        Intent siguiente = new Intent(this, MainContacto.class);
        startActivity(siguiente);
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this, MainApoyo.class);
        startActivity(interfaz);
    }


    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
    }

}
