package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText usuario, contraseña, email;

    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrarse=findViewById(R.id.buttonRegistro);
        usuario=findViewById(R.id.usuarioRegistro);
        contraseña=findViewById(R.id.contraseñaRegistro);
        email=findViewById(R.id.emailRegistro);



        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Usuarios datos=new Usuarios();

                String mail=email.getText().toString();

                boolean arroba=mail.contains("@");
                boolean puntocom=mail.contains(".");


                if (usuario.getText().length()!=0 && contraseña.getText().length()!=0 && email.getText().length()!=0 ){
                    //validamos que el usuario ha introducdido todos los datos
                    if (arroba==true && puntocom==true){
                        //validadmos que el email es correcto
                        datos.setUsuario(String.valueOf(usuario.getText()));
                        datos.setContraseña((String .valueOf(contraseña.getText())));
                        //damos valores


                        int insertar=objeto.insertarUsuario(Registro.this, datos);
                        //llamamos a la funcion insertarUsuario y pasamos los valores

                        if (insertar==1){
                            //validamos que se ha ejecutado correctamente la Query
                            Toast.makeText(Registro.this, "Registro correcto", Toast.LENGTH_LONG).show();
                            //ejecuta Intent para enviar email
                            Intent intent=new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:"));

                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Usuario y Contraseña ");
                            intent.putExtra(Intent.EXTRA_TEXT, "Usuario: "+usuario.getText().toString()+ " Contraseña: "+contraseña.getText().toString());

                            startActivity(intent);

                        }else{
                            // de no ejecutarse indicamos fallo
                            Toast.makeText(Registro.this, "Nombre de usuario ya registrado", Toast.LENGTH_LONG).show();

                        }

                    }else{
                        //indicamos que el mail no es correcto
                        Toast.makeText(Registro.this, "El e-mail introducido no es correcto", Toast.LENGTH_LONG).show();
                    }


                }else{
                    //indicamos que falta algun dato
                    Toast.makeText(Registro.this, "No ha introducido algun dato", Toast.LENGTH_LONG).show();


                }


            }
        });
    }
}