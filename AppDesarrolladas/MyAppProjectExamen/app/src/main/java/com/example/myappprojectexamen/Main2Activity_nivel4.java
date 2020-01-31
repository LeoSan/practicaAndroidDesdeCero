package com.example.myappprojectexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class Main2Activity_nivel4 extends AppCompatActivity {

    private TextView tv_nombre, tv_score;
    private ImageView iv_Auno, iv_Ados, iv_vidas, imagSigno;
    private EditText et_respuesta;
    private MediaPlayer mp, mp_great, mp_bad;

    //Variables de ambito global
    int score, numAletorio_uno, numAletorio_dos, resultado, vidas = 3;
    String nombre_jugador, string_score, string_vidas;
    String numero [] = {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_nivel4);

        Toast.makeText(this, "Nivel 4,  Sumas y Restas  ", Toast.LENGTH_SHORT).show();
        //Relacion dinamica
        tv_nombre = (TextView)findViewById(R.id.inpNombre);
        tv_score  = (TextView)findViewById(R.id.inpScore);
        iv_vidas  = (ImageView)findViewById(R.id.img_vidas);
        iv_Auno   = (ImageView)findViewById(R.id.imagNum);
        iv_Ados   = (ImageView)findViewById(R.id.imagNum2);
        et_respuesta  = (EditText)findViewById(R.id.inpResp);

        imagSigno   = (ImageView)findViewById(R.id.imagNum);




        setNombreJugador();

    }//fin del onCreate
    //Todo-> Usar boton regresar (Back)
    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }

    public void comprobar(View view){

        String string_resp = et_respuesta.getText().toString();

        if (  string_resp.length() == 0  ){

            Toast.makeText(this, "Debes colocar una respuesta " + nombre_jugador, Toast.LENGTH_SHORT ).show();

        }else{

            System.out.println("--------------respuesta ---"+ string_resp);
            System.out.println("--------------resultado ---"+ resultado);

            if ( parseInt(string_resp) == resultado ){

                System.out.println("--- TE Entro Bien  ---");
                reproducirAudioBuena();
                score ++;
                tv_score.setText( "Score : " + score );
                et_respuesta.setText("");
                getScore();
                numAleatorio();

            }else{

                System.out.println("--- TE  Entro Mal  ---");
                reproducirAudioMala();
                vidas --;
                getScore();
                //Logica cuando te equivocas
                validaVidas(vidas);
                numAleatorio();

            }
        }

    }//Fin del comprobar

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
                // mp.stop();
                //mp.release();
                break;
        }

        et_respuesta.setText(" ");

    }//fin del metodo

    public void numAleatorio(){

        if (score <=3){

            numAletorio_uno = (int) (Math.random() * 10);
            numAletorio_dos = (int) (Math.random() * 10);

            resultado = numAletorio_uno - numAletorio_dos;


            if (resultado >=  0 ){
                for (int i = 0;  i < numero.length; i++) {
                    int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());

                    if (numAletorio_uno == i) {
                        iv_Auno.setImageResource(id);
                    }
                    if (numAletorio_dos == i) {
                        iv_Ados.setImageResource(id);
                    }
                }

            }else{
                numAleatorio();
            }


        }else{

            Intent intent = new Intent(this, Main2Activity_nivel5.class);

            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);

            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);
            intent.putExtra("nombreJugador", tv_nombre.getText() );
            startActivity(intent);
            finish();
            // mp.stop();
            // mp.release();

        }

    }//fin del metodo que dibuja y muestra las operaciones


    private void reproducirAudio() {
        mp = MediaPlayer.create(this, R.raw.goats);
        mp.start();
        mp.setLooping(true);
    }

    private void reproducirAudioBuena() {
        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_great.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        mp_great.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    private void reproducirAudioMala() {
        mp_bad = MediaPlayer.create(this, R.raw.bad);

        mp_bad.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        mp_bad.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void setNombreJugador() {
        nombre_jugador = getIntent().getStringExtra("nombreJugador");
        tv_nombre.setText( "Jugador :" + nombre_jugador  );

        string_score  = getIntent().getStringExtra("score");
        tv_score.setText("Score : "  + string_score);

    }


    //Metodo  Tipo Cursor ->  Devuelve un cursos
    public void getScore(){
        //Creamos el conector de bases de datos
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "BasesDeDatos", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();
        Cursor consultaScore = BasesDeDatos.rawQuery( "SELECT * FROM t_puntaje as a WHERE a.score = ( "+string_score+"  ) ", null );

        if (consultaScore.moveToFirst()){
            String temp_nombre = consultaScore.getString(0);
            String temp_score  = consultaScore.getString(1);

            int bestScore = parseInt(temp_score);

            if (score > bestScore){
                ContentValues modificaScore = new ContentValues();
                modificaScore.put("nomJugador", nombre_jugador );
                modificaScore.put("score", score );
                BasesDeDatos.update("t_puntaje", modificaScore, "score =" + bestScore, null);
            }

        }else{
            ContentValues insertScore = new ContentValues();
            insertScore.put("nomJugador", nombre_jugador );
            insertScore.put("score", score );
            BasesDeDatos.insert("t_puntaje", null, insertScore);
        }

        consultaScore.close();
        BasesDeDatos.close();
    }

}
