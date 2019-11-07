package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

//librerias BD
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

//Librerias Diseño
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainRetoSaber extends AppCompatActivity {

    TextView labelNivel, labelCategoria, labelPregunta, idRespValidar;
    Cursor categoriaId, nivelesId;
    Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, btnCalificar, btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reto_saber);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Inicio relaciones con la interfaz
        labelNivel      = (TextView)findViewById(R.id.labelNivel);
        labelCategoria  = (TextView)findViewById(R.id.labelCategoria);
        labelPregunta   = (TextView)findViewById(R.id.labelPregunta);
        idRespValidar   = (TextView)findViewById(R.id.idRespValidar);

        //botones opciones
        btnOpcion1  = (Button)findViewById(R.id.btnOpcion1);
        btnOpcion2  = (Button)findViewById(R.id.btnOpcion2);
        btnOpcion3  = (Button)findViewById(R.id.btnOpcion3);
        btnOpcion4  = (Button)findViewById(R.id.btnOpcion4);
        btnCalificar = (Button)findViewById(R.id.btnCalificar);
        btnContinuar = (Button)findViewById(R.id.btnContinuar);

        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String iContador   = getIntent().getStringExtra("iContador");
        String IndicePreg  = getIntent().getStringExtra("IndicePreg");

        //Elementos Dinamicos.
        setNomNiveles(IdNivel, iContador, IdCategoria);
        setNomCategoria(IdCategoria);

        System.out.println( "  -------------------------------------  PARAMETROS DE ENTRADA ------------------------------------------------------ "  );
        System.out.println( "  IdCategoria " + IdCategoria );
        System.out.println( "  IdNivel "     + IdNivel     );
        System.out.println( "  iContador "   + iContador   );
        System.out.println( "  IndicePreg "  + IndicePreg  );

        if( validaPreguntaFinal(IndicePreg, IdCategoria, IdNivel) == false ){
            getPreguntasNivelCategoria(IdCategoria, IdNivel, IndicePreg);
        }

    }// fin del onCreate

    //Todo-> Usar boton regresar (Back)
    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }

    private boolean validaPreguntaFinal(String indicePreg, String IdCategoria, String IdNivel) {

        String contTotalPreg = getPreguntasTotal(IdCategoria, IdNivel );
        Boolean UltimaPregunta = false;

        if ( Integer.parseInt(indicePreg) >= Integer.parseInt(contTotalPreg) ){
            //Metodo que me permite crear variable
            Intent interfaz = new Intent(this,MainResulRetoSaber.class);
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
        registro.put("co_actividad", "1");              // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)

        //Conectamos con la base datos insertamos.
        BasesDeDatos.insert("t_estudios", null, registro);
        BasesDeDatos.close();

    }

    //Metodo  Void->  Solo inserta parte de las estadisticas
    public void insertEstadisticas(String IdPregunta, Integer validacicion, String co_nivel, String co_categoria){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();   // Instanciamos el objeto contenedor de valores.
        registro.put("co_estudios", consultarEstudioUltimaId() );
        registro.put("co_pregunta", IdPregunta);
        registro.put("co_nivel", co_nivel);
        registro.put("co_categoria", co_categoria);
        registro.put("validacion", validacicion);

        System.out.println( " -------------  Insert RESPUESTA ------------------");
        System.out.println( "  IdEstudio " + validacicion );


        //Conectamos con la base datos insertamos.
        BasesDeDatos.insert("t_estadisticas", null, registro);
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

    //Metodo Tipo Arreglo -> Me devuelve las opciones relacionadas a una pregunta.
    public String[] getComplementoPreguntas(String idPregunta){
        //Creamos el conector de bases de datos

        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId= BasesDeDatos.rawQuery("SELECT complemento FROM t_complemento WHERE co_pregunta = " + idPregunta, null);

        String[ ] sComplemento = new  String[20];
        try {
            if (consultaId != null) {
                consultaId.moveToFirst();
                int indice = 0;

                do {
                    //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    sComplemento[indice] = consultaId.getString(consultaId.getColumnIndex("complemento"));
                    indice ++;

                } while ( consultaId.moveToNext() );


            }else{
                System.out.println( " -------------  No hay datos ------------------"  );
                System.out.println();
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        consultaId.close();
        BasesDeDatos.close();
        return  sComplemento;
    }

    //Metodo Tipo Arreglo ->  Devuelve el string con todas las preguntas del nivel y categoria
    public String[] getPreguntasNivelCategoria(String idCategoria, String idNivel, String IndicePreg){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, co_respuesta, co_categoria, co_nivel, pregunta, respuesta FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel =  " + idNivel , null);

        String contTotalPreg = getPreguntasTotal( idCategoria, idNivel );


        //Declaro arreglos. todo aqui debo definir el total  de indices
        Integer[ ] iPregunta = new  Integer[ Integer.parseInt(contTotalPreg) ];
        String[ ] sPreguntas = new  String[  Integer.parseInt(contTotalPreg) ];
        String[ ] sRespuesta = new  String[  Integer.parseInt(contTotalPreg) ];
        String[ ] sComplemento = new  String[2];

        try {

            if (consultaId != null) {
                consultaId.moveToFirst();
                int indice = 0;

                do {
                    // todo Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    iPregunta[indice] = consultaId.getInt(consultaId.getColumnIndex("id"));
                    sPreguntas[indice] = consultaId.getString(consultaId.getColumnIndex("pregunta"));
                    sRespuesta[indice] = consultaId.getString(consultaId.getColumnIndex("respuesta"));
                    indice++;


                } while ( consultaId.moveToNext() );

                //Todo -> Aqui PRegunta // Establecer Pregunta y Respuestas Dinamico todo
                labelPregunta.setText(  sPreguntas[ Integer.parseInt( IndicePreg ) ] );

                int numero = (int)( Math.random()*4+1 );

                //todo aqui las preguntas complementarias leo
               sComplemento = getComplementoPreguntas( Integer.toString( iPregunta[Integer.parseInt( IndicePreg )]  ) );

                if ( numero  == 1 ){
                    btnOpcion1.setText(  sRespuesta[ Integer.parseInt( IndicePreg ) ] );
                    btnOpcion1.setId( iPregunta[Integer.parseInt( IndicePreg )] );

                     btnOpcion2.setText(  sComplemento[0] );
                     btnOpcion2.setId( 0 );
                     btnOpcion3.setText(  sComplemento[1] );
                     btnOpcion3.setId( 0 );
                     btnOpcion4.setText(  sComplemento[2] );
                     btnOpcion4.setId( 0 );

                }

                if ( numero  == 2 ){
                    btnOpcion2.setText(  sRespuesta[ Integer.parseInt( IndicePreg ) ] );
                    btnOpcion2.setId( iPregunta[Integer.parseInt( IndicePreg )] );

                   btnOpcion1.setText(  sComplemento[0] );
                    btnOpcion1.setId( 0 );
                    btnOpcion3.setText(  sComplemento[1] );
                    btnOpcion3.setId( 0 );
                    btnOpcion4.setText(  sComplemento[2] );
                    btnOpcion4.setId( 0 );

                }

                if ( numero  == 3 ){
                    btnOpcion3.setText(  sRespuesta[ Integer.parseInt( IndicePreg ) ] );
                    btnOpcion3.setId( iPregunta[Integer.parseInt( IndicePreg )] );

                    btnOpcion1.setText(  sComplemento[0] );
                    btnOpcion1.setId( 0 );
                    btnOpcion2.setText(  sComplemento[1] );
                    btnOpcion2.setId( 0 );
                    btnOpcion4.setText(  sComplemento[2] );
                    btnOpcion4.setId( 0 );
                }

                if ( numero  == 4 ){
                    btnOpcion4.setText(  sRespuesta[ Integer.parseInt( IndicePreg ) ] );
                    btnOpcion4.setId( iPregunta[Integer.parseInt( IndicePreg )] );

                    btnOpcion1.setText(  sComplemento[0] );
                    btnOpcion1.setId( 0 );
                    btnOpcion2.setText(  sComplemento[1] );
                    btnOpcion2.setId( 0 );
                    btnOpcion3.setText(  sComplemento[2] );
                    btnOpcion3.setId( 0 );

                }

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


    ////////////////////////// ///////////////////////////////// Elementos Dinámicos  ////////////////////////////////////////////////////////////////////////
    ////////////////////////// ///////////////////////////////// Elementos Dinámicos  ////////////////////////////////////////////////////////////////////////
    ////////////////////////// ///////////////////////////////// Elementos Dinámicos  ////////////////////////////////////////////////////////////////////////

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
        //Metodo que me permite crear variable
        enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
        startActivity(interfaz);
        finish();
    }


    // Establecer texto en los nivles
    public void setNomNiveles (String IdNivel, String  Contador, String IdCategoria){
        nivelesId = getNivelId( IdNivel );
        if ( nivelesId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelNivel.setText( nivelesId.getString(1) + " - Preguntas : " + Contador + " /   " + getPreguntasTotal(IdCategoria, IdNivel ) );
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
    public void vistaInterfazNiveles (View view){
        Intent interfaz = new Intent(this,MainNivelesReto.class);
        //Intancio el Objeto Intent que necesito enviar la información
        Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
        //Metodo que me permite crear variable
        enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
        startActivity(interfaz);
        finish();
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

    //Metodo Clic Evaluar -> Todo Evaluar Respuesta Reto al saber
    public void evaluarRespuesta_1(View view){
        int valdiar = btnOpcion1.getId();
        btnOpcion1.setBackgroundColor(Color.rgb(70, 218, 100));
        idRespValidar.setText( Integer.toString( valdiar ) );

        // opciones
        btnOpcion2.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion3.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion4.setBackgroundColor(Color.rgb(208, 215, 211));
        Toast.makeText(this, "Puedes cambiar de opción y luego Clic en Calificar.", Toast.LENGTH_SHORT).show();

        // Muestro Boton de Calificar
        btnCalificar.setVisibility(View.VISIBLE);

    }

    public void evaluarRespuesta_2(View view){

        int valdiar = btnOpcion2.getId();
        btnOpcion2.setBackgroundColor(Color.rgb(70, 218, 100));
        idRespValidar.setText( Integer.toString( valdiar ) );

        // opciones
        btnOpcion1.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion3.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion4.setBackgroundColor(Color.rgb(208, 215, 211));

        // Muestro Boton de Calificar
        btnCalificar.setVisibility(View.VISIBLE);
    }

    public void evaluarRespuesta_3(View view){

        int valdiar = btnOpcion3.getId();
        btnOpcion3.setBackgroundColor(Color.rgb(70, 218, 100));
        idRespValidar.setText( Integer.toString( valdiar ) );

        // opciones
        btnOpcion1.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion2.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion4.setBackgroundColor(Color.rgb(208, 215, 211));

        // Muestro Boton de Calificar
        btnCalificar.setVisibility(View.VISIBLE);

    }

    public void evaluarRespuesta_4(View view){

        int valdiar = btnOpcion4.getId();
        btnOpcion4.setBackgroundColor(Color.rgb(70, 218, 100));
        idRespValidar.setText( Integer.toString( valdiar ) );

        // opciones
        btnOpcion1.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion2.setBackgroundColor(Color.rgb(208, 215, 211));
        btnOpcion3.setBackgroundColor(Color.rgb(208, 215, 211));

        // Muestro Boton de Calificar
        btnCalificar.setVisibility(View.VISIBLE);
    }

    public void evaluarRespuestaGeneral(View view){
        // La Respuesta siempre va ser  un numero entero y mayor a cero
        String IdResp  = (String) idRespValidar.getText();

        if ( ( Integer.parseInt( IdResp ) ) > 0 ){
            Toast.makeText(this, "Respuesta Correcta, Clic para Continuar", Toast.LENGTH_SHORT).show();
            // Transformo el boton si la respuesta es correcta
            btnCalificar.setText(" ");
            Drawable d = getResources().getDrawable(R.drawable.respcorrecto);
            btnCalificar.setBackgroundDrawable(d);
            // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
            LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
            paramImageViewBoton.setMargins(0, 50, 0, 0 );
            btnCalificar.setLayoutParams(paramImageViewBoton);

            // Muestro Boton Continuar
            btnContinuar.setVisibility(View.VISIBLE);
            // Focus
            btnContinuar.setFocusable(true);
            btnContinuar.setFocusableInTouchMode(true);///add this line
            btnContinuar.requestFocus();

            insertEstadisticas(IdResp, 1, getIntent().getStringExtra("IdNivel"), getIntent().getStringExtra("IdCategoria"));

        }else {
            Toast.makeText(this, "Respuesta InCorrecta, Clic para Continuar", Toast.LENGTH_SHORT).show();
            // Transformo el boton si la respuesta es Incorrecta
            btnCalificar.setText(" ");
            Drawable d = getResources().getDrawable(R.drawable.respincorrecta);
            btnCalificar.setBackgroundDrawable(d);
            // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
            LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
            paramImageViewBoton.setMargins(0, 50, 0, 0 );
            btnCalificar.setLayoutParams(paramImageViewBoton);
            // Muestro Boton Continuar
            btnContinuar.setVisibility(View.VISIBLE);
            // Focus
            btnContinuar.setFocusable(true);
            btnContinuar.setFocusableInTouchMode(true);///add this line
            btnContinuar.requestFocus();

            insertEstadisticas(IdResp, 0, getIntent().getStringExtra("IdNivel"), getIntent().getStringExtra("IdCategoria"));

            System.out.println( " -------------  RESPUESTA ERRADA  ------------------"  );
            System.out.println();

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
                // Toast.makeText(this, "Entro", Toast.LENGTH_SHORT).show();
                System.out.println( " -------------  Limite total de las preguntas ------------------"  );
                System.out.println();

            }else{
                nuevoIndice = Integer.parseInt(contTotalPreg) - 1;
                nuevoIContador = nuevoIContador;
            }
        }

        //Metodo que me permite crear variable
        Intent interfaz = new Intent(this,MainRetoSaber.class);
        interfaz.putExtra("IdCategoria", IdCategoria  );
        interfaz.putExtra("IdNivel",     IdNivel );
        interfaz.putExtra("iContador",   String.valueOf(nuevoIContador) );
        interfaz.putExtra("IndicePreg",  String.valueOf(nuevoIndice) );
        interfaz.putExtra("IdEstudio", consultarEstudioUltimaId() ); // todo posible error
        //Activa la intent y envia el objeto con la variable.
        startActivity(interfaz);
        finish();

    }
}// Fin de la clase