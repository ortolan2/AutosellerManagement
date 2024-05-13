package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehiculoActivity extends AppCompatActivity {

    EditText matricula, marca, model, aniomat, kmauto, precioauto;

    Button insertarauto, buscarauto, actualizarauto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);

        matricula=findViewById(R.id.editTextMatricula);
        marca=findViewById(R.id.editTextMarca);
        model=findViewById(R.id.editTextModelo);
        aniomat=findViewById(R.id.editTextMatriculacion);
        kmauto=findViewById(R.id.editTextkm);
        precioauto=findViewById(R.id.editTextPrecio);

        insertarauto=findViewById(R.id.insertarVehiculo);
        buscarauto=findViewById(R.id.consultarVehiculo);
        actualizarauto=findViewById(R.id.actualizarVehiculo);


        insertarauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Vehiculos datos=new Vehiculos();

                if (matricula.getText().length()==7 && marca.getText().length()!=0 && model.getText().length()!=0 && aniomat.getText().length()!=0 && kmauto.getText().length()!=0 && precioauto.getText().length()!=0){
                    //validamos que todos los campos esten rellenados
                    datos.setMatricula(String.valueOf(matricula.getText()));
                    datos.setMarca(String.valueOf(marca.getText()));
                    datos.setModelo(String.valueOf(model.getText()));
                    datos.setAnio(String.valueOf(aniomat.getText()));
                    datos.setKm(String.valueOf(kmauto.getText()));
                    datos.setPrecio(String.valueOf(precioauto.getText()));

                    int insertar=objeto.insertarVehiculo(VehiculoActivity.this, datos);

                    if (insertar==1){
                        //mostramos mensaje de que ha funcionado y vaciamos campos
                        Toast.makeText(VehiculoActivity.this, "Añadido Vehiculo", Toast.LENGTH_LONG).show();
                        matricula.setText("");
                        marca.setText("");
                        model.setText("");
                        aniomat.setText("");
                        kmauto.setText("");
                        precioauto.setText("");
                    }else{
                        //mostramos error de que la PK ya existe
                        Toast.makeText(VehiculoActivity.this, "Error al añadir el Vehiculo, matricula ya existente", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //mostramos error de que algun campo esta vacio
                    Toast.makeText(VehiculoActivity.this, "Revisa los datos añadidos", Toast.LENGTH_LONG).show();
                }
            }
        });

        buscarauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Vehiculos datos=new Vehiculos();

                datos.setMatricula(String.valueOf(matricula.getText()));

                String buscar=objeto.buscarVehiculo(VehiculoActivity.this, datos);
                //alojamos en string el return de la funcion

                if (buscar.length()>4){
                    //validamos que el resultado es superior a las comas que devuelve
                    String [] resultado=buscar.split(",");
                    //ingresmos el return en una array separado por comas
                    marca.setText(resultado[0]);
                    model.setText(resultado[1]);
                    aniomat.setText(resultado[2]);
                    kmauto.setText(resultado[3]);
                    precioauto.setText(resultado[4]);
                    //alojamos en cada campo la posicion correspondiente de cada array
                }else{
                    //mostramos mensaje de que la consutal ha dado error
                    Toast.makeText(VehiculoActivity.this, "Vehiculo no encontrado, introduce una matricula correcta", Toast.LENGTH_LONG).show();
                }

            }
        });

        actualizarauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Vehiculos datos=new Vehiculos();

                datos.setMatricula(String.valueOf(matricula.getText()));
                datos.setMarca(String.valueOf(marca.getText()));
                datos.setModelo(String.valueOf(model.getText()));
                datos.setAnio(String.valueOf(aniomat.getText()));
                datos.setKm(String.valueOf(kmauto.getText()));
                datos.setPrecio(String.valueOf(precioauto.getText()));

                String matriculaV=matricula.getText().toString();

                if (matricula.getText().length()==7 && marca.getText().length()!=0 && model.getText().length()!=0 && aniomat.getText().length()!=0 && kmauto.getText().length()!=0 && precioauto.getText().length()!=0){
                    //validamos que todos los campos estan rellenos
                    int actualizar=objeto.actualizarVehiculo(VehiculoActivity.this, datos);
                    if (actualizar==1){
                        Toast.makeText(VehiculoActivity.this, "Vehiculo actualizado", Toast.LENGTH_LONG).show();
                        matricula.setText("");
                        marca.setText("");
                        model.setText("");
                        aniomat.setText("");
                        kmauto.setText("");
                        precioauto.setText("");
                    }else{
                        Toast.makeText(VehiculoActivity.this, "Error al actualizar el vehiculo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(VehiculoActivity.this, "Introduce correctamente todos los datos obligatorios", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}