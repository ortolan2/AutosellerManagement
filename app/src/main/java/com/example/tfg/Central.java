package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Central extends AppCompatActivity {
    //clase de la pantalla central

    Button cliente, autos, pedidos, resumen, usuario;
    Toolbar barrra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);


        cliente=findViewById(R.id.cliente);

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ejecutamos intent al pulsar boton
                Intent intent=new Intent(Central.this, ClientesActivity.class);
                startActivity(intent);

            }
        });

        autos=findViewById(R.id.autos);

        autos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Central.this, VehiculoActivity.class);
                startActivity(intent);
            }
        });

        pedidos=findViewById(R.id.pedidos);

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ejecutamos intent al pulsar boton
                Intent intent=new Intent(Central.this, PresupuestosActivity.class);
                startActivity(intent);
            }
        });

        resumen=findViewById(R.id.estado);

        resumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ejecutamos intent al pulsar boton
                Intent intent=new Intent(Central.this, EstadosActivity.class);
                startActivity(intent);
            }
        });




        barrra=findViewById(R.id.toolbarMenu);
        setSupportActionBar(barrra);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelect=item.getItemId();
        //ejecutamos intent al pulsar opcion del menu

        if(itemSelect==R.id.seleccion1){
            Intent intent=new Intent(Central.this, CambioContrasenia.class);
            startActivity(intent);
        }else if(itemSelect==R.id.seleccion2){
            Intent intent=new Intent(Central.this, InfomacionYGuia.class);
            startActivity(intent);
        }else if (itemSelect==R.id.seleccion3){
            Intent inten=new Intent(Central.this, MainActivity.class);
            startActivity(inten);
        }else{
            Intent inten=new Intent(Central.this, Contacto.class);
            startActivity(inten);

        }


        return super.onOptionsItemSelected(item);
    }
}