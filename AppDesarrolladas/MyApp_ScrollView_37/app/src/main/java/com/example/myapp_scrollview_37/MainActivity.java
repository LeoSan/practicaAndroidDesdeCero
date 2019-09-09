package com.example.myapp_scrollview_37;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Este metodo permita saber que estas seleccionan en la imagen
    public void metodoSelccion(View view){
        switch (view.getId()){
            case R.id.imgLimon:
                Toast.makeText(this, "Estas Seleccionando un limon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgDragon:
                Toast.makeText(this, "Estas Seleccionando un lichi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgKiwi:
                Toast.makeText(this, "Estas Seleccionando un Kiwi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgLechoza:
                Toast.makeText(this, "Estas Seleccionando una LECHOSA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgNaranja:
                Toast.makeText(this, "Estas Seleccionando una Naranja", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgNose:
                Toast.makeText(this, "Estas Seleccionando un nI Idea cual es", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgAjo:
                Toast.makeText(this, "Estas Seleccionando un Mamon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgGranada:
                Toast.makeText(this, "Estas Seleccionando una Granada", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgMango:
                Toast.makeText(this, "Estas Seleccionando un Mango", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgPlatano:
                Toast.makeText(this, "Estas Seleccionando un Platano", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgManzanar:
                Toast.makeText(this, "Estas Seleccionando una Manzana", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgManzanav:
                Toast.makeText(this, "Estas Seleccionando una Manzana Verde", Toast.LENGTH_SHORT).show();
                break;

        }


    }
}
