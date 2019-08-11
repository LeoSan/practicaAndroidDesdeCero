package com.example.myappbd_30;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText inpCodigo, inpNombre,inpPrecio;
    private TextView inpResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la relacion con la interfaz
        inpCodigo = (EditText)findViewById(R.id.inpCodigo);
        inpNombre = (EditText)findViewById(R.id.inpNombre);
        inpPrecio = (EditText)findViewById(R.id.inpPrecio);

        inpResp = (TextView)findViewById(R.id.textResp);


    }


    /*
     * Description : Metodo que permite registrar los valores en la bases de datos.
     *
     * */

    public void metodoRegistrar(View view){
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1 );
        // Abre la base de datos en modo lectura y escritura
        SQLiteDatabase BasesDeDatos = admin.getWritableDatabase();

        String codigo =  inpCodigo.getText().toString();
        String nombre =  inpNombre.getText().toString();
        String precio =  inpPrecio.getText().toString();
        boolean valCodigo, valNombre, valPrecio;


        valCodigo = validaCampos(codigo, "Registrar", inpResp);
        valNombre = validaCampos(nombre, "Registrar", inpResp);
        valPrecio = validaCampos(precio, "Registrar", inpResp);

        if (valCodigo == true &&  valNombre == true &&  valPrecio == true){
            ContentValues registro = new ContentValues(); // Aqui intaciamos el insert para la tabla
            registro.put("codigo", codigo);               // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)
            registro.put("nomArticulo", nombre);          // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)
            registro.put("precio", precio);               // Aqui asociamos los atributos de la tabla con los valores de los campos (Recuerda el campo de la tabla debe ser igual aqui)

            //Conectamos con la base datos insertamos.
            BasesDeDatos.insert("t_articulos", null, registro);

            BasesDeDatos.close();

            //limpiamos los valores de los campos
            inpCodigo.setText(" ");
            inpNombre.setText(" ");
            inpPrecio.setText(" ");

            inpResp.setText("Registro Exitoso!");
            Toast.makeText(this, "Registro Exitoso!", Toast.LENGTH_SHORT).show();

        }




    }//Fin del metodo registrar en tabla


    /*
    * Description : Metodo que ermite validar un campo que no este vacio
    *
    * */
    public boolean validaCampos(String valor,String Tipo, TextView inpResp){
        boolean valida = true; //true == todo Ok, todo bien
        String mensaje = " ";

        //Configuro el tipo de mensaje
        if (Tipo == "Registrar"){ mensaje = " Para registrar un articulo, todos los campos son obligatorios"; }

        //Valido si el campo esta vacio
        if ( valor.isEmpty() ){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            inpResp.setText(mensaje);
            valida = false;
        }

        return valida;
    }



}
