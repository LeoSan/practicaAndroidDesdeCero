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

    //Item de Nivel Arte
    TextView inpArteAprobadas, inpArtePreguntas, inpArteReprobadas;

    //Item de Nivel Deporte
    TextView inpDeporteAprobadas, inpDeportePreguntas, inpDeporteReprobadas;

    //Item de Nivel Prehispanico
    TextView inpPrehispanicoAprobadas, inpPrehispanicoPreguntas, inpPrehispanicoReprobadas;

    //Item de Nivel NuevaEspania
    TextView inpNuevaEspaniaAprobadas, inpNuevaEspaniaPreguntas, inpNuevaEspaniaReprobadas;

    //Item de Nivel Independencia
    TextView inpIndenpendenciaAprobadas, inpIndenpendenciaPreguntas, inpIndenpendenciaReprobadas;

    //Item de Nivel Revolucion
    TextView inpRevolucionAprobadas, inpRevolucionPreguntas, inpRevolucionReprobadas;

    //Item de Nivel Contemporanea
    TextView inpContemporaneaAprobadas, inpContemporaneaPreguntas, inpContemporaneaReprobadas;

    //Item de Nivel Geograficos
    TextView inpGeograficasAprobadas, inpGeograficasPreguntas, inpGeograficasReprobadas;

    //Item de Nivel Legislación
    TextView inpLegislacionAprobadas, inpLegislacionPreguntas, inpLegislacionReprobadas;

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

        //Item Categoria Arte
        inpArteAprobadas  = (TextView)findViewById(R.id.inpTotalArteBuenas);
        inpArtePreguntas  = (TextView)findViewById(R.id.inpTotalArteVistas);
        inpArteReprobadas = (TextView)findViewById(R.id.inpTotalArteMalas);

        toAprobadas  = consultarTotalPreguntasEstaditicas("4", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("4", 0);
        toVistas    = getPreguntasTotal("4");

        inpArteAprobadas.setText( String.valueOf(toAprobadas) );
        inpArteReprobadas.setText( String.valueOf(toReprobadas) );
        inpArtePreguntas.setText( toVistas );

        //Item Categoria Deporte
        inpDeporteAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasDeporte);
        inpDeportePreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasDeporte);
        inpDeporteReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasDeporte);

        toAprobadas  = consultarTotalPreguntasEstaditicas("5", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("5", 0);
        toVistas    = getPreguntasTotal("5");

        inpDeporteAprobadas.setText( String.valueOf(toAprobadas) );
        inpDeporteReprobadas.setText( String.valueOf(toReprobadas) );
        inpDeportePreguntas.setText( toVistas );

        //Item Categoria Prehispanico
        inpPrehispanicoAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasPrehispanica);
        inpPrehispanicoPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasPrehispanica);
        inpPrehispanicoReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasPrehispanica);

        toAprobadas  = consultarTotalPreguntasEstaditicas("6", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("6", 0);
        toVistas    = getPreguntasTotal("6");

        inpPrehispanicoAprobadas.setText( String.valueOf(toAprobadas) );
        inpPrehispanicoReprobadas.setText( String.valueOf(toReprobadas) );
        inpPrehispanicoPreguntas.setText( toVistas );

        //Item Categoria Prehispanico
        inpNuevaEspaniaAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasNuevaEspania);
        inpNuevaEspaniaPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasNuevaEspania);
        inpNuevaEspaniaReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasNuevaEspania);

        toAprobadas  = consultarTotalPreguntasEstaditicas("7", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("7", 0);
        toVistas    = getPreguntasTotal("7");

        inpNuevaEspaniaAprobadas.setText( String.valueOf(toAprobadas) );
        inpNuevaEspaniaReprobadas.setText( String.valueOf(toReprobadas) );
        inpNuevaEspaniaPreguntas.setText( toVistas );


        //Item Categoria Independencia
        inpIndenpendenciaAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasIndependencia);
        inpIndenpendenciaPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasIndependencia);
        inpIndenpendenciaReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasIndependencia);

        toAprobadas  = consultarTotalPreguntasEstaditicas("8", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("8", 0);
        toVistas    = getPreguntasTotal("8");

        inpIndenpendenciaAprobadas.setText( String.valueOf(toAprobadas) );
        inpIndenpendenciaReprobadas.setText( String.valueOf(toReprobadas) );
        inpIndenpendenciaPreguntas.setText( toVistas );

        //Item Categoria Independencia
        inpRevolucionAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasRevolucion);
        inpRevolucionPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasRevolucion);
        inpRevolucionReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasRevolucion);

        toAprobadas  = consultarTotalPreguntasEstaditicas("9", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("9", 0);
        toVistas    = getPreguntasTotal("9");

        inpRevolucionAprobadas.setText( String.valueOf(toAprobadas) );
        inpRevolucionReprobadas.setText( String.valueOf(toReprobadas) );
        inpRevolucionPreguntas.setText( toVistas );

        //Item Categoria Independencia
        inpIndenpendenciaAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasIndependencia);
        inpIndenpendenciaPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasIndependencia);
        inpIndenpendenciaReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasIndependencia);

        toAprobadas  = consultarTotalPreguntasEstaditicas("10", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("10", 0);
        toVistas    = getPreguntasTotal("10");

        inpIndenpendenciaAprobadas.setText( String.valueOf(toAprobadas) );
        inpIndenpendenciaReprobadas.setText( String.valueOf(toReprobadas) );
        inpIndenpendenciaPreguntas.setText( toVistas );

        //Item Categoria Datos Geograficos
        inpGeograficasAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasGeografia);
        inpGeograficasPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasGeografia);
        inpGeograficasReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasGeografia);

        toAprobadas  = consultarTotalPreguntasEstaditicas("11", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("11", 0);
        toVistas    = getPreguntasTotal("11");

        inpGeograficasAprobadas.setText( String.valueOf(toAprobadas) );
        inpGeograficasReprobadas.setText( String.valueOf(toReprobadas) );
        inpGeograficasPreguntas.setText( toVistas );

        //Item Categoria Datos Legislacion
        inpLegislacionAprobadas  = (TextView)findViewById(R.id.inpTotalLeyendaBuenasLegislacion);
        inpLegislacionPreguntas  = (TextView)findViewById(R.id.inpTotalLeyendaVistasLegislacion);
        inpLegislacionReprobadas = (TextView)findViewById(R.id.inpTotalLeyendaMalasLegislacion);

        toAprobadas  = consultarTotalPreguntasEstaditicas("12", 1);
        toReprobadas = consultarTotalPreguntasEstaditicas("12", 0);
        toVistas    = getPreguntasTotal("12");

        inpLegislacionAprobadas.setText( String.valueOf(toAprobadas) );
        inpLegislacionReprobadas.setText( String.valueOf(toReprobadas) );
        inpLegislacionPreguntas.setText( toVistas );


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
