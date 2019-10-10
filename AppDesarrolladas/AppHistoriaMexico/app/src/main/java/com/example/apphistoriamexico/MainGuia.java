package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

//Librerias para la BD
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import android.view.ViewGroup;

public class MainGuia extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contBtnCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guia);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        contBtnCategorias = (LinearLayout)findViewById(R.id.contBtnCategorias);

        //Obtener los valores de categoria
        agregarBotones(getCategorias(), contBtnCategorias);

    }


    //Metodos interfaz

    //Este Metodo permite crear los botones de las categorias
    public void agregarBotones(Cursor datosCategoria, LinearLayout contenedor ){

        if ( datosCategoria.moveToFirst() ) {//Muestra los valores encontrados en la consulta

            String nombreLogo = "";

            do{

                //Instancio el Imagen View y Texto
                ImageView botonCategoria = new ImageView( getApplicationContext() );
                TextView  textCategoria = new TextView ( getApplicationContext() );

                //Text View -> Anexo nombre de la categoria
                textCategoria.setText( datosCategoria.getString(2) );

                // Forma para anexar un evento al ImageView
                botonCategoria.setOnClickListener(interfazEstdudio);
                // Atributo "setImageResource" Anexar Imagen
               // nombreLogo = "R.drawable." + datosCategoria.getString(3);
                botonCategoria.setImageResource(R.drawable.arte);

                // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
                LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
                botonCategoria.setLayoutParams(paramImageViewBoton);

                LinearLayout.LayoutParams  paramTextViewBoton = new LinearLayout.LayoutParams(300, 150);
                textCategoria.setLayoutParams(paramTextViewBoton);

                //Defino la Id al Boton
                botonCategoria.setId(datosCategoria.getInt(1));

                // AddView -> Permite agregar los compornentes al contenedor
                contenedor.addView(botonCategoria);
                contenedor.addView(textCategoria);
                contenedor.setGravity(Gravity.CENTER);

            }while( datosCategoria.moveToNext() );

        }else{
            Toast.makeText(this, "Error, Falla en la  Bases de datos en la tabla categorias. ", Toast.LENGTH_LONG).show();
        }

    }

    //Eventos

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
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
    }

    // Metodos Conexion Bases de Datos


    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getCategorias(){
        //Consulta Categoria
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor DatosCategoria = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 ORDER BY id ASC", null);
        return DatosCategoria;
    }

}
