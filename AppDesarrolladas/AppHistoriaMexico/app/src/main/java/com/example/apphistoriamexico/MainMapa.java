package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMapa extends AppCompatActivity {

    Button botonTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mapa);

        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Relacion Interfaz controlador
       // OrdenEstado01     = (TextView)findViewById(R.id.OrdenEstado01);



        System.out.println("---------------- Estoy MainMapa.java  -------------------------------");
    }
    @Override
    public  void onBackPressed(){
        System.out.println("---------------- No debe hacer nada  -------------------------------");
    }

    //Apunta al Activity de Contacto (Enviar Correo)
    public void vistaContacto(View view){

        //stopReproducir();
        Intent siguiente = new Intent(this, MainContacto.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz de Apoyo de memoria
    public void vistaApoyo (View view){
        //stopReproducir();
        Intent interfaz = new Intent(this, MainApoyo.class);
        startActivity(interfaz);
        finish();
    }

    //Apunta al Activity de Preguntas (Reto al Saber)
    public void vistaEstaConfig (View view){

        //stopReproducir();
        Intent siguiente = new Intent(this, MainEstadisticaConfiguracion.class);
        startActivity(siguiente);
        finish();
    }

    //Redirect-> Redirecciona a la interfaz Principal
    public void vistaPrincipal(View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        finish();
    }


    //Apunta al Activity de Preguntas (Reto al Saber)
    public void abrirMaps (View v){
        String[ ] sEstadosMapas = new  String[32];// Son 32 estados

        sEstadosMapas[1] = "https://www.google.com.mx/maps/place/Aguascalientes/@22.2977604,-104.0706238,6z/data=!4m5!3m4!1s0x8429e6a5760913b9:0x662e2fa6b1f4151f!8m2!3d21.8853247!4d-102.2915131";
        sEstadosMapas[2] = "https://www.google.com.mx/maps/place/Baja+California/@30.3449022,-117.7752789,7z/data=!3m1!4b1!4m5!3m4!1s0x80d7700ca877ddd3:0xfca4fd9f0318de8e!8m2!3d30.8406338!4d-115.2837585";
        sEstadosMapas[3] = "https://www.google.com.mx/maps/place/Baja+California+Sur/@25.4238927,-114.5630432,7z/data=!3m1!4b1!4m5!3m4!1s0x86afd339b5a21ec9:0x71dfb33aa9a75918!8m2!3d26.0444446!4d-111.6660725";
        sEstadosMapas[4] = "https://www.google.com.mx/maps/place/Campeche/@19.331158,-91.9164672,8z/data=!3m1!4b1!4m5!3m4!1s0x85e8c3424bc7e215:0xe68bf78c7baafcc3!8m2!3d18.931225!4d-90.2618068";
        sEstadosMapas[5] = "https://www.google.com.mx/maps/place/Chihuahua/@28.6070634,-110.6868512,6z/data=!3m1!4b1!4m5!3m4!1s0x8696752f8591a409:0x9b83e25340a77e07!8m2!3d28.4854458!4d-105.7820674";
        sEstadosMapas[6] = "https://www.google.com.mx/maps/place/Chiapas/@16.2521546,-94.4996008,7z/data=!3m1!4b1!4m5!3m4!1s0x858d44b49757ce67:0xcf0033824619d615!8m2!3d16.7569318!4d-93.1292353";
        sEstadosMapas[7] = "https://www.google.com.mx/maps/place/Coahuila+de+Zaragoza/@27.401327,-104.7003826,6.25z/data=!4m5!3m4!1s0x868872bda68e1e29:0x64009ad714cd7a39!8m2!3d27.058676!4d-101.7068294";
        sEstadosMapas[8] = "https://www.google.com.mx/maps/place/Colima/@18.9753924,-104.1061267,9.75z/data=!4m5!3m4!1s0x8425320dfe7025eb:0x2c1e40971f57690a!8m2!3d19.1222634!4d-104.0072348";
        sEstadosMapas[9] = "https://www.google.com.mx/maps/place/Durango/@24.5833429,-107.0860264,7z/data=!3m1!4b1!4m5!3m4!1s0x8690a9c1af734549:0x297de241abe308b2!8m2!3d24.5592665!4d-104.6587821";
        sEstadosMapas[10] = "https://www.google.com.mx/maps/place/Guanajuato/@20.8749973,-102.005615,8z/data=!3m1!4b1!4m5!3m4!1s0x842b5b8f509b7f7f:0xe78ea61981be43a0!8m2!3d20.9170187!4d-101.1617356";
        sEstadosMapas[11] = "https://www.google.com.mx/maps/place/Guerrero/@17.6013559,-101.2172817,8z/data=!3m1!4b1!4m5!3m4!1s0x85cb6558f327203f:0x3137229b4277cb3a!8m2!3d17.4391926!4d-99.5450974";
        sEstadosMapas[12] = "https://www.google.com.mx/maps/place/Hidalgo/@20.4971141,-100.0436852,8z/data=!3m1!4b1!4m5!3m4!1s0x85d10a0422ce2fe7:0x8152c5bf7940781b!8m2!3d20.0910963!4d-98.7623874";
        sEstadosMapas[13] = "https://www.google.com.mx/maps/place/Jalisco/@20.8285815,-105.8477366,7z/data=!3m1!4b1!4m5!3m4!1s0x841f40ebd4624e6f:0xa0feb0a35a1b4a53!8m2!3d20.6595382!4d-103.3494376";
        sEstadosMapas[14] = "https://www.google.com.mx/maps/place/Estado+de+M%C3%A9xico/@19.3255708,-100.7264365,8z/data=!3m1!4b1!4m5!3m4!1s0x85cd8992c0eb0a3b:0xc2fef9be9fc5a857!8m2!3d19.4968732!4d-99.7232673";
        sEstadosMapas[15] = "https://www.google.com.mx/maps/place/Michoac%C3%A1n/@19.1539266,-103.0220388,8z/data=!3m1!4b1!4m5!3m4!1s0x842a5f3e1eb35cb7:0x3bc7650cf34be0d4!8m2!3d19.5665192!4d-101.7068294";
        sEstadosMapas[16] = "https://www.google.com.mx/maps/place/Morelos/@18.7939106,-99.2533695,11.25z/data=!4m5!3m4!1s0x85cddfae25f6fe47:0x975f8225a169dd0f!8m2!3d18.6813049!4d-99.1013498";
        sEstadosMapas[17] = "https://www.google.com.mx/maps/place/Nayarit/@21.8426231,-106.3257518,8z/data=!3m1!4b1!4m5!3m4!1s0x8420a89ce63edb47:0xc2c8f5a28694d239!8m2!3d21.7513844!4d-104.8454619";
        sEstadosMapas[18] = "https://www.google.com.mx/maps/place/Nuevo+Le%C3%B3n/@25.4688014,-102.0587418,7z/data=!3m1!4b1!4m5!3m4!1s0x86629584a2a5b05d:0x701df442c36cbbc6!8m2!3d25.592172!4d-99.9961947";
        sEstadosMapas[19] = "https://www.google.com.mx/maps/place/Oaxaca/@17.1629599,-97.3315374,8z/data=!3m1!4b1!4m5!3m4!1s0x85c0d84f3a0e5c51:0x44c60c433dd90bc9!8m2!3d17.0542297!4d-96.7132304";
        sEstadosMapas[20] = "https://www.google.com.mx/maps/place/Puebla/@19.349598,-99.0190461,8z/data=!3m1!4b1!4m5!3m4!1s0x85cf8cb0ddc0a765:0xe25cae5bd60220ad!8m2!3d19.0412894!4d-98.2062013";
        sEstadosMapas[21] = "https://www.google.com.mx/maps/place/Quer%C3%A9taro/@20.8414311,-100.9412555,8z/data=!3m1!4b1!4m5!3m4!1s0x85d344d16144705f:0x9146b1c4a44869bb!8m2!3d20.5888184!4d-100.3898876";
        sEstadosMapas[22] = "https://www.google.com.mx/maps/place/Quintana+Roo/@19.7409375,-90.2482871,7z/data=!3m1!4b1!4m5!3m4!1s0x8f4ffcf0eac32695:0xc4f0c52a484c8d9c!8m2!3d19.1817393!4d-88.4791376";
        sEstadosMapas[23] = "https://www.google.com.mx/maps/place/San+Luis+Potos%C3%AD/@22.8151869,-102.5556892,7z/data=!3m1!4b1!4m5!3m4!1s0x842aa20005acfb79:0xe620b241c404415e!8m2!3d22.1565651!4d-100.9854628";
        sEstadosMapas[24] = "https://www.google.com.mx/maps/place/Sinaloa/@24.7429967,-109.6645612,7z/data=!3m1!4b1!4m5!3m4!1s0x869f539428a74959:0x9c7bdf760dbe5fef!8m2!3d25.1721091!4d-107.4795173";
        sEstadosMapas[25] = "https://www.google.com.mx/maps/place/Sonora/@29.3296077,-116.2344487,6z/data=!3m1!4b1!4m5!3m4!1s0x86982969e74dd20f:0xcd56ff1afcbbe374!8m2!3d29.2972247!4d-110.3308814";
        sEstadosMapas[26] = "https://www.google.com.mx/maps/place/Tabasco/@17.9503627,-93.6802879,8z/data=!3m1!4b1!4m5!3m4!1s0x85edfb5f8f78cb4f:0xd426cb11d9866b44!8m2!3d17.8409173!4d-92.6189273";
        sEstadosMapas[27] = "https://www.google.com.mx/maps/place/Tamaulipas/@24.931222,-100.8891835,7z/data=!3m1!4b1!4m5!3m4!1s0x867953aedb1e2459:0x33859f5a35e81925!8m2!3d24.26694!4d-98.8362755";
        sEstadosMapas[28] = "https://www.google.com.mx/maps/place/Tlaxcala/@18.5906959,-97.6380689,8z/data=!4m5!3m4!1s0x85cfd93f1c006113:0x38bae25ad47cbde0!8m2!3d19.318154!4d-98.2374954";
        sEstadosMapas[29] = "https://www.google.com.mx/maps/place/Veracruz/@19.4842412,-97.447793,8z/data=!4m5!3m4!1s0x85c355d0af54526d:0x2d777f0a6710b9b3!8m2!3d19.2601605!4d-96.5783387";
        sEstadosMapas[30] = "https://www.google.com.mx/maps/place/Yucat%C3%A1n/@21.0728617,-92.1744775,7z/data=!3m1!4b1!4m5!3m4!1s0x8f540ff8aad604ed:0xcccc217531083d0a!8m2!3d20.7098786!4d-89.0943377";
        sEstadosMapas[31] = "https://www.google.com.mx/maps/place/Zacatecas/@23.0727544,-104.7926045,7z/data=!3m1!4b1!4m5!3m4!1s0x8681d1a07ec9fe6f:0x1c1f2dfd5e256d98!8m2!3d22.7708555!4d-102.5832426";

        //Todo -> Aqui leo-> forma de capturar un evento y crear un componente y obtener su valor
        botonTemp     = (Button)findViewById( v.getId() );
        System.out.println( " -------------  Valor del  boton  ------------------"  );
        System.out.println( botonTemp.getText() );

        String urlEstado =   sEstadosMapas[  Integer.parseInt((String) botonTemp.getText()) ] ;

        //Genero el enlace
        Uri uri = Uri.parse(urlEstado);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

}//fin  de la clase

