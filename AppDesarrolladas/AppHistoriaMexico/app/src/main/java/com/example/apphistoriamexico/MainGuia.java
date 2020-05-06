package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

//Librerias para la BD
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//Libreria para imagen dinamica
import android.content.res.Resources;

public class MainGuia extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contBtnCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guia);

        //Capturo parametros Intent
        String IdActividad = getIntent().getStringExtra("IdActividad");

        System.out.println( " -------------  TIPO DE ACTIVIDAD  - Estoy en MainGuia ------------------"  );
        System.out.println( " IdActividad ->  " + IdActividad );

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        contBtnCategorias = (LinearLayout)findViewById(R.id.contBtnCategorias);
        //Obtener los valores de categoria
        agregarBotones(getCategorias(), contBtnCategorias);
    }

    //Metodos interfaz

    //Este Metodo permite crear los botones de las categorias
    public void agregarBotones(Cursor datosCursor, LinearLayout contenedor){

        if ( datosCursor.moveToFirst() ) {//Muestra los valores encontrados en la consulta

            String nombreLogo = "";

            do{

                //Instancio el Imagen View y Texto
                ImageView botonCategoria = new ImageView( getApplicationContext() );
                TextView  textCategoria = new TextView ( getApplicationContext(), null, R.style.labelTituloCategoriaMainGuia);

               //Ajuste del Tamaño del Texto dinamico
                textCategoria.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

                //Text View -> Anexo nombre de la categoria
                textCategoria.setText( datosCursor.getString(1) );

                // Forma para anexar un evento al ImageView
                botonCategoria.setOnClickListener( interfazEstdudio );
                // Atributo "setImageResource" Anexar Imagen
                nombreLogo =  datosCursor.getString(2);
                // Forma de Agregar Icono Dinamicamente - > todo Aqui leo
                Resources res = getApplicationContext().getResources();
                int resId = res.getIdentifier(nombreLogo, "drawable", "com.example.apphistoriamexico");
                botonCategoria.setImageResource(resId);

                // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
                LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
                botonCategoria.setLayoutParams(paramImageViewBoton);

                LinearLayout.LayoutParams  paramTextViewBoton = new LinearLayout.LayoutParams(450, 170);
                paramTextViewBoton.setMargins(95,0, 0, 20);
                textCategoria.setLayoutParams(paramTextViewBoton);

                //Defino la Id al Boton
                botonCategoria.setId( datosCursor.getInt(0) );

                // AddView -> Permite agregar los al contenedor
                contenedor.addView(botonCategoria);
                contenedor.addView(textCategoria);
                contenedor.setGravity(Gravity.CENTER);

            }while( datosCursor.moveToNext() );

        }else{
            Toast.makeText(this, "Error, Falla en la  Bases de datos en la tabla categorias. ", Toast.LENGTH_LONG).show();
        }

    }

    //Eventos

    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }


    //OnClic -> Para el arbol de pensamientos
    private View.OnClickListener interfazEstdudio = new View.OnClickListener(){

        public void onClick(View view){
            //Genero el boton para poder setear sus elementos
            ImageView objBoton = (ImageView) view;
            //Intancio el Objeto Intent que necesito enviar la información
            Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
            //Debo realizar un parse de entero a string
            String cadena = Integer.toString( objBoton.getId() );
            //Metodo que me permite crear variable
            enviar.putExtra("IdCategoria", cadena  );
            enviar.putExtra("IdActividad", getIntent().getStringExtra("IdActividad")  );
            //Activa la intent y envia el objeto con la variable.
            startActivity(enviar);
        }
    };


    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }

    // Metodos Conexion Bases de Datos


    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getCategorias(){
        //Permite crear la conexion a la BD
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor DatosCategoria = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 ORDER BY id ASC", null);
        return DatosCategoria;
    }

}
