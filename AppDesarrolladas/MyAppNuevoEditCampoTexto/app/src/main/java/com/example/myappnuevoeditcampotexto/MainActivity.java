package com.example.myappnuevoeditcampotexto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etn, etp;
    private TextView respText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etn = (EditText)findViewById(R.id.inpNombre);
        etp = (EditText)findViewById(R.id.inpClave);
        respText = (TextView)findViewById(R.id.textView2);
    }

    public void Registrar(View view){
        String nombre = etn.getText().toString();
        String clave = etp.getText().toString();

        Validar(nombre, clave);

    }


    public void Validar(String valNombre, String valClave){

        if (valNombre.length() == 0 ){
            Toast.makeText(this, "Debes Ingresar un nombre", Toast.LENGTH_LONG).show();
            respText.setText("Debes Ingresar un nombre");


        }

        if (valClave.length() == 0 ){
            Toast.makeText(this, "Debes Ingresar una clave", Toast.LENGTH_LONG).show();
            respText.setText("Debes Ingresar una clave");

        }

        if (valNombre.length() != 0 && valClave.length() != 0  ){
            Toast.makeText(this, "Registro en proceso...", Toast.LENGTH_LONG).show();
            respText.setText("Registro en proceso...");

        }

    }
}
