package com.example.myappselect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner inpSelect;
    private EditText inpValor1, inpValor2;
    private TextView respText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Todo Permite la comunicaciòn con la parte grafica
        inpValor1 = (EditText)findViewById(R.id.inpValor1);
        inpValor2 = (EditText)findViewById(R.id.inpValor2);
        respText = (TextView)findViewById(R.id.respValor);
        inpSelect = (Spinner)findViewById(R.id.inpSelect);

        // Todo aqui se muestra como crear un componente tipo Select
        //Arreglo con las  opciones
        String [] opciones = {"Sumar", "Restar", "Multiplicar", "Dividir"};
        //Debemos declarar un adaptare para poder comunicar el arreglo con el  componente
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.spinner_item_view, opciones);
        // Aqui agrego el objeto tipo adapte al componente
        inpSelect.setAdapter(adapter);

    }

    // Creamos el metodo de inicio Aqui se validan los campos y se validan los radio button
    public void procesaCalculo(View view){
        // todo lo que nos devuelve la view es texto. asi que declaro otras variables tipo String para recibir
        String strValorA = inpValor1.getText().toString();
        String strValorB = inpValor2.getText().toString();
        String seleccion = inpSelect.getSelectedItem().toString();

        // Metodo para validar Campos -- Todo - Esta Forma de llamado de funciones ->
        if ( evaluaCampos(strValorA, "Primer Campo vacio." ) == true  && evaluaCampos(strValorB, "Segundo Campo vacio." ) == true  ){
            // Metodo que valida el radio si uno de los dos es true suma o resta
            procesoEvaluacion(strValorA, strValorB, seleccion);
        }else{
            respText.setText("Debe Ingresar algun Valor.");
        }


    }

    // Metodo para Validar.
    public boolean evaluaCampos(String valor, String msj){
        //Valido los  inputs
        if (valor.length() == 0 ){
            //Muestra mensaje como un echo de PHP
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            //Setea la variables tipo TextView que definimos en el diseño
            respText.setText(msj);
            return false;
        }else{
            return true;
        }

    }

    public void procesoEvaluacion(String strValorA, String strValorB, String seleccion) {
        int inVAlorA = Integer.parseInt(strValorA);
        int inVAlorB = Integer.parseInt(strValorB);

        int intRespSuma, intRespResta ;
        String strResul = "";

        //valido para entrar al proceso
        if (strValorA.length() >  0 && strValorB.length() > 0 ) {
            if ( seleccion.equals("Sumar")  ){
                intRespResta = inVAlorA + inVAlorB;
                strResul =  String.valueOf(intRespResta);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, " La suma es  "+ strResul, Toast.LENGTH_SHORT).show();
            }

            if ( seleccion.equals("Restar")  ){
                intRespResta = inVAlorA - inVAlorB;
                strResul =  String.valueOf(intRespResta);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, " La Restar es  "+ strResul, Toast.LENGTH_SHORT).show();
            }

            if ( seleccion.equals("Multiplicar")  ){
                intRespResta = inVAlorA * inVAlorB;
                strResul =  String.valueOf(intRespResta);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, " La Multiplicar es  "+ strResul, Toast.LENGTH_SHORT).show();
            }

            if ( seleccion.equals("Dividir")  ){

                if (inVAlorB!=0){
                    intRespResta = inVAlorA / inVAlorB;
                    strResul =  String.valueOf(intRespResta);
                    //Muestra mensaje como un echo de PHP
                    Toast.makeText(this, " La Dividir es  "+ strResul, Toast.LENGTH_SHORT).show();
               }else{
                    Toast.makeText(this, " No se puede dividir entre 0  ", Toast.LENGTH_SHORT).show();
                    strResul = "No se Puede dividir entre 0";
                }
            }

            //Setea la variables tipo TextView que definimos en el diseño
            respText.setText(strResul);
        }else{
            respText.setText("Debe Ingresar algun Valor.");
        }

    }



}
