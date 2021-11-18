package Conexion

import Utiles.Constantes
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteConexion(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db:SQLiteDatabase){
        /*
        * CREATE TABLE NOTAS(ID integer primary key autoincrement, FECHA text,HORA text,ASUNTO text)
          CREATE TABLE TEXTO(ID integer primary key, CONTENIDO text, FOREIGN KEY(ID) REFERENCES NOTAS(ID) ON DELETE CASCADE)
          CREATE TABLE TAREAS(COD integer primary key autoincrement,ID_NOTAS integer, DESCRIPCION text,FOTO TEXT,REALIZADO BOOL, FOREIGN KEY(ID_NOTAS) REFERENCES NOTAS(ID) ON DELETE CASCADE)
        *
        * */
        db.execSQL("CREATE TABLE NOTAS(ID integer primary key autoincrement, FECHA text,HORA text,ASUNTO text, TIPO integer)")
        db.execSQL("CREATE TABLE TEXTO(ID integer primary key, CONTENIDO text, FOREIGN KEY(ID) REFERENCES NOTAS(ID) ON DELETE CASCADE)")
        db.execSQL("CREATE TABLE TAREAS(COD integer primary key autoincrement,ID_NOTAS integer, DESCRIPCION text,FOTO TEXT,REALIZADO BOOL, FOREIGN KEY(ID_NOTAS) REFERENCES NOTAS(ID) ON DELETE CASCADE)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
}