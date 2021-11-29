package Modelo

import java.io.Serializable

data class Tarea(var idTarea:String, var id_nota:String, var descripcion:String,var foto:Int, var realizada:Boolean = false):Serializable{
}