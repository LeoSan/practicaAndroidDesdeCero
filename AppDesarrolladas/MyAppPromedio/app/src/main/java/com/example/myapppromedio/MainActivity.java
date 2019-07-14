package com.example.myapppromedio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText EditNotaMate, EditNotaFisica, EditNotaLeng;
    private TextView TextResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Aqui practicamente obtenemos con los componentes del Layout de nuestro diseño R (va ser su nombre )
        setContentView(R.layout.activity_main);

        //Definimoslos valores que vamos a recibir del Layout R
        EditNotaMate   = (EditText)findViewById(R.id.inpMatematicas);
        EditNotaFisica = (EditText)findViewById(R.id.inpFisica);
        EditNotaLeng   = (EditText)findViewById(R.id.inpLeng);
        TextResp       = (TextView)findViewById(R.id.inpLabelResp);

    }

    // Creamos el metodo a evaluar notas
    public void evaluaNotas(View view){
        // todo lo que nos devuelve la view es texto. asi que declaro otras variables tipo String para recibir
        String viewNota1 = EditNotaMate.getText().toString();
        String viewNota2 = EditNotaFisica.getText().toString();
        String viewNota3 = EditNotaLeng.getText().toString();

        // Metodo para validar Campos -- Todo Esta Forma de llamado de funciones ->
        evaluaCampos(viewNota1, "Campo vacio Mate." );
        evaluaCampos(viewNota2, "Campo vacio Fisica." );
        evaluaCampos(viewNota3, "Campo Vacio Lenguaje." );

        // Metodo que calcula promedio y evalua -- Todo Esta Forma de llamado de funciones ->
        procesoEvalua(viewNota1, viewNota2, viewNota3);

    }

    // Metodo para Validar.
    public void evaluaCampos(String valor, String msj){
        //Valido los  inputs
        if (valor.length() == 0 ){
            //Muestra mensaje como un echo de PHP
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            //Setea la variables tipo TextView que definimos en el diseño
            TextResp.setText(msj);
        }
    }

    // Metodo para Validar.
    public void procesoEvalua(String viewNota1, String viewNota2, String viewNota3){
        //valido para entrar al proceso
        if (viewNota1.length() >  0 && viewNota2.length() > 0 && viewNota3.length() > 0 ){
            //como es un calculo debemos pasar los valores a enteros
            int inNotaMate = Integer.parseInt(viewNota1);
            int inNotaFisica = Integer.parseInt(viewNota2);
            int inNotaLenguaje = Integer.parseInt(viewNota3);

            int promedio = (inNotaMate + inNotaFisica + inNotaLenguaje) / 3;

            if (promedio >= 5){

                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Este Alumno Aprobo", Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                TextResp.setText("Este Alumno Aprobo");

            }else{

                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Este Alumno Reprobo", Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                TextResp.setText("Este Alumno Reprobo");

            }

        }

    }// fin del procesoEvalua



}//Fin de la clase
