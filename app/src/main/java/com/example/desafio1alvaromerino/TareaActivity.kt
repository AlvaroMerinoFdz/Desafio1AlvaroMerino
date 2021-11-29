package com.example.desafio1alvaromerino

import Adaptador.AdaptadorRecyclerTarea
import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Tarea
import Utiles.Auxiliar
import Utiles.FactoriaNota
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TareaActivity : AppCompatActivity() {
    lateinit var btnAdd:ImageButton
    var tareas : ArrayList<Tarea> = ArrayList<Tarea>()
    lateinit var miAdapter:Adaptador.AdaptadorRecyclerTarea
    lateinit var miRecyclerView: RecyclerView
    lateinit var deTarea: DeTareas
    lateinit var txtAsunto:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)
        txtAsunto = findViewById(R.id.txtAsuntoTarea)

        val i = intent.extras
        deTarea = i?.getSerializable("Tarea") as DeTareas

        txtAsunto.setText(deTarea.asunto)



        btnAdd = findViewById<ImageButton>(R.id.imgAddNota)
        miRecyclerView = findViewById(R.id.rvTareas)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        miAdapter = AdaptadorRecyclerTarea(tareas,this)
        miRecyclerView.adapter = miAdapter

    }

    fun add(view:View){
        val intent = Intent(this, ItemTareaActivity::class.java)
        var tarea :Tarea = Tarea(FactoriaNota.factoria_id(),deTarea.id,"",0)
        intent.putExtra("Tarea", tarea)
        tareas.add(tarea)
        Conexion.Conexion.addTarea(this,deTarea.id, tarea)
        startActivity(intent)
    }
    fun cancelar(view:View){
        finish()
    }
    fun guardar(view:View){

    }
}