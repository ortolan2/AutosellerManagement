package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class CambioContrasenia extends AppCompatActivity {
    //clase para el cambio de contraseña

    EditText usuario, contraseña, nuevaContraseña;
    TextInputLayout ContraseniaNuevalayout;

    Button validar, cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasenia);

        cambiar=findViewById(R.id.buttonCambioContrasenia);
        nuevaContraseña=findViewById(R.id.editTextContraseniaNueva);
        ContraseniaNuevalayout=findViewById(R.id.editTextContraseniaNuevalayout);

        validar=findViewById(R.id.buttonValidar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=findViewById(R.id.editTextUsuarioValidar);
                String nombreusuario=String.valueOf(usuario.getText());

                contraseña=findViewById(R.id.editTextContraseniaValidar);
                String contraseñausuario=String.valueOf(contraseña.getText());

                if (nombreusuario.length()==0 || contraseñausuario.length()==0){
                    //validamos que se han rellenado los campos
                    Toast.makeText(CambioContrasenia.this, "No has introducido nombre usuario o contraseña", Toast.LENGTH_LONG).show();
                }

                Modelo objeto=new Modelo();
                Usuarios datos=new Usuarios();

                datos.setUsuario(String.valueOf(usuario.getText()));

                String buscar=objeto.buscar(CambioContrasenia.this, datos);
                //llamamos a la funcion buscar

                if (buscar.length()!=0){
                    if(buscar.equals(contraseñausuario)){
                        //si la contraseña insertada en el campo es correcta mostramos el siguiente campo y boton
                        cambiar.setVisibility(View.VISIBLE);
                        ContraseniaNuevalayout.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(CambioContrasenia.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(CambioContrasenia.this, "Usuario inexistente", Toast.LENGTH_LONG).show();
                }
            }
        });

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Usuarios datos=new Usuarios();

                datos.setUsuario(String.valueOf(usuario.getText()));
                datos.setContraseña(String.valueOf(nuevaContraseña.getText()));

                int actualizar=objeto.actualizarUsuario(CambioContrasenia.this,datos);
                //llamamos a la funcion actualizar contraseña usuario

                if (nuevaContraseña.getText().length()!=0){
                    //validamos que el campo no esta vacio
                    if (actualizar==1 && nuevaContraseña.getText().length()!=0){
                        //si se ha ejecutado y la contraseña no es vacio mostramos mensaje y ejecutamos Intent
                        Toast.makeText(CambioContrasenia.this, "Contraseña actualizada", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(CambioContrasenia.this, MainActivity.class);
                        startActivity(intent);

                    }else{
                        //mostramo error
                        Toast.makeText(CambioContrasenia.this, "Error contraseña no actualizada", Toast.LENGTH_LONG).show();
                    }

                }else{
                    //indicamos que la contraseña nueva esta vacia
                    Toast.makeText(CambioContrasenia.this, "Error contraseña nueva vacia", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}