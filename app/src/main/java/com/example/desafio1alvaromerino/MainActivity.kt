package com.example.desafio1alvaromerino

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var miRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Esto luego lo voy a borrar, es para probar
        val texto = DeTexto(0,"25/11","11:15","asunto","Contenido")
        val lista = DeTareas(1,"23/11","2:34","asunto2",ArrayList())
        val notas:ArrayList<Notas> = ArrayList()
        notas.add(lista)
        notas.add(texto)
        notas.add(lista)
        notas.add(texto)

        miRecyclerView = findViewById(R.id.rvVista)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        val miAdapter = Adaptador.AdaptadorRecycler(notas, this)
        miRecyclerView.adapter = miAdapter
    }

    /*fun addNota(view: View){
        AlertDialog.Builder(this).setTitle(getString(R.string.addTitulo))
            .setMessage(getString(R.string.addMensaje))
            .setPositiveButton(getString(R.string.addTipoTexto)) { view, _ ->
                pedirAsunto(TipoNota.TEXTO)
                view.dismiss()
            }
            .setNegativeButton(getString(R.string.strTipoTareas)) { view, _ ->
                pedirAsunto(TipoNota.LISTA_TAREAS)
                view.dismiss()
            }
            .setCancelable(true)
            .create()
            .show()
    }
    private fun pedirAsunto(tipoNota: TipoNota) {
        var asunto = ""
        val dialogView = layoutInflater.inflate(R.layout.asunto_view, null)
        val txtAsunto = dialogView.findViewById<EditText>(R.id.edAsunto)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.strTituloAsunto))
            .setView(dialogView)
            .setPositiveButton("OK") { view, _ ->
                asunto = txtAsunto.text.toString().trim()
                asunto = if (asunto.isNotEmpty()) asunto
                else "Sin asunto"
                crearNota(tipoNota, asunto)
                rv.adapter = NotasAdapter(this, notasList)
                view.dismiss()

            }
            .setCancelable(false)
            .create()
            .show()
    }*/
}