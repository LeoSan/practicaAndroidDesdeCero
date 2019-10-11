package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static android.widget.Toast.*;


public class MainContacto extends AppCompatActivity {

    private String correo, password;
    private EditText inpNombreContacto, inpAsuntoContacto, inpMsjContacto, inpCorreoContacto;
    private Button enviar;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contacto);
        //Metodo de cambiar nombre de la App y el Icono en cada Activity
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Forma para comunicarme con la interfaz
        //inpNombreContacto = (EditText) findViewById(R.id.inpNombreContacto);
        inpAsuntoContacto = (EditText) findViewById(R.id.inpAsuntoContacto);
        inpMsjContacto = (EditText) findViewById(R.id.inpMsjContacto);
        //   inpCorreoContacto = (EditText) findViewById(R.id.inpCorreoContacto);
        enviar = (Button) findViewById(R.id.idEnviarCorreo);

        //Declaro listener
        //Impementamos el metodo para el boton
        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Forma Intent
               // mandarMail();

                mandarMailSmtp();

            }// fin del onlic interno
        });
    }//fin del onCreate


    private void mandarMail(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");

        String[] CC = {"cuenca623@gmail.com"};
        //email.putExtra(Intent.EXTRA_EMAIL, inpCorreoContacto.getText().toString());
        email.putExtra(Intent.EXTRA_CC, CC);
        email.putExtra(Intent.EXTRA_SUBJECT, inpAsuntoContacto.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, inpMsjContacto.getText().toString());

        try {
            startActivity(Intent.createChooser(email, "send email"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No tienes ningún gestor de correo instalado", Toast.LENGTH_SHORT).show();
        }
    }//fin del metodo


    private void mandarMailSmtp(){

        //Variables constantes
        correo   = "cuenca623@hotmail.com";
        password = "h0tm41l_0102=*";

        //agregamos politicas
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Creamos nuestro objeto de propiesades
        Properties ObjProps = new Properties();
        ObjProps.put("mail.smtp.host", "smtp.live.com");
        ObjProps.put("mail.smtp.auth", "true");
        ObjProps.put("mail.smtp.starttls.enable", true); //Esto es en caso de gamil
        ObjProps.put("mail.smtp.port", "25");
        ObjProps.put("mail.smtp.ssl.enable", true);


        // iniciamos session
        try {
            Session session = Session.getInstance(ObjProps,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo, password);
                        }
                    });

            session.setDebug(true);

            if (session != null){

                System.out.println("Entro a la seccion");

                //Aqui creamos pracaticamente el cuerpo del correo
                MimeMessage message = new MimeMessage(session);

                message.setSubject( inpAsuntoContacto.getText().toString() );
                message.setContent( inpMsjContacto.getText().toString(), "text/html charset=utf-8" );
                message.setFrom(new InternetAddress(correo));
                message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse( "cuenca623@gmail.com" , false));
                //message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse( inpCorreoContacto.getText().toString())  );

                // aqui enviamos el correo

                Transport transport = session.getTransport("smtp"); //Esto en caso de hotmail
                transport.connect("smtp.live.com", 587, correo, password );
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();

                System.out.println("Envio");

            }else{
                System.out.println("Fallo la conexion");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }// fin del metodo

    //Eventos
//Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        Toast.makeText(this, "Cambio", Toast.LENGTH_SHORT).show();
    }

}// fin de la clase
