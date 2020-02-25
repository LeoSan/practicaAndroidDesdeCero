package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainEstadisticaConfiguracion extends AppCompatActivity {

    //Item de Nivel Basico
    TextView inpAprobadas, inpVistas, inpReprobadas;

    //Item de Nivel Medio
    TextView inpTotalMedioBuenas, inpTotalMedioVistas, inpTotalMedioMalas;

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
        //Item de Nivel Basico
            inpAprobadas  = (TextView)findViewById(R.id.inpTotalMedioBuenas);
            inpVistas     = (TextView)findViewById(R.id.inpTotalBasicVistas);
            inpReprobadas = (TextView)findViewById(R.id.inpTotalBasicMalas);

            toAprobadas  = consultarTotalPreguntasEstaditicas("1", 1);
            toReprobadas = consultarTotalPreguntasEstaditicas("1", 0);
            toVistas     = getPreguntasTotal("1");

            inpAprobadas.setText( String.valueOf(toAprobadas) );
            inpReprobadas.setText( String.valueOf(toReprobadas) );
            inpVistas.setText( toVistas );

        //Item de Nivel Medio
            inpTotalMedioBuenas  = (TextView)findViewById(R.id.inpTotalBasicBuenas);
            inpTotalMedioVistas  = (TextView)findViewById(R.id.inpTotalMedioVistas);
            inpTotalMedioMalas   = (TextView)findViewById(R.id.inpTotalMedioMalas);

            toAprobadas  = consultarTotalPreguntasEstaditicas("2", 1);
            toReprobadas = consultarTotalPreguntasEstaditicas("2", 0);
            toVistas     = getPreguntasTotal("2");

            inpTotalMedioBuenas.setText( String.valueOf(toAprobadas) );
            inpTotalMedioVistas.setText( String.valueOf(toReprobadas) );
            inpTotalMedioMalas.setText( toVistas );

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
