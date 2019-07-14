package com.example.myradiobuton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText EditViewValorA, EditViewValorB;
    private TextView TexViewResp;
    private RadioButton RadioButonSumar, RadioButonRestar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo Aqui obtenemos todo lo relacionado con la vista Diseño pero este objeto lo llamamos R
        setContentView(R.layout.activity_main);
        //Definimos los valores que vamos a recibir del Layout R que es la vista diseño.
        EditViewValorA   = (EditText)findViewById(R.id.inpValorA);
        EditViewValorB   = (EditText)findViewById(R.id.inpValorB);

        TexViewResp      = (TextView)findViewById(R.id.inpResultado);
        RadioButonSumar  = (RadioButton)findViewById(R.id.rbSumar);
        RadioButonRestar = (RadioButton)findViewById(R.id.rbRestar);

    }

    // Creamos el metodo de inicio Aqui se validan los campos y se validan los radio button
    public void procesaCalculo(View view){
        // todo lo que nos devuelve la view es texto. asi que declaro otras variables tipo String para recibir
        String strValorA = EditViewValorA.getText().toString();
        String strValorB = EditViewValorB.getText().toString();

        // Metodo para validar Campos -- Todo - Esta Forma de llamado de funciones ->
        evaluaCampos(strValorA, "Primer Campo vacio." );
        evaluaCampos(strValorB, "Segundo Campo vacio." );

        // Metodo que valida el radio si uno de los dos es true suma o resta
        procesoEvaluacion(strValorA, strValorB, RadioButonSumar, RadioButonRestar );

    }



    // Metodo para Validar.
    public void procesoEvaluacion(String strValorA, String strValorB, RadioButton rbSum, RadioButton rbRes){
        //valido para entrar al proceso
        if (strValorA.length() >  0 && strValorB.length() > 0 ){
            //como es un calculo debemos pasar los valores a enteros
            int inVAlorA = Integer.parseInt(strValorA);
            int inVAlorB = Integer.parseInt(strValorB);

            int intSuma, intResta;
            String strSuma, strResta;

            if (rbSum.isChecked() == true ){
                intSuma = inVAlorA + inVAlorB;
                strSuma = String.valueOf(intSuma);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Total:"+strSuma, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                TexViewResp.setText(strSuma);

            }

            if (rbRes.isChecked() == true ){
                intResta = inVAlorA - inVAlorB;
                strResta = String.valueOf(intResta);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Total"+strResta, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                TexViewResp.setText(strResta);


            }

        }

    }// fin del procesoEvalua


    // Metodo para Validar.
    public void evaluaCampos(String valor, String msj){
        //Valido los  inputs
        if (valor.length() == 0 ){
            //Muestra mensaje como un echo de PHP
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            //Setea la variables tipo TextView que definimos en el diseño
            TexViewResp.setText(msj);
        }
    }


}
