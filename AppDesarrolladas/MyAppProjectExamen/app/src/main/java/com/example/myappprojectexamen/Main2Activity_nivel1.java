package com.example.myappprojectexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Main2Activity_nivel1 extends AppCompatActivity {

    private TextView tv_nombre, tv_score;
    private ImageView iv_Auno, iv_Ados, iv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mp, mp_great, mp_bad;

    //Variables de ambito global
    int score, numAletorio_uno, numAletorio_dos, resultado, vidas = 3;
    String nombre_jugador, string_score, string_vidas;
    String numero [] = {"cero", "uno", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_nivel1);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Toast.makeText(this, "Nivel -1,  Sumas Basicas ", Toast.LENGTH_SHORT).show();

        //Relacion dinamica
        tv_nombre = (TextView)findViewById(R.id.inpNombre);
        tv_score  = (TextView)findViewById(R.id.inpScore);
        iv_vidas  = (ImageView)findViewById(R.id.img_vidas);
        iv_Auno  = (ImageView)findViewById(R.id.imagNum);
        iv_Ados  = (ImageView)findViewById(R.id.imagNum2);
        et_respuesta  = (EditText)findViewById(R.id.inpResp);

        //Metodos Dinamicos
        setNombreJugador();
        reproducirAudio();
        numAleatorio();

    }//Fin del onCreate

    public void comprobar(View view){

       String string_resp = et_respuesta.getText().toString();
        if (  string_resp.length() == 0  ){
            Toast.makeText(this, "Debes colocar una respuesta " + nombre_jugador, Toast.LENGTH_SHORT ).show();
        }else{

            System.out.println("--------------respuesta ---"+string_resp);
            System.out.println("--------------resultado ---"+resultado);

            Integer respuesta = Integer.parseInt(string_resp);
            if ( resultado == respuesta ){
                reproducirAudioBuena();
                score ++;
                tv_score.setText( "Score : " + score );
                et_respuesta.setText(" ");

            }else{
                reproducirAudioMala();
                vidas --;
                //Logica cuando te equivocas
                validaVidas(vidas);
                numAleatorio();

            }
        }

    }

    public void validaVidas(int vidas ){

        switch (vidas){
            case 3:
                iv_vidas.setImageResource(R.drawable.tresvidas);
                Toast.makeText(this, "Te queda 3 Manzanas Amigo " + nombre_jugador, Toast.LENGTH_SHORT ).show();
                break;
            case 2:
                iv_vidas.setImageResource(R.drawable.dosvidas);
                Toast.makeText(this, "Te queda 2 Manzanas Amigo " + nombre_jugador, Toast.LENGTH_SHORT ).show();
                break;
            case 1:
                iv_vidas.setImageResource(R.drawable.unavida);
                Toast.makeText(this, "Te queda 1 Manzanas Amigo " + nombre_jugador, Toast.LENGTH_SHORT ).show();
                break;
            case 0:
                Toast.makeText(this, "Perdiste todas tus manzanas Amigo " + nombre_jugador, Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                mp.release();
                break;
        }

        et_respuesta.setText(" ");

    }


    public void numAleatorio(){

        if (score <=9){

            numAletorio_uno = (int) (Math.random() * 10);
            numAletorio_dos = (int) (Math.random() * 10);

            resultado = numAletorio_uno + numAletorio_dos;

            if (resultado <= 10 ){
                for (int i =0;  i < numero.length; i++){
                    int id = getResources().getIdentifier( numero[i], "drawable", getPackageName() );

                    if (numAletorio_uno  == i){
                        iv_Auno.setImageResource(id);
                    }
                    if (numAletorio_dos  == i){
                        iv_Ados.setImageResource(id);
                    }
                }

            }else{
                numAleatorio(); //Implementamos recursividad
            }

        }else{

            Intent intent = new Intent(this, Main2Activity_nivel2.class);

            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);

            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);
            intent.putExtra("nombreJugador", nombre_jugador);
            startActivity(intent);
            finish();
            mp.stop();
            mp.release();

        }

    }//fin del metodo que dibuja y muestra las operaciones

    private void reproducirAudio() {
        mp = MediaPlayer.create(this, R.raw.goats);
        mp.start();
        mp.setLooping(true);
    }

    private void reproducirAudioBuena() {
        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_great.start();
    }

    private void reproducirAudioMala() {
        mp_bad = MediaPlayer.create(this, R.raw.bad);
        mp_bad.start();
    }

    private void setNombreJugador() {
        nombre_jugador = getIntent().getStringExtra("nomJugador");
        tv_nombre.setText( "Jugador :" + nombre_jugador  );

    }



}
