package com.example.myappradiobox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textTitulo, respText;
    private EditText inpValor1, inpValor2;
    private CheckBox checkSuma, checkResta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo Aqui obtenemos todo lo relacionado con la vista Diseño pero este objeto lo llamamos R
        setContentView(R.layout.activity_main);

        //Definimos los valores que vamos a recibir del Layout R que es la vista diseño.
        inpValor1   = (EditText)findViewById(R.id.inpValor1);
        inpValor2   = (EditText)findViewById(R.id.inpValor2);

        checkResta  = (CheckBox)findViewById(R.id.checkBoxResta);
        checkSuma   = (CheckBox)findViewById(R.id.checkBoxSuma);

        textTitulo   = (TextView)findViewById(R.id.textTitulo);
        respText     = (TextView)findViewById(R.id.respApp);

    }

    // Creamos el metodo de inicio Aqui se validan los campos y se validan los radio button
    public void procesaCalculo(View view){
        // todo lo que nos devuelve la view es texto. asi que declaro otras variables tipo String para recibir
        String strValorA = inpValor1.getText().toString();
        String strValorB = inpValor2.getText().toString();

        // Metodo para validar Campos -- Todo - Esta Forma de llamado de funciones ->

        //evaluaCampos(strValorA, "Primer Campo vacio." );
        //evaluaCampos(strValorB, "Segundo Campo vacio." );
        //procesoEvaluacion(strValorA, strValorB, checkSuma, checkResta);

        if ( evaluaCampos(strValorA, "Primer Campo vacio." ) == true  && evaluaCampos(strValorB, "Segundo Campo vacio." ) == true  ){
            // Metodo que valida el radio si uno de los dos es true suma o resta
            procesoEvaluacion(strValorA, strValorB, checkSuma, checkResta);
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

    public void procesoEvaluacion( String strValorA, String strValorB, CheckBox radioBoxSuma, CheckBox radioBoxResta ) {
        int inVAlorA = Integer.parseInt(strValorA);
        int inVAlorB = Integer.parseInt(strValorB);

        int intRespSuma, intRespResta ;
        String strResul = "Debes escoger una opciòn.";

        //valido para entrar al proceso
        if (strValorA.length() >  0 && strValorB.length() > 0 ) {
            if ( radioBoxResta.isChecked() == true ){
                intRespResta = inVAlorA - inVAlorB;
                strResul = "La Resta es " + String.valueOf(intRespResta);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, strResul, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                //respText.setText(strResulRest);
            }

            if (radioBoxSuma.isChecked() == true ){
                intRespSuma = inVAlorA + inVAlorB;
                strResul =  strResul + " // La Suma es :" +String.valueOf(intRespSuma);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, strResul, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                //respText.setText(strResulSuma);
            }
            respText.setText(strResul);
        }else{
            respText.setText("Debe Ingresar algun Valor.");
        }

    }


}
