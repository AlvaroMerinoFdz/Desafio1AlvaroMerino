package Adaptador

import Adaptador.AdaptadorRecyclerTarea.*
import Modelo.Tarea
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio1alvaromerino.R

class AdaptadorRecyclerTarea(var tareas: ArrayList<Tarea>, var context: AppCompatActivity) :
RecyclerView.Adapter<ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.tarea_item, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.texto.text = tarea.descripcion
        holder.isRealizada.isChecked = tarea.realizada

    }

    override fun getItemCount(): Int {
        return tareas.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val texto = view.findViewById<TextView>(R.id.txtTarea)
        val isRealizada = view.findViewById<Switch>(R.id.swRealizada)

    }
}