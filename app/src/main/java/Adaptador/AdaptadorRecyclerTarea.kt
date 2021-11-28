package Adaptador

import Adaptador.AdaptadorRecyclerTarea.*
import Modelo.Tarea
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio1alvaromerino.R

class AdaptadorRecyclerTarea(var tareas: ArrayList<Tarea>, var context: AppCompatActivity) :
RecyclerView.Adapter<ViewHolder>()  {
    companion object {
        var seleccionado: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.tarea_item, parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.texto.text = tarea.descripcion
        holder.isRealizada.isChecked = tarea.realizada
        holder.bind(tarea, context,position
            , this )

    }

    override fun getItemCount(): Int {
        return tareas.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val texto = view.findViewById<TextView>(R.id.txtTarea)
        val isRealizada = view.findViewById<Switch>(R.id.swRealizada)

        fun seleccionar(adaptadorRecyclerTarea: AdaptadorRecyclerTarea,pos:Int){
            if(pos == seleccionado){
                seleccionado = -1
            }else{
                seleccionado = pos
            }
            adaptadorRecyclerTarea.notifyDataSetChanged()
        }
        fun bind(tarea:Tarea,context: AppCompatActivity,pos:Int, adaptadorRecyclerTarea: AdaptadorRecyclerTarea){
            if(pos == seleccionado){
                with(texto){
                    this.setTextColor(resources.getColor(R.color.colorTexto))
                }
            }else{
                with(texto){
                    this.setTextColor(resources.getColor(R.color.black))
                }
            }
            itemView.setOnLongClickListener(View.OnLongClickListener
            {
                seleccionar(adaptadorRecyclerTarea,pos)
                adaptadorRecyclerTarea.notifyDataSetChanged()
                AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.borrarTitulo))
                    .setMessage(context.getString(R.string.borrarMensaje))
                    .setPositiveButton(context.getString(R.string.borrarSi)) { view, _ ->
                        //eliminar nota
                        adaptadorRecyclerTarea.tareas.removeAt(pos)
                        Conexion.Conexion.delTarea(context as AppCompatActivity,tarea)

                        Toast.makeText(context, context.getString(R.string.strEliminando), Toast.LENGTH_SHORT).show()
                        adaptadorRecyclerTarea.notifyDataSetChanged()
                        view.dismiss()
                    }
                    .setNegativeButton(context.getString(R.string.borrarNo)) { view, _ -> view.dismiss() }.setCancelable(false).create().show()
                true
            })


            }
        }

    }