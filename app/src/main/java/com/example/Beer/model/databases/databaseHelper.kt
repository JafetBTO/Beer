package com.example.Beer.model.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class databaseHelper (context: Context):SQLiteOpenHelper(context, dbname,factory, version) {

   companion object{
       internal val dbname = "userDB"
       internal val factory = null
       internal val version = 1
   }

    override fun onCreate(p0: SQLiteDatabase?) {

        val creacion = "CREATE TABLE usuarios " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(30), apellido VARCHAR(40),email VARCHAR(100),password VARCHAR (20))"

        p0?.execSQL(creacion)
        //p0?.execSQL("create table user(codigo int primary key, nombre varchar(30), apellidos varchar(40),email varchar(100),password varchar (20))")
        //p0?.execSQL("create table favorite(codigo int primary key, name varchar(30), apellidos varchar(40),email varchar(100),password varchar (20))")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")

    }

    fun insertaUsuario(nombre:String, apellido:String, email:String, password:String) {

        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("apellido", apellido)
        datos.put("email", email)
        datos.put("password", password)

        val db = this.writableDatabase
        db.insert("usuarios", null,datos)
        db.close()
    }

    fun inicioSesion(email:String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "SELECT  * FROM usuarios WHERE email=\'$email\' AND password=\'$password\'"

            //"select * from usuarios where email = $email and passsword = $password"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }



}