package Conexion

import Modelo.DeTareas
import Modelo.Notas
import Modelo.Tarea
import Utiles.Constantes
import android.content.ContentValues
import android.content.Context

object Conexion {
    private var nombreBBDD = Constantes.NOMBREBBDD

    fun addNota(context: Context, nota:Notas){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put(Constantes.ID_NOTAS, nota.id)
        reg.put(Constantes.FECHA_NOTAS,nota.fecha)
        reg.put(Constantes.HORA_NOTAS, nota.hora)
        reg.put(Constantes.ASUNTO_NOTAS, nota.asunto)
        reg.put(Constantes.TIPO_NOTAS, nota.tipo)
        bd.insert(Constantes.TABLA_NOTAS, null, reg)
        bd.close()

    }

    fun addTarea(context: Context, idNota:Int, tarea:Tarea){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()
    }
}