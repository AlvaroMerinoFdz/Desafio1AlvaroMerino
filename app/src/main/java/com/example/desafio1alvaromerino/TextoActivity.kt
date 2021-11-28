package com.example.desafio1alvaromerino

import Modelo.DeTexto
import Modelo.Notas
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat

class TextoActivity : AppCompatActivity() {
    lateinit var txtAsunto: EditText
    lateinit var txtContenido : EditText
    lateinit var btnGuardar : Button
    lateinit var  nota: DeTexto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto)

        val i = intent.extras
        nota = i?.getSerializable("Texto") as DeTexto

        txtAsunto = findViewById(R.id.etAsunto)
        txtContenido = findViewById(R.id.etTexto)
        btnGuardar = findViewById(R.id.btnGuardar)

        txtAsunto.append(nota.asunto)
        txtContenido.setText(nota.texto)

    }
    fun cerrar(view:View){
        finish()
    }
    fun guardar(view:View){
        val id = nota.id
        Conexion.Conexion.modificarNotaTexto(this,id, nota)
    }

}