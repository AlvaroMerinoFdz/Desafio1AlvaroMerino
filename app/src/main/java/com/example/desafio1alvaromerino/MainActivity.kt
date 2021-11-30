package com.example.desafio1alvaromerino

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import Utiles.Auxiliar
import Utiles.FactoriaNota
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var miRecyclerView : RecyclerView
    private lateinit var notas:ArrayList<Notas>
    private lateinit var txtAsunto:EditText
    lateinit var miAdapter:Adaptador.AdaptadorRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        notas= ArrayList()
        notas = Conexion.Conexion.getNotas(this)


        miRecyclerView = findViewById(R.id.rvVista)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        val miAdapter = Adaptador.AdaptadorRecycler(notas, this)
        miRecyclerView.adapter = miAdapter
    }

    override fun onResume() {
        super.onResume()

        //Cojo las notas de la bbdd
        notas= ArrayList()
        notas = Conexion.Conexion.getNotas(this)

        miRecyclerView = findViewById(R.id.rvVista)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        miAdapter = Adaptador.AdaptadorRecycler(notas, this)
        miRecyclerView.adapter = miAdapter
    }

    fun editNota(view: View){
        if(miAdapter.getSelected().tipo == 0){

            val intent = Intent(this,TextoActivity::class.java)
            val nota:Notas = miAdapter.getSelected() as Notas
            var texto:String? = null
            intent.putExtra("Texto",texto)
            intent.putExtra("Nota", nota.id)
            startActivity(intent)
        }else{
            val intent = Intent(this,TareaActivity::class.java)
            val deTareas = miAdapter.getSelected()
            intent.putExtra("idTarea", deTareas.id)
            intent.putExtra("Asunto", deTareas.asunto)
            startActivity(intent)
        }


    }

    fun addNota(view: View){
        val dialogview = layoutInflater.inflate(R.layout.asunto_layout,null)
        val txtAsunto = dialogview.findViewById<EditText>(R.id.txtAsuntoItem)
        var asunto:String
        AlertDialog.Builder(this).setTitle(getString(R.string.addTitulo))
            .setMessage(getString(R.string.addOpcion)).setView(dialogview)
            .setPositiveButton(R.string.addTexto){ view,_ ->

                if(txtAsunto.text.toString().trim().isEmpty()){
                    asunto = "Asunto"
                }else{
                    asunto = txtAsunto.text.trim().toString()
                }
                val intent = Intent(this, TextoActivity::class.java)
                intent.putExtra("Texto", txtAsunto.text.toString())
                startActivity(intent)
                view.dismiss()}
            .setNegativeButton(R.string.addTareas){view,_ ->
                if(txtAsunto.text.toString().trim().isEmpty()){
                    asunto = "Asunto"
                }else{
                    asunto = txtAsunto.text.trim().toString()
                }

                var deTareas:DeTareas =FactoriaNota.generarTarea(asunto)
                val intent = Intent(this, TareaActivity::class.java)
                intent.putExtra("idTarea", deTareas.id)
                intent.putExtra("Asunto", deTareas.asunto)


                startActivity(intent)
                view.dismiss()}.create().show()
    }

}