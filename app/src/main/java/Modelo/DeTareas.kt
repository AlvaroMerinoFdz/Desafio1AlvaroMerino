package Modelo

class DeTareas(id: Int, fecha: String, hora: String, asunto: String, var tareas:ArrayList<Tarea> = ArrayList()) :
    Notas(id, fecha, hora, asunto) {
        fun addTarea(tarea:Tarea){
            tareas.add(tarea)
        }
}