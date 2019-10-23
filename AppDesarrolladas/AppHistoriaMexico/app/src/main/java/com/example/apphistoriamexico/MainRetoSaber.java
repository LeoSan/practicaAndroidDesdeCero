package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainRetoSaber extends AppCompatActivity {

    TextView labelNivel, labelCategoria, labelPregunta, idRespValidar;
    Cursor categoriaId, nivelesId,  listPreguntas;
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
        labelPregunta  = (TextView)findViewById(R.id.labelPregunta);
        idRespValidar  = (TextView)findViewById(R.id.idRespValidar);

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
        getPreguntasNivelCategoria(IdCategoria, IdNivel, IndicePreg);

    }// fin del onCreate

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        Intent enviar = new Intent( view.getContext(), MainNivelesReto.class );
        //Metodo que me permite crear variable
        enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
        startActivity(interfaz);
    }



    //Metodo  Tipo Cursor ->  Devuelve un cursos con los niveles de aprendizaje
    public Cursor getNivelId(String id){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id,  nom_nivel, icono, activo FROM t_niveles WHERE activo = 1 AND id = " + id, null);
        // consultaId.close();
        // BasesDeDatos.close();
        return consultaId;
    }

    //Metodo Tipo String -> Me devulve el total de preguntas en un nivel
    public String getPreguntasTotal(String idCategoria, String idNivel ){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaIdPreguntas = BasesDeDatos.rawQuery("SELECT count(id) total FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel = " +idNivel, null);
        if (consultaIdPreguntas.moveToFirst()){
          return  consultaIdPreguntas.getString(0);
        }else{
            return "0";
        }
        //consultaIdPreguntas.close();
        // BasesDeDatos.close();
    }

    //Metodo Tipo Arreglo -> Me devuelve las opciones relacionadas a una pregunta.
    public String[] getComplementoPreguntas(String idPregunta){
        //Consulta Los niveles de aprendizaje

        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId= BasesDeDatos.rawQuery("SELECT complemento FROM t_complemento WHERE co_pregunta = " + idPregunta, null);

        String[ ] sComplemento = new  String[3];
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
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, co_respuesta, co_categoria, co_nivel, pregunta, respuesta FROM t_preguntas WHERE co_categoria = "+idCategoria+" AND co_nivel =  " + idNivel , null);

        //Declaro arreglos. todo aqui debo definir el total  de indices
        Integer[ ] iPregunta = new  Integer[19];
        String[ ] sPreguntas = new  String[19];
        String[ ] sRespuesta = new  String[19];
        String[ ] sComplemento = new  String[3];

        try {

            if (consultaId != null) {
                consultaId.moveToFirst();
                int indice = 0;

                do {
                    // todo Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    iPregunta[indice] = consultaId.getInt(consultaId.getColumnIndex("id"));
                    sPreguntas[indice] = consultaId.getString(consultaId.getColumnIndex("pregunta"));
                    sRespuesta[indice] = consultaId.getString(consultaId.getColumnIndex("respuesta"));
                    indice ++;

                } while ( consultaId.moveToNext() );

                //Establecer Pregunta y Respuestas Dinamico todo
                labelPregunta.setText(  sPreguntas[ Integer.parseInt( IndicePreg ) ] );
                int numero = (int)( Math.random()*4+1 );

                //todo aqui las preguntas complementarias
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
                    btnOpcion4.setText(  sComplemento[3] );
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


    ///////////////////////////////// Elementos Dinámicos  ////////////////////////////////////////////////////////////////////////


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
    }

    //Metodo Tipo Cursor ->  Devuelve un cursos con los valores de la categoria
    public Cursor getCategoriaId(String idCategoria){

        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 AND id =" + idCategoria, null);
        // consultaId.close();
        //BasesDeDatos.close();
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
        if ( (Integer.parseInt( IdResp ) ) > 0 ){
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

            //Todo Crear metodo para insertar estadisticas


        }else{
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

            // Todo Crear metodo para insertar estadisticas

        }
    }


    //Redirect-> Redirecciona a la interfaz Principal
    public void btnContinuarInterfaz(View view){
        Intent interfaz = new Intent(this,MainRetoSaber.class);
        //Metodo que me permite crear variable
        interfaz.putExtra("IdCategoria", "1"  );
        interfaz.putExtra("IdNivel", "1" );
        interfaz.putExtra("iContador", "1"  );
        interfaz.putExtra("IndicePreg", "2"  );
        //Activa la intent y envia el objeto con la variable.
        startActivity(interfaz);
    }


}// Fin de la clase
