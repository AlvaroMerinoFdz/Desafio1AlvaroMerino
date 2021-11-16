package Conexion

import Utiles.Constantes
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteConexion(
    context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db:SQLiteDatabase){
        db.execSQL("CREATE TABLE ${Constantes.TABLA_NOTAS}(${Constantes.ID_NOTAS} integer primary key autoincrement, ${Constantes.FECHA_NOTAS} text,${Constantes.HORA_NOTAS} text,${Constantes.ASUNTO_NOTAS} text)")
        db.execSQL("CREATE TABLE ${Constantes.TABLA_TEXTO}(${Constantes.ID_TEXTO} integer, ${Constantes.CONTENIDO_TEXTO} text)")
        db.execSQL("CREATE TABLE ${Constantes.TABLA_TAREAS}(${Constantes.CODIGO_TAREAS} integer primary key autoincrement,${Constantes.IDCRUZADO_TAREAS} integer, ${Constantes.DESCRIPCION_TAREAS} text,${Constantes.FOTO_TAREAS} TEXT,${Constantes.REALIZADO_TAREAS} BOOL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
}