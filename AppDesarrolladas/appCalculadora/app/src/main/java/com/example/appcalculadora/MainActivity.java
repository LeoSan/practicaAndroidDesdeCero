package com.example.appcalculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView msjResp, textResultado;
    private RadioButton rbSuma, rbResta, rbMulti, rbDiv;
    private EditText inpNum1, inpNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo Aqui obtenemos todo lo relacionado con la vista Diseño pero este objeto lo llamamos R
        setContentView(R.layout.activity_main);

        //Definimos los valores que vamos a recibir del Layout R que es la vista diseño.
        inpNum1   = (EditText)findViewById(R.id.inpNumPri);
        inpNum2   = (EditText)findViewById(R.id.inpNumDos);

        msjResp      = (TextView)findViewById(R.id.respTitulo);
        textResultado      = (TextView)findViewById(R.id.textResultado);
        rbSuma  = (RadioButton)findViewById(R.id.radioSum);
        rbResta = (RadioButton)findViewById(R.id.radioResta);
        rbMulti = (RadioButton)findViewById(R.id.radioMul);
        rbDiv = (RadioButton)findViewById(R.id.radioDiv);

    }

    // Creamos el metodo de inicio Aqui se validan los campos y se validan los radio button
    public void procesaCalculo(View view){
        // todo lo que nos devuelve la view es texto. asi que declaro otras variables tipo String para recibir
        String strValorA = inpNum1.getText().toString();
        String strValorB = inpNum2.getText().toString();

        // Metodo para validar Campos -- Todo - Esta Forma de llamado de funciones ->
        evaluaCampos(strValorA, "Primer Campo vacio." );
        evaluaCampos(strValorB, "Segundo Campo vacio." );

        // Metodo que valida el radio si uno de los dos es true suma o resta
        procesoEvaluacion(strValorA, strValorB, rbSuma, rbResta, rbMulti, rbDiv  );

    }

    // Metodo para Validar.
    public void evaluaCampos(String valor, String msj){
        //Valido los  inputs
        if (valor.length() == 0 ){
            //Muestra mensaje como un echo de PHP
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            //Setea la variables tipo TextView que definimos en el diseño
            textResultado.setText(msj);
        }
    }

    // Metodo para Validar.
    public void procesoEvaluacion(String strValorA, String strValorB, RadioButton rbSum, RadioButton rbRes, RadioButton rbRMulti, RadioButton rbDiv ){
        //valido para entrar al proceso
        if (strValorA.length() >  0 && strValorB.length() > 0 ){
            //como es un calculo debemos pasar los valores a enteros
            int inVAlorA = Integer.parseInt(strValorA);
            int inVAlorB = Integer.parseInt(strValorB);

            int intResp;
            String strResul;

            if (rbSum.isChecked() == true ){
                intResp = inVAlorA + inVAlorB;
                strResul = String.valueOf(intResp);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Total:"+strResul, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                textResultado.setText(strResul);

            }

            if (rbRes.isChecked() == true ){
                intResp = inVAlorA - inVAlorB;
                strResul = String.valueOf(intResp);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Total"+strResul, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                textResultado.setText(strResul);
            }

            if (rbRMulti.isChecked() == true ){
                intResp = inVAlorA * inVAlorB;
                strResul = String.valueOf(intResp);
                //Muestra mensaje como un echo de PHP
                Toast.makeText(this, "Total"+strResul, Toast.LENGTH_SHORT).show();
                //Setea la variables tipo TextView que definimos en el diseño
                textResultado.setText(strResul);
            }

            if (rbDiv.isChecked() == true ){

                if (inVAlorB > 0){
                    intResp = inVAlorA / inVAlorB;
                    strResul = String.valueOf(intResp);
                    //Muestra mensaje como un echo de PHP
                    Toast.makeText(this, "Total"+strResul, Toast.LENGTH_SHORT).show();
                    //Setea la variables tipo TextView que definimos en el diseño
                    textResultado.setText(strResul);
                }else{
                    //Muestra mensaje como un echo de PHP
                    Toast.makeText(this, "Este valor no puede ser 0", Toast.LENGTH_SHORT).show();
                    //Setea la variables tipo TextView que definimos en el diseño
                    textResultado.setText("Este valor no puede ser 0");
                    textResultado.setHighlightColor(255);
                }

            }


        }

    }// fin del procesoEvalua

}
