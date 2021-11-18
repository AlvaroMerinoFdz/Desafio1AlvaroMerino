package Utiles

import java.text.SimpleDateFormat
import java.util.*

object Auxiliar {

    fun horaActual(): String{
        return SimpleDateFormat(Constantes.FORMATO_HORA).format(Date())
    }

    fun fechaActual():String{
        return SimpleDateFormat(Constantes.FORMATO_FECHA).format(Date())
    }
}