package com.example.mysharepreferenciasbuscador;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inpNombre, inpDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inpNombre = (EditText)findViewById(R.id.inpNombre);
        inpDatos = (EditText)findViewById(R.id.inpDatos);

    }

    public void guardarClic(View view){
        String nombre = inpNombre.getText().toString();
        String datos  = inpDatos.getText().toString();

        SharedPreferences ObjPreferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor ObjEditor = ObjPreferencias.edit();
        ObjEditor.putString(nombre, datos); // seteamos valores
        ObjEditor.commit(); // Guardamos

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();

    }

    public void buscarClic(View view){
        String nombre = inpNombre.getText().toString(); // recibimos lo que queremos buscar
        SharedPreferences ObjPreferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = ObjPreferencias.getString(nombre, "");

        if (datos.length() == 0){
            Toast.makeText(this, "No se encontro valor.", Toast.LENGTH_SHORT).show();
        }else{
            inpDatos.setText(datos);
        }

    }

}
