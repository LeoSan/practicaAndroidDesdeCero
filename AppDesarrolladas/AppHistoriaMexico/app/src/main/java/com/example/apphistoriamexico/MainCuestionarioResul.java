package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainCuestionarioResul extends AppCompatActivity {

    ProgressBar idBarraCuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuestionario_resul);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        idBarraCuestionario =  (ProgressBar)findViewById(R.id.idBarraCuestionario);

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////EVENTOS DINAMICOS /////////////////////////////////////////////////////////////

    //Evento -> Barra de progreso
    private void setBarra(String indicePreg, String idCategoria, String idNivel, String iContador) {
        //String contTotalPreg = getPreguntasTotal( idCategoria, idNivel );
        idBarraCuestionario.setMax( 100 );
        idBarraCuestionario.setProgress( 50 );
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
    }

}// fin de la clase.
