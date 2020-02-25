package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainEstadisticaConfiguracion extends AppCompatActivity {

    //Item de Nivel Curiosidad
    TextView inpCuriosidadesAprobadas, inpCuriosidadesPreguntas, inpCuriosidadesReprobadas;

    //Item de Nivel Comida
    TextView inpComidaAprobadas, inpComidaPreguntas, inpComidaReprobadas;

    //Item de Nivel Cine
    TextView inpCineAprobadas, inpCinePreguntas, inpCineReprobadas;

    Integer toAprobadas, toReprobadas;
    String toVistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estadistica_configuracion);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        /*Enlace con la interfaz*/
        //Item Categoria Curiosidad
            inpCuriosidadesAprobadas  = (TextView)findViewById(R.id.inpCuriosidadesAprobadas);
            inpCuriosidadesPreguntas  = (TextView)findViewById(R.id.inpCuriosidadesPreguntas);
            inpCuriosidadesReprobadas = (TextView)findViewById(R.id.inpCuriosidadesReprobadas);

            toAprobadas  = consultarTotalPreguntasEstaditicas("1", 1);
            toReprobadas = consultarTotalPreguntasEstaditicas("1", 0);
            toVistas     = getPreguntasTotal("1");

            inpCuriosidadesAprobadas.setText( String.valueOf(toAprobadas) );
            inpCuriosidadesReprobadas.setText( String.valueOf(toReprobadas) );
            inpCuriosidadesPreguntas.setText( toVistas );

        //Item Categoria Comida
            inpComidaAprobadas  = (TextView)findViewById(R.id.inpComidaAprobadas);
            inpComidaPreguntas  = (TextView)findViewById(R.id.inpComidaPreguntas);
            inpComidaReprobadas = (TextView)findViewById(R.id.inpComidaReprobadas);

            toAprobadas  = consultarTotalPreguntasEstaditicas("2", 1);
            toReprobadas = consultarTotalPreguntasEstaditicas("2", 0);
            toVistas     = getPreguntasTotal("2");

            inpComidaAprobadas.setText( String.valueOf(toAprobadas) );
            inpComidaReprobadas.setText( String.valueOf(toReprobadas) );
            inpComidaPreguntas.setText( toVistas );


        //Item Categoria Cine
            inpCineAprobadas  = (TextView)findViewById(R.id.inpCineAprobadas);
            inpCinePreguntas  = (TextView)findViewById(R.id.inpCinePreguntas);
            inpCineReprobadas = (TextView)findViewById(R.id.inpCineReprobadas);

            toAprobadas  = consultarTotalPreguntasEstaditicas("3", 1);
            toReprobadas = consultarTotalPreguntasEstaditicas("3", 0);
            toVistas    = getPreguntasTotal("3");

            inpCineAprobadas.setText( String.valueOf(toAprobadas) );
            inpCineReprobadas.setText( String.valueOf(toReprobadas) );
            inpCinePreguntas.setText( toVistas );

    }


    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }


    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){
        Intent siguiente = new Intent(this, MainContacto.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this, MainApoyo.class);
        startActivity(interfaz);
        finish();
    }


    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }


    // --------------------------------------------------------- CONSULTAS BASES DE DATOS  -------------------------------------------------------


    //Metodo  String ->  Devuelve la ultima id registrada en la tabla t:estudios
    public Integer consultarTotalPreguntasEstaditicas( String idCategoria, Integer tipoEstadisticas){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        //Consulta El valor t_estudio
        Cursor consultaIdTotal = BasesDeDatos.rawQuery("SELECT count(co_pregunta) FROM t_estadisticas WHERE validacion = "+tipoEstadisticas+" AND co_categoria = "+idCategoria+" GROUP BY co_pregunta ", null);

        Integer totalIdEstadistica = 0;

        if (consultaIdTotal.moveToFirst()){
            totalIdEstadistica =  consultaIdTotal.getCount();
        }

        consultaIdTotal.close();
        BasesDeDatos.close();

        return totalIdEstadistica;

    }


    //Metodo Tipo String -> Me devulve el total de preguntas en un nivel
    public String getPreguntasTotal(String idCategoria ){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaIdPreguntas = BasesDeDatos.rawQuery("SELECT count(id) total FROM t_preguntas WHERE co_categoria = "+idCategoria, null);

        String idPregunta = "0";

        if (consultaIdPreguntas.moveToFirst()){
            idPregunta =  consultaIdPreguntas.getString(0);
        }

        consultaIdPreguntas.close();
        BasesDeDatos.close();
        return idPregunta;

    }


}
