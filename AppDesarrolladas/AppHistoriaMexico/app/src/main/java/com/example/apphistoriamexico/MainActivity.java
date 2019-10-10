package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }


    //Apunta al Activity de Guia (Cuestionario)
    public void vistaGuia (View view){
        Intent siguiente = new Intent(this,MainGuia.class);
        startActivity(siguiente);
    }


    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){
        Intent siguiente = new Intent(this,MainContacto.class);
        startActivity(siguiente);
    }

    //Apunta al Activity de Preguntas (Aqui se realizaran las preguntas)
    public void vistaPreguntas (View view){
        Intent siguiente = new Intent(this,MainGuia.class);
        startActivity(siguiente);
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
    }


}//fin de la clase
