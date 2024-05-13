package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EstadosActivity extends AppCompatActivity {
//clase de la pantalla para visualizar el estado de todos los presupuestos
    Button consultar;

    EditText interesa, rechaza,acepta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados);

        interesa=findViewById(R.id.editTextInteresados);
        rechaza=findViewById(R.id.editTextRechazados);
        acepta=findViewById(R.id.editTextAceptados);

        consultar=findViewById(R.id.buttonConsultarEstadoPedidos);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo objeto=new Modelo();
                Pedidos datos=new Pedidos();
                //al pulsar el boton se ejecutan las funciones y rellena los campos con los resultados

                String pedidosInteresados= objeto.buscarInteresados(EstadosActivity.this, datos);
                interesa.setText(pedidosInteresados);

                String pedidosRechazados=objeto.buscarRechazados(EstadosActivity.this, datos);
                rechaza.setText(pedidosRechazados);

                String pedidosAceptados=objeto.buscarVendido(EstadosActivity.this, datos);
                acepta.setText(pedidosAceptados);


            }
        });

    }
}