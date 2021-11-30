package Conexion

import Utiles.Constantes
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteConexion(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db:SQLiteDatabase){
        db.execSQL("CREATE TABLE NOTAS(ID text primary key , FECHA text,HORA text,ASUNTO text, TIPO integer)")
        db.execSQL("CREATE TABLE TEXTO(ID text primary key, CONTENIDO text, FOREIGN KEY(ID) REFERENCES NOTAS(ID) ON DELETE CASCADE)")
        db.execSQL("CREATE TABLE TAREAS(COD text primary key ,ID_NOTAS integer, DESCRIPCION text,FOTO blob,REALIZADO int, FOREIGN KEY(ID_NOTAS) REFERENCES NOTAS(ID) ON DELETE CASCADE)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
}