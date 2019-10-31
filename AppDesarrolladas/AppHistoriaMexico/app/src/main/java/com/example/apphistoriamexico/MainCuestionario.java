package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCuestionario extends AppCompatActivity {

    TextView labelNivel, labelCategoria, labelPregunta, labelRespuesta, labelEnlace;
    Cursor categoriaId, nivelesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuestionario);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Relacion Interfaz controlador
        labelNivel     = (TextView)findViewById(R.id.labelNivel);
        labelCategoria = (TextView)findViewById(R.id.labelCategoria);
        labelPregunta  = (TextView)findViewById(R.id.labelPregunta);
        labelRespuesta = (TextView)findViewById(R.id.labelRespuesta);
        labelEnlace    = (TextView)findViewById(R.id.labelEnlace);



        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String iContador   = getIntent().getStringExtra("iContador");
        String IndicePreg  = getIntent().getStringExtra("IndicePreg");

        System.out.println( "  -------------------------------------  PARAMETROS DE ENTRADA ------------------------------------------------------ "  );
        System.out.println( "  IdCategoria " + IdCategoria );
        System.out.println( "  IdNivel "     + IdNivel     );
        System.out.println( "  iContador "   + iContador   );
        System.out.println( "  IndicePreg "  + IndicePreg  );


        //Elementos Dinamicos.
        setNomNiveles(IdNivel, iContador, IdCategoria);
        setNomCategoria(IdCategoria);

        if( validaPreguntaFinal(IndicePreg, IdCategoria, IdNivel) == false ){
            getPreguntasNivelCategoria(IdCategoria, IdNivel, IndicePreg);
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////EVENTOS DINAMICOS /////////////////////////////////////////////////////////////


    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
    }

    // Establecer texto en los nivles
    public void setNomNiveles (String IdNivel, String  Contador, String IdCategoria){
        nivelesId = getNivelId( IdNivel );
        if ( nivelesId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelNivel.setText( nivelesId.getString(1) + " - Preguntas : " + Contador + " / " + getPreguntasTotal(IdCategoria, IdNivel ) );
        }

    }

    // Establecer texto en los nivles
    public void setNomCategoria (String IdCategoria){
        categoriaId = getCategoriaId( IdCategoria );
        if ( categoriaId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelCategoria.setText( categoriaId.getString(1) );
        }
    }


    //Redirect-> Redirecciona a la interfaz Principal
    public void btnContinuarInterfaz(View view){

        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String contPregPut = getIntent().getStringExtra("IndicePreg");
        String iContador   = getIntent().getStringExtra("iContador");
        String IndicePreg  = getIntent().getStringExtra("IndicePreg");

        String contTotalPreg = getPreguntasTotal(IdCategoria, IdNivel );

        int nuevoIndice = Integer.parseInt(IndicePreg);;
        int nuevoIContador = Integer.parseInt(iContador);

        if ( Integer.parseInt(contPregPut) < Integer.parseInt(contTotalPreg) ){
            nuevoIndice++;    //no tocar el indice
            nuevoIContador++;

        }else{

            if ( Integer.parseInt(contPregPut) >= Integer.parseInt(contTotalPreg) ){
                System.out.println( " -------------  Limite total de las preguntas ------------------"  );
                System.out.println();

            }else{
                nuevoIndice = Integer.parseInt(contTotalPreg) - 1;
                nuevoIContador = nuevoIContador;
            }
        }

        //Metodo que me permite crear variable
        Intent interfaz = new Intent(this, MainCuestionario.class);
        interfaz.putExtra("IdCategoria", IdCategoria  );
        interfaz.putExtra("IdNivel",     IdNivel );
        interfaz.putExtra("iContador",   String.valueOf(nuevoIContador) );
        interfaz.putExtra("IndicePreg",  String.valueOf(nuevoIndice) );
        interfaz.putExtra("IdEstudio", consultarEstudioUltimaId() );
        //Activa la intent y envia el objeto con la variable.
        startActivity(interfaz);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////// CONSULTAS BD /////////////////////////////////////////////////////////////

    //Metodo  Tipo Cursor ->  Devuelve un cursos con los niveles de aprendizaje
    public Cursor getNivelId(String id){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id,  nom_nivel, icono, activo FROM t_niveles WHERE activo = 1 AND id = " + id, null);

        //consultaId.close();
        //BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Tipo Cursor ->  Devuelve un cursos con los valores de la categoria
    public Cursor getCategoriaId(String idCategoria){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 AND id =" + idCategoria, null);
        // consultaId.close();
        // BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Tipo String -> Me devulve el total de preguntas en un nivel
    public String getPreguntasTotal(String idCategoria, String idNivel ){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaIdPreguntas = BasesDeDatos.rawQuery("SELECT count(id) total FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel = " +idNivel, null);

        String idPregunta = "0";

        if (consultaIdPreguntas.moveToFirst()){
            idPregunta =  consultaIdPreguntas.getString(0);
        }

        consultaIdPreguntas.close();
        BasesDeDatos.close();
        return idPregunta;

    }


    //Metodo Tipo Arreglo ->  Devuelve el string con todas las preguntas del nivel y categoria
    public String[] getPreguntasNivelCategoria(String idCategoria, String idNivel, String IndicePreg){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, co_respuesta, co_categoria, co_nivel, pregunta, respuesta, enlace_resp  FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel =  " + idNivel , null);

        String contTotalPreg = getPreguntasTotal( idCategoria, idNivel );


        //Declaro arreglos. todo aqui debo definir el total  de indices
        Integer[ ] iPregunta = new  Integer[ Integer.parseInt(contTotalPreg) ];
        String[ ] sPreguntas = new  String[  Integer.parseInt(contTotalPreg) ];
        String[ ] sRespuesta = new  String[  Integer.parseInt(contTotalPreg) ];
        String[ ] sEnlace = new  String[  Integer.parseInt(contTotalPreg) ];
        String[ ] sComplemento = new  String[3];

        try {

            if (consultaId != null) {
                consultaId.moveToFirst();
                int indice = 0;

                do {
                    // todo Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    iPregunta[indice]  = consultaId.getInt(consultaId.getColumnIndex("id"));
                    sPreguntas[indice] = consultaId.getString(consultaId.getColumnIndex("pregunta"));
                    sRespuesta[indice] = consultaId.getString(consultaId.getColumnIndex("respuesta"));
                    sEnlace[indice]    = consultaId.getString(consultaId.getColumnIndex("enlace_resp"));
                    indice++;


                } while ( consultaId.moveToNext() );

                //Todo -> Aqui PRegunta // Establecer Pregunta y Respuestas Dinamico todo
                labelPregunta.setText(   sPreguntas[ Integer.parseInt( IndicePreg ) ] );
                labelRespuesta.setText(  sRespuesta[ Integer.parseInt( IndicePreg ) ] );
                labelEnlace.setText(     sEnlace[ Integer.parseInt( IndicePreg ) ] );

                int numero = (int)( Math.random()*4+1 );

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

        return sPreguntas;
    }

    private boolean validaPreguntaFinal(String indicePreg, String IdCategoria, String IdNivel) {

        String contTotalPreg = getPreguntasTotal(IdCategoria, IdNivel );
        Boolean UltimaPregunta = false;

        if ( Integer.parseInt(indicePreg) >= Integer.parseInt(contTotalPreg) ){
            //Aqui redirecciono al activity final  luego de repasar todas las preguntas
            Intent interfaz = new Intent(this,MainCuestionarioResul.class);
            interfaz.putExtra("IdCategoria", IdCategoria  );
            interfaz.putExtra("IdNivel"    , IdNivel );
            interfaz.putExtra("IdEstudio"  , consultarEstudioUltimaId() );
            //Activa la intent y envia el objeto con la variable.
            startActivity(interfaz);
            UltimaPregunta = true;

        }else if ( Integer.parseInt(indicePreg) == 0){
            // Inserto los datos de estudio
            insertEstudio();
            System.out.println( " -------------  Insert ------------------");
            System.out.println( "  IdEstudio " + consultarEstudioUltimaId() );
            System.out.println();
        }

        return  UltimaPregunta;

    }

    //Metodo  Void->  Solo inserta una sola vez el estudio
    public void insertEstudio(){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();

        //Metodo apra almacenar fecha en SQLite
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        ContentValues registro = new ContentValues();   // Instanciamos el objeto contenedor de valores.
        registro.put("fecha", date);                    // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)
        registro.put("co_actividad", "2");              // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)

        //Conectamos con la base datos insertamos.
        BasesDeDatos.insert("t_estudios", null, registro);
        BasesDeDatos.close();

    }

    //Metodo  String ->  Devuelve la ultima id registrada en la tabla t:estudios
    public String consultarEstudioUltimaId(){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        //Consulta El valor t_estudio
        Cursor consultaIdPreguntas = BasesDeDatos.rawQuery("SELECT id FROM t_estudios ORDER BY id DESC LIMIT 1", null);

        String idPregunta = "0";

        if (consultaIdPreguntas.moveToFirst()){
            idPregunta =  consultaIdPreguntas.getString(0);
        }

        consultaIdPreguntas.close();
        BasesDeDatos.close();

        return idPregunta;

    }

}// fin de la clase
