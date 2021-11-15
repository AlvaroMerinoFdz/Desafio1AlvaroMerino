package Adaptador

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio1alvaromerino.R

class AdaptadorRecycler(var notas: ArrayList<Notas>, var context: Context) :
    RecyclerView.Adapter<AdaptadorRecycler.ViewHolder>() {

    companion object {
        var seleccionado: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.nota_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas.get(position)

        holder.bind(item, context, position, this)
    }

    override fun getItemCount(): Int {
        return notas.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val asunto = view.findViewById(R.id.txtAsuntoCard) as TextView
        val fecha = view.findViewById(R.id.txtFechaCard) as TextView
        val hora = view.findViewById(R.id.txtHoraCard) as TextView
        val fondo = view.findViewById(R.id.linear_card) as LinearLayout

        fun bind(texto: DeTexto, context: Context, pos: Int, adaptadorRecycler: AdaptadorRecycler) {
            asunto.text = texto.asunto
            fecha.text = texto.fecha
            hora.text = texto.hora


            if (pos == seleccionado) {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.miAzul))
                }
                with(fondo){
                    this.setBackgroundColor(resources.getColor(R.color.seleccionado))
                }
            } else {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(fondo){
                    this.setBackgroundColor(resources.getColor(R.color.white))
                }
            }

            itemView.setOnClickListener(View.OnClickListener
            {
                if (pos == seleccionado) {
                    seleccionado = -1
                } else {
                    seleccionado = pos
                }
                //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
                adaptadorRecycler.notifyDataSetChanged()
            })
        }
        fun bind(texto: Notas, context: Context, pos: Int, adaptadorRecycler: AdaptadorRecycler) {
            asunto.text = texto.asunto
            fecha.text = texto.fecha
            hora.text = texto.hora

            if (pos == seleccionado) {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.miAzul))
                }
                with(fondo){
                    this.setBackgroundColor(resources.getColor(R.color.seleccionado))
                }
            } else {
                with(asunto) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(fondo){
                    this.setBackgroundColor(resources.getColor(R.color.white))
                }
            }

            itemView.setOnClickListener(View.OnClickListener
            {
                if (pos == seleccionado) {
                    seleccionado = -1
                } else {
                    seleccionado = pos
                }
                //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
                adaptadorRecycler.notifyDataSetChanged()
            })
        }
    }
}