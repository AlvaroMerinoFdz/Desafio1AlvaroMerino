package com.example.desafio1alvaromerino

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var miRecyclerView : RecyclerView
    private lateinit var notas:ArrayList<Notas>
    private lateinit var txtAsunto:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Esto luego lo voy a borrar, es para probar
        val texto = DeTexto(0,"25/11","11:15","asunto","Contenido")
        val lista = DeTareas(1,"23/11","2:34","asunto2",ArrayList())
        notas= ArrayList()
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

    override fun onResume() {
        super.onResume()

        //Cojo las notas de la bbdd

        miRecyclerView = findViewById(R.id.rvVista)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        val miAdapter = Adaptador.AdaptadorRecycler(notas, this)
        miRecyclerView.adapter = miAdapter
    }

    fun addNota(view: View){
        AlertDialog.Builder(this).setTitle(getString(R.string.addTitulo))
            .setMessage(getString(R.string.addOpcion)).setView(R.layout.asunto_layout)
            .setPositiveButton(R.string.addTexto){ view,_ ->
                val intent = Intent(this, TextoActivity::class.java)

                startActivity(intent)
            view.dismiss()}
            .setNegativeButton(R.string.addTareas){view,_ ->
                val intent = Intent(this, TareaActivity::class.java)
                startActivity(intent)
            view.dismiss()}.create().show()


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