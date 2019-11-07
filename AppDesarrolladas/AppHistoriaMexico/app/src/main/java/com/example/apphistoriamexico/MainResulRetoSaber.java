package com.example.apphistoriamexico;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainResulRetoSaber extends AppCompatActivity {

    Button btnResultado;
    TextView idTextResul;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resul_reto_saber);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Recibo parametros
        String IdCategoria = getIntent().getStringExtra("IdCategoria");
        String IdNivel     = getIntent().getStringExtra("IdNivel");
        String IdEstudio   = getIntent().getStringExtra("IdEstudio");

        btnResultado   = (Button)findViewById(R.id.btnResultado);
        idTextResul    = (TextView)findViewById(R.id.idTextResul);

        // todo Test
        System.out.println( "  -------------------------------------  PARAMETROS DE ENTRADA ------------------------------------------------------ "  );
        System.out.println( "  IdCategoria " + IdCategoria );
        System.out.println( "  IdNivel "     + IdNivel     );
        System.out.println( "  IdEstudio "   + IdEstudio   );

        //Eventos
        resultadoFinal( IdEstudio );

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////  BASES DE DATOS  ////////////////////////////////////////////////////////////



    //Metodo  Tipo Cursor ->  Devuelve un cursos con los niveles de aprendizaje
    public String getTotalEstadisticas(String IdEstudio, Integer validacion){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaId = BasesDeDatos.rawQuery("SELECT count(id) total FROM t_estadisticas WHERE validacion = "+validacion+" AND co_estudios = " + IdEstudio, null);
        String Total = "0";

        if (consultaId.moveToFirst()){
            Total =  consultaId.getString(0);
        }

        consultaId.close();
        BasesDeDatos.close();
        return Total;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////EEVENTOS INTERAFZ ////////////////////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  void resultadoFinal(String IdEstudio){
       // Total de buenas
        String totalBuenas = getTotalEstadisticas(IdEstudio, 1);
        // Total de Malas
        String totalMalas  =  getTotalEstadisticas(IdEstudio , 0);

        System.out.println( " -------------  Total BUENAS ------------------"  );
        System.out.println( totalBuenas );

        System.out.println( " -------------  Total MALAS   ------------------"  );
        System.out.println( totalMalas );


        if ( Integer.parseInt(totalBuenas) > Integer.parseInt(totalMalas) ){
            btnResultado.setBackgroundResource(R.drawable.respcorrecto);
            idTextResul.setText(R.string.labelTextAprobado);
            idTextResul.setTextAppearance(getApplicationContext(), R.style.labelText_aprobado);

       }else{
            btnResultado.setBackgroundResource(R.drawable.respincorrecta);
            idTextResul.setText(R.string.labelTextReprobado);
            idTextResul.setTextAppearance(getApplicationContext(), R.style.labelText_reprobado);
        }

    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }

    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }



}// fin de la clase
