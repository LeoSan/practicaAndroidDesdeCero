package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView textResp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definiciòn de Valores y relaciòn de componentes con el entorno Grafico
        editText1 = (EditText)findViewById(R.id.inpValorA);
        editText2 = (EditText)findViewById(R.id.inpValorB);
        textResp = (TextView)findViewById(R.id.inpRespSuma);

    }

    // Este metodo realiza la suma
    public void sumarValores(View view){

        String valorA = editText1.getText().toString();
        String valorB = editText2.getText().toString();

        //Valido los  inputs
        if (valorA.length() == 0 ){

            Toast.makeText(this, "El primer valor, no puede estar vacio", Toast.LENGTH_SHORT).show();
            textResp.setText("El primer valor, no puede estar vacio");

        }

        if (valorB.length() == 0 ){
            Toast.makeText(this, "El segundo valor, no puede estar vacio", Toast.LENGTH_SHORT).show();
            textResp.setText("El segundo valor, no puede estar vacio");

        }


        if (valorA.length() >  0 && valorB.length() > 0 ){
            int numero1 = Integer.parseInt(valorA);
            int numero2 = Integer.parseInt(valorB);
            //Operacion
            int suma = numero1 + numero2;

            String result = String.valueOf(suma);
            textResp.setText(result);

        }
    }



}// fin de la clase principal
