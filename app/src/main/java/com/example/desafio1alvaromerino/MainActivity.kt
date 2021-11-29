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
            intent.putExtra("Texto",miAdapter.getSelected())
            startActivity(intent)
        }else{
            val intent = Intent(this,TareaActivity::class.java)
            intent.putExtra("Tarea",miAdapter.getSelected())
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
                var nota:Notas = FactoriaNota.genererarNota(asunto,0)
                //Añadimos la nota a la lista
                notas.add(nota)



                val intent = Intent(this, TextoActivity::class.java)
               //Generamos la de texto
                var deTexto = DeTexto(nota.id,nota.fecha,nota.hora,asunto)
                //Guardamos la nota en la Base de Datos
                Conexion.Conexion.addTexto(this,deTexto)
                //La pasamos a la siguiente ventana
                intent.putExtra("Texto", deTexto)
                startActivity(intent)
            view.dismiss()}
            .setNegativeButton(R.string.addTareas){view,_ ->
                if(txtAsunto.text.toString().trim().isEmpty()){
                    asunto = "Asunto"
                }else{
                    asunto = txtAsunto.text.trim().toString()
                }
                var nota:Notas = FactoriaNota.genererarNota(asunto,0)
                //Añadimos la nota a la lista
                notas.add(nota)

                val intent = Intent(this, TareaActivity::class.java)
                //Generamos la deTareas
                var deTareas = DeTareas(nota.id,nota.fecha,nota.hora,asunto)
                //Guardamos la nota en la Base de Datos
                Conexion.Conexion.addNota(this,nota)
                //La pasamos a la siguiente ventana
                intent.putExtra("Tarea", deTareas)
                startActivity(intent)
            view.dismiss()}.create().show()
    }
}