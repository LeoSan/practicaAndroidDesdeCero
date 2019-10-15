package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainNivelesReto extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contLinearBtnsNiveles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_niveles_reto);

        //Capturo parametros Intent
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        Toast.makeText(this, "idCategoria" + IdCategoria, Toast.LENGTH_LONG).show();


        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Layout -> Componentes Dimamicos.
        contLinearBtnsNiveles = (LinearLayout)findViewById(R.id.contLinearBtnsNiveles);

        //Botones-> Genero los Botones de los Niveles
        agregarBotones(getNiveles(), contLinearBtnsNiveles);


    }


    //Metodos interfaz

    //Este Metodo permite crear los botones de las categorias
    public void agregarBotones(Cursor datosCursor, LinearLayout contenedor ){

        if ( datosCursor.moveToFirst() ) {//Muestra los valores encontrados en la consulta

            String nombreLogo = "";

            do{
                //Instancio el Imagen View y Texto
                ImageView botonNiveles = new ImageView( getApplicationContext() );
                TextView textNiveles = new TextView ( getApplicationContext() );

                //Text View -> Anexo nombre de la categoria
                textNiveles.setText( datosCursor.getString(1) );

                // Forma para anexar un evento al ImageView
                botonNiveles.setOnClickListener(interfazEstdudio);
                // Atributo "setImageResource" Anexar Imagen
                nombreLogo =  datosCursor.getString(2);

                Resources res = getApplicationContext().getResources();
                int resId = res.getIdentifier(nombreLogo, "drawable", "com.example.apphistoriamexico");
                botonNiveles.setImageResource(resId);

                // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
                LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
                botonNiveles.setLayoutParams(paramImageViewBoton);

                LinearLayout.LayoutParams  paramTextViewBoton = new LinearLayout.LayoutParams(300, 150);
                textNiveles.setLayoutParams(paramTextViewBoton);

                //Defino la Id al Boton
                botonNiveles.setId( datosCursor.getInt(1) );

                // AddView -> Permite agregar los al contenedor
                contenedor.addView(botonNiveles);
                contenedor.addView(textNiveles);
                contenedor.setGravity(Gravity.CENTER);

            }while( datosCursor.moveToNext() );

        }else{
            Toast.makeText(this, "Error, Falla en la  Bases de datos en la tabla niveles . ", Toast.LENGTH_LONG).show();
        }

    }


    //OnClic -> Para el arbol de pensamientos
    private View.OnClickListener interfazEstdudio = new View.OnClickListener(){
        public void onClick(View v){
            ImageView objBoton = (ImageView) v;
            Toast.makeText(getApplicationContext(), "Se preciono el " + objBoton.getId(), Toast.LENGTH_LONG ).show();
        }
    };


    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazCategoria (View view){
        Intent interfaz = new Intent(this,MainGuia.class);
        startActivity(interfaz);
    }


    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getNiveles(){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor DatosNiveles = BasesDeDatos.rawQuery("SELECT id,  nom_nivel, icono, activo FROM t_niveles WHERE activo = 1", null);
        return DatosNiveles;
    }

}// fin de la clase