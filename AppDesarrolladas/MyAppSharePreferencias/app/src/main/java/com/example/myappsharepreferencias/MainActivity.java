package com.example.myappsharepreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inpValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inpValor = (EditText)findViewById(R.id.inpValor);

        //Metodo para guardar valores
        SharedPreferences preferences = getSharedPreferences("Datos", Context.MODE_PRIVATE );
        //Aqui coloca rl valor en el campo.
        inpValor.setText(preferences.getString("email", ""));
    }

    public void guardarClic(View view){
        SharedPreferences preferencias = getSharedPreferences("Datos", Context.MODE_PRIVATE);
        //Permite almacenar en el archivo sin esto  no guardar
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString("email", inpValor.getText().toString()); // email ->  mismo nombre recuerda que es la referencia
        obj_editor.commit();//Permite guardar
        finish();//Finaliza el activity
    }
}
