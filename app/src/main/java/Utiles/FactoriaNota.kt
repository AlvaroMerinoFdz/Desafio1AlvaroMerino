package Utiles

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import Modelo.Tarea
import com.example.desafio1alvaromerino.R
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat

object FactoriaNota {
    fun factoria_id():String{
        var id:String
        var time = Timestamp(System.currentTimeMillis())
        val format = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(time).toString()
        val alea = (0..100).random()
        id = "id${time}${alea}"
        return id
    }

    fun genererarNota(id:String, asunto: String, tipo:Int):Notas{
        var id = id
        var time = Timestamp(System.currentTimeMillis())
        var fecha = SimpleDateFormat("yyyy/MM/dd").format(time).toString()
        var hora = SimpleDateFormat("HH:mm").format(time).toString()

        val nota:Notas = Notas(id,fecha,hora,asunto,tipo)
        return nota
    }

    fun generarNotaTexto(idnota:String, asunto:String, contenido:String): DeTexto{
        var id = idnota
        var time = Timestamp(System.currentTimeMillis())
        var fecha = SimpleDateFormat("yyyy/MM/dd").format(time).toString()
        var hora = SimpleDateFormat("HH:mm").format(time).toString()
        var texto=contenido
        var notaTexto = DeTexto(id, fecha, hora, asunto, texto)
        return notaTexto
    }

    fun generarTarea(asunto: String): DeTareas{
        var id = factoria_id()
        var time = Timestamp(System.currentTimeMillis())
        var fecha = SimpleDateFormat("yyyy/MM/dd").format(time).toString()
        var hora = SimpleDateFormat("HH:mm").format(time).toString()
        var texto=""
        var tareas:ArrayList<Tarea> = ArrayList()
        var notaTarea: DeTareas = DeTareas(id, fecha, hora, asunto, tareas)
        return notaTarea
    }

}