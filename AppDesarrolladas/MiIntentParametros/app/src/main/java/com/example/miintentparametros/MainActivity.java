package com.example.miintentparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText inpNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       inpNombre = (EditText)findViewById(R.id.inpNombre1);
    }

    public void Enviar(View view){
        Intent enviar = new Intent(this, Main2Activity.class);

        //Metodo que me permite enviar variables a otro Activity
        enviar.putExtra("dato", inpNombre.getText().toString());
        startActivity(enviar);
    }
}
