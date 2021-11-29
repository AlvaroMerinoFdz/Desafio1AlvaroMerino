package Adaptador

import Modelo.Contacto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio1alvaromerino.R

class AdaptadorContactos (var context: AppCompatActivity,var contactos:ArrayList<Contacto>): RecyclerView.Adapter<AdaptadorContactos.ViewHolder>(){
    companion object {
        var seleccionado: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.contacto_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contactos.get(position)
        holder.bind(item,context,position,this)
    }

    override fun getItemCount(): Int {
        return contactos.size
    }
    fun getSelected():Contacto{
        return contactos.get(seleccionado)
    }
    fun isSelected():Boolean{
        return seleccionado != -1
    }


    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val nombre = view.findViewById<TextView>(R.id.txtContacto)
        val fondo = view.findViewById<ConstraintLayout>(R.id.LayoutContacto)
        fun bind(contacto: Contacto, context: AppCompatActivity, position: Int, adaptadorContactos: AdaptadorContactos) {
            nombre.text = contacto.nombre
            if(position == seleccionado){
                with(fondo) {
                    setBackgroundResource(R.color.seleccionado)
                }
            }else{
                with(fondo){
                    setBackgroundResource(R.color.miFondo)
                }
            }
            fondo.setOnClickListener(View.OnClickListener {
                marcarSeleccion(adaptadorContactos, position)
            })
        }
        private fun marcarSeleccion(adaptadorContactos: AdaptadorContactos, position: Int) {
            seleccionado = if (position == seleccionado) {
                -1
            } else {
                position
            }
            adaptadorContactos.notifyDataSetChanged()
        }
    }
}