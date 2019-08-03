    package com.example.miintentparametros;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.TextView;

    public class Main2Activity extends AppCompatActivity {

        private TextView textNombre;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            textNombre = (TextView)findViewById(R.id.textNombre2);
            //Forma de  obtener
            // Nuevo Ojo Forma de captura Los datos del Intent Principal
            String dato = getIntent().getStringExtra("dato");
            textNombre.setText("Hola " + dato);
        }

        public void Atras(View view){
            Intent atras = new Intent(this, MainActivity.class);
            startActivity(atras);

        }

    }
