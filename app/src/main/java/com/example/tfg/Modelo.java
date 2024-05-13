package com.example.tfg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Modelo {

    public SQLiteDatabase getCon(Context context){
        //Creamos objeto para la creacion de la base de datos
        ConexionSQLite conBBDD=new ConexionSQLite(context, "BBDDtfg", null, 1);
        //ejecutamos para la creacionde la base de datos y de de alta las tablas.
        SQLiteDatabase db=conBBDD.getWritableDatabase();
        return db;
    }

    int insertarUsuario (Context context, Usuarios datos){//funcion para dar de alta un usuario inserta usuario y contraseña
        int resultado=0;
        //ejecuta la Query de insertar un usuario
        String sql="insert into usuarios (usuario, contraseña) values ('"+datos.getUsuario()+"','"+datos.getContraseña()+"') ";
        SQLiteDatabase db=this.getCon(context);

        try{
            db.execSQL(sql);
            resultado=1;
            //si se ha ejecutado resultado cambia a 1
        }catch (Exception e){

        }
        return resultado;
    }

    String buscar(Context context, Usuarios datos){
        //funcion para buscar contraseña insertada de un usuario concreto
        String password="";

        String [] args=new String[]{datos.getUsuario()};
        //coge los datos del usuario
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select contraseña from usuarios where usuario=?",args);
        //ejecuta query
        if (micursor.moveToFirst()){
            do{
                password= micursor.getString(0);
                //guarda valor primera columna de la Query de consulta
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado=password;
        return resultado;
    }

    int actualizarUsuario (Context context, Usuarios datos){
        //funcion para actualizar usuario
        int resultado=0;

        String sql="update usuarios set contraseña='"+datos.getContraseña()+"' where usuario='"+datos.getUsuario()+"'";
        //creamos Query
        SQLiteDatabase db=this.getCon(context);
        try{
            db.execSQL(sql);
            //ejecutamos Query
            resultado=1;
        }catch (Exception e){

        }
        return resultado;
    }

    int insertarCliente (Context context, Clientes datos){
        //funcion crear cliente
        int resultado=0;

        String sql="insert into clientes (telefono, nombre, apellido, email) values ('"+datos.getTelefono()+"', '"+datos.getNombre()+"', '"+datos.getApellido()+"', '"+datos.getEmail()+"')";
        SQLiteDatabase db=this.getCon(context);

        try{
            db.execSQL(sql);
            resultado=1;
        }catch(Exception e){

        }
        return resultado;
    }

    String buscarCliente(Context context, Clientes datos){
        //funcion buscar cliente
        String nombre="";
        String apellido="";
        String email="";
        //creamos variables donde alojar el resultado

        String [] args=new String[] {datos.getTelefono()};
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select nombre, apellido, email from clientes where telefono=?", args);
        if (micursor.moveToFirst()){
            do{
                nombre= micursor.getString(0);
                apellido= micursor.getString(1);
                email= micursor.getString(2);
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado= nombre+ ","+apellido+","+email;
        //creamos string con el resultado concatenado
        return resultado;
    }

    int actualizarCliente (Context context, Clientes datos){
        //funcion actualizar cliente
        int resultado=0;
        String sql="update clientes set nombre='"+datos.getNombre()+"', apellido='"+datos.getApellido()+"', email='"+datos.getEmail()+"' where telefono='"+datos.getTelefono()+"'";
        SQLiteDatabase db=this.getCon(context);
        try{
            db.execSQL(sql);
            resultado=1;
        }catch (Exception e){

        }
        return resultado;
    }

    int insertarVehiculo (Context context, Vehiculos datos){
        //funcion insertar vehiculo
        int resultado=0;

        String sql="insert into vehiculos (matricula, marca, modelo, anio, km, precio) values ('"+datos.getMatricula()+"', '"+datos.getMarca()+"', '"+datos.getModelo()+"', '"+datos.getAnio()+"', '"+datos.getKm()+"','"+datos.getPrecio()+"')";
        SQLiteDatabase db=this.getCon(context);

        try{
            db.execSQL(sql);
            resultado=1;
        }catch(Exception e){

        }
        return resultado;
    }

    String buscarVehiculo(Context context, Vehiculos datos){
        //funcion buscar vehiculo
        String marca="";
        String modelo="";
        String anio="";
        String km="";
        String precio="";
        //creamos variables donde alojar resultado de la consulta

        String [] args=new String[] {datos.getMatricula()};
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select marca, modelo, anio, km, precio from vehiculos where matricula =?", args);
        if (micursor.moveToFirst()){
            do{
                marca= micursor.getString(0);
                modelo= micursor.getString(1);
                anio= micursor.getString(2);
                km=micursor.getString(3);
                precio=micursor.getString(4);
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado= marca+ ","+modelo+","+anio+","+km+","+precio;
        //concatenamos en un String el resultado
        return resultado;
    }

    int actualizarVehiculo (Context context, Vehiculos datos){
        //funcion actualizar vehiculo
        int resultado=0;
        String sql="update vehiculos set marca='"+datos.getMarca()+"', modelo='"+datos.getModelo()+"', anio='"+datos.getAnio()+"', km='"+datos.getKm()+"', precio='"+datos.getPrecio()+"' where matricula='"+datos.getMatricula()+"'";
        SQLiteDatabase db=this.getCon(context);
        try{
            db.execSQL(sql);
            resultado=1;
        }catch (Exception e){

        }
        return resultado;
    }

    int insertarPedido (Context context, Pedidos datos){
        //funcion insertar Pedido
        int resultado=0;

        String sql="insert into pedidos (referencia, vendedor, vehiculo, cliente, pvp, fechaPresupuesto, estado, comentarios) values ('"+datos.getReferencia()+"', '"+datos.getVendedor()+"'," +
                "+ '"+datos.getVehiculo()+"', '"+datos.getCliente()+"', '"+datos.getPvp()+"','"+datos.getFechaPresupuesto()+"', '"+datos.getEstado()+"', '"+datos.getComentarios()+"')";
        SQLiteDatabase db=this.getCon(context);

        try{
            db.execSQL(sql);
            resultado=1;
        }catch(Exception e){

        }
        return resultado;
    }

    String buscarPedido (Context context, Pedidos datos){
        //funcion buscarPedido
        String vendedor="";
        String vehiculo="";
        String cliente="";
        String pvp="";
        String fechaPresupuesto="";
        String estado="";
        String comentarios="";
        //creamos variables donde alojar el resultado

        String [] args=new String[] {datos.getReferencia()};
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select vendedor, vehiculo, cliente, pvp, fechaPresupuesto, estado, comentarios from pedidos where referencia =?", args);
        if (micursor.moveToFirst()){
            do{
                vendedor= micursor.getString(0);
                vehiculo= micursor.getString(1);
                cliente= micursor.getString(2);
                pvp=micursor.getString(3);
                fechaPresupuesto=micursor.getString(4);
                estado= micursor.getString(5);
                comentarios=micursor.getString(6);
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado= vendedor+ ","+vehiculo+","+cliente+","+pvp+","+fechaPresupuesto+","+estado+","+comentarios;
        //concatenamos el resultado en un String
        return resultado;
    }

    int actualizarPedido (Context context, Pedidos datos){
        //funcion actualizar pedido
        int resultado=0;
        String sql="update pedidos set vendedor='"+datos.getVendedor()+"', vehiculo='"+datos.getVehiculo()+"', cliente='"+datos.getCliente()+"', pvp='"+datos.getPvp()+"', fechaPresupuesto='"+datos.getFechaPresupuesto()+"', estado='"+datos.getEstado()+"', comentarios='"+datos.getComentarios()+"' where referencia='"+datos.getReferencia()+"'";
        SQLiteDatabase db=this.getCon(context);
        try{
            db.execSQL(sql);
            resultado=1;
        }catch (Exception e){

        }
        return resultado;
    }

    String buscarInteresados(Context context, Pedidos datos){
        //funcion buscar pedidos con valor interesado

        ArrayList<String> interesados=new ArrayList<>();
        //cramos ArrayList para alojar los resultados

        String[] args=new String []{"interesado"};
        //creamos Array con el valor a buscar
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select referencia from pedidos where estado=?",args);
        if (micursor.moveToFirst()){
            do{
                interesados.add(micursor.getString(0));
                //añadimos al ArrayList el resultado
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado="";
        for (String x:interesados){
            //recorremos Array
            resultado+=x+",";
            //concatenamos en un String los resultados
        }
        return resultado;
    }

    String buscarRechazados(Context context, Pedidos datos){
        //funcion para buscar pedidos con valor rechazado

        ArrayList<String> rechazado=new ArrayList<>();

        String[] args=new String []{"rechazado"};
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select referencia from pedidos where estado=?",args);
        if (micursor.moveToFirst()){
            do{
                rechazado.add(micursor.getString(0));
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado="";
        for (String x:rechazado){
            resultado+=x+",";
        }
        return resultado;
    }

    String buscarVendido(Context context, Pedidos datos){
        //funcion para buscar pedidos con valor vendido

        ArrayList<String> vendido=new ArrayList<>();

        String[] args=new String []{"vendido"};
        SQLiteDatabase db=this.getCon(context);
        Cursor micursor=db.rawQuery("select referencia from pedidos where estado=?",args);
        if (micursor.moveToFirst()){
            do{
                vendido.add(micursor.getString(0));
            }while (micursor.moveToNext());
        }
        micursor.close();

        String resultado="";
        for (String x:vendido){
            resultado+=x+",";
        }
        return resultado;
    }


}
