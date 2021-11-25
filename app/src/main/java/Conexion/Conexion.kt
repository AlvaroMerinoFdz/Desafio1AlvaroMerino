package Conexion

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import Modelo.Tarea
import Utiles.Constantes
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    private var nombreBBDD = Constantes.NOMBREBBDD

    //CREATE
    fun addNotaText(context: Context, nota:DeTexto){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put(Constantes.ID_NOTAS, nota.id)
        reg.put(Constantes.FECHA_NOTAS,nota.fecha)
        reg.put(Constantes.HORA_NOTAS, nota.hora)
        reg.put(Constantes.ASUNTO_NOTAS, nota.asunto)
        reg.put(Constantes.TIPO_NOTAS, nota.tipo)
        bd.insert(Constantes.TABLA_NOTAS, null, reg)

        //ahora registramos una de texto
        val regTexto = ContentValues()
        regTexto.put("${Constantes.ID_TEXTO}", nota.id)
        regTexto.put("${Constantes.CONTENIDO_TEXTO}",nota.texto)
        bd.insert("${Constantes.TABLA_TEXTO}", null, regTexto)
        bd.close()

    }

    fun addTarea(context: Context, idNota:Int, tarea:Tarea){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()
        reg.put("${Constantes.CODIGO_TAREAS}", tarea.idTarea)
        reg.put("${Constantes.IDCRUZADO_TAREAS}", idNota)
        reg.put("${Constantes.DESCRIPCION_TAREAS}", tarea.descripcion)
        reg.put("${Constantes.REALIZADO_TAREAS}", tarea.realizada)
        reg.put("${Constantes.FOTO_TAREAS}", tarea.foto)
        bd.insert("${Constantes.TABLA_TAREAS}",null, reg)
    }

    //DELETE
    fun delNota(context: AppCompatActivity, nota:Notas):Int{
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val id = nota.id
        var cant = bd.delete("${Constantes.TABLA_NOTAS}", "${Constantes.ID_NOTAS}='${id}'", null)
        bd.close()
        return cant
    }


    //UPDATE



    //READ

    fun getNotas(context: AppCompatActivity):ArrayList<Notas>{
        var notas: ArrayList<Notas> = ArrayList(1)
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select ${Constantes.ID_NOTAS},${Constantes.ASUNTO_NOTAS},${Constantes.FECHA_NOTAS},${Constantes.HORA_NOTAS},${Constantes.TIPO_NOTAS} from ${Constantes.TABLA_NOTAS}",
            null
        )
        while (fila.moveToNext()) {

            var n: Notas = Notas(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4))
            notas.add(n)
        }
        bd.close()
        return notas
    }
    fun getNota(context: AppCompatActivity, id:String){
        var n:Notas? = null
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select ${Constantes.ASUNTO_NOTAS},${Constantes.FECHA_NOTAS},${Constantes.HORA_NOTAS},${Constantes.TIPO_NOTAS} WHERE ${Constantes.ID_NOTAS} =='$id'",
            null)
        if(fila.moveToFirst()){
            n = Notas(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getInt(4))
        }
    }
}