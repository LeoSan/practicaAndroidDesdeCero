package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainRetoSaber extends AppCompatActivity {

    TextView labelNivel, labelCategoria;
    Cursor categoriaId, nivelesId, preguntaId, listPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reto_saber);

        //Inicio relaciones con la interfaz
        labelNivel      = (TextView)findViewById(R.id.labelNivel);
        labelCategoria  = (TextView)findViewById(R.id.labelCategoria);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel = getIntent().getStringExtra("IdNivel");
        String Contador = getIntent().getStringExtra("IdContador");

        Toast.makeText(getApplicationContext(), "Nivel " + IdNivel + " | Contador -> " + Contador , Toast.LENGTH_LONG ).show();

        //Elementos Dinamicos.
        setNomNiveles(IdNivel, Contador);
        setNomCategoria(IdCategoria);
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
        //Metodo que me permite crear variable
        enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
        startActivity(interfaz);
    }


    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazNiveles (View view){
        Intent interfaz = new Intent(this,MainNivelesReto.class);
        //Intancio el Objeto Intent que necesito enviar la información
        Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
        //Metodo que me permite crear variable
        enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
        startActivity(interfaz);
    }

    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getCategoriaId(String idCategoria){

        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 AND id =" + idCategoria, null);
        //BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Devuelve un cursos con los niveles de aprendizaje
    public Cursor getNivelId(String id){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id,  nom_nivel, icono, activo FROM t_niveles WHERE activo = 1 AND id = " + id, null);
       // BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Devuelve un cursor con toda las preguntas asignadas a una categoria y aun nivel
    public Cursor getPreguntas(String idCategoria, String idNivel ){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id FROM t_preguntas WHERE co_categoria = " + idCategoria + " AND  co_nivel = " + idNivel + " AND activo = 1 ", null);
        // BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Devuelve un cursor con la Pregunta Unica
    public Cursor getPreguntaId(String id){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, co_respuesta, co_categoria, co_nivel, pregunta, respuesta FROM t_preguntas WHERE id = " + id + " AND activo = 1 ", null);
        // BasesDeDatos.close();
        return consultaId;
    }


    // Elementos Dinámicos
    // Establecer texto en los nivles
    public void setNomNiveles (String IdNivel, String  Contador){
        nivelesId = getNivelId( IdNivel );
        if ( nivelesId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelNivel.setText( nivelesId.getString(1) + " - Preguntas : "+Contador+" / 19  " );
        }

    }

    // Establecer texto en los nivles
    public void setNomCategoria (String IdCategoria){
        categoriaId = getCategoriaId( IdCategoria );
        if ( categoriaId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelCategoria.setText( categoriaId.getString(1) );
        }
    }

}// Fin de la clase
