package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PresupuestosActivity extends AppCompatActivity {

    EditText referenciaPedido, vendedorPedido, vehiculoPedido, clientePedido, precioPedido, comentarios;


    TextView fechaPedido;

    Spinner estadoPedido;
    Button agregarPedido, buscarPedido, actualizarPedido, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuestos);

        referenciaPedido=findViewById(R.id.editTextReferencia);
        vendedorPedido=findViewById(R.id.editTextVendedor);
        vehiculoPedido=findViewById(R.id.editTextMatriculaVehiculo);
        clientePedido=findViewById(R.id.editTextCliente);
        precioPedido=findViewById(R.id.editTextPVP);
        fechaPedido=findViewById(R.id.editTextFecha);
        estadoPedido=findViewById(R.id.editTextEstado);
        comentarios=findViewById(R.id.editTextComentarios);

        String [] valoresEstado={"vendido", "interesado", "rechazado"};
        ArrayAdapter<String> adapta=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valoresEstado);
        estadoPedido.setAdapter(adapta);

        agregarPedido=findViewById(R.id.insertarPedido);
        buscarPedido=findViewById(R.id.consultarPedido);
        actualizarPedido=findViewById(R.id.actualizarPedido);

        agregarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Pedidos datos=new Pedidos();
                Clientes datos1=new Clientes();
                Vehiculos datos2=new Vehiculos();
                Usuarios datos3=new Usuarios();




                datos3.setUsuario(String.valueOf(vendedorPedido.getText()));
                String vendedorCorrecto=objeto.buscar(PresupuestosActivity.this, datos3);
                //alojamos en string el resultado de la funcion
                datos2.setMatricula(String.valueOf(vehiculoPedido.getText()));
                String vehiculoCorrecto=objeto.buscarVehiculo(PresupuestosActivity.this, datos2);
                //alojamos en string el resultado de la funcion
                datos1.setTelefono(String.valueOf(clientePedido.getText()));
                String clienteCorrecto=objeto.buscarCliente(PresupuestosActivity.this, datos1);;
                //alojamos en string el resultado de la funcion

                if (referenciaPedido.getText().length()!=0 && vendedorPedido.getText().length()!=0 && vehiculoPedido.getText().length()!=0 && clientePedido.getText().length()!=0 && precioPedido.getText().length()!=0 && fechaPedido.getText().length()!=0 && comentarios.getText().length()!=0/*&& estadoPedido.getText().length()!=0*/ ){
                   //validamos que todos los campos estan rellenados
                    if (clienteCorrecto.length()>2 && vehiculoCorrecto.length()>4 && vendedorCorrecto.length()!=0){
                    //validamos que el cliente, el vehiculo y el vendedor ya estan registrados
                        datos.setReferencia(String.valueOf(referenciaPedido.getText()));
                        datos.setVendedor(String.valueOf(vendedorPedido.getText()));
                        datos.setVehiculo(String.valueOf(vehiculoPedido.getText()));
                        datos.setCliente(String.valueOf(clientePedido.getText()));
                        datos.setPvp(String.valueOf(precioPedido.getText()));
                        datos.setFechaPresupuesto(String.valueOf(fechaPedido.getText()));
                        datos.setEstado(String.valueOf(estadoPedido.getSelectedItem()));
                        datos.setComentarios(String.valueOf(comentarios.getText()));

                        int insertar=objeto.insertarPedido(PresupuestosActivity.this, datos);

                        if (insertar==1){
                            //mostramos mensaje de correcto y vaciamos campos
                            Toast.makeText(PresupuestosActivity.this, "Añadido pedido", Toast.LENGTH_LONG).show();
                            referenciaPedido.setText("");
                            vendedorPedido.setText("");
                            vehiculoPedido.setText("");
                            clientePedido.setText("");
                            precioPedido.setText("");
                            fechaPedido.setText("");
                            comentarios.setText("");
                        }else{
                            Toast.makeText(PresupuestosActivity.this, "Error al añadir el pedido, Referencia ya usada", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(PresupuestosActivity.this, "Vendedor inexistente o cliente inexistente o vehiculo inexistente", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(PresupuestosActivity.this, "Revisa los datos añadidos", Toast.LENGTH_LONG).show();
                }

            }
        });

        buscarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Pedidos datos=new Pedidos();

                datos.setReferencia(String.valueOf(referenciaPedido.getText()));

                String buscar=objeto.buscarPedido(PresupuestosActivity.this, datos);
                //alojamos en string el return de la funcion

                if (buscar.length()>6){
                    //indicamos que el resultado es mayor que todas las comas que tiene el return
                    String [] resultado=buscar.split(",");
                    //creamos array donde ingresamos el resultado de la consulta pero los separamos por las comas
                    vendedorPedido.setText(resultado[0]);
                    vehiculoPedido.setText(resultado[1]);
                    clientePedido.setText(resultado[2]);
                    precioPedido.setText(resultado[3]);
                    fechaPedido.setText(resultado[4]);
                    //estadoPedido.set(resultado[5]);
                    comentarios.setText(resultado[6]);
                }else{
                    //mostramo mensaje de error
                    Toast.makeText(PresupuestosActivity.this, "Presupuesto no encontrado, introduce una referencia correcta", Toast.LENGTH_LONG).show();
                }
            }
        });

        actualizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo objeto=new Modelo();
                Pedidos datos=new Pedidos();
                Clientes datos1=new Clientes();
                Vehiculos datos2=new Vehiculos();
                Usuarios datos3=new Usuarios();

                datos3.setUsuario(String.valueOf(vendedorPedido.getText()));
                String vendedorCorrecto=objeto.buscar(PresupuestosActivity.this, datos3);
                datos2.setMatricula(String.valueOf(vehiculoPedido.getText()));
                String vehiculoCorrecto=objeto.buscarVehiculo(PresupuestosActivity.this, datos2);
                datos1.setTelefono(String.valueOf(clientePedido.getText()));
                String clienteCorrecto=objeto.buscarCliente(PresupuestosActivity.this, datos1);;

                datos.setReferencia(String.valueOf(referenciaPedido.getText()));
                datos.setVendedor(String.valueOf(vendedorPedido.getText()));
                datos.setVehiculo(String.valueOf(vehiculoPedido.getText()));
                datos.setCliente(String.valueOf(clientePedido.getText()));
                datos.setPvp(String.valueOf(precioPedido.getText()));
                datos.setFechaPresupuesto(String.valueOf(fechaPedido.getText()));
                datos.setEstado(String.valueOf(estadoPedido.getSelectedItem()));
                datos.setComentarios(String.valueOf(comentarios.getText()));

                String referenciaV=referenciaPedido.getText().toString();

                if (referenciaPedido.getText().length()!=0 && vendedorPedido.getText().length()!=0 && vehiculoPedido.getText().length()!=0 && clientePedido.getText().length()!=0 && precioPedido.getText().length()!=0 && fechaPedido.getText().length()!=0 && comentarios.getText().length()!=0/*&& estadoPedido.getText().length()!=0*/){
                //validamos que todos los campos estan rellenos
                    if (clienteCorrecto.length()>2 && vehiculoCorrecto.length()>4 && vendedorCorrecto.length()!=0){
                        //validamos que al modificar no se ingresen o cliente no registrao o vehiculo no registrado o vendedor no regstrado
                        int actualizar=objeto.actualizarPedido(PresupuestosActivity.this, datos);
                        if (actualizar==1){
                            Toast.makeText(PresupuestosActivity.this, "Pedido actualizado", Toast.LENGTH_LONG).show();
                            referenciaPedido.setText("");
                            vendedorPedido.setText("");
                            vehiculoPedido.setText("");
                            clientePedido.setText("");
                            precioPedido.setText("");
                            fechaPedido.setText("");
                            comentarios.setText("");
                        }else{
                            Toast.makeText(PresupuestosActivity.this, "Error al actualizar el pedido", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(PresupuestosActivity.this, "Vendedor inexistente o cliente inexistente o vehiculo inexistente", Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(PresupuestosActivity.this, "Introduce correctamente todos los datos obligatorios", Toast.LENGTH_LONG).show();
                }
            }
        });

        fecha=findViewById(R.id.buttonFecha);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calen=Calendar.getInstance();//con estas variables indicamos lo que queremos sacar.
                int anio=calen.get(Calendar.YEAR);
                int mes=calen.get(Calendar.MONTH);
                int dia=calen.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog fecha1=new DatePickerDialog(PresupuestosActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int mescorrecto=month+1;

                        fechaPedido.setText(dayOfMonth+ " - "+mescorrecto+" - "+year);//para setear necesitamos las que nos pasa el metodo

                    }
                },anio, mes, dia);
                fecha1.show();
            }
        });


    }
}