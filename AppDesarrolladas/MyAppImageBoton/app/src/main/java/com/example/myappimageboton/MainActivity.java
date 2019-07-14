package com.example.myappimageboton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MensajeUno(View view){
        Toast.makeText(this, "Mensaje imagen uno", Toast.LENGTH_SHORT).show();

    }

    public void MensajeDos(View view){
        Toast.makeText(this, "Mensaje imagen Dos", Toast.LENGTH_SHORT).show();

    }
}
