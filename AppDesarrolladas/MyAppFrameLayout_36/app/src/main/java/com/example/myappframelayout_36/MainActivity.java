package com.example.myappframelayout_36;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivi;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivi = (ImageView)findViewById(R.id.imageView);
        boton = (Button)findViewById(R.id.button);
    }

    public void metodoOcultar(View view){
        boton.setVisibility(View.INVISIBLE);
        ivi.setVisibility(View.VISIBLE);
    }
}
