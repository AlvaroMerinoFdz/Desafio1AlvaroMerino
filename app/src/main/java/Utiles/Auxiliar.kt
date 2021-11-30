package Utiles

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

object Auxiliar {

    fun horaActual(): String{
        return SimpleDateFormat(Constantes.FORMATO_HORA).format(Date())
    }

    fun fechaActual():String{
        return SimpleDateFormat(Constantes.FORMATO_FECHA).format(Date())
    }
    fun getBytes(bitmap: Bitmap): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    // convert from byte array to bitmap
    fun getImage(image: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}