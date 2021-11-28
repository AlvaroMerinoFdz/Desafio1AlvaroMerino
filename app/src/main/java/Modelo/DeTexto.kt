package Modelo

import java.io.Serializable

class DeTexto(id: String, fecha: String, hora: String, asunto: String, var texto:String = " ") :
    Notas(id, fecha, hora, asunto, tipo = 0) , Serializable