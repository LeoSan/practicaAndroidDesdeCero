package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainCuestionarioResul extends AppCompatActivity {

    ProgressBar idBarraCuestionario;
    TextView labelCategoria, inpTotalBuenas, inpTotalVistas, inpTotalMalas, inpMensaje;
    Cursor categoriaId, nivelesId;
    String totalMalas, totalBuenas, TotalEstudiadas;
    Button btnBuenas, btnVistas, btnMalas, btnNivel, btnEstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuestionario_resul);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Declaro vsriables para interactividad

        labelCategoria   = (TextView)findViewById(R.id.labelCategoria);
        inpMensaje       = (TextView)findViewById(R.id.inpMensaje);
        idBarraCuestionario =  (ProgressBar)findViewById(R.id.idBarraCuestionario);

        btnBuenas  =  (Button)findViewById(R.id.btnBuenas);
        btnVistas  =  (Button)findViewById(R.id.btnVistas);
        btnMalas   =  (Button)findViewById(R.id.btnMalas);
        btnNivel   =  (Button)findViewById(R.id.btnNivel);
        btnEstatus =  (Button)findViewById(R.id.btnEstatus);

        inpTotalBuenas = (TextView)findViewById(R.id.inpTotalBuenas);
        inpTotalVistas = (TextView)findViewById(R.id.inpTotalVistas);
        inpTotalMalas  = (TextView)findViewById(R.id.inpTotalMalas);

        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");

        //Detalles dinamicos.
        setNomCategoria ( IdCategoria );
        setNivel (IdNivel);

        totalBuenas     = consultarTotalPreguntasEstaditicas(IdNivel, IdCategoria, 1);
        TotalEstudiadas = consultarTotalNivelEstaditicas(IdNivel, IdCategoria, 3);
        totalMalas      = consultarTotalPreguntasEstaditicas(IdNivel, IdCategoria, 0);

        setMsjEstatus(totalBuenas, totalMalas );
        setBarra(IdCategoria, IdNivel, totalBuenas, totalMalas );


        //Muestra estadisticas
        btnBuenas.setText( totalBuenas );
        btnVistas.setText( TotalEstudiadas );
        btnMalas.setText( totalMalas );

        inpTotalBuenas.setText(totalBuenas);
        inpTotalVistas.setText(TotalEstudiadas);
        inpTotalMalas.setText(totalMalas);

    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////EVENTOS DINAMICOS /////////////////////////////////////////////////////////////

    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }



    //Evento -> Barra de progreso
    @SuppressLint("ResourceAsColor")
    private void setBarra(String idCategoria, String idNivel, String Buenas, String Malas) {
        String contTotalPreg = getPreguntasTotal( idCategoria, idNivel );
        idBarraCuestionario.setMax(  Integer.parseInt(contTotalPreg) );

        if ( Integer.parseInt(Buenas) > Integer.parseInt(Malas) ){
            idBarraCuestionario.setProgress( 100 );
            idBarraCuestionario.getIndeterminateDrawable().setColorFilter( ContextCompat.getColor( getApplicationContext(), R.color.colorAprobada ), PorterDuff.Mode.SRC_IN);
            //
        }else{
            idBarraCuestionario.setProgress( 50 );
            idBarraCuestionario.getIndeterminateDrawable().setColorFilter( ContextCompat.getColor( getApplicationContext(), R.color.colorReprobada ), PorterDuff.Mode.SRC_IN);
        }

    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        Intent interfaz = new Intent(this,MainApoyo.class);
        startActivity(interfaz);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }

    // Establecer texto en Categoria
    public void setNomCategoria (String IdCategoria){
        categoriaId = getCategoriaId( IdCategoria );
        if ( categoriaId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            labelCategoria.setText( categoriaId.getString(1) );
            String  nombreLogo =  categoriaId.getString(2);
            Resources res = getApplicationContext().getResources();
            int resId = res.getIdentifier(nombreLogo, "drawable", "com.example.apphistoriamexico");
            btnEstatus.setBackgroundResource(resId);

        }
    }

    // Establecer texto en los nivles
    public void setNivel (String idNivel){
        nivelesId = getNivelId( idNivel );
        if ( nivelesId.moveToFirst() ){//Muestra los valores encontrados en la consulta
            String  nombreLogo =  nivelesId.getString(2);
            Resources res = getApplicationContext().getResources();
            int resId = res.getIdentifier(nombreLogo, "drawable", "com.example.apphistoriamexico");
            btnNivel.setBackgroundResource(resId);

        }
    }

    private void setMsjEstatus(String totalBuenas, String totalMalas) {
        if ( Integer.parseInt(totalBuenas) > Integer.parseInt(totalMalas) ){
            inpMensaje.setText(R.string.labelMsjResulBien);
        }else{
            inpMensaje.setText(R.string.labelMsjResulMal);
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //////////////////////////////////////////////////CONSULTA BD //////////////////////////////////////////////////////////////////

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

    //Metodo  String ->  Devuelve la ultima id registrada en la tabla t:estudios
    public String consultarTotalPreguntasEstaditicas(String idNivel, String idCategoria, Integer tipoEstadisticas){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        //Consulta El valor t_estudio
        Cursor consultaIdTotal = BasesDeDatos.rawQuery("SELECT count(id) FROM t_estadisticas WHERE validacion = "+tipoEstadisticas+" AND co_nivel = "+idNivel+" AND co_categoria = "+idCategoria, null);

        String totalIdEstadistica = "0";

        if (consultaIdTotal.moveToFirst()){
            totalIdEstadistica =  consultaIdTotal.getString(0);
        }

        consultaIdTotal.close();
        BasesDeDatos.close();

        return totalIdEstadistica;

    }

    private String consultarTotalNivelEstaditicas(String idNivel, String idCategoria, int tipoEstadisticas) {
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        //Consulta El valor t_estudio
        Cursor consultaIdTotal = BasesDeDatos.rawQuery("SELECT count(id) FROM t_estadisticas WHERE validacion = "+tipoEstadisticas+" AND co_nivel = "+idNivel+" AND co_categoria = "+idCategoria+" GROUP BY co_estudios ", null);

        String totalIdEstadistica = "0";

        if (consultaIdTotal.moveToFirst()){
            totalIdEstadistica =  consultaIdTotal.getString(0);
        }

        consultaIdTotal.close();
        BasesDeDatos.close();

        return totalIdEstadistica;
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



}// fin de la clase.
