package com.example.myappbd_30;

import android.content.ContentValues;
import android.database.Cursor;
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
     * Description : Metodo que permite consultar
     *
     * */

    public void metodoBuscar(View view){
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDedatos = admin.getReadableDatabase();

        String codigo = inpCodigo.getText().toString();
        Boolean valCodigo;

        valCodigo = validaCampos(codigo,"Buscar", inpResp);
        if (valCodigo == true){
            Cursor fila = BaseDedatos.rawQuery("SELECT nomArticulo, precio FROM t_articulos WHERE codigo =" + codigo, null);

            if ( fila.moveToFirst() ){//Muestra los valores encontrados en la consulta
                inpNombre.setText(fila.getString(0)); // Siempre debe ponerle cero // todo nota
                inpPrecio.setText(fila.getString(1)); // Siempre debe ponerle cero // todo nota

            }else{
                Toast.makeText(this, "No hay valores encontrados con este código ->"+codigo , Toast.LENGTH_SHORT).show();
                inpResp.setText("No hay valores encontrados");
                //limpiamos los valores de los campos
                inpCodigo.setText(" ");
                inpNombre.setText(" ");
                inpPrecio.setText(" ");

            }
        }
        BaseDedatos.close();

    }//fin del metodo Buscar


    /*
    * Description : Metodo que ermite validar un campo que no este vacio
    *
    * */
    public boolean validaCampos(String valor,String Tipo, TextView inpResp){
        boolean valida = true; //true == todo Ok, todo bien
        String mensaje = " ";

        //Configuro el tipo de mensaje
        if (Tipo == "Registrar"){ mensaje = " Para registrar un articulo, todos los campos son obligatorios"; }
        if (Tipo == "Buscar"){ mensaje = " Debe ingresar un código para buscar "; }
        if (Tipo == "Eliminar"){ mensaje = " Debe ingresar un código para Eliminar un registro"; }
        if (Tipo == "Modificar"){ mensaje = " Debe ingresar valores para modificar"; }

        //Valido si el campo esta vacio
        if ( valor.isEmpty() ){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            inpResp.setText(mensaje);
            valida = false;
        }

        return valida;
    }



    /*
     * Description : Metodo que permite eliminar
     *
     * */

    public void metodoEliminar(View view){
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDedatos = admin.getReadableDatabase();// abre  la base de datos en modo lectura y escritura

        String codigo = inpCodigo.getText().toString();
        Boolean valCodigo;

        valCodigo = validaCampos(codigo,"Eliminar", inpResp);

        if (valCodigo == true){

            int cantidad = BaseDedatos.delete("t_articulos", "codigo=" + codigo, null);

            if ( cantidad > 0 ){//si Encontro algo muestra la cantidad de elementos eliminados

                //limpiamos los valores de los campos
                inpCodigo.setText(" ");
                inpNombre.setText(" ");
                inpPrecio.setText(" ");

                inpResp.setText("Articulo Eliminado exitosamente");
            }else{
                Toast.makeText(this, "No hay valores encontrados" , Toast.LENGTH_SHORT).show();
                inpResp.setText("No hay valores encontrados");
            }
        }
        BaseDedatos.close();//  hay que cerrar cada vez que se abre

    }//fin del metodo Eliminar

    /*
     * Description : Metodo que permite eliminar
     *
     * */

    public void metodoModificar(View view){
        /* Inicio para conectar una bases de datos, Nota: debemos crear la clase AdminSqliteOpenHelper  todo */
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDedatos = admin.getReadableDatabase();// abre  la base de datos en modo lectura y escritura


        String codigo =  inpCodigo.getText().toString();
        String nombre =  inpNombre.getText().toString();
        String precio =  inpPrecio.getText().toString();

        boolean valCodigo, valNombre, valPrecio;


        valCodigo = validaCampos(codigo, "Modificar", inpResp);
        valNombre = validaCampos(nombre, "Modificar", inpResp);
        valPrecio = validaCampos(precio, "Modificar", inpResp);

        if (valCodigo == true &&  valNombre == true &&  valPrecio == true){

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nomArticulo", nombre);
            registro.put("precio", precio);

            //Metodo para actualizar recibe como parametro (NombreBd, Arreglo con los valores para modificar, condicion, null -> Obligatorio)
            int cantidad = BaseDedatos.update("t_articulos", registro,  "codigo=" + codigo, null);

            BaseDedatos.close();//  hay que cerrar cada vez que se abre
            if ( cantidad == 1 ){//si Encontro algo muestra la cantidad de elementos eliminados

                //limpiamos los valores de los campos
                inpCodigo.setText(" ");
                inpNombre.setText(" ");
                inpPrecio.setText(" ");

                Toast.makeText(this, "Artículo Modificado correctamente" , Toast.LENGTH_SHORT).show();
                inpResp.setText("Artículo Modificado correctamente");


            }else{
                Toast.makeText(this, "No se encontro valores con ese código" , Toast.LENGTH_SHORT).show();
                inpResp.setText("No se encontro valores con ese código");
            }




        }





    }//fin del metodo Eliminar

}
