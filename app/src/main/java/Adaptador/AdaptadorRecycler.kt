package Adaptador

import Modelo.Notas
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio1alvaromerino.R

class AdaptadorRecycler(var notas: ArrayList<Notas>, var context: AppCompatActivity) :
    RecyclerView.Adapter<AdaptadorRecycler.ViewHolder>() {

    companion object {
        var seleccionado: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.nota_card, parent, false),context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas.get(position)
        holder.asunto.text = notas[position].asunto
        holder.fecha.text = notas[position].fecha
        holder.hora.text = notas[position].hora

        holder.bind(item, context, position, this)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    fun getSelected():Notas{
        return notas.get(seleccionado)
    }


    class ViewHolder(view: View, context: AppCompatActivity) : RecyclerView.ViewHolder(view) {
        val asunto = view.findViewById(R.id.txtAsuntoCard) as TextView
        val fecha = view.findViewById(R.id.txtFechaCard) as TextView
        val hora = view.findViewById(R.id.txtHoraCard) as TextView
        val fondo = view.findViewById(R.id.linear_card) as LinearLayout
        val btnEditar = context.findViewById(R.id.imgDeleteNota) as ImageButton


        fun seleccionar(adaptadorRecycler: AdaptadorRecycler,pos: Int){
            if (pos == seleccionado) {
                seleccionado = -1
                btnEditar.isInvisible = true
            } else {
                seleccionado = pos
                btnEditar.isVisible = true
            }
            //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
            adaptadorRecycler.notifyDataSetChanged()
        }
        fun bind(nota: Notas, context: AppCompatActivity, pos: Int, adaptadorRecycler: AdaptadorRecycler) {
            /*
            asunto.text = texto.asunto
            fecha.text = texto.fecha
            hora.text = texto.hora*/

            if (pos == seleccionado) {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.colorTexto))
                }
                 with(fecha) {
                    this.setTextColor(resources.getColor(R.color.colorTexto))
                }
                 with(hora) {
                    this.setTextColor(resources.getColor(R.color.colorTexto))
                }

                with(fondo) {
                    this.setBackgroundColor(resources.getColor(R.color.seleccionado))
                }

            } else {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(fecha) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(hora) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(fondo) {
                    this.setBackgroundColor(Color.TRANSPARENT)
                }
            }


            itemView.setOnClickListener(View.OnClickListener
            {
                seleccionar(adaptadorRecycler,pos)
            })

            //Si el usuario realiza una pulsación larga, la nota se eliminará.
            itemView.setOnLongClickListener(View.OnLongClickListener
            {
                seleccionar(adaptadorRecycler,pos)

                adaptadorRecycler.notifyDataSetChanged()
                AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.borrarTitulo))
                    .setMessage(context.getString(R.string.borrarMensaje))
                    .setPositiveButton(context.getString(R.string.borrarSi)) { view, _ ->
                        //eliminar nota
                        adaptadorRecycler.notas.removeAt(pos)
                        Conexion.Conexion.delNota(context as AppCompatActivity, nota)

                        Toast.makeText(context, context.getString(R.string.strEliminando), Toast.LENGTH_SHORT).show()
                        adaptadorRecycler.notifyDataSetChanged()
                        view.dismiss()
                    }
                    .setNegativeButton(context.getString(R.string.borrarNo)) { view, _ -> view.dismiss() }.setCancelable(false).create().show()
                btnEditar.isVisible = false
                true
            })

        }
    }
}