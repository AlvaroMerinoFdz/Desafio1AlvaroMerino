package com.example.desafio1alvaromerino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)
    }

    fun add(view:View){
        val intent = Intent(this, ItemTareaActivity::class.java)
        startActivity(intent)
    }
}