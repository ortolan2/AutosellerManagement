package com.example.tfg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
//creamos las Querys para dar de alta las tablas
    String TABLE_USUARIOS="create table usuarios (usuario Varchar (50) constraint pk_usuarios primary key, contraseña varchar(50))";
    String TABLE_CLIENTES="create table clientes (telefono Varchar (50) constraint pk_clientes primary key, nombre varchar (50), apellido varchar (50), email varchar(50))";

    String TABLE_VEHICULOS="create table vehiculos (matricula Varchar (50) constraint pk_vehiculos primary key, marca varchar (50), modelo varchar (50), anio varchar (50), km varchar (50), precio varchar (50))";

    String TABLE_PEDIDOS="create table pedidos (referencia Varchar (50) constraint pk_pedidos primary key, vendedor varchar (50), vehiculo varchar (50), cliente varchar (50)," +
            " pvp varchar(50), fechaPresupuesto varchar(50),estado varchar (50), comentarios varchar (500), foreign key (vendedor) references usuarios (usuario)," +
            "foreign key (vehiculo) references vehiculos (matricula), foreign key (cliente) references clientes (telefono)    ) ";

    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//executamos las Querys para que se den de alta las tablas.
        sqLiteDatabase.execSQL(TABLE_USUARIOS);
        sqLiteDatabase.execSQL(TABLE_CLIENTES);
        sqLiteDatabase.execSQL(TABLE_VEHICULOS);
        sqLiteDatabase.execSQL(TABLE_PEDIDOS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
