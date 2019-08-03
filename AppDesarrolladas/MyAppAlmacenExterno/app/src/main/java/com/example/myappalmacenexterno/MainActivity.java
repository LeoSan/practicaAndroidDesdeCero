package com.example.myappalmacenexterno;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText inpBuscar, inpDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inpBuscar =  (EditText)findViewById(R.id.inpBuscar);
        inpDatos =  (EditText)findViewById(R.id.inpDatos);
    }


    public void metodoGuardar(View view){
        String nombre = inpBuscar.getText().toString();
        String contenido = inpDatos.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT ).show();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            OutputStreamWriter crearArchivo = new OutputStreamWriter( openFileOutput(nombre, Activity.MODE_PRIVATE) );

            crearArchivo.write(contenido);
            crearArchivo.flush();
            crearArchivo.close();
            Toast.makeText(this,"Guardado", Toast.LENGTH_SHORT).show();

            inpBuscar.setText(" ");
            inpDatos.setText(" ");

        }catch (IOException e){
            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
        }
    }

    public  void metodoConsultar(View view){
        String nombre = inpBuscar.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);

            //Abre el archivo
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));
            //leermos el archivo
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);

            String ln = leerArchivo.readLine();
            String contenidoCompleto = "";

            while(ln != null){
                contenidoCompleto = contenidoCompleto + ln + "\n";
                ln = leerArchivo.readLine();
            }
            leerArchivo.close();
            abrirArchivo.close();
            inpDatos.setText(contenidoCompleto);

        }catch (IOException e){
            Toast.makeText(this,"Error al leer ", Toast.LENGTH_SHORT).show();
        }
    }

}
