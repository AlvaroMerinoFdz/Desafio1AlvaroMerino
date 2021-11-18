package Utiles

object Constantes {
    //Nombre de la base de Datos
    val NOMBREBBDD = "MiColorNoteAlvaro.db3"

    //DATOS DE LA TABLA NOTAS
    val TABLA_NOTAS = "NOTAS"
    val ID_NOTAS = "ID"
    val FECHA_NOTAS = "FECHA"
    val HORA_NOTAS = "HORA"
    val ASUNTO_NOTAS = "ASUNTO"
    val TIPO_NOTAS = "TIPO"


    //DATOS DE LA TABLA DE NOTAS DE TEXTO
    val TABLA_TEXTO = "DETEXTO"
    val ID_TEXTO = "ID"
    val CONTENIDO_TEXTO = "CONTENIDO"

    //DATOS DE LA TABLA DE NOTAS DE TAREAS
    val TABLA_TAREAS = "DETAREAS"
    val CODIGO_TAREAS = "COD"
    val IDCRUZADO_TAREAS = "ID_NOTAS"
    val DESCRIPCION_TAREAS = "DESCRIPCION"
    val FOTO_TAREAS = "FOTO"
    val REALIZADO_TAREAS = "REALIZADO"

    //FORMATOS
    val FORMATO_HORA = "hh:mm"
    val FORMATO_FECHA = "dd/MM/yyyy"


}