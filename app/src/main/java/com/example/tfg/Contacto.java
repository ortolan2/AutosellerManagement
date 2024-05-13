package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {
    //clase para el contacto con el desarrollador

    EditText asunto, mensaje, contacto;
    Button enviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        asunto=findViewById(R.id.editTextAsunto);
        mensaje=findViewById(R.id.editTextMensaje);
        contacto=findViewById(R.id.editTextContacto);

        enviar=findViewById(R.id.buttonEnviarMailCOntacto);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));

                if (contacto.getText().length()!=0 && asunto.getText().length()!=0 && mensaje.getText().length()!=0){
                    //validacion de que todos los campos estan rellenos
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"f.garzon@fp.safavalladolid.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString()  + "\ncontacto : "+contacto.getText().toString());

                    startActivity(intent);
                    //ejecutamos intent para el envio de email

                }else{
                    //error de que algun dato no se relleno
                    Toast.makeText(Contacto.this, "Error, rellene todos los campos", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}