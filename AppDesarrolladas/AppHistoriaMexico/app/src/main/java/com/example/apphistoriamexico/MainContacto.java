package com.example.apphistoriamexico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        inpNombreContacto = (EditText) findViewById(R.id.inpNombreContacto);
        inpAsuntoContacto = (EditText) findViewById(R.id.inpAsuntoContacto);
        inpMsjContacto = (EditText) findViewById(R.id.inpMsjContacto);
        inpCorreoContacto = (EditText) findViewById(R.id.inpCorreoContacto);
        enviar = (Button) findViewById(R.id.idEnviarCorreo);

        //Variables constantes
        correo = "cuenca623@hotmail.com";
        password = "h0tm41l_0102=*";

        //Declaro listener
        //Impementamos el metodo para el boton
        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //agregamos politicas
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                //Creamos nuestro objeto de propiesades
                Properties ObjProps = new Properties();
                ObjProps.put("mail.smpt.host", "smtp.live.com");
              //  ObjProps.put("mail.smpt.socketFactory.port", "587"); // solo para gmail
              //  ObjProps.put("mail.smpt.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Solo para gmail
                ObjProps.put("mail.smpt.auth", "true");
               //  ObjProps.put("mail.smpt.port", "587"); // Solo para gmail
                ObjProps.put("mail.smpt.starttls.enable", "true"); //Esto es en caso de gamil

                try {


                    Session session = Session.getInstance(ObjProps,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(correo, password);
                                }
                            });

                    if (session != null){


                        //Aqui creamos pracaticamente el cuerpo del correo
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Prueba Correo Android");  // ojo leo todo
                        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse( inpCorreoContacto.getText().toString())  );
                        message.setContent( inpMsjContacto.getText().toString(), "text/html charset=utf-8" );

                        // aqui enviamos el correo

                        Transport transport = session.getTransport("smtp"); //Esto en caso de hotmail
                        transport.connect("smtp.live.com", 587, correo, password );
                        transport.sendMessage(message, message.getAllRecipients());
                        transport.close();


                        //Transport.send(message); // esto es en caso de gmail

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }//fin del onCreate






//Eventos
//Redirect-> Redirecciona a la interfaz Principal
    public void vistaInterfazPrincipal (View view){
        Intent interfaz = new Intent(this,MainActivity.class);
        startActivity(interfaz);
        Toast.makeText(this, "Cambio", Toast.LENGTH_SHORT).show();
    }



}// fin de la clase
