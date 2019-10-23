package com.example.myappactionbar_44;

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

        if (id == R.id.itemA ){
            Toast.makeText(this, "Accion item 1", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.itemB ){
            Toast.makeText(this, "Accion item 2", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.itemC ){
            Toast.makeText(this, "Accion item 3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
