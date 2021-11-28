package com.example.desafio1alvaromerino

import Adaptador.AdaptadorRecyclerTarea
import Modelo.Tarea
import Utiles.Auxiliar
import Utiles.FactoriaNota
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TareaActivity : AppCompatActivity() {
    lateinit var btnAdd:ImageButton
    var tareas : ArrayList<Tarea> = ArrayList<Tarea>()
    lateinit var miAdapter:Adaptador.AdaptadorRecyclerTarea
    lateinit var miRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)

        var tarea1:Tarea = Tarea("idTarea","id_nota","Descripcion",0)
        tareas.add(tarea1)
        btnAdd = findViewById<ImageButton>(R.id.imgAddNota)
        miRecyclerView = findViewById(R.id.rvTareas)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        miAdapter = AdaptadorRecyclerTarea(tareas,this)
        miRecyclerView.adapter = miAdapter

    }

    fun add(view:View){
        val intent = Intent(this, ItemTareaActivity::class.java)
        //var tarea :Tarea = Tarea(FactoriaNota.factoria_id(),)
        startActivity(intent)
    }
    fun cancelar(view:View){
        finish()
    }
    fun guardar(view:View){

    }
}