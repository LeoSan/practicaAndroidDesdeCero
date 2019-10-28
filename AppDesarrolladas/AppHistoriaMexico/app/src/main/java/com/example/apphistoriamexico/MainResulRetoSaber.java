package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainResulRetoSaber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resul_reto_saber);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String IdEstudio   = getIntent().getStringExtra("IdEstudio");


        // todo Test
        System.out.println( "  -------------------------------------  PARAMETROS DE ENTRADA ------------------------------------------------------ "  );
        System.out.println( "  IdCategoria " + IdCategoria );
        System.out.println( "  IdNivel "     + IdNivel     );
        System.out.println( "  IdEstudio "  + IdEstudio  );

    }






}
