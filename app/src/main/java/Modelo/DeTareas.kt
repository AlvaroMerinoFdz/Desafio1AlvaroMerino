package Modelo

import java.io.Serializable

class DeTareas(id: String, fecha: String, hora: String, asunto: String, var tareas:ArrayList<Tarea> = ArrayList()) :
    Notas(id, fecha, hora, asunto, tipo = 1),Serializable {
        fun addTarea(tarea:Tarea){
            tareas.add(tarea)
        }
}