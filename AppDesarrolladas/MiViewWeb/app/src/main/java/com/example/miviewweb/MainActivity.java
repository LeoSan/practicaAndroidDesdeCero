package com.example.miviewweb;

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
        inpNombre = (EditText)findViewById(R.id.inpNombre);
    }

    public void Navegar(View view){
        Intent i = new Intent(this, MainWeb.class);
        i.putExtra("sitioWeb", inpNombre.getText().toString());
        startActivity(i);

    }

}
