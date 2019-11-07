package com.example.myappprojectexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre;
    private ImageView iv_personaje;
    private TextView tv_bestScore;
    private MediaPlayer mp;

    int num_aleatorio =  (int) (Math.random() * 10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Asigno la relacion logica con la grafica
        et_nombre = (EditText)findViewById(R.id.et_nombre);
        iv_personaje = (ImageView)findViewById(R.id.iv_personaje);
        tv_bestScore = (TextView)findViewById(R.id.tv_bestScore);

        //Metodos dinamicos
        pintarPersonaje();
        reproducirMusicaIntro();


    }//fin de la clase onCreate

    //Todo-> Usar boton regresar (Back)
    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }


    public void jugar(View view){
        String nombre = et_nombre.getText().toString();

        if ( nombre.length() != 0 ){
            mp.stop();
            mp.release();

            //Todo -> Permite crear una variable tipo seccion y abre otro activity
            Intent intent = new Intent(this, Main2Activity_nivel1.class);
            intent.putExtra("nomJugador", nombre );
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(this, "Debes colocar el nombre", Toast.LENGTH_SHORT).show();

            //todo -> Metodo que abre el teclaodo
            et_nombre.requestFocus();
            InputMethodManager  imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);

        }



    }


    public void reproducirMusicaIntro(){

        mp = MediaPlayer.create(this, R.raw.alphabet_song );
        mp.start();
        mp.setLooping(true);


    }


    public void pintarPersonaje(){

        //Asignar personajes
        int id;
        if(num_aleatorio == 0 || num_aleatorio == 10){
            //todo -> Forma de obtener la ruta de una imagen
            id = getResources().getIdentifier("mango", "drawable", getPackageName());
            iv_personaje.setImageResource(id);
        }else if (num_aleatorio == 1 || num_aleatorio == 9){
            //todo -> Forma de obtener la ruta de una imagen
            id = getResources().getIdentifier("fresa", "drawable", getPackageName());
            iv_personaje.setImageResource(id);
        }else if (num_aleatorio == 2 || num_aleatorio == 8){
            //todo -> Forma de obtener la ruta de una imagen
            id = getResources().getIdentifier("manzana", "drawable", getPackageName());
            iv_personaje.setImageResource(id);
        }else if (num_aleatorio == 3 || num_aleatorio == 7){
            //todo -> Forma de obtener la ruta de una imagen
            id = getResources().getIdentifier("sandia", "drawable", getPackageName());
            iv_personaje.setImageResource(id);
        }else if (num_aleatorio == 4 || num_aleatorio == 5){
            //todo -> Forma de obtener la ruta de una imagen
            id = getResources().getIdentifier("uva", "drawable", getPackageName());
            iv_personaje.setImageResource(id);
        }

    }

}//Fin de la clase
