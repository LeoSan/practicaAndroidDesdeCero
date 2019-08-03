package com.example.mybitacora;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText inpTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inpTarea = (EditText)findViewById(R.id.inpTarea);
        //Paso 1:
        // Buscar Fichero
         String arrgArchivos[] = fileList();

        //Paso 2: validamos si existe el fichero y leyemos el fichero
        if ( ArchivoExiste(arrgArchivos, "bitacora.txt") ){
            try{
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader objBuff = new BufferedReader(archivo);
                String Linea = objBuff.readLine();
                String BitacoraCompleta = "";

                while (Linea != null){
                    BitacoraCompleta = BitacoraCompleta + Linea + "\n";
                    Linea = objBuff.readLine();
                }
                objBuff.close();
                archivo.close();

                inpTarea.setText(BitacoraCompleta);
            }catch (IOException e){
                Toast.makeText(this, "Error Primer Try" + e, Toast.LENGTH_SHORT).show();
            }


        }

    }// fin del create


    // Metodo para validar el archivo
    public boolean ArchivoExiste(String arreglo[], String nomFichero){
        for (int i =0; i < arreglo.length; i++){
            if (nomFichero.equals(arreglo[i])){
                    return  true;
            };
        }
        return false;
    }

    // Metodo para el boton guardar

    public void metodoGuardar(View view){

        try{
            OutputStreamWriter objArchivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            objArchivo.write(inpTarea.getText().toString());
            objArchivo.flush();//Limpiamos al terminar de escribir
            objArchivo.close();
            Toast.makeText(this, "Bitacora guardada", Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            Toast.makeText(this, "Error Segundo Try" + e, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Bitacora guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
