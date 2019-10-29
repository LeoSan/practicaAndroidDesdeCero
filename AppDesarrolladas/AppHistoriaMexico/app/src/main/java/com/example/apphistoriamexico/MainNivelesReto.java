package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainNivelesReto extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contLinearBtnsNiveles;
    TextView labelTituloNiveles, labelTipoCategoria;
    Cursor categoriaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_niveles_reto);

        //Comunicación con la interfaz
        labelTituloNiveles = (TextView)findViewById(R.id.labelTituloNiveles);
        labelTipoCategoria = (TextView)findViewById(R.id.labelNivel);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Layout -> Componentes Dimamicos.
        contLinearBtnsNiveles = (LinearLayout)findViewById(R.id.contLinearBtnsNiveles);

        //Botones-> Genero los Botones de los Niveles
        agregarBotones( getNiveles(), contLinearBtnsNiveles );

        // Titulo Dinamico
        //Capturo parametros Intent
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdActividad = getIntent().getStringExtra("IdActividad");

        System.out.println( " -------------  PARAMETROS DE ENTRADA   ------------------"  );
        System.out.println(  " IdCategoria ->" + IdCategoria );
        System.out.println(  " IdActividad ->" + IdActividad );

        categoriaId = getCategoriaId( IdCategoria );
        if ( categoriaId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelTipoCategoria.setText( categoriaId.getString(1) );
        }

    }//Final del onCreate


    //Metodos interfaz

    //Este Metodo permite crear los botones de las categorias
    public void agregarBotones(Cursor datosCursor, LinearLayout contenedor ){

        if ( datosCursor.moveToFirst() ) {//Muestra los valores encontrados en la consulta

            String nombreLogo = "";

            do{
                //Instancio el Imagen View y Texto
                ImageView botonNiveles = new ImageView( getApplicationContext() );
                TextView textNiveles = new TextView ( getApplicationContext() );

                //Text View -> Anexo nombre de la categoria
                textNiveles.setText( datosCursor.getString(1) );

                // Forma para anexar un evento al ImageView
                botonNiveles.setOnClickListener(interfazEstdudio);
                // Atributo "setImageResource" Anexar Imagen
                nombreLogo =  datosCursor.getString(2);

                Resources res = getApplicationContext().getResources();
                int resId = res.getIdentifier(nombreLogo, "drawable", "com.example.apphistoriamexico");
                botonNiveles.setImageResource(resId);

                // Atributo Layout Permite definir el tamaño a los botones Tipo Image View
                LinearLayout.LayoutParams  paramImageViewBoton = new LinearLayout.LayoutParams(300, 300);
                botonNiveles.setLayoutParams(paramImageViewBoton);

                LinearLayout.LayoutParams  paramTextViewBoton = new LinearLayout.LayoutParams(300, 150);
                paramTextViewBoton.setMargins(0,0, 0, 40);
                textNiveles.setLayoutParams(paramTextViewBoton);

                //Defino la Id al Boton
                botonNiveles.setId( datosCursor.getInt(0) );

                // AddView -> Permite agregar los al contenedor
                contenedor.addView(botonNiveles);
                contenedor.addView(textNiveles);
                contenedor.setGravity(Gravity.CENTER);

            }while( datosCursor.moveToNext() );

        }else{
            Toast.makeText(this, "Error, Falla en la  Bases de datos en la tabla niveles . ", Toast.LENGTH_LONG).show();
        }

    }


    //OnClic -> Para el arbol de pensamientos
    private View.OnClickListener interfazEstdudio = new View.OnClickListener(){
        public void onClick(View v){
            ImageView objBoton = (ImageView) v;
            String  IdActividad  = getIntent().getStringExtra("IdActividad");

            //Intancio el Objeto Intent que necesito enviar la información
            if( IdActividad.equals("1")  ){ // Redirecciono a la interfaz reto al saber
                Intent enviar = new Intent( v.getContext(), MainRetoSaber.class );
                //Debo realizar un parse de entero a string
                String idNivel = Integer.toString( objBoton.getId() );
                //Metodo que me permite crear variable
                enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
                enviar.putExtra("IdNivel",     idNivel  );
                // todo Debe de pensar una forma de recibir variables actualizadas y enviarlas
                enviar.putExtra("iContador",  "1"  );
                enviar.putExtra("IndicePreg", "0"  );

                //Activa la intent y envia el objeto con la variable.
                startActivity(enviar);

            }else{ // Redirecciono a la interfaz Cuestionario

                Intent enviar = new Intent( v.getContext(), MainCuestionario.class );
                //Debo realizar un parse de entero a string
                String idNivel = Integer.toString( objBoton.getId() );
                //Metodo que me permite crear variable
                enviar.putExtra("IdCategoria", getIntent().getStringExtra("IdCategoria")  );
                enviar.putExtra("IdNivel",     idNivel  );
                // todo Debe de pensar una forma de recibir variables actualizadas y enviarlas
                enviar.putExtra("iContador",  "1"  );
                enviar.putExtra("IndicePreg", "0"  );

                //Activa la intent y envia el objeto con la variable.
                startActivity(enviar);
            }



        }
    };


    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazCategoria (View view){
        Intent interfaz = new Intent(this,MainGuia.class);
        startActivity(interfaz);
    }


    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getNiveles(){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor DatosNiveles = BasesDeDatos.rawQuery("SELECT id,  nom_nivel, icono, activo FROM t_niveles WHERE activo = 1", null);
        return DatosNiveles;
    }

    //Metodo Devuelve un cursos con los valores de la categoria
    public Cursor getCategoriaId( String idCategoria){
        //Consulta Los niveles de aprendizaje
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor categoriaId = BasesDeDatos.rawQuery("SELECT id, nom_categoria, desp_categoria FROM t_categoria WHERE activo = 1 AND id =" + idCategoria, null);
        return categoriaId;
    }



}// fin de la clase
