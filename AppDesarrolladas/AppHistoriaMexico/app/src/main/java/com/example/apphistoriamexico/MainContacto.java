package com.example.apphistoriamexico;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class MainContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contacto);
        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    //Eventos
//Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
    }


}//fin de la clase
