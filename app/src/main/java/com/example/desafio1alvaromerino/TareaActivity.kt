package com.example.desafio1alvaromerino

import Adaptador.AdaptadorRecyclerTarea
import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
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
    private var idTarea: String? = null
    private var asunto:String? = null
    lateinit var txtAsunto:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)
        txtAsunto = findViewById(R.id.txtAsuntoTarea)

        val i = intent.extras
        idTarea = i?.getString("idTarea")
        asunto = i?.getString("Asunto")

        //coger las tareas de la base de datos

        //coger el asunto de la tarea
        txtAsunto.setText(asunto)

        tareas = Conexion.Conexion.getTareas(this, idTarea)

        btnAdd = findViewById<ImageButton>(R.id.imgAddNota)
        miRecyclerView = findViewById(R.id.rvTareas)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        miAdapter = AdaptadorRecyclerTarea(tareas,this)
        miRecyclerView.adapter = miAdapter

    }

    override fun onResume() {
        super.onResume()
        miRecyclerView = findViewById(R.id.rvTareas)
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        miAdapter = AdaptadorRecyclerTarea(tareas,this)
        miRecyclerView.adapter = miAdapter

    }

    fun add(view:View){
        val intent = Intent(this, ItemTareaActivity::class.java)
        intent.putExtra("idTarea", FactoriaNota.factoria_id())
        intent.putExtra("idNota", idTarea)
        startActivity(intent)
    }

    fun guardar(view:View){
        var nota:Notas = FactoriaNota.genererarNota(idTarea!!,asunto!!,1)
        Conexion.Conexion.addNota(this, nota)
        finish()
    }
}