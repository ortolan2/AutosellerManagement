package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InfomacionYGuia extends AppCompatActivity {
    //clase con informacion de la app y para mostrar la guia de usuario

    ImageView guia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomacion_yguia);
        guia=findViewById(R.id.imageViewGuia);
        guia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //al pulsar boton muestra la guia de usuarioa alojada en la URL

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1LbpG0fv_8oNW6C27lNyL_oUGwmIhiw-u/view?usp=drive_link"));
                startActivity(intent);

            }
        });
    }
}