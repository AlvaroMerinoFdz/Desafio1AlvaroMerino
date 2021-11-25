package Utiles

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

    fun genererarNota(asunto:String, tipo:Int):Notas{
        var id = factoria_id()
        var time = Timestamp(System.currentTimeMillis())
        var fecha = SimpleDateFormat("yyyy/MM/dd").format(time).toString()
        var hora = SimpleDateFormat("HH:mm").format(time).toString()

        val nota:Notas = Notas(id,asunto,fecha,hora,tipo)
        return nota
    }

    fun generarNotaTexto(nota:Notas, texto:String): DeTexto{
        var notaTexto:DeTexto = DeTexto(nota.id,nota.asunto,nota.fecha,nota.hora,texto)
        return notaTexto
    }

    fun generarTarea(id_nota:String, asunto: String){
        var id = factoria_id()
        var imagen = R.drawable.ic_baseline_image_24
        var tarea: Tarea = Tarea(id,id_nota,asunto,imagen)
    }
}