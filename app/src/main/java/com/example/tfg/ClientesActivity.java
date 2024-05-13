package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutput;

public class ClientesActivity extends AppCompatActivity {
    //clase de la opcion clientes

    EditText telefono, nombre, apellido, email;
    Button insertarCliente, actualizarCliente, consultarCliente;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        insertarCliente=findViewById(R.id.insertar);
        actualizarCliente=findViewById(R.id.actualizar);
        consultarCliente=findViewById(R.id.consultar);

        telefono=findViewById(R.id.editTextTelefono);
        nombre=findViewById(R.id.editTextNombre);
        apellido=findViewById(R.id.editTextApellido);
        email=findViewById(R.id.editTextEmail);



        insertarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Clientes datos=new Clientes();

                String mail=email.getText().toString();

                boolean arroba=mail.contains("@");
                boolean puntocom=mail.contains(".");


                if (telefono.getText().length()==9 && nombre.getText().length()!=0 && apellido.getText().length()!=0 && email.getText().length()!=0 && arroba==true && puntocom==true){
                    //validamos que todos los datos se han rellenado
                    datos.setTelefono(String.valueOf(telefono.getText()));
                    datos.setNombre(String.valueOf(nombre.getText()));
                    datos.setApellido(String.valueOf(apellido.getText()));
                    datos.setEmail(String.valueOf(email.getText()));

                    int insertar=objeto.insertarCliente(ClientesActivity.this, datos);
                    if (insertar==1){
                        //mostramos mensaje y vaciamos campos
                        Toast.makeText(ClientesActivity.this, "Añadido Cliente", Toast.LENGTH_LONG).show();
                        nombre.setText("");
                        apellido.setText("");
                        email.setText("");
                        telefono.setText("");
                    }else{
                        //nos muestra error por intentar repetir PK
                        Toast.makeText(ClientesActivity.this, "Error al añadir cliente, telefono ya existente", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //error por dejar algun campo vacio o telefono incorrecto
                    Toast.makeText(ClientesActivity.this, "Revisa los datos añadidos", Toast.LENGTH_LONG).show();
                }

            }
        });

        consultarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo objeto=new Modelo();
                Clientes datos=new Clientes();

                datos.setTelefono(String.valueOf(telefono.getText()));

                String buscar=objeto.buscarCliente(ClientesActivity.this, datos);

                if (buscar.length()>2){
                    //si el return es superior a 2 (el numero de comas minimo que tiene el return)
                    String [] resultado=buscar.split(",");
                    //cremos array separando el return pos sus comas
                    nombre.setText(resultado[0]);
                    apellido.setText(resultado[1]);
                    email.setText(resultado[2]);
                    //ponemos cada posicion del array en su campo
                }else{
                    //mostramos mensaje de que no se ha encontrado la busqueda
                    Toast.makeText(ClientesActivity.this, "Cliente no encontrado, introduce un telefono correcto", Toast.LENGTH_LONG).show();
                }
            }
        });

        actualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Clientes datos=new Clientes();

                String mail=email.getText().toString();

                boolean arroba=mail.contains("@");
                boolean puntocom=mail.contains(".");



                datos.setTelefono(String.valueOf(telefono.getText()));
                datos.setNombre(String.valueOf(nombre.getText()));
                datos.setApellido(String.valueOf(apellido.getText()));
                datos.setEmail(String.valueOf(email.getText()));

                String telefonoV=telefono.getText().toString();

                if (telefono.getText().length()==9 && nombre.getText().length()!=0 && apellido.getText().length()!=0 && email.getText().length()!=0 && arroba==true && puntocom==true){
                    //validadmos que todos los campos estan rellenos
                    int actualizar=objeto.actualizarCliente(ClientesActivity.this, datos);
                    if (actualizar==1){
                        //mostramos mensaje correto y vaciamos campos
                        Toast.makeText(ClientesActivity.this, "Cliente actualizado", Toast.LENGTH_LONG).show();
                        nombre.setText("");
                        apellido.setText("");
                        email.setText("");
                        telefono.setText("");
                    }else{
                        //mensaje de error
                        Toast.makeText(ClientesActivity.this, "Error al actualizar el cliente", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //mensaje de dejar campo vacio
                    Toast.makeText(ClientesActivity.this, "Introduce correctamente todos los datos obligatorio", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}