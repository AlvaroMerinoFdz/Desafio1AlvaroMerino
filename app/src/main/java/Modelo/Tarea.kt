package Modelo

import android.graphics.Bitmap
import java.io.Serializable
import java.sql.Blob

data class Tarea(var idTarea:String, var id_nota:String, var descripcion:String,var foto:ByteArray = ByteArray(0), var realizada:Boolean = false):Serializable{
}