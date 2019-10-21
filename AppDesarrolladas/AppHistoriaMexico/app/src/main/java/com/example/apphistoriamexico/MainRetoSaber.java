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
    Cursor categoriaId, nivelesId,  listPreguntas;

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
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String Contador    = getIntent().getStringExtra("IdContador");

        //Elementos Dinamicos.
        setNomNiveles(IdNivel, Contador, IdCategoria);
        setNomCategoria(IdCategoria);

        // practica
          getPreguntasNivelCategoria(IdCategoria, IdNivel);

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
        Cursor consultaIdPreguntas = BasesDeDatos.rawQuery("SELECT count(id) total FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel = " +idNivel, null);
        //
        // BasesDeDatos.close();
        return consultaIdPreguntas;
    }

    //Metodo Devuelve un cursor con la Pregunta Unica
    public Cursor getPreguntasNivelCategoria(String idCategoria, String idNivel){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, co_respuesta, co_categoria, co_nivel, pregunta, respuesta FROM t_preguntas WHERE co_categoria = 1 AND co_nivel =  1" , null);
        // BasesDeDatos.close();

        try {

            if (consultaId != null) {
                consultaId.moveToFirst();
                do {
                    //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    String idPregunta = consultaId.getString(consultaId.getColumnIndex("id"));
                    String sPregunta = consultaId.getString(consultaId.getColumnIndex("pregunta"));


                       System.out.println( "ID = " + consultaId.getString(0) );
                       System.out.println( "pregunta = " + consultaId.getString(consultaId.getColumnIndex("pregunta")) );
                       System.out.println();

                } while (consultaId.moveToNext());
            }else{
                System.out.println( " -------------  No hay datos ------------------"  );
                System.out.println();
            }

        } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
        }

        //Cerramos el cursor y la conexion con la base de datos
        consultaId.close();
        BasesDeDatos.close();

        return consultaId;
    }


    // Elementos Dinámicos  ////////////////////////////////////////////////////////////////////////


    // Establecer texto en los nivles
    public void setNomNiveles (String IdNivel, String  Contador, String IdCategoria){

        nivelesId = getNivelId( IdNivel );
        Cursor  preguntaId =  getPreguntas(IdCategoria, IdNivel );

        if (preguntaId.moveToFirst()){
            System.out.println( "El total de registros es : " + preguntaId.getString(0));
        }

        if ( nivelesId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelNivel.setText( nivelesId.getString(1) + " - Preguntas : " + Contador + " /   " + preguntaId.getString(0) );
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
