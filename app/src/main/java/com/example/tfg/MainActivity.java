package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registrarse, iniciosesion;

    EditText usuario, contraseña;

    ImageView logo;

    Animation aparecer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrarse=findViewById(R.id.buttonRegistrarse);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

        iniciosesion=findViewById(R.id.buttonConectar);

        iniciosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=findViewById(R.id.nombreusuarioconectar);
                String nombreusuario=String.valueOf(usuario.getText());

                contraseña=findViewById(R.id.contraseñaconectar);
                String contraseñausuario=String.valueOf(contraseña.getText());

                if (nombreusuario.length()==0 || contraseñausuario.length()==0){
                    //validamos que el usuariio ha rellenado los campos
                    Toast.makeText(MainActivity.this, "No has introducido nombre usuario o contraseña", Toast.LENGTH_LONG).show();
                }

                Modelo objeto=new Modelo();
                Usuarios datos=new Usuarios();

                datos.setUsuario(String.valueOf(usuario.getText()));
                //damos valor a usuario con lo que ha introducido el usuario en la app

                String buscar=objeto.buscar(MainActivity.this, datos);
                //llamamos a la funcion buscar y pasamos los valores

                if (buscar.length()!=0){
                    if(buscar.equals(contraseñausuario)){//validadmos que lo insertado en la app en el campo contraseña es lo mismo que
                        //validadmos que lo insertado en la app en el campo contraseña es lo mismo que  nos devuelve la funcion
                        Intent intent=new Intent(MainActivity.this, Central.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_LONG).show();
                }
            }
        });
        //creamos la animacion y la ejecutamos con la imagen del Layout
        aparecer= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacioncompuesta);
        logo=findViewById(R.id.logo);
        logo.startAnimation(aparecer);
    }
}