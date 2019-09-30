package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

//Librerias para la BD
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainGuia extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contBtnCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guia);

        contBtnCategorias = (LinearLayout)findViewById(R.id.contBtnCategorias);

        //Obtener los valores de categoria
        agregarBotones(getCategorias(), contBtnCategorias);

    }


    //Metodos interfaz

    //Este Metodo permite crear los botones de las categorias
    public void agregarBotones(Cursor datosCategoria, LinearLayout contenedor ){

        if ( datosCategoria.moveToFirst() ) {//Muestra los valores encontrados en la consulta

            do{

                //Instancio el boton
                Button botonCategoria = new Button( getApplicationContext() );

                //Le coloco nombre al boton
                botonCategoria.setText(datosCategoria.getString(1)); //aqui Obtenemos el nom_categoria Indice 1

                // Forma para anexar un evento al boton
                botonCategoria.setOnClickListener(interfazEstdudio);

                // Forma para anexar el boton al contenedor
                contenedor.addView(botonCategoria);

            }while( datosCategoria.moveToNext() );

        }else{
            Toast.makeText(this, "Error, Falla en la  Bases de datos en la tabla categorias. ", Toast.LENGTH_LONG).show();
        }

    }


    //Eventos

    private View.OnClickListener interfazEstdudio = new View.OnClickListener(){
        public void onClick(View v){
            Button objBoton = (Button) v;
            Toast.makeText(getApplicationContext(), "Se preciono el " + objBoton.getText(), Toast.LENGTH_LONG ).show();

        }
    };







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
