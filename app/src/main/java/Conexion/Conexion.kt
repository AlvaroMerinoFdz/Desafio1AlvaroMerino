package Conexion

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
    fun addTexto(context: Context, nota: DeTexto){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put(Constantes.ID_NOTAS, nota.id)
        reg.put(Constantes.FECHA_NOTAS,nota.fecha)
        reg.put(Constantes.HORA_NOTAS, nota.hora)
        reg.put(Constantes.ASUNTO_NOTAS, nota.asunto)
        reg.put(Constantes.TIPO_NOTAS, nota.tipo)
        bd.insert(Constantes.TABLA_NOTAS, null, reg)

        val regTexto = ContentValues()
        regTexto.put("${Constantes.ID_TEXTO}", nota.id)
        regTexto.put("${Constantes.CONTENIDO_TEXTO}",nota.texto)
        bd.insert("${Constantes.TABLA_TEXTO}", null, regTexto)
        bd.close()

    }
    fun addTarea(context: Context,idNota:String, tarea:Tarea){
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put("${Constantes.CODIGO_TAREAS}", tarea.idTarea)
        reg.put("${Constantes.IDCRUZADO_TAREAS}", idNota)
        reg.put("${Constantes.DESCRIPCION_TAREAS}", tarea.descripcion)
        reg.put("${Constantes.REALIZADO_TAREAS}", tarea.realizada)
        reg.put("${Constantes.FOTO_TAREAS}", tarea.foto)
        bd.insert("${Constantes.TABLA_TAREAS}", null, reg)
        bd.close()
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
    fun delTarea(context: AppCompatActivity, tarea: Tarea):Int {
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val id = tarea.idTarea
        var cant = bd.delete("${Constantes.TABLA_TAREAS}", "${Constantes.CODIGO_TAREAS}='${id}'", null)
        bd.close()
        return cant
    }


    //UPDATE
    fun modificarNotaTexto(context: AppCompatActivity,id:String, nota:DeTexto):Int{
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()



        reg.put(Constantes.ASUNTO_NOTAS, nota.asunto)
        bd.insert(Constantes.TABLA_NOTAS, null, reg)
        val cant = bd.update("${Constantes.TABLA_NOTAS}",reg,"${Constantes.ID_NOTAS}='${id}'",null)

        val regTexto = ContentValues()
        //regTexto.put("${Constantes.ID_TEXTO}", nota.id)
        regTexto.put("${Constantes.CONTENIDO_TEXTO}",nota.texto)
        val cantTexto = bd.update("${Constantes.TABLA_TEXTO}", regTexto,"${Constantes.ID_TEXTO}='${id}'",null)

        bd.close()

        if(cant == cantTexto){
            return cantTexto
        }else
            return cant
    }

    fun modificarTarea(context: AppCompatActivity, tarea: Tarea):Int{
        val admin = AdminSQLiteConexion(context, nombreBBDD, null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put("${Constantes.DESCRIPCION_TAREAS}", tarea.descripcion)
        reg.put("${Constantes.FOTO_TAREAS}", tarea.foto)
        reg.put("${Constantes.REALIZADO_TAREAS}", tarea.realizada)

        val cant = bd.update("${Constantes.TABLA_TAREAS}",reg,"${Constantes.CODIGO_TAREAS}='${tarea.idTarea}'",null)
        bd.close()
        return cant
    }



    //READ

    fun getNotas(context: AppCompatActivity):ArrayList<Notas>{
        var notas: ArrayList<Notas> = ArrayList(1)
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select ${Constantes.ID_NOTAS},${Constantes.FECHA_NOTAS},${Constantes.HORA_NOTAS},${Constantes.ASUNTO_NOTAS},${Constantes.TIPO_NOTAS} from ${Constantes.TABLA_NOTAS}",
            null
        )
        while (fila.moveToNext()) {
            var n: Notas = Notas(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4))
            notas.add(n)
        }
        bd.close()
        return notas
    }
    fun getNota(context: AppCompatActivity, id:String): Notas? {
        var n:Notas? = null
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select ${Constantes.FECHA_NOTAS},${Constantes.HORA_NOTAS},${Constantes.ASUNTO_NOTAS},${Constantes.TIPO_NOTAS} from ${Constantes.TABLA_NOTAS} WHERE ${Constantes.ID_NOTAS} =='$id' ",
            null)
        if(fila.moveToFirst()){
            n = Notas(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getInt(4))
        }
        return n
    }
    fun getNotaTexto(context: AppCompatActivity, id: String): DeTexto? {
        var deTexto:DeTexto? = null
        val admin = AdminSQLiteConexion(context, Constantes.NOMBREBBDD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select ${Constantes.ID_NOTAS},${Constantes.FECHA_NOTAS},${Constantes.HORA_NOTAS},${Constantes.ASUNTO_NOTAS},${Constantes.TIPO_NOTAS} from ${Constantes.TABLA_NOTAS} WHERE ${Constantes.ID_NOTAS} =='${id}' ", null)

        var filaTexto = bd.rawQuery("select ${Constantes.ID_TEXTO}, ${Constantes.CONTENIDO_TEXTO} from ${Constantes.TABLA_TEXTO} WHERE ${Constantes.ID_TEXTO}=='${id}'",null)
        if(fila.moveToFirst()){
            var nota = Notas(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getInt(4))
            if(filaTexto.moveToFirst()){
                deTexto = DeTexto(nota.id, nota.fecha, nota.hora, nota.asunto, filaTexto.getString(1))
            }
        }
        return deTexto
    }


}