package com.example.desafio1alvaromerino

import Modelo.DeTareas
import Modelo.DeTexto
import Modelo.Notas
import Modelo.Tarea
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var miRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var texto:DeTexto = DeTexto(0,"25/11","11:15","asunto","Contenido")
        var lista:DeTareas = DeTareas(1,"23/11","2:34","asunto2",ArrayList<Tarea>())
        var notas:ArrayList<Notas> = ArrayList()
        notas.add(lista)
        notas.add(texto)
        notas.add(texto)
        notas.add(texto)
        notas.add(texto)
        notas.add(texto)



        miRecyclerView = findViewById(R.id.rvVista) as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdapter = Adaptador.AdaptadorRecycler(notas, this)
        miRecyclerView.adapter = miAdapter
    }
}