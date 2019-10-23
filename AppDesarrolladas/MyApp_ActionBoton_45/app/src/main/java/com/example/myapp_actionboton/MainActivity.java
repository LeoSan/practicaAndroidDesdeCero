package com.example.myapp_actionboton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }
    // Metodo para asignar las funcuones correspondientes.
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.btnCompartir ){
            Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
            return  true;
        } else if(id == R.id.btnBuscar){
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
            return  true;
        }

        if (id == R.id.item1 ){
            Toast.makeText(this, "Accion item 1", Toast.LENGTH_SHORT).show();
            return  true;
        }

        if (id == R.id.item2 ){
            Toast.makeText(this, "Accion item 2", Toast.LENGTH_SHORT).show();
            return  true;
        }
        return super.onOptionsItemSelected(item);// siempre debe ir la parte final del metodo
    }

}



